package com.stardust.express.actions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;




public class UploadAction extends  ActionExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6671097863297369346L;
	
	private String filename = "";
	private File file; 
	
	
	public String getFilename(){
		return filename;
	}
	
	public void setFilename(String path){
		filename = path;
	}
	
	public void setFile(File image) {
		this.file = image;
	}
	
	public File getFile() {
		return this.file;
	}
	
	public String upload()throws IOException{
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload/snapshoot");
        if (file != null) {
            File savefile = new File(new File(realpath), "test.png");
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
            ActionContext.getContext().put("message", "文件上传成功");
        }
		return SUCCESS;
	}
	
}
