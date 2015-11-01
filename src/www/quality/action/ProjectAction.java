package www.quality.action;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblProject;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ProjectAction extends BasicAction implements
		ModelDriven<TblProject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TblProject pro = new TblProject();

	public String list(){
		String name = getParameter("name");
		String col = getParameter("col");
		String px = getParameter("px");
		if (null == px && "".equals(px)) {
			px = "desc";
		}

		Pager p = this.get_page();

		if (name != null || (col != null && px != null)) {
			p = proser.getPagerByCriteria(p, name, col, px);
		} else {
			p = proser.getPagerByCriteria(p, null, null, null);
		}

		if ("desc".equals(px)) {
			px = "asc";
		} else {
			px = "desc";
		}
		
		p.putParam("col", col);
		p.putParam("px", px);
		p.putParam("name", name);
		setAttribute("_page", p);
		return "list";
	}

	public String add() {
		return "add";
	}

	public String save() {
		// 获取UUID并进行添加
		String code = UUID.randomUUID().toString();
		pro.setCode(code);
		proser.saveProject(pro);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 项目管理", "添加", "添加项目名称为“"+pro.getName()+"”的数据", null);
		return "success";
	}

	public String edit() {
		TblProject p = proser.getOneById(pro.getId());
		setAttribute("pro", p);
		return "edit";
	}

	public String update() {
		proser.updateProject(pro);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 项目管理", "修改", "修改项目名称为“"+pro.getName()+"”的数据", null);
		return "success";
	}

	public String delete() {
		//操作日志添加
		pro = proser.findById(pro.getId());
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 项目管理", "删除", "删除项目名称为“"+pro.getName()+"”的数据", null);
				
		proser.deleteProject(pro.getId());
		return "success";
	}

	public void deleAll() {
		String ids = getParameter("id");
		
		//操作日志添加
		List<String> list = proser.getNamesByIds(ids);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 项目管理", "删除", "删除项目名称为“"+StringUtils.join(list,'、')+"”的数据", null);
				
		proser.deleteall(ids);
	}

	public String detail() {
		TblProject pro = proser.findById(Integer.parseInt(getParameter("id")));
		setAttribute("pro", pro);
		return "detail";
	}

	/**
	 * 验证是否重复
	 * 0 不重复
	 * 1 重复
	 */
	public void isRepeat(){
		List<TblProject> list = proser.findByName(pro.getName());
		if(null==list||list.size()<=0){
			super.setAjaxData("0");
		}else{
			super.setAjaxData("1");
		}
	}
	
	/* 不用了 */
	public void ajaxCheck() {
		String code = getParameter("code");
		String id = getParameter("id");
		if (id == null) {// 添加页面
			TblProject pro = proser.getProByCode(code);
			if (pro != null)
				setAjaxData("1");
		}
		if (id != null) {// 修改页面
			@SuppressWarnings("unchecked")
			List<TblProject> list = (List<TblProject>) proser
					.getProByCode(code);
			if (list.size() > 0) {
				for (TblProject u : list) {
					if (!id.equals(u.getId() + "")) {
						setAjaxData("1");
						break;
					}
				}
			}
		}
	}

	public TblProject getModel() {
		return pro;
	}

}