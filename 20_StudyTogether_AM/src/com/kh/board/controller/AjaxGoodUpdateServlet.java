package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Mood;

/**
 * Servlet implementation class AjaxUpdateServlet
 */
@WebServlet("/ajax/goodUpdate")
public class AjaxGoodUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxGoodUpdateServlet() {
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
		String mood=request.getParameter("good");
		
		if(id.equals("")) {
			id=null;
		}
		
		Mood m = new Mood(id,no,'N');
		boolean flag=new BoardService().insertMood(m, mood);
		if(flag&&id!=null) {			
			int result=new BoardService().updateGood(no);
		}else {
			
		}
		
		Board b=new BoardService().boardView(no);
		response.getWriter().print(b.getGood_cnt());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
