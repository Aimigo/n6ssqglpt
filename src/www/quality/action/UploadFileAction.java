package www.quality.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import www.quality.util.ConvertVideo;
import www.quality.util.ImageUtil;
import www.quality.util.IoUtil;
import www.quality.util.TokenUtil;
import www.quality.util.UploadConfigurations;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadFileAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	//内存读取大小
	static final int BUFFER_LENGTH = 10240;
	//读取令牌标记
	static final String TOKEN_FIELD = "token";
	//服务器标记
	static final String SERVER_FIELD = "server";
	//是否成功标记 不成功一般会有message 成功也会有提示成功
	static final String SUCCESS = "success";
	//消息标志 可以利用message返回错误信息在前台处理
	static final String MESSAGE = "message";
	//一共传输数据量标记
	static final String START_FIELD = "start";


	//上传文件的类型(后缀名form)
	public String type;
	//存储的客户端上传文件的名字html5方式
	public String name;
	//存储的客户端上传文件的名字form方式
	public String Filename;
	//存储的客户端上传文件
	public File FileData;
	//令牌
	public String token;
	//文件的大小
	public String size;
	//通过什么方式上传 form/html5
	public String client;
	//提交的方式 submit query
	public String  Upload;
	//lx 上传文件的类型，视频，图片，文件，文档 四中 分别对应不同的路径
	public String lx;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilename() {
		return Filename;
	}
	public void setFilename(String filename) {
		Filename = filename;
	}
	public File getFileData() {
		return FileData;
	}
	public void setFileData(File fileData) {
		FileData = fileData;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getUpload() {
		return Upload;
	}
	public void setUpload(String upload) {
		Upload = upload;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * 根据类型判断不同的参数调整页面
	 * 
	 */
	public String uploadfile(){
		String message="";
		if("media".equals(lx)){
			message="请尽量上传mp4、flv、f4v格式视频,若上传其他格式格式转码时间会增长!";
		}else if("img".equals(lx)){

		}else if("imgs".equals(lx)){

		}else if("doc".equals(lx)){

		}else if("file".equals(lx)){

		}else{
			message="系统目前不支持你所传入的类型,请检查参数的正确性！";
		}

		setAttribute(MESSAGE,message);
		setAttribute("lx",lx);
		return "uploadfile";
	}
	/**
	 * 生成上传令牌
	 * @throws Exception
	 */
	public void tk() throws Exception{
		String token = TokenUtil.generateToken(name, size,lx);
		JSONObject json = new JSONObject();
		try {
			json.put(TOKEN_FIELD, token);
			if (UploadConfigurations.isCrossed())
				json.put(SERVER_FIELD, UploadConfigurations.getCrossServer());
			json.put(SUCCESS, true);
			json.put(MESSAGE, "");
		} catch (JSONException e) {
		}
		setAjaxData(json.toString());

	}
	public void upload() throws Exception{
		InputStream in = null;
		JSONObject json = new JSONObject();
		long start = 0;
		boolean success = true;
		String message = "";
		File f = IoUtil.getTokenedFile(token);
		OutputStream out =  new FileOutputStream(f, true);
		if("form".equals(client)){
			in=new FileInputStream(FileData);
			name=this.Filename;

		}else if("html5".equals(client)){
			in = ServletActionContext.getRequest().getInputStream();
		}else{
			message="对不起，你上传的方式暂不支持，请通过正确方式上传，谢谢";
		}
		int read = 0;
		final byte[] bytes = new byte[BUFFER_LENGTH];
		while ((read = in.read(bytes)) != -1)
			out.write(bytes, 0, read);
		start = f.length();
		IoUtil.close(out);
		IoUtil.close(in);
		String upload_url0="";
		String upload_url1="";
		String upload_url2="";
		/** 判断当文件大小size与上传的数据的大小start相等时，重命名文件 */
		if ("form".equals(client)||Long.parseLong(size) == start) {
			//重命名文件 
			//毫秒文件名
			String serialName=String.valueOf(System.currentTimeMillis());
			String hzm=name.substring(name.lastIndexOf("."),name.length());
			name=serialName+hzm;
			File dst = IoUtil.getFile(name);
			dst.delete();
			f.renameTo(dst);

			String path=ServletActionContext.getServletContext().getRealPath("");
			if("media".equals(lx)){
				//源文件位置
				upload_url0=UploadConfigurations.getTempRepository()+ "/" +name;
				//生成的截图位置
				upload_url1=UploadConfigurations.getMediaImgRepository()+"/" + serialName + ".jpg";
				//视频转码后的位置
				upload_url2=UploadConfigurations.getMediaTranscodRepository()+"/"+serialName+".flv";
				//转码
				if(".mp4".equals(hzm)||".flv".equals(hzm)||".f4v".equals(hzm)){
					FileUtils.copyFile(new File(path+"/"+upload_url0),new File(path+"/"+upload_url2));
				}else{
					ConvertVideo.process(path+"/"+upload_url0,path+"/"+upload_url2,path+"/"+UploadConfigurations.getMediaFileRepository()+"/"+serialName+".avi");
				}
				//截图
				ConvertVideo.processImg(path+"/"+upload_url2, path+"/"+upload_url1,"300","290");
			}else if("imgs".equals(lx)||"img".equals(lx)){
				//源文件位置
				upload_url0=UploadConfigurations.getTempRepository()+ "/" +name;
				//生成的mini图片位置
				upload_url1=UploadConfigurations.getImgMiniRepository()+"/" +name;
				//保存的图片位置
				upload_url2=UploadConfigurations.getImgFileRepository()+"/"+name;
				//压缩图片
				ImageUtil mypic = new ImageUtil();
				String flag = mypic.compressPic(path+"/"+UploadConfigurations.getTempRepository()+"/",path+"/"+UploadConfigurations.getImgMiniRepository()+"/", name, name, 300, 290, true);
				if("no".equals(flag)){
					FileUtils.copyFile(new File(path+"/"+upload_url0),new File(path+"/"+upload_url1));
				}
				FileUtils.copyFile(new File(path+"/"+upload_url0),new File(path+"/"+upload_url2));

			}else if("doc".equals(lx)){
				upload_url0=UploadConfigurations.getTempRepository()+ "/" +name;
				upload_url1=UploadConfigurations.getDocFileRepository()+"/"+name;
				upload_url2=UploadConfigurations.getDocOnlineRepository()+"/"+serialName+".swf";
				FileUtils.copyFile(new File(path+"/"+upload_url0),new File(path+"/"+upload_url1));
				FileUtils.copyFile(new File(path+"/"+upload_url0),new File(path+"/"+UploadConfigurations.getDocFileTempRepository()+"/"+name));
			}else if("file".equals(lx)){
				upload_url0=UploadConfigurations.getTempRepository()+ "/" +name;
				upload_url1=UploadConfigurations.getFileRepository()+"/"+name;
				FileUtils.copyFile(new File(path+"/"+upload_url0),new File(path+"/"+upload_url1));
			}else{
				System.out.println("上传的类型不正确");
			}
			/** if `STREAM_DELETE_FINISH`, then delete it. */
			if (UploadConfigurations.isDeleteFinished()) {
				dst.delete();
			}
			//json.put("upload_url0", upload_url0);
			//json.put("upload_url1", upload_url1);
			//json.put("upload_url2", upload_url2);

		}
		if (success){
			json.put(START_FIELD, start);
			json.put("upload_url0", upload_url0);
			json.put("upload_url1", upload_url1);
			json.put("upload_url2", upload_url2);
		}
		json.put(SUCCESS, success);
		json.put(MESSAGE, message);
		setAjaxData(json.toString());

	}
	/**
	 * ajax 请求返回视图
	 * 
	 * @param data
	 *            ajax结果
	 * @return
	 */
	protected String setAjaxData(String data) {
		try {
			if (data != null && !data.trim().equals("")) {
				// 此时请求 的 字符集 为 struts.i18n.encoding
				String ENDODING = ServletActionContext.getRequest()
						.getCharacterEncoding();
				// 设置相应的 字符集 为 struts.i18n.encoding
				ServletActionContext.getResponse().setCharacterEncoding(
						ENDODING);
				PrintWriter pw = ServletActionContext.getResponse().getWriter();
				pw.append(data);
				pw.flush();
				pw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 跳转到文章在线浏览页
	 * @return
	 */
	public String readonline(){
		String width=getParameter("w");
		String height=getParameter("h");
		String src=getParameter("s");
		if("".equals(width)||width==null)width="770";
		if("".equals(height)||height==null)height="500";
		if("".equals(src)||src==null||!"swf".equals(src.substring(src.lastIndexOf(".")+1)))src="doc/noread.swf";
		setAttribute("width",width);
		setAttribute("height",height);
		setAttribute("src",src);
		return "readonline";
	}
	/**
	 * 在线播放视频
	 * @return
	 */
	public String videoplayer(){
		String width=getParameter("w");
		String p=getParameter("p");
		String wh=getParameter("wh");
		String height=getParameter("h");
		String videosrc=getParameter("vs");
		String imgsrc=getParameter("is");
		String ms=getParameter("ms");
		if("".equals(p)||p==null)p="0";
		if("".equals(wh)||wh==null)wh="16:9";
		if("".equals(width)||width==null)width="600";
		if("".equals(height)||height==null)height="400";
		//if("".equals(videosrc)||videosrc==null||!"flv".equals(videosrc.substring(videosrc.lastIndexOf(".")+1)))videosrc="doc/noread.swf";
		//if("".equals(imgsrc)||imgsrc==null||!"jpg".equals(imgsrc.substring(imgsrc.lastIndexOf(".")+1)))imgsrc="doc/noread.swf";
		if("".equals(ms)||ms==null)ms="该视频暂无描述！";
		setAttribute("p",p);
		setAttribute("wh",wh);
		setAttribute("width",width);
		setAttribute("height",height);
		setAttribute("videosrc",videosrc);
		setAttribute("imgsrc",imgsrc);
		setAttribute("ms",ms);
		return "videoplayer";
	}
	protected void setAttribute(String key, Object value) {
		Map m = (Map) ActionContext.getContext().get("request");
		m.put(key, value);
	}
	/**
	 * @param key
	 *            获取 请求作用于参数
	 * @return
	 */
	protected String getParameter(String key) {
		try {
			String[] param = (String[]) ActionContext.getContext()
					.getParameters().get(key);
			return param[0];
		} catch (Exception e) {
			return null;
		}
	}
	public void destroy() {
		// TODO Auto-generated method stub

	}
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
