package com.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		Cookie[] cookies=request.getCookies();
		String cookieVal="";//데이터를 보관용
		boolean hasRead=false;//읽은표시
		//cookie값에 있는 읽은 게시판을 확인
		if(cookies!=null) {
			for(Cookie c : cookies) {
				String name=c.getName();
				String value=c.getValue();
				if("boardCookie".equals(name)) {
					cookieVal=value;
					if(value.contains("|"+no+"|")) {
						hasRead=true;
						break;
					}
				}
			}
		}
		//읽지 않았다면 쿠키에 데이터를 추가
		if(!hasRead) {
			Cookie c=new Cookie("boardCookie",cookieVal+"|"+no+"|");
			c.setMaxAge(-1);//session이 종료시에 삭제
			response.addCookie(c);
		}		
		String category=request.getParameter("category");
		Board b=new BoardService().boardView(no,hasRead);
		
		int maxNo=new BoardService().maxNo(no);
		int minNo=new BoardService().minNo(no);
		Board nextView=new BoardService().boardView(no-1);
		Board preView=new BoardService().boardView(no+1);
		
		List<Comment> commentList=new BoardService().selectBoardComment(no);
		request.setAttribute("board", b);
		request.setAttribute("maxNo", maxNo);
		request.setAttribute("minNo", minNo);
		request.setAttribute("nextView", nextView);
		request.setAttribute("preView", preView);
		request.setAttribute("cPage", cPage);
		request.setAttribute("commentList", commentList);
		request.setAttribute("category", category);
		
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
