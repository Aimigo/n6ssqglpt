package www.quality.interceptor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import www.quality.model.TblFunction;
import www.quality.model.TblUser;
import www.quality.service.TblCzfwglService;
import www.quality.service.TblFunctionService;
import www.quality.service.TblUserService;
import www.quality.service.impl.TblCzfwglServiceImpl;
import www.quality.util.SpringConfigTool;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class OperationInterceptor extends AbstractInterceptor {

	@Resource(name=TblUserService.TBLUSER_SERVICE_IMPL)
	protected TblUserService userser;//用户表
	@Resource(name=TblFunctionService.TBLFUNCTION_SERVICE_IMPL)
	protected TblFunctionService funser;//功能表
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext. getRequest();
		Map session = ActionContext.getContext().getSession();
		String actionName = invocation.getProxy().getActionName();
		if (actionName.equals("login_loginUser"))
			return invocation.invoke();
		
		//得到一级菜单的FUNCTIONCODE
		String functioncode = request.getParameter("functioncode");//得到FUNCTIONCODE
		if(null!=functioncode&&!"".equals(functioncode)){
			session.remove("function_one");
			//获取一级功能
			TblFunction function = funser.getOneByCode(functioncode);
			session.put("function_one", function);
		}
		
		//得到二级菜单的FUNCTIONCODE2
		String functioncode2 = request.getParameter("functioncode2");//得到FUNCTIONCODE
		if(null!=functioncode2&&!"".equals(functioncode2)){
			session.remove("function_two");
			//获取二级功能
			TblFunction function = funser.getOneByCode(functioncode2);
			session.put("function_two", function);
		}
		
		//得到二级菜单的FUNCTIONCODE3
		String functioncode3 = request.getParameter("functioncode3");//得到FUNCTIONCODE
		if(null!=functioncode3&&!"".equals(functioncode3)){
			session.remove("function_three");
			//获取二级功能
			TblFunction function = funser.getOneByCode(functioncode3);
			session.put("function_three", function);
		}
		
		if("login_enter".equals(actionName)){
			session.remove("function_one");
		}
		
		if(null!=functioncode2&&!"".equals(functioncode2)&&(null==functioncode3||"".equals(functioncode3))){
			session.remove("function_three");
		}
		
		if("pxjn_list".equals(actionName)){//由于改变了功能路径，这里需要转换成功能路径才能正常使用
			actionName = "pxjn_chart";
		}
		
		//获取功能的操作列表
		TblUser user = (TblUser) session.get("user"); // 当前登录的用户
		List<String> operationlist  = userser.getOperationByUsercodeAndUrl(user.getUsercode(), actionName+".action");
		request.setAttribute("operationlist", operationlist);
		
		if(operationlist!=null&&operationlist.size()>0)
			for (String string : operationlist) {
				System.out.println(string);
			}
		
		return invocation.invoke();
	}

}
