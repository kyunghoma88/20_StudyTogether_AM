package com.kh.study.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.join.model.vo.StudyJoin;
import com.kh.lector.model.vo.Lector;
import com.kh.study.model.dao.StudyDao;
import com.kh.study.model.vo.Study;


public class StudyService {
	
	private StudyDao dao=new StudyDao();

	public int insertStudy(Study s) {
		Connection conn=getConnection();
		int result=dao.insertStudy(conn,s);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Study> searchStudy(int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<Study> list=dao.searchStudy(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int studyCount() {
		Connection conn=getConnection();
		int result=dao.studyCount(conn);
		close(conn);
		return result;
	}

	public Study selectStudy(int no) {
		Connection conn=getConnection();
		Study s=dao.selectStudy(conn,no);
		close(conn);
		return s;
	}

//	지현 - 리뷰
	public List<Study> selectStudyName(String writer) {
		Connection conn = getConnection();
		List<Study> list = dao.selectStudyName(conn,writer);
		close(conn);
		return list;
	}

	public int deleteStudy(int no) {
		Connection conn=getConnection();
		int result=dao.deleteStudy(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}

	public List<Study> searchStudyPage(String area, String searchType, String day) {
		Connection conn=getConnection();
		List<Study> list=dao.searchStudyPage(conn,area,searchType,day);
		close(conn);
		return list;
	}
	public int updateStudy(Study s) {
			Connection conn=getConnection();
			int result=dao.updateStudy(conn,s);
			if(result>0) commit(conn);
			else rollback(conn);
			close(conn);
			return result;
		}
	
	public int insertJoin(StudyJoin sj) {
		Connection conn=getConnection();
		int result=dao.insertJoin(conn,sj);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<StudyJoin> searchStudyJoin(int no) {
		Connection conn=getConnection();
		List<StudyJoin> slist=dao.searchStudyJoin(conn,no);
		close(conn);
		return slist;
	}
}
