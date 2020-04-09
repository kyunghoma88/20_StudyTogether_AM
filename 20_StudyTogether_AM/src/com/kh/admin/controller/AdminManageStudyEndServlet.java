package com.kh.admin.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.lector.model.vo.Lector;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin/adminManageStudyEnd")
public class AdminManageStudyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminManageStudyEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stuNoArr=request.getParameter("stuNoArr");
		
		if(!stuNoArr.equals("")) {			
			String[] arr=stuNoArr.split(",");
		    int[] stuNums = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
		    int result=new AdminService().deleteStudyAll(stuNums);
		    request.setAttribute("msg", result+"개의 스터디가 성공적으로 폐강되었습니다");
			request.setAttribute("loc", "/admin/adminManageStudy");
		} else {
			int stuNo=Integer.parseInt(request.getParameter("stuNo"));
			int result=new AdminService().deleteStudy(stuNo);
			request.setAttribute("msg", stuNo+"번 스터디가 성공적으로 폐강되었습니다");
			request.setAttribute("loc", "/admin/adminManageStudy");
		}
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
