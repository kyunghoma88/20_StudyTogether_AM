package com.kh.lector.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kh.lector.model.service.LectorService;
import com.kh.lector.model.vo.Lector;
import com.kh.lector.model.vo.LectorChannel;

/**
 * Servlet implementation class LectorWatch
 */
@WebServlet("/lector/lectorView")
public class LectorViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectorViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no=Integer.parseInt(request.getParameter("pno"));
		
		///////////////LectorChannel lc=new LectorService().selectChannel(no);
		//lectorChannelRefNo를 이용하여 엄마강좌의 자기자식만 출력할것임
		
		//channel페이징처리시작
		/*	1.	cPage : 현재 페이지를 의미(1페이지를 보고있는지 2페이지를 보고있는지)
		2.	numPerPage : 한 개 페이지에 출력될 데이터 수
		3.	totalData : 총 데이터수(총 로우수)
		4.	totalPage : 총 페이지 수 (페이지는 하나의 구역이라고 보면됨)
		5.	pageBarSize : 화면에 출력할 페이지 번호 개수 
		6.	pageNo : 출력할 페이지의 시작번호*/
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=10;
		
		Lector l=new LectorService().selectLector(no);
		//lectorno를 이용하여 특정lector출력
		///lectorView에서 채널  리스트로불러오는 메서드->페이징처리위해
		List<LectorChannel> clist=new LectorService().searchChannel(no,cPage,numPerPage);
		//channelRefNo를 이용하여 (엄마강좌번호)로 자식들 list로 출력 
		
		//pageBar만들기
		int RefTotalChannel=new LectorService().channelCount(no);//엄마강좌의 pNo를 가지고 그에대한 자식들의 총갯수
		int totalPage=(int)Math.ceil((double)RefTotalChannel/numPerPage);//2
		
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		
		//페이징처리시 필요한것 pNo,cNo,cPage
		//페이지바가 표시되어야할시점:clist가 5이상일때,엄마채널의 관련된 영상에 대한것만 페이지바가 구성이되어야함

		if(pageNo==1) {
			pageBar+="<li class='page-item'><a class='page-link'>이전</a></li>";
			
		}else{
			pageBar+="<a class='page-link' href='"+request.getContextPath()+"/lector/channelView?cPage="+(pageNo-1)+"&pNo="+no+"'>이전</a>";

		}
		
		while(!(pageNo>pageEnd || pageNo>totalPage)) {
			//시작페이지번호가 끝페이지번호보다 크지않거나 시작페이지번호가 전체페이지보다 크지않으면
			if(pageNo==cPage) {
				pageBar+="<li class='page-item'><a class='page-link'  style='background-color: lightblue; color:black; '>"+pageNo+"</a></li>";
			}else {
				pageBar+="<a class='page-link' href='"+request.getContextPath()+"/lector/channelView?cPage="+pageNo+"&pNo="+no+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage&&pageNo==1) {
		
				pageBar+="<li class='page-item'><a class='page-link'>다음</a></li>";
			}else  {
				pageBar+="<a class='page-link' href='"+request.getContextPath()+"/lector/channelView?cPage="+pageNo+"&pNo="+no+"'>다음</a>";
			}
		
		String msg="";
		String loc="";
		
		if(l==null) {
			request.setAttribute("msg", "조회할 강좌가 없습니다.");
			request.setAttribute("loc", "lector/lectorList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}else {
			request.setAttribute("clist", clist);
			request.setAttribute("lector", l);
			request.setAttribute("RefTotalChannel", RefTotalChannel);//엄마강좌의 자식들갯수
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("cPage", cPage);
			request.getRequestDispatcher("/views/lector/lectorView.jsp").forward(request, response);
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
