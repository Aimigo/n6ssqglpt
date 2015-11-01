package www.quality.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.net.URLCodec;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblFunction;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.model.TblUser;
import www.quality.model.TblTsrysj;
import www.quality.util.ExportExcel;
import www.quality.util.Pager;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;

/**
*类的描述:该类是对应TblTsrysj的管理操作
*作者:李留洋
*创建日期:2013-01-24
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("Zztotype")
public class TsryAction extends BasicAction implements ModelDriven<TblTsrysj> {
	
	private TblTsrysj model = new TblTsrysj();
	public TblTsrysj getModel() {
		return model;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblTsrysj的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
		Pager p = this.get_page();
		TblTsryfl fl=null;
		String ryflid = p.getParams().get("ryflid");
		if(ryflid==null||"".equals(ryflid.trim())){
			ryflid = getParameter("ryflid");
			String msg = getParameter("msg");
			try {
				msg=URLDecoder.decode(msg, "utf-8");
				setAttribute("message",msg);
			} catch (Exception e) {
				setAttribute("message",msg);
			}
			
			if(ryflid==null||"".equals(ryflid.trim())){
				String code = getParameter("functioncode3");
				TblFunction function = funser.getOneByCode(code);
				fl = tsryser.getFlByName(function.getName().trim());
				if(fl==null){
					setAttribute("_page", p);
					return "list";
				}
				p.putParam("ryflid",fl.getId()+"");
			}
			else{
				fl=tsryser.getFlById(ryflid);
			}
		}else{
			fl=tsryser.getFlById(ryflid);
		}
		setAttribute("ryfl",fl);
		p.putParam("ryflid",fl.getId()+"");
		p=tsryser.getPagerByCriteria(p);
		setAttribute("_page", p);
		List<TblTsryflzd> flzd = tsryser.getShowFlzdByFlid(fl.getId()+"");
		setAttribute("flzd",flzd);
		List<TblTsryflzd> allflzd = tsryser.getFlzdByFlid(fl.getId()+"");
		setAttribute("allflzd",allflzd);
		return "list";
	}
	
	/**
	 * 函  数  名 :add
	 * 功能描述：跳转到新增页面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 函  数  名 :save
	 * 功能描述：用于新增
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String save(){
		//保存数据
		tsryser.save(model);
		//保存特殊人员
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		//logser.getLog(user.getUsername(), "共青团农场社区管理平台>>文化娱乐", "数码文化娱乐资源管理>>数码文化娱乐分类", "添加", "", null);
		return "success";
	}
	
	/**
	 * 函  数  名 :edit
	 * 功能描述：跳转到修改界面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		model = tsryser.getOneById(Integer.parseInt(id));
		super.setAttribute("model", model);
		return "edit";
	}
	
	/**
	 * 函  数  名 :update
	 * 功能描述：用于修改
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String update(){
		//model.get
		tsryser.update(model);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>文化娱乐", "数码文化娱乐资源管理>>数码文化娱乐分类", "修改", "", null);
		return "success";
	}
	
	/**
	 * 函  数  名 :delete
	 * 功能描述：用于批量或单独删除
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String delete(){
		String id = getParameter("id");
		tsryser.deleteById(id);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>文化娱乐", "数码文化娱乐资源管理>>数码文化娱乐分类", "删除", "", null);
			return "success";
	}
	
	/**
	 * 函  数  名 :detail
	 * 功能描述：用于跳转到查看
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入查看页面
		}
		model = tsryser.getOneById(Integer.parseInt(id));
		super.setAttribute("model", model);
		List<TblTsryflzd> flzd = tsryser.getFlzdByFlid(model.getFlid()+"");
		setAttribute("flzd",flzd);
		TblTsryfl fl = tsryser.getFlById(model.getFlid()+"");
		setAttribute("fl",fl);
		//String sfz = model.getSfz();
		//List list = jmxxser.findByHql("from TblJmxx where idNumber='"+sfz+"'");
		//if(list!=null&&list.size()>0){
		//	setAttribute("jmxx", list.get(0));
		//}
		
		return "detail";
	}
	public void getTsry(){
		String tag = getParameter("tag");
		String name = getParameter("name");
		String stime = getParameter("stime");
		String etime = getParameter("etime");
		
		String flid="115";
		if(tag==null||"".equals(tag)){
			String code = getParameter("code");
			TblFunction function = funser.getOneByCode(code);
			if(function!=null){
				TblTsryfl fl = tsryser.getFlByName(function.getName().trim());
				if(fl!=null)
					flid=fl.getId()+"";
			}
			
			
		}else{
			if("d".equals(tag)){
				flid="115";
			}else if("t".equals(tag)){
				flid="122";
			}
		}
		String hql="from TblTsrysj where flid="+flid;
		
		if(flid=="115"){
			if(name!=null&&!"".equals(name.trim())){
				hql+=" and ryname like '%"+URLDecoder.decode(name.trim())+"%' ";
			}
			if(stime!=null&&!"".equals(stime)){
				hql+=" and s4 >='"+formatData(stime.trim(),null)+"'";
			}
			if(etime!=null&&!"".equals(etime)){
				hql+=" and s4 <='"+formatData(etime.trim(),null)+" 23:59:59'";
			}
		}
		hql+=" order by s4 asc ";
		List<TblTsrysj> list=tsryser.getByHql(hql);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		setAjaxData(json);
	}
	private String formatData(String dt,String format) {
		if(format==null){
			format="yyyy-MM-dd";
		}
		
		String[] st=new String[]{"yyyy-MM-dd","yyyy/MM/dd","dd/MM/yyyy","yyyy年MM月dd日","yyyyMMdd","MM/dd/yyyy","MM-dd-yyyy","dd-MM-yyyy"};
		try {
			SimpleDateFormat format1 = new SimpleDateFormat(format);
			for(int i=0;i<st.length;i++){
				try{
					SimpleDateFormat format2 = new SimpleDateFormat(st[i]);
					Date date1 = format2.parse(dt);
					return format1.format(date1);
				}catch(Exception e1){
				}
			}
			
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 导出模板
	 * @return
	 */
	public InputStream  getDownloadFile() {
		String flid = getParameter("flid");
		List<TblTsryflzd> list = tsryser.getFlzdByFlid(flid);
		TblTsryfl fl = tsryser.getFlById(flid);
		try {
			HSSFWorkbook export = ExportExcel.exportTemplet(list, fl.getName(),
					fl.getName());
			//new FileInputStream(export.get);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		try {
			int rndm = (int) (Math.random() * 10000);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msexcel");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ URLEncoder.encode(fl.getName() + rndm + ".xls", "utf-8"));
			HSSFWorkbook export = ExportExcel.exportTemplet(list, fl.getName(),
					fl.getName());
			export.w
			export.write(response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
	  @Override  
	  public String execute() throws Exception {  
	      return SUCCESS;  
	  }  
	  public void expExcel(){
		  	Pager p = this.get_page();
		  	p.setPageRows(100);
			String ryflid = p.getParams().get("ryflid");
			List<TblTsryflzd> allflzd = tsryser.getFlzdByFlid(ryflid);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("人员信息");
			HSSFRow row = sheet.createRow(0);
			HSSFCellStyle style1 = wb.createCellStyle();
	        HSSFDataFormat format1= wb.createDataFormat();
	        style1.setDataFormat(format1.getFormat("@"));
	        sheet.setDefaultColumnStyle(0, style1);
	        row.createCell(0).setCellValue("姓名");
			for(int i=0;i<allflzd.size();i++){
				sheet.setDefaultColumnStyle(i, style1);
				row.createCell(i+1).setCellValue(allflzd.get(i).getZdname());
			}
			int totalPages=1;
			int curPage=1;
			int rownub=1;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			do{
				p=tsryser.getPagerByCriteria(p);
				totalPages=p.getTotalPages();
				curPage=p.getCurPage();
				p.setCurPage(curPage+1);
				for(int j=0;j<p.getData().size();j++){
					HSSFRow mrow = sheet.createRow(rownub++);
					TblTsrysj sj=(TblTsrysj)p.getData().get(j);
					mrow.createCell(0).setCellValue(sj.getRyname());
					for(int i=0;i<allflzd.size();i++){
						String datazd=allflzd.get(i).getDatazd();
						datazd = "get"+datazd.substring(0, 1).toUpperCase()+ datazd.substring(1, datazd.length());
						try {
							Object invoke = TblTsrysj.class.getMethod(datazd).invoke(sj);
							mrow.createCell(i+1).setCellValue(invoke.toString());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}while(totalPages>curPage);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.reset();
			try {
				 response.addHeader("Content-Disposition", "attachment;filename=" + new String((tsryser.getFlById(ryflid).getName()+".xls").getBytes("utf-8"),"iso8859-1"));
				 response.setContentType("application/octet-stream");
				 response.setCharacterEncoding(ServletActionContext.getRequest().getCharacterEncoding());
				ServletOutputStream fileOut = response.getOutputStream();
				wb.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	  /*public static void main(String[] args) {
		//String ss="大家好";
		URLDecoder decoder = new URLDecoder();
		String s2 = URLDecoder.decode("大家好");
		System.out.println(s2);
		String ss = URLEncoder.encode("大家好");
		System.out.println(ss);
		System.out.println(URLDecoder.decode(ss));
	}*/
}
