package www.quality.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

/**
*类的描述:该类是查找属性参数配置文件中的value值
*作者:杜长吉
*创建日期：2012-03-19
*
*修改人
*修改日期
*修改原因描述
*/

public class Configuration {
	
	private final static String NAME="comman.properties";
	
	public static String getValue(String key) {
		String path=ServletActionContext.getServletContext().getRealPath("");
		path+="\\WEB-INF\\config\\"+NAME;
		Properties p=new Properties();
		InputStream in=null;
		String value="";
		try {
			in=new FileInputStream(path);
			p.load(in);
			in.close();
			 value=p.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
