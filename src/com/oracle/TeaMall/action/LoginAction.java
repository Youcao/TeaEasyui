package com.oracle.TeaMall.action;

public class LoginAction {

	private String userid;
	private String password;
	
	public String getUserid() {   
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String execute() {
		System.out.println(userid+password);
		if(userid.equals("admin")&&password.equals("admin")) {
			return "success";
		}else {
			return "failed";
		}
	}
}
