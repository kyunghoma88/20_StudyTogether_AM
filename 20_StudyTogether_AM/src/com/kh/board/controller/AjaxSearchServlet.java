package com.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class AjaxSearchServlet
 */
@WebServlet("/ajax/search")
public class AjaxSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date=request.getParameter("date");
		String content=request.getParameter("content");
		String searchText=request.getParameter("searchText");
		JSONObject jsonObj=new JSONObject();
		//현재 페이지 번호
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		//한페이지에 보여줄 데이터 갯수
		int numPerPage=15;
				
		List<Board> list = new BoardService().boardFindList(cPage, numPerPage,date,content,searchText);
		
				
		//총 게시판 갯수 구하기
		int totalBoard=new BoardService().boardFindCount(date, content, searchText);
		System.out.println("총 게시판 수 : "+totalBoard);
		//총 페이지 갯수 구하기
		int totalPage=(int)Math.ceil((double)totalBoard/numPerPage);
				
		int pageBarSize=10;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
				
		String pageBar="";
		if(pageNo>10) {
			pageBar+="<li class='page-item'><a class='page-link' href='javascript:boardFind("+(pageNo-1)+",\""+date+",\""+content+"\",\""+searchText+"\")'><이전</a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<li class='page-item disabled'><a class='page-link' href='#' style='background-color: lightblue; color:black; font-weight:bold;'>"+pageNo+"</a></li>";
			}else {
						pageBar+="<li class='page-item'><a class='page-link' href='javascript:boardFind("+pageNo+",\""+date+"\",\""+content+"\",\""+searchText+"\")'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar+="";
		}else {
			pageBar+="<li class='page-item'><a class='page-link' href='javascript:boardFind("+pageNo+",\""+date+"\",\""+content+"\",\""+searchText+"\")'>다음></a>";
		}
		
//		JSONArray ja=new JSONArray();
		jsonObj.put("date", date);
		jsonObj.put("content", content);
		jsonObj.put("searchText", searchText);
		jsonObj.put("pageBar", pageBar);
//		for(Board b : list) {
//			jsonObj.put("no", b.getBoard_no());
//			jsonObj.put("reply_no", b.getReply_no());
//			jsonObj.put("reply_level", b.getReply_level());
//			jsonObj.put("nickname", b.getNickname());
//			jsonObj.put("title", b.getTitle());
//			jsonObj.put("content", b.getContent());
//			jsonObj.put("img_file", b.getImg_file());
//			jsonObj.put("file_upload", b.getFile_upload());
//			jsonObj.put("write_date", b.getWrite_date());
//			jsonObj.put("cnt", b.getCnt());
//			jsonObj.put("good_cnt", b.getGood_cnt());
//			jsonObj.put("bad_cnt", b.getBad_cnt());
//			ja.add(jsonObj);
//		}
		jsonObj.put("list", list);
		System.out.println(list);
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(jsonObj,response.getWriter());
//		request.setAttribute("list", list);
//		request.setAttribute("pageBar", pageBar);
//		request.getRequestDispatcher("/views/board/list.jsp")
//		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
