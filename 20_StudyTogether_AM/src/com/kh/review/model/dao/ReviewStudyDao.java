package com.kh.review.model.dao;

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

import com.kh.review.model.vo.ReviewStudy;

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
			pstmt.setInt(4, revS.getReviewStuStar());
			pstmt.setString(5, revS.getReviewStuContent());
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
		System.out.println("dao revS" + revS);
		return revS;//null이거나 값이 있거나
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
			pstmt.setInt(5, revS.getReviewStuNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;//null이거나 값이 있거나
	}

}
