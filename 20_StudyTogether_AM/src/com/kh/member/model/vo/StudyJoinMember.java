package com.kh.member.model.vo;

public class StudyJoinMember {

	private int studyNo;
	private String studyName;
	private String userId;
	private String userName;
	private String email;
	private String phone;
	
	public StudyJoinMember() {
		// TODO Auto-generated constructor stub
	}

	public StudyJoinMember(int studyNo, String studyName, String userId, String userName, String email, String phone) {
		super();
		this.studyNo = studyNo;
		this.studyName = studyName;
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
	}

	public int getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "StudyJoinMember [studyNo=" + studyNo + ", studyName=" + studyName + ", userId=" + userId + ", userName="
				+ userName + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
	
}
