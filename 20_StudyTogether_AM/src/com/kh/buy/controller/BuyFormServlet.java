package com.kh.buy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.vo.Cart;

/**
 * Servlet implementation class PaymentFormServlet
 */
@WebServlet("/buy/buyForm")
public class BuyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("buyForm으로 넘어온 내용");
		String[] cartNoArr= request.getParameterValues("cartList");
		Cart c=null;
		
		for(int i=0; i<cartNoArr.length; i++) {
			new Cart
		}
		
		
		List<Cart> list=new ArrayList<Cart>();
		
		String totalSum=request.getParameter("total_sum");
		System.out.println(totalSum);
		
		request.setAttribute("totalSum", totalSum);
				
		request.getRequestDispatcher("/views/buy/buyForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletReqliuest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
