package com.kh.faq.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.faq.model.vo.FAQ;

public class FAQDao {
	
	private Properties prop = new Properties();
	
	public FAQDao() {
		try {
			String path = FAQDao.class.getResource("/sql/faq/faq-query.properties").getPath();
			prop.load(new FileReader(path));
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public List<FAQ> searchFAQ(Connection conn, int cPage, int numPerPage, String category) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		if(category.equals("전체보기")) {
			String sql = prop.getProperty("faqAll");
			List<FAQ> list = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, (cPage-1)*numPerPage+1);
				pstmt.setInt(2, cPage*numPerPage);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					FAQ f = new FAQ();
					f.setFaqNo(rs.getInt("faq_no"));
					f.setFaqTitle(rs.getString("faq_title"));
					f.setFaqCategory(rs.getString("faq_category"));
					f.setFaqContent(rs.getString("faq_content"));
					f.setFaqDate(rs.getDate("faq_date"));
					f.setFaqDeleteStatus(rs.getString("faq_delete_status"));
					
					list.add(f);
					
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				} finally {
					close(rs);
					close(pstmt);
					
				} 
				return list;
				
			} else {
				String sql = prop.getProperty("faqCategory");
				List<FAQ> list = new ArrayList();
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, category);
					pstmt.setInt(2, (cPage-1)*numPerPage+1);
					pstmt.setInt(3, cPage*numPerPage);
					
					rs = pstmt.executeQuery();
					while(rs.next()) {
						FAQ f = new FAQ();
						f.setFaqNo(rs.getInt("faq_no"));
						f.setFaqTitle(rs.getString("faq_title"));
						f.setFaqCategory(rs.getString("faq_category"));
						f.setFaqContent(rs.getString("faq_content"));
						f.setFaqDate(rs.getDate("faq_date"));
						f.setFaqDeleteStatus(rs.getString("faq_delete_status"));
						
						list.add(f);
						
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				} finally {
					close(rs);
					close(pstmt);
					
				} 
				return list;
			}
				
		}

	public int faqCount(Connection conn, String category) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
	
		
		if(category.equals("전체보기")) {
		
			String sql = prop.getProperty("faqCount");
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return result;
			
		} else {
			String sql = prop.getProperty("categoryCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, category);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);					
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return result;
			
		}
			
			
	}

	public FAQ selectFAQ(Connection conn, int no) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectFaq");
		FAQ f = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				f = new FAQ();
				f.setFaqNo(rs.getInt("faq_no"));
				f.setFaqTitle(rs.getString("faq_title"));
				f.setFaqCategory(rs.getString("faq_category"));
				f.setFaqContent(rs.getString("faq_content"));
				f.setFaqDate(rs.getDate("faq_date"));
				f.setFaqDeleteStatus(rs.getString("faq_delete_status"));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return f;
	}

	public int insertFaq(Connection conn, FAQ f) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getFaqTitle());
			pstmt.setString(2, f.getFaqCategory());
			pstmt.setString(3, f.getFaqContent());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {		
			close(pstmt);
		}
		return result;
	}

	public int updateFaq(Connection conn, FAQ f) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateFaq");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getFaqTitle());
			pstmt.setString(2, f.getFaqCategory());
			pstmt.setString(3, f.getFaqContent());
			pstmt.setInt(4, f.getFaqNo());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	
	public int deleteFaq(Connection conn, FAQ f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteFaq");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getFaqNo());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<FAQ> searchFormFAQ(Connection conn, int cPage, int numPerPage, String title) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("faqSearch");
		List<FAQ> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+title+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FAQ f = new FAQ();
				f.setFaqNo(rs.getInt("faq_no"));
				f.setFaqTitle(rs.getString("faq_title"));
				f.setFaqCategory(rs.getString("faq_category"));
				f.setFaqContent(rs.getString("faq_content"));
				f.setFaqDate(rs.getDate("faq_date"));
				f.setFaqDeleteStatus(rs.getString("faq_delete_status"));
				
				list.add(f);
				
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
				
			} 
			return list;

	}

	public int faqFormCount(Connection conn, String title) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
	
		String sql = prop.getProperty("faqFormCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+title+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;

	}
	

}
