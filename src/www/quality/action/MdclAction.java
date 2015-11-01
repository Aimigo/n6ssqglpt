package www.quality.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import www.quality.model.TblDepartment;
import www.quality.model.TblMdcl;
import www.quality.model.TblMdfl;
import www.quality.model.TblRole;
import www.quality.model.TblUser;
import www.quality.util.ExcelExport;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MdclAction extends BasicAction implements ModelDriven<TblMdcl> {

	private TblMdcl mdcl = new TblMdcl();
	
	public TblMdcl getModel() {
		// TODO Auto-generated method stub
		return mdcl;
	}

	public String list(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户

		Pager page = this.get_page();
		
		String type = this.getUserType();
		if("1".equals(type)){
			page.putParam("userid", user.getId()+"");
		}else if("2".equals(type)){
			page.putParam("wg", user.getGridname());
		}else if("3".equals(type)){
			page.putParam("dkbm", user.getDepartmentid()+"");
		}else if("4".equals(type)){
			page.putParam("userid", user.getId()+"");
		}else if("5".equals(type)){
			
		}else if("6".equals(type)){
			
		}
		page.putParam("type", type);
		
		page = mdclser.getPagerByCriteria(page);
		super.setAttribute("_page", page);
		
		//列表头部提醒
		List<Object> headlist = mdclser.getSomeDataByPager(page); 
		super.setAttribute("headlist", headlist);
		
		List<TblMdfl> mdfllist = mdflser.getAllDate();
		super.setAttribute("mdfllist", mdfllist);
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
		
		if(rolelist.contains("信息采集员")){
			type= "1";
		}else if(rolelist.contains("网格管理员")){
			type= "2";
		}else if(rolelist.contains("职能部门")){
			type= "3";
		}else if(rolelist.contains("分管领导")){
			type= "4";
		}else if(rolelist.contains("领导")){
			type= "5";
		}else if(rolelist.contains("综治办")){
			type= "6";
		}
		
		return type;
	}
	
	public String add(){
		List<TblMdfl> mdfllist = mdflser.getAllDate();
		super.setAttribute("mdfllist", mdfllist);
		return "add";
	}
	
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		mdcl = mdclser.getOneById(Integer.parseInt(id));
		super.setAttribute("mdcl", mdcl);
		
		//将用户类型反馈到页面方便控制
		String type = this.getUserType();
		super.setAttribute("type", type);
		
		//部门信息
		List<TblDepartment> departmentlist = departmentser.getAllDate();
		super.setAttribute("departmentlist", departmentlist);
		
		//分管领导
		List<TblUser> userlist = userser.getUsersByRolename("分管领导");
		super.setAttribute("userlist", userlist);
		
		List<TblMdfl> mdfllist = mdflser.getAllDate();
		super.setAttribute("mdfllist", mdfllist);
		return "edit";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		mdcl = mdclser.getOneById(Integer.parseInt(id));
		super.setAttribute("mdcl", mdcl);
		return "detail";
	}
	
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		mdcl.setTxsj(new Date());
		mdcl.setCjrid(user.getId());
		mdcl.setWg(user.getGridname());
		
		if(null!=mdcl.getClyj())
			mdcl.setClsj(new Date());
		
		mdclser.save(mdcl);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 矛盾处理", "添加", "添加标题为“"+mdcl.getBt()+"”的数据", null);
	
		return "success";
	}
	
	public String update(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		
		String content = "修改";
		
		String isjj = super.getParameter("isjj");
		if("1".equals(isjj)){
			mdcl.setZt(4);
			mdcl.setClsj(new Date());
			content = "解决问题";
		}
		
		String type = this.getUserType();
		if("1".equals(type)){
			if("0".equals(isjj)){
				mdcl.setZt(0);
				content = "上报问题";
			}
		}else if("2".equals(type)){
			if("0".equals(isjj)){
				mdcl.setZt(1);
				content = "上报问题";
			}
			mdcl.setGlyid(user.getId());
		}else if("3".equals(type)){
			if("0".equals(isjj)){
				mdcl.setZt(2);
				content = "上报问题";
			}
			mdcl.setDkbmryid(user.getId());
		}else if("4".equals(type)){
			if("0".equals(isjj)){
				mdcl.setZt(3);
				content = "上报问题";
			}
			mdcl.setBmldid(user.getId());
		}else if("5".equals(type)){
			mdcl.setLdid(user.getId());
		}
		
		mdclser.update(mdcl);
		
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 矛盾处理", content, "修改标题为“"+mdcl.getBt()+"”的数据", null);
		
		return "success";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = mdclser.getBtsByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "社会民生 >> 矛盾处理", "删除", "删除标题为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		mdclser.deleteById(id);
	}
	
	public void exportExcel(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户

		Pager page = this.get_page();
		
		String type = this.getUserType();
		if("1".equals(type)){
			page.putParam("wg", user.getGridname());
		}else if("2".equals(type)){
			page.putParam("wg", user.getGridname());
		}else if("3".equals(type)){
			page.putParam("dkbm", user.getDepartmentid()+"");
		}else if("4".equals(type)){
			page.putParam("userid", user.getId()+"");
		}else if("5".equals(type)){
			
		}else if("6".equals(type)){
			
		}
		page.putParam("type", type);
		
		List<TblMdcl> list = mdclser.getListByCriteria(page);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		try {
			int rndm = (int) (Math.random() * 10000);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msexcel");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ URLEncoder.encode("矛盾处理" + rndm + ".xls", "utf-8"));
			
			ExcelExport<TblMdcl> ex = new ExcelExport<TblMdcl>();
			String[] headers = { "标题", "矛盾分类", "解决方式", "所属网格", "内容", "处理意见", "填写时间", "网格采集员", "处理时间",  "网格管理员", "职能部门", "分管领导", "领导" };
			String[] fields = { "bt", "flname", "jcfs", "gridname", "nr", "clyj", "txsj", "cjrname", "clsj", "glyname", "dkbmryname", "bmldname", "ldname" };
			ex.exportExcel(headers, list, response.getOutputStream(),fields);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
