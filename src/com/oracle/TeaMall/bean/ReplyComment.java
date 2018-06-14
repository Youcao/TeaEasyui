package com.oracle.TeaMall.bean;

import java.util.Date;

public class ReplyComment {
	private int replyId;
	private String respondent;
	private String byReply;
	private Date replyDate;
	private String replyWords;
	private int commentId;
	public ReplyComment() {
		super();
	}
	
	public ReplyComment(int replyId, String respondent, String byReply, Date replyDate, String replyWords,
			int commentId) {
		super();
		this.replyId = replyId;
		this.respondent = respondent;
		this.byReply = byReply;
		this.replyDate = replyDate;
		this.replyWords = replyWords;
		this.commentId = commentId;
	}



	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getRespondent() {
		return respondent;
	}
	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}
	public String getByReply() {
		return byReply;
	}
	public void setByReply(String byReply) {
		this.byReply = byReply;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getReplyWords() {
		return replyWords;
	}
	public void setReplyWords(String replyWords) {
		this.replyWords = replyWords;
	}
	@Override
	public String toString() {
		return "ReplyComment [replyId=" + replyId + ", respondent=" + respondent + ", byReply=" + byReply
				+ ", replyDate=" + replyDate + ", replyWords=" + replyWords + ", commentId=" + commentId + "]";
	}
	
	
	
}
