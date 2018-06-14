package com.oracle.TeaMall.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oracle.TeaMall.bean.Cart;

public class CartDAOImp extends BaseDAOImp implements CartDAO{

	@Override
	public boolean add(Object o) {
		Cart c = (Cart)o;
		ResultSet rs = null;
		boolean result = false;
		try {
			System.out.println(c.toString());
			System.out.println("daoaddfangfalimianle");
			rs = getSta().executeQuery("select cart_id from cart where cart_id=" + c.getCart_id());
			if(rs.next()) {
				result = false;
				System.out.println("rslimianyouzhi");
			} else {
				System.out.println("rslimianmeizhi");
				int count=getSta().executeUpdate("insert into cart(cart_id, user_id, product_id, product_number) values(null, "+c.getUser_id()+","+c.getProduct_id()+","+c.getProduct_number()+")");
				result = (count>0)?true:false;
				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Object o) {
		Cart c = (Cart)o;
		boolean result = false;
		System.out.println(c.getCart_id());
		try {
			int count = getSta().executeUpdate("delete from cart where cart_id="+c.getCart_id());
			result = (count>0)?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Object o) {
		Cart c = (Cart)o;
		System.out.println(c.toString());
		boolean result = false;
		try {
			int count = getSta().executeUpdate("update cart set user_id="+c.getUser_id()+",product_id="+c.getProduct_id()+",product_number="+c.getProduct_number()+" where cart_id="+c.getCart_id());
			result = (count>0)?true:false;
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public ArrayList<Integer> listProductByUserId(int count) {
		
		return null;
	}

	@Override
	public ArrayList<Integer> listUserByproductId(int count) {
		return null;
	}

	@Override
	public int watchcount() {
		int n = 0;
		ResultSet rs = null;
		try {
			rs = getSta().executeQuery("select count(cart_id) from cart");
			rs.next();
			n = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cart> list(int row, int page) {
		ResultSet rs = null;
		ArrayList<Cart> al = new ArrayList<>();
		try {
			rs = getSta().executeQuery("select * from cart limit "+(page-1)*row+","+row);
			while(rs.next()) {
				Cart c = new Cart();//这个是建立节点，必须一个一个的建
				c.setCart_id(rs.getInt("cart_id"));
				c.setUser_id(rs.getInt("user_id"));
				c.setProduct_id(rs.getInt("product_id"));
				c.setProduct_number(rs.getInt("product_number"));
				al.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

}
