package www.quality.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblFunction;
import www.quality.model.TblLoginLog;
import www.quality.model.TblUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
*类的描述:该类是起到用户登录的作用
*作者:李留洋
*创建日期:2013-01-23
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LoginAction extends BasicAction implements ModelDriven<TblUser> {
	private TblUser tblUser = new TblUser();
	public String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TblUser getModel() {
		// TODO Auto-generated method stub
		return tblUser;
	}
	
	/**
	 * 函  数  名 :loginUser
	 * 功能描述： 利用Ajax进行用户登录
	 * 参数描述：  
	 * 返回值  : void
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-23
	 * 修 改 人: 
	 * 日    期:
	 */
	public void loginUser(){
		ActionContext.getContext().getSession().remove("user");
		
		//获取用户输入的验证码 进行验证是否正确
		code = super.getParameter("code");
		
		//验证验证码是否为空 [前台已经验证过了]
		if(null==code||"".equals(code)){
			super.setAjaxData("验证码不能为空！");
			return;
		}
		
		String Auth_Code = (String) super.getParameterFromSession("AUTH_CODE");
		
		if(!code.equals(Auth_Code)){
			super.setAjaxData("验证码输入错误！");
			return;
		}
		
		//获取用户名和密码进行验证[写这么长是不想一个一个验证了 前台已经验证过了]
		if(null==tblUser.getUsername()||"".equals(tblUser.getUsername())||null==tblUser.getPassword()||"".equals(tblUser.getPassword())){
			super.setAjaxData("用户名或密码不能为空！");
			return;
		}
		
		//根据账户查询所有的信息 [只有0个或1个]
		List<TblUser> tblUserList = userser.getListByUsername(tblUser.getUsername());
		
		//判断所查询的信息是不是为0个
		if(null==tblUserList || tblUserList.size()<=0){
			super.setAjaxData("该用户名不存在！");
			return;
		}
		
		//如果不是0个 则根据其LIST的第一个来和password进行对比 这里不进行唯一判定
		if(tblUser.getPassword().equals(tblUserList.get(0).getPassword())){
			setSessionsetAttribute("user", tblUserList.get(0));
			
			HttpServletRequest request = ServletActionContext. getRequest(); 
			//写入登录日志
			TblLoginLog loginlog = new TblLoginLog();
			loginlog.setUsername(tblUserList.get(0).getUsername());
			loginlog.setRealname(tblUserList.get(0).getRealname());
			loginlog.setLogintime(new Date());
			loginlog.setLoginip(this.getIpAddr(request));
			loginlogser.saveLoginlog(loginlog);
			
			super.setAjaxData("1");			//登录成功
		}else{
			super.setAjaxData("密码输入错误！");	//依然登录失败
		}
	
	}
	
	/**
	 * 函  数  名 :enter
	 * 功能描述： 用户登录成功后进入index.jsp页面
	 * 参数描述：  
	 * 返回值  : String
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-23
	 * 修 改 人: 
	 * 日    期:
	 */
	public String enter(){
		TblUser user = (TblUser) super.getParameterFromSession("user");
		if(user==null)
			return "input";
		
		Map<String, List<TblFunction>> functionMap = userser
				.getDataByUsercode(user.getUsercode(),
						"c0fefd10-702c-4b4b-8ffb-ce5b91b6bffd");
		super.setSessionsetAttribute("functionMap", functionMap);
		
		return "success";
	}
	
	public String clearUser(){
		ActionContext.getContext().getSession().clear();
		return "input";
	}
	
	public String getIpAddr(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	}
	
	public void editPwd(){
		String oldpwd = super.getParameter("oldpwd");
		String newpwd = super.getParameter("newpwd");
		String renewpwd = super.getParameter("renewpwd");
		
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		if(null==oldpwd||"".equals(oldpwd)||null==newpwd||"".equals(newpwd)||null==renewpwd||"".equals(renewpwd)){
			super.setAjaxData("-1");
			return;
		}
		
		if(null!=oldpwd&&!oldpwd.equals(user.getPassword())){
			super.setAjaxData("1");
			return;
		}
		
		if(!newpwd.equals(renewpwd)){
			super.setAjaxData("2");
			return;
		}
		
		user.setPassword(newpwd);
		userser.updateUser(user);
		super.setAjaxData("0");
	}
}
