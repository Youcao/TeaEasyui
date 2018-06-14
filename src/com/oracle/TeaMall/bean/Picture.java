package com.oracle.TeaMall.bean;

public class Picture {
	
	private int image_id;
	private String image_shoutu;
	private String image_qita1;
	private String image_qita2;
	private String image_qita3;
	private String image_qita4;
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getImage_shoutu() {
		return image_shoutu;
	}
	public void setImage_shoutu(String image_shoutu) {
		this.image_shoutu = image_shoutu;
	}
	public String getImage_qita1() {
		return image_qita1;
	}
	public void setImage_qita1(String image_qita1) {
		this.image_qita1 = image_qita1;
	}
	public String getImage_qita2() {
		return image_qita2;
	}
	public void setImage_qita2(String image_qita2) {
		this.image_qita2 = image_qita2;
	}
	public String getImage_qita3() {
		return image_qita3;
	}
	public void setImage_qita3(String image_qita3) {
		this.image_qita3 = image_qita3;
	}
	public String getImage_qita4() {
		return image_qita4;
	}
	public void setImage_qita4(String image_qita4) {
		this.image_qita4 = image_qita4;
	}
	@Override
	public String toString() {
		return "Picture [image_id=" + image_id + ", image_shoutu=" + image_shoutu + ", image_qita1=" + image_qita1
				+ ", image_qita2=" + image_qita2 + ", image_qita3=" + image_qita3 + ", image_qita4=" + image_qita4
				+ "]";
	}
	public Picture(int image_id, String image_shoutu, String image_qita1, String image_qita2, String image_qita3,
			String image_qita4) {
		super();
		this.image_id = image_id;
		this.image_shoutu = image_shoutu;
		this.image_qita1 = image_qita1;
		this.image_qita2 = image_qita2;
		this.image_qita3 = image_qita3;
		this.image_qita4 = image_qita4;
	}
	public Picture() {
		super();
	}
	
	
	
}
