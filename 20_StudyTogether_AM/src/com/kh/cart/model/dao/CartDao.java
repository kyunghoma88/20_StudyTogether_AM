package com.kh.cart.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.cart.model.vo.Cart;

public class CartDao {
	
	private Properties prop = new Properties();
	
	public CartDao() {
		try {
			String path = CartDao.class.getResource("/sql/cart/cart-query.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertCart(Connection conn, String userId, int lectorNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCart");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, lectorNo);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Cart> searchCart(Connection conn, String userId){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("searchCart");
		List<Cart> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Cart c = new Cart();
				c.setCartNo(rs.getInt("cart_no"));
				c.setUserId(userId);
				c.setLectorNo(rs.getInt("lector_no"));
				c.setLectorTitle(rs.getString("lector_title"));
				c.setLectorWriter(rs.getString("lector_writer"));
				c.setLectorCategory(rs.getString("lector_category"));
				c.setLectorPrice(rs.getInt("lector_price"));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int deleteCart(Connection conn, int cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteCart");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	

}
