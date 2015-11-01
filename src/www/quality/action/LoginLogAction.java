package www.quality.action;

import www.quality.model.TblLoginLog;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LoginLogAction extends BasicAction implements
		ModelDriven<TblLoginLog> {

	private TblLoginLog loginlog = new TblLoginLog();

	public String list() throws Exception {
		String cond = super.getParameter("cond");
		String nr = getParameter("nr");
		String stime = getParameter("stime");
		String etime = getParameter("etime");
		String col = getParameter("col");//排序的列
		String px = getParameter("px");//排序方式
		String pageRows = getParameter("pageRows");
		
		if (null == px && "".equals(px)) {
			px = "desc";
		}

		Pager p = this.get_page();
		if (null != pageRows && !"".equals(pageRows)) {
			//p.setPageRows(Integer.parseInt(pageRows));
		}

		p = loginlogser.getPagerByCriteria(p, cond, nr, stime, etime, col, px);
		
		if ("desc".equals(px)) {
			px = "asc";
		} else {
			px = "desc";
		}
		
		p.putParam("col", col);
		p.putParam("px", px);
		p.putParam("nr", nr);
		p.putParam("cond", cond);
		p.putParam("stime", stime);
		p.putParam("etime", etime);
		setAttribute("_page", p);
		return "list";
	}

	public String delete() {
		String id = super.getParameter("id");
		loginlogser.deleteLoginlog(id);
		//操作日志添加
		//TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		//logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 日志管理 >> 登录日志管理", "删除", "", null);
		return "success";
	}

	public String detail() {
		TblLoginLog loginlog = loginlogser.findById(Integer.parseInt(getParameter("id")));
		setAttribute("loginlog", loginlog);
		return "detail";
	}

	public TblLoginLog getModel() {
		return loginlog;
	}

}
