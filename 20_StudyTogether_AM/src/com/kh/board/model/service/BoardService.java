package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Comment;

public class BoardService {
	private BoardDao dao=new BoardDao();
	
	public List<Board> boardList(String category, int cPage, int numPerPage){
		Connection conn=getConnection();
		List<Board> list=dao.boardList(conn, category, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public Board boardView(int no) {
		Connection conn=getConnection();
		Board b=dao.boardView(conn, no);
		close(conn);
		return b;
	}
	public Board boardView(int no, boolean hasRead) {
		Connection conn=getConnection();
		Board b=dao.boardView(conn, no);
		if(b!=null&&!hasRead) {
			int result=dao.viewCount(conn,no);
			if(result>0) {
				b.setCnt(dao.boardView(conn, no).getCnt());
				commit(conn);
			}
			else rollback(conn);
		}
		close(conn);
		return b;
	}
	
	public int boardCount() {
		Connection conn=getConnection();
		int result=dao.boardCount(conn);
		close(conn);
		return result;
	}
	
	public void viewCount(int no) {
		Connection conn=getConnection();
		int result=dao.viewCount(conn, no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	
	public int maxNo(int no) {
		Connection conn=getConnection();
		int result=dao.maxNo(conn, no);
		close(conn);
		return result;
	}
	public int minNo(int no) {
		Connection conn=getConnection();
		int result=dao.maxNo(conn, no);
		close(conn);
		return result;
	}
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn, b);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public void updateGood(int no) {
		Connection conn=getConnection();
		int result=dao.updateGood(conn, no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	
	public void updateBad(int no) {
		Connection conn=getConnection();
		int result=dao.updateBad(conn, no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	
	public List<Board> boardFindList(int cPage, int numPerPage, String date, String content, String searchText){
		Connection conn=getConnection();
		List<Board> list=dao.boardFindList(conn, cPage, numPerPage, date, content, searchText);
		close(conn);
		return list;
		
	}
	public int boardFindCount(String date, String content, String searchText) {
		Connection conn=getConnection();
		int result=dao.boardFindCount(conn,date,content,searchText);
		close(conn);
		return result;
	}
	public int updateBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.updateBoard(conn, b);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int deleteBoard(int no) {
		Connection conn=getConnection();
		int result=dao.deleteBoard(conn, no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public void insertComment(Comment c) {
		Connection conn=getConnection();
		int result=dao.insertComment(conn, c);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	public List<Comment> selectBoardComment(int no){
		Connection conn=getConnection();
		List<Comment> list=dao.selectBoardComment(conn, no);
		close(conn);
		return list;
	}
	public void insertReplyBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertReplyBoard(conn, b);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	public List<Board> boardReplyList(){
		Connection conn=getConnection();
		List<Board> replyList=dao.boardReplyList(conn);
		close(conn);
		return replyList;
	}
	public List<Board> commentCount() {
		Connection conn=getConnection();
		List<Board> result=dao.boardCount(conn);
		close(conn);
		return result;
	}
	public int deleteComment(int no) {
		Connection conn=getConnection();
		int result=dao.deleteComment(conn, no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}















