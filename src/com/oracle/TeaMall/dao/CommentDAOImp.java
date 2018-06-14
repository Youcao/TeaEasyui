package com.oracle.TeaMall.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.oracle.TeaMall.bean.Comment;
import com.oracle.TeaMall.bean.ReplyComment;

public class CommentDAOImp extends BaseDAOImp implements CommentDAO{

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 添加评论的方法
	 */
	public boolean addComment(Object comment) {
		Comment c=(Comment)comment;
		boolean result=false;
		Statement sta=null;
		try {
			sta=getSta();
			int count=sta.executeUpdate("insert into comment_table values(SEQ_COMMENT_ID.NEXTVAL,"+c.getProductId()+",'"+c.getCommentUsername()+"','"+c.getCommentWords()+"',sysdate,'"+c.getCommentImage()+"','"+c.getCommentVideo()+"')");
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除评论的方法
	 */
	public boolean deleteComment(int commentId) {
		boolean result=false;
		Statement sta=null;
		try {
			sta=getSta();
			int count=sta.executeUpdate("delete comment_table where comment_id="+commentId);
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询评论的方法
	 * select * from COMMENT_TABLE where to_char(comment_date,'YYYY')=2018 and to_char(comment_date,'MM')=6 and to_char(comment_date,'DD')=10;
	 */
	public ArrayList<Comment> queryComment(String keyWords,String date) {
		ArrayList<Comment> comments = new ArrayList<>();
		Statement sta=null;
		ResultSet rs=null;
		
		//根据查询条件拼接SQL语句
		String sql="select * from COMMENT_TABLE where 1=1 ";
		if(!keyWords.equals("")) {
			System.out.println("进入关键词判断了");
			sql+=" and COMMENT_WORDS like '%"+keyWords+"%' ";
		}
		if(!date.equals("")) {
			System.out.println("进入日期判断了");
			String[] str = date.split("/");
			for(int i=0;i<str.length;i++) {
			}
			sql+=" and to_char(comment_date,'YYYY')="+str[2]+" and to_char(comment_date,'MM')="+str[0]+" and to_char(comment_date,'DD')="+str[1];
		}
		System.out.println(sql);
		try {
			sta=getSta();
			rs=sta.executeQuery(sql);
			while(rs.next()) {
				Comment c = new Comment();
				c.setCommentId(rs.getInt("comment_id"));
				c.setProductId(rs.getInt("product_id"));
				c.setCommentUsername(rs.getString("comment_username"));
				c.setCommentImage(rs.getString("comment_image"));
				c.setCommentDate(rs.getDate("comment_date"));
				c.setCommentWords(rs.getString("comment_words"));
				c.setCommentVideo(rs.getString("comment_video"));
				
				comments.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta, rs);
		return comments;
	}

	/**
	 * 根据商品ID列出所有该商品的评论
	 */
	@Override
	public ArrayList<Comment> listCommentsByProductId(int productId) {
		ArrayList<Comment> comments = new ArrayList<>();
		Statement sta=null;
		ResultSet rs=null;
		try {
			sta=getSta();
			rs=sta.executeQuery("select * from comment_table where product_id="+productId);
			while(rs.next()) {
				Comment c = new Comment();
				c.setCommentId(rs.getInt("comment_id"));
				c.setProductId(rs.getInt("product_id"));
				c.setCommentUsername(rs.getString("comment_username"));
				c.setCommentImage(rs.getString("comment_image"));
				c.setCommentDate(rs.getDate("comment_date"));
				c.setCommentWords(rs.getString("comment_words"));
				c.setCommentVideo(rs.getString("comment_video"));
				
				comments.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta, rs);
		return comments;
	}

	@Override
	public int getAllCountOfComments() {
		int count=0;
		Statement  sta=null;
		ResultSet  rs=null;
		try {
			sta=getSta();
			rs=sta.executeQuery("select  count(comment_id)  from  comment_table");
			rs.next();
			count=rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta, rs);
		return count;
	}
	/**
	 * 获取一条评论下的所有回复
	 */
	@Override
	public ArrayList<ReplyComment> allReplyComments(int commentID) {
		ArrayList<ReplyComment> replyComments = new ArrayList<>();
		Statement sta=null;
		ResultSet rs=null;
		try {
			sta = getSta();
			rs = sta.executeQuery("select * from reply_table where comment_id="+commentID);
			while(rs.next()) {
				ReplyComment r = new ReplyComment();
				r.setReplyId(rs.getInt("REPLY_ID"));
				r.setCommentId(rs.getInt("COMMENT_ID"));
				r.setRespondent(rs.getString("RESPONDENT"));
				r.setByReply(rs.getString("BYREPLY"));
				r.setReplyDate(rs.getDate("REPLY_DATE"));
				r.setReplyWords(rs.getString("REPLY_WORDS"));
				
				replyComments.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta, rs);
		return replyComments;
	}
	/**
	 * 添加回复
	 */
	@Override
	public boolean addReplyComment(ReplyComment reply) {
		boolean result = false;
		Statement  sta=null;
		try {
			sta=getSta();
			int count=sta.executeUpdate("insert into reply_table values(SEQ_REPLY_ID.NEXTVAL,'管理员','"+reply.getByReply()+"',sysdate,'"+reply.getReplyWords()+"',"+reply.getCommentId()+")");
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteReplyByReplyId(int replyId) {
		boolean result = false;
		Statement  sta=null;
		try {
			sta=getSta();
			int count=sta.executeUpdate("delete reply_table where reply_id="+replyId);
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}
