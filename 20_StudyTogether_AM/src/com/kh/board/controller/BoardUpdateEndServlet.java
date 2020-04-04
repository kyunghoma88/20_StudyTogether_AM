package com.kh.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateEndServlet
 */
@WebServlet("/board/updateEnd")
public class BoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//파일 업로드 로직 수행
		String path=getServletContext().getRealPath("/upload/board/");
		int maxSize=1024*1024*10;//10MB
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		int fileCnt = Integer.parseInt(mr.getParameter("fileCnt"));
		int no=Integer.parseInt(mr.getParameter("no"));
		String title=mr.getParameter("title");
		String write_text=mr.getParameter("write_text");

//		List<String> list = new ArrayList<>(Arrays.asList(oriFileName));
//		for(int i=0;i<list.size();i++) {
//			if(list.get(i)==null) {				
//				list.remove(null);
//			}
//		}
		//System.out.println(fileNames.substring(fileNames.lastIndexOf(",")));
		
		//upfile새로 추가한 파일이 있으면, 없으면
		//있으면 orifile삭제, 없으면 업로드 되고 끝!~
		Enumeration er=mr.getFileNames();//클라이언트가 넘긴 파일이 있는지 없는지 확인
		//Enumeration hidden=mr.getParameterNames();
		String hiddenName="";
		File f=null;
		File deleteFile=null;
		int cnt=0;
		while(er.hasMoreElements()) {
			System.out.println("한번"+er.nextElement());
			cnt++;
		}
		String oriFile[]=new String[cnt];
		String nFile[]=new String[cnt];
		System.out.println("카운터 : "+cnt);
		for(int i=1;i<=cnt;i++) {
			oriFile[i-1]=mr.getParameter("oriFile"+i);
			System.out.println(oriFile[i-1]);
		}
		int j=0;
		for(int i=1;i<=cnt;i++) {
			if(mr.getOriginalFileName("fileup"+i)!=null) {				
				nFile[j]=mr.getOriginalFileName("fileup"+i);
				j++;
			}
		}
		for(int i=1;i<=nFile.length;i++) {
			System.out.println("널 없어야되"+nFile[i-1]);
		}
//		Board b = new Board(no,0,0,"admin",title,write_text,null,fileNames,new Date(),0,0,0);
//		
//		while(er.hasMoreElements()) {
//			System.out.println("er출력 : "+er.nextElement());
//			f=mr.getFile((String)er.nextElement());
//			if(f!=null&&f.length()>0) {//파일이 있을경우!
//				while(hidden.hasMoreElements()) {
//					hiddenName=(String)er.nextElement();
//					if(hiddenName.contains("oriFile")) {
//						deleteFile=new File(path+mr.getParameter(hiddenName));
//						boolean flag=deleteFile.delete();
//						System.out.println(flag?"파일삭제 성공":"파일삭제실패");
//						break;
//					}
//				}
//			}else {
//					b.setFile_upload(file_upload);
//			}
//		}
		//new BoardService().updateBoard(no);
		//response.sendRedirect(request.getContextPath()+"/board/boardList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
