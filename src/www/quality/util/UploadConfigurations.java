package www.quality.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

/**
 * read the configurations from file `config.properties`.
 */
public class UploadConfigurations {
	static String path=ServletActionContext.getServletContext().getRealPath("");
	static final String CONFIG_FILE =path+File.separator +"upload"+File.separator +"config"+File.separator +"upload.properties";
	private static Properties properties = null;
	private static final String TEMP_REPOSITORY ="tmp";
	private static final String MEDIA_FILE_REPOSITORY ="videos";
	private static final String MEDIA_IMG_REPOSITORY ="videos/images";
	private static final String MEDIA_TRANSCOD_REPOSITORY ="videos/transcod";
	
	private static final String IMG_FILE_REPOSITORY ="picture";
	private static final String IMG_MINI_REPOSITORY ="picture/mini";
	
	private static final String DOC_FILE_REPOSITORY ="doc";
	private static final String DOC_FILE_TEMP_REPOSITORY ="doc/temp";
	private static final String DOC_ONLINE_REPOSITORY ="doc/online";
	
	private static final String FILE_REPOSITORY ="file";
	

	static {
		new UploadConfigurations();
	}

	private UploadConfigurations() {
		init();
	}

	void init() {
		try {
			
			InputStream in = new FileInputStream(CONFIG_FILE);
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			System.err.println("reading `" + CONFIG_FILE + "` error!");
			e.printStackTrace();
		}
	}

	public static String getConfig(String key) {
		return getConfig(key, null);
	}

	public static String getConfig(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static int getConfig(String key, int defaultValue) {
		String val = getConfig(key);
		int setting = 0;
		try {
			setting = Integer.parseInt(val);
		} catch (NumberFormatException e) {
			setting = defaultValue;
		}
		return setting;
	}

	public static String getTempRepository() {
		String val = getConfig("TEMP_REPOSITORY");
		if (val == null || val.isEmpty())
			val = TEMP_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}
	
	public static String getMediaFileRepository() {
		String val = getConfig("MEDIA_FILE_REPOSITORY");
		if (val == null || val.isEmpty())
			val = MEDIA_FILE_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}
	
	public static String getMediaImgRepository() {
		String val = getConfig("MEDIA_IMG_REPOSITORY");
		if (val == null || val.isEmpty())
			val = MEDIA_IMG_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())
			myPath.mkdirs();
		return val;
	}
	
	public static String getImgFileRepository() {
		String val = getConfig("IMG_FILE_REPOSITORY");
		if (val == null || val.isEmpty())
			val = IMG_FILE_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}
	public static String getImgMiniRepository() {
		String val = getConfig("IMG_MINI_REPOSITORY");
		if (val == null || val.isEmpty())
			val = IMG_MINI_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}
	
	public static String getMediaTranscodRepository() {
		String val = getConfig("MEDIA_TRANSCOD_REPOSITORY");
		if (val == null || val.isEmpty())
			val = MEDIA_TRANSCOD_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())
			myPath.mkdirs();
		return val;
	}
	
	public static String getDocFileTempRepository() {
		String val = getConfig("DOC_FILE_TEMP_REPOSITORY");
		if (val == null || val.isEmpty())
			val = DOC_FILE_TEMP_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}
	public static String getDocFileRepository() {
		String val = getConfig("DOC_FILE_REPOSITORY");
		if (val == null || val.isEmpty())
			val = DOC_FILE_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}
	public static String getDocOnlineRepository() {
		String val = getConfig("DOC_ONLINE_REPOSITORY");
		if (val == null || val.isEmpty())
			val = DOC_ONLINE_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}
	public static String getFileRepository() {
		String val = getConfig("FILE_REPOSITORY");
		if (val == null || val.isEmpty())
			val = FILE_REPOSITORY;
		File myPath=new File(path+File.separator+val);
		if(!myPath.exists())myPath.mkdirs();
		return val;
	}

	public static String getCrossServer() {
		return getConfig("STREAM_CROSS_SERVER");
	}
	
	public static String getCrossOrigins() {
		return getConfig("STREAM_CROSS_ORIGIN");
	}
	
	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(getConfig(key));
	}
	
	public static boolean isDeleteFinished() {
		return getBoolean("STREAM_DELETE_FINISH");
	}
	
	public static boolean isCrossed() {
		return getBoolean("STREAM_IS_CROSS");
	}
}
