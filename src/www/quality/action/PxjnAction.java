package www.quality.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import www.quality.model.Series;
import www.quality.model.TblJnfl;
import www.quality.model.TblPxjn;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class PxjnAction extends BasicAction implements ModelDriven<TblPxjn> {

	private TblPxjn pxjn = new TblPxjn();
	
	public TblPxjn getModel() {
		// TODO Auto-generated method stub
		return pxjn;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String chart(){
        Map map=pxjnser.getChart();
        setAttribute("map", map);
        List<String> cate = (List<String>) map.get("Cate");
        List<Series> seri = (List<Series>) map.get("Seri");
        Gson gson = new Gson();
        setAttribute("cate",gson.toJson(cate));
        setAttribute("seri",gson.toJson(seri));
		return "chart";
	}
	
	public String list() throws UnsupportedEncodingException{
		Pager p = this.get_page();
		
		String type = super.getParameter("dtype");
		if(null==type || "".equals(type)){
			type = "1";
		}
		p.putParam("type", type);
		
		p=pxjnser.getPagerByCriteria(p);
		setAttribute("_page", p);
		
		List<TblJnfl> jnfllist = jnflser.getAllDate(); 
		super.setAttribute("jnfllist", jnfllist);
		return "list";
	}
	
	public String add(){
		List<TblJnfl> jnfllist = jnflser.getAllDate(); 
		super.setAttribute("jnfllist", jnfllist);
		return "add";
	}
	
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		pxjn = pxjnser.getOneById(Integer.parseInt(id));
		super.setAttribute("pxjn", pxjn);
		
		List<TblJnfl> jnfllist = jnflser.getAllDate(); 
		super.setAttribute("jnfllist", jnfllist);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		pxjn = pxjnser.getOneById(Integer.parseInt(id));
		super.setAttribute("pxjn", pxjn);
		return "detail";
	}
	
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		pxjn.setUserid(user.getId());
		pxjn.setScsj(new Date());
		pxjnser.save(pxjn);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务>> 技能培训多媒体 >> 多媒体管理", "添加", "添加多媒体标题为“"+pxjn.getBt()+"”的数据", null);
	
		return "success";
	}
	
	public String update(){
		pxjnser.update(pxjn);
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务>> 技能培训多媒体 >> 多媒体管理", "修改", "修改多媒体标题为“"+pxjn.getBt()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = pxjnser.getBtsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务>> 技能培训多媒体 >> 多媒体管理", "删除", "删除多媒体标题为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		pxjnser.deleteById(id);
	}
}
