package com.kh.cart.service;

import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;
import static com.kh.common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.kh.cart.model.dao.CartDao;
import com.kh.cart.model.vo.Cart;

public class CartService {
	
	private CartDao dao = new CartDao();
	
	public int insertCart(String userId, int lectorNo) {
		
		Connection conn = getConnection();
		int result = dao.insertCart(conn, userId, lectorNo);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public List<Cart> searchCart(String userId){
		Connection conn = getConnection();
		List<Cart> list = dao.searchCart(conn, userId);
		close(conn);
		return list;
	}

}
