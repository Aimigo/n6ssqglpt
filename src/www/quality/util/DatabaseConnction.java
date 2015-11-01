package www.quality.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

public class DatabaseConnction {
	private final static String NAME = "jdbc.properties";
	Connection dbConn;

	// String driverName="net.sourceforge.jtds.jdbc.Driver";
	// String dbURL="jdbc:jtds:sqlserver://localhost:1433/ncpsyglpt";
	// String userName = "sa"; //默认用户名
	// String userPwd = "123456"; //安装sql server 2005时的密码
	
	/**
	 * 读取配置文件
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String path = ServletActionContext.getServletContext().getRealPath("");
		path += "\\WEB-INF\\config\\" + NAME;
		Properties p = new Properties();
		InputStream in = null;
		String value = "";
		try {
			in = new FileInputStream(path);
			p.load(in);
			in.close();
			value = p.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 获得数据库联接
	 * 
	 * @param
	 * @return
	 */
	public Connection getCon() {
		try {
			Class.forName(getValue("driverClassName"));
			dbConn = DriverManager.getConnection(getValue("url"),
					getValue("user"), getValue("password"));
			System.out.println("Connection Successful!");
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("connection error");
		}
		return null;
	}

	/**
	 * 测试
	 * 
	 * @param
	 * @return
	 */
	public static void main(String[] args) throws SQLException {
		DatabaseConnction dbcon = new DatabaseConnction();
		Connection con = dbcon.getCon();

		String sql = "select s4, count(s5), count(s6) from Tbldata32 where 1=1 group by s4";
		PreparedStatement stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Object object = rs.getObject(1);
			Object object1 = rs.getObject(2);
			Object object2 = rs.getObject(3);
			// Array array = rs.getArray(1);
			System.out.println(object + "--" + object1 + "--" + object2);
		}
		System.out.println(rs);
	}
}
