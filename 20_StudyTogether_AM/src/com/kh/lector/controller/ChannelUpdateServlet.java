package com.kh.lector.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.lector.model.service.LectorService;
import com.kh.lector.model.vo.Lector;
import com.kh.lector.model.vo.LectorChannel;

/**
 * Servlet implementation class ChannelUpdateServlet
 */
@WebServlet("/lector/ChannelUpdate")
public class ChannelUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cNo=Integer.parseInt(request.getParameter("cNo"));
		int pNo=Integer.parseInt(request.getParameter("pNo"));
		System.out.println(pNo);
		System.out.println(cNo);

		LectorChannel lc=new LectorService().selectChannel(pNo,cNo);
		
		request.setAttribute("lc", lc);
		System.out.println("lc:"+lc);
		//화면 전환용 서블릿
		request.getRequestDispatcher("/views/lector/channelUpdate.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
