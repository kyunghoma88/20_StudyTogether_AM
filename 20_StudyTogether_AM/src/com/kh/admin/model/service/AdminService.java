package com.kh.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.admin.model.dao.AdminDao;
import com.kh.lector.model.vo.Lector;
import com.kh.member.model.vo.Member;

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

}
