package www.quality.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblUser;
import www.quality.model.TblLog;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LogAction extends BasicAction implements ModelDriven<TblLog> {

	private TblLog log = new TblLog();

	public String list() throws Exception {
		String nr = getParameter("nr");
		String cond = getParameter("cond");
		String stime = getParameter("stime");
		String etime = getParameter("etime");
		String col = getParameter("col");
		String px = getParameter("px");
		String pageRows = getParameter("pageRows");
		
		Pager p = this.get_page();
		
		if (null != pageRows && !"".equals(pageRows)) {
			//p.setPageRows(Integer.parseInt(pageRows));
		}

		p = logser.getPagerByCriteria(p, cond, nr, stime, etime, col, px);
		if (px == null && "".equals(px)) {
			px = "desc";
		}
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
		logser.deleteLog(id);
		//操作日志添加
		//TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		//logser.getLog(user.getUsername(), "共青团农场社区管理平台", "系统管理 >> 日志管理 >> 操作日志管理", "删除", "", null);
		return "tolist";
	}

	public String detail() {
		TblLog log = logser.findById(Integer.parseInt(getParameter("id")));
		setAttribute("log", log);
		return "detail";
	}

	public TblLog getModel() {
		return log;
	}

}
