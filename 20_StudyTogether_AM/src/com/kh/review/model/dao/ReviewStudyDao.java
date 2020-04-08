package com.kh.review.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.review.model.vo.ReviewStudy;
import com.kh.study.model.vo.Study;


public class ReviewStudyDao {
	private Properties prop = new Properties();
	
	public ReviewStudyDao() {
		try {
			String path=ReviewStudyDao.class.getResource("/sql/review/reviewStudy-query.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public int reviewStudyCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count = 0;
		String sql = prop.getProperty("reviewStudyCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	public List<ReviewStudy> selectReviewStudy(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectReviewStudy");
		List<ReviewStudy> list = new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1); //시박 데이터 번호 -> rnum기준
			pstmt.setInt(2, cPage*numPerPage); //끝 데이터 번호 -> rnum기준
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewStudy revS =new ReviewStudy();
				revS.setReviewStuNo(rs.getInt("review_stu_no"));
				revS.setReviewStuWriter(rs.getString("review_stu_writer"));
				revS.setStudyName(rs.getString("study_name"));
				revS.setReviewStuCategory(rs.getString("review_stu_category"));
				revS.setReviewStuContent(rs.getString("review_stu_content"));
				revS.setReviewStuStar(rs.getInt("review_stu_star"));
				revS.setReviewStuDate(rs.getDate("review_stu_date"));
				list.add(revS);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int insertReviewStudy(Connection conn, ReviewStudy revS) {
		PreparedStatement pstmt=null; //변경되는게 없어
		int result=0;
		String sql=prop.getProperty("insertReviewStudy");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, revS.getReviewStuWriter());
			pstmt.setString(2, revS.getStudyName());
			pstmt.setString(3, revS.getReviewStuCategory());
			pstmt.setString(4, revS.getReviewStuContent());
			pstmt.setInt(5, revS.getReviewStuStar());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;//null이거나 값이 있거나
	}
	public ReviewStudy searchReviewStudy(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchReviewStudy");
		ReviewStudy revS=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				revS = new ReviewStudy();
				revS.setReviewStuNo(rs.getInt("review_stu_no"));
				revS.setReviewStuWriter(rs.getString("review_stu_writer"));
				revS.setStudyName(rs.getString("study_name"));
				revS.setReviewStuCategory(rs.getString("review_stu_category"));
				revS.setReviewStuContent(rs.getString("review_stu_content"));
				revS.setReviewStuStar(rs.getInt("review_stu_star"));
				revS.setReviewStuDate(rs.getDate("review_stu_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return revS;
	}
	public int updateReviewStudy(Connection conn, ReviewStudy revS) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql=prop.getProperty("updateReviewStudy");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, revS.getReviewStuWriter());
			pstmt.setString(2, revS.getStudyName());
			pstmt.setString(3, revS.getReviewStuCategory());
			pstmt.setString(4, revS.getReviewStuContent());
			pstmt.setInt(5, revS.getReviewStuStar());
			pstmt.setInt(6, revS.getReviewStuNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	public int deleteReviewStudy(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteReviewStudy");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public String selectStudyCategory(Connection conn, String study) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectStudyCategory");
		String category ="";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, study);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				category=rs.getString("STUDY_CATEGORY");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return category;
	}

	public List<ReviewStudy> searchReviewStudyPage(Connection conn, int cPage, int numPerPage, String type, String key) {
		Statement stmt = null;
		ResultSet rs = null;
		List<ReviewStudy> list= new ArrayList();
		String sql="SELECT * FROM ("
				+ "SELECT ROWNUM AS RNUM, A.* FROM "
				+ "(SELECT * FROM REVIEW_STUDY "
				+ 	"WHERE "+type+" like '%"+key+"%' ORDER BY REVIEW_STU_DATE) A) "
				+ "	WHERE RNUM BETWEEN "+((cPage-1)*numPerPage+1)
				+ " AND "+(cPage*numPerPage);
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				ReviewStudy revS = new ReviewStudy();
				revS.setReviewStuNo(rs.getInt("REVIEW_STU_NO"));
				revS.setReviewStuWriter(rs.getString("REVIEW_STU_WRITER"));
				revS.setStudyName(rs.getString("STUDY_NAME"));
				revS.setReviewStuCategory(rs.getString("REVIEW_STU_CATEGORY"));
				revS.setReviewStuContent(rs.getString("REVIEW_STU_CONTENT"));
				revS.setReviewStuStar(rs.getInt("REVIEW_STU_STAR"));
				revS.setReviewStuDate(rs.getDate("REVIEW_STU_DATE"));
				list.add(revS);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	public int reviewStudyCount(Connection conn, String type, String key) {
		Statement stmt = null;
		ResultSet rs=null;
		int result = 0;
		String sql="SELECT COUNT(*) as cnt FROM REVIEW_STUDY WHERE "+type+" LIKE '%"+key+"%'";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) result=rs.getInt("cnt");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return result;
	}

}
