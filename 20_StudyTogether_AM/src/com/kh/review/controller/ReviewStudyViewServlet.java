package com.kh.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.review.model.service.ReviewStudyService;
import com.kh.review.model.vo.ReviewStudy;

/**
 * Servlet implementation class ReviewViewServlet
 */
@WebServlet("/review/reviewStudyView")
public class ReviewStudyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewStudyViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int no = Integer.parseInt(request.getParameter("no"));
		ReviewStudy revS = new ReviewStudyService().searchReviewStudy(no);
		if(revS==null) {
			//msg.jsp페이지로 전환
			request.setAttribute("msg", "등록된 후기가 없습니다");
			request.setAttribute("loc", "/review/reviewStudy/reviewStudyList");//servlet을 호춣하면 거기서 views의 noticeList로 넘김
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}else {
			
			request.setAttribute("reviewStudy", revS);
			request.getRequestDispatcher("/views/review/reviewStudy/reviewStudyView.jsp").forward(request, response);
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
