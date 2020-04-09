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

import com.kh.review.model.vo.ReviewLecture;



public class ReviewLectureDao {

private Properties prop = new Properties();
	
	public ReviewLectureDao() {
		try {
			String path=ReviewLectureDao.class.getResource("/sql/review/reviewLecture-query.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int reviewLectureCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count = 0;
		String sql = prop.getProperty("reviewLectureCount");
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

	public List<ReviewLecture> selectReviewLecture(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectReviewLecture");
		List<ReviewLecture> list = new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1); //시작 데이터 번호 -> rnum기준
			pstmt.setInt(2, cPage*numPerPage); //끝 데이터 번호 -> rnum기준
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewLecture revL =new ReviewLecture();
				revL.setReviewLecNo(rs.getInt("review_lec_no"));
				revL.setReviewLecWriter(rs.getString("review_lec_writer"));
				revL.setLectureName(rs.getString("lecture_title"));
				revL.setReviewLecCategory(rs.getString("review_lec_category"));
				revL.setReviewLecContent(rs.getString("review_lec_content"));
				revL.setReviewLecStar(rs.getInt("review_lec_star"));
				revL.setReviewLecDate(rs.getDate("review_lec_date"));
				list.add(revL);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public ReviewLecture searchReviewLecture(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchReviewLecture");
		ReviewLecture revL=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				revL = new ReviewLecture();
				revL.setReviewLecNo(rs.getInt("review_lec_no"));
				revL.setReviewLecWriter(rs.getString("review_lec_writer"));
				revL.setLectureName(rs.getString("lecture_title"));
				revL.setReviewLecCategory(rs.getString("review_lec_category"));
				revL.setReviewLecContent(rs.getString("review_lec_content"));
				revL.setReviewLecStar(rs.getInt("review_lec_star"));
				revL.setReviewLecDate(rs.getDate("review_lec_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return revL;
	}

	public int updateReviewLecture(Connection conn, ReviewLecture revL) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateReviewLecture");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, revL.getReviewLecWriter());
			pstmt.setString(2, revL.getLectureName());
			pstmt.setString(3, revL.getReviewLecCategory());
			pstmt.setString(4, revL.getReviewLecContent());
			pstmt.setInt(5, revL.getReviewLecStar());
			pstmt.setInt(6, revL.getReviewLecNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public int deleteReviewLecture(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteReviewLecture");
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

	public int insertReviewLecture(Connection conn, ReviewLecture revL) {
		PreparedStatement pstmt=null; //변경되는게 없어
		int result=0;
		String sql=prop.getProperty("insertReviewLecture");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, revL.getReviewLecWriter());
			pstmt.setString(2, revL.getLectureName());
			pstmt.setString(3, revL.getReviewLecCategory());
			pstmt.setString(4, revL.getReviewLecContent());
			pstmt.setInt(5, revL.getReviewLecStar());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public String selectLectureCategory(Connection conn, String lecture) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectLectureCategory");
		String category ="";
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, lecture);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				category=rs.getString("LECTOR_CATEGORY");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return category;
	}

	public List<ReviewLecture> searchReviewLecturePage(Connection conn, int cPage, int numPerPage, String type, String key) {
		Statement stmt = null;
		ResultSet rs = null;
		List<ReviewLecture> list= new ArrayList();
		String sql="SELECT * FROM ("
				+ "SELECT ROWNUM AS RNUM, A.* FROM "
				+ "(SELECT * FROM REVIEW_LECTURE "
				+ 	"WHERE "+type+" like '%"+key+"%' ORDER BY REVIEW_LEC_DATE) A) "
				+ "	WHERE RNUM BETWEEN "+((cPage-1)*numPerPage+1)
				+ " AND "+(cPage*numPerPage);
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				ReviewLecture revL = new ReviewLecture();
				revL.setReviewLecNo(rs.getInt("REVIEW_LEC_NO"));
				revL.setReviewLecWriter(rs.getString("REVIEW_LEC_WRITER"));
				revL.setLectureName(rs.getString("LECTURE_TITLE"));
				revL.setReviewLecCategory(rs.getString("REVIEW_LEC_CATEGORY"));
				revL.setReviewLecContent(rs.getString("REVIEW_LEC_CONTENT"));
				revL.setReviewLecStar(rs.getInt("REVIEW_LEC_STAR"));
				revL.setReviewLecDate(rs.getDate("REVIEW_LEC_DATE"));
				list.add(revL);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	public int reviewLectureCount(Connection conn, String type, String key) {
		Statement stmt = null;
		ResultSet rs=null;
		int result = 0;
		String sql="SELECT COUNT(*) as cnt FROM REVIEW_LECTURE WHERE "+type+" LIKE '%"+key+"%'";
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

	public List<ReviewLecture> realtime(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("realtime");
		List<ReviewLecture> list = new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewLecture revL =new ReviewLecture();
				revL.setReviewLecNo(rs.getInt("review_lec_no"));
				revL.setReviewLecWriter(rs.getString("review_lec_writer"));
				revL.setLectureName(rs.getString("lecture_title"));
				revL.setReviewLecCategory(rs.getString("review_lec_category"));
				revL.setReviewLecContent(rs.getString("review_lec_content"));
				revL.setReviewLecStar(rs.getInt("review_lec_star"));
				revL.setReviewLecDate(rs.getDate("review_lec_date"));
				list.add(revL);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

}
