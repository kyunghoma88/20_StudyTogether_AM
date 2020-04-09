package com.kh.board.model.vo;

public class Mood {
	private String nickname;
    private int board_ref;
    private char status;
    
    public Mood() {
		// TODO Auto-generated constructor stub
	}

	public Mood(String nickname, int board_ref, char status) {
		super();
		this.nickname = nickname;
		this.board_ref = board_ref;
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getBoard_ref() {
		return board_ref;
	}

	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Mood [nickname=" + nickname + ", board_ref=" + board_ref + ", status=" + status + "]";
	}
    
}
