package www.quality.action;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblJmxx;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.model.TblUser;
import www.quality.util.ReadExcel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

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
public class ImportTsryAction extends BasicAction implements ModelDriven<TblTsrysj> {
	/////////////=============导出模板=============
	ByteArrayInputStream excelFile;
	String fileName="特殊人员";
	//String message;
	String msg;
	private TblTsrysj model = new TblTsrysj();
	public TblTsrysj getModel() {
		return model;
	}
	/**
	 * 导出模板
	 * @return
	 */
	public String getTemplet(){
		String ryflid = getParameter("flid");
		TblTsryfl ryfl = tsryser.getFlById(ryflid);
		if(ryfl!=null){
			fileName=ryfl.getName();	
		}
		
		return "excel";
	}
	@SuppressWarnings("unused")
	public InputStream getExcelFile() throws Exception  {
		String ryflid = getParameter("flid");
		//
		TblTsryfl ryfl = tsryser.getFlById(ryflid);
		List<TblTsryflzd> list=jmxxser.getZdesByFlid(Integer.parseInt(ryflid));
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0,ryfl.getName());
		//fileName=ryfl.getName();
		HSSFRow row= sheet.createRow((short)0);
		HSSFCell cell;
		if(list==null&&list.size()<=0) 
			return null;
		cell = row.createCell(0);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("身份证");
		
		HSSFCellStyle style1 = workbook.createCellStyle();
        HSSFDataFormat format1= workbook.createDataFormat();
        style1.setDataFormat(format1.getFormat("@"));
        sheet.setDefaultColumnStyle(0, style1);
		
