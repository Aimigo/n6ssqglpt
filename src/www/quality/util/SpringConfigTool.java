package www.quality.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringConfigTool implements ApplicationContextAware {// extends
																	// ApplicationObjectSupport{

	// 测试
	/**
	 * 配置方法1 使用SpringConfigTool获取Bean必须在spring文件设置这一项 
	 * <bean id="SpringConfigTool" class="www.quality.util.SpringConfigTool"></bean>
	 */
	public static void main(String[] args) {
		//TblUserService userser = (TblUserServiceImpl) SpringConfigTool
		//		.getBean("www.quality.service.impl.TblUserServiceImpl");
		//System.out.println(userser);
	}

	private static ApplicationContext context = null;
	private static SpringConfigTool stools = null;

	public synchronized static SpringConfigTool init() {
		if (stools == null) {
			stools = new SpringConfigTool();
		}
		return stools;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	public synchronized static Object getBean(String beanName) {
		Object o = null;
		try {
			o = context.getBean(beanName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return o;
	}

}