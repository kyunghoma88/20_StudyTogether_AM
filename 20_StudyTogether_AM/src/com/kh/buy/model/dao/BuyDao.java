package com.kh.buy.model.dao;

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

import com.kh.buy.model.vo.Buy;
import com.kh.cart.model.vo.Cart;

public class BuyDao {
	
	private Properties prop = new Properties();
	
	public BuyDao() {
		try {
			String path = BuyDao.class.getResource("/sql/buy/buy-query.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertBuy(Connection conn, String userId, int cartNo, int lectorNo, int amount) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBuy");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, cartNo);
			pstmt.setInt(3, lectorNo);
			pstmt.setInt(4, amount);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Buy selectBuyForUserId(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectBuyForUserId");
		Buy b = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				b = new Buy();
				b.setBuyNo(rs.getInt("BUY_NO"));
				b.setUserId(rs.getString("USER_ID"));
				b.setCartNo(rs.getInt("CART_NO"));
				b.setLectorNo(rs.getInt("LECTOR_NO"));
				b.setProcess(rs.getString("PROCESS"));
				b.setApprovedDate(rs.getDate("APPROVED_DATE"));
				b.setPaymentAmount(rs.getInt("PAYMENT_AMOUNT"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}

	public int insertLectorJoin(Connection conn, int lectorNo, String userId, int buyNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLectorJoin");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectorNo);
			pstmt.setString(2, userId);
			pstmt.setInt(3, buyNo);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


}
