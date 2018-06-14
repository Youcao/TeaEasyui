package com.oracle.TeaMall.bean;



/**
 *	PRODUCT_ID		NUMBER
	PRODUCT_BRAND	NVARCHAR2(20 CHAR)
	PRODUCT_NAME	NVARCHAR2(20 CHAR)
	PRODUCT_SXWX	NVARCHAR2(20 CHAR)
	PRODUCT_DATE	DATE
	PRODUCT_WEIGHT	NUMBER
	PRODUCT_SERIES	NVARCHAR2(20 CHAR)
	PRODUCT_PACKAGE	NVARCHAR2(20 CHAR)
	PRODUCT_MALL_PRICE	NUMBER(38,0)
	PRODUCT_NUM	NUMBER(38,0)
	PRODUCT_IMAGE	NVARCHAR2(20 CHAR)
	PRODUCT_PRICE	NUMBER(38,0)
 * @author Administrator
 *
 */
public class Tea {
	private int product_id;//主键
	private String product_brand;//品牌---
	private String product_name;//茶叶名---
	private String product_sxwx;//色香味形---
	private String product_date;//生产时间
	private int product_weight;//茶叶重量
	private String product_series;//明前茶或雨前茶---
	private String product_package;//茶叶的包装盒类型
	private int mall_price;//市场价格
	private int product_num;//库存数量
	private String product_image;//图片路径
	private int cost_price;//成本价
	private String shifoutuiguang;//是否推广
	
	public Tea(int product_id) {
		super();
		this.product_id = product_id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + product_id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tea other = (Tea) obj;
		if (product_id != other.product_id)
			return false;
		return true;
	}


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getProduct_brand() {
		return product_brand;
	}


	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getProduct_sxwx() {
		return product_sxwx;
	}


	public void setProduct_sxwx(String product_sxwx) {
		this.product_sxwx = product_sxwx;
	}


	public String getProduct_date() {
		return product_date;
	}


	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}


	public int getProduct_weight() {
		return product_weight;
	}


	public void setProduct_weight(int product_weight) {
		this.product_weight = product_weight;
	}


	public String getProduct_series() {
		return product_series;
	}


	public void setProduct_series(String product_series) {
		this.product_series = product_series;
	}


	public String getProduct_package() {
		return product_package;
	}


	public void setProduct_package(String product_package) {
		this.product_package = product_package;
	}


	public int getMall_price() {
		return mall_price;
	}


	public void setMall_price(int mall_price) {
		this.mall_price = mall_price;
	}


	public int getProduct_num() {
		return product_num;
	}


	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}


	public String getProduct_image() {
		return product_image;
	}


	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}


	public int getCost_price() {
		return cost_price;
	}


	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}


	public String getShifoutuiguang() {
		return shifoutuiguang;
	}


	public void setShifoutuiguang(String shifoutuiguang) {
		this.shifoutuiguang = shifoutuiguang;
	}


	public Tea(int product_id, String product_brand, String product_name, String product_sxwx, String product_date,
			int product_weight, String product_series, String product_package, int mall_price, int product_num,
			String product_image, int cost_price, String shifoutuiguang) {
		super();
		this.product_id = product_id;
		this.product_brand = product_brand;
		this.product_name = product_name;
		this.product_sxwx = product_sxwx;
		this.product_date = product_date;
		this.product_weight = product_weight;
		this.product_series = product_series;
		this.product_package = product_package;
		this.mall_price = mall_price;
		this.product_num = product_num;
		this.product_image = product_image;
		this.cost_price = cost_price;
		this.shifoutuiguang = shifoutuiguang;
	}


	public Tea() {
		super();
	}


	@Override
	public String toString() {
		return "Tea [product_id=" + product_id + ", product_brand=" + product_brand + ", product_name=" + product_name
				+ ", product_sxwx=" + product_sxwx + ", product_date=" + product_date + ", product_weight="
				+ product_weight + ", product_series=" + product_series + ", product_package=" + product_package
				+ ", mall_price=" + mall_price + ", product_num=" + product_num + ", product_image=" + product_image
				+ ", cost_price=" + cost_price + ", shifoutuiguang=" + shifoutuiguang + "]";
	}


}
