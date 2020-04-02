package com.kh.lector.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.lector.model.service.LectorService;
import com.kh.lector.model.vo.Lector;
import com.kh.member.model.vo.Member;
/**
 * Servlet implementation class LectorFinderServlet
 */
@WebServlet("/lector/lectorFinder")
public class LectorFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectorFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("searchType");
		String key=request.getParameter("searchKeyword");
		System.out.println(type);
		System.out.println(key);
		
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=12;
		
		List<Lector> list=new LectorService().searchLectorPage(cPage,numPerPage,type,key);

		int totalLector=new LectorService().lectorCount(type,key);
		
		int totalPage=(int)Math.ceil((double)totalLector/numPerPage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;//얘가 제일 중요함
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</이전>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/lector/lectorList?cPage="+(pageNo-1)+
					"&searchType="+type+""
					+ "&searchKeyword="+key+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/lector/lectorFinder?cPage="
						+pageNo+"&searchType="+type+"&searchKeyword="+key+"'>"+pageNo+"</a>";
				/*pageBar+="<a href='"+request.getContextPath()+"/admin/memberFinder?cPage="+pageNo
							+"&searchType="+type+"&searchKeyword="+key+"'>"+pageNo+"</a>";*/
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/lector/lectorFinder?cPage="
					+pageNo+"&searchType="+type+"&searchKeyword="+key+"'>[다음]</a>";
			/*pageBar+="<a href='"+request.getContextPath()
			+"/admin/memberFinder?cPage="+pageNo
					+ "&searchType="+type+""
							+ "&searchKeyword="+key+"'>[다음]</a>*/
		}
		
		

		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/lector/lectorList.jsp").forward(request, response);
		
		
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
