package com.oracle.TeaMall.bean;

import java.io.Serializable;

public class User implements Serializable{
	private int userid;
	private String username;
	private String password;
	private String nickname;
	private int age;
	private int sex;
	private String image;
	private String job;
	private int jialing;
	private String email;
	private String tel;
	private String jianjie;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJianjie() {
		return jianjie;
	}
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid=userid;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getJialing() {
		return jialing;
	}
	public void setJialing(int jialing) {
		this.jialing = jialing;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", age=" + age + ", sex=" + sex + ", image=" + image + ", job=" + job + ", jialing=" + jialing
				+ ", email=" + email + ", tel=" + tel + ", jianjie=" + jianjie + "]";
	}
	public User(int userid, String username, String password, String nickname, int age, int sex, String image,
			String job, int jialing, String email, String tel, String jianjie) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.image = image;
		this.job = job;
		this.jialing = jialing;
		this.email = email;
		this.tel = tel;
		this.jianjie = jianjie;
	}
	
	


}