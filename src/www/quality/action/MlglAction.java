package www.quality.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblMlfl;
import www.quality.model.TblMlgl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MlglAction extends BasicAction implements ModelDriven<TblMlgl> {

	private TblMlgl mlgl = new TblMlgl();
	
	public TblMlgl getModel() {
		// TODO Auto-generated method stub
		return mlgl;
	}
	
	public String list(){
		Pager p = this.get_page();
		p=mlglser.getPagerByCriteria(p);
		setAttribute("_page", p);
		
		List<TblMlfl> mlfllist = mlflser.getAllDate(); 
		super.setAttribute("mlfllist", mlfllist);
		return "list";
	}
	
	public String add(){
		List<TblMlfl> mlfllist = mlflser.getAllDate(); 
		super.setAttribute("mlfllist", mlfllist);
		return "add";
	}
	
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		mlgl = mlglser.getOneById(Integer.parseInt(id));
		super.setAttribute("mlgl", mlgl);
		
		List<TblMlfl> mlfllist = mlflser.getAllDate(); 
		super.setAttribute("mlfllist", mlfllist);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		mlgl = mlglser.getOneById(Integer.parseInt(id));
		super.setAttribute("mlgl", mlgl);
		return "detail";
	}
	
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		mlgl.setUserid(user.getId());
		mlgl.setScsj(new Date());
		mlglser.save(mlgl);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 目录管理", "添加", "添加目录标题为“"+mlgl.getBt()+"”的数据", null);
	
		return "success";
	}
	
	public String update(){
		mlglser.update(mlgl);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 目录管理", "修改", "修改目录标题为“"+mlgl.getBt()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = mlglser.getBtsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 目录管理", "删除", "删除目录标题为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		mlglser.deleteById(id);
	}

}
