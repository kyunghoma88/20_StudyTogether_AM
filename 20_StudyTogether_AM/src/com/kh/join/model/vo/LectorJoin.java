package com.kh.join.model.vo;

public class LectorJoin {

	private int lecterJoin;
	private int lectorNo;
	private String userId;
	private int buyNo;
	private String process;
	
	public LectorJoin() {
		// TODO Auto-generated constructor stub
	}

	public LectorJoin(int lecterJoin, int lectorNo, String userId, int buyNo, String process) {
		super();
		this.lecterJoin = lecterJoin;
		this.lectorNo = lectorNo;
		this.userId = userId;
		this.buyNo = buyNo;
		this.process = process;
	}

	public int getLecterJoin() {
		return lecterJoin;
	}

	public void setLecterJoin(int lecterJoin) {
		this.lecterJoin = lecterJoin;
	}

	public int getLectorNo() {
		return lectorNo;
	}

	public void setLectorNo(int lectorNo) {
		this.lectorNo = lectorNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "LectorJoin [lecterJoin=" + lecterJoin + ", lectorNo=" + lectorNo + ", userId=" + userId + ", buyNo="
				+ buyNo + ", process=" + process + "]";
	}
	
}
