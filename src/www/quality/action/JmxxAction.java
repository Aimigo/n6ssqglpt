package www.quality.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.jws.WebParam.Mode;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.xmlbeans.impl.schema.StscImporter.DownloadTable;
import org.omg.CORBA.Request;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblCbxx;
import www.quality.model.TblGrid;
import www.quality.model.TblHjgl;
import www.quality.model.TblJtxx;
import www.quality.model.TblRegion;
import www.quality.model.TblSyfwgl;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.model.TblUser;
import www.quality.model.TblJmxx;
import www.quality.model.TblXsxx;
import www.quality.model.TblZzrxx;
import www.quality.util.Pager;
import www.quality.util.ZTree;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
*类的描述:该类是对应TblJmxx的管理操作
*作者:李留洋
*创建日期:2013-01-24
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class JmxxAction extends BasicAction implements ModelDriven<TblJmxx> {
	
	private TblJmxx model = new TblJmxx();
	TblJtxx jtxx=new TblJtxx();
	TblCbxx cbxx=new TblCbxx();
	TblXsxx xsxx=new TblXsxx();
	TblZzrxx zzrxx=new TblZzrxx();
	TblTsrysj sj = new TblTsrysj();
	public TblJmxx getModel() {
		return model;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblJmxx的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
	
		Pager p = this.get_page();
		p.setPageRows(15);
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser user = (TblUser) session.get("user");
		Integer curPage =  (Integer)session.get("curPage");
		if(curPage!=null){
			p.setCurPage(curPage);
			session.remove("curPage");
		}
		String grid=p.getParams().get("grid");
		if(grid==null||grid.isEmpty()){
			p.putParam("grid", user.getGridname());
		}
		p=jmxxser.getPagerByCriteria(p);
		setAttribute("_page", p);
		List<TblTsryfl> fl=jmxxser.getAllTrsy();
		setAttribute("tsryfl", fl);
		String gridname = user.getGridname();
		if(gridname==null||gridname.isEmpty()){
			String[] ss=new String[]{"场部一区网格","场部二区网格","场部三区网格","场部四区网格","场部五区网格","场部六区网格","场部七区网格","一连一区网格",
			"一连二区网格","二连一区网格","二连二区网格","三连网格","四连一区网格 ","四连二区网格","五连网格","六连网格  ","七连网格",
			"八连一区网格 ","八连二区网格","葡萄公司网格"};
			setAttribute("mgrid", ss);
		}else{
			String[] split = gridname.split(",");
			setAttribute("mgrid", split);
		}
		String[] ss=new String[]{"身份证","姓名","户籍状态","性别",	"曾用名",	"所属网格"	,"其他网格","联系手机",
				"固定电话","出生日期"	,"民族",	"政治面貌","文化程度"	,"受教育年限","职业","工作单位","婚姻状况",
				"是否死亡","身高（cm）","血型","宗教信仰","电子邮箱","籍贯","联系方式","户籍详细地址","户籍类型",
				"所属区域","居民身份","公民自身民主权利满意度（%）","有无住房","住房地址/无房原因","社区","小区（路）",	"栋（排）"	,
				"单元","室（号）","备注","家庭编号",	"与家长关系","家长证件号码","家长姓名","家长手机","家长电话","户口薄号","与户主关系",
				"户主身份证号",	"户主姓名"	,"户主手机","固定电话","家庭电话","人户状态","是否外出","户口类别","外出原因","外出时间","外出去向","家庭称号",
				"户口薄号"	,"户主编号","家庭总人数（人）","年用电量（瓦）","住房面积（㎡）","套内面积（㎡）","家庭收入（元）","家庭可支配收入（元）"	,"家庭总支出（元）",
				"食物消费支出","教育支出（元）","医疗支出（元）","文化娱乐支出（元）","其他支出（元）","参加养老保险","养老保险月缉费（元）"	,"养老保险参加时间",	
				"参加医疗保险","医疗保险月缉费（元）","医疗保险参加时间","参加失业保险","失业保险月缉费（元）","失业保险参保时间",	"参加工伤保险",	"参加生育保险",	
				"性别","出生年份","学习阶段","学校","入学时间","预计毕业时间"};
		setAttribute("zdes",ss);
		return "list";
	}
	HSSFSheet sheet=null;
	int totalPages=1;
	int curPage=1;
	int pageRows=100;
	Map<String, String> params=null;
	CountDownLatch threadSignal =null;//new CountDownLatch(20);
	String[] dc=new String[]{};
	public void expExcel(){
		Pager p = this.get_page();
		pageRows=50;
		p.setPageRows(pageRows);
		params = p.getParams();
		try{
			params.put("name",URLDecoder.decode(params.get("name"), "utf-8"));
			params.put("sex",URLDecoder.decode(params.get("sex"), "utf-8"));
		}catch(Exception e){}
		
		threadSignal =new CountDownLatch(20);
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser user = (TblUser) session.get("user");
		p.putParam("grid", user.getGridname());
		String[] ss=new String[]{"身份证","姓名","户籍状态","性别",	"曾用名",	"所属网格"	,"其他网格","联系手机",
				"固定电话","出生日期"	,"民族",	"政治面貌","文化程度"	,"受教育年限","职业","工作单位","婚姻状况",
				"是否死亡","身高（cm）","血型","宗教信仰","电子邮箱","籍贯","联系方式","户籍详细地址","户籍类型",
				"所属区域","居民身份","公民自身民主权利满意度（%）","有无住房","住房地址/无房原因","社区","小区（路）",	"栋（排）"	,
				"单元","室（号）","备注","家庭编号",	"与家长关系","家长证件号码","家长姓名","家长手机","家长电话","户口薄号","与户主关系",
				"户主身份证号",	"户主姓名"	,"户主手机","固定电话","家庭电话","人户状态","是否外出","户口类别","外出原因","外出时间","外出去向","家庭称号",
				"户口薄号"	,"户主编号","家庭总人数（人）","年用电量（瓦）","住房面积（㎡）","套内面积（㎡）","家庭收入（元）","家庭可支配收入（元）"	,"家庭总支出（元）",
				"食物消费支出","教育支出（元）","医疗支出（元）","文化娱乐支出（元）","其他支出（元）","参加养老保险","养老保险月缉费（元）"	,"养老保险参加时间",	
				"参加医疗保险","医疗保险月缉费（元）","医疗保险参加时间","参加失业保险","失业保险月缉费（元）","失业保险参保时间",	"参加工伤保险",	"参加生育保险",	
				"性别","出生年份","学习阶段","学校","入学时间","预计毕业时间"};
		
		dc = getParameterValues("daochuziduan");
		HSSFWorkbook wb = new HSSFWorkbook();
		sheet = wb.createSheet("居民信息");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style1 = wb.createCellStyle();
        HSSFDataFormat format1= wb.createDataFormat();
        style1.setDataFormat(format1.getFormat("@"));
        sheet.setDefaultColumnStyle(0, style1);
        
		for(int i=0;i<dc.length;i++){
			int j = Integer.parseInt(dc[i]);
			sheet.setDefaultColumnStyle(i, style1);
			row.createCell(i).setCellValue(ss[j]);
		}
		p=jmxxser.getPagerByCriteria(p);
		
		totalPages = p.getTotalPages();
		curPage = p.getCurPage();
		//p.setCurPage(curPage+1);
		
		for(int i=0;i<20;i++){
			new Thread("s" + i) {
				public void run() {
					int s = curPage;
					while ((s = curPage++) <= totalPages) {
						System.out.println(s);
						try{
							Pager pager2 = new Pager();
							pager2.setCurPage(s);
							pager2.setTotalPages(totalPages);
							pager2.setParams(params);
							pager2.setPageRows(pageRows);
							pager2=jmxxser.getPagerByCriteria(pager2);
							createXls(pager2);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
					threadSignal.countDown();
				}
			}.start();
		}
		createXls(p);
		try {
			System.out.println("threadSignal=============="+threadSignal.getCount());
			threadSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		try {
			 response.addHeader("Content-Disposition", "attachment;filename=" + new String("居民信息.xls".getBytes("utf-8"),"iso8859-1"));
			 //response.addHeader("Content-Length", "");
			 response.setContentType("application/octet-stream");
			 response.setCharacterEncoding(ServletActionContext.getRequest().getCharacterEncoding());
			ServletOutputStream fileOut = response.getOutputStream();
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void valideNumber(){
		String idNumber = getParameter("idNumber");//
		String jmxxid = getParameter("jmxxid");//
		//List<TblJmxx> list = jmxxser.getByIdcart(idNumber);
		List<TblJmxx> list = jmxxser.getByIdcart2(idNumber,jmxxid);
		if(list.size()>0){
			String name=list.get(0).getWangge();
			List<TblUser> useres=userser.getUserByGridName(list.get(0).getWangge());
			if(useres!=null&&useres.size()>0){
				String phone=useres.get(0).getPhone();
				name+="#"+phone;
			}
			setAjaxData(name);
		}else{
			setAjaxData("1");
		}
	}
	public void createXls(Pager p){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List data = p.getData();
		int rownub = (p.getCurPage()-1)*p.getPageRows()+1;
		for(int j=0;j<data.size();j++){
			HSSFRow dtrow = sheet.createRow(rownub++);
			TblJmxx jmxx = (TblJmxx)data.get(j);
			TblJtxx jt=null;
			TblCbxx cb=null;
			TblXsxx xx =null;
			if("1".equals(jmxx.getHuzhu()))
				jt=jmxxser.getHuzhuBySFZ(model.getIdNumber());
			if("1".equals(jmxx.getCanbao()))
				cb = jmxxser.getCanbaoBySFZ(jmxx.getIdNumber());
			if("1".equals(jmxx.getXuesheng()))
				xx = jmxxser.getXueshengBySFZ(jmxx.getIdNumber());
			for(int i=0;i<dc.length;i++){
				HSSFCell cell = dtrow.createCell(i);
				int n = Integer.parseInt(dc[i]);
				switch (n) {
				case 0:
					cell.setCellValue(jmxx.getIdNumber());
					break;
				case 1:
					cell.setCellValue(jmxx.getName());
					break;
				case 2:
					cell.setCellValue(jmxx.getRegister());
					break;
				case 3:
					cell.setCellValue(jmxx.getGender());
					break;
				case 4:
					cell.setCellValue(jmxx.getAlias());
					break;
				case 5:
					cell.setCellValue(jmxx.getWangge());
					break;
				case 6:
					cell.setCellValue(jmxx.getOtherwg());
					break;
				case 7:
					cell.setCellValue(jmxx.getCellphone());
					break;
				case 8:
					cell.setCellValue(jmxx.getPhone());
					break;
				case 9:
					cell.setCellValue((jmxx.getBirthday()==null?"":format.format(jmxx.getBirthday())));
					break;
				case 10:
					cell.setCellValue(jmxx.getNation());
					break;
				case 11:
					cell.setCellValue(jmxx.getPolitics());
					break;
				case 12:
					cell.setCellValue(jmxx.getEducational());
					break;
				case 13:
					cell.setCellValue(jmxx.getEducationYears());
					break;
				case 14:
					cell.setCellValue(jmxx.getProfession());
					break;
				case 15:
					cell.setCellValue(jmxx.getUnit());
					break;
				case 16:
					cell.setCellValue(jmxx.getMarital());
					break;
				case 17:
					cell.setCellValue(jmxx.getBedied());
					break;
				case 18:
					cell.setCellValue((jmxx.getHeight()==null?0:jmxx.getHeight().doubleValue()));
					break;
				case 19:
					cell.setCellValue(jmxx.getBloodType());
					break;
				case 20:
					cell.setCellValue(jmxx.getFaith());
					break;
				case 21:
					cell.setCellValue(jmxx.getEmail());
					break;
				case 22:
					cell.setCellValue(jmxx.getBirthplace());
					break;
				case 23:
					cell.setCellValue(jmxx.getContact());
					break;
				case 24:
					cell.setCellValue(jmxx.getDomicileDetailedAddress());
					break;
				case 25:
					cell.setCellValue(jmxx.getHouseholdType());
					break;
				case 26:
					cell.setCellValue(jmxx.getSubordinateCompany());
					break;
				case 27:
					cell.setCellValue(jmxx.getResidentialStatus());
					break;
				case 28:
					cell.setCellValue((jmxx.getCitizenSatisfaction()==null?0:jmxx.getCitizenSatisfaction().doubleValue()));
					break;
				case 29:
					cell.setCellValue(jmxx.getPresenceHousing());
					break;
				case 30:
					cell.setCellValue(jmxx.getNohouse());
					break;
				case 31:
					cell.setCellValue(jmxx.getCommunity());
					break;
				case 32:
					cell.setCellValue(jmxx.getRoad());
					break;
				case 33:
					cell.setCellValue(jmxx.getRidgepole());
					break;
				case 34:
					cell.setCellValue(jmxx.getElement());
					break;
				case 35:
					cell.setCellValue(jmxx.getRoomnumber());
					break;
				case 36:
					cell.setCellValue(jmxx.getRemark());
					break;
				case 37:
					cell.setCellValue(jmxx.getFamilyno());
					break;
				case 38:
					cell.setCellValue(jmxx.getParentsRelationship());
					break;
				case 39:
					cell.setCellValue(jmxx.getParentsidno());
					break;
				case 40:
					cell.setCellValue(jmxx.getParentsname());
					break;
				case 41:
					cell.setCellValue(jmxx.getParentscellphone());
					break;
				case 42:
					cell.setCellValue(jmxx.getParentsphone());
					break;
				case 43:
					cell.setCellValue(jmxx.getAccountNumber());
					break;
				case 44:
					cell.setCellValue(jmxx.getAccountRelation());
					break;
				case 45:
					cell.setCellValue(jmxx.getAccountIdnumber());
					break;
				case 46:
					cell.setCellValue(jmxx.getAccountName());
					break;
				case 47:
					cell.setCellValue(jmxx.getAccountCellphone());
					break;
				case 48:
					cell.setCellValue(jmxx.getAccountPhone());
					break;
				case 49:
					cell.setCellValue(jmxx.getAccountFlphone());
					break;
				case 50:
					cell.setCellValue(jmxx.getHushaiState());
					break;
				case 51:
					cell.setCellValue(jmxx.getYnout());
					break;
				case 52:
					cell.setCellValue(jmxx.getAccountType());
					break;
				case 53:
					cell.setCellValue(jmxx.getOutReason());
					break;
				case 54:
					cell.setCellValue(jmxx.getOutTime()==null?"":format.format(jmxx.getOutTime()));
					break;
				case 55:
					cell.setCellValue(jmxx.getOutto());
					break;
				case 56:
					cell.setCellValue(jmxx.getFamilyTitle());
					break;
				case 57:
					if(jt!=null)
						cell.setCellValue(jt.getHkbh()==null?"":jt.getHkbh());
					break;
				case 58:
					if(jt!=null)
						cell.setCellValue(jt.getHzbh());
					break;
				case 59:
					if(jt!=null)
						cell.setCellValue(jt.getZfmj());
					break;
				case 60:
					if(jt!=null)
						cell.setCellValue(jt.getNydl());
					break;
				case 61:
					if(jt!=null)
						cell.setCellValue(jt.getZfmj());
					break;
				case 62:
					if(jt!=null)
						cell.setCellValue(jt.getTnmj());
					break;
				case 63:
					if(jt!=null)
						cell.setCellValue(jt.getJtsr());
					break;
				case 64:
					if(jt!=null)
						cell.setCellValue(jt.getJtkzpsr());
					break;
				case 65:
					if(jt!=null)
						cell.setCellValue(jt.getJtzzc());
					break;
				case 66:
					if(jt!=null)
						cell.setCellValue(jt.getSwxfzc());
					break;
				case 67:
					if(jt!=null)
						cell.setCellValue(jt.getJyzc());
					break;
				case 68:
					if(jt!=null)
						cell.setCellValue(jt.getYlzc());
					break;
				case 69:
					if(jt!=null)
						cell.setCellValue(jt.getWhylzc());
					break;
				case 70:
					if(jt!=null)
						cell.setCellValue(jt.getQtzc());
					break;
				case 71:
					if(cb!=null)
						cell.setCellValue(cb.getCjylbx()!=null&&cb.getCjylbx()?"是":"否");
					break;
				case 72:
					if(cb!=null)
						cell.setCellValue(cb.getYlbxyjf());
					break;
				case 73:
					if(cb!=null)
						cell.setCellValue(cb.getYlbxcbsj()==null?"":format.format(cb.getYlbxcbsj()));
					break;
				case 74:
					if(cb!=null)
						cell.setCellValue(cb.getCjylbx1()!=null&&cb.getCjylbx1()?"是":"否");
					break;
				case 75:
					if(cb!=null)
						cell.setCellValue(cb.getYlbxyjf1());
					break;
				case 76:
					if(cb!=null)
						cell.setCellValue(cb.getYlbxcbsj1()==null?"":format.format(cb.getYlbxcbsj1()));
					break;
				case 77:
					if(cb!=null)
						cell.setCellValue(cb.getCjsybx()!=null&&cb.getCjsybx()?"是":"否");
					break;
				case 78:
					if(cb!=null)
						cell.setCellValue(cb.getSybxyjf());
					break;
				case 79:
					if(cb!=null)
						cell.setCellValue(cb.getSybxcbsj()==null?"":format.format(cb.getSybxcbsj()));
					break;
				case 80:
					if(cb!=null)
						cell.setCellValue(cb.getCjgsbx()!=null&&cb.getCjgsbx()?"是":"否");
					break;
				case 81:
					if(cb!=null)
						cell.setCellValue(cb.getCjsybx1()!=null&&cb.getCjsybx1()?"是":"否");
					break;
				case 82:
					if(xx!=null)
						cell.setCellValue(xx.getXb());
					break;
				case 83:
					if(xx!=null)
						cell.setCellValue(xx.getCsnf());
					break;
				case 84:
					if(xx!=null)
						cell.setCellValue(xx.getXxjd());
					break;
				case 85:
					if(xx!=null)
						cell.setCellValue(xx.getXx());
					break;
				case 86:
					if(xx!=null)
						cell.setCellValue(xx.getRxsj()==null?"":format.format(xx.getRxsj()));
					break;
				case 87:
					if(xx!=null)
						cell.setCellValue(xx.getYjbysj()==null?"":format.format(xx.getYjbysj()));
					break;
				
				default:
					cell.setCellValue("");
					break;
				}
			}
		}
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
		//显示特殊人员分类
		List<TblTsryfl> spl_type=jmxxser.getSpecialType();
		setAttribute("spl_type", spl_type);
		//网格信息
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		String gridname = user.getGridname();
		if(gridname!=null&&!"".equals(gridname)){
			List<TblGrid> gridlist = gridser.getDateByGridname(gridname);
			super.setAttribute("gridlist", gridlist);
		}else{
			List<TblGrid> gridlist = gridser.getAllDate();
			super.setAttribute("gridlist", gridlist);
		}
		List<TblGrid> allgrid = gridser.getAllDate();
		super.setAttribute("allgrid", allgrid);
		//获取户籍tre
		List<TblHjgl> hulist = hjglser.getAllDate();
		setAttribute("hjgl", hulist);
		
		return "add";
	}
	public String addmobile(){
		//显示特殊人员分类
		List<TblTsryfl> spl_type=jmxxser.getSpecialType();
		setAttribute("spl_type", spl_type);
		//网格信息
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		String gridname = user.getGridname();
		if(gridname!=null&&!"".equals(gridname)){
			List<TblGrid> gridlist = gridser.getDateByGridname(gridname);
			super.setAttribute("gridlist", gridlist);
		}else{
			List<TblGrid> gridlist = gridser.getAllDate();
			super.setAttribute("gridlist", gridlist);
		}
		List<TblGrid> allgrid = gridser.getAllDate();
		super.setAttribute("allgrid", allgrid);
		//获取户籍tre
		List<TblHjgl> hulist = hjglser.getAllDate();
		setAttribute("hjgl", hulist);
		
		return "addmobile";
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
		
		//保存特殊人员
		String  tsryflids = model.getTsryflids();
		if(tsryflids!=null&&!"".equals(tsryflids)){
			String[] flids = tsryflids.split(",");
			for(String sid:flids){
				if(!sid.trim().equals("")){
					//TblTsryfl fl=jmxxser.getTsryflById(Integer.parseInt(sid.trim()));
					List<TblTsryflzd> flzds=jmxxser.getZdesByFlid(Integer.parseInt(sid.trim()));
					TblTsrysj sj = new TblTsrysj();
					for(int i=0;i<flzds.size();i++){
						String zd = flzds.get(i).getDatazd();
						String zdsj=getParameter2("id"+sid.trim()+"_"+zd);
						if(zdsj!=null&&!"".equals(zdsj)){
							try {
								TblTsrysj.class.getMethod("set" + zd.substring(0, 1).toUpperCase()
										+ zd.substring(1), String.class).invoke(sj,zdsj);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					sj.setFlid(Integer.parseInt(sid.trim()));
					sj.setSfz(model.getIdNumber());
					sj.setRyname(model.getName());
					jmxxser.saveTsry(sj);
				}
			}
		}
		//保存数据
		model.setCreatetime(new Date());
		jmxxser.save(model);
		//保存户主信息
		String huzhu = model.getHuzhu();
		if(huzhu!=null&&!"".equals(huzhu.trim())){
			jtxx.setSfz(model.getIdNumber());
			jmxxser.saveJtxx(jtxx);
		}
		//保存户主信息
		String account = model.getAccountNumber();
		if(account!=null&&!"".equals(account.trim())){
			String relation = model.getAccountRelation();
			if("户主".equals(relation)){
				List<TblHjgl> hj = hjglser.findByHql("from TblHjgl where hkbh='"+account+"'");
				if(hj.size()<=0){
					String road = model.getRoad();
					String ridgepole = model.getRidgepole();
					String element = model.getElement();
					String roomnumber = model.getRoomnumber();
					TblHjgl hjgl = new TblHjgl();
					hjgl.setDh(model.getAccountPhone());
					hjgl.setSfz(model.getIdNumber());
					hjgl.setHkbh(account);
					hjgl.setHzname(model.getName());
					hjgl.setSj(model.getAccountCellphone());
					String ss="from TblSyfwgl where xqdz='"+road+"' " +
							"and zhuang='"+ridgepole+"' "+
							(element==null||"".equals(element)?"":"and dy='"+element+"' ") +
							(roomnumber==null||"".equals(roomnumber)?"":"and shi='"+roomnumber+"' " );
					List<TblSyfwgl> syfw = syfwglser.findByHql(ss);
					if(syfw!=null&&syfw.size()>0){
						hjgl.setSyfwid(syfw.get(0).getId());
					}
					hjglser.save(hjgl);
				}else{
					String road = model.getRoad();
					String ridgepole = model.getRidgepole();
					String element = model.getElement();
					String roomnumber = model.getRoomnumber();
					TblHjgl hjgl = hj.get(0);
					hjgl.setDh(model.getAccountPhone());
					hjgl.setSfz(model.getIdNumber());
					hjgl.setHkbh(account);
					hjgl.setHzname(model.getName());
					hjgl.setSj(model.getAccountCellphone());
					String ss="from TblSyfwgl where xqdz='"+road+"' " +
							"and zhuang='"+ridgepole+"' "+
							(element==null||"".equals(element)?"":"and dy='"+element+"' ") +
							(roomnumber==null||"".equals(roomnumber)?"":"and shi='"+roomnumber+"' " );
					List<TblSyfwgl> syfw = syfwglser.findByHql(ss);
					if(syfw!=null&&syfw.size()>0){
						hjgl.setSyfwid(syfw.get(0).getId());
					}
					hjglser.update(hjgl);
				}
			}
		}
		//保存参保信息
		String canbao = model.getCanbao();
		if(canbao!=null&&!"".equals(canbao.trim())){
			cbxx.setSfz(model.getIdNumber());
			jmxxser.saveCbxx(cbxx);
		}
		//保存学生信息
		String xuesheng = model.getXuesheng();
		if(xuesheng!=null&&!"".equals(xuesheng.trim())){
			xsxx.setSfz(model.getIdNumber());
			jmxxser.saveXsxx(xsxx);
		}
		//保存租住人信息
		String register = model.getRegister();
		if(register!=null&&"流动人口".equals(register.trim())){
			Integer ccfwid = zzrxx.getCcfwid();
			if(ccfwid!=null){
				List<TblZzrxx> list = zzrxxser.findByHql("from TblZzrxx where sfz='"+model.getIdNumber()+"'");
				if(list.size()>0){
					zzrxx.setId(list.get(0).getId());
				}
				zzrxx.setSfz(model.getIdNumber());
				zzrxx.setXm(model.getName());
				zzrxx.setSfz(model.getIdNumber());
				zzrxx.setJg(model.getBirthplace());
				zzrxx.setXb(model.getGender());
				zzrxx.setMz(model.getNation());
				zzrxx.setCsny(model.getBirthday());
				zzrxx.setWhcd(model.getEducational());
				zzrxx.setZzmm(model.getPolitics());
				zzrxx.setHkszd(model.getBirthplace());
				zzrxx.setDh(model.getCellphone());
				zzrxx.setHyzk(model.getMarital());
				zzrxxser.save(zzrxx);
			}
		}
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", "人员管理 >> 人员信息管理 ", "添加", "添加了人员信息名为“"+model.getName()+"”的数据", null);
		return "success";
	}
	
	private String getParameter2(String key) {
		String[] ps = (String[])ActionContext.getContext().getParameters().get(key);
		if(ps==null||ps.length<=0){
			return null;
		}
		String value="";
		for(String p:ps){
			if(!"".equals(p.trim())){
				if("".equals(value)){
					value+=p;
				}else{
					value+=","+p;
				}
			}
		}
		return value;
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
		model = jmxxser.getOneById(Integer.parseInt(id));
		super.setAttribute("model", model);
		//特殊人员
		//String tsryflids = model.getTsryflids();
		Map<String, List<TblTsryflzd>> map = jmxxser.getTsryInfoBySFZ(model.getIdNumber());
		setAttribute("tsry",map);
		//List<TblTsryflzd> list=jmxxser.getTsrysjBySfz(model.getIdNumber());
		//setAttribute("tsry",list);
		//户主信息
		String huzhu = model.getHuzhu();
		if(huzhu!=null&&"1".equals(huzhu.trim())){
			TblJtxx jtxx=jmxxser.getHuzhuBySFZ(model.getIdNumber());
			setAttribute("jtxx",jtxx);
		}
		//参保信息
		String canbao = model.getCanbao();
		if(canbao!=null&&"1".equals(canbao.trim())){
			TblCbxx cbxx=jmxxser.getCanbaoBySFZ(model.getIdNumber());
			setAttribute("cbxx",cbxx);
		}
		//学生信息
		String xuesheng = model.getXuesheng();
		if(xuesheng!=null&&"1".equals(xuesheng.trim())){
			TblXsxx xsxx=jmxxser.getXueshengBySFZ(model.getIdNumber());
			setAttribute("xsxx",xsxx);
		}
		//显示特殊人员分类
		List<TblTsryfl> spl_type=jmxxser.getSpecialType();
		setAttribute("spl_type", spl_type);
		//网格信息
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		String gridname = user.getGridname();
		if(gridname!=null&&!"".equals(gridname)){
			List<TblGrid> gridlist = gridser.getDateByGridname(gridname);
			super.setAttribute("gridlist", gridlist);
		}else{
			List<TblGrid> gridlist = gridser.getAllDate();
			super.setAttribute("gridlist", gridlist);
		}
		List<TblGrid> allgrid = gridser.getAllDate();
		super.setAttribute("allgrid", allgrid);
		//获取户籍tre
		List<TblHjgl> hulist = hjglser.getAllDate();
		setAttribute("hjgl", hulist);
		
		List<TblZzrxx> list = zzrxxser.findByHql("from TblZzrxx where sfz='"+model.getIdNumber()+"'");
		if(list!=null&&list.size()>0){
			setAttribute("zzrxx",list.get(0));
			TblSyfwgl syfwgl = syfwglser.getOneById(list.get(0).getCcfwid());
			setAttribute("syfw", syfwgl);
			
		}
		//获得全部家庭成员
		String an = model.getAccountNumber();
		if(an!=null&&!"".equals(an)){
			List<TblJmxx> jmlist = jmxxser.findByHql("from TblJmxx where accountNumber='"+an+"' ");//and id!='"+model.getId()+"'");
			setAttribute("tjcy", jmlist);
		}
		
		
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
		
		//保存特殊人员
		jmxxser.delTsryByIdcart(model.getIdNumber());
		String  tsryflids = model.getTsryflids();
		if(tsryflids!=null&&!"".equals(tsryflids)){
			String[] flids = tsryflids.split(",");
			for(String sid:flids){
				if(!sid.trim().equals("")){
					//TblTsryfl fl=jmxxser.getTsryflById(Integer.parseInt(sid.trim()));
					List<TblTsryflzd> flzds=jmxxser.getZdesByFlid(Integer.parseInt(sid.trim()));
					TblTsrysj sj = new TblTsrysj();
					for(int i=0;i<flzds.size();i++){
						String zd = flzds.get(i).getDatazd();
						String zdsj=getParameter2("id"+sid.trim()+"_"+zd);
						if(zdsj!=null&&!"".equals(zdsj)){
							try {
								TblTsrysj.class.getMethod("set" + zd.substring(0, 1).toUpperCase()
										+ zd.substring(1), String.class).invoke(sj,zdsj);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					sj.setFlid(Integer.parseInt(sid.trim()));
					sj.setSfz(model.getIdNumber());
					sj.setRyname(model.getName());
					jmxxser.saveTsry(sj);
				}
			}
		}
		//保存数据
		jmxxser.update(model);
		//保存户主信息
		String huzhu = model.getHuzhu();
		jmxxser.delHzxxByIdcart(model.getIdNumber());
		if(huzhu!=null&&!"".equals(huzhu.trim())){
			jtxx.setSfz(model.getIdNumber());
			jmxxser.saveJtxx(jtxx);
		}
		//保存参保信息
		String canbao = model.getCanbao();
		jmxxser.delCbxxByIdcart(model.getIdNumber());
		if(canbao!=null&&!"".equals(canbao.trim())){
			cbxx.setSfz(model.getIdNumber());
			jmxxser.saveCbxx(cbxx);
		}
		//保存学生信息
		String xuesheng = model.getXuesheng();
		jmxxser.delXsxxByIdcart(model.getIdNumber());
		if(xuesheng!=null&&!"".equals(xuesheng.trim())){
			xsxx.setSfz(model.getIdNumber());
			jmxxser.saveXsxx(xsxx);
		}
		//保存户主信息
		String account = model.getAccountNumber();
		if(account!=null&&!"".equals(account.trim())){
			String relation = model.getAccountRelation();
			if("户主".equals(relation)){
				List<TblHjgl> hj = hjglser.findByHql("from TblHjgl where hkbh='"+account+"'");
				if(hj.size()<=0){
					String road = model.getRoad();
					String ridgepole = model.getRidgepole();
					String element = model.getElement();
					String roomnumber = model.getRoomnumber();
					TblHjgl hjgl = new TblHjgl();
					hjgl.setDh(model.getAccountPhone());
					hjgl.setSfz(model.getIdNumber());
					hjgl.setHkbh(account);
					hjgl.setHzname(model.getName());
					hjgl.setSj(model.getAccountCellphone());
					String ss="from TblSyfwgl where xqdz='"+road+"' " +
							"and zhuang='"+ridgepole+"' "+
							(element==null||"".equals(element)?"":"and dy='"+element+"' ") +
							(roomnumber==null||"".equals(roomnumber)?"":"and shi='"+roomnumber+"' " );
					List<TblSyfwgl> syfw = syfwglser.findByHql(ss);
					if(syfw!=null&&syfw.size()>0){
						hjgl.setSyfwid(syfw.get(0).getId());
					}
					hjglser.save(hjgl);
				}else{
					String road = model.getRoad();
					String ridgepole = model.getRidgepole();
					String element = model.getElement();
					String roomnumber = model.getRoomnumber();
					TblHjgl hjgl = hj.get(0);
					hjgl.setDh(model.getAccountPhone());
					hjgl.setSfz(model.getIdNumber());
					hjgl.setHkbh(account);
					hjgl.setHzname(model.getName());
					hjgl.setSj(model.getAccountCellphone());
					String ss="from TblSyfwgl where xqdz='"+road+"' " +
							"and zhuang='"+ridgepole+"' "+
							(element==null||"".equals(element)?"":"and dy='"+element+"' ") +
							(roomnumber==null||"".equals(roomnumber)?"":"and shi='"+roomnumber+"' " );
					List<TblSyfwgl> syfw = syfwglser.findByHql(ss);
					if(syfw!=null&&syfw.size()>0){
						hjgl.setSyfwid(syfw.get(0).getId());
					}
					hjglser.update(hjgl);
				}
			}
		}
		//保存租住人信息
		String register = model.getRegister();
		if(register!=null&&"流动人口".equals(register.trim())){
			Integer ccfwid = zzrxx.getCcfwid();
			if(ccfwid!=null){
				List<TblZzrxx> list = zzrxxser.findByHql("from TblZzrxx where sfz='"+model.getIdNumber()+"'");
				if(list.size()>0){
					zzrxx.setId(list.get(0).getId());
				}
				zzrxx.setSfz(model.getIdNumber());
				zzrxx.setXm(model.getName());
				zzrxx.setSfz(model.getIdNumber());
				zzrxx.setJg(model.getBirthplace());
				zzrxx.setXb(model.getGender());
				zzrxx.setMz(model.getNation());
				zzrxx.setCsny(model.getBirthday());
				zzrxx.setWhcd(model.getEducational());
				zzrxx.setZzmm(model.getPolitics());
				zzrxx.setHkszd(model.getBirthplace());
				zzrxx.setDh(model.getCellphone());
				zzrxx.setHyzk(model.getMarital());
				zzrxxser.save(zzrxx);
			}
		}
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", 
				"人员管理 >> 人员信息管理 ", "修改", "修改人员信息名称为“"+model.getName()+"”的数据", null);
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
		String ids = getParameter("ids");
		String names=jmxxser.delByIds(ids);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", 
				"人员管理 >> 人员信息管理 ", "删除", "删除人员信息名称为“"+names+"”的数据", null);
			return null;
	}
	public String showgjcx(){
		return "gjcx";
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
		String include = getParameter("include");
		setAttribute("include", include);
		if(null==id || "".equals(id)){
			String sfz = super.getParameter("sfz");
			if(sfz!=null){
				model = jmxxser.getOneByIdCard(sfz);
			}else{
				return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
			}
		}else{
			model = jmxxser.getOneById(Integer.parseInt(id));
		}
		
		super.setAttribute("model", model);
		//特殊人员
		Map<String, List<TblTsryflzd>> map = jmxxser.getTsryInfoBySFZ(model.getIdNumber());
		setAttribute("tsry",map);
		//户主信息
		String huzhu = model.getHuzhu();
		if(huzhu!=null&&"1".equals(huzhu.trim())){
			TblJtxx jtxx=jmxxser.getHuzhuBySFZ(model.getIdNumber());
			setAttribute("jtxx",jtxx);
		}
		//参保信息
		String canbao = model.getCanbao();
		if(canbao!=null&&"1".equals(canbao.trim())){
			TblCbxx cbxx=jmxxser.getCanbaoBySFZ(model.getIdNumber());
			setAttribute("cbxx",cbxx);
		}
		//学生信息
		String xuesheng = model.getXuesheng();
		if(xuesheng!=null&&"1".equals(xuesheng.trim())){
			TblXsxx xsxx=jmxxser.getXueshengBySFZ(model.getIdNumber());
			setAttribute("xsxx",xsxx);
		}
		//显示特殊人员分类
		List<TblTsryfl> spl_type=jmxxser.getSpecialType();
		setAttribute("spl_type", spl_type);
		//网格信息
		List<TblGrid> gridlist = gridser.getAllDate();
		super.setAttribute("gridlist", gridlist);
		
		String an = model.getAccountNumber();
		if(an!=null&&!"".equals(an)){
			List<TblJmxx> jmlist = jmxxser.findByHql("from TblJmxx where accountNumber='"+an+"' ");//and id!='"+model.getId()+"'");
			setAttribute("tjcy", jmlist);
		}
		
		List<TblZzrxx> list = zzrxxser.findByHql("from TblZzrxx where sfz='"+model.getIdNumber()+"'");
		if(list!=null&&list.size()>0){
			setAttribute("zzrxx",list.get(0));
			TblSyfwgl syfwgl = syfwglser.getOneById(list.get(0).getCcfwid());
			setAttribute("syfw", syfwgl);
			
		}
		return "detail";
	}
	
	public void hjgx(){
		String ryid = getParameter("ryid");
		String ar=getParameter("ar");
		String an = getParameter("an");
		Boolean b=jmxxser.findHjgx(ryid,ar,an);
		if(b){
			setAjaxData("0");
		}else{
			setAjaxData("1");
		}
		
	}
	public void verifyRepeat(){
		String repeatTab = getParameter("repeatTab");
		String repeatZd = getParameter("repeatZd");
		String value = getParameter("value");
		String repeatId = getParameter("repeatId");
		String hql="from "+repeatTab+" where "+repeatZd+" = '"+value+"' ";
		if(repeatId!=null&&!"".equals(repeatId.trim())&&!"undefined".equals(repeatId)){
			hql+=" and id!="+repeatId.trim();
		}
		List list=jmxxser.findByHql(hql);
		if(list!=null&&list.size()>0){
			setAjaxData("0");//有重复
		}else{
			setAjaxData("1");
		}
		
	}
	public void getFlzd(){
		String flid = getParameter("flid");
		List<TblTsryflzd> flzd = jmxxser.getFlzdByFlid(flid);
		setAjaxData(new Gson().toJson(flzd));
	}

	public TblTsrysj getSj() {
		return sj;
	}

	public void setSj(TblTsrysj sj) {
		this.sj = sj;
	}

	public TblJtxx getJtxx() {
		return jtxx;
	}

	public void setJtxx(TblJtxx jtxx) {
		this.jtxx = jtxx;
	}

	public TblCbxx getCbxx() {
		return cbxx;
	}

	public void setCbxx(TblCbxx cbxx) {
		this.cbxx = cbxx;
	}

	public TblXsxx getXsxx() {
		return xsxx;
	}

	public void setXsxx(TblXsxx xsxx) {
		this.xsxx = xsxx;
	}
	//人员数据的导入模板。
	ByteArrayInputStream excelFile;
	String fileName;
	String msg;
	/**
	 * 导出模板
	 * @return
	 */
	public String getTemplet(){
		return "excel";
	}
	public InputStream getExcelFile() throws Exception  {
		fileName="人员信息";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		// 另一个字体样式   
        HSSFFont columnHeadFont = workbook.createFont();   
        columnHeadFont.setFontName("宋体");   
        columnHeadFont.setFontHeightInPoints((short) 15);   
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式   
        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();   
        columnHeadStyle.setFont(columnHeadFont);   
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        columnHeadStyle.setLocked(true);   
        columnHeadStyle.setWrapText(true);   
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色   
        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小   
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色   
        columnHeadStyle.setBorderRight((short) 1);// 边框的大小   
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体   
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色   
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）   
        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);  
		
		
		
		sheet.createFreezePane(0,2);// 冻结 
		workbook.setSheetName(0,"居民信息");

		HSSFRow row= sheet.createRow((short)0);
		CellRangeAddress range = new CellRangeAddress(0, 0, 0, 36);   
	    sheet.addMergedRegion(range); 
		HSSFCell cell;
		//居民基本信息
		cell = row.createCell(0);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("基本信息");
		cell.setCellStyle(columnHeadStyle);  
		HSSFRow row2= sheet.createRow((short)1);
		cell = row2.createCell(0);
		cell.setCellValue("身份证");
		HSSFCellStyle style1 = workbook.createCellStyle();
        HSSFDataFormat format1= workbook.createDataFormat();
        style1.setDataFormat(format1.getFormat("@"));
        sheet.setDefaultColumnStyle(0, style1);
		cell = row2.createCell(1);
		cell.setCellValue("姓名");
		cell = row2.createCell(2);
		cell.setCellValue("户籍状态");
		CellRangeAddressList regions = new CellRangeAddressList(2,99999,2,2);
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(new String[]{"户籍人口","流动人口"});
		HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(3);
		cell.setCellValue("性别");
		regions = new CellRangeAddressList(2,99999,3,3);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"男","女"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(4);
		cell.setCellValue("曾用名");
		cell = row2.createCell(5);
		cell.setCellValue("所属网格");
		cell = row2.createCell(6);
		cell.setCellValue("其他网格");
		List<TblGrid> gridlist = gridser.getAllDate();
		if(gridlist!=null&&gridlist.size()>0){
			regions = new CellRangeAddressList(2,99999,5,5);
			String[] ss=new String[gridlist.size()];
			for(int i=0;i<gridlist.size();i++){
				ss[i]=gridlist.get(i).getName().trim();
			}
			constraint = DVConstraint.createExplicitListConstraint(ss);
			data_validation = new HSSFDataValidation(regions,constraint);
			sheet.addValidationData(data_validation);
			regions = new CellRangeAddressList(2,99999,6,6);
			data_validation = new HSSFDataValidation(regions,constraint);
			sheet.addValidationData(data_validation);
		}
		cell = row2.createCell(7);
		cell.setCellValue("联系手机");
		sheet.setDefaultColumnStyle(7, style1);
		cell = row2.createCell(8);
		cell.setCellValue("固定电话");
		sheet.setDefaultColumnStyle(8, style1);
		cell = row2.createCell(9);
		cell.setCellValue("出生日期");
		regions = new CellRangeAddressList(2, 999999,9,9);//设置范围   
        constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
        HSSFDataValidation dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
        sheet.addValidationData(dataValidate); 
		cell = row2.createCell(10);
		cell.setCellValue("民族");
		cell = row2.createCell(11);
		cell.setCellValue("政治面貌");
		cell = row2.createCell(12);
		cell.setCellValue("文化程度");
		cell = row2.createCell(13);
		cell.setCellValue("受教育年限");
		sheet.setDefaultColumnStyle(13, style1);
		cell = row2.createCell(14);
		cell.setCellValue("职业");
		cell = row2.createCell(15);
		cell.setCellValue("工作单位");
		cell = row2.createCell(16);
		cell.setCellValue("婚姻状况");
		cell = row2.createCell(17);
		cell.setCellValue("是否死亡");
		regions = new CellRangeAddressList(2,99999,17,17);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"否","是"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(18);
		cell.setCellValue("身高（cm）");
		regions = new CellRangeAddressList(2, 999999,18,18);//设置范围   
		constraint = DVConstraint.createNumericConstraint(DVConstraint.ValidationType.DECIMAL,DVConstraint.OperatorType.BETWEEN, "0", "1000");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入数字");   
        sheet.addValidationData(dataValidate); 
		//style.setDataFormat(format.getFormat("0.0"));
		cell = row2.createCell(19);
		cell.setCellValue("血型");
		cell = row2.createCell(20);
		cell.setCellValue("宗教信仰");
		cell = row2.createCell(21);
		cell.setCellValue("电子邮箱");
		sheet.setDefaultColumnStyle(21, style1);
		cell = row2.createCell(22);
		cell.setCellValue("籍贯");
		cell = row2.createCell(23);
		cell.setCellValue("联系方式");
		sheet.setDefaultColumnStyle(23, style1);
		cell = row2.createCell(24);
		cell.setCellValue("户籍详细地址");
		cell = row2.createCell(25);
		cell.setCellValue("户籍类型");
		regions = new CellRangeAddressList(2,99999,25,25);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"城镇","农村"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(26);
		cell.setCellValue("所属区域");
		cell = row2.createCell(27);
		cell.setCellValue("居民身份");
		regions = new CellRangeAddressList(2,99999,27,27);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"居民","职工","管理人员","其他"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(28);
		cell.setCellValue("公民自身民主权利满意度（%）");// TODO 必须为数字
		regions = new CellRangeAddressList(2, 999999,28,28);//设置范围   
		constraint = DVConstraint.createNumericConstraint(DVConstraint.ValidationType.INTEGER,DVConstraint.OperatorType.BETWEEN, "0", "100");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入数字");   
        sheet.addValidationData(dataValidate); 
		cell = row2.createCell(29);
		cell.setCellValue("有无住房");
		cell = row2.createCell(30);
		cell.setCellValue("住房地址/无房原因");
		cell = row2.createCell(31);
		cell.setCellValue("社区");
		cell = row2.createCell(32);
		cell.setCellValue("小区（路）");
		sheet.setDefaultColumnStyle(32, style1);
		cell = row2.createCell(33);
		cell.setCellValue("栋（排）");
		sheet.setDefaultColumnStyle(33, style1);
		cell = row2.createCell(34);
		cell.setCellValue("单元");
		sheet.setDefaultColumnStyle(34, style1);
		cell = row2.createCell(35);
		cell.setCellValue("室（号）");
		sheet.setDefaultColumnStyle(35, style1);
		cell = row2.createCell(36);
		cell.setCellValue("备注");
		//家庭信息  
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 37, 42)); 
		cell = row.createCell(37);
		cell.setCellStyle(columnHeadStyle);  
		cell.setCellValue("家庭信息");
		cell = row2.createCell(37);
		cell.setCellValue("家庭编号");
		sheet.setDefaultColumnStyle(37, style1);
		cell = row2.createCell(38);
		cell.setCellValue("与家长关系");
		cell = row2.createCell(39);
		cell.setCellValue("家长证件号码");
		sheet.setDefaultColumnStyle(39, style1);
		cell = row2.createCell(40);
		cell.setCellValue("家长姓名");
		cell = row2.createCell(41);
		cell.setCellValue("家长手机");
		sheet.setDefaultColumnStyle(41, style1);
		cell = row2.createCell(42);
		cell.setCellValue("家长电话");
		sheet.setDefaultColumnStyle(42, style1);
		//户籍关系  
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 43, 56)); 
		cell = row.createCell(43);
		cell.setCellValue("户籍关系");
		cell.setCellStyle(columnHeadStyle); 
		cell = row2.createCell(43);
		cell.setCellValue("户口薄号");
		sheet.setDefaultColumnStyle(43, style1);
		cell = row2.createCell(44);
		cell.setCellValue("与户主关系");
		regions = new CellRangeAddressList(2,99999,44,44);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"户主","妻","长子","次子","长女","次女"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(45);
		cell.setCellValue("户主身份证号");
		sheet.setDefaultColumnStyle(45, style1);
		cell = row2.createCell(46);
		cell.setCellValue("户主姓名");
		cell = row2.createCell(47);
		cell.setCellValue("户主手机");
		sheet.setDefaultColumnStyle(47, style1);
		cell = row2.createCell(48);
		cell.setCellValue("固定电话");
		sheet.setDefaultColumnStyle(48, style1);
		cell = row2.createCell(49);
		cell.setCellValue("家庭电话");
		sheet.setDefaultColumnStyle(49, style1);
		cell = row2.createCell(50);
		cell.setCellValue("人户状态");
		sheet.setDefaultColumnStyle(50, style1);
		cell = row2.createCell(51);
		cell.setCellValue("是否外出");
		regions = new CellRangeAddressList(2,99999,51,51);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"否","是"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(52);
		cell.setCellValue("户口类别");
		cell = row2.createCell(53);
		cell.setCellValue("外出原因");
		cell = row2.createCell(54);
		cell.setCellValue("外出时间");
		regions = new CellRangeAddressList(2, 999999,54,54);//设置范围   
        constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
        sheet.addValidationData(dataValidate); 
		cell = row2.createCell(55);
		cell.setCellValue("外出去向");
		cell = row2.createCell(56);
		cell.setCellValue("家庭称号");
		regions = new CellRangeAddressList(2,99999,56,56);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"五好家庭","平安家庭"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		
		//户主（家庭 
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 57, 70)); 
		cell = row.createCell(57);
		cell.setCellValue("户主（家庭）");
		cell.setCellStyle(columnHeadStyle);  
		cell = row2.createCell(57);
		sheet.setDefaultColumnStyle(57, style1);
		cell.setCellValue("户口薄号");
		cell = row2.createCell(58);
		sheet.setDefaultColumnStyle(58, style1);
		cell.setCellValue("户主编号");
		cell = row2.createCell(59);
		cell.setCellValue("家庭总人数（人）");
		sheet.setDefaultColumnStyle(59, style1);
		cell = row2.createCell(60);
		cell.setCellValue("年用电量（瓦）");
		sheet.setDefaultColumnStyle(60, style1);
		cell = row2.createCell(61);
		sheet.setDefaultColumnStyle(61, style1);
		cell.setCellValue("住房面积（㎡）");
		cell = row2.createCell(62);
		sheet.setDefaultColumnStyle(62, style1);
		cell.setCellValue("套内面积（㎡）");
		cell = row2.createCell(63);
		sheet.setDefaultColumnStyle(63, style1);
		cell.setCellValue("家庭收入（元）");
		cell = row2.createCell(64);
		sheet.setDefaultColumnStyle(64, style1);
		cell.setCellValue("家庭可支配收入（元）");
		cell = row2.createCell(65);
		sheet.setDefaultColumnStyle(65, style1);
		cell.setCellValue("家庭总支出（元）");
		cell = row2.createCell(66);
		sheet.setDefaultColumnStyle(66, style1);
		cell.setCellValue("食物消费支出");
		cell = row2.createCell(67);
		sheet.setDefaultColumnStyle(67, style1);
		cell.setCellValue("教育支出（元）");
		cell = row2.createCell(68);
		sheet.setDefaultColumnStyle(68, style1);
		cell.setCellValue("医疗支出（元）");
		cell = row2.createCell(69);
		sheet.setDefaultColumnStyle(69, style1);
		cell.setCellValue("文化娱乐支出（元）");
		cell = row2.createCell(70);
		cell.setCellValue("其他支出（元）");
		sheet.setDefaultColumnStyle(70, style1);
		
		//参保信息
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 71, 81)); 
		cell = row.createCell(71);
		cell.setCellValue("参加养老保险");
		cell.setCellStyle(columnHeadStyle); 
		cell = row2.createCell(71);
		cell.setCellValue("参加养老保险");
		regions = new CellRangeAddressList(2,99999,71,71);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"否","是"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(72);
		sheet.setDefaultColumnStyle(72, style1);
		cell.setCellValue("养老保险月缉费（元）");
		cell = row2.createCell(73);
		cell.setCellValue("养老保险参加时间");
		regions = new CellRangeAddressList(2, 999999,73,73);//设置范围   
        constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
        sheet.addValidationData(dataValidate); 
		cell = row2.createCell(74);
		cell.setCellValue("参加医疗保险");
		regions = new CellRangeAddressList(2,99999,74,74);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"否","是"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(75);
		cell.setCellValue("医疗保险月缉费（元）");
		sheet.setDefaultColumnStyle(75, style1);
		cell = row2.createCell(76);
		cell.setCellValue("医疗保险参加时间");
		regions = new CellRangeAddressList(2, 999999,76,76);//设置范围   
        constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
        sheet.addValidationData(dataValidate); 
		cell = row2.createCell(77);
		cell.setCellValue("参加失业保险");
		regions = new CellRangeAddressList(2,99999,77,77);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"否","是"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(78);
		cell.setCellValue("失业保险月缉费（元）");
		sheet.setDefaultColumnStyle(78, style1);
		cell = row2.createCell(79);
		cell.setCellValue("失业保险参保时间");
		regions = new CellRangeAddressList(2, 999999,79,79);//设置范围   
        constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
        sheet.addValidationData(dataValidate); 
		cell = row2.createCell(80);
		cell.setCellValue("参加工伤保险");
		regions = new CellRangeAddressList(2,99999,80,80);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"否","是"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		/*
		cell = row2.createCell(81);
		cell.setCellValue("食物消费支出");*/
		cell = row2.createCell(81);
		cell.setCellValue("参加生育保险");
		regions = new CellRangeAddressList(2,99999,81,81);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"否","是"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);

		//学生信息
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 82, 87)); 
		cell = row.createCell(82);
		cell.setCellValue("学生信息");
		cell.setCellStyle(columnHeadStyle);  
		cell = row2.createCell(82);
		cell.setCellValue("性别");
		regions = new CellRangeAddressList(2,99999,82,82);
		constraint = DVConstraint.createExplicitListConstraint(new String[]{"男","女"});
		data_validation = new HSSFDataValidation(regions,constraint);
		sheet.addValidationData(data_validation);
		cell = row2.createCell(83);
		cell.setCellValue("出生年份");
		cell = row2.createCell(84);
		cell.setCellValue("学习阶段");
		cell = row2.createCell(85);
		cell.setCellValue("学校");
		cell = row2.createCell(86);
		cell.setCellValue("入学时间");
		regions = new CellRangeAddressList(2, 999999,86,86);//设置范围   
        constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
        sheet.addValidationData(dataValidate); 
		cell = row2.createCell(87);
		cell.setCellValue("预计毕业时间");
		regions = new CellRangeAddressList(2, 999999,87,87);//设置范围   
        constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
        dataValidate = new HSSFDataValidation(regions, constraint);   
        dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
        sheet.addValidationData(dataValidate); 
		
		//HSSFWorkbook excelPrint = new Excel().excelPrint();
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();  
		//excelPrint.write(output);  
		workbook.write(output);
        byte[] ba = output.toByteArray();  
        excelFile = new ByteArrayInputStream(ba);
        output.flush();  
        output.close();  
		return excelFile;
	}
	public void setExcelFile(ByteArrayInputStream excelFile) {
		this.excelFile = excelFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private File upload;// 用来封装上传文件的封装类
	private String uploadContentType;// 上传文件的类型 [add] [2013-8-12]
	private String uploadFileName;// 上传文件的名称 [add] [2013-8-12]
	private String EXCEL_HISTORY = null;// 图片目录 [add] [2013-8-12]
	@Override
	public String input() {
		return "input";
	}
	
public String excelUp() {
	List<String[]> exceldata = readexcel(upload,uploadFileName);
	//String[] header = exceldata.get(0);
	ArrayList<TblJmxx> jmxxes = new ArrayList<TblJmxx>();
	ArrayList<TblXsxx> xsxxes = new ArrayList<TblXsxx>();
	ArrayList<TblJtxx> jtxxes = new ArrayList<TblJtxx>();
	ArrayList<TblCbxx> cbxxes = new ArrayList<TblCbxx>();
	for (int row_i = 2; row_i < exceldata.size(); row_i++) {
		String[] row = exceldata.get(row_i);
		TblJmxx jmxx = new TblJmxx();
		if(row[0]==null){
			setAttribute("message","身份证号不能为空");
			return "input";
		}else{
			List<TblJmxx> x=jmxxser.getByIdcart(row[0].trim());
			if(x.size()>0){
				setAttribute("message","身份证号为："+row[0]+" 人员已存在");
				return "input";
			}
		}
		if(row[1]==null){
			setAttribute("message","居民姓名不能为空");
			return "input";
		}
		jmxx.setIdNumber(row[0]);
		jmxx.setName(row[1]);
		jmxx.setRegister(row[2]);
		jmxx.setGender(row[3]);
		jmxx.setAlias(row[4]);
		jmxx.setWangge(row[5]);
		if(row[6]!=null&&!"".equals(row[6].trim())){
			jmxx.setIsotherwg(1);
			jmxx.setOtherwg(row[6]);
		}
		jmxx.setCellphone(row[7]);
		jmxx.setPhone(row[8]);
		jmxx.setBirthday(formatData2(row[9]));
		jmxx.setNation(row[10]);
		jmxx.setPolitics(row[11]);
		jmxx.setEducational(row[12]);
		jmxx.setEducationYears(row[13]);
		jmxx.setProfession(row[14]);
		jmxx.setUnit(row[15]);
		jmxx.setMarital(row[16]);
		jmxx.setBedied(row[17]);
		jmxx.setHeight(formatDouble(row[18]));
		jmxx.setBloodType(row[19]);
		jmxx.setFaith(row[20]);
		jmxx.setEmail(row[21]);
		jmxx.setBirthplace(row[22]);
		jmxx.setContact(row[23]);
		jmxx.setDomicileDetailedAddress(row[24]);
		jmxx.setHouseholdType(row[25]);
		jmxx.setSubordinateCompany(row[26]);
		jmxx.setResidentialStatus(row[27]);
		jmxx.setCitizenSatisfaction(formatDouble(row[28]));
		jmxx.setPresenceHousing(row[29]);
		jmxx.setNohouse(row[30]);
		jmxx.setCommunity(row[31]);
		jmxx.setRoad(row[32]);
		jmxx.setRidgepole(row[33]);
		jmxx.setElement(row[34]);
		jmxx.setRoomnumber(row[35]);
		jmxx.setRemark(row[36]);
		jmxx.setFamilyno(row[37]);
		jmxx.setParentsRelationship(row[38]);
		jmxx.setParentsidno(row[39]);
		jmxx.setParentsname(row[40]);
		jmxx.setParentscellphone(row[41]);
		jmxx.setParentsphone(row[42]);
		jmxx.setAccountNumber(row[43]);
		jmxx.setAccountRelation(row[44]);
		jmxx.setAccountIdnumber(row[45]);
		jmxx.setAccountName(row[46]);
		jmxx.setAccountCellphone(row[47]);
		jmxx.setAccountPhone(row[48]);
		jmxx.setAccountFlphone(row[49]);
		jmxx.setHushaiState(row[50]);
		jmxx.setYnout(row[51]);
		jmxx.setAccountType(row[52]);
		jmxx.setOutReason(row[53]);
		jmxx.setOutTime(formatData2(row[54])); 
		jmxx.setOutto(row[55]);
		jmxx.setFamilyTitle(row[56]);
		jmxx.setCreatetime(new Date());
		jmxxes.add(jmxx);
		//
		String account = row[43];//model.getAccountNumber();
		if(account!=null&&!"".equals(account.trim())){
			//String relation = model.getAccountRelation();
			if("户主".equals(row[44])){
				List<TblHjgl> hj = hjglser.findByHql("from TblHjgl where hkbh='"+account+"'");
				if(hj.size()<=0){
					String road = jmxx.getRoad();
					String ridgepole = jmxx.getRidgepole();
					String element = jmxx.getElement();
					String roomnumber = jmxx.getRoomnumber();
					TblHjgl hjgl = new TblHjgl();
					hjgl.setDh(jmxx.getAccountPhone());
					hjgl.setSfz(jmxx.getIdNumber());
					hjgl.setHkbh(account);
					hjgl.setHzname(jmxx.getName());
					hjgl.setSj(jmxx.getAccountCellphone());
					List<TblSyfwgl> syfw = syfwglser.findByHql("from TblSyfwgl where xqdz='"+road+"' " +
							"and zhuang='"+ridgepole+"' and dy='"+element+"' and shi='"+roomnumber+"'");
					if(syfw!=null&&syfw.size()>0){
						hjgl.setSyfwid(syfw.get(0).getId());
					}
					hjglser.save(hjgl);
				}
			}
		}
		
		
		
		if(row[57]!=null||row[58]!=null||row[59]!=null||row[60]!=null||row[61]!=null||row[62]!=null||row[63]!=null||row[64]!=null||row[65]!=null||row[66]!=null||row[56]!=null){
			TblJtxx jt=new TblJtxx();
			jmxx.setHuzhu("1");
			jt.setSfz(row[0]);
			jt.setHkbh(row[57]);
			jt.setHzbh(row[58]);
			jt.setJtzrs(row[59]);
			jt.setNydl(row[60]);
			jt.setZfmj(row[61]);
			jt.setTnmj(row[62]);
			jt.setJtsr(row[63]);
			jt.setJtkzpsr(row[64]);
			jt.setJtzzc(row[65]);
			jt.setSwxfzc(row[66]);
			jt.setJyzc(row[67]);
			jt.setYlzc(row[68]);
			jt.setWhylzc(row[69]);
			jt.setQtzc(row[70]);
			jtxxes.add(jt);
		}
		if(row[71]!=null||row[72]!=null||row[73]!=null||row[74]!=null||row[75]!=null||row[76]!=null||row[77]!=null||row[70]!=null){
			TblCbxx cb = new TblCbxx();
			jmxx.setCanbao("1");
			cb.setSfz(row[0]);
			cb.setCjylbx(parseBoolean(row[71]));
			cb.setYlbxyjf(row[72]);
			cb.setYlbxcbsj(formatData2(row[73]));
			cb.setCjylbx1(parseBoolean(row[74]));
			cb.setYlbxyjf1(row[75]);
			cb.setYlbxcbsj1(formatData2(row[76]));
			cb.setCjsybx(parseBoolean(row[77])); 
			cb.setSybxyjf(row[78]);
			cb.setSybxcbsj(formatData2(row[79]));
			cb.setCjgsbx(parseBoolean(row[80]));
			cb.setCjsybx1(parseBoolean(row[81]));
			cbxxes.add(cb);
		}
		if(row[82]!=null||row[83]!=null||row[84]!=null||row[85]!=null||row[86]!=null||row[87]!=null){
			TblXsxx xs=new TblXsxx();
			jmxx.setXuesheng("1");
			xs.setSfz(row[0]);
			xs.setXb(row[82]);
			xs.setCsnf(row[83]);
			xs.setXxjd(row[84]);
			xs.setXx(row[85]);
			xs.setRxsj(formatData2(row[86]));
			xs.setYjbysj(formatData2(row[87]));
			xsxxes.add(xs);
		}
	}
	try{
	jmxxser.saveJmxxList(jmxxes);
	jmxxser.saveCbxxList(cbxxes);
	jmxxser.saveJtxxList(jtxxes);
	jmxxser.saveXsxxList(xsxxes);
	}catch(Exception e){
		e.printStackTrace();
		setAttribute("message","导入失败,可能因为身份证或户主身份证号输入有误");
		return "input";
	}
	//操作日志添加
	TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
	logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", "人员管理 >> 人员信息管理 ", "导入", "导入了("+jmxxes.size()+")条人员数据", null);
	setAttribute("message","导入成功");
	return "input";
}
private Boolean parseBoolean(String b) {
	// TODO Auto-generated method stub
	if(b!=null){
		b = b.trim();
		if("是".equals(b)||"有".equals(b)){
			return true;
		}else{
			return false;
		}
	}
	return null;
}

private Double formatDouble(String d) {
	try{
		return Double.parseDouble(d.trim());
	}catch(Exception e){
		
	}
	return null;
}

public static List<String[]> readexcel(File upload,String filename){
		
		List<String[]> exceldata = new ArrayList<String[]>();
		//获取工作薄
		Workbook wb=null;
		//获取工作表
		Sheet 	 sheet=null; 
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(upload));
			
			if(filename.endsWith(".xls"))
				wb=new HSSFWorkbook(input); //生成excel2003以前的版本  
			else if(filename.endsWith(".xlsx")){}
				//wb=new XSSFWorkbook(input); //生成excel2007版本的
			//获取第一个工作表
			sheet=wb.getSheetAt(0);
			//循环每一行
			for(Iterator it=sheet.iterator();it.hasNext();){
				Row row=(Row)it.next();//获得每一个行
				//int col_num = row.getLastCellNum();
				//if(col_num<0){
				//	col_num = 0;
				//}
				String[] values = new String[100];
				for(int col=row.getFirstCellNum();col<row.getLastCellNum();col++){
					if(col>99){
						break;
					}
					String value = "";
					Cell cell= row.getCell(col);
					if(cell!=null){
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC:
								if(DateUtil.isCellDateFormatted(cell)){
									Date date = cell.getDateCellValue();
									if (date != null) {
										value = new SimpleDateFormat("yyyy-MM-dd").format(date);
									} else {
										value = "";
									}
								}else{
									value = cell.getNumericCellValue()+"";
								}
								
								break;
							default:
								break;
						}
						values[col] = value;
					}
				 }
				exceldata.add(values);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return exceldata;
	}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public File getUpload() {
	return upload;
}

