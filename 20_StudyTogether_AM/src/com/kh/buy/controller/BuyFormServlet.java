package com.kh.buy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cart.model.vo.Cart;
import com.kh.cart.service.CartService;
import com.kh.member.model.vo.Member;

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
		
		String userId=((Member)request.getSession().getAttribute("loginedMember")).getUserId();

		String[] cartNoArr= request.getParameterValues("cartList");
		
		if(cartNoArr==null) {
			request.setAttribute("msg", "장바구니를 선택하지 않았습니다");
			request.setAttribute("loc", "/cart/cartView?id="+userId);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		} else {
			
			int cartNo=0;
			Cart c=null;
			List<Cart> list=new ArrayList<Cart>();
			
			for(int i=0; i<cartNoArr.length; i++) {
				cartNo=Integer.parseInt(cartNoArr[i]);
				c=new CartService().searchCartForCartNo(cartNo);
				list.add(c);
			}
			
			String totalSum=request.getParameter("total_sum");
			
			request.setAttribute("totalSum", totalSum);
			request.setAttribute("cartList", list);
			request.setAttribute("cartNoArr", cartNoArr);
			
			request.getRequestDispatcher("/views/buy/buyForm.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletReqliuest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
