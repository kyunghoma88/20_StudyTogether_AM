package com.kh.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cart.model.vo.Cart;
import com.kh.cart.service.CartService;

/**
 * Servlet implementation class CartViewServlet
 */
@WebServlet("/cart/cartView")
public class CartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("id");
		List<Cart> list = new CartService().searchCart(userId);
		HttpSession session=request.getSession(false); //세션이 있으면 가져오고 없으면 null을 return해라
		if(session!=null && session.getAttribute("loginedMember")==null) { //세션이 없거나 로그인이 되어있지 않다면
			request.setAttribute("msg", "잘못된 접근입니다. 메인화면으로 이동합니다");
			request.setAttribute("loc", "/");//메인화면으로 이동
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/cart/cartView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
