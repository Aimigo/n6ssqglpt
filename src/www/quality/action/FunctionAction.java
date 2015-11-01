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
import www.quality.model.TblUser;
import www.quality.util.Pager;
import www.quality.util.ZTree;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 类的描述:该类是对应Tblfunction的管理操作 作者:李留洋 创建日期:2013-01-24 修改人 修改日期 修改原因描述
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FunctionAction extends BasicAction implements
		ModelDriven<TblFunction> {
	private TblFunction tblfunction = new TblFunction();
	//private static final String URL = "function_list.action";

	private Gson gson = new Gson();
	public TblFunction getModel() {
		// TODO Auto-generated method stub
		return tblfunction;
	}

	/**
	 * 函 数 名 :list 
	 * 功能描述： 无条件或有条件的查询tblfunction的信息并分页展示
	 * 参数描述：
	 * 返回值 : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋 
	 * 日 期: 2013-01-24
	 * 修 改 人: 
	 * 日 期:
	 */
	public String list() {
		// 查出用户在此功能中拥有的操作权限
		// TblUser user = (TblUser)super.getParameterFromSession("user");
		// List<String> operationlist =
		// userser.getOperationByUser(user.getUsercode(), URL);
		// super.setAttribute("operationlist", operationlist);

		// 分页查询功能
		String name = super.getParameter("name");
		String code=super.getParameter("code");
		//获取isBtn判断是点击查询按钮还是点击的树形菜单
		String isBtn=super.getParameter("isBtn");
		Pager pager = this.get_page();
		if (null != name && !"".equals(name)) {
			pager = funser.getPagerByCriteria(pager, name);
			pager.putParam("name", name);
		}else if(code!=null&&isBtn.equals("no")){
			pager=funser.getPagerByCode(pager, code);
		}else {
			pager = funser.getPagerByCriteria(pager, null);
		}
		
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
		super.setAttribute("_page", pager);
		super.setAttribute("operationMap", operationMap);
		
		if(isBtn!=null){
			return "funList";
		}
		return "list";
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
			ztree.setNodeId(pro.getId());
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
			ztree.setNodeId(fun.getId());
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
				ztree.setNodeId(oper.getId());
				ztree.setId(oper.getCode());
				ztree.setpId(key+"");
				ztree.setParent(false);
				ztree.setName(oper.getOperationname());
//				ztree.setIcon("/n6ssqglpt/fun/images/tree_img/dot.gif");
				treeList.add(ztree);
			}
		}
		super.setAjaxData(gson.toJson(treeList));
	}

	/**
	 * 函 数 名 :add
	 * 功能描述：跳转到新增页面
	 * 参数描述： 
	 * 返回值 : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋 
	 * 日 期: 2013-01-24 
	 * 修 改 人: 
	 * 日 期:
	 */
	public String add() {
		String code = super.getParameter("code");
		String projectCode = super.getParameter("projectCode");
		if ("".equals(code)) {
			TblProject tblproject = proser.getProByCode(projectCode);
			TblFunction tblfunction = new TblFunction();
			tblfunction.setCode("0");
			tblfunction.setName("顶级节点");
			super.setAttribute("tblproject", tblproject);
			super.setAttribute("tblfunction", tblfunction);
		} else {
			TblFunction tblfunction = funser.getOneByCode(code);
			TblProject tblproject = proser.getProByCode(tblfunction.getProjectcode());
			System.out.println(tblfunction.getUrl()+"---"+tblproject.getName());
			super.setAttribute("tblproject", tblproject);
			super.setAttribute("tblfunction", tblfunction);
		}

		// 查出全部项目
		//List<Tblproject> projectList = proser.getAlldata();
		//super.setAttribute("projectList", projectList);
		return "add";
	}

	/**
	 * 函 数 名 :save
	 * 功能描述：用于新增功能 
	 * 参数描述： 
	 * 返回值 : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋 
	 * 日 期: 2013-01-24 
	 * 修 改 人: 
	 * 日 期:
	 */
	public String save() {
		// 获取UUID并进行添加
		String code = UUID.randomUUID().toString();
		tblfunction.setCode(code);

		// 获取功能级别level
		// 获取功能顺序SERIALNUMBER
		// 获取唯一标识marker
		// 保存功能
		funser.saveFunction(tblfunction);

		// 保存功能后保存关联操作到关联表operation
		String isParent = super.getParameter("isParent");
		if ("0".equals(isParent)) {
			String[] operationxx = (String[]) ActionContext.getContext()
					.getParameters().get("operationxx");
			List<TblOperation> operationList = new ArrayList<TblOperation>();
			for (String string : operationxx) {
				TblOperation tbloperation = new TblOperation();
				tbloperation.setFunctioncode(code);
				tbloperation.setCode(UUID.randomUUID().toString());
				tbloperation.setName(string);
				operationList.add(tbloperation);
			}
			opser.saveOperation(operationList);
		}
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 功能管理", "添加", "添加功能名称为“"+tblfunction.getName()+"”的数据", null);
		return "success";
	}

	/**
	 * 函 数 名 :edit 
	 * 功能描述：跳转到修改功能界面 
	 * 参数描述： 
	 * 返回值 : String类型的数据，跳转到所对应的页面 
	 * 创 建人: 李留洋 
	 * 日 期: 2013-01-24 
	 * 修 改 人: 
	 * 日 期:
	 */
	public String edit() {
		String code = super.getParameter("code");
		if (null == code || "".equals(code)) {
			return "success"; // 如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		TblFunction tblfunction = funser.getOneByCode(code);
		TblProject tblproject = proser.getProByCode(tblfunction.getProjectcode());
		if (null != tblfunction.getUrl() && tblfunction.getUrl().length() > 0) {
			// 如果是子功能 则需要查出已添加的操作 tbloperation
			List<TblOperation> operationList = opser
					.getDataByFunctionCode(tblfunction.getCode());
			super.setAttribute("operationList", operationList);
		}

		// 查出父功能名称
		TblFunction ffunction = new TblFunction();
		if ("0".equals(tblfunction.getFcode())) {
			ffunction.setName("顶级节点");
			ffunction.setCode("0");
		} else {
			ffunction = funser.getOneByCode(tblfunction.getFcode());
		}

		// 查出全部项目
		//List<Tblproject> projectList = proser.getAlldata();
		//super.setAttribute("projectList", projectList);
		
		super.setAttribute("tblproject", tblproject);
		super.setAttribute("ffunction", ffunction);
		super.setAttribute("tblfunction", tblfunction);
		return "edit";
	}

	/**
	 * 函 数 名 :update
	 * 功能描述：用于修改功能 
	 * 参数描述： 
	 * 返回值 : String类型的数据，跳转到所对应的页面 
	 * 创 建人: 李留洋 
	 * 日 期: 2013-01-24 
	 * 修 改 人: 
	 * 日 期:
	 */
	public String update() {
		funser.updateFunction(tblfunction);

		// 如果是子功能 则删除子功能的关联信息
		if (null != tblfunction.getUrl() && tblfunction.getUrl().length() > 0) {
			// 得到已修改的操作值
			String[] operationxx = (String[]) ActionContext.getContext()
					.getParameters().get("operationxx");
			// 得到未修改的操作值
			String operationList = super.getParameter("operationList");
			String[] olist = operationList.split(",");
			// 1.比较出新添加的操作 进行保存操作
			List<TblOperation> list = new ArrayList<TblOperation>();
			for (String i : operationxx) {
				boolean a = true;
				for (String j : olist) {
					if (i.equals(j)) {
						a = false;
					}
				}
				if (a) {// 如果a为true则说明此操作为新添加
					TblOperation tbloperation = new TblOperation();
					tbloperation.setFunctioncode(tblfunction.getCode());
					tbloperation.setCode(UUID.randomUUID().toString());
					tbloperation.setName(i);
					list.add(tbloperation);

				}
			}
			opser.saveOperation(list);
			// 2.比较出已存在被删除的操作
			for (String j : olist) {
				boolean a = true;
				for (String i : operationxx) {
					if (i.equals(j)) {
						a = false;
					}
				}
				if (a) {// 如果a为true则说明此操作已被删除 须先删除角色功能表 再删除功能操作表
					opser.deleteOperation(tblfunction.getCode(), j);
				}
			}

		}
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 功能管理", "修改", "修改功能名称为“"+tblfunction.getName()+"”的数据", null);
		return "success";
	}

	/**
	 * 函 数 名 :delete
	 * 功能描述：用于批量或单独删除功能 
	 * 参数描述： 
	 * 返回值 : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋 
	 * 日 期: 2013-01-24
	 * 修 改 人: 
	 * 日 期:
	 */
	public String delete() {
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = funser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 功能管理", "删除", "删除功能名称为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		funser.deleteFunctionById(id);
		return "success";
	}

	/**
	 * 函 数 名 :detail
	 * 功能描述：用于跳转到查看功能 
	 * 参数描述： 
	 * 返回值 : String类型的数据，跳转到所对应的页面
	 * 创建 人: 李留洋 
	 * 日 期: 2013-01-24 
	 * 修 改 人: 
	 * 日 期:
	 */
	public String detail() {
		String id = super.getParameter("id");
		if (null == id || "".equals(id)) {
			return "success"; // 如果ID为空，则暂时跳到list页面，不进入查看页面
		}
		tblfunction = funser.getOneById(Integer.parseInt(id));
		TblProject tblproject = proser.getProByCode(tblfunction.getProjectcode());
		if (null != tblfunction.getUrl() && tblfunction.getUrl().length() > 0) {
			// 如果是子功能 则需要查出已添加的操作 tbloperation
			List<TblOperation> operationList = opser
					.getDataByFunctionCode(tblfunction.getCode());
			super.setAttribute("operationList", operationList);
		}

		// 查出父功能名称
		TblFunction ffunction = new TblFunction();
		if ("0".equals(tblfunction.getFcode())) {
			ffunction.setName("顶级节点");
			ffunction.setCode("0");
		} else {
			ffunction = funser.getOneByCode(tblfunction.getFcode());
		}
		// 查出全部项目
		//List<Tblproject> projectList = proser.getAlldata();
		//super.setAttribute("projectList", projectList);
		
		super.setAttribute("tblproject", tblproject);
		super.setAttribute("ffunction", ffunction);
		super.setAttribute("tblfunction", tblfunction);
		return "detail";
	}

	/**
	 * 验证是否重复
	 * 0 不重复
	 * 1 重复
	 */
	public void isRepeat(){
		List<TblFunction> list = funser.getListByNameAndFcode(tblfunction.getName(),tblfunction.getFcode());
		if(null==list||list.size()<=0){
			super.setAjaxData("0");
		}else{
			super.setAjaxData("1");
		}
	}
}
