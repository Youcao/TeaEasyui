package com.oracle.TeaMall.dao;

import java.util.ArrayList;

import com.oracle.TeaMall.bean.Cart;

public interface CartDAO extends BaseDAO{
	
	public int watchcount();
	
	public ArrayList<Cart> list(int row, int page);
	
	public ArrayList<Integer> listProductByUserId(int count);
	
	public ArrayList<Integer> listUserByproductId(int count);

}
