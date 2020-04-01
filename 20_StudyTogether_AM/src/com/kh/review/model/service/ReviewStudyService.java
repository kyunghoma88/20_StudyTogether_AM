package com.kh.review.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.review.model.dao.ReviewStudyDao;
import com.kh.review.model.vo.ReviewStudy;

public class ReviewStudyService {

	private ReviewStudyDao dao = new ReviewStudyDao();
	
	public int reviewStudyCount() {
		Connection conn = getConnection();
		int result = dao.reviewStudyCount(conn);
		close(conn);
		return result;
	}

	public List<ReviewStudy> selectReviewStudy(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ReviewStudy> list = dao.selectReviewStudy(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int insertReviewStudy(ReviewStudy revS) {
		Connection conn = getConnection();
		int result = dao.insertReviewStudy(conn,revS);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public ReviewStudy searchReviewStudy(int no) {
		Connection conn = getConnection();
		ReviewStudy revS = dao.searchReviewStudy(conn,no);
		close(conn);
		System.out.println("service revS" + revS);
		return revS;
	}

	public int updateReviewStudy(ReviewStudy revS) {
		Connection conn = getConnection();
		int flag = dao.updateReviewStudy(conn,revS);
		if(flag>0) commit(conn);
		else rollback(conn);
		close(conn);
		return flag;
	}

}
