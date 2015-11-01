package www.quality.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblFunction;
import www.quality.model.TblOperation;
import www.quality.model.TblProject;
import www.quality.model.TblRole;
import www.quality.model.TblRoleAndOperation;
import www.quality.model.TblUser;
import www.quality.util.Pager;
import www.quality.util.ZTree;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
*类的描述:该类是对应TblRole的管理操作
*作者:李留洋
*创建日期:2013-01-24
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends BasicAction implements ModelDriven<TblRole> {
	
	private TblRole tblrole = new TblRole();
	//private static final String URL = "role_list.action";
	
	private Gson gson = new Gson();
	public TblRole getModel() {
		// TODO Auto-generated method stub
		return tblrole;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询Tblrole的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
		//查出用户在此功能中拥有的操作权限
		//TblUser user = (TblUser)super.getParameterFromSession("user");
		//List<String> operationlist = userser.getOperationByUsercodeAndUrl(user.getUsercode(), URL);
		//super.setAttribute("operationlist", operationlist);
		
		//查出列表
		String name = super.getParameter("name");
		Pager pager = this.get_page();
		if(null!=name&&!"".equals(name)){
			pager = roleser.getPagerByCriteria(pager,name);
			pager.putParam("name", name);
		}else{
			pager = roleser.getPagerByCriteria(pager,null);
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
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 函  数  名 :save
	 * 功能描述：用于新增角色
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String save(){
		//获取UUID并进行添加
		String code = UUID.randomUUID().toString();
		tblrole.setCode(code);
		//保存角色
		roleser.saveRole(tblrole);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 角色管理", "添加", "添加角色名称为“"+tblrole.getName()+"”的数据", null);
		return "success";
	}
	
	/**
	 * 函  数  名 :edit
	 * 功能描述：跳转到修改角色界面
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
		tblrole = roleser.getOneById(Integer.parseInt(id));
		super.setAttribute("tblrole", tblrole);
		return "edit";
	}
	
	/**
	 * 函  数  名 :update
	 * 功能描述：用于修改角色
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String update(){
		roleser.updateRole(tblrole);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 角色管理", "修改", "修改角色名称为“"+tblrole.getName()+"”的数据", null);
		return "success";
	}
	
	/**
	 * 函  数  名 :delete
	 * 功能描述：用于批量或单独删除角色
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
		List<String> list = roleser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 角色管理", "删除", "删除角色名称为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		roleser.deleteRoleById(id);
		return "success";
	}
	
	/**
	 * 函  数  名 :detail
	 * 功能描述：用于跳转到查看角色
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
		tblrole = roleser.getOneById(Integer.parseInt(id));
		super.setAttribute("tblrole", tblrole);
		return "detail";
	}
	
	/**
	 * 函  数  名 :getTreeFromOperation
	 * 功能描述：用于AJAX生成JSON数据并通过DTREE展示到前台页面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String getTreeFromOperation(){
		return "tree";
	}
	
	/**
	 * 获取左边树
	 */
	public void getTree(){
		// 查出所有操作 Map保存 用于展示表格以及树
		Map<String, List<TblOperation>> operationMap = new HashMap<String, List<TblOperation>>();
		List<TblOperation> operationList = opser.getAllData();
		
		for (TblOperation tbloperation : operationList) {
			if (!operationMap.containsKey(tbloperation.getFunctioncode())) {
				operationMap.put(tbloperation.getFunctioncode(),
						new ArrayList<TblOperation>());
			}
			operationMap.get(tbloperation.getFunctioncode()).add(tbloperation);
		}

		// 查出全部功能
		List<TblFunction> functionList = funser.getAllData();
		// 查出全部项目
		List<TblProject> projectList = proser.getAlldata();
		
		List<ZTree> treeList=new ArrayList<ZTree>();
		//遍历项目
		for(TblProject pro:projectList){
			ZTree ztree = new ZTree();
			ztree.setId(pro.getCode());
			ztree.setpId("0");
			ztree.setName(pro.getName());
			ztree.setOpen(true);
			ztree.setParent(true);
//			ztree.setIconClose("/n6ssqglpt/fun/images/tree_img/folder.gif");
//			ztree.setIconOpen("/n6ssqglpt/fun/images/tree_img/folderopen.gif");
			treeList.add(ztree);
		}
		//遍历功能
		for(TblFunction fun:functionList){
			ZTree ztree = new ZTree();
			ztree.setId(fun.getCode());
			if(("0").equals(fun.getFcode())){
				ztree.setpId(fun.getProjectcode());
			}else{
				ztree.setpId(fun.getFcode());
			}
			ztree.setName(fun.getName());
			ztree.setParent(true);
//			ztree.setIconClose("/n6ssqglpt/fun/images/tree_img/folder.gif");
//			ztree.setIconOpen("/n6ssqglpt/fun/images/tree_img/folderopen.gif");
			treeList.add(ztree);
		}
		//遍历操作
		for (String key : operationMap.keySet()) {
			for (TblOperation oper : operationMap.get(key)) {
				ZTree ztree = new ZTree();
				ztree.setId(oper.getFunctioncode()+oper.getName());
				ztree.setpId(key+"");
				ztree.setParent(false);
				ztree.setName(oper.getOperationname());
//				ztree.setIcon("/n6ssqglpt/fun/images/tree_img/dot.gif");
				treeList.add(ztree);
			}
		}
		
		//查出对应角色的操作
		String rolecode = super.getParameter("rolecode");
		List<TblRoleAndOperation> raoList = raoser.getDataByRolecode(rolecode);
		
		List<Object> list = new ArrayList<Object>();
		list.add(treeList);
		list.add(raoList);
		super.setAjaxData(gson.toJson(list));
	}
	
	/**
	 * 函  数  名 :selectFunction
	 * 功能描述：用于AJAX向数据库中添加role和function的关联关系
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public void selectFunction(){
		String rolecode = super.getParameter("rolecode");
		String opercode = super.getParameter("opercode");
		//先删除TBLROLEANDOPERATION表中的数据
		raoser.deleteRoleAndOperation(rolecode);
		//再添加所选择的关系
		String[] opers = opercode.split(",");	//将所得到的字符串分割成数组
		List<TblRoleAndOperation> list = new ArrayList<TblRoleAndOperation>();
		for (String string : opers) {
			TblRoleAndOperation rao = new TblRoleAndOperation();
			rao.setRolecode(rolecode);
			rao.setFuncode(string.substring(0,string.length()-1));
			rao.setFunxxcode(string.substring(string.length()-1, string.length()));//操作为个位数时 多位数报错谨慎
			list.add(rao);
		}
		raoser.saveRoleAndOperation(list);
	}
	
	/**
	 * 验证是否重复
	 * 0 不重复
	 * 1 重复
	 */
	public void isRepeat(){
		List<TblRole> list = roleser.getRoleByName(tblrole.getName());
		if(null==list||list.size()<=0){
			super.setAjaxData("0");
		}else{
			super.setAjaxData("1");
		}
	}
}
