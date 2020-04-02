package com.kh.cart.model.vo;

public class Cart {
	
	private int cartNo;
	private String userId;
	private int lectorNo;
	private String lectorTitle;
	private String lectorWriter;
	private String lectorCategory;
	private int lectorPrice;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartNo, String userId, int lectorNo, String lectorTitle, String lectorWriter, String lectorCategory,
			int lectorPrice) {
		super();
		this.cartNo = cartNo;
		this.userId = userId;
		this.lectorNo = lectorNo;
		this.lectorTitle = lectorTitle;
		this.lectorWriter = lectorWriter;
		this.lectorCategory = lectorCategory;
		this.lectorPrice = lectorPrice;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLectorNo() {
		return lectorNo;
	}

	public void setLectorNo(int lectorNo) {
		this.lectorNo = lectorNo;
	}

	public String getLectorTitle() {
		return lectorTitle;
	}

	public void setLectorTitle(String lectorTitle) {
		this.lectorTitle = lectorTitle;
	}

	public String getLectorWriter() {
		return lectorWriter;
	}

	public void setLectorWriter(String lectorWriter) {
		this.lectorWriter = lectorWriter;
	}

	public String getLectorCategory() {
		return lectorCategory;
	}

	public void setLectorCategory(String lectorCategory) {
		this.lectorCategory = lectorCategory;
	}

	public int getLectorPrice() {
		return lectorPrice;
	}

	public void setLectorPrice(int lectorPrice) {
		this.lectorPrice = lectorPrice;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", userId=" + userId + ", lectorNo=" + lectorNo + ", lectorTitle="
				+ lectorTitle + ", lectorWriter=" + lectorWriter + ", lectorCategory=" + lectorCategory
				+ ", lectorPrice=" + lectorPrice + "]";
	}
	
	
	
	
	
	
	
	
	
}
