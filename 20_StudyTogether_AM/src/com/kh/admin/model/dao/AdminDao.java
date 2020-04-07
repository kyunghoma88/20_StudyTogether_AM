package com.kh.admin.model.dao;

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

import com.kh.lector.model.vo.Lector;

public class AdminDao {

private Properties prop = new Properties();
	
	public AdminDao() {
		try {
			String path = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
			prop.load(new FileReader(path));
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<Lector> selectLectorA(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql=prop.getProperty("selectLectorA");
		List<Lector> list=new ArrayList<Lector>();
		Lector l=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				l=new Lector();
				l.setLectorNo(rs.getInt("lector_no"));
				l.setLectorTitle(rs.getString("lector_title"));
				l.setLectorWriter(rs.getString("lector_writer"));
				l.setLectorCategory(rs.getString("lector_category"));
				l.setLectorDetail(rs.getString("lector_detail"));
				l.setLectorPrice(rs.getInt("lector_price"));
				l.setLectorOriginalImg(rs.getString("lector_original_img"));
				l.setLectorRenamedImg(rs.getString("lector_renamed_img"));
				l.setLectorOriginalVideo("lector_original_video");
				l.setLectorRenamedVideo("lector_renamed_video");
				l.setLectorDate(rs.getDate("lector_date"));
				l.setLectorAssign(rs.getString("lector_assign"));
				list.add(l);
			}
				
		}catch(SQLException e) {
				e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int updateGrantLector(Connection conn, String lectorNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateGrantLector");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lectorNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateRejectLector(Connection conn, String lectorNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateRejectLector");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lectorNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Lector> selectLectorM(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql=prop.getProperty("selectLectorM");
		List<Lector> list=new ArrayList<Lector>();
		Lector l=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				l=new Lector();
				l.setLectorNo(rs.getInt("lector_no"));
				l.setLectorTitle(rs.getString("lector_title"));
				l.setLectorWriter(rs.getString("lector_writer"));
				l.setLectorCategory(rs.getString("lector_category"));
				l.setLectorDetail(rs.getString("lector_detail"));
				l.setLectorPrice(rs.getInt("lector_price"));
				l.setLectorOriginalImg(rs.getString("lector_original_img"));
				l.setLectorRenamedImg(rs.getString("lector_renamed_img"));
				l.setLectorOriginalVideo("lector_original_video");
				l.setLectorRenamedVideo("lector_renamed_video");
				l.setLectorDate(rs.getDate("lector_date"));
				l.setLectorAssign(rs.getString("lector_assign"));
				list.add(l);
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
