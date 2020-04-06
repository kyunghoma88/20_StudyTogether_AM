package com.kh.buy.model.vo;

import java.sql.Date;

public class Buy {

	private int buyNo;
	private String process;
	private int cartNo;
	private String userId;
	private int lectorNo;
	private int paymentAmount;
	private Date approvedDate;
	
	public Buy() {
		// TODO Auto-generated constructor stub
	}

	public Buy(int buyNo, String process, int cartNo, String userId, int lectorNo, int paymentAmount,
			Date approvedDate) {
		super();
		this.buyNo = buyNo;
		this.process = process;
		this.cartNo = cartNo;
		this.userId = userId;
		this.lectorNo = lectorNo;
		this.paymentAmount = paymentAmount;
		this.approvedDate = approvedDate;
	}

	public int getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
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

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Override
	public String toString() {
		return "Buy [buyNo=" + buyNo + ", process=" + process + ", cartNo=" + cartNo + ", userId=" + userId
				+ ", lectorNo=" + lectorNo + ", paymentAmount=" + paymentAmount + ", approvedDate=" + approvedDate
				+ "]";
	}
	
}
