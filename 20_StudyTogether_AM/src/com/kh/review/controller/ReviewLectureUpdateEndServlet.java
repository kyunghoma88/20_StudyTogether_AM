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
 * Servlet implementation class ReviewLectureUpdateEndServlet
 */
@WebServlet("/review/reviewLectureUpdateEnd")
public class ReviewLectureUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewLectureUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
 		String writer = request.getParameter("writer");
 		String allLecture = request.getParameter("allLecture");
 		String field = request.getParameter("field");
 		String content = request.getParameter("content");
 		int star = Integer.parseInt(request.getParameter("starCnt"));

		 ReviewLecture revL = new ReviewLecture(no,writer,allLecture,field,content,star,null);
		
		 String msg="";
			String loc="/review/reviewLectureView?no="+request.getParameter("no");
			
			int result = new ReviewLectureService().updateReviewLecture(revL);
			//System.out.println(result);
			if(result>0) {
				msg="수정이 완료되었습니다";		
			}else {
				msg="수정이 실패되었습니다.";		
			}
			request.setAttribute("msg",msg);
			request.setAttribute("loc",loc);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
