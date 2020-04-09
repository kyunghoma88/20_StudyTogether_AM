package com.kh.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(name="MemberUpdate",urlPatterns="/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member m=new Member();
		m.setUserId(request.getParameter("id"));
		m.setUserName(request.getParameter("username"));
		m.setPassword(request.getParameter("pwd"));
		m.setEmail(request.getParameter("email"));
		m.setNickName(request.getParameter("nickname"));
		m.setGender(request.getParameter("gender"));
		if(!request.getParameter("birth").equals("")) {
			m.setBirthDate(Date.valueOf(request.getParameter("birth")));
		};		
		m.setPhone(request.getParameter("phone"));
		m.setAddress(request.getParameter("address1")+request.getParameter("address2"));
		
		int result=new MemberService().memberUpdate(m);
		
		if(result>0) {
			request.getSession(true).setAttribute("loginedMember", m);
		}else {
		}
		request.getRequestDispatcher("/member/memberView").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
