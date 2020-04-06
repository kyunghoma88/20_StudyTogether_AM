package com.kh.board.model.vo;

import java.util.Date;

public class Board {
	private int board_no;
    private int reply_no;
    private int reply_level;
    private String nickname;
    private String title;
    private String content;
    private String category;
    private String file_upload;
    private Date write_date;
    private int cnt;
    private int good_cnt;
    private int bad_cnt;
    private int comment_cnt;
    
    public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int board_no, int reply_no, int reply_level, String nickname, String title, String content,
			String category, String file_upload, Date write_date, int cnt, int good_cnt, int bad_cnt, int comment_cnt) {
		super();
		this.board_no = board_no;
		this.reply_no = reply_no;
		this.reply_level = reply_level;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.category = category;
		this.file_upload = file_upload;
		this.write_date = write_date;
		this.cnt = cnt;
		this.good_cnt = good_cnt;
		this.bad_cnt = bad_cnt;
		this.comment_cnt = comment_cnt;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public int getReply_level() {
		return reply_level;
	}

	public void setReply_level(int reply_level) {
		this.reply_level = reply_level;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFile_upload() {
		return file_upload;
	}

	public void setFile_upload(String file_upload) {
		this.file_upload = file_upload;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getGood_cnt() {
		return good_cnt;
	}

	public void setGood_cnt(int good_cnt) {
		this.good_cnt = good_cnt;
	}

	public int getBad_cnt() {
		return bad_cnt;
	}

	public void setBad_cnt(int bad_cnt) {
		this.bad_cnt = bad_cnt;
	}

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", reply_no=" + reply_no + ", reply_level=" + reply_level + ", nickname="
				+ nickname + ", title=" + title + ", content=" + content + ", category=" + category + ", file_upload="
				+ file_upload + ", write_date=" + write_date + ", cnt=" + cnt + ", good_cnt=" + good_cnt + ", bad_cnt="
				+ bad_cnt + ", comment_cnt=" + comment_cnt + "]";
	}
    
    
}
