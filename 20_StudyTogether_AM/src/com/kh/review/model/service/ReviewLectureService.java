package com.kh.review.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.review.model.dao.ReviewLectureDao;
import com.kh.review.model.vo.ReviewLecture;


public class ReviewLectureService {

	private ReviewLectureDao dao = new ReviewLectureDao();
	public int reviewLectureCount() {
		Connection conn = getConnection();
		int result = dao.reviewLectureCount(conn);
		close(conn);
		return result;
	}

	public List<ReviewLecture> selectReviewLecture(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ReviewLecture> list = dao.selectReviewLecture(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public ReviewLecture searchReviewLecture(int no) {
		Connection conn = getConnection();
		ReviewLecture revL = dao.searchReviewLecture(conn,no);
		close(conn);
		return revL;
	}

	public int updateReviewLecture(ReviewLecture revL) {
		Connection conn = getConnection();
		int result = dao.updateReviewLecture(conn,revL);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteReviewLecture(int no) {
		Connection conn=getConnection();
		int result=dao.deleteReviewLecture(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int insertReviewLecture(ReviewLecture revL) {
		Connection conn = getConnection();
		int result = dao.insertReviewLecture(conn,revL);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public String selectLectureCategory(String lecture) {
		Connection conn = getConnection();
		String category = dao.selectLectureCategory(conn,lecture);
		close(conn);
		return category;
	}

	public List<ReviewLecture> searchReviewLecturePage(int cPage, int numPerPage, String type, String key) {
		Connection conn = getConnection();
		List<ReviewLecture> list = dao.searchReviewLecturePage(conn,cPage,numPerPage,type,key);
		close(conn);
	
		return list;
	}

	public int reviewLectureCount(String type, String key) {
		Connection conn = getConnection();
		int result = dao.reviewLectureCount(conn,type,key);
		close(conn);
		return result;
	}

	public List<ReviewLecture> realtime() {
		Connection conn = getConnection();
		List<ReviewLecture> list = dao.realtime(conn);
		close(conn);
	
		return list;
	}

}
