package com.oracle.TeaMall.action;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.oracle.TeaMall.bean.Tea;
import com.oracle.TeaMall.dao.BaseDAO;
import com.oracle.TeaMall.dao.BaseDAOImp;
import com.oracle.TeaMall.dao.TeaDAO;
import com.oracle.TeaMall.dao.TeaDAOImp;
import com.oracle.TeaMall.util.JsonUtil;
import com.oracle.TeaMall.util.Responser;
import com.oracle.TeaMall.util.StringUtil;

import net.sf.json.JSONException;






public class TeaAction extends ActionSupport{

	private Tea tea;//tea对象
	
	public Tea getTea() {
		return tea;
	}

	public void setTea(Tea tea) {
		this.tea = tea;
	}
	private TeaDAOImp dao;
	//TeaDAOImp teadao=new TeaDAOImp();
	
	public TeaAction() {
		dao=new TeaDAOImp();
	}
	private int page;//分页数据
	private int rows;//分页数据
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
	private String product_id;//修改商品资料传递的商品编号
	

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	private String delIds;//批量删除数据的序列号
	
	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	//查询条件的变量get和set	
	private String s_product_name;//搜索茶叶名称
	private String s_minDate;//搜索大于生产时间	
	private String s_maxDate;//搜索小于生产时间
	private String s_product_package;//搜索茶叶的包装盒类型
	private String s_product_series;//搜索系列明前茶或雨前茶
	
	public String getS_product_name() {
		return s_product_name;
	}

	public void setS_product_name(String s_product_name) {
		this.s_product_name = s_product_name;
	}

	public String getS_minDate() {
		return s_minDate;
	}

	public void setS_minDate(String s_minDate) {
		this.s_minDate = s_minDate;
	}

	public String getS_maxDate() {
		return s_maxDate;
	}

	public void setS_maxDate(String s_maxDate) {
		this.s_maxDate = s_maxDate;
	}

	public String getS_product_package() {
		return s_product_package;
	}

	public void setS_product_package(String s_product_package) {
		this.s_product_package = s_product_package;
	}

	public String getS_product_series() {
		return s_product_series;
	}

