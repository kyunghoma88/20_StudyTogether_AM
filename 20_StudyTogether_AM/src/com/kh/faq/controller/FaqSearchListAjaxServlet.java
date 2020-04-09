package com.kh.faq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.faq.model.service.FAQService;
import com.kh.faq.model.vo.FAQ;

/**
 * Servlet implementation class FaqSearchListAjaxServlet
 */
@WebServlet("/faq/faqSearchListAjax")
public class FaqSearchListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqSearchListAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
			
		} catch(NumberFormatException e) {
			cPage=1;
		}
		
		int numPerPage = 5;
		
		
		String title=request.getParameter("title");
		if(title==null) {
			title="";
		}
		
		
		List<FAQ> list = new FAQService().searchFormFAQ(cPage, numPerPage, title);
	
		int totalFAQ = new FAQService().searchFormCount(title);
	
		
		int totalPage = (int)Math.ceil((double)totalFAQ/numPerPage);
		
		int pageBarSize = 5;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		
		int pageEnd = pageNo + pageBarSize-1;
		
		String pageBar = "";
	
		
			if(pageNo==1) {
				pageBar+="<li class='page-item disabled'><a class='page-link' href='#'>이전</a></li>";
			} else {
				pageBar+="<li class='page-item'><a class='page-link'  href='javascript:void(0)' onclick='fn_search_btn("+(pageNo-1)+")'>이전</a></li>";
			}
			
			while(!(pageNo>pageEnd||pageNo>totalPage)) {
				if(pageNo==cPage) {
					pageBar += "<li class='page-item'><span class='page-link' style='background-color: lightblue; color:black; font-weight:bold;'>"+pageNo+"</span></li>";
				} else {
					pageBar += "<li class='page-item'><a class='page-link' href='javascript:void(0)' onclick='fn_search_btn("+pageNo+")'>"+pageNo+"</a></li>";
				}
				pageNo++;
			}
			
			if(pageNo>totalPage) {
				
				pageBar+="<li class='page-item disabled'><a class='page-link' href='#'>다음</a></li>";
			} else {
				
				pageBar += "<li class='page-item'><a class='page-link' href='javascript:void(0)' onclick='fn_search_btn("+(pageNo)+")'>다음</a></li>";
				
			}
			
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
			request.getRequestDispatcher("/views/faq/faqTable.jsp").forward(request, response);
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
