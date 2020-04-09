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
 * Servlet implementation class ReviewLectureWriteEndServlet
 */
@WebServlet("/lecture/reviewLecFormEnd")
public class ReviewLectureWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewLectureWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String writer = request.getParameter("writer");
		String lecture = request.getParameter("allLecture");
		String category = new ReviewLectureService().selectLectureCategory(lecture); 
		int star = Integer.parseInt(request.getParameter("starCnt"));
	
		String content = request.getParameter("content");
		
		ReviewLecture revL = new ReviewLecture(0,writer,lecture,category,content,star,null);
		
		int result = new ReviewLectureService().insertReviewLecture(revL);
		
		String msg="";
		String loc="/review/reviewLecture/reviewLectureList";
		if(result>0) {
		//저장 성공 : 공지사항 저장성공 메세지출력,리스트페이지로 이동
			msg="강좌 후기 작성 성공";
		}else {
		//저장 실패 : 공지사항 저장이 실패 메세지 출력, 공지사항 작성페이지로 이동
			msg="강좌 후기 작성 실패";
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
