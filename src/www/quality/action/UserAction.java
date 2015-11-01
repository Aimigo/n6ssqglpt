package www.quality.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblDepartment;
import www.quality.model.TblGrid;
import www.quality.model.TblJkzxfl;
import www.quality.model.TblRole;
import www.quality.model.TblUser;
import www.quality.model.TblUserAndRole;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
*类的描述:该类是对应TblUser的管理操作
*作者:李留洋
*创建日期:2013-01-23
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BasicAction implements ModelDriven<TblUser> {
	private TblUser tblUser=new TblUser();
	//private static final String URL = "uInfo_list.action";
	
	public TblUser getModel() {
		// TODO Auto-generated method stub
		return tblUser;
	}
	
	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblUser的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-23
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
		//查出用户在此功能中拥有的操作权限
		//TblUser user = (TblUser)super.getParameterFromSession("user");
		//List<String> operationlist = userser.getOperationByUsercodeAndUrl(user.getUsercode(), URL);
		//super.setAttribute("operationlist", operationlist);
		
		//查出列表
		String realname = super.getParameter("realname");
		Pager pager = this.get_page();
		if(null!=realname&&!"".equals(realname)){
			pager = userser.getPagerByCriteria(pager,"2",realname);
			pager.putParam("realname", realname);
		}else{
			pager = userser.getPagerByCriteria(pager,null,null);
		}
		
		super.setAttribute("_page", pager);
		return "list";
	}
	
	/**
	 * 函  数  名 :add
	 * 功能描述：跳转到新增页面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-23
	 * 修 改 人: 
	 * 日    期:
	 */
	public String add(){
		//角色信息
		List<TblRole> roleList = roleser.getAllDate();
		super.setAttribute("roleList", roleList);
		//网格信息
		List<TblGrid> gridlist = gridser.getAllDate();
		super.setAttribute("gridlist", gridlist);
		//部门信息
		List<TblDepartment> departmentlist = departmentser.getAllDate();
		super.setAttribute("departmentlist", departmentlist);
		//健康分类
		List<TblJkzxfl> jkzxfllist = jkzxflser.getAllDate();
		super.setAttribute("jkzxfllist", jkzxfllist);
		return "add";
	}
	
	/**
	 * 函  数  名 :save
	 * 功能描述：用于新增用户
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-23
	 * 修 改 人: 
	 * 日    期:
	 */
	public String save(){
		//获取UUID并进行添加
		String code = UUID.randomUUID().toString();
		tblUser.setUsercode(code);
		//保存用户
		userser.addUser(tblUser);
		
		//添加角色
		List<TblUserAndRole> list = new ArrayList<TblUserAndRole>();
		TblUserAndRole uar = new TblUserAndRole();
		uar.setUsercode(code);
		uar.setRolecode(super.getParameter("rolecode"));
		list.add(uar);
		uarser.saveUandR(list);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 用户管理", "添加", "添加用户名称为“"+tblUser.getRealname()+"”的数据", null);
		return "success";
	}
	
	/**
	 * 函  数  名 :edit
	 * 功能描述：跳转到修改用户界面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		tblUser = userser.getOneById(Integer.parseInt(id));
		super.setAttribute("tblUser", tblUser);
		
		//用户所拥有的角色
		List<TblRole> uroleList = uarser.getRoleByUsercode(tblUser.getUsercode());
		if(uroleList != null && uroleList.size() > 0)
			super.setAttribute("urole", uroleList.get(0));
		
		//角色信息
		List<TblRole> roleList = roleser.getAllDate();
		super.setAttribute("roleList", roleList);
		//网格信息
		List<TblGrid> gridlist = gridser.getAllDate();
		super.setAttribute("gridlist", gridlist);
		//部门信息
		List<TblDepartment> departmentlist = departmentser.getAllDate();
		super.setAttribute("departmentlist", departmentlist);
		//健康分类
		List<TblJkzxfl> jkzxfllist = jkzxflser.getAllDate();
		super.setAttribute("jkzxfllist", jkzxfllist);
		
		return "edit";
	}
	
	/**
	 * 函  数  名 :update
	 * 功能描述：用于修改用户
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String update(){
		userser.updateUser(tblUser);
		
		//添加角色
		uarser.deleteUAndR(tblUser.getUsercode());
		List<TblUserAndRole> list = new ArrayList<TblUserAndRole>();
		TblUserAndRole uar = new TblUserAndRole();
		uar.setUsercode(tblUser.getUsercode());
		uar.setRolecode(super.getParameter("rolecode"));
		list.add(uar);
		uarser.saveUandR(list);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 用户管理", "修改", "修改用户名称为“"+tblUser.getRealname()+"”的数据", null);
		return "success";
	}
	

	/**
	 * 函  数  名 :delete
	 * 功能描述：用于批量或单独删除功能
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = userser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 用户管理", "删除", "删除用户名称为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		userser.deleteUserById(id);
		return "success";
	}
	
	/**
	 * 函  数  名 :detail
	 * 功能描述：用于跳转到查看功能
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入查看页面
		}
		tblUser = userser.getOneById(Integer.parseInt(id));
		super.setAttribute("tblUser", tblUser);
		
		//用户所拥有的角色
		List<TblRole> uroleList = uarser.getRoleByUsercode(tblUser.getUsercode());
		if(uroleList != null && uroleList.size() > 0)
			super.setAttribute("urole", uroleList.get(0));
		return "detail";
	}
	
	/**
	 * 函  数  名 :getTreeFromRole
	 * 功能描述：用于AJAX生成JSON数据并通过DTREE展示到前台页面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public void getTreeFromRole(){
		//1.查出所有角色
		List<TblRole> roleList = roleser.getAllDate();
		//2.查出对应用户的角色
		String usercode = super.getParameter("usercode");
		List<TblRole> uroleList = uarser.getRoleByUsercode(usercode);
		//3.创建一个新的StringBuffer（json格式） 用于保存 是否用户已经拥有这个角色 和 角色的信息
		StringBuffer json = new StringBuffer("[");
		for (TblRole tblrole : roleList) {
			Boolean isHave = false;
			for (TblRole r : uroleList) {
				if(tblrole.getCode().equals(r.getCode())){
					isHave = true;
				}
			}
			if("[".equals(json.toString()))
				json.append("{");
			else
				json.append(",{");
			json.append("\"isHave\":\""+isHave+"\",");
			json.append("\"id\":\""+tblrole.getId()+"\",");
			json.append("\"name\":\""+tblrole.getName()+"\",");
			json.append("\"code\":\""+tblrole.getCode()+"\"");
			json.append("}");
		}
		json.append("]");
		System.out.println(json);
		//4.生成JSON数据
		super.setAjaxData(json.toString());
	}
	
	/**
	 * 函  数  名 :selectRole
	 * 功能描述：用于AJAX向数据库中添加role和user的关联关系
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public void selectRole(){
		String usercode = super.getParameter("usercode");
		String rolecode = super.getParameter("rolecode");
		//先删除usercode的所有关系
		uarser.deleteUAndR(usercode);
		//再添加所选择的角色关系
		String[] roles = rolecode.split(",");	//将所得到的字符串分割成数组
		List<TblUserAndRole> list = new ArrayList<TblUserAndRole>();
		for (String role : roles) {
			TblUserAndRole uar = new TblUserAndRole();
			uar.setUsercode(usercode);
			uar.setRolecode(role);
			list.add(uar);
		}
		uarser.saveUandR(list);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "用户管理", "分配角色", "", null);
	}
	
	/**
	 * 验证是否重复
	 * 0 不重复
	 * 1 重复
	 */
	public void isRepeat(){
		List<TblUser> list = userser.getListByUsername(tblUser.getUsername());
		if(null==list||list.size()<=0){
			super.setAjaxData("0");
		}else{
			super.setAjaxData("1");
		}
	}
}
