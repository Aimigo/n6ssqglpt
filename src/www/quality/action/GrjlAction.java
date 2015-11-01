package www.quality.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblGrjl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class GrjlAction extends BasicAction implements ModelDriven<TblGrjl> {

	private TblGrjl grjl = new TblGrjl();
	
	public TblGrjl getModel() {
		// TODO Auto-generated method stub
		return grjl;
	}

	public String list(){
		Pager p = this.get_page();
		p=grjlser.getPagerByCriteria(p);
		setAttribute("_page", p);
		return "list";
	}
	
	public String add(){
		return "add";
	}
	
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		grjl = grjlser.getOneById(Integer.parseInt(id));
		super.setAttribute("grjl", grjl);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		grjl = grjlser.getOneById(Integer.parseInt(id));
		super.setAttribute("grjl", grjl);
		return "detail";
	}
	
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		grjl.setUserid(user.getId());
		grjlser.save(grjl);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务 >> 个人就业信息管理", "添加", "添加姓名为“"+grjl.getXm()+"”的数据", null);
	
		return "success";
	}
	
	public String update(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		grjl.setUserid(user.getId());
		grjlser.update(grjl);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务 >> 个人就业信息管理", "修改", "修改姓名为“"+grjl.getXm()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = grjlser.getXmsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务 >> 个人就业信息管理", "删除", "删除姓名为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		grjlser.deleteById(id);
	}
}
