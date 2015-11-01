package www.quality.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblMlfl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MlflAction extends BasicAction implements ModelDriven<TblMlfl> {

	private TblMlfl mlfl = new TblMlfl();
	
	public TblMlfl getModel() {
		// TODO Auto-generated method stub
		return mlfl;
	}

	@SuppressWarnings("rawtypes")
	public String list(){
		Pager p = this.get_page();
		p.setPageRows(60);
		p=mlflser.getPagerByCriteria(p);
		setAttribute("_page", p);
		
		//查询top5分类中有多少数据
        Map topmap=mlglser.getTop();
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
		mlfl = mlflser.getOneById(Integer.parseInt(id));
		super.setAttribute("mlfl", mlfl);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		mlfl = mlflser.getOneById(Integer.parseInt(id));
		super.setAttribute("mlfl", mlfl);
		return "detail";
	}
	
	public String save(){
		mlflser.save(mlfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 目录分类", "添加", "添加目录分类名称为“"+mlfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public String update(){
		mlflser.update(mlfl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 目录分类", "修改", "修改目录分类名称为“"+mlfl.getName()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = mlflser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 目录分类", "删除", "删除目录分类名称为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		mlflser.deleteById(id);
	}
}
