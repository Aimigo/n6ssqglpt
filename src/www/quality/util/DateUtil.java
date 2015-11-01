package www.quality.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * @ms 判断当前日期之前多少天的日期
	 * @author 韩庆民
	 * @param 多少天
	 * @return 多少天之前的日期
	 */
	public static Date previous(int days) {
		return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
	}
	/** 
	 * @ms 获取当前日期n天后的日期 
	 * @author 韩庆民
	 * @param n:返回当天后的第N天 
	 * @return 返回的日期 
	 */ 
	public static Date getAfterDate(int n) { 
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.DAY_OF_MONTH, n); 
		return c.getTime(); 
	}
	/**
	 * @ms 根据传入日期格式化日期格式为"yyyy-MM-dd HH:mm:ss"
	 * @author 韩庆民
	 * @param 日期
	 * @return 格式化的日期字符串
	 */
	public static String formatDateTime(Date d) {
		return new SimpleDateFormat(DATETIME_FORMAT).format(d);
	}

	/**
	 * @ms 传入长整形日期格式化日期格式为"yyyy-MM-dd HH:mm:ss"
	 * @author 韩庆民
	 * @param 长整形日期
	 * @return 格式化的日期字符串
	 */
	public static String formatDateTime(long d) {
		return new SimpleDateFormat(DATETIME_FORMAT).format(d);
	}

	/**
	 * @ms 根据传入日期格式化日期格式为"yyyy-MM-dd"
	 * @author 韩庆民
	 * @param 日期
	 * @return 格式化的日期字符串
	 */
	public static String formatDate(Date d) {
		return new SimpleDateFormat(DATE_FORMAT).format(d);
	}
	/**
	 * @ms 根据传入日期和格式字符串格式成要格式的字符日期
	 * @author 韩庆民
	 * @param 日期
	 * @param 格式字符串
	 * @return 字符日期
	 */
	public static String formatDate(Date d,String format) {
		return new SimpleDateFormat(format).format(d);
	}
	/**
	 * @ms 根据传入日期字符和格式字符串格式成要格式的日期
	 * @author 韩庆民
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * @ms 根据传入格式字符串日期"yyyy-MM-dd"转化成日期
	 * @author 韩庆民
	 * @param 格式的日期字符串
	 * @return 字符日期
	 */
	public static Date parseDate(String d) {
		try {
			return new SimpleDateFormat(DATE_FORMAT).parse(d);
		}
		catch(Exception e) {}
		return null;
	}

	/**
	 * @ms 根据传入格式字符串日期"yyyy-MM-dd HH:mm:ss"转化成日期
	 * @author 韩庆民
	 * @param 格式的日期字符串
	 * @return 字符日期
	 */
	public static Date parseDateTime(String dt) {
		try {
			return new SimpleDateFormat(DATETIME_FORMAT).parse(dt);
		}
		catch(Exception e) {}
		return null;
	}

	/**
	 * @ms 获得指定日期“yyyy-MM-dd”的前一天
	 * @author 韩庆民
	 * @param 格式日期字符串
	 * @return 前一天的日期“yyyy-MM-dd”
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(
				c.getTime());
		return dayBefore;
	}

	/**
	 * @ms 获得指定日期的后一天
	 * @author 韩庆民
	 * @param 指定的格式日期字符串
	 * @return 后一天的日期“yyyy-MM-dd”
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}
	/**
	 * @ms 根据年和周计算这一周的日期从周一~周日
	 * @author 韩庆民
	 * @param  year 年 week 第几周
	 * @return String 日期数组
	 */
	public static String [] weekdays(int year,int week){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String weeks[]=new String [7];
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);


		c.set(Calendar.DAY_OF_WEEK,2);

		weeks[0]=df.format(c.getTime());

		c.set(Calendar.DAY_OF_WEEK,3);

		weeks[1]=df.format(c.getTime());


		c.set(Calendar.DAY_OF_WEEK,4);
		weeks[2]=df.format(c.getTime());


		c.set(Calendar.DAY_OF_WEEK,5);
		weeks[3]=df.format(c.getTime());


		c.set(Calendar.DAY_OF_WEEK,6);
		weeks[4]=df.format(c.getTime());


		c.set(Calendar.DAY_OF_WEEK,7);
		weeks[5]=df.format(c.getTime());

		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week+1);
		c.set(Calendar.DAY_OF_WEEK,1);

		weeks[6]=df.format(c.getTime());
		return weeks;
	}
	/**
	 * @ms：根据年计算这一年有多少周
	 * @author 韩庆民
	 * @param year
	 * @return 周数
	 */
	public static int getWeeks(int year){   
		int week = 0;         
		int days = 365;         
		int day = 0;           
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))     
		{//判断是否闰年，闰年366天               
			days = 366;           
		}           

		//得到一年所有天数然后除以7    
		day = days % 7;
		//得到余下几天       
		week = days / 7;
		if(day>=0){
			week=week+1;
		}
		//得到多少周         
		return week;     
	}
	/**
	 * @ms：根据日期等到该日期是这一年的第几周
	 * @author 韩庆民
	 * @param 日期
	 * @return 周数
	 */
	public static int getWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);

	}


	/**
	 * @ms 根据日期计算所在周的周一和周日
	 * @author 韩庆民
	 * @param time 指定的日期
	 * @return 当前日期的周一和周日日期
	 */
	public static String[] convertWeekByDate(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		String startEnd[] = { imptimeBegin, imptimeEnd };
		return startEnd;
	}
	/**
	 *@ms 根据日期计算日期所在月的第一天和最后一天
	 *@author 韩庆民
	 *@param 指定的日期
	 *@return 当前日期的月初和月末日期
	 */
	public static String [] getMonthdayByDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		// 不加下面2行，就是取当前时间前一个月的第一天及最后一天
		cal.set(Calendar.YEAR,Integer.parseInt(time.substring(0,4)));
		cal.set(Calendar.MONTH, Integer.parseInt(time.substring(5,7)));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();

		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();

		return new String []{sdf.format(firstDate),sdf.format(lastDate)};
	}

	/** 
	 * @ms 判断是闰年还是平年。
	 * @author 韩庆民
	 * @param 年份
	 * @return 布尔值
	 */
	public static boolean pdrn(int i){
		if((i%4==0&&i%100!=0)||i%400==0)
			return true;
		else
			return false;
	}
	//测试main方法
	public static void main(String[] args){
		System.out.println("当前时间为:"+formatDateTime(new java.util.Date()));
		System.out.println("当前日期："+formatDate(new java.util.Date(),"yyyyMMdd"));
		for(String s:weekdays(2012,48)){
			System.out.println(s);
		}
	}
}
