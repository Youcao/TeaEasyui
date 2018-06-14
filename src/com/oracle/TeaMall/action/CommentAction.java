package com.oracle.TeaMall.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.oracle.TeaMall.bean.Comment;
import com.oracle.TeaMall.bean.ReplyComment;
import com.oracle.TeaMall.dao.CommentDAOImp;
import com.oracle.TeaMall.util.Responser;

public class CommentAction {

	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	private int page;
	private int count;
	private int producuId;
	private int commentId;
	private String keyWords;
	private String date;
	private String commentWords;
	private String byReply;
	private String replyWords;
	private int replyId;
	private CommentDAOImp comDao;
	
	public CommentAction() {
		comDao = new CommentDAOImp();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	
	public String getReplyWords() {
		return replyWords;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public void setReplyWords(String replyWords) {
		this.replyWords = replyWords;
	}

	public String getByReply() {
		return byReply;
	}

	public void setByReply(String byReply) {
		this.byReply = byReply;
	}

	public String getCommentWords() {
		return commentWords;
	}

	public void setCommentWords(String commentWords) {
		this.commentWords = commentWords;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getProducuId() {
		return producuId;
	}

	public void setProducuId(int producuId) {
		this.producuId = producuId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 删除评论的方法
	 */
	public void deleteComment() {
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out=response.getWriter();
			boolean result = comDao.deleteComment(commentId);
			if(result) {
				out.write("deleteSuccess");
			}else {
				out.write("deleteFail");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//根据商品ID列出该商品下的所有评论
	
	public void listCommentByProductId(){
		ArrayList<Comment> comments =comDao.listCommentsByProductId(producuId);
		JSONArray ja = new JSONArray();
		for(Comment c:comments) {
			try {
				JSONObject j = new JSONObject();
				j.put("commentId", c.getCommentId());
				j.put("productId", c.getProductId());
				j.put("commentUsername", c.getCommentUsername());
				j.put("commentWords", c.getCommentWords());
				j.put("commentDate", c.getCommentDate());
				j.put("commentImage", c.getCommentImage());
				j.put("commentVideo", c.getCommentVideo());
				
				ja.put(j);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(ja.toString());
			Responser.responseToJson(response, request, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//搜索评论方法
	public void searchCommentsByAjax(){
		System.out.println(keyWords+"\t"+date);
		ArrayList<Comment> searchedComments = comDao.queryComment(keyWords, date);
		JSONArray ja = new JSONArray();
		for(Comment c:searchedComments) {
			try {
				JSONObject j = new JSONObject();
				j.put("commentId", c.getCommentId());
				j.put("productId", c.getProductId());
				j.put("commentUsername", c.getCommentUsername());
				j.put("commentWords", c.getCommentWords());
				j.put("commentDate", c.getCommentDate());
				j.put("commentImage", c.getCommentImage());
				j.put("commentVideo", c.getCommentVideo());
				
				ja.put(j);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(ja.toString());
			Responser.responseToJson(response, request, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//添加评论方法
	public void addComment(){
		Comment c = new Comment();
		c.setProductId(producuId);
		c.setCommentWords(commentWords);
		boolean result = comDao.addComment(c);
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			if(result) {
				out.write("addCommentSuccess");
			}else {
				out.write("addCommentFailed");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//获取评论下的所有回复
	public void getAllReplyComments() {
		System.out.println(commentId);
		ArrayList<ReplyComment> replyComments = comDao.allReplyComments(commentId);
		JSONArray ja = new JSONArray();
		for(ReplyComment r:replyComments) {
			try {
				JSONObject j = new JSONObject();
				j.put("replyId", r.getReplyId());
				j.put("respondent", r.getRespondent());
				j.put("byReply", r.getByReply());
				j.put("replyDate", r.getReplyDate());
				j.put("replyWords", r.getReplyWords());
				j.put("commentId", r.getCommentId());
				
				ja.put(j);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(ja.toString());
			Responser.responseToJson(response, request, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//在评论下添加回复
	public void replyComment(){
		ReplyComment r = new ReplyComment();
		r.setByReply(byReply);
		r.setCommentId(commentId);
		r.setReplyWords(replyWords);
		
		boolean result = comDao.addReplyComment(r);
		System.out.println(result);
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			if(result) {
				out.write("replySuccess");
			}else {
				out.write("replyFaailed");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除评论下的回复
	public void deleteReplyByReplyId() {
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out=response.getWriter();
			boolean result = comDao.deleteReplyByReplyId(replyId);
			if(result) {
				out.write("deleteReplySuccess");
			}else {
				out.write("deleteReplyFailed");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
