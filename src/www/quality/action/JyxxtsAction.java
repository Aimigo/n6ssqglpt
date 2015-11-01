package www.quality.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblTsjl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class JyxxtsAction extends BasicAction implements ModelDriven<TblTsjl> {

	private TblTsjl tsjl = new TblTsjl();
	
	public TblTsjl getModel() {
		// TODO Auto-generated method stub
		return tsjl;
	}

	public String list(){
		Pager p = this.get_page();
		p=tsjlser.getPagerByCriteria(p,1);
		setAttribute("_page", p);
		return "list";
	}
	
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		tsjl = tsjlser.getOneById(Integer.parseInt(id));
		super.setAttribute("tsjl", tsjl);
		return "detail";
	}
	
	public void delete(){
		String id = getParameter("id");
		
		//操作日志添加
		List<String> list = tsjlser.getNamesByIds(id);
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "就业服务 >> 推送记录", "删除", "删除推送对象为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		tsjlser.deleteById(id);
	}
}
