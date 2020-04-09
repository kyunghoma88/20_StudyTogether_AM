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
@WebServlet("/admin/adminManageLectorEnd")
public class AdminManageLectorEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminManageLectorEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lecNoArr=request.getParameter("lecNoArr");
		
		if(!lecNoArr.equals("")) {			
			String[] arr=lecNoArr.split(",");
		    int[] lecNums = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
		    int result=new AdminService().deleteLectorAll(lecNums);
		    request.setAttribute("msg", result+"개의 강좌가 성공적으로 폐강되었습니다");
			request.setAttribute("loc", "/admin/adminManageLector");
		} else {
			int lecNo=Integer.parseInt(request.getParameter("lecNo"));
			int result=new AdminService().deleteLector(lecNo);
			request.setAttribute("msg", lecNo+"번 강좌가 성공적으로 폐강되었습니다");
			request.setAttribute("loc", "/admin/adminManageLector");
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
