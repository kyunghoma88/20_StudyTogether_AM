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
 * Servlet implementation class ReviewLectureViewServlet
 */
@WebServlet("/review/reviewLectureView")
public class ReviewLectureViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewLectureViewServlet() {
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
		if(revL==null) {
			//msg.jsp페이지로 전환
			request.setAttribute("msg", "등록된 후기가 없습니다");
			request.setAttribute("loc", "/review/reviewLecture/reviewLectureList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}else {
			
			request.setAttribute("reviewLecture", revL);
			request.getRequestDispatcher("/views/review/reviewLecture/reviewLectureView.jsp").forward(request, response);
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
