package com.kh.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminGrantLectorEnd
 */
@WebServlet("/admin/adminGrantLectorEnd")
public class AdminGrantLectorEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGrantLectorEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lectorNo=request.getParameter("lecNo");
		String optradio=request.getParameter("optradio");
		System.out.println(lectorNo);
		System.out.println(optradio);
		int result=0;
//		if(optradio.equals("Y")) {
//			result=new AdminService().updateGrantLector(lectorNo);
//		}else if(optradio.equals("N")) {
//			result=new AdminService().updateRejectLector(lectorNo);			
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
