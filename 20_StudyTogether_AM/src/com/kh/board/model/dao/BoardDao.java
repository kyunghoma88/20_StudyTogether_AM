package com.kh.board.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Comment;
import com.kh.board.model.vo.Mood;

public class BoardDao {
	
	private Properties prop=new Properties();
	
	public BoardDao() {
		try {
			String path=BoardDao.class.getResource("/sql/board/board.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> boardList(Connection conn, String category, int cPage, int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("boardList");
		List<Board> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			//1=1~15, 2=16~30, 3=31
			pstmt.setString(1, category);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoard_no(rs.getInt("board_no"));
				b.setReply_no(rs.getInt("reply_no"));
				b.setReply_level(rs.getInt("reply_level"));
				b.setNickname(rs.getString("nickname"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCategory(rs.getString("category"));
				b.setFile_upload(rs.getString("file_upload"));
				b.setWrite_date(rs.getDate("write_date"));
				b.setCnt(rs.getInt("cnt"));
				b.setGood_cnt(rs.getInt("good_cnt"));
				b.setBad_cnt(rs.getInt("bad_cnt"));
				b.setComment_cnt(rs.getInt("comment_cnt"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public Board boardView(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql=prop.getProperty("boardView");
		Board b=new Board();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b.setBoard_no(rs.getInt("board_no"));
				b.setReply_no(rs.getInt("reply_no"));
				b.setReply_level(rs.getInt("reply_level"));
				b.setNickname(rs.getString("nickname"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCategory(rs.getString("category"));
				b.setFile_upload(rs.getString("file_upload"));
				b.setWrite_date(rs.getDate("write_date"));
				b.setCnt(rs.getInt("cnt"));
				b.setGood_cnt(rs.getInt("good_cnt"));
				b.setBad_cnt(rs.getInt("bad_cnt"));
				b.setComment_cnt(rs.getInt("comment_cnt"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public int boardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("boardCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int viewCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("viewCount");
		try {
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
	
	public int maxNo(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("maxNo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int minNo(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("minNo");
		try {
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertBoard");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,b.getReply_no());
			pstmt.setInt(2,b.getReply_level());
			pstmt.setString(3, b.getNickname());
			pstmt.setString(4, b.getTitle());
			pstmt.setString(5, b.getContent());
			pstmt.setString(6, b.getCategory());
			pstmt.setString(7, b.getFile_upload());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateGood(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateGood");
		try {
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
	
	public int updateBad(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateBad");
		try {
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
	
	public List<Board> boardFindList(Connection conn, int cPage, int numPerPage, String date, String content, String searchText){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		String sql="";
		int d;
		try {
			if(content.equals("titleContent")) {
				sql=prop.getProperty("weekFindTitleContent");
				System.out.println("'%"+searchText+"%'");
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, searchText);
				pstmt.setString(2, searchText);
				pstmt.setInt(3, (cPage-1)*numPerPage+1);
				pstmt.setInt(4, cPage*numPerPage);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Board b=new Board();
					b.setBoard_no(rs.getInt("board_no"));
					b.setReply_no(rs.getInt("reply_no"));
					b.setReply_level(rs.getInt("reply_level"));
					b.setNickname(rs.getString("nickname"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setCategory(rs.getString("category"));
					b.setFile_upload(rs.getString("file_upload"));
					b.setWrite_date(rs.getDate("write_date"));
					b.setCnt(rs.getInt("cnt"));
					b.setGood_cnt(rs.getInt("good_cnt"));
					b.setBad_cnt(rs.getInt("bad_cnt"));
					b.setComment_cnt(rs.getInt("comment_cnt"));
					list.add(b);
				}
			}else if(content.equals("title")) {
				sql=prop.getProperty("weekFindTitle");
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, searchText);
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Board b=new Board();
					b.setBoard_no(rs.getInt("board_no"));
					b.setReply_no(rs.getInt("reply_no"));
					b.setReply_level(rs.getInt("reply_level"));
					b.setNickname(rs.getString("nickname"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setCategory(rs.getString("category"));
					b.setFile_upload(rs.getString("file_upload"));
					b.setWrite_date(rs.getDate("write_date"));
					b.setCnt(rs.getInt("cnt"));
					b.setGood_cnt(rs.getInt("good_cnt"));
					b.setBad_cnt(rs.getInt("bad_cnt"));
					b.setComment_cnt(rs.getInt("comment_cnt"));
					list.add(b);
				}
			}else if(content.equals("writer")) {
				sql=prop.getProperty("weekFindWriter");
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, searchText);
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Board b=new Board();
					b.setBoard_no(rs.getInt("board_no"));
					b.setReply_no(rs.getInt("reply_no"));
					b.setReply_level(rs.getInt("reply_level"));
					b.setNickname(rs.getString("nickname"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setCategory(rs.getString("category"));
					b.setFile_upload(rs.getString("file_upload"));
					b.setWrite_date(rs.getDate("write_date"));
					b.setCnt(rs.getInt("cnt"));
					b.setGood_cnt(rs.getInt("good_cnt"));
					b.setBad_cnt(rs.getInt("bad_cnt"));
					b.setComment_cnt(rs.getInt("comment_cnt"));
					list.add(b);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int boardFindCount(Connection conn, String date, String content, String searchText) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int result=0;
		try {
			if(content.equals("titleContent")) {
				sql=prop.getProperty("weekFindTitleContent_count");
				System.out.println("'%"+searchText+"%'");
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, searchText);
				pstmt.setString(2, searchText);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result=rs.getInt(1);
				}
			}else if(content.equals("title")) {
				sql=prop.getProperty("weekFindTitle_count");
				System.out.println("'%"+searchText+"%'");
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, searchText);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result=rs.getInt(1);
				}
			}else if(content.equals("writer")) {
				sql=prop.getProperty("weekFindWriter_count");
				System.out.println("'%"+searchText+"%'");
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, searchText);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result=rs.getInt(1);
			}
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getFile_upload());
			pstmt.setInt(4, b.getBoard_no());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteBoard(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		ResultSet rs=null;
		String sql=prop.getProperty("deleteBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
			if(result>0) {
				sql=prop.getProperty("selectReplyBoard");
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs=pstmt.executeQuery();
				if(rs.next()) {					
					sql=prop.getProperty("deleteReplyBoard");
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, no);
					result=pstmt.executeUpdate();
				}else {
					return result;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int insertComment(Connection conn, Comment c) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c.getComment_level());
			pstmt.setString(2, c.getComment_writer());
			pstmt.setString(3, c.getComment_content());
			pstmt.setInt(4, c.getBoard_ref());
			pstmt.setInt(5, c.getComment_no_ref());
			result=pstmt.executeUpdate();
			if(result>0) {
				sql=prop.getProperty("commentCount");
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, c.getBoard_ref());
				result=pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public List<Comment> selectBoardComment(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Comment> list=new ArrayList();
		String sql=prop.getProperty("selectBoardComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Comment c = new Comment();
				c.setComment_no(rs.getInt("comment_no"));
				c.setComment_level(rs.getInt("comment_level"));
				c.setComment_writer(rs.getString("comment_writer"));
				c.setComment_content(rs.getString("comment_content"));
				c.setBoard_ref(rs.getInt("board_ref"));
				c.setComment_no_ref(rs.getInt("comment_no_ref"));
				c.setComment_date(rs.getDate("comment_date"));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int insertReplyBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertReplyBoard");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,b.getReply_no());
			pstmt.setInt(2,b.getReply_level());
			pstmt.setString(3, b.getNickname());
			pstmt.setString(4, b.getTitle());
			pstmt.setString(5, b.getContent());
			pstmt.setString(6, b.getCategory());
			pstmt.setString(7, b.getFile_upload());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public List<Board> boardReplyList(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("boardReplyList");
		List<Board> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoard_no(rs.getInt("board_no"));
				b.setReply_no(rs.getInt("reply_no"));
				b.setReply_level(rs.getInt("reply_level"));
				b.setNickname(rs.getString("nickname"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCategory(rs.getString("category"));
				b.setFile_upload(rs.getString("file_upload"));
				b.setWrite_date(rs.getDate("write_date"));
				b.setCnt(rs.getInt("cnt"));
				b.setGood_cnt(rs.getInt("good_cnt"));
				b.setBad_cnt(rs.getInt("bad_cnt"));
				b.setComment_cnt(rs.getInt("comment_cnt"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public List<Integer> commentCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Integer> list = new ArrayList();
		String sql=prop.getProperty("commentCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int deleteComment(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public boolean insertMood(Connection conn, Mood m, String mood) {
		PreparedStatement pstmt=null;
		int result=0;
		ResultSet rs=null;
		String sql=prop.getProperty("insertMood");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getNickname());
			pstmt.setInt(2, m.getBoard_ref());
			result=pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
		}catch(SQLException e) {
			if(mood.equals("bad")) {				
				sql=prop.getProperty("deleteBad");
				try {				
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, m.getBoard_ref());
					result=pstmt.executeUpdate();
					if(result>0) {
						sql=prop.getProperty("deleteMood");
						pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, m.getNickname());
						pstmt.setInt(2, m.getBoard_ref());
						result=pstmt.executeUpdate();
						if(result>0) {
							return true;
						}
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}else if(mood.equals("good")){
				sql=prop.getProperty("deleteGood");
				try {				
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, m.getBoard_ref());
					result=pstmt.executeUpdate();
					if(result>0) {
						sql=prop.getProperty("deleteMood");
						pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, m.getNickname());
						pstmt.setInt(2, m.getBoard_ref());
						result=pstmt.executeUpdate();
						if(result>0) {
							return true;
						}
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return false;
	}
	public int selectCommentCount(Connection conn, int board_ref) {
		PreparedStatement pstmt = null;
		int result=0;
		ResultSet rs = null;
		String sql=prop.getProperty("selectCommentCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board_ref);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int updateCommentCount(Connection conn, int board_ref, int cnt) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql=prop.getProperty("updateCommentCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setInt(2, board_ref);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}















