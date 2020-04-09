package com.kh.buy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cart.model.vo.Cart;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/buy/buyTest")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("order_name");
		String email=request.getParameter("order_email");
		String phone=request.getParameter("order_phone");
		String postcode=request.getParameter("sample3_postcode");
		String address1=request.getParameter("sample3_address");
		String address2=request.getParameter("sample3_detailAddress");
		String address3=request.getParameter("sample3_extraAddress");
		String address=address1+" "+address2+" "+address3;
		
		String stotalPrice=request.getParameter("totalPrice");
		if(stotalPrice!=null) {
			int totalPrice=Integer.parseInt(stotalPrice);			
		}
		
		List<Cart> list=(List)(request.getSession()).getAttribute("cartList");
		
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("phone", phone);
		request.setAttribute("postcode", postcode);
		request.setAttribute("address", address);
		request.setAttribute("totalPrice", stotalPrice);
		request.setAttribute("list", list);
				
		request.getRequestDispatcher("/views/buy/buyTest.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
