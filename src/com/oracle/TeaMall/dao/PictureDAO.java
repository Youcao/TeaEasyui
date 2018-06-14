package com.oracle.TeaMall.dao;

import java.util.ArrayList;

import com.oracle.TeaMall.bean.Picture;


public interface PictureDAO extends BaseDAO {

	public ArrayList<Picture>  listPictureByPage(int count,int page);
	public Boolean deleteimage(int image_id);
	public ArrayList<Picture>  search(int image_id);
}
