package com.kh.review.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

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

}
