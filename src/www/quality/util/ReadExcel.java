package www.quality.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	/**
	 * 不区分版本读取EXCEL
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static List<Object[]> read(String filename) throws IOException{
		try{
			return readByXSSF(filename);
		}catch(Exception ex){
			try{
				return readByHSSF(filename);
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * 读取2007EXCEL
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static List<Object[]> readByXSSF(String filename) throws IOException{
		List<Object[]> list = new ArrayList<Object[]>();
		
		//指定要读取的Excel文件
		File file = new File(filename);
		//创建输入流
		FileInputStream fis = new FileInputStream(file);
		//创建XSSFWorkbook对象
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		//获得第一个sheet工作表
		XSSFSheet childSheet = wb.getSheetAt(0);
		//显示行数（如果中间隔行的话getPhysicalNumberOfRows就不能读取到所有的行）
		System.out.println(childSheet.getPhysicalNumberOfRows());
		//显示行数
		System.out.println("X行数："+childSheet.getLastRowNum());
		//遍历EXCEL数据
		for (int i = 0; i < childSheet.getPhysicalNumberOfRows(); i++) {
			//获取表格中的行
			XSSFRow row = childSheet.getRow(i);
			//显示列数（如果中间隔列的话getPhysicalNumberOfCells就不能读取到所有列）
			//System.out.println(row.getPhysicalNumberOfCells());
			//显示列数
			//System.out.println("列数："+row.getLastCellNum());
			//如果所获得的行不为空
			if(null!=row){
				//定义数组保存一行数据 根据标题的列数而定
				Object[] obj = new Object[childSheet.getRow(0).getLastCellNum()];
				//遍历每个单元格
				for (int j = 0; j < row.getLastCellNum(); j++) {
					//获取行中的单元格
					XSSFCell cell = row.getCell(j);
					
					if(null!=cell){
						switch (cell.getCellType()) {  
                        case XSSFCell.CELL_TYPE_NUMERIC: // 数字  
                            // System.out.print(cell.getNumericCellValue() + "   ");
                        	double a = cell.getNumericCellValue();
                        	int b = (int)a;
                        	if(a == b){
                        		obj[j] = b;
                        	}else{
                        		obj[j] = a;
                        	}
                            break;  
                        case XSSFCell.CELL_TYPE_STRING: // 字符串  
                            //System.out.print(cell.getStringCellValue()+ "   ");
                            obj[j] = cell.getStringCellValue();
                            break;  
                        case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                            //System.out.println(cell.getBooleanCellValue() + "   ");  
                        	obj[j] = cell.getBooleanCellValue();
                        	break;  
                        case XSSFCell.CELL_TYPE_FORMULA: // 公式  
                            //System.out.print(cell.getCellFormula() + "   ");
                            obj[j] = cell.getCellFormula();
                            break;  
                        case XSSFCell.CELL_TYPE_BLANK: // 空值  
                            //System.out.println(" ");  
                            obj[j] = "";
                        	break;  
                        case XSSFCell.CELL_TYPE_ERROR: // 故障  
                            //System.out.println(" ");
                            obj[j] = "";
                            break;  
                        default:  
                            //System.out.print("未知类型   ");  
                        	obj[j] = "";
                        	break; 
                        } 
					}else{
						//System.out.print("-   ");
						obj[j] = "";
					}
				}
				list.add(obj);
			}
			//System.out.println();
		}
		fis.close();
		
		return list;
	}
	
	/**
	 * 读取2003EXCEL
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static List<Object[]> readByHSSF(String filename) throws IOException{
		List<Object[]> list = new ArrayList<Object[]>();
		
		//指定要读取的Excel文件
		File file = new File(filename);
		//创建输入流
		FileInputStream fis = new FileInputStream(file);
		//创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		//获得第一个sheet工作表
		HSSFSheet childSheet = wb.getSheetAt(0);
		//显示行数（如果中间隔行的话getPhysicalNumberOfRows就不能读取到所有的行）
		System.out.println(childSheet.getPhysicalNumberOfRows());
		//显示行数
		System.out.println("H行数："+childSheet.getLastRowNum());
		//遍历EXCEL数据
		for (int i = 0; i < childSheet.getPhysicalNumberOfRows(); i++) {
			//获取表格中的行
			HSSFRow row = childSheet.getRow(i);
			//显示列数（如果中间隔列的话getPhysicalNumberOfCells就不能读取到所有列）
			//System.out.println(row.getPhysicalNumberOfCells());
			//显示列数
			//System.out.println("列数："+row.getLastCellNum());
			//如果所获得的行不为空
			if(null!=row){
				//定义数组保存一行数据 根据标题的列数而定
				Object[] obj = new Object[childSheet.getRow(0).getLastCellNum()];
				//遍历每个单元格
				for (int j = 0; j < obj.length; j++) {
					if(j >= row.getLastCellNum())
						break;
					//获取行中的单元格
					HSSFCell cell = row.getCell(j);
					
					if(null!=cell){
						switch (cell.getCellType()) {  
                        case XSSFCell.CELL_TYPE_NUMERIC: // 数字  
                            // System.out.print(cell.getNumericCellValue() + "   ");
                        	double a = cell.getNumericCellValue();
                        	int b = (int)a;
                        	if(a == b){
                        		obj[j] = b;
                        	}else{
                        		obj[j] = a;
                        	}
                            break;  
                        case XSSFCell.CELL_TYPE_STRING: // 字符串  
                            //System.out.print(cell.getStringCellValue()+ "   ");
                            obj[j] = cell.getStringCellValue();
                            break;  
                        case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                            //System.out.println(cell.getBooleanCellValue() + "   ");  
                        	obj[j] = cell.getBooleanCellValue();
                        	break;  
                        case XSSFCell.CELL_TYPE_FORMULA: // 公式  
                            //System.out.print(cell.getCellFormula() + "   ");
                            obj[j] = cell.getCellFormula();
                            break;  
                        case XSSFCell.CELL_TYPE_BLANK: // 空值  
                            //System.out.println(" ");  
                            obj[j] = "";
                        	break;  
                        case XSSFCell.CELL_TYPE_ERROR: // 故障  
                            //System.out.println(" ");
                            obj[j] = "";
                            break;  
                        default:  
                            //System.out.print("未知类型   ");  
                        	obj[j] = "";
                        	break; 
                        } 
					}else{
						//System.out.print("-   ");
						obj[j] = "";
					}
				}
				list.add(obj);
			}
			//System.out.println();
		}
		fis.close();
		
		return list;
	}
}
