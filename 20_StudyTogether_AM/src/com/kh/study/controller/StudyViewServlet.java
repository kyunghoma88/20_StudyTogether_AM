package com.kh.study.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.join.model.vo.StudyJoin;
import com.kh.member.model.vo.Member;
import com.kh.study.model.service.StudyService;
import com.kh.study.model.vo.Study;

/**
 * Servlet implementation class StudyViewServlet
 */
@WebServlet("/study/studyView")
public class StudyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no=Integer.parseInt(request.getParameter("no"));
		Study s=new StudyService().selectStudy(no);
		/*
		 * System.out.println(no); System.out.println(s.getEndDate());
		 */
		
		List<StudyJoin> sList=new StudyService().searchStudyJoin(no);
		
		
		HttpSession session=request.getSession();
		
		String loginId=((Member)session.getAttribute("loginedMember")).getUserId();
		//System.out.println("로그인아이디"+loginId);
		StudyJoin sj1=new StudyService().selectStudyJoin(no,loginId);

		//no where 
		boolean attendAble=true;
		
		for(StudyJoin sj : sList) {
			System.out.println("아이디출력!!!!!!"+sj.getUserId());
			if(sj.getUserId().equals(loginId)) {
				attendAble=false;
				break;
			}
		}
		
		String msg="";
		String loc="";
		
		if(s==null) {
			request.setAttribute("msg", "조회할 강좌가 없습니다.");
			request.setAttribute("loc", "study/studyList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}else {
			request.setAttribute("sj1", sj1);
			request.setAttribute("sList", sList);
			request.setAttribute("attendAble", attendAble);
			request.setAttribute("study", s);
			request.getRequestDispatcher("/views/study/studyView.jsp").forward(request, response);
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
