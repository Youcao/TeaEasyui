package com.oracle.TeaMall.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.oracle.TeaMall.bean.Picture;


public class PictureDAOImp extends BaseDAOImp implements PictureDAO {

	
	public boolean addimage(Picture image) {
		System.out.println(image.getImage_shoutu());
		boolean result=false;
		java.sql.Statement  sta=null;
		try {
			sta=getSta();
			String sql="insert into  picture(image_shoutu,image_qita1,image_qita2,image_qita3,image_qita4) values('"+image.getImage_shoutu()+"','"+image.getImage_qita1()+"','"+image.getImage_qita2()+"','"+image.getImage_qita3()+"','"+image.getImage_qita4()+"')";
			int count=sta.executeUpdate(sql);
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Picture> listPictureByPage(int count, int page) {
		ArrayList<Picture>  pictures=new ArrayList<>();
		PreparedStatement   sta=null;
		ResultSet rs=null;
		try {
			sta=getPreSta("select *  from picture order by image_id asc limit  ?,?");
			sta.setInt(1, (page-1)*count);
			sta.setInt(2, count);
			rs=sta.executeQuery();
			while(rs.next()) {
				Picture  picture=new Picture();
				picture.setImage_id(rs.getInt("Image_id"));
				picture.setImage_shoutu(rs.getString("Image_shoutu"));
				picture.setImage_qita1(rs.getString("Image_qita1"));
				picture.setImage_qita2(rs.getString("Image_qita2"));	
				picture.setImage_qita3(rs.getString("Image_qita3"));	
				picture.setImage_qita4(rs.getString("Image_qita4"));
				
				pictures.add(picture);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta,rs);
		return pictures;
	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean deleteimage(int image_id) {
		int  n=0;
		PreparedStatement sta=getPreSta("delete from  picture where image_id=?");
		try {
			  sta.setInt(1, image_id);
			  n=sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0?true:false;
		
	}

	@Override
	public ArrayList<Picture> search(int image_id) {
		System.out.println(image_id);
		ArrayList<Picture>  pictures=new ArrayList<>();
		PreparedStatement   sta=null;
		ResultSet rs=null;
		try {
			System.out.println(image_id);
			sta=getPreSta("select *  from picture where  image_id=?");
			sta.setInt(1,image_id);
			rs=sta.executeQuery();
			while(rs.next()) {
				Picture  picture=new Picture();
				picture.setImage_id(rs.getInt("Image_id"));
				picture.setImage_shoutu(rs.getString("Image_shoutu"));
				picture.setImage_qita1(rs.getString("Image_qita1"));
				picture.setImage_qita2(rs.getString("Image_qita2"));	
				picture.setImage_qita3(rs.getString("Image_qita3"));	
				picture.setImage_qita4(rs.getString("Image_qita4"));
				
				pictures.add(picture);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(sta,rs);
		return pictures;
	}

}
