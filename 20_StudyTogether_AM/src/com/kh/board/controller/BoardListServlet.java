package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//현재 페이지 번호
		int cPage;
		String category=request.getParameter("category");
		if(category==null) {
			category="qna";
		}
		System.out.println("카테고리 : "+category);
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		//한페이지에 보여줄 데이터 갯수
		int numPerPage=15;
		
		List<Board> list = new BoardService().boardList(category, cPage, numPerPage);
		List<Board> replyList = new BoardService().boardReplyList();
		
		//총 게시판 갯수 구하기
		int totalBoard=new BoardService().boardCount();
		System.out.println("총 게시판 수 : "+totalBoard);
		//총 페이지 갯수 구하기
		int totalPage=(int)Math.ceil((double)totalBoard/numPerPage);
		
		int pageBarSize=10;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		//댓글 갯수 구하기
//		List<Integer> commentCount = new ArrayList();
//		for(Board b : list) {
//			commentCount=new BoardService().commentCount(b.getBoard_no());
//			System.out.println("댓글 수 "+commentCount);
//		}
		
		
		String pageBar="";
		if(pageNo>10) {
			pageBar+="<li class='page-item'><a class='page-link' href='javascript:boardList("+(pageNo-1)+")'><이전</a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<li class='page-item disabled'><a class='page-link' href='#' style='background-color: lightblue; color:black; font-weight:bold;'>"+pageNo+"</a></li>";
			}else {
				pageBar+="<li class='page-item'><a class='page-link' href='javascript:boardList("+pageNo+")'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar+="";
		}else {
			pageBar+="<li class='page-item'><a class='page-link' href='javascript:boardList("+pageNo+")'>다음></a>";
		}
		request.setAttribute("category", category);
		//request.setAttribute("commentCount", commentCount);
		request.setAttribute("list", list);
		request.setAttribute("replyList", replyList);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.getRequestDispatcher("/views/board/list.jsp")
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
