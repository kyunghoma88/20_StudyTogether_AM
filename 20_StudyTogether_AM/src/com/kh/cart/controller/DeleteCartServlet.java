package com.kh.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.service.CartService;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet("/cart/deleteCart")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("삭제하기 서블릿");
		String[] cartList = request.getParameterValues("cartList");

		String msg = "";
		String loc = "";
		for(int i = 0; i < cartList.length; i++) {
			System.out.println("cartList 데이터 : " + cartList[i]);

			String cartNo = cartList[i];
			int result = new CartService().deleteCart(Integer.parseInt(cartNo));
			if(result > 0) {
				msg = "장바구니 삭제 성공";
				loc = "/index.jsp";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
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
