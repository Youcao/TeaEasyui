package com.oracle.TeaMall.action;


import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.oracle.TeaMall.bean.Picture;
import com.oracle.TeaMall.dao.PictureDAOImp;

@ParentPackage("struts-default")
@Action("admin/UploadAction")
@Results({
	@Result(name="SUCCESS", location="index.jsp")
})
public class UploadAction extends ActionSupport{
	private Picture image=new Picture();
	//表单上提供的字段
	private File[]  myfile;                                                                            
	//struts2在文件上传时提供的属性                                                                                
	private String[] myfileFileName;  //上传的文件名。上传字段名称+FileName  注意大小写                                       
	private String[] myfileContentType;//上传文件的MIME类型。上传字段名称+ContentType 注意大小写         
	String[] lujing=new String[5];
	public String upload (){                                                                            
         //System.out.println(myfile[0]);   
		//1.拿到ServletContext                                                                            
		ServletContext servletContext = ServletActionContext.getServletContext();                       
		//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录
		for(int i=0;i<myfile.length;i++){
			String  uuidName=UUID.randomUUID().toString();
			String childPath="";
			for(int n=0;n<uuidName.length();n++)
			{
				childPath+=uuidName.charAt(n)+"/";
			}
		String realPath = servletContext.getRealPath("/images/uploadFile/"); 
		System.out.println(realPath);
		//3.如果这个真实的目录不存在，需要创建                                                                           
		File file = new File(realPath,childPath);                                                                
		if(!file.exists()){                                                                             
			file.mkdirs();                                                                              
		}                                                                                               
		//4.把文件存过去                                                                                      
		//剪切：把临时文件剪切指定的位置，并且给他重命名。 注意：临时文件没有了                                                           
			myfile[i].renameTo(new File(file,myfileFileName[i]));
			//String uirpath=file.getAbsolutePath();
			String  urlPath="images/uploadFile/"+childPath;
			String imagepath=urlPath+myfileFileName[i];
			//String imagepath1=imagepath.replaceAll("\\\\\\\\", "\\\\\\\\\\\\\\\\");
			lujing[i]=imagepath;
			System.out.println(imagepath);
			
		}    
		image.setImage_shoutu(lujing[0]);
		image.setImage_qita1(lujing[1]);
		image.setImage_qita2(lujing[2]);
		image.setImage_qita3(lujing[3]);
		image.setImage_qita4(lujing[4]);
		System.out.println(lujing[0]);
		PictureDAOImp dao=new  PictureDAOImp();
		boolean result=dao.addimage(image);
		if(result)
		{
			ServletActionContext.getRequest().setAttribute("message", "uploadfail");
			return "SUCCESS"; 
		}else
		{
			return "SUCCESS"; 
		}
		                                                                                
	}                                                                                                   
                                                                                                        
	
	
	
	
	
	
	public File[] getMyfile() {                                                                         
		return myfile;                                                                                  
	}                                                                                                   
                                                                                                        
	public void setMyfile(File[] myfile) {                                                              
		this.myfile = myfile;                                                                           
	}                                                                                                   
                                                                                                        
	public String[] getMyfileFileName() {                                                               
		return myfileFileName;                                                                          
	}                                                                                                   
                                                                                                        
	public void setMyfileFileName(String[] myfileFileName) {                                            
		this.myfileFileName = myfileFileName;                                                           
	}                                                                                                   
                                                                                                        
	public String[] getMyfileContentType() {                                                            
		return myfileContentType;                                                                       
	}                                                                                                   
                                                                                                        
	public void setMyfileContentType(String[] myfileContentType) {                                      
		this.myfileContentType = myfileContentType;                                                     
	}                                                                                                   
                                                                                                        
}
