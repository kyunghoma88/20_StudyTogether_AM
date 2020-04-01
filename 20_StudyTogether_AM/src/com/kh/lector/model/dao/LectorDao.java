package com.kh.lector.model.dao;
import static com.kh.common.JDBCTemplate.close;//static import!

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.kh.lector.model.vo.Lector;
import com.kh.lector.model.vo.LectorChannel;

public class LectorDao {

	private Properties prop=new Properties();
	
	public LectorDao() {
		try {
			String path=LectorDao.class.getResource("/sql/lector/lector-query.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//강좌조회
	public List<Lector> searchLector(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchLector");
		List<Lector> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Lector l=new Lector();
				l.setLectorNo(rs.getInt("lector_no"));
				l.setLectorTitle(rs.getString("lector_title"));
				l.setLectorWriter(rs.getString("lector_writer"));
				l.setLectorCategory(rs.getString("lector_category"));
				l.setLectorDetail(rs.getString("lector_detail"));
				l.setLectorPrice(rs.getInt("lector_price"));
				l.setLectorOriginalImg(rs.getString("lector_original_img"));
				l.setLectorRenamedImg("lector_renamed_img");
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

	//총 로우수 받아오는 메서드
	public int lectorCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("lectorCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt(1);//카운트행
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}



	//no이용해서 특정강좌select
	public Lector selectLector(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectLector");
		Lector l=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next())
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
		//	l.setLectorVideo(rs.getString("lector_video"));
			l.setLectorDate(rs.getDate("lector_date"));
			l.setLectorAssign(rs.getString("lector_assign"));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return l;
	}

//강좌개설
	public int insertLector(Connection conn, Lector l) {

		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertLector");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,l.getLectorTitle());
			pstmt.setString(2, l.getLectorWriter());
			pstmt.setString(3,l.getLectorCategory());
			pstmt.setString(4, l.getLectorDetail());
			pstmt.setInt(5, l.getLectorPrice());
			pstmt.setString(6,l.getLectorOriginalImg());
			pstmt.setString(7,l.getLectorRenamedImg());
			pstmt.setString(8, l.getLectorOriginalVideo());
			pstmt.setString(9, l.getLectorRenamedVideo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}



//강좌 삭제
	public int deleteLector(Connection conn, int no) {

		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteLector");
		
		try {
			Lector l=new Lector();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}



//강좌수정-이미등록한 강좌는 수정못하게했음
	public int updateLector(Connection conn, Lector l) {
		
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateLector");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, l.getLectorTitle());
			pstmt.setString(2, l.getLectorWriter());
			pstmt.setString(3, l.getLectorCategory());
			pstmt.setString(4, l.getLectorDetail());
			pstmt.setString(5, l.getLectorOriginalImg());
//			pstmt.setString(6, l.getLectorRenamedImg());
//			pstmt.setString(7, l.getLectorOriginalVideo());
//			pstmt.setString(8, l.getLectorRenamedVideo());
			pstmt.setInt(6, l.getLectorNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}




	public int insertChannelLector(Connection conn, LectorChannel lc) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertChannelLector");
		try {
			pstmt=conn.prepareStatement(sql);
		//	pstmt.setInt(1,lc.getChannelNo());
			pstmt.setInt(1, lc.getChannelNoRef());
			pstmt.setString(2, lc.getChannelTitle());
			pstmt.setString(3, lc.getChannelWriter());
			pstmt.setString(4, lc.getChannelDetail());
			pstmt.setInt(5, lc.getChannelPrice());
			pstmt.setString(6,lc.getChannelOriginalVideo() );
			pstmt.setString(7, lc.getChannelRenamedVideo());
		//	pstmt.setDate(9, lc.getChannelEnrollDate());
		//	pstmt.setInt(10, lc.getChannelLevel());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<LectorChannel> searchChannel(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchLectorChannel");
		List<LectorChannel> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LectorChannel lc=new LectorChannel();
				lc.setChannelNo(rs.getInt("lector_channel_no"));
				lc.setChannelNoRef(rs.getInt("lector_channel_no_ref"));
				lc.setChannelTitle(rs.getString("lector_channel_title"));
				lc.setChannelWriter(rs.getString("lector_channel_writer"));
				lc.setChannelDetail(rs.getString("lector_channel_detail"));
				lc.setChannelPrice(rs.getInt("lector_channel_price"));
				lc.setChannelOriginalVideo(rs.getString("original_lector_channel_video"));
				lc.setChannelRenamedVideo(rs.getString("renamed_lector_channel_video"));
				lc.setChannelEnrollDate(rs.getDate("lector_channel_date"));
				lc.setChannelLevel(rs.getInt("lector_channel_level"));
				list.add(lc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}




	public List<LectorChannel> searchChannel(Connection conn, int pNo, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchLectorChannelPage");
		List<LectorChannel> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,pNo);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
		
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LectorChannel lc=new LectorChannel();
				lc.setChannelNo(rs.getInt("lector_channel_no"));
				lc.setChannelNoRef(rs.getInt("lector_channel_no_ref"));
				lc.setChannelTitle(rs.getString("lector_channel_title"));
				lc.setChannelWriter(rs.getString("lector_channel_writer"));
				lc.setChannelDetail(rs.getString("lector_channel_detail"));
				lc.setChannelPrice(rs.getInt("lector_channel_price"));
				lc.setChannelOriginalVideo(rs.getString("original_lector_channel_video"));
				lc.setChannelRenamedVideo(rs.getString("renamed_lector_channel_video"));
				lc.setChannelEnrollDate(rs.getDate("lector_channel_date"));
				lc.setChannelLevel(rs.getInt("lector_channel_level"));
				lc.setChannel_assign(rs.getString("lector_channel_assign"));
				list.add(lc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int channelCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("channelCount");//
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt(1);//카운트행
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<LectorChannel> searchChannel(Connection conn, int pNo, int cNo, int cPage, int numPerPage) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchLectorChannelPage2");
		List<LectorChannel> list=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,pNo);
			pstmt.setInt(2, cNo);
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				LectorChannel lc=new LectorChannel();
				lc.setChannelNo(rs.getInt("lector_channel_no"));
				lc.setChannelNoRef(rs.getInt("lector_channel_no_ref"));
				lc.setChannelTitle(rs.getString("lector_channel_title"));
				lc.setChannelWriter(rs.getString("lector_channel_writer"));
				lc.setChannelDetail(rs.getString("lector_channel_detail"));
				lc.setChannelPrice(rs.getInt("lector_channel_price"));
				lc.setChannelOriginalVideo(rs.getString("original_lector_channel_video"));
				lc.setChannelRenamedVideo(rs.getString("renamed_lector_channel_video"));
				lc.setChannelEnrollDate(rs.getDate("lector_channel_date"));
				lc.setChannelLevel(rs.getInt("lector_channel_level"));
				lc.setChannel_assign(rs.getString("lector_channel_assign"));
				list.add(lc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
	}
	//lector_channel(자식)의 no만을 이용하여 자식강좌 출력하게 하는 메서드
	public LectorChannel selectLectorChannel(Connection conn, int cNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchLectorChannel");
		LectorChannel lc=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cNo);
			rs=pstmt.executeQuery();
		if(rs.next())	{
			lc.setChannelNo(rs.getInt("lector_channel_no"));
			lc.setChannelNoRef(rs.getInt("lector_channel_no_ref"));
			lc.setChannelTitle(rs.getString("lector_channel_title"));
			lc.setChannelWriter(rs.getString("lector_channel_writer"));
			lc.setChannelDetail(rs.getString("lector_channel_detail"));
			lc.setChannelPrice(rs.getInt("lector_channel_price"));
			lc.setChannelOriginalVideo(rs.getString("original_lector_channel_video"));
			lc.setChannelRenamedVideo(rs.getString("renamed_lector_channel_video"));
			lc.setChannelEnrollDate(rs.getDate("lector_channel_date"));
			lc.setChannelLevel(rs.getInt("lector_channel_level"));
			lc.setChannel_assign(rs.getString("lector_channel_assign"));
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return lc;
	}
	
	//엄마의 pNo와 자식이cNo를 이용하여 특정 자식강좌만 출력하게 하는 메서드
	public LectorChannel searchChannel(Connection conn, int pNo, int cNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchLectorChannel3");
		LectorChannel lc=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.setInt(2, cNo);
			rs=pstmt.executeQuery();
		if(rs.next())	{
			lc=new LectorChannel();
			lc.setChannelNo(rs.getInt("lector_channel_no"));
			lc.setChannelNoRef(rs.getInt("lector_channel_no_ref"));
			lc.setChannelTitle(rs.getString("lector_channel_title"));
			lc.setChannelWriter(rs.getString("lector_channel_writer"));
			lc.setChannelDetail(rs.getString("lector_channel_detail"));
			lc.setChannelPrice(rs.getInt("lector_channel_price"));
			lc.setChannelOriginalVideo(rs.getString("original_lector_channel_video"));
			lc.setChannelRenamedVideo(rs.getString("renamed_lector_channel_video"));
			lc.setChannelEnrollDate(rs.getDate("lector_channel_date"));
			lc.setChannelLevel(rs.getInt("lector_channel_level"));
			lc.setChannel_assign(rs.getString("lector_channel_assign"));
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return lc;
		
	}
	
	//lectorNo를 가지고 엄마강좌에 대한 자식들만 출력할것임
	public int channelCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("channelCount2");
		try {
			LectorChannel lc=new LectorChannel();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}




	
		


}
