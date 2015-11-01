package www.quality.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import www.quality.util.ImageUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UpLoadAction extends ActionSupport{
	
	private String PICTURE_HISTORY=null;//图片目录
	private String VIEDO_HISTORY=null;//视频目录
	private File tempImg;//用来封装上传文件的封装类 
	private String tempImgContentType;//上传文件的类型

	private String tempImgFileName;//上传文件的名称
	private String upAddr;

	/**上传文件
	 * @return
	 */
	public String fileUp(){
		if(tempImgFileName==null){
			ServletActionContext.getRequest()
			.setAttribute("message","请不要上传空文件");
			return  INPUT;
		}
		//获取上传文件后缀名
		String tname=tempImgFileName.substring(tempImgFileName.lastIndexOf("."));
		//判断不符合图片的格式
		if(!(tempImgContentType.contains("wmv") ||tempImgContentType.contains("image") )){
			//上传不是图片文件时错误信息
			ServletActionContext.getRequest()
			.setAttribute("message","ERROR 请上传系统支持的图片或视频文件");
			return  INPUT;
		}
		//获取图片上传目录字符串
		if(PICTURE_HISTORY==null){
			PICTURE_HISTORY=ServletActionContext.getServletContext().getRealPath("/picture/");
		}
		//获视频上传目录字符串
		if(VIEDO_HISTORY==null){
			VIEDO_HISTORY=ServletActionContext.getServletContext().getRealPath("/viedo");
		}
		//获取上传目录
		File f=new File(PICTURE_HISTORY);
		//判断目录是否存在 false  建立
		if(!f.exists())
			f.mkdirs();
		//获取上传目录
		File f1=new File(VIEDO_HISTORY);
		//判断目录是否存在 false  建立
		if(!f1.exists())
			f1.mkdirs();
		try {
			//根据当前毫秒数 为上传图片生成文件名
			String imgName=""+System.currentTimeMillis();
			File newFile=null;
			if(tempImgContentType.contains("video"))
				newFile=new File(VIEDO_HISTORY+"/"+imgName+tname);
			if(tempImgContentType.contains("image"))
				newFile=new File(PICTURE_HISTORY+"/"+imgName+tname);
			FileUtils.copyFile(tempImg,newFile);
			
			//压缩图片
			if(tempImgContentType.contains("image")){
				ImageUtil mypic = new ImageUtil();
				String flag = mypic.compressPic(PICTURE_HISTORY+"\\",PICTURE_HISTORY+"\\", imgName+tname, "mini"+imgName+tname, 500, 500, true);
				//把文件名存入action message
				if("no".equals(flag)){
					ServletActionContext.getRequest().setAttribute("imgname",imgName+tname);
				}else{
					ServletActionContext.getRequest().setAttribute("imgname","mini"+imgName+tname);
				}
			}else if(tempImgContentType.contains("video")){
				//把文件名存入action message
				ServletActionContext.getRequest().setAttribute("imgname",imgName+tname);
			}
		} catch (IOException e) {
			ServletActionContext.getRequest().setAttribute("message","上传文件失败");
			return ERROR;
		}
		return  INPUT;
	}
	
	
	public void setTempImg(File tempImg) {
		this.tempImg = tempImg;
	}
	public void setTempImgContentType(String tempImgContentType) {
		this.tempImgContentType = tempImgContentType;
	}
	public void setTempImgFileName(String tempImgFileName) {
		this.tempImgFileName = tempImgFileName;
	}
	
	/* 重写actionError 方法(解决上传文件过大时 	错误信息不能国际化问题)
	 * 
	 */
	@Override
	public void addActionError(String anErrorMessage) {
		if(anErrorMessage.startsWith("the request was rejected because its size")){
			try {
				//获取上传文件大小
				Integer i=new Integer(anErrorMessage.substring(anErrorMessage.indexOf("(")+1,anErrorMessage.indexOf(")")));
				//获取限制大小
				Integer i1=new Integer(anErrorMessage.substring(anErrorMessage.lastIndexOf("(")+1,anErrorMessage.lastIndexOf(")")));
				//设置中文化后的信息
				super.addActionError("您上传的文件 大小为   "+i/1024+" KB   本系统最大允许上传  "+i1/1024+" KB文件");
			} catch (Exception e) {
				super.addActionError(anErrorMessage);
			}
		}else{
			super.addActionError(anErrorMessage);
		}
	}
	
	

	public String getUpAddr() {
		return upAddr;
	}


	public void setUpAddr(String upAddr) {
		this.upAddr = upAddr;
	}


	public String getTempImgContentType() {
		return tempImgContentType;
	}
}
