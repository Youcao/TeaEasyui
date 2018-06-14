package com.oracle.TeaMall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import com.oracle.TeaMall.bean.Tea;







public interface TeaDAO  extends BaseDAO{
	/*
	 * 设计一个可以根据用户传入的数量查询显示出最近发布的茶信息
	 */
	public abstract ArrayList<Tea>  listTeasByCount(int count);
	
	
	/**
	 * 根据传入的条件，搜索对应的茶信息的方法
	 * @param chandi
	 * @param teaname
	 * @return
	 * @throws Exception 
	 */
	public abstract ResultSet searchproduct(Tea tea,String minDate,String maxDate) throws Exception;
	
	/**
	 * 显示所有商品分页
	 * @param page
	 * @param count
	 * @return
	 */
	public ArrayList<Tea>  listTeaByPage(int page,int count);
	/**
	 * 查询出茶信息表有多少条数据
	 * @return
	 */
	public int getAllCountOfproduct();
	
	public int updateTea(Tea tea);
	public int deleteTea(String delIds);
	public int teaAdd(Tea t);
	public int searchproductCount(Tea tea,String minDate,String maxDate)throws Exception;
}
