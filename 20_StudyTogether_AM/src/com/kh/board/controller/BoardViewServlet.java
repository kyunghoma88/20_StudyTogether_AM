package com.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Comment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		System.out.println("페이지 "+cPage);
		
		Board b=new BoardService().boardView(no);
		
		int maxNo=new BoardService().maxNo(no);
		int minNo=new BoardService().minNo(no);
		Board nextView=new BoardService().boardView(no-1);
		Board preView=new BoardService().boardView(no+1);
		//String preContent=new BoardService().boardPreView(no+1);
		new BoardService().viewCount(no);
		//System.out.println("최대값 : "+maxNo+"no 값 : "+no);
		List<Comment> commentList=new BoardService().selectBoardComment(no);
		request.setAttribute("board", b);
		request.setAttribute("maxNo", maxNo);
		request.setAttribute("minNo", minNo);
		request.setAttribute("nextView", nextView);
		request.setAttribute("preView", preView);
		request.setAttribute("cPage", cPage);
		request.setAttribute("commentList", commentList);
		
		request.getRequestDispatcher("/views/board/view.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
