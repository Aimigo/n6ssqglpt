package www.quality.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblJkzxfl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ZxflAction extends BasicAction implements ModelDriven<TblJkzxfl> {

	private TblJkzxfl zxfl = new TblJkzxfl();
	
	public TblJkzxfl getModel() {
		// TODO Auto-generated method stub
		return zxfl;
	}

	@SuppressWarnings("rawtypes")
	public String list(){
		Pager p = this.get_page();
		p.setPageRows(30);
		
			p = jkzxflser.getPagerByCriteria(p);
	
		setAttribute("_page", p);
		
		//查询top5分类中有多少数据
        Map topmap=jkzxser.getTop();
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
		zxfl = jkzxflser.getOneById(Integer.parseInt(id));
		super.setAttribute("zxfl", zxfl);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		zxfl = jkzxflser.getOneById(Integer.parseInt(id));
		super.setAttribute("zxfl", zxfl);
		return "detail";
	}
	
	public String save(){
		jkzxflser.save(zxfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康咨询 >> 咨询分类", "添加", "添加咨询分类名称为“"+zxfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public String update(){
		jkzxflser.update(zxfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康咨询 >> 咨询分类", "修改", "修改咨询分类名称为“"+zxfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = jkzxflser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康咨询 >> 咨询分类", "删除", "删除咨询分类名称为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		jkzxflser.deleteById(id);
	}
}
