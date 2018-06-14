package com.oracle.TeaMall.dao;

import java.util.ArrayList;

import com.oracle.TeaMall.bean.Comment;
import com.oracle.TeaMall.bean.ReplyComment;

public interface CommentDAO {
	//添加评论
	public boolean addComment(Object comment);
	//删除评论
	public boolean deleteComment(int commentId);
	//查询评论
	public ArrayList<Comment> queryComment(String keyWords,String date);
	//列出所有评论
	public ArrayList<Comment> listCommentsByProductId(int productId);
	//获得所有评论数
	public int getAllCountOfComments();
	//获取所有的回复评论
	public ArrayList<ReplyComment> allReplyComments(int commentID);
	//添加回复
	public boolean addReplyComment(ReplyComment reply);
	//删除回复
	public boolean deleteReplyByReplyId(int replyId);
}
