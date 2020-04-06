package com.kh.buy.model.service;

import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;
import static com.kh.common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.kh.buy.model.dao.BuyDao;
import com.kh.buy.model.vo.Buy;
import com.kh.cart.model.vo.Cart;

public class BuyService {
	
	private BuyDao dao = new BuyDao();
	
	public int insertBuy(String userId, int cartNo, int lectorNo, int amount) {
		
		Connection conn = getConnection();
		int result = dao.insertBuy(conn, userId, cartNo, lectorNo, amount);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Buy selectBuyForUserId(String userId) {
		Connection conn = getConnection();
		Buy b= dao.selectBuyForUserId(conn, userId);
		close(conn);
		return b;
	}

	public int insertLectorJoin(int lectorNo, String userId, int buyNo) {
		Connection conn = getConnection();
		int result = dao.insertLectorJoin(conn, lectorNo, userId, buyNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

}
