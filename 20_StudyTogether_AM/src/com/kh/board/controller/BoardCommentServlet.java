package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Comment;

/**
 * Servlet implementation class BoardCommentServlet
 */
@WebServlet("/board/comment")
public class BoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int boardRef=Integer.parseInt(request.getParameter("board_ref"));
		String comment=request.getParameter("comment_text");
		
		String writer=request.getParameter("comment_writer");
		int level=Integer.parseInt(request.getParameter("comment_level"));
		int commentRef=Integer.parseInt(request.getParameter("comment_no_ref"));
		System.out.println(comment);
		System.out.println(boardRef);
		Comment c=new Comment(0,level,writer,comment,boardRef,commentRef,null);
		new BoardService().insertComment(c);
		JSONObject jo=new JSONObject();
		jo.put("comment", c);
		//jo.put("data", 1);
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(jo,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
