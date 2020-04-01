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
import com.kh.lector.model.vo.LectorChannel;

/**
 * Servlet implementation class ChannelViewServlet
 */
@WebServlet("/lector/channelView")
public class ChannelViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//자식강좌 단일출력 및 목록구성하는 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pNo=Integer.parseInt(request.getParameter("pNo"));//엄마번호
		int cNo=Integer.parseInt(request.getParameter("cNo"));//자식번호
		
		Lector l=new LectorService().selectLector(pNo);
		//LectorChannel lc1=new LectorService().selectChannel(cNo);
		//페이징처리시작
			
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=5;
		
		///목록구성 : pNo=lectoNo,cNo=channelNo가지고 lectorChannelList을 출력해주는 메서드(페이징처리에 사용)
		List<LectorChannel> clist=new LectorService().searchChannel(pNo,cNo,cPage,numPerPage);
		
		//단일 채널:채널의 특정강좌 1개 출력메서드
		LectorChannel lc1=new LectorService().selectChannel(pNo,cNo);

		
		
		
		//pageBar만들기
		int RefTotalChannel=new LectorService().channelCount(pNo);
		int totalPage=(int)Math.ceil((double)RefTotalChannel/numPerPage);//2
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";

		/*	1.	cPage : 현재 페이지를 의미(1페이지를 보고있는지 2페이지를 보고있는지)
			2.	numPerPage : 한 개 페이지에 출력될 데이터 수
			3.	totalData : 총 데이터수(총 로우수)
			4.	totalPage : 총 페이지 수 (페이지는 하나의 구역이라고 보면됨)
			5.	pageBarSize : 화면에 출력할 페이지 번호 개수 
			6.	pageNo : 출력할 페이지의 시작번호*/
		
		if(pageNo==1) {
			pageBar+="<li class='page-item'><a class='page-link'>이전</a></li>";
			
		}else {
			pageBar+="<a class='page-link' href='"+request.getContextPath()+"/lector/channelView?cPage="+(pageNo-1)+"&pNo="+pNo+"&cNo="+cNo+"'>이전</a>";
		}
		while(!(pageNo>pageEnd || pageNo>totalPage)) {
			if(pageNo==cPage&&pNo==lc1.getChannelNoRef()&&cNo==lc1.getChannelNo()) {
				pageBar+="<li class='page-item'><a class='page-link'  style='background-color: lightblue; color:black; '>"+pageNo+"</a></li>";
			}else {
				pageBar+="<a class='page-link' href='"+request.getContextPath()+"/lector/channelView?cPage="+pageNo+"&pNo="+pNo+"&cNo="+cNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
				pageBar+="<li class='page-item'><a class='page-link'>다음</a></li>";
			}else {
				pageBar+="<a class='page-link' href='"+request.getContextPath()+"/lector/channelView?cPage="+(pageNo)+"&pNo="+pNo+"&cNo="+cNo+"'>다음</a>";
			}
		String msg="";
		String loc="";
		
		if(clist==null) {
			request.setAttribute("msg", "조회할 강좌가 없습니다.");
			request.setAttribute("loc", "lector/lectorList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}else {
			request.setAttribute("lc1", lc1);//channel
			request.setAttribute("clist", clist);//channelList
			request.setAttribute("lector", l);//lector
			request.setAttribute("RefTotalChannel", RefTotalChannel);//
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("cPage", cPage);
			request.getRequestDispatcher("/views/lector/channelView.jsp").forward(request, response);
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
