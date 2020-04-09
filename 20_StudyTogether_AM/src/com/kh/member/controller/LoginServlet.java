package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		//로그인 로직 처리하기
		//1.client가 보낸 데이터 확인하기
		String id = request.getParameter("uname");
		String pw = request.getParameter("psw");
		//2.DB에 id,pw일치한 값이 있는지 확인, 일치하는 값이 있으면 그 데이터를 가져오자
		Member m=new MemberService().selectId(id,pw);
		//m 일치하는 값이 있으면 값이 있고, 없으면  null
		
		String msg="";
		HttpSession session = request.getSession();	
		
		if(m!=null) {	
			msg=id + "님 환영합니다!";

			session.setAttribute("loginedMember", m);

			//cookie로 아이디 저장 유지하기
			String saveId = request.getParameter("saveId");
			
			//	System.out.println("saveId : " + saveId);
			
			if(saveId!=null) {
				//아이디를 쿠키에 저장하게함.
				Cookie c = new Cookie("saveId",id);
				//쿠키의 유효기간을 설정(7일)
				c.setMaxAge(7*24*60*60);//초단위라~
				response.addCookie(c);
			}else {
				//저장된 cookie값 지우고 check된 것 해제
				Cookie c = new Cookie("saveId",id);
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}else {
			msg="로그인 실패하였습니다. 다시 시도 하세요!";
		}
		
	
	String loc="/";

	request.setAttribute("msg", msg);
	request.setAttribute("loc", loc);
	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	//response.sendRedirect(request.getContextPath());
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
