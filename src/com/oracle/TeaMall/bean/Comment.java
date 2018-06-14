package com.oracle.TeaMall.bean;

import java.util.Date;

public class Comment {
	private int commentId;
	private int productId;
	private String commentUsername;
	private String commentWords;
	private Date commentDate;
	private String commentImage;
	private String commentVideo;
	public Comment(int commentId, int productId, String commentUsername, String commentWords, Date commentDate,
			String commentImage, String commentVideo) {
		super();
		this.commentId = commentId;
		this.productId = productId;
		this.commentUsername = commentUsername;
		this.commentWords = commentWords;
		this.commentDate = commentDate;
		this.commentImage = commentImage;
		this.commentVideo = commentVideo;
	}
	public Comment() {
		super();
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getCommentUsername() {
		return commentUsername;
	}
	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}
	public String getCommentWords() {
		return commentWords;
	}
	public void setCommentWords(String commentWords) {
		this.commentWords = commentWords;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentImage() {
		return commentImage;
	}
	public void setCommentImage(String commentImage) {
		this.commentImage = commentImage;
	}
	public String getCommentVideo() {
		return commentVideo;
	}
	public void setCommentVideo(String commentVideo) {
		this.commentVideo = commentVideo;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", productId=" + productId + ", commentUsername=" + commentUsername
				+ ", commentWords=" + commentWords + ", commentDate=" + commentDate + ", commentImage=" + commentImage
				+ ", commentVideo=" + commentVideo + "]";
	}
	
	
}
