package www.quality.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblUser;
import www.quality.model.TblYlzsfl;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class YlzsflAction extends BasicAction implements ModelDriven<TblYlzsfl> {

	private TblYlzsfl ylzsfl = new TblYlzsfl();
	
	public TblYlzsfl getModel() {
		// TODO Auto-generated method stub
		return ylzsfl;
	}

	@SuppressWarnings("rawtypes")
	public String list(){
		Pager p = this.get_page();
		p.setPageRows(60);
		p=ylzsflser.getPagerByCriteria(p);
		setAttribute("_page", p);
		
		//查询top5分类中有多少数据
        Map topmap=ylzsser.getTop();
        setAttribute("topmap", topmap);
        JSONArray json = JSONArray.fromObject(topmap);  
        setAttribute("topjson",json.toString());
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
		ylzsfl = ylzsflser.getOneById(Integer.parseInt(id));
		super.setAttribute("ylzsfl", ylzsfl);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		ylzsfl = ylzsflser.getOneById(Integer.parseInt(id));
		super.setAttribute("ylzsfl", ylzsfl);
		return "detail";
	}
	
	public String save(){
		ylzsflser.save(ylzsfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 医疗知识 >> 知识分类", "添加", "添加知识分类名称为“"+ylzsfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public String update(){
		ylzsflser.update(ylzsfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 医疗知识 >> 知识分类", "修改", "修改知识分类名称为“"+ylzsfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = ylzsflser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 医疗知识 >> 知识分类", "删除", "删除知识分类名称为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		ylzsflser.deleteById(id);
	}
}
