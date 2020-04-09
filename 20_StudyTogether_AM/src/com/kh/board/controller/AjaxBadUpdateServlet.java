package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Mood;

/**
 * Servlet implementation class AjaxBadUpdateServlet
 */
@WebServlet("/ajax/badUpdate")
public class AjaxBadUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxBadUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
		String id=request.getParameter("id");
		String mood=request.getParameter("bad");
		if(id.equals("")) {
			id=null;
		}
		
		
		Cookie[] cookies=request.getCookies();
		String cookieVal="";//데이터를 보관용
		boolean hasRead=false;//읽은표시
		//cookie값에 있는 읽은 게시판을 확인
		if(cookies!=null) {
			for(Cookie c : cookies) {
				String name=c.getName();
				String value=c.getValue();
				if("mood".equals(name)) {
					cookieVal=value;
					if(value.contains("|"+no+"|"+mood+"|")) {
						hasRead=true;
						break;
					}
				}
			}
		}
		//읽지 않았다면 쿠키에 데이터를 추가
		if(!hasRead) {
			Cookie c=new Cookie("mood",cookieVal+"|"+no+"|"+mood+"|");
			c.setMaxAge(-1);//session이 종료시에 삭제
			response.addCookie(c);
		}				
		int result=new BoardService().updateBad(no);
		
		
		Board b=new BoardService().boardView(no);
		response.getWriter().print(b.getBad_cnt());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
