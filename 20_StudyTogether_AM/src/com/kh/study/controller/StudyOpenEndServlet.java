package com.kh.study.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.study.model.service.StudyService;
import com.kh.study.model.vo.Study;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class StudyOpenEndServlet
 */
@WebServlet("/study/studyOpenEnd")
public class StudyOpenEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyOpenEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "업로드에러 [form:enctype 관리자에게 문의] ");
			request.setAttribute("loc", "/study/studyOpen");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response );
		}
		
		String path=getServletContext().getRealPath("/upload/study/");
		int maxSize=1024*1024*30;
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
	
		String writer=mr.getParameter("studyWriter");
		String title=mr.getParameter("studyName");
		String area=mr.getParameter("studyArea");
		String category=mr.getParameter("studyCategory");
		int maxMember=Integer.parseInt(mr.getParameter("maxMember"));
		String endDate=mr.getParameter("endDate");
		String[] day=mr.getParameterValues("day");
		String days=String.join(",", day);
		String intro=mr.getParameter("intro1");
		String oriImg=mr.getOriginalFileName("thumbnail");
		String reImg=mr.getFilesystemName("thumbnail");
		
		System.out.println("개설자:"+writer);
		System.out.println("스터디명:"+title);
		System.out.println("지역:"+area);
		System.out.println("카테고리:"+category);
		System.out.println("모집인원:"+maxMember);
		System.out.println("마감일:"+endDate);
		System.out.println("가능일:"+days);
		System.out.println("상세소개:"+intro);
		System.out.println("파일명:"+oriImg);
		System.out.println("파일명:"+reImg);
		
		Study s=new Study(0,title,writer,category,days,area,intro,maxMember,null,endDate,oriImg,reImg,null,null);
		
		int result=new StudyService().insertStudy(s);
		System.out.println(result);
		System.out.println(s);
		
		String msg="";//사용자에게 띄울 메세지 내용
		String loc="";//메세지 띄운 후 이동할 페이지

		if(result>0) {
			msg="등록되었습니다.";
			loc="/study/studyList";
		}else {
			msg="등록을 실패하였습니다.";
			loc="/study/studyOpen";//슬러시 꼭
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher rd=request.getRequestDispatcher("views/common/msg.jsp");
		rd.forward(request, response);


		
		

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
