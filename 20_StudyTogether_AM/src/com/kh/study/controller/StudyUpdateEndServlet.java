package com.kh.study.controller;

import java.io.File;
import java.io.IOException;

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
 * Servlet implementation class StudyUpdateEndServlet
 */
@WebServlet("/study/studyUpdateEnd")
public class StudyUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "수정 오류![form:enctype 관리자에게 물어보세요]");
			request.setAttribute("loc", "/");//메인
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		String path=getServletContext().getRealPath("/upload/study/");
		int maxSize=1024*1024*10;
		
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		int no=Integer.parseInt(mr.getParameter("no"));
		String title=mr.getParameter("studyName");
		String writer=mr.getParameter("studyWriter");
		String category=mr.getParameter("studyCategory");
		String day=mr.getParameter("day");
		String area=mr.getParameter("studyArea");
		String detail=mr.getParameter("intro1");
		int maxMember=Integer.parseInt(mr.getParameter("maxMember"));
		String endDate=mr.getParameter("endDate");
		
		String oriImg=mr.getOriginalFileName("thumbnail");
		String reImg=mr.getFilesystemName("thumbnail");
		System.out.println("ori"+oriImg);
		Study s=new Study(no,title,writer,category,day,area,detail,maxMember,null,endDate,oriImg,reImg,null,null);
		System.out.println(s);
		
		java.io.File f=mr.getFile("thumbnail");

		
		if(f!=null&&f.length()>0) {
			File deleteFile=new File(path+mr.getParameter("thumbnail1"));
			boolean flag=deleteFile.delete();
			System.out.println(flag?"파일삭제성공":"파일삭제실패");
		}else {
			s.setOriImg(mr.getParameter("thumbnail1"));
		}
		
		int result=new StudyService().updateStudy(s);
		System.out.println(result);
		String msg="";
		String loc="/study/studyView?no="+mr.getParameter("no");
		//jsp 안받아도 들어가나?
		
		if(result>0) {
			msg="수정이 완료되었습니다.";
		}else {
			msg="수정을 실패하였습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
