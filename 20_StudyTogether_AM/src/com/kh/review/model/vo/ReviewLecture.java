package com.kh.review.model.vo;

import java.sql.Date;

public class ReviewLecture {
	private int reviewLecNo;
    private String reviewLecWriter;
    private String lectureName;
    private String reviewLecCategory;
    private String reviewLecContent;
    private int reviewLecStar;
    private Date reviewLecDate;
    
    public ReviewLecture() {
		// TODO Auto-generated constructor stub
	}

	public ReviewLecture(int reviewLecNo, String reviewLecWriter, String lectureName, String reviewLecCategory,
			String reviewLecContent, int reviewLecStar, Date reviewLecDate) {
		super();
		this.reviewLecNo = reviewLecNo;
		this.reviewLecWriter = reviewLecWriter;
		this.lectureName = lectureName;
		this.reviewLecCategory = reviewLecCategory;
		this.reviewLecContent = reviewLecContent;
		this.reviewLecStar = reviewLecStar;
		this.reviewLecDate = reviewLecDate;
	}

	public int getReviewLecNo() {
		return reviewLecNo;
	}

	public void setReviewLecNo(int reviewLecNo) {
		this.reviewLecNo = reviewLecNo;
	}

	public String getReviewLecWriter() {
		return reviewLecWriter;
	}

	public void setReviewLecWriter(String reviewLecWriter) {
		this.reviewLecWriter = reviewLecWriter;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getReviewLecCategory() {
		return reviewLecCategory;
	}

	public void setReviewLecCategory(String reviewLecCategory) {
		this.reviewLecCategory = reviewLecCategory;
	}

	public String getReviewLecContent() {
		return reviewLecContent;
	}

	public void setReviewLecContent(String reviewLecContent) {
		this.reviewLecContent = reviewLecContent;
	}

	public int getReviewLecStar() {
		return reviewLecStar;
	}

	public void setReviewLecStar(int reviewLecStar) {
		this.reviewLecStar = reviewLecStar;
	}

	public Date getReviewLecDate() {
		return reviewLecDate;
	}

	public void setReviewLecDate(Date reviewLecDate) {
		this.reviewLecDate = reviewLecDate;
	}

	@Override
	public String toString() {
		return "ReviewLecture [reviewLecNo=" + reviewLecNo + ", reviewLecWriter=" + reviewLecWriter + ", lectureName="
				+ lectureName + ", reviewLecCategory=" + reviewLecCategory + ", reviewLecContent=" + reviewLecContent
				+ ", reviewLecStar=" + reviewLecStar + ", reviewLecDate=" + reviewLecDate + "]";
	}
    
    
	
    
}
