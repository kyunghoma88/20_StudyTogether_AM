package com.kh.review.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.review.model.service.ReviewStudyService;
import com.kh.review.model.vo.ReviewStudy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ReviewStudyWriteEndServlet
 */
@WebServlet("/review/reviewFormEnd")
public class ReviewStudyWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewStudyWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request)) {
			//업로드 처리하는 로직에서 multipart/formdata형식으로 넘어오지 않는다면
			//서비스로직이 돌아가면 안됨~
			request.setAttribute("msg", "후기 등록에러!!![form:enctype 관리자에게 문의]");
			request.setAttribute("loc","/review/reviewStudy/reviewStudyList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	
		String writer = request.getParameter("writer");
		String study = request.getParameter("allStudy");
		String categoey = request.getParameter("field");
		int star = Integer.parseInt(request.getParameter("star"));
		String content = request.getParameter("content");

		ReviewStudy revS = new ReviewStudy(0,writer,study,categoey,content,star,null);
		
		int result =  new ReviewStudyService().insertReviewStudy(revS);

		String msg="";
		String loc="";
		if(result>0) {
		//저장 성공 : 공지사항 저장성공 메세지출력,리스트페이지로 이동
			msg="스터디 후기 작성 성공";
			loc="/review/reviewStudy/reviewStudyList";
		}else {
		//저장 실패 : 공지사항 저장이 실패 메세지 출력, 공지사항 작성페이지로 이동
			msg="스터디 후기 작성 실패";
			loc="/review/reviewStudyWrite";
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
