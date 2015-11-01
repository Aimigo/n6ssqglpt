package www.quality.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import www.quality.model.TblTsryflzd;

import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportExcel {
	/**
	 * 导出数据到excel
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook export(ResultSet rs,String xlsName,String sheetName) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0,sheetName);
		HSSFRow row= sheet.createRow((short)0);
		HSSFCell cell;
		if(rs==null) return workbook;
		ResultSetMetaData md= rs.getMetaData();
		int nColumn=md.getColumnCount();
		//写入各个字段的名称
		for(int i=1;i<=nColumn;i++){ 
			cell = row.createCell((short)(i-1));
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(md.getColumnLabel(i));
		}
		int iRow=1;
		//写入各条记录，每条记录对应Excel中的一行
		while(rs.next()){
			row= sheet.createRow((short)iRow);;
			for(int j=1;j<=nColumn;j++){ 
				cell = row.createCell((short)(j-1));
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(rs.getObject(j)==null?"":rs.getObject(j).toString());
			}
			iRow++;
		}
		//FileOutputStream fOut = new FileOutputStream(xlsName);
		//workbook.write(fOut);
		//fOut.flush();
		//fOut.close();
		//JOptionPane.showMessageDialog(null,"导出数据成功！");
		return workbook;

	}
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public static ResultSet doreport(String sql) {
		DatabaseConnction dbcon = new DatabaseConnction();
		Connection con = dbcon.getCon();
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		String sql="select s4 as '打开覅', count(s5) as '哈哈', count(s6) as '哈哈2' from Tbldata32 where 1=1 group by s4";
		ResultSet rs = doreport(sql);
		try {
			export( rs,"d://第一个表格.xls","ceshi数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 导出自定义表单模板到excel
	 * @param zdes
	 * @param xlsName
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
/*	@SuppressWarnings({ "deprecation", "null" })
	public static HSSFWorkbook exportTemplet(List<String> zdes,String xlsName,String sheetName) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0,sheetName);
		HSSFRow row= sheet.createRow((short)0);
		HSSFCell cell;
		if(zdes==null&&zdes.size()>0) return workbook;
		for(int i=0;i<zdes.size();i++){
			cell = row.createCell((short)(i));
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(zdes.get(i));
		}
		return workbook;

	}*/
	/*
	 * 读取excel表格数据
	 */
	@SuppressWarnings("rawtypes")
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
				//System.out.println("第"+row.getRowNum()+"行");
				//System.out.println("共"+row.getFirstCellNum()+"-"+row.getLastCellNum()+"列");
				int col_num = row.getLastCellNum();
				if(col_num<0){
					col_num = 0;
				}
				String[] values = new String[col_num];
				for(int col=row.getFirstCellNum();col<row.getLastCellNum();col++){
					//Iterator i=row.cellIterator();i.hasNext();(Cell)i.next()
					//System.out.println("正在读取第"+col+"列");
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
	/**
	 * 导出自定义表单模板到excel
	 * @param zdes
	 * @param xlsName
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "null" })
	public static HSSFWorkbook exportTemplet(List<TblTsryflzd> zdes,String xlsName,String sheetName) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0,sheetName);
		HSSFRow row= sheet.createRow((short)0);
		HSSFCell cell;
		if(zdes==null&&zdes.size()>0) return workbook;
		for(int i=0;i<zdes.size();i++){
			cell = row.createCell((short)(i));
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(zdes.get(i).getZdname());
		}
		return workbook;

	}
}

