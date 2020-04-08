package com.kh.study.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.study.model.service.StudyService;
import com.kh.study.model.vo.Study;

/**
 * Servlet implementation class StudyFinderServlet
 */
@WebServlet("/study/studyFinder")
public class StudyFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String area=request.getParameter("area");
		String searchType=request.getParameter("searchType");
		String day=request.getParameter("day");
		System.out.println(area+" "+searchType+" "+day);
		
		List<Study> list=new StudyService().searchStudyPage(area,searchType,day);
		System.out.println(list);
		request.setAttribute("list", list);
		request.setAttribute("searchType", searchType);
		request.setAttribute("day", day);
		request.setAttribute("area", area);

		request.getRequestDispatcher("/views/study/studyFinder.jsp").forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
