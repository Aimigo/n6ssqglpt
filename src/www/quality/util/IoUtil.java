package www.quality.util;



import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;



/**
 * IO--closing, getting file name ... main function method
 */
public class IoUtil {
	//static final Pattern RANGE_PATTERN = Pattern.compile("bytes \\d+-\\d+/\\d+");
	static String path=ServletActionContext.getServletContext().getRealPath("/");
	/**
	 * According the key, generate a file (if not exist, then create
	 * a new file).
	 * @param filename
	 * @param fullPath the file relative path(something like `a../bxx/wenjian.txt`)
	 * @return
	 * @throws IOException
	 */
	public static File getFile(String filename) throws IOException {
		if (filename == null || filename.isEmpty())
			return null;
		String name = filename.replaceAll("/", Matcher.quoteReplacement(File.separator));
		File f =null;
		f = new File(path+UploadConfigurations.getTempRepository() + File.separator + name);

		if (!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		if (!f.exists())
			f.createNewFile();

		return f;
	}

	/**
	 * Acquired the file.
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public static File getTokenedFile(String key) throws IOException {
		if (key == null || key.isEmpty())
			return null;

		File f =null;
		f = new File(path+UploadConfigurations.getTempRepository() + File.separator + key);

		if (!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		if (!f.exists())
			f.createNewFile();

		return f;
	}

	public static void storeToken(String key) throws IOException {
		if (key == null || key.isEmpty())
			return;
		File f =null;
		f = new File(path+UploadConfigurations.getTempRepository() + File.separator + key);
		if (!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		if (!f.exists())
			f.createNewFile();
	}

	/**
	 * close the IO stream.
	 * @param stream
	 */
	public static void close(Closeable stream) {
		try {
			if (stream != null)
				stream.close();
		} catch (IOException e) {
		}
	}

}
