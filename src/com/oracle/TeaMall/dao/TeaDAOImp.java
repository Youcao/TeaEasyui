package com.oracle.TeaMall.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.oracle.TeaMall.bean.Tea;
import com.oracle.TeaMall.util.StringUtil;










public class TeaDAOImp extends BaseDAOImp implements TeaDAO {

	@Override
	public boolean add(Object o) {
		return false;
		
	}

	@Override
	public boolean delete(Object id) {
		return false;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object list() {
		return null;
	}
	
	public Tea parsetResultToproduct(ResultSet rs) {
		Tea t = null;
		try {
			t = new Tea();
			t.setProduct_id(rs.getInt("product_id"));
			t.setProduct_brand(rs.getString("product_brand"));
			t.setProduct_name(rs.getString("product_name"));
			t.setProduct_sxwx(rs.getString("product_sxwx"));
			t.setProduct_date(rs.getString("product_date"));
			t.setProduct_weight(rs.getInt("product_weight"));
			t.setProduct_series(rs.getString("product_series"));
			t.setProduct_package(rs.getString("product_package"));
			t.setMall_price(rs.getInt("mall_price"));
			t.setProduct_num(rs.getInt("product_num"));
			t.setProduct_image(rs.getString("product_image"));
			t.setCost_price(rs.getInt("cost_price"));
			t.setShifoutuiguang(rs.getString("shifoutuiguang"));

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	
			}

	
	
	public ArrayList<Tea> listTeasByCount(int count){
		ArrayList<Tea>  teas=new  ArrayList<Tea>();
		ResultSet rs=null;
	try {
		rs=getSta().executeQuery("select *  from  product_table order by  product_id desc  limit "+count);
		while (rs.next()) {
			teas.add(parsetResultToproduct(rs));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	disposeResource(getSta(), rs, getCon());
		return  teas;
	}


	public ResultSet searchproduct(Tea tea,String minDate,String maxDate)throws Exception {
		StringBuffer sb = new StringBuffer("select * from product_table  where 1=1");
		//String SQL="select count(*) as total from product_table";
		if(StringUtil.isNotEmpty(tea.getProduct_brand()))
		{
			sb.append(" and product_brand like '%"+tea.getProduct_brand()+"%'");
			//SQL+=" and product_brand like '%"+tea.getProduct_brand()+"%'";
		}
		
		if(StringUtil.isNotEmpty(minDate))
		{
			sb.append(" and to_days(product_date)>=to_days('"+minDate+"')");
			//SQL+=" and to_days(product_date)>=to_days('"+minDate+"')";
		}
		if(StringUtil.isNotEmpty(maxDate))
		{
			sb.append(" and to_days(product_date)<=to_days('"+maxDate+"')");
			//SQL+=" and to_days(product_date)<=to_days('"+maxDate+"')";
		}
		if(StringUtil.isNotEmpty(tea.getProduct_series()))
		{
			sb.append(" and product_series like '%"+tea.getProduct_series()+"%'");
			//SQL+=" and product_series='"+tea.getProduct_series()+"'";
		}
		if(StringUtil.isNotEmpty(tea.getProduct_package()))
		{
			sb.append(" and product_package like '%"+tea.getProduct_package()+"%'");
			//SQL+=" and product_package='"+tea.getProduct_package()+"'";
		}
		System.out.println(sb.toString());
		PreparedStatement pstmt =getPreSta(sb.toString());
		//PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();	
		}
	/**
	 * 计算条件搜索的数据有多少个
	 */
	@Override
	public int searchproductCount(Tea tea, String minDate, String maxDate)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total product_table  where 1=1");
		if(StringUtil.isNotEmpty(tea.getProduct_brand()))
		{
			sb.append(" and product_brand like '%"+tea.getProduct_brand()+"%'");
			//SQL+=" and product_brand like '%"+tea.getProduct_brand()+"%'";
		}
		
		if(StringUtil.isNotEmpty(minDate))
		{
			sb.append(" and to_days(product_date)>=to_days('"+minDate+"')");
			//SQL+=" and to_days(product_date)>=to_days('"+minDate+"')";
		}
		if(StringUtil.isNotEmpty(maxDate))
		{
			sb.append(" and to_days(product_date)<=to_days('"+maxDate+"')");
			//SQL+=" and to_days(product_date)<=to_days('"+maxDate+"')";
		}
		if(StringUtil.isNotEmpty(tea.getProduct_series()))
		{
			sb.append(" and product_series like '%"+tea.getProduct_series()+"%'");
			//SQL+=" and product_series='"+tea.getProduct_series()+"'";
		}
		if(StringUtil.isNotEmpty(tea.getProduct_package()))
		{
			sb.append(" and product_package like '%"+tea.getProduct_package()+"%'");
			//SQL+=" and product_package='"+tea.getProduct_package()+"'";
		}
		System.out.println(sb.toString());
		PreparedStatement pstmt =getPreSta(sb.toString());
		//PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * 计算所有商品有多少页
	 */
	@Override
	public ArrayList<Tea> listTeaByPage(int page, int count) {
		ArrayList<Tea> teas = new ArrayList<Tea>();
		ResultSet rs = null;
		try {
			rs = getSta().executeQuery("select *  from  product_table  order by product_id asc   limit  "+(page-1)*count+" ,"+count);
			while (rs.next()) {

				teas.add(parsetResultToproduct(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(getSta(), rs, getCon());
		return teas;
	}
	
	/**
	 * 查询所有商品有多少个
	 */
	@Override
	public int getAllCountOfproduct() {
		int  n=0;
		ResultSet  rs=null;
		try {
			  rs=getSta().executeQuery("select count(product_id)  from  product_table");
			  rs.next();
			  n=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	

	
	@Override
	public int teaAdd(Tea t) {
	System.out.println("进入添加");
	int  n=0;
	//String sql="insert into  product_table(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	System.out.println(t);
	String sql="insert into  product_table(product_id,product_brand,product_name,product_sxwx,product_date,product_weight,product_series,product_package,mall_price,product_num,product_image,cost_price,shifoutuiguang)   values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	PreparedStatement preSta=null;
	try {
		preSta=getPreSta(sql);
		System.out.println(preSta);
		preSta.setString(1, t.getProduct_brand());
		System.out.println(t.getProduct_brand());
		preSta.setString(2, t.getProduct_name());
		preSta.setString(3, t.getProduct_sxwx());
		preSta.setString(4, t.getProduct_date());
		preSta.setInt(5, t.getProduct_weight());
		preSta.setString(6, t.getProduct_series());
		preSta.setString(7, t.getProduct_package());
		preSta.setInt(8, t.getMall_price());
		preSta.setInt(9, t.getProduct_num());
		preSta.setString(10, t.getProduct_image());
		preSta.setInt(11,t.getCost_price());
		preSta.setString(12, t.getShifoutuiguang());
		//preSta.setInt(13, t.getProduct_id());
		return preSta.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return n;
	}

	@Override
	public int deleteTea(String delIds) {
		int n=0;
		System.out.println(delIds);
		
		String sql="delete from product_table where product_id in("+delIds+")";  
		Statement stat = null;  
		try {
			stat=getSta();
			n = stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
		
	}
	@Override
	public int updateTea(Tea tea) {
		System.out.println("进入到修改");
		System.out.println(tea);
		int  n=0;
		String sql="update product_table set product_brand=?,product_name=?,product_sxwx=?,product_date=?,product_weight=?,product_series=?,product_package=?,mall_price=?,product_num=?,product_image=?,cost_price=?,shifoutuiguang=? where product_id=?";
		PreparedStatement preSta=null;	
		try {	
			preSta=getPreSta(sql);
			preSta.setString(1, tea.getProduct_brand());
			preSta.setString(2, tea.getProduct_name());
			preSta.setString(3, tea.getProduct_sxwx());
			preSta.setString(4, tea.getProduct_date());
			preSta.setInt(5, tea.getProduct_weight());
			preSta.setString(6, tea.getProduct_series());
			preSta.setString(7, tea.getProduct_package());
			preSta.setInt(8, tea.getMall_price());
			preSta.setInt(9, tea.getProduct_num());
			preSta.setString(10, tea.getProduct_image());
			preSta.setInt(11,tea.getCost_price());
			preSta.setString(12, tea.getShifoutuiguang());
			preSta.setInt(13, tea.getProduct_id());
			return preSta.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
		}

	
	

	

	
	}

	

	

	

	
	
	

	

