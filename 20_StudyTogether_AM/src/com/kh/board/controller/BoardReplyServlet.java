package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardReplyServlet
 */
@WebServlet("/board/replyWrite")
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no=Integer.parseInt(request.getParameter("no"));
		String id=request.getParameter("id");
		Board b=new BoardService().boardView(no);
		String category=request.getParameter("category");
		request.setAttribute("category", category);
		System.out.println("너도 안나오니? "+id);
		
		request.setAttribute("board", b);
		
		if(id!=null&&!id.isEmpty()) {			
			request.getRequestDispatcher("/views/board/boardReply.jsp")
			.forward(request, response);
		}else {
			request.setAttribute("msg", "로그인후 이용 부탁 드립니다. ");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