	public void setS_product_series(String s_product_series) {
		this.s_product_series = s_product_series;
	}
	//uploadfile上传定义
	private File  image;
	private String imageFileName;
	private String imageContentType;
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	/**
	 * 分页显示查询茶叶信息的方法
	 */
	public void listTeasByPage()throws Exception{
		ArrayList<Tea> teas=dao.listTeaByPage(page, rows);
		int a = dao.getAllCountOfproduct();
		JSONObject data = new JSONObject();
		System.out.println(page);
		System.out.println(rows);
		JSONArray  js=new JSONArray();
		for(Tea t:teas) {
			try {
				JSONObject  j=new JSONObject();
				j.put("product_id", t.getProduct_id());
				j.put("product_brand", t.getProduct_brand());
				j.put("product_name", t.getProduct_name());
				j.put("product_sxwx", t.getProduct_sxwx());
				j.put("product_date", t.getProduct_date());
				j.put("product_weight", t.getProduct_weight());
				j.put("product_series", t.getProduct_series());
				j.put("product_package",t.getProduct_package());
				j.put("mall_price", t.getMall_price());
				j.put("product_num", t.getProduct_num());
				j.put("product_image", "<img src='"+t.getProduct_image()+"'  style='width:20px;height:20px'/>");
				j.put("cost_price", t.getCost_price());
				j.put("shifoutuiguang", t.getShifoutuiguang());
				js.put(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		data.put("total", a);
		data.put("rows", js);
		System.out.println(js.toString());
		try {
			Responser.responseToJson(ServletActionContext.getResponse(), ServletActionContext.getRequest(), data.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	//保存茶叶数据(新增和修改)
	public void save()throws Exception{
		//判断product_id是否为空
		if(StringUtil.isNotEmpty(product_id)){
			
			tea.setProduct_id(Integer.parseInt(product_id));
		}
		Connection con=null;
		try{
			
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(product_id)){
			//不为空，修改商品信息
				saveNums=dao.updateTea(tea);
			}else{
			//product_id为空，新增商品
				saveNums=dao.teaAdd(tea);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");//业务逻辑,需要返回success，但返回的是错误message
				result.put("errorMsg", "保存失败");
			}
			Responser.write(ServletActionContext.getResponse(), result);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	//删除数据
	public void delete() {
		try{
			
			JSONObject result=new JSONObject();
			int delNums=dao.deleteTea(delIds);
			System.out.println(delNums);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败");
			}
			Responser.write(ServletActionContext.getResponse(), result);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public String execute()throws Exception{
		
		try {
			
			tea = new Tea();
			
			if(s_product_name!=null){
				tea.setProduct_name(s_product_name);
			}
			if(s_minDate!=null){
				tea.setProduct_date(s_minDate);
			}
			if(s_maxDate!=null){
				tea.setProduct_date(s_maxDate);
			}
			if(s_product_package!=null){
				tea.setProduct_package(s_product_package);
			}
			if(s_product_series!=null){
				tea.setProduct_series(s_product_series);
			}
			
			
			JSONObject result = new JSONObject();
			net.sf.json.JSONArray jsonArray = JsonUtil.formatRsToJsonArray(dao.searchproduct(tea, s_minDate, s_maxDate));
			int total = dao.searchproductCount(tea, s_minDate, s_maxDate);
			result.put("rows", jsonArray);
			result.put("total", total);
			Responser.write(ServletActionContext.getResponse(), result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	//输出excel表格（xls）
	public void exportTea() throws Exception{
		System.out.println("进入到导出Excel");
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
	    HSSFWorkbook wb = new HSSFWorkbook();

	    // 创建Excel的工作sheet,对应到一个excel文档的tab
	    HSSFSheet sheet = wb.createSheet("sheet1");

	    // 设置excel每列宽度
	    sheet.setColumnWidth(0, 4000);
	    sheet.setColumnWidth(1, 3500);

	    // 创建字体样式
	    HSSFFont font = wb.createFont();
	    font.setFontName("Verdana");
	    font.setBoldweight((short) 100);
	    font.setFontHeight((short) 300);
	    font.setColor(HSSFColor.BLUE.index);

	    // 创建单元格样式
	    HSSFCellStyle style = wb.createCellStyle();
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // 设置边框
	    style.setBottomBorderColor(HSSFColor.RED.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	    style.setFont(font);// 设置字体
	    style.setWrapText(true);// 自动换行
	    
	    // 创建Excel的sheet的一行
	    HSSFRow row = sheet.createRow(0);
	    row.setHeight((short) 500);// 设定行的高度
	    HSSFCell cell=null;
	    // 创建一个Excel的单元格
	    String[] cellTitle = {"茶叶编号", "茶叶品牌", "茶叶名称","色香味形", "生产时间", "系列","茶叶的包装盒类型",
	    		"市场价格","库存数量","图片路径","成本价","是否推广"};	
		for (int i = 0; i < cellTitle.length; i++) {
			cell = row.createCell(i);
			// 给Excel的单元格设置样式和赋值
		    cell.setCellStyle(style);
			cell.setCellValue(cellTitle[i]);
		}
		
		
		ResultSet rs=null;
		try{
			rs=dao.searchproduct(tea, s_minDate, s_maxDate);
			int rowIndex=1;
			while(rs.next()){
				row = sheet.createRow(rowIndex++);
				cell = row.createCell(0);
				cell.setCellValue(rs.getInt("product_id"));
				cell = row.createCell(1);
				cell.setCellValue(rs.getString("product_brand"));
				cell = row.createCell(2);
				cell.setCellValue(rs.getString("product_name"));
				cell = row.createCell(3);
				cell.setCellValue(rs.getString("product_sxwx"));
				cell = row.createCell(4);
				cell.setCellValue(rs.getString("product_date"));
				cell = row.createCell(5);
				cell.setCellValue(rs.getString("product_weight"));
				cell = row.createCell(6);
				cell.setCellValue(rs.getString("product_series"));
				cell = row.createCell(7);
				cell.setCellValue(rs.getString("product_package"));
				cell = row.createCell(9);
				cell.setCellValue(rs.getInt("mall_price"));
				cell = row.createCell(10);
				cell.setCellValue(rs.getInt("product_num"));
				cell = row.createCell(11);
				cell.setCellValue(rs.getString("product_image"));
				cell = row.createCell(12);
				cell.setCellValue(rs.getInt("cost_price"));
				cell = row.createCell(13);
				cell.setCellValue(rs.getInt("shifoutuiguang"));
			}
			
	    String exportFileName = "product.xls";
		
		ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String((exportFileName).getBytes(), "ISO8859-1"));//设定输出文件头
		ServletActionContext.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型
		
		
		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
public void updateFile() {
		
		String path=ServletActionContext.getRequest().getRealPath("upload");//用request获取服务器上的upload目录绝对地址
//		System.out.println(path);
//		System.out.println(image);
//		System.out.println(imageFileName);
//		System.out.println(imageContentType);
		String lastFileName=UUID.randomUUID()+imageFileName.substring(imageFileName.lastIndexOf("."),	 imageFileName.length());
		File  dest=new File(path,lastFileName);//新建一个文件对象，准备将上传的文件存储到这个文件位置上
		try {
			FileUtils.copyFile(image, dest);//用apache的fileupload组件里面的文件帮助类直接讲上传的文件拷贝到我们想放置的文件位置上
			System.out.println("upload  ok");
			//ajax response text
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter  out=ServletActionContext.getResponse().getWriter();
			System.out.println("upload/"+lastFileName);
			out.write("upload/"+lastFileName);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	
	}
	



