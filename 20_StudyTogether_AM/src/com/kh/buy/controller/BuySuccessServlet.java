package com.kh.buy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.buy.model.service.BuyService;
import com.kh.buy.model.vo.Buy;
import com.kh.cart.model.vo.Cart;
import com.kh.cart.service.CartService;

/**
 * Servlet implementation class BuySuccessServlet
 */
@WebServlet("/buy/buySuccess")
public class BuySuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuySuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Cart> list=(List)(request.getSession()).getAttribute("cartList");
		int result=0;
		for(Cart c:list) {
			//결제가 성공했습니다
			String userId=c.getUserId();
			int cartNo=c.getCartNo();
			int lectorNo=c.getLectorNo();
			int amount=c.getLectorPrice();
			
			//결제정보를 Buy테이블에 입력
			result=new BuyService().insertBuy(userId,cartNo,lectorNo,amount);
			
			//장바구니에 구매한 Cart를 제거
			result=new CartService().updateCartForCartNo(cartNo, userId);
			
			//구매한 결제정보가 입력된 buyNo를 조회
			Buy b=new BuyService().selectBuyForUserId(userId);
			
			//LectorJoin에 lectorNo, userId, buyNo를 입력 
			int buyNo=b.getBuyNo();
			result=new BuyService().insertLectorJoin(lectorNo,userId, buyNo);
			
			//중복구매를 막는 로직은??
			//cart에서 중복장바구니 입력 방지
			
		}
		
		request.getRequestDispatcher("/member/memberLector").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
