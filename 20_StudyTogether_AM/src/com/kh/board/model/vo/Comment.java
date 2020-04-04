package com.kh.board.model.vo;

import java.util.Date;

public class Comment {
	private int comment_no;
    private int comment_level;
    private String comment_writer;
    private String comment_content;
    private int board_ref;
    private int comment_no_ref; 
    private Date comment_date;
    
    public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int comment_no, int comment_level, String comment_writer, String comment_content, int board_ref,
			int comment_no_ref, Date comment_date) {
		super();
		this.comment_no = comment_no;
		this.comment_level = comment_level;
		this.comment_writer = comment_writer;
		this.comment_content = comment_content;
		this.board_ref = board_ref;
		this.comment_no_ref = comment_no_ref;
		this.comment_date = comment_date;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getComment_level() {
		return comment_level;
	}

	public void setComment_level(int comment_level) {
		this.comment_level = comment_level;
	}

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public int getBoard_ref() {
		return board_ref;
	}

	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}

	public int getComment_no_ref() {
		return comment_no_ref;
	}

	public void setComment_no_ref(int comment_no_ref) {
		this.comment_no_ref = comment_no_ref;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	@Override
	public String toString() {
		return "Comment [comment_no=" + comment_no + ", comment_level=" + comment_level + ", comment_writer="
				+ comment_writer + ", comment_content=" + comment_content + ", board_ref=" + board_ref
				+ ", comment_no_ref=" + comment_no_ref + ", comment_date=" + comment_date + "]";
	}
    
}
