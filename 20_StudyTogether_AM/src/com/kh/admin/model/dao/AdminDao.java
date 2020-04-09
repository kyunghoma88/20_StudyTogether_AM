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
import com.kh.study.model.vo.Study;

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

	public List<Study> searchStudyM(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchStudyM");
		List<Study> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Study s=new Study();
				s.setStudyNo(rs.getInt("STUDY_NO"));
				s.setStudyName(rs.getString("STUDY_NAME"));
				s.setStudyWriter(rs.getString("STUDY_WRITER"));
				s.setStudyCategory(rs.getString("STUDY_CATEGORY"));
				s.setStudyPossibleDay(rs.getString("STUDY_POSSIBLE_DAY"));
				s.setStudyArea(rs.getString("STUDY_AREA"));
				s.setStudyDetail(rs.getString("STUDY_DETAIL"));
				s.setMaxMember(rs.getInt("STUDY_MAX_MEMBER"));
				s.setEnrollDate(rs.getDate("STUDY_DATE"));
				s.setEndDate(rs.getString("STUDY_END_DATE"));
				s.setOriImg(rs.getString("STUDY_ORIGINAL_IMG"));
				s.setReImg(rs.getString("STUDY_RENAMED_IMG"));
				s.setDateAssign(rs.getString("STUDY_DATE_ASSIGN"));
				s.setMemberAssign(rs.getString("STUDY_MEMBER_ASSIGN"));
				list.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int deleteLector(Connection conn, int lecNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteLector");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, lecNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteStudy(Connection conn, int stuNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteStudy");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stuNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
