package www.quality.action;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblQyzp;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class QyzpAction extends BasicAction implements ModelDriven<TblQyzp> {

	private TblQyzp qyzp = new TblQyzp();
	
	public TblQyzp getModel() {
		// TODO Auto-generated method stub
		return qyzp;
	}

	public String list() throws ParseException{
		Pager p = this.get_page();
		p=qyzpser.getPagerByCriteria(p);
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
		qyzp = qyzpser.getOneById(Integer.parseInt(id));
		super.setAttribute("qyzp", qyzp);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		qyzp = qyzpser.getOneById(Integer.parseInt(id));
		super.setAttribute("qyzp", qyzp);
		return "detail";
	}
	
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		qyzp.setUserid(user.getId());
		qyzpser.save(qyzp);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务 >> 职位信息动态", "添加", "添加招聘职位为“"+qyzp.getZpzw()+"”的数据", null);
	
		return "success";
	}
	
	public String update(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		qyzp.setUserid(user.getId());
		qyzpser.update(qyzp);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务 >> 职位信息动态", "修改", "修改招聘职位为“"+qyzp.getZpzw()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = qyzpser.getZpzwsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务 >> 职位信息动态", "删除", "删除招聘职位为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		qyzpser.deleteById(id);
	}
}
