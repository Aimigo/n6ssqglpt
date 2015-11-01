package www.quality.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblGrjkda;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class JkdaAction extends BasicAction implements ModelDriven<TblGrjkda> {

	private TblGrjkda grjkda = new TblGrjkda();
	
	public TblGrjkda getModel() {
		// TODO Auto-generated method stub
		return grjkda;
	}

	public String list(){
		Pager p = this.get_page();
		p=grjkdaser.getPagerByCriteria(p);
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
		grjkda = grjkdaser.getOneById(Integer.parseInt(id));
		super.setAttribute("grjkda", grjkda);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		grjkda = grjkdaser.getOneById(Integer.parseInt(id));
		super.setAttribute("grjkda", grjkda);
		return "detail";
	}
	
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		grjkdaser.save(grjkda);

		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康档案", "添加", "添加姓名为“"+grjkda.getXm()+"”的数据", null);
	
		return "success";
	}
	
	public String update(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		grjkdaser.update(grjkda);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康档案", "修改", "修改姓名为“"+grjkda.getXm()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = grjkdaser.getXmsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康档案", "删除", "删除姓名为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		grjkdaser.deleteById(id);
	}
}
