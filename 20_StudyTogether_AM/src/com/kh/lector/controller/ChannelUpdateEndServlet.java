package com.kh.lector.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.lector.model.service.LectorService;
import com.kh.lector.model.vo.LectorChannel;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ChannelUpdateEndServlet
 */
@WebServlet("/lector/channelUpdateEnd")
public class ChannelUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "공지사항 수정 오류![form:enctype 관리자에게 물어보세요]");
			request.setAttribute("loc", "/");//메인
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		String path=getServletContext().getRealPath("/upload/lector/");
		int maxSize=1024*1024*10;
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		int no=Integer.parseInt(mr.getParameter("no"));
		int noRef=Integer.parseInt(mr.getParameter("noRef"));
		String title=mr.getParameter("lectorTitle");
		String writer=mr.getParameter("lectorWriter");
		String detail=mr.getParameter("intro");
		int price=Integer.parseInt(mr.getParameter("price"));
		String oriFile=mr.getOriginalFileName("lectorVideo");
		String renameFile=mr.getFilesystemName("lectorVideo");
		LectorChannel lc=new LectorChannel(no,noRef,title,writer,detail,price,oriFile,renameFile,null,0,null);
		System.out.println(lc);
		
		java.io.File f=mr.getFile("oriFile");

		if(f!=null&&f.length()>0) {
			File deleteFile=new File(path+mr.getParameter("lectorImg1"));
			boolean flag=deleteFile.delete();
			System.out.println(flag?"파일삭제성공":"파일삭제실패");
		}else {
			lc.setChannelOriginalVideo(mr.getParameter("lectorVideo"));
		}
		
		int result=new LectorService().updateChannel(lc);
		System.out.println("result"+result);
		
		String msg="";
		String loc="/lector/channelView?pNo="+noRef+"&cNo="+no;

		if(result>0) {
			msg="수정이 완료되었습니다.";
		}else {
			msg="수정을 실패하였습니다.";
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
