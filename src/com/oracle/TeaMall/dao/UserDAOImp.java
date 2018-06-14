package com.oracle.TeaMall.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.oracle.TeaMall.bean.User;
import com.oracle.TeaMall.util.MD5;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.oracle.TeaMall.util.*;


public class UserDAOImp extends BaseDAOImp implements UserDAO {
	
	private Connection con;
	private Statement sta;
	private PreparedStatement  preSta;
	private CallableStatement  callSta;
	private DataSource  dataSource;//定义一个连接池对象，这个对象是用来缓存若干个连接的一个‘集合’
	/**
	 * 添加用户
	 */
	@Override
	public boolean add(Object o) {
		User user=(User)o;
		boolean result=false;
		Statement  sta=null;
		try {
			sta=getSta();
			System.out.println("添加用户已连接数据库");
			int count=sta.executeUpdate("insert into  user(username,userid,password)   values('云','"+user.getUserid()+"','"+user.getPassword()+"')");
			//由于userID列不能为空，所以在这里用一个变量的值来填充，后面为保证userid按顺序增加，将rownum的值赋给它
			result=(count>0)?true:false;
			System.out.println("dao添加用户"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 删除用户
	 */
	@Override
	public boolean delete(Object id) {
		boolean result=false;
		Statement sta=null;
		try {
			sta=getSta();
			int count=sta.executeUpdate("delete from  user where userid ="+id);
			//由于userID列不能为空，所以在这里用一个变量的值来填充，后面为保证userid按顺序增加，将rownum的值赋给它
			result=(count>0)?true:false;
		} catch (Exception e) {
		}
		return result;
	}
	/**
	 * 修改用户
	 */
	public boolean update(Object o) {
		User u=(User)o;
		PreparedStatement  preSta=null;
		int result=0;
		try {
			  preSta=getPreSta("update user set userid=? ,password=?,	username=? where userid=?");
			  //System.out.println("UserAaoImp中:"+userid);
			  preSta.setInt(1, u.getUserid());
			
	
			  MD5 m = new MD5();
			  preSta.setString(2,u.getPassword() );
			  preSta.setString(3, u.getUsername());
			  preSta.setInt(4, u.getUserid());
		
			  result=preSta.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result>0?true:false;
	}
	
	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}
	public PreparedStatement getPreSta(String sql) {
		try {
			preSta=getCon().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preSta;
	}



	@Override
	public User login(String username, String password) {
		System.out.println("UserDaoImp中："+username+password);
		User  user=null;
		PreparedStatement  sta=null;
		ResultSet rs=null;
		try {
			sta=getPreSta("select *  from user where username=? and password=?");
			sta.setString(1, username);
			sta.setString(2, password);
			rs=sta.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setAge(rs.getInt("age"));
				user.setImage(rs.getString("image"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getInt("sex"));
				user.setUserid(rs.getInt("userid"));
				System.out.println("出UserDaoImp");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 搜索功能
	 */
	@Override
	public User getUserInfoByUserId(int userid) {
		User  user=null;
		ResultSet rs=null;
		try {
			rs=getSta().executeQuery("select *  from user where userid=?");
			if(rs.next()) {
				user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setPassword(rs.getString("password"));
				if(rs.getString("image")!=null)
				{
					user.setAge(rs.getInt("age"));
					user.setImage(rs.getString("image"));
					user.setNickname(rs.getString("nickname"));
					user.setPassword(rs.getString("password"));
					user.setSex(rs.getInt("sex"));
					user.setUserid(rs.getInt("userid"));
		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;	
		}

	@Override
	public boolean changePasswd(String useridByChange,String newPasswd) {
		String password=newPasswd;
		String userid=useridByChange;
		ResultSet rs = null;
		int result=0;
		try {
			result=getSta().executeUpdate("UPDATE USER SET PASSWORD='"+password+"' WHERE userid='"+userid+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result>0?true:false;
	}
	
	
	
	@Override
	public ArrayList<User> listUserByPage(int count, int page) {
		System.out.println("后台dao方法listUserByPage---"+count+"--"+page);
		ArrayList<User>  users=new ArrayList<>();
		PreparedStatement  sta=null;
		ResultSet rs=null;
		try {
			rs = getSta().executeQuery("select *  from  user  limit  "+((page-1)*count)+" ,"+count);
			//sta=getPreSta("select *  from user limit "+((page-1)*count)+","+count);
			/*sta.setInt(1, (page-1)*count);
			sta.setInt(2, count);
			rs=sta.executeQuery();*/
			while(rs.next()) {
				User  user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				if(rs.getString("image")!=null)
				{
					user.setAge(rs.getInt("age"));
					user.setImage(rs.getString("image"));
					user.setNickname(rs.getString("nickname"));
					user.setSex(rs.getInt("sex"));
					user.setJob(rs.getString("job"));
					user.setJialing(rs.getInt("jialing"));
					user.setEmail(rs.getString("email"));
					user.setTel(rs.getString("tel"));
					user.setJianjie(rs.getString("jianjie"));
				}
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(getSta(), rs, getCon());
		//disposeResource(sta,	rs);
		return users;
	}
	@Override
	public int getAllCountOfUser() {
		int  n=0;
		ResultSet  rs=null;
		System.out.println("将在数据库中查询到的Products按ID的信息发给userAction");
		try {
			  rs=getSta().executeQuery("select count(userid)  from  user");
			  rs.next();
			  n=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	

}
