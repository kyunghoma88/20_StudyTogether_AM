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

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 파일 업로드 로직 수행
		String path = getServletContext().getRealPath("/upload/board/");
		int maxSize = 1024 * 1024 * 10;// 10MB
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());

		int fileCnt = Integer.parseInt(mr.getParameter("fileCnt"));
		int no = Integer.parseInt(mr.getParameter("no"));
		String title = mr.getParameter("title");
		String write_text = mr.getParameter("write_text");
		String category = mr.getParameter("category");
		String id = mr.getParameter("id");

		String oriFileName[]= new String[fileCnt];
		for(int i=0;i<fileCnt;i++) {
			oriFileName[i]=mr.getParameter("oriFile"+(i+1));
		}
		List<String> list = new ArrayList<>(Arrays.asList(oriFileName));
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==null) {				
				list.remove(null);
			}
		}
		String fileNames=String.join(",", list);
		System.out.println("파일명 : "+fileNames);
		

		Board b = new Board(no, 0, 0, id, title, write_text, category, fileNames, new Date(), 0, 0, 0, 0);
		// upfile새로 추가한 파일이 있으면, 없으면
		// 있으면 orifile삭제, 없으면 업로드 되고 끝!~
		File f = null;// 클라이언트가 넘긴 파일이 있는지 없는지 확인
		for(int i=0;i<fileCnt;i++) {
			f = mr.getFile("fileup"+(i+1));
			if (f != null && f.length() > 0) {// 파일이 있을경우!
				File deleteFile = new File(path + mr.getParameter("oriFile"+(i+1)));
				boolean flag = deleteFile.delete();
				System.out.println(flag ? "파일삭제 성공" : "파일삭제실패");
			} else {
				b.setFile_upload(fileNames);
			}
		}
		int result = new BoardService().updateBoard(b);

		response.sendRedirect(request.getContextPath() + "/board/boardList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
