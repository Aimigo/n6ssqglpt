package www.quality.util;

import org.springframework.util.DigestUtils;

/**
 * 类的描述:一些基本工具类 作者:胡域 创建日期：2012-03-19
 * 
 * 修改人 修改日期 修改原因描述
 */
public class StringUtil {

	/**
	 * 通过给定的字符串数组创建出SQL语句中的IN子句字符集合<br/>
	 * 例如：给定字符数组：strs为001，002，003，004，则将其拼装成IN子句的字符集合为（'001'，'002'，'003'，'004'）
	 * 
	 * @param strs
	 * @return
	 */
	public static String buildInClauseString(String[] strs) {
		StringBuffer sb = new StringBuffer("(");
		if (strs == null || strs.length < 1) {
			return null;
		}
		for (String str : strs) {
			sb.append("'" + str + "',");
		}
		return sb.substring(0, sb.length() - 1) + ")";

	}

	/**
	 * 通过给定的字符串数组创建一个用逗号分隔的字符串<br/>
	 * 例如：给定字符数组：strs为a，b，c，d，则返回a,b,c,d<br/>
	 * 空字符串意味着返回''。
	 * 
	 * @param strs
	 * @return
	 */
	public static String buildDotted(String[] strs) {
		StringBuffer sb = new StringBuffer("");
		if (strs == null || strs.length < 1) {
			return "";
		}
		for (String str : strs) {
			sb.append(str + ",");
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 产生随机数<br/>
	 * <br/>
	 * 
	 * @param size
	 *            随机数的位数
	 * @return
	 */
	public static String random(int size) {
		String tm = "";
		for (int i = 0; i < size; i++) {
			tm += String.valueOf(new Double(Math.random() * 10).intValue());
		}
		return tm;
	}

	public static void main(String[] args) {
		String str = DigestUtils.md5DigestAsHex("124.225.111.110A1".getBytes());

		System.out.println(str.toUpperCase());
	}
}