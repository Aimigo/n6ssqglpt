package www.quality.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblMdfl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MdflAction extends BasicAction implements ModelDriven<TblMdfl> {

	private TblMdfl mdfl = new TblMdfl();
	
	public TblMdfl getModel() {
		// TODO Auto-generated method stub
		return mdfl;
	}

	@SuppressWarnings("rawtypes")
	public String list(){
		Pager p = this.get_page();
		p.setPageRows(60);
		p=mdflser.getPagerByCriteria(p);
		setAttribute("_page", p);
		
		//查询top5分类中有多少数据
        Map topmap=mdclser.getTop();
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
		mdfl = mdflser.getOneById(Integer.parseInt(id));
		super.setAttribute("mdfl", mdfl);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		mdfl = mdflser.getOneById(Integer.parseInt(id));
		super.setAttribute("mdfl", mdfl);
		return "detail";
	}
	
	public String save(){
		mdflser.save(mdfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 矛盾分类", "添加", "添加矛盾分类名称为“"+mdfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public String update(){
		mdflser.update(mdfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 矛盾分类", "修改", "修改矛盾分类名称为“"+mdfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = mdflser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 矛盾分类", "删除", "删除矛盾分类名称为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		mdflser.deleteById(id);
	}
}
