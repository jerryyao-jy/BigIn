package com.ylj.biginsight.model;

import android.widget.ImageView;

public class NewsModel {

	private int id;
	private String title;
	private String content;
	private int showImage;
	private int comment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getShowImage() {
		return showImage;
	}
	public void setShowImage(int showImage) {
		this.showImage = showImage;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}

	
}
