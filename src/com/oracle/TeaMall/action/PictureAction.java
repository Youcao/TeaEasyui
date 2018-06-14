package com.oracle.TeaMall.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.RequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.oracle.TeaMall.bean.Picture;
import com.oracle.TeaMall.dao.PictureDAO;
import com.oracle.TeaMall.dao.PictureDAOImp;
import com.oracle.TeaMall.util.Responser;

@ParentPackage("struts-default")
@Action("/admin/PictureAction")
public class PictureAction implements RequestAware {
	
	private Map<String,Object>  request=new HashMap<>();
	
    
	private PictureDAO dao;
	private int page;
	private int rows;
	private Picture u;
	
	public Picture getU() {
		return u;
	}


	public void setU(Picture u) {
		this.u = u;
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
	
	public PictureAction() {
		dao=new PictureDAOImp();
	}


	public void listPictureByPage() {
		
		ArrayList<Picture>  pictures=dao.listPictureByPage(rows, page);
		JSONObject data = new JSONObject();
		int count =120;//这个从dao里面查的数据总数
		JSONArray  js=new JSONArray();
		for(Picture u:pictures)
		{
			try {
				JSONObject  j=new JSONObject();
				j.put("image_id", u.getImage_id());
				j.put("image_shoutu", "<img src='"+u.getImage_shoutu()+"' style='width:40px;height:40px' />");
				j.put("image_qita1", "<img src='"+u.getImage_qita1()+"' style='width:40px;height:40px' />");
				j.put("image_qita2", "<img src='"+u.getImage_qita2()+"' style='width:40px;height:40px' />");
				j.put("image_qita3", "<img src='"+u.getImage_qita3()+"' style='width:40px;height:40px' />");
				j.put("image_qita4", "<img src='"+u.getImage_qita4()+"' style='width:40px;height:40px' />");
				js.put(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			data.put("total", count);
			data.put("rows", js);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		System.out.println(data.toString());
		try {
			Responser.responseToJson(ServletActionContext.getResponse(), ServletActionContext.getRequest(), data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

     public void setRequest(Map<String, Object> arg0) {
		
	}
	
}
