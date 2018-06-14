package com.oracle.TeaMall.bean;

public class Cart {
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
	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", user_id=" + user_id + ", product_id=" + product_id + ", product_number="
				+ product_number + "]";
	}

}
