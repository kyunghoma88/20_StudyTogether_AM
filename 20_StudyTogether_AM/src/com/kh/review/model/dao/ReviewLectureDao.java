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

}
