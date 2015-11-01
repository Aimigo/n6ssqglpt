package www.quality.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerListener  implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().log("++++Tomcat服务器监听开始 ++++");
	}

	public void contextDestroyed(ServletContextEvent event) {
		event.getServletContext().log("++++Tomcat服务器监听结束 ++++");
	}
}