		cell = row.createCell(1);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("人员姓名");
		for(int i=0;i<list.size();i++){
			TblTsryflzd flzd = list.get(i);
			cell = row.createCell((short)(i+2));
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(flzd.getZdname());
			if("checkbox".equals(flzd.getZdlx())||"radio".equals(flzd.getZdlx())||"select".equals(flzd.getZdlx())){
				//下拉列表框或单选·复选框
				String zddata = flzd.getZddata();
				if(zddata!=null&&!"".equals(zddata)){
					CellRangeAddressList regions = new CellRangeAddressList(1,99999,i+2,i+2);
					//生成下拉框内容
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(zddata.split(","));
					//绑定下拉框和作用区域
					HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
					sheet.addValidationData(data_validation);
				}
			}else if("datatime".equals(flzd.getZdlx())){
	            CellRangeAddressList regions = new CellRangeAddressList(1, 999999,i+2,i+2);//设置范围   
	            DVConstraint constraint =DVConstraint.createDateConstraint(DVConstraint.ValidationType.DATE,"1900年1月1日",null,"yyyy年MM月dd日");
	            HSSFDataValidation dataValidate = new HSSFDataValidation(regions, constraint);   
	            dataValidate.createErrorBox("error", "只允许输入日期格式数据：如yyyy-dd-mm");   
	            sheet.addValidationData(dataValidate); 
			}else{
				HSSFCellStyle style = workbook.createCellStyle();
	            HSSFDataFormat format= workbook.createDataFormat();
	            style.setDataFormat(format.getFormat("@"));
	            sheet.setDefaultColumnStyle(i+2, style);
			}
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();  
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
	/**
	 * 上传EXCEL [add] [2013-8-12]
	 * 
	 * @return
	 */
	public String excelUp() {
		// String code = getParameter("code");
		// ServletActionContext.getRequest().setAttribute("code", code);

		if (uploadFileName == null) {
			ServletActionContext.getRequest().setAttribute("message",
					"请不要上传空文件");
			return INPUT;
		}
		// 获取上传文件后缀名
		String tname = uploadFileName
				.substring(uploadFileName.lastIndexOf("."));
		// 判断不符合EXCEL的格式
		if (!(uploadContentType.contains("excel") || uploadContentType
				.contains("spreadsheetml.sheet"))) {
			// 上传不是EXCEL文件时错误信息
			ServletActionContext.getRequest().setAttribute("message",
					"ERROR 请上传正确的EXCEL文件");
			return INPUT;
		}
		// 获取EXCEL上传目录字符串
		if (EXCEL_HISTORY == null) {
			EXCEL_HISTORY = ServletActionContext.getServletContext()
					.getRealPath("/excel/");
		}

		// 获取上传目录
		File f = new File(EXCEL_HISTORY);
		// 判断目录是否存在 false 建立
		if (!f.exists())
			f.mkdirs();

		try {
			// 根据当前毫秒数 为上传图片生成文件名
			String imgName = "" + System.currentTimeMillis();
			File newFile = null;
			if (uploadContentType.contains("excel")
					|| uploadContentType.contains("spreadsheetml.sheet"))
				newFile = new File(EXCEL_HISTORY + "/" + imgName + tname);
			FileUtils.copyFile(upload, newFile);
			// 把文件名存入action message
			ServletActionContext.getRequest().setAttribute("imgname",imgName + tname);
		} catch (IOException e) {
			ServletActionContext.getRequest().setAttribute("message", "上传文件失败");
			return ERROR;
		}
		return INPUT;
	}
	/**
	 * 选择字段
	 * 
	 * @return
	 * @throws IOException
	 */
	public void selectZd() throws IOException {
		String imgname = super.getParameter("imgname");
		imgname = ServletActionContext.getServletContext()
				.getRealPath("/excel") + "/" + imgname;
		// 利用工具类查出对象数组的List

		List<Object[]> listSj = ReadExcel.read(imgname);

		// 将listSj.get(0)转换成json类型
		String data = "";
		for (Object obj : listSj.get(0)) {
			if (null != obj && !"".equals(obj)) {
				if ("".equals(data))
					data = "[\"" + obj + "\"";
				else
					data += ",\"" + obj + "\"";
			}
		}
		data += "]";
		super.setAjaxData(data);
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

	/**
	 * 上传
	 * 
	 * @return
	 */
	public String excelRead() {
		//TblUser user = (TblUser) getParameterFromSession("user");
		int success = 0;// 记录成功条数
		String failed = "";// 记录失败的行
		// 获取导入记录UUID
		
		String imgname = super.getParameter("imgname");
		imgname = ServletActionContext.getServletContext()
				.getRealPath("/excel") + "/" + imgname;

		String flid = getParameter("flid");
		if (null == flid || "".equals(flid)) {
			setAttribute("importexcelmsg", "0");
			msg= "提示：未知特殊人员分类，请尝试刷新页面重新导入数据。";
			setAttribute("msg", msg);
			return "cglist";
		}
		setAttribute("flid", flid);
		//msg="sdk";
		//setAttribute("msg",msg);
		try {
			long begintime = System.currentTimeMillis();// 开始时间
			List<TblTsryflzd> list = tsryser.getFlzdByFlid(flid);
			//TblTsryfl fn=tsryser.getFlById(flid);
			List<Object[]> exceldata = ReadExcel.read(imgname);

			List<String[]> indx = new ArrayList<String[]>();
			for (int k = 0; k < list.size(); k++) {
				String[] flt = new String[3];
				flt[0] = list.get(k).getDatazd();
				flt[1] = super.getParameter(list.get(k).getDatazd());
				flt[2] = list.get(k).getZdlx() + "";
				indx.add(flt);
			}

			if (indx.size() <= 0) {
				msg="提示：当前分类不存在";
				setAttribute("msg", msg);
				return "cglist";
			}

			List<TblTsrysj> mydatas = new ArrayList<TblTsrysj>();
			outter: for (int row_i = 1; row_i < exceldata.size(); row_i++) {
				Object[] row = exceldata.get(row_i);
				TblTsrysj mdata = new TblTsrysj();
				String idcard = row[0].toString();
				String name = row[1].toString();
				if(idcard==null||name==null||"".equals(idcard.trim())||"".equals(name.trim())){
					msg="提示：身份证号或人员姓名不能为空。请检查数据完整性";
					setAttribute("msg", msg);
					return "cglist";
				}
				List<TblTsrysj> rysj = tsryser.getBySfz(idcard.trim());
				if(rysj!=null&&rysj.size()>0){
					msg="提示：特殊人员中身份证号为 "+idcard+" 特殊人员已存在，请检查数据。";
					setAttribute("msg", msg);
					return "cglist";
				}
				TblJmxx jmxx=jmxxser.getOneByIdCard(idcard.trim());
				if(jmxx==null){
					msg="提示：系统中不存在身份证号为 "+idcard+" 的人员信息，请检查数据是否正确。";
					setAttribute("msg", msg);
					return "cglist";
				}
				if(!name.equals(jmxx.getName())){
					msg="提示：身份证号为 "+idcard+" 的人员信息在系统对应姓名为 "+jmxx.getName()+"，请检查数据是否正确。";
					setAttribute("msg", msg);
					return "cglist";
				}
				
				mdata.setFlid(Integer.parseInt(flid));
				mdata.setSfz(row[0].toString());
				mdata.setRyname(row[1].toString());
				for (int m = 0; m < indx.size(); m++) {
					String[] flt = indx.get(m);
					String cell = "";

					// 得到值
					if (Integer.parseInt(flt[1]) != -1) {
						try {
							cell = row[Integer.parseInt(flt[1])] + "";
						} catch (Exception e) {
							failed = failed + (row_i + 1) + " ";
							continue outter;// 跳出执行下一次循环
						}
						if (isNaN(cell)) {
							//if ("radio".equals(flt[2])||"checkbox".equals(flt[2])||"select".equals(flt[2])) {
							if("int".equals(flt[2])){
								try {
									int i = Integer.parseInt(cell);
									cell = i + "";
								} catch (Exception e) {
									try {
										Float f = Float.parseFloat(cell);
										cell = f.toString();
									} catch (Exception e2) {
										failed = failed + (row_i + 1) + " ";
										continue outter;// 跳出执行下一次循环
									}
								}
							} else if ("datetime".equals(flt[2])) {
								cell=formatData(cell,"yyyy-MM-dd");
								if(cell==null){
									failed = failed + (row_i + 1)
											+ " ";
									continue outter;// 跳出执行下一次循环
								}
							}
						}
					}

					String zdname = flt[0];
					String mname = "set" + zdname.substring(0, 1).toUpperCase()
							+ zdname.substring(1);
					try {
						TblTsrysj.class.getMethod(mname, String.class).invoke(
								mdata, cell);
					} catch (Exception e) {
						// e.printStackTrace();
						failed = failed + (row_i + 1) + " ";
						break;// 跳出内层循环
					}
				}
				for(TblTsrysj sj:mydatas){
					if(sj.getSfz().equals(mdata.getSfz())){
						msg="提示：身份证号为 "+idcard+" 的人员信息在表格中重复出现 ";
						setAttribute("msg", msg);
						return "cglist";
					}
				}
				mydatas.add(mdata);
				success += 1;
			}
			tsryser.saveAllTsry(mydatas);
			TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
			logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", "人员管理 >> 特殊人员信息管理 ", "导入", "导入了("+mydatas.size()+")条数据", null);
			long endtime = System.currentTimeMillis();// 开始时间
			if(!"".equals(failed)){
				failed="出错行号"+failed;
			}
			//setAttribute("msg","成功导入"+success+"条数据，耗时"+(endtime-begintime)+"毫秒。"+failed);
			msg="成功导入"+success+"条数据，耗时"+(endtime-begintime)+"毫秒。"+failed;
			setAttribute("msg",msg);
		} catch (Exception e) {
			e.printStackTrace();
			return "cglist";
		}
		//super.setAjaxData(success + "");

		// 删除导入的Excel
		File file = new File(imgname);
		if (file.exists())
			file.delete();
		return "cglist";
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
	public Boolean isNaN(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}

	}
	/*public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}*/
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
