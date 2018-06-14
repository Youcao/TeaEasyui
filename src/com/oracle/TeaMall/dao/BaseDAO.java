package com.oracle.TeaMall.dao;

public interface BaseDAO {
	public String  dirverClass="com.mysql.jdbc.Driver";
	public String  url="jdbc:mysql://localhost:3306/shangcheng?characterEncoding=utf8&useSSL=true";
	public String username="root";
	public String password="1234";
	
	//添加
	public boolean add(Object o);
	
	public boolean  delete(Object id);
	
	public boolean update(Object  o);
	
	public Object  list();
}
