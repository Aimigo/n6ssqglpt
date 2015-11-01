package www.quality.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblUser;
import www.quality.model.TblYjzs;
import www.quality.model.TblYlzsfl;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class YlzsAction extends BasicAction implements ModelDriven<TblYjzs> {

	private TblYjzs ylzs = new TblYjzs();
	private TblYlzsfl ylzsfl=new TblYlzsfl();
	
	public TblYjzs getModel() {
		// TODO Auto-generated method stub
		return ylzs;
	}

	public String list(){
		Pager p = this.get_page();
		p=ylzsser.getPagerByCriteria(p);
		List<TblYlzsfl> ylzsfl = ylzsflser.getAllDate(); 
		super.setAttribute("ylzsfl", ylzsfl);
		setAttribute("_page", p);
		return "list";
	}
	
	public String add(){
		List<TblYlzsfl> ylzsfl = ylzsflser.getAllDate(); 
		super.setAttribute("ylzsfl", ylzsfl);
		return "add";
	}
	
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		ylzs = ylzsser.getOneById(Integer.parseInt(id));
		super.setAttribute("ylzs", ylzs);
		
		List<TblYlzsfl> ylzsfl = ylzsflser.getAllDate(); 
		super.setAttribute("ylzsfl", ylzsfl);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		ylzs = ylzsser.getOneById(Integer.parseInt(id));
		super.setAttribute("ylzs", ylzs);
		return "detail";
	}
	
	public String save(){
		ylzsser.save(ylzs);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 医疗知识 >> 医疗知识 ", "添加", "添加医疗知识标题为“"+ylzs.getBt()+"”的数据", null);
		
		return "success";
	}
	
	public String update(){
		ylzsser.update(ylzs);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 医疗知识 >> 医疗知识 ", "修改", "修改医疗知识标题为“"+ylzs.getBt()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = ylzsser.getBtsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 医疗知识 >> 医疗知识 ", "删除", "删除医疗知识标题为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		ylzsser.deleteById(id);
	}
}
