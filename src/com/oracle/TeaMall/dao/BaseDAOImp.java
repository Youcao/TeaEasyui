package com.oracle.TeaMall.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAOImp implements BaseDAO{
	private Connection con=null;
	private Statement sta=null;
	private PreparedStatement  preSta;
	public PreparedStatement getPreSta(String sql) {
		try {
			preSta=getCon().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preSta;
	}
	//创建连接
	public Connection getCon() {
		try {
			try {
				Class.forName(dirverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	//获取会话对象
	public Statement getSta() {
		try {
			sta = getCon().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sta;
	}
	//初始化连接
	public BaseDAOImp() {
		try {
			Class.forName(dirverClass);
			con = DriverManager.getConnection(url, username, password);
			sta = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//释放资源对象
	public void disposeResource(Statement sta, ResultSet rs) {// dispose释放，关闭资源
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sta != null) {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void disposeResource(Statement sta, ResultSet rs, Connection con) {// dispose释放，关闭资源
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sta != null) {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
