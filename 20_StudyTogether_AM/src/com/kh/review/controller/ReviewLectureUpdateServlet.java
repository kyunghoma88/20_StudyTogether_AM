package com.kh.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.review.model.service.ReviewLectureService;
import com.kh.review.model.vo.ReviewLecture;




/**
 * Servlet implementation class ReviewLectureUpdateServlet
 */
@WebServlet("/review/reviewLectureUpdate")
public class ReviewLectureUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewLectureUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		int no = Integer.parseInt(request.getParameter("no"));
		ReviewLecture revL = new ReviewLectureService().searchReviewLecture(no);
		request.setAttribute("reviewLecture", revL);
		
		request.getRequestDispatcher("/views/review/reviewLecture/reviewLectureUpdate.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
