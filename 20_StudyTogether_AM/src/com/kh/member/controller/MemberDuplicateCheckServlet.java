package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDuplicateCheckServlet
 */
@WebServlet("/member/idDuplicateCheck")
public class MemberDuplicateCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDuplicateCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("id");
		System.out.println(id);
		int result=new MemberService().duplicateCheck(id);
		System.out.println(result);
		
		String str="";
		if(result==1) {
			System.out.println("회원 있다");
			str="NO";
		}
		else {
			System.out.println("회원 없다");
			str="YES";
		}
		
		JSONObject jobj=new JSONObject();
		jobj.put("result", str);
		
		response.getWriter().print(jobj);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
