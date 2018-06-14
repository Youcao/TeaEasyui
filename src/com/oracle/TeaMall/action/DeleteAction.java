package com.oracle.TeaMall.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.oracle.TeaMall.bean.Picture;
import com.oracle.TeaMall.dao.PictureDAOImp;
import com.oracle.TeaMall.util.Responser;



@ParentPackage("struts-default")
@Action("/admin/DeleteAction")
public class DeleteAction {
	private Picture u;
	public Picture getU() {
		return u;
	}
	public void setU(Picture u) {
		this.u = u;
	}
	PictureDAOImp dao=new  PictureDAOImp();
	public void delete()
	{
		boolean result=dao.deleteimage(u.getImage_id());
		System.out.println(u.getImage_id());
		System.out.println("delete result:"+result);
		try {
			Responser.responseToJson(ServletActionContext.getResponse(), ServletActionContext.getRequest(), result+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
