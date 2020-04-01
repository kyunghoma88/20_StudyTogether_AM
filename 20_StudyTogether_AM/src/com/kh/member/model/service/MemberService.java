package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
private MemberDao dao = new MemberDao();
	
	public Member selectId(String id, String pw) {
		Connection conn=getConnection();
		Member m = dao.selectId(conn,id,pw);
		close(conn);
		return m;
	}
	public int memberEnroll(String id, String password, String userName, String email) {
		Connection conn=getConnection();
		int result = dao.memberEnroll(conn,id,password,userName,email);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Member lookforPassword(String id, String name, String email) {
		Connection conn = getConnection();
		Member m = dao.lookforPassword(conn, id, name, email);
		close(conn);
		return m;
	}
	
	public int updatePassword(String id, String pw) {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn, id, pw);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Member> selectMember() {
		Connection conn = getConnection();
		List<Member> list= dao.selectMember(conn);
		close(conn);
		return list;
	}

	public int memberUpdate(Member m) {
		Connection conn = getConnection();
		int result=dao.memberUpdate(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int memberDelete(String id, String password) {
		Connection conn = getConnection();
		int result=dao.memberDelete(conn,id,password);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int duplicateCheck(String id) {
		Connection conn = getConnection();
		int result=dao.duplicateCheck(conn,id);
		return result;
	}


}
