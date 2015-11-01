package www.quality.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;



public class DBHelper {
	private static String sqlDriver = "";
	private static String username = "";
	private static String password = "";
	private static String url = "";
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	/**
	 * 初始化连接
	 * 
	 */
	public static Connection initConn(){
		try {
			Properties pro = getProperties("jdbc.properties");
			sqlDriver = pro.getProperty("driverClassName");
			username = pro.getProperty("useruser");
			password = pro.getProperty("password");
			url = pro.getProperty("url");
			//url = oldUrl.replace("{0}", ip);
		} catch (Exception e) {
			System.out.println("配置文件出错！");
		}
		try {
			Class.forName(sqlDriver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("未能连接到"+url+"的数据库，连接失败");
		}
		return conn;
	}
	/**
	 * 关闭所有连接
	 */
	public void closeAll(){
		try {
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Properties getProperties2(String name){
		Properties pro = new Properties();
		try {
			URL fileurl = DBHelper.class.getClassLoader().getResource(name);
			InputStream in = new BufferedInputStream(new FileInputStream(fileurl.getPath()));
			pro.load(in);
		} catch (Exception e) {
			pro = null;
			e.printStackTrace();
		}
		return pro;
	}
	public static Properties getProperties(String name){
		Properties pro = new Properties();
		try {
			URL fileurl = DBHelper.class.getClassLoader().getResource("./");
			String path = fileurl.toString();
			path = path.substring(6,path.length()-8)+"jdbc.properties";
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			pro.load(in);
		} catch (Exception e) {
			pro = null;
			e.printStackTrace();
		}
		return pro;
	}
	public static void main(String[] args) {
		Connection conn2 = DBHelper.initConn();
		System.out.println(conn2);
	}
}
