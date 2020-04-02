package com.kh.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.service.CartService;

/**
 * Servlet implementation class CartAddServlet
 */
@WebServlet("/cart/cartAdd")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CartAddServlet호출");
		String userId = request.getParameter("userId");
		int lectorNo = Integer.parseInt(request.getParameter("lectorNo"));

		int result = new CartService().insertCart(userId, lectorNo);
		
		String msg = "";
		String loc = "";
		if(result>0) {
			msg = "장바구니 담기 성공";
			loc = "/lector/lectorList";
		} else {
			msg = "이미 장바구니에 담겨 있습니다.";
			loc = "/lector/lectorList";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
