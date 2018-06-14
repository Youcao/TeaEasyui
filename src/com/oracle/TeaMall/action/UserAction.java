package com.oracle.TeaMall.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.fabric.Response;
import com.oracle.TeaMall.bean.User;
import com.oracle.TeaMall.dao.UserDAO;
import com.oracle.TeaMall.dao.UserDAOImp;
import com.oracle.TeaMall.util.MD5;
import com.oracle.TeaMall.util.Responser;

//component
//javabean 豆子
//功能bean和模型bean
public class UserAction  implements RequestAware{
	private Map<String,Object>  request=new HashMap<>();
	private  User  u;
	private int userid;
	private String password;
	private String username	;
	private  UserDAO  dao;
	private int page;
	private int rows;
	private int i;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public UserAction() {
		dao=new UserDAOImp();
		u=new User();
	}
	/**
	 * 添加用户
	 */
	public void add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
		User user=new User();
		user.setUserid(userid);
		user.setPassword(MD5.MD5(password));//在将表单提交过来的密码风涨到user对象前，先用md5算法把密码加密
		boolean  result=dao.add(user);
		System.out.println("action添加用户"+result);
	
		PrintWriter w = response.getWriter();  
		if(result) {
			
			 w.write("registerSuccess");
		}else {
			w.write("registerFail");
			
		}
		w.flush();
		 w.close();
		
		
	}
	
	public void del() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("进了del");
		System.out.println(userid);
		boolean  result=dao.delete(userid);
		PrintWriter w = response.getWriter();
		if(result) {
			System.out.println("result      ttt");
			w.write("delSuccess");
			//return "delSuccess";
		}else
		{		System.out.println("result      fff");
			w.write("delFail");
			//return "delFail";
		}
	}
	
	
	public void update() throws IOException {
		
		System.out.println("进了update");
		System.out.println("UserAction中"+userid+password+username);
		u.setPassword(password);
		u.setUserid(userid);
		u.setUsername(username);
		boolean  result=dao.update(u);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter w =	response.getWriter();
	
		if(result) {
			w.write("updateSuccess");
		}else {
			w.write("updateFail");
		}
	}
	
	
	/**
	 * 分页加载用户资料
	 */
	public void listUserByPage() {
		ArrayList<User>  users=dao.listUserByPage(rows, page);
		int a = dao.getAllCountOfUser();
		JSONObject data = new JSONObject();
		System.out.println(page);
		System.out.println(rows);
		JSONArray  js=new JSONArray();
		for(User u:users)
		{
			try {
				JSONObject  j=new JSONObject();
				j.put("userid", u.getUserid());
				j.put("username", u.getUsername());
				j.put("nickname", u.getNickname());
				j.put("sex",(u.getSex()==0)?"男":"女");
				j.put("age", u.getAge());
				j.put("image", "<img src='"+u.getImage()+"' style='width:20px;height:20px' />");
				j.put("job", u.getJob());		
				j.put("jialing", u.getJialing());
				j.put("email", u.getEmail());
				j.put("tel", u.getTel());
				j.put("jianjie", u.getJianjie());
				js.put(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*需多次使用，已封装在Responser中
		 * ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		 * PrintWriter out=ServletActionContext.getResponse().getWriter;
		 * out.write(js.toString());
		 * out.flush();
		 * out.close();
		 */
		try {
			data.put("total", a);
			data.put("rows", js);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(js.toString());
		try {
			Responser.responseToJson(ServletActionContext.getResponse(), ServletActionContext.getRequest(), data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUserInfoByUserId() throws IOException {
		
		u=dao.getUserInfoByUserId(userid);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter w = response.getWriter();
		if(u.equals("")) {
			w.write("you");
			
		}else {
			w.write("mei");
		}
	}
	

	
	
	/**
	 * 处理登陆的业务方法
	 * @return
	 */

	public String   login() {
		System.out.println("进UserAction");
		System.out.println(userid+password);
		User u=dao.login(username, password);
		System.out.println("resultUser:"+u);
		if(u!=null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", u);
			System.out.println("success");
			return "success";
		}else
		{		System.out.println("fail");
			return "fail";
		}
	}
	
	/**
	 * 处理注销登陆的业务方法
	 * @return
	 */

	public String   loginout() {
		System.out.println("进UserAction");
		System.out.println(userid+password);
		User u=dao.login(username, password);
		System.out.println("resultUser:"+u);
		if(u!=null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", u);
			System.out.println("success");
			return "success";
		}else
		{		System.out.println("fail");
			return "fail";
		}
	}
	
	public String  sss() {
		System.out.println("oooo");
		System.out.println(i);	
		System.out.println("ppppp");
		return "s";

	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}
		
	}

