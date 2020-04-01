package com.kh.lector.model.vo;

import java.sql.Date;

public class LectorChannel {
	
      private int channelNo;
	 private int channelNoRef;
	 private String channelTitle;
	 private String channelWriter;
	 private String channelDetail;
	 private int channelPrice;
	 private String channelOriginalVideo;
	 private String channelRenamedVideo;
	 private Date ChannelEnrollDate;
	 private int channelLevel;
	 private String channel_assign;
	 
	 
	public LectorChannel() {
		// TODO Auto-generated constructor stub
	}


	public LectorChannel(int channelNo, int channelNoRef, String channelTitle, String channelWriter,
			String channelDetail, int channelPrice, String channelOriginalVideo, String channelRenamedVideo,
			Date channelEnrollDate, int channelLevel, String channel_assign) {
		super();
		this.channelNo = channelNo;
		this.channelNoRef = channelNoRef;
		this.channelTitle = channelTitle;
		this.channelWriter = channelWriter;
		this.channelDetail = channelDetail;
		this.channelPrice = channelPrice;
		this.channelOriginalVideo = channelOriginalVideo;
		this.channelRenamedVideo = channelRenamedVideo;
		ChannelEnrollDate = channelEnrollDate;
		this.channelLevel = channelLevel;
		this.channel_assign = channel_assign;
	}


	public int getChannelNo() {
		return channelNo;
	}


	public void setChannelNo(int channelNo) {
		this.channelNo = channelNo;
	}


	public int getChannelNoRef() {
		return channelNoRef;
	}


	public void setChannelNoRef(int channelNoRef) {
		this.channelNoRef = channelNoRef;
	}


	public String getChannelTitle() {
		return channelTitle;
	}


	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}


	public String getChannelWriter() {
		return channelWriter;
	}


	public void setChannelWriter(String channelWriter) {
		this.channelWriter = channelWriter;
	}

	public String getChannelDetail() {
		return channelDetail;
	}


	public void setChannelDetail(String channelDetail) {
		this.channelDetail = channelDetail;
	}


	public int getChannelPrice() {
		return channelPrice;
	}


	public void setChannelPrice(int channelPrice) {
		this.channelPrice = channelPrice;
	}


	public String getChannelOriginalVideo() {
		return channelOriginalVideo;
	}


	public void setChannelOriginalVideo(String channelOriginalVideo) {
		this.channelOriginalVideo = channelOriginalVideo;
	}


	public String getChannelRenamedVideo() {
		return channelRenamedVideo;
	}


	public void setChannelRenamedVideo(String channelRenamedVideo) {
		this.channelRenamedVideo = channelRenamedVideo;
	}


	public Date getChannelEnrollDate() {
		return ChannelEnrollDate;
	}


	public void setChannelEnrollDate(Date channelEnrollDate) {
		ChannelEnrollDate = channelEnrollDate;
	}


	public int getChannelLevel() {
		return channelLevel;
	}


	public void setChannelLevel(int channelLevel) {
		this.channelLevel = channelLevel;
	}


	public String getChannel_assign() {
		return channel_assign;
	}


	public void setChannel_assign(String channel_assign) {
		this.channel_assign = channel_assign;
	}


	@Override
	public String toString() {
		return "LectorChannel [channelNo=" + channelNo + ", channelNoRef=" + channelNoRef + ", channelTitle="
				+ channelTitle + ", channelWriter=" + channelWriter + ", channelDetail=" + channelDetail
				+ ", channelPrice=" + channelPrice + ", channelOriginalVideo=" + channelOriginalVideo
				+ ", channelRenamedVideo=" + channelRenamedVideo + ", ChannelEnrollDate=" + ChannelEnrollDate
				+ ", channelLevel=" + channelLevel + ", channel_assign=" + channel_assign + "]";
	}
	

}
