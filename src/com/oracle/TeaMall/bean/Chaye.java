package com.oracle.TeaMall.bean;

public class Chaye {
	
	
    @Override
	public String toString() {
		return "Chaye [lingshiid=" + lingshiid + ", pinpaiming=" + pinpaiming + ", shoujia=" + shoujia + ", yuanjia="
				+ yuanjia + ", shoutu=" + shoutu + ", zhuangtai=" + zhuangtai + ", pinglun=" + pinglun + ", riqi="
				+ riqi + ", chengzhong=" + chengzhong + "]";
	}
    
    private int lingshiid;
	private String pinpaiming;
    private float  shoujia;
    private float  yuanjia;
    private String shoutu;
    private int zhuangtai;
    private int pinglun;
    private String riqi;
    private String chengzhong;
    
    
	public Chaye(int lingshiid, String pinpaiming, float shoujia, float yuanjia, String shoutu, int zhuangtai,
			int pinglun, String riqi, String chengzhong) {
		super();
		this.lingshiid = lingshiid;
		this.pinpaiming = pinpaiming;
		this.shoujia = shoujia;
		this.yuanjia = yuanjia;
		this.shoutu = shoutu;
		this.zhuangtai = zhuangtai;
		this.pinglun = pinglun;
		this.riqi = riqi;
		this.chengzhong = chengzhong;
	}

	public Chaye(int lingshiid) {
		super();
		this.lingshiid = lingshiid;
	}
	
	public Chaye() {
		super();
	}


	public int getLingshiid() {
		return lingshiid;
	}


	public void setLingshiid(int lingshiid) {
		this.lingshiid = lingshiid;
	}


	public String getPinpaiming() {
		return pinpaiming;
	}


	public void setPinpaiming(String pinpaiming) {
		this.pinpaiming = pinpaiming;
	}


	public float getShoujia() {
		return shoujia;
	}


	public void setShoujia(float shoujia) {
		this.shoujia = shoujia;
	}


	public float getYuanjia() {
		return yuanjia;
	}


	public void setYuanjia(float yuanjia) {
		this.yuanjia = yuanjia;
	}


	public String getShoutu() {
		return shoutu;
	}


	public void setShoutu(String shoutu) {
		this.shoutu = shoutu;
	}


	public int getZhuangtai() {
		return zhuangtai;
	}


	public void setZhuangtai(int zhuangtai) {
		this.zhuangtai = zhuangtai;
	}


	public int getPinglun() {
		return pinglun;
	}


	public void setPinglun(int pinglun) {
		this.pinglun = pinglun;
	}


	public String getRiqi() {
		return riqi;
	}


	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}


	public String getChengzhong() {
		return chengzhong;
	}


	public void setChengzhong(String chengzhong) {
		this.chengzhong = chengzhong;
	}
	
	
    
}
