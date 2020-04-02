package com.kh.lector.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.lector.model.service.LectorService;
import com.kh.lector.model.vo.Lector;
import com.kh.member.model.vo.Member;
/**
 * Servlet implementation class LectorFinderServlet
 */
@WebServlet("/lector/lectorFinder")
public class LectorFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectorFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("searchType");
		String key=request.getParameter("searchKeyword");
		System.out.println("카테고리 :"+type);
		System.out.println("키워드:"+key);
		
		
		List<Lector> list=new LectorService().searchLectorPage(type,key);
		
		System.out.println(list);
	
		
		

		request.setAttribute("list", list);
		request.setAttribute("key",key);
		request.getRequestDispatcher("/views/lector/lectorFinder.jsp").forward(request, response);
		
		
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
