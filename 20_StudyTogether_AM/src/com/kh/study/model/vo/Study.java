package com.kh.study.model.vo;

import java.sql.Date;

public class Study {
	
		
		private int studyNo;
	 	private String studyName;
	 	private String studyWriter;
	 	private String studyCategory;
	 	private String studyPossibleDay;
	 	private String studyArea;
	 	private String studyDetail;
	 	private int maxMember;
	 	private Date enrollDate;
	 	private String endDate;
	 	private String oriImg;
	 	private String reImg;
	 	private String dateAssign;
	 	private String memberAssign;
	 	
	 	public Study() {
			// TODO Auto-generated constructor stub
		}

		public Study(int studyNo, String studyName, String studyWriter, String studyCategory, String studyPossibleDay,
				String studyArea, String studyDetail, int maxMember, Date enrollDate, String endDate, String oriImg,
				String reImg, String dateAssign, String memberAssign) {
			super();
			this.studyNo = studyNo;
			this.studyName = studyName;
			this.studyWriter = studyWriter;
			this.studyCategory = studyCategory;
			this.studyPossibleDay = studyPossibleDay;
			this.studyArea = studyArea;
			this.studyDetail = studyDetail;
			this.maxMember = maxMember;
			this.enrollDate = enrollDate;
			this.endDate = endDate;
			this.oriImg = oriImg;
			this.reImg = reImg;
			this.dateAssign = dateAssign;
			this.memberAssign = memberAssign;
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

		public String getStudyWriter() {
			return studyWriter;
		}

		public void setStudyWriter(String studyWriter) {
			this.studyWriter = studyWriter;
		}

		public String getStudyCategory() {
			return studyCategory;
		}

		public void setStudyCategory(String studyCategory) {
			this.studyCategory = studyCategory;
		}

		public String getStudyPossibleDay() {
			return studyPossibleDay;
		}

		public void setStudyPossibleDay(String studyPossibleDay) {
			this.studyPossibleDay = studyPossibleDay;
		}

		public String getStudyArea() {
			return studyArea;
		}

		public void setStudyArea(String studyArea) {
			this.studyArea = studyArea;
		}

		public String getStudyDetail() {
			return studyDetail;
		}

		public void setStudyDetail(String studyDetail) {
			this.studyDetail = studyDetail;
		}

		public int getMaxMember() {
			return maxMember;
		}

		public void setMaxMember(int maxMember) {
			this.maxMember = maxMember;
		}

		public Date getEnrollDate() {
			return enrollDate;
		}

		public void setEnrollDate(Date enrollDate) {
			this.enrollDate = enrollDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getOriImg() {
			return oriImg;
		}

		public void setOriImg(String oriImg) {
			this.oriImg = oriImg;
		}

		public String getReImg() {
			return reImg;
		}

		public void setReImg(String reImg) {
			this.reImg = reImg;
		}

		public String getDateAssign() {
			return dateAssign;
		}

		public void setDateAssign(String dateAssign) {
			this.dateAssign = dateAssign;
		}

		public String getMemberAssign() {
			return memberAssign;
		}

		public void setMemberAssign(String memberAssign) {
			this.memberAssign = memberAssign;
		}

		@Override
		public String toString() {
			return "Study [studyNo=" + studyNo + ", studyName=" + studyName + ", studyWriter=" + studyWriter
					+ ", studyCategory=" + studyCategory + ", studyPossibleDay=" + studyPossibleDay + ", studyArea="
					+ studyArea + ", studyDetail=" + studyDetail + ", maxMember=" + maxMember + ", enrollDate="
					+ enrollDate + ", endDate=" + endDate + ", oriImg=" + oriImg + ", reImg=" + reImg + ", dateAssign="
					+ dateAssign + ", memberAssign=" + memberAssign + "]";
		}
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	

}
