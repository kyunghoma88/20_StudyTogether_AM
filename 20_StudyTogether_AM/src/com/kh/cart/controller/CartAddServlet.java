package com.kh.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.service.CartService;
import com.kh.join.model.vo.LectorJoin;
import com.kh.lector.model.service.LectorService;

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
		int lectorNo = Integer.parseInt(request.getParameter("pNo"));
		int result = new CartService().insertCart(userId, lectorNo);
		List<LectorJoin> list=new LectorService().selectLectorJoin(lectorNo);
		LectorJoin lj=new LectorService().searchLectorJoin(lectorNo,userId);
 		
		System.out.println("누가들어있니?"+userId);
 		System.out.println("no는 누구니?"+lectorNo);
		
		String msg = "";
		String loc = "";
		if(result>0) {
			msg = "장바구니 담기 성공";
			loc = "/lector/lectorView?no="+lectorNo;
			request.setAttribute("list", list);
			request.setAttribute("lj", lj);

			
		} else {
			msg = "이미 장바구니에 담겨 있습니다.";
			loc = "/lector/lectorList?no="+lectorNo;
			request.setAttribute("list", list);
			request.setAttribute("lj", lj);

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
