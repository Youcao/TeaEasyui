package com.oracle.TeaMall.action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.oracle.TeaMall.bean.Picture;
import com.oracle.TeaMall.dao.PictureDAOImp;
import com.oracle.TeaMall.util.Responser;

@ParentPackage("struts-default")
@Action("/admin/SearchAction")
@Results({
	@Result(name="youshuju", location="index.jsp"),
	@Result(name="wushuju", location="index.jsp")
})
public class SearchAction {
	PictureDAOImp dao=new  PictureDAOImp();
	private Picture u;

	
	
	public Picture getU() {
		return u;
	}



	public void setU(Picture u) {
		this.u = u;
	}



	public String search()
	{
		ArrayList<Picture>  pictures=dao.search(u.getImage_id());
		System.out.println(pictures);
		JSONArray  js=new JSONArray();
		if(pictures!=null) 
		{
			for(Picture u:pictures)
			{
				try {
					JSONObject  j=new JSONObject();
					j.put("simage_id", u.getImage_id());
					j.put("simage_shoutu", "<img src='"+u.getImage_shoutu()+"' style='width:40px;height:40px' />");
					j.put("simage_qita1", "<img src='"+u.getImage_qita1()+"' style='width:40px;height:40px' />");
					j.put("simage_qita2", "<img src='"+u.getImage_qita2()+"' style='width:40px;height:40px' />");
					j.put("simage_qita3", "<img src='"+u.getImage_qita3()+"' style='width:40px;height:40px' />");
					j.put("simage_qita4", "<img src='"+u.getImage_qita4()+"' style='width:40px;height:40px' />");
					js.put(j);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(js.toString());
			try {
				Responser.responseToJson(ServletActionContext.getResponse(), ServletActionContext.getRequest(), js.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "youshuju";
		}else {
			return "wushuju";
		}
		
	}

}
