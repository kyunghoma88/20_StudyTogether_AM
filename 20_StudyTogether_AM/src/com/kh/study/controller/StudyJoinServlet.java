package com.kh.study.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.join.model.vo.StudyJoin;
import com.kh.study.model.service.StudyService;
import com.kh.study.model.vo.Study;

/**
 * Servlet implementation class StudyJoinServlet
 */
@WebServlet("/study/studyJoin")
public class StudyJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no=Integer.parseInt(request.getParameter("no"));
		System.out.println("no:"+no);
		String writer=request.getParameter("userId");
		System.out.println("userId"+writer);
		
		StudyJoin sj=new StudyJoin(no,writer);
		
		int result=new StudyService().insertJoin(sj);
		
		String msg="";
		String loc="";
		
		if(result>0) {
			msg="참가 되었습니다.";
			loc="/study/studyView?no="+no;
		}
		else {
			msg="이미 가입한 스터디 입니다.";
			loc="/study/studyView?no="+no;
			
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
