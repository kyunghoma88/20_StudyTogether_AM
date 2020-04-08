package com.kh.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.admin.model.dao.AdminDao;
import com.kh.lector.model.vo.Lector;
import com.kh.member.model.vo.Member;
import com.kh.study.model.vo.Study;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

public class AdminService {
	
	private AdminDao dao = new AdminDao();

	public List<Lector> selectLectorA() {
		Connection conn = getConnection();
		List<Lector> list= dao.selectLectorA(conn);
		close(conn);
		return list;
	}

	public int updateGrantLector(String lectorNo) {
		Connection conn = getConnection();
		int result=dao.updateGrantLector(conn,lectorNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateRejectLector(String lectorNo) {
		Connection conn = getConnection();
		int result=dao.updateRejectLector(conn,lectorNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Lector> selectLectorM() {
		Connection conn = getConnection();
		List<Lector> list= dao.selectLectorM(conn);
		close(conn);
		return list;
	}

	public List<Study> searchStudyM() {
		Connection conn = getConnection();
		List<Study> list= dao.searchStudyM(conn);
		close(conn);
		return list;
	}

	public int deleteLector(int lecNo) {
		Connection conn = getConnection();
		int result=dao.deleteLector(conn,lecNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteLectorAll(int[] lecNums) {
		Connection conn = getConnection();
		int result=0;
		int check=0;
		for(int i=0;i<lecNums.length;i++) {
			check=dao.deleteLector(conn,lecNums[i]);
			if(check>0) {
				commit(conn);
				result++;
			}
			else rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteStudy(int stuNo) {
		Connection conn = getConnection();
		int result=dao.deleteStudy(conn,stuNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteStudyAll(int[] stuNums) {
		Connection conn = getConnection();
		int result=0;
		int check=0;
		for(int i=0;i<stuNums.length;i++) {
			check=dao.deleteStudy(conn,stuNums[i]);
			if(check>0) {
				commit(conn);
				result++;
			}
			else rollback(conn);
		}
		close(conn);
		return result;
	}

	


}
