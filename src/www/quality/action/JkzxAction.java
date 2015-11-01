package www.quality.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblJkzx;
import www.quality.model.TblJkzxfl;
import www.quality.model.TblRole;
import www.quality.model.TblTsjl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class JkzxAction extends BasicAction implements ModelDriven<TblJkzx> {

	private TblJkzx jkzx = new TblJkzx();
	
	public TblJkzx getModel() {
		// TODO Auto-generated method stub
		return jkzx;
	}

	public String list(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户

		Pager page = this.get_page();
		
		String type = this.getUserType();
		if("1".equals(type)){
			page.putParam("yhid", user.getId()+"");
		}else if("2".equals(type)){
			page.putParam("zjid", user.getId()+"");
			page.putParam("zxfl", user.getHealthid()+"");
		}
		page.putParam("type", type);
		
		page = jkzxser.getPagerByCriteria(page);
		super.setAttribute("_page", page);
		
		//健康咨询分类
		List<TblJkzxfl> jkzxfllist = jkzxflser.getAllDate();
		super.setAttribute("jkzxfllist", jkzxfllist);
		return "list";
	}
	
	private String getUserType(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		//获取用户的角色
		List<TblRole> list = uarser.getRoleByUsercode(user.getUsercode());
		List<String> rolelist = new ArrayList<String>();
		for (TblRole role : list) {
			rolelist.add(role.getName());
		}
		
		String type = null;
		
		if(rolelist.contains("普通用户")){
			type= "1";
		}else if(rolelist.contains("专家用户")){
			type= "2";
		}
		
		return type;
	}
	
	public String add(){
		//健康咨询分类
		List<TblJkzxfl> jkzxfllist = jkzxflser.getAllDate();
		super.setAttribute("jkzxfllist", jkzxfllist);

		return "add";
	}
	
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		jkzx = jkzxser.getOneById(Integer.parseInt(id));
		super.setAttribute("jkzx", jkzx);
		
		//将用户类型反馈到页面方便控制
		String type = this.getUserType();
		super.setAttribute("type", type);
		
		//健康咨询分类
		List<TblJkzxfl> jkzxfllist = jkzxflser.getAllDate();
		super.setAttribute("jkzxfllist", jkzxfllist);
		
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		jkzx = jkzxser.getOneById(Integer.parseInt(id));
		super.setAttribute("jkzx", jkzx);
		return "detail";
	}
	
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		jkzx.setZxsj(new Date());
		jkzx.setYjid(user.getId());
		
		if(null!=jkzx.getZjid()&&!"".equals(jkzx.getZjid())){//发送短信给专家
			TblUser zj = userser.getOneById(jkzx.getZjid());
			TblTsjl tsjl = new TblTsjl();
			tsjl.setName(zj.getRealname());
			tsjl.setPhone(zj.getPhone());
			String nr = zj.getRealname() + "专家，您好！"
					+ "共青团提醒您：有一位用户向您提出问题，请登录网站进行查看。";
			tsjl.setNr(nr);
			tsjl.setZt(0);
			tsjl.setFl(0);
			tsjl.setSj(new Date());
			tsjlser.save(tsjl);
		}
		
		jkzxser.save(jkzx);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康咨询 >> 健康咨询 ", "添加", "添加标题为“"+jkzx.getZxbt()+"”的数据", null);
	
		return "success";
	}
	
	public String update(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		String content = "修改";
		
		String type = this.getUserType();
		if("2".equals(type)){
			jkzx.setHfsj(new Date());
			jkzx.setZjid(user.getId());
			content = "咨询回复";
			
			TblUser yh = userser.getOneById(jkzx.getYjid());
			TblTsjl tsjl = new TblTsjl();
			tsjl.setName(yh.getRealname());
			tsjl.setPhone(yh.getPhone());
			String nr = yh.getRealname() + "，您好！"
					+ "共青团提醒您：专家已为您回复问题，请登录网站进行查看。";
			tsjl.setNr(nr);
			tsjl.setZt(0);
			tsjl.setFl(0);
			tsjl.setSj(new Date());
			tsjlser.save(tsjl);
		}
		
		jkzxser.update(jkzx);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康咨询 >> 健康咨询", content, "修改标题为“"+jkzx.getZxbt()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = jkzxser.getBtsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "医疗卫生 >> 健康咨询 >> 健康咨询", "删除", "删除标题为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		jkzxser.deleteById(id);
	}
	
	public void getZjs(){
		Gson gson = new Gson();
		String flid = super.getParameter("flid");
		List<TblUser> list = userser.getDataByHealthid(flid);
		super.setAjaxData(gson.toJson(list));
	}
}
