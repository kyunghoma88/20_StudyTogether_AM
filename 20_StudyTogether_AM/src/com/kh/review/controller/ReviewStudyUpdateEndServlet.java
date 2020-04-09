package com.kh.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.review.model.service.ReviewStudyService;
import com.kh.review.model.vo.ReviewStudy;

/**
 * Servlet implementation class ReviewUpdateEndServlet
 */
@WebServlet("/review/reviewStudyUpdateEnd")
public class ReviewStudyUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewStudyUpdateEndServlet() {
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
		String allStudy = request.getParameter("allStudy");
		String field = request.getParameter("field");
		int star = Integer.parseInt(request.getParameter("starCnt"));
		String content = request.getParameter("content");
		
		/*
		 * System.out.println("번호 : " + no); System.out.println("작성자 : " + writer);
		 * System.out.println("스터디명 : " + allStudy); System.out.println("분야 : " +
		 * field); System.out.println("내용 : " + content); System.out.println("만족 : " +
		 * star);
		 */
		
		ReviewStudy revS = new ReviewStudy(no,writer,allStudy,field,content,star,null);
		

		
		
		String msg="";
		String loc="/review/reviewStudyView?no="+request.getParameter("no");
		
		int result = new ReviewStudyService().updateReviewStudy(revS);
		
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
