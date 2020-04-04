package com.kh.join.model.vo;

public class StudyJoin {

	private int studyNo;
	private String userId;
	
	public StudyJoin() {
		// TODO Auto-generated constructor stub
	}

	public StudyJoin(int studyNo, String userId) {
		super();
		this.studyNo = studyNo;
		this.userId = userId;
	}

	public int getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "StudyJoin [studyNo=" + studyNo + ", userId=" + userId + "]";
	}
	
	
	
}
