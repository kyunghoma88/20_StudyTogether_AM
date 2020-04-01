package com.kh.lector.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.lector.model.service.LectorService;
import com.kh.lector.model.vo.Lector;
import com.kh.lector.model.vo.LectorChannel;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ChannelOpenEndServlet
 */
@WebServlet("/lector/lectorChannelOpenEnd")
public class ChannelOpenEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelOpenEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "업로드에러 [form:enctype 관리자에게 문의] ");
			request.setAttribute("loc", "/lector/lectorOpen");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response );
		}
		
		String path=getServletContext().getRealPath("/upload/lector/");
		int maxSize=1024*1024*30;
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		int noRef=Integer.parseInt(mr.getParameter("no"));
		System.out.println(noRef);
		String title=mr.getParameter("title");
		String writer=mr.getParameter("writer");
		String detail=mr.getParameter("detail");
		int price=Integer.parseInt(mr.getParameter("price"));
		String oriFile=mr.getOriginalFileName("video");
		String renameFile=mr.getFilesystemName("video");
		
		LectorChannel lc=new LectorChannel(0,noRef,title,writer,detail,price,oriFile,renameFile,null,0,null);
		int result=new LectorService().insertChannelLector(lc);
		System.out.println(result);
		String msg="";
		String loc="";
		
		if(result>0) {
			msg="등록 완료";
			loc="/lector/lectorList";
		}
		else {
			msg="등록 실패";
			loc="/lector/lector/lectorView?no="+noRef;
			
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
