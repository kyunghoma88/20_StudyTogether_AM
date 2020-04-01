package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/deleteMember")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("id");
		String password=request.getParameter("pw");
		System.out.println(id+", "+password);
		
		int result=new MemberService().memberDelete(id,password);
		System.out.println(result!=0?"탈퇴성공":"탈퇴실패");
		
		String msg="";
		String loc="/";
		
		if(result>0) {
			//탈퇴 후 세션 무효화
			request.getSession().invalidate();
			msg="회원에서 탈퇴하셨습니다. 이용해주셔서 감사합니다.";
		} else {
			//탈퇴실패시 내정보보기로 돌아감
			msg="입력한 정보와 일치하는 회원이 없습니다. 관리자에게 문의하세요";
			loc="/member/memberView"; 
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		//메인으로
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
