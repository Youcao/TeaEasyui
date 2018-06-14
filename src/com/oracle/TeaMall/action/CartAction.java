package com.oracle.TeaMall.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.oracle.TeaMall.bean.Cart;
import com.oracle.TeaMall.dao.CartDAOImp;
import com.oracle.TeaMall.util.Responser;

@ParentPackage("struts-default")
@Action("CartAction")
@Results({
	@Result(name="ok", location="waitDelivery.jsp"),
	@Result(name="ok", location="obligation.jsp")
})
public class CartAction {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public CartAction() {
		dao = new CartDAOImp();
	}

	private CartDAOImp dao;
	private int page;
	private int rows;
	private int delId;
	public int getDelId() {
		return delId;
	}

	public void setDelId(int delId) {
		this.delId = delId;
	}

	private int cart_id;
	private int user_id;
	private int product_id;
	private int product_number;

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
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

	public String listDeliveryByPage() {
		ArrayList<Cart> all = (ArrayList<Cart>)dao.list(rows, page);
		int count = dao.watchcount();
		JSONObject data = new JSONObject();
		JSONArray rowsCollection = new JSONArray();
		for(Cart c:all) {
			JSONObject propertiseobject = new JSONObject();
			try {
				propertiseobject.put("cart_id", c.getCart_id());
				propertiseobject.put("user_id", c.getUser_id());
				propertiseobject.put("product_id", c.getProduct_id());
				propertiseobject.put("product_number", c.getProduct_number());
				rowsCollection.put(propertiseobject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		try {
			data.put("total", count);
			data.put("rows", rowsCollection);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			Responser.responseToJson(response, request, data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String listObligationByPage() {
		ArrayList<Cart> all = (ArrayList<Cart>)dao.list(rows, page);
		int count = dao.watchcount();
		JSONObject data = new JSONObject();
		JSONArray rowsCollection = new JSONArray();
		for(Cart c:all) {
			JSONObject propertiseobject = new JSONObject();
			try {
				propertiseobject.put("cart_id", c.getCart_id());
				propertiseobject.put("user_id", c.getUser_id());
				propertiseobject.put("product_id", c.getProduct_id());
				propertiseobject.put("product_number", c.getProduct_number());
				rowsCollection.put(propertiseobject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		try {
			data.put("total", count);
			data.put("rows", rowsCollection);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			Responser.responseToJson(response, request, data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String listCartByTime() {
		return null;
	}
	
	public String addCart() {
		Cart c = new Cart();
		c.setCart_id(Integer.parseInt(request.getParameter("cart_id")));
		c.setUser_id(user_id);
		c.setProduct_id(product_id);
		c.setProduct_number(product_number);
		System.out.println(c.toString());
		boolean result = dao.add(c);
		JSONObject data = new JSONObject();
		try {
			data.put("result", result+"");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(data.toString());
			Responser.responseToJson(response, request, data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteCart() {//单独操作delld
		Cart c = new Cart();
		c.setCart_id(delId);
		boolean result = dao.delete(c);
		JSONObject data = new JSONObject();
		try {
			data.put("result", result+"");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			Responser.responseToJson(response, request, data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String editCart() {
		Cart c = new Cart();
		c.setCart_id(Integer.parseInt(request.getParameter("cart_id")));
		c.setUser_id(user_id);
		c.setProduct_id(product_id);
		c.setProduct_number(product_number);
		System.out.println(c.toString());
		System.out.println(request.getParameter("cart_id"));
		boolean result = dao.update(c);
		JSONObject data = new JSONObject();
		try {
			data.put("result", result+"");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(data.toString());
			Responser.responseToJson(response, request, data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