public void setUpload(File upload) {
	this.upload = upload;
}

public String getUploadContentType() {
	return uploadContentType;
}

public void setUploadContentType(String uploadContentType) {
	this.uploadContentType = uploadContentType;
}

public String getUploadFileName() {
	return uploadFileName;
}

public void setUploadFileName(String uploadFileName) {
	this.uploadFileName = uploadFileName;
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
private Date formatData2(String dt) {
	String[] st=new String[]{"yyyy-MM-dd","yyyy/MM/dd","dd/MM/yyyy","yyyy年MM月dd日","yyyyMMdd","MM/dd/yyyy","MM-dd-yyyy","dd-MM-yyyy"};
	try {
		for(int i=0;i<st.length;i++){
			try{
				SimpleDateFormat format2 = new SimpleDateFormat(st[i]);
				Date date1 = format2.parse(dt);
				return date1;
			}catch(Exception e1){
				
			}
		}
		
	} catch (Exception e) {
	}
	return null;
}
public String getZzList(){
	return "pop";
}
public void getTree(){
	//List<TblGrid> gridlist = gridser.getAllDate();
	String hzname = getParameter("hzname");
	TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
	String gridname = user.getGridname();
	//List<TblHjgl> hulist=hjglser.getByName(hzname);
	Map<String,String> param=new HashMap<String,String>();
	param.put("grid", gridname);
	param.put("hzname",hzname );
	List<TblHjgl> hulist=hjglser.getHjTree(param);
	/*if(hzname!=null&&!"".equals(hzname)){
		hulist=hjglser.getByName(hzname);
	}else{
		hulist = hjglser.getAllDate();
	}*/
	
	List<ZTree> treelist = new ArrayList<ZTree>();
	for (TblHjgl ower: hulist) {
		if(ower.getHkbh()==null){
			continue;
		}
		ZTree ztree = new ZTree();
		ztree.setId(ower.getId()+"");
		ztree.setpId("0");
		ztree.setName(ower.getHkbh()+"  "+ower.getHzname());
		ztree.setParent(false);
		treelist.add(ztree);
	}
	Gson gson = new Gson();
	if(treelist.size()==0){
		ZTree ztree = new ZTree();
		ztree.setId("8");
		ztree.setpId("0");
		ztree.setName("没有查询到‘"+hzname+"’的数据");
		ztree.setParent(false);
		treelist.add(ztree);
	}
	super.setAjaxData(gson.toJson(treelist));
}
	public void getHjxx(){
		String hjid = getParameter("hjid");
		TblHjgl hjgl = hjglser.getOneById(Integer.parseInt(hjid));
		Integer fwid = hjgl.getSyfwid();
		TblSyfwgl gl = syfwglser.getOneById(fwid);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(hjgl);
		list.add(gl);
		setAjaxData( new Gson().toJson(list));
		
	}
	public String getLd(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		String grid=user.getGridname();
		List<String> lst=zzser.getAllOwners(grid);
		setAttribute("grides", lst);
		return "ldpop";
	}
	public String checkhj(){
		String hkbh = getParameter("hkbh");
		List<TblHjgl> list = hjglser.findByHql("from TblHjgl where hkbh='"+hkbh.trim()+"'");
		if(list!=null&&list.size()>0){
			TblHjgl hjgl = list.get(0);
			Integer fwid = hjgl.getSyfwid();
			TblSyfwgl gl = syfwglser.getOneById(fwid);
			ArrayList<Object> list2 = new ArrayList<Object>();
			list2.add(hjgl);
			list2.add(gl);
			setAjaxData( new Gson().toJson(list2));
			return null;
		}else{
			setAjaxData("0");
		}
		return null;
	}
	public String toaddHjxx(){
		String hkbh = getParameter("hkbh");
		setAttribute("hkbh", hkbh);
		return "addhj";
	}
	public String hjadd(){
		TblHjgl hjgl = new TblHjgl();
		hjgl.setHkbh(getParameter("hkbh"));
		System.out.println(getParameter("syfwid"));
		//hjgl.setSyfwid(Integer.parseInt(getParameter("syfwid")););
		hjgl.setSfz(getParameter("sfz"));
		hjgl.setSj(getParameter("sj"));
		hjgl.setDh(getParameter("dh"));
		hjgl.setHzname(getParameter("hzname"));
		hjglser.save(hjgl);
		
		Integer fwid = hjgl.getSyfwid();
		TblSyfwgl gl = syfwglser.getOneById(fwid);
		ArrayList<Object> list2 = new ArrayList<Object>();
		list2.add(hjgl);
		list2.add(gl);
		setAjaxData( new Gson().toJson(list2));
		return null;
	}
	public String getXqdz(){
		String ints = getParameter("ints");
		String road = getParameter("road");
		String ridgepole = getParameter("ridgepole");
		String element = getParameter("element");
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		String grid=user.getGridname();
		List data=jmxxser.getXqdz(ints,road,ridgepole,element,grid);
		setAttribute("data", data);
		setAttribute("ints", ints);
		return "xqpop";
	}
	public String checkSyfw(){
		String road=getParameter("road");
		String ridgepole=getParameter("ridgepole");
		String element=getParameter("element");
		String roomnumber=getParameter("roomnumber");
		//String ar=getParameter("ar");
		String an=getParameter("an");
		String result="ok";
		List<TblHjgl> list = hjglser.findByHql("from TblHjgl where hkbh='"+an+"'");
		if(list.size()<=0){
			List ls = syfwglser.findByHql("from TblSyfwgl where xqdz='"+road+"' and zhuang='"+ridgepole+"' and " +
					" dy='"+element+"' and shi='"+roomnumber+"'");
			if(ls.size()>0){
				result="ok";
			}else{
				result="no";
			}
		}else{
			result="ok";
		}
		setAjaxData(result);
		return null;
	}
	
	
	
	public String getSyfwTree(){
		return "pop2";
	}
	public void getFwtree(){
		String level = getParameter("level");
		String owner = getParameter("code");
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		String grid=user.getGridname();
		List<ZTree> treelist = new ArrayList<ZTree>();
		String dw="";
		 if("2".equals(level)){
			dw="(室)";
			String[] split = owner.split(",");
			split[0]=grid;
			List<TblSyfwgl> ls=zzrxxser.getZtreeDate(split);
			for (TblSyfwgl ower: ls) {
				ZTree ztree = new ZTree();
				ztree.setId(ower.getId()+"");
				ztree.setpId(owner);
				ztree.setName(ower.getShi()+dw);//(ower.getZhuang()+"(幢)"+ower.getDy()+"(单元)"+ower.getShi()+dw);
				ztree.setParent(false);
				treelist.add(ztree);
			}
		}else{
			if("0".equals(level)){
				dw="(幢)";
			}else if("1".equals(level)){
				dw="(单元)";
			}
			String[] split = owner.split(",");
			split[0]=grid;
			List<String> lst=zzrxxser.getZtreeDate(split);
			for (String ower: lst) {
				if(ower==null){
					continue;
				}
				if("".equals(ower.trim())){
					continue;
				}
				ZTree ztree = new ZTree();
				ztree.setId(owner+","+ower);
				ztree.setpId(owner);
				ztree.setName(ower.trim()+dw);
				ztree.setParent(true);
				treelist.add(ztree);
			}
		}
		Gson gson = new Gson();
		super.setAjaxData(gson.toJson(treelist));
	}

	public TblZzrxx getZzrxx() {
		return zzrxx;
	}

	public void setZzrxx(TblZzrxx zzrxx) {
		this.zzrxx = zzrxx;
	}
	public void setSessionPage(){
		String curPage = getParameter("curPage");
		if(curPage!=null){
			ServletActionContext.getRequest().getSession().putValue("curPage", Integer.parseInt(curPage));
		}
		
	}
	//手机页面保存 第一步 基本信息
	public String jmxx_goOneStemp(){
		//jmxx.
		
		
		return null;
	}
}
