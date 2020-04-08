package com.kh.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.study.model.vo.Study;


/**
 * Servlet implementation class MemberLectorServlet
 */
@WebServlet("/member/memberStudy")
public class MemberStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberStudyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false); //세션이 있으면 가져오고 없으면 null을 return해라
		if(session!=null && session.getAttribute("loginedMember")==null) { //세션이 없거나 로그인이 되어있지 않다면
			request.setAttribute("msg", "잘못된 접근입니다. 메인화면으로 이동합니다");
			request.setAttribute("loc", "/");//메인화면으로 이동
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		Member m=(Member)session.getAttribute("loginedMember");
		String id=m.getUserId();
		
		
		List<Study> scList=new MemberService().selectMemberCreateStudy(id); //이용자가 개설한 강좌 모두 조회
		List<Study> sjList=new MemberService().selectMemberJoinStudy(id); //이용자가 수강한 강좌 모두 조회
		
		request.setAttribute("scList", scList);
		request.setAttribute("sjList", sjList);
		
		request.getRequestDispatcher("/views/member/myStudy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
