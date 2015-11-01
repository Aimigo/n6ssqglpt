package www.quality.interceptor;
import www.quality.model.TblUser;
import www.quality.service.TblCzfwglService;
import www.quality.service.impl.TblCzfwglServiceImpl;
import www.quality.util.SpringConfigTool;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * session超时拦截器<br/> 
 * 判断session中是否有user对象，如果没有，一切操作无法进行，跳转到提示页面。
 */
@SuppressWarnings("serial")
public class SessionTimeoutInterceptor extends AbstractInterceptor {

	@Override
	@SuppressWarnings("unused")
	public String intercept(ActionInvocation invocation) throws Exception {
		// String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String methodName = invocation.getProxy().getMethod();
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		// 如果未登录
		if (user == null) {
			// 如果正在使用的是登录功能，就放行
			if (actionName.equals("login_loginUser")) {
				return invocation.invoke();
				// 否则就转到登录页面
			} else {
				return "sessionTimeOutPrompt";
			}
		}
		return invocation.invoke();
	}
}
