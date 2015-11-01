package www.quality.util;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Classlib {

	/**
	 * @return String
	 * 获取工程当前目录
	 * 当前工程为：pdwyfw；当前目录为：E:\workspace\pdwyfw
	 */
	public  String getProjectRoot(){
		String projectRoot = null;		
		File f = new File(this.getClass().getResource("/").getPath());
		projectRoot = f.getParent();
		projectRoot = projectRoot.substring(0, projectRoot.indexOf("WEB-INF"));
		return projectRoot;
	}

	/**
	 * @return String
	 * 获取WEB-INF当前目录
	 * WEB-INF当前目录为：E:\workspace\pdwyfw\WebRoot\WEB-INF
	 */
	public String getProjectRootTwo(){
		String projectRoot = null;
		File f = new File(this.getClass().getResource("/").getPath());
		projectRoot = f.getParent();
		return projectRoot;
	}	

	/**
	 * @return URL
	 * 获取classes当前目录(注意类型)
	 * classes当前目录为：file:/E:/workspace/pdwyfw/WebRoot/WEB-INF/classes/
	 */
	public URL getProjectRootThree(){
		URL projectRoot = null;
		projectRoot = this.getClass().getClassLoader().getResource(""); 
		return projectRoot;
	}

	/**
	 * @return URL
	 * 获取当前目录(注意类型)
	 * 当前目录为：file:/E:/workspace/pdwyfw/WebRoot/WEB-INF/classes/com/pdwy/tools/
	 */
	public URL getProjectRootFour(){
		URL projectRoot = null;
		projectRoot = this.getClass().getResource("");		
		return projectRoot;
	}

	/**
	 * @return String
	 * 获取当前时间格式yyyy-MM-dd
	 * 当前日期为：2012-12-11
	 */
	public String getCurrentTime() {
		String CurrentTime = new String();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CurrentTime = formatter.format(new Date());
		return CurrentTime;
	}
	
	/**
	 * @return String
	 * 获取当前时间格式yyyy-MM-dd hh:mm:ss
	 * 当前日期为：2012-12-11 09:49:09
	 */
	public String getCurrentTime2() {
		String CurrentTime = new String();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		CurrentTime = formatter.format(new Date());
		return CurrentTime;
	}

	/**
	 * @return String
	 * 将时间格式转换为"yyyy-MM-dd"，如果增加时分秒则格式为：yyyy-MM-dd hh:mm:ss
	 * 当前日期为：2012-12-11
	 */

   public String getDateString(String dateString) {
    	String dd="";
    	SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
    	if(dateString==null)
    	{
    		return dd;
    	}
    	else
    	{
    		Date date;
			try {
				date = formatDate.parse(dateString);
				SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
				dd=String.valueOf(time.format(date));
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    		return dd;
    	}
     }

   /**
	 * @return String
	 * 将时间格式转换为：yyyy-MM-dd hh:mm:ss
	 * 当前日期为：2012-12-11
	 */
   public String getDateString2(String dateString){
   	String dd="";
   	SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   	if(dateString==null)
   	{
   		return dd;
   	}
   	else
   	{
   		Date date;
			try {
				//System.out.println(dateString);
				date = formatDate.parse(dateString);
				SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dd=String.valueOf(time.format(date));
				//System.out.println(dd);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
   		
   		 return dd;
   	}
   }

   /**
	 * @return double
	 * @param d
	 * 将浮点数数据保留2位小数点
	 * 当前数据为：10.09
     */

   public double getTwoByDouble(double d) {
		double td = 0;
		BigDecimal b = new BigDecimal(d);
		td = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return td;
	}

   /**
	 * 得到最大的数字
	 * @param number
	 * @param number
	 * @return
	 */
	public static int getMaxNumber(int[] number) {
		if (number.length <= 0)
			return 0;

		int max = number[0];
		for (int i = 1; i < number.length; i++) {
			if (max < number[i]) {
				max = number[i];
			}
		}
		return max;

	}

	/**
	 * 构造sql时对传输过来的字符串进行转义
	 * 
	 * @param str
	 *   String
	 * @return String
	 */
	public static String parseStringForSql(String str) {
		//System.out.println("dsfffffffffffffffffff"+str);
		StringBuffer sb = new StringBuffer(10);
		if (str == null) {
			return "";
		}
		str = str.trim();
		for (int j = 0; j < str.length(); j++) {
			char c = str.charAt(j);
			switch (c) {
			case 39: // "'"
				sb.append("''");
				break;
			// 以下用在like中
			case '[':
				sb.append("[[]");
				break;
			case '%':
				sb.append("[%]");
				break;
//			case '_':
//				sb.append("[_]");
//				break;
			case '^':
				sb.append("[^]");
				break;
			case '|':
				sb.append("[|]");
				break;
			case ',':
				sb.append("[,]");
				break;
			case '$':
				sb.append("[$]");
			case '<':
				sb.append("[,]");
				break;
			case '>':
				sb.append("[>]");
				break;
			default:
				sb.append(c);
				break;
			}

		}
		return sb.toString();
	}

	/**
	 * 加密解密类及方法
	 * 
	 * @param str
	 *   String
	 * @return String
	 */
	/**
	 *读取配置文件的参数
	 * properties
	 * @param str
	 *   String
	 * @return String
	 */
	/**
	 *获取本机的IP地址
	 *   String
	 * @return String
	 */
	public String getIp()
	{
		String ip="";
		try {
			ip=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}

	/**
	 *根据IP地址获取机器的MAC地址
	 * 
	 * @param host
	 *   String
	 * @return String
	 */
public String getMacAddress(String host)  
    {  
        String mac = "";  
        StringBuffer sb = new StringBuffer();  
          
        try   
        {  
            NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getByName(host));  
              
            byte[] macs = ni.getHardwareAddress();  
              
            for(int i=0; i<macs.length; i++)  
            {  
                mac = Integer.toHexString(macs[i] & 0xFF);   
                  
                 if (mac.length() == 1)   
                 {   
                     mac = '0' + mac;   
                 }   
  
                sb.append(mac + "-");  
            }  
                          
        } catch (SocketException e) {  
            e.printStackTrace();  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        }  
          
        mac = sb.toString();  
        mac = mac.substring(0, mac.length()-1);  
          
        return mac;  
    }

}
