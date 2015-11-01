package www.quality.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import www.quality.model.TblGrid;
import www.quality.model.TblJmxx;
import www.quality.model.TblTsjl;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class XxtzAction extends BasicAction implements ModelDriven<TblTsjl> {

	private TblTsjl tsjl = new TblTsjl();
	
	public TblTsjl getModel() {
		// TODO Auto-generated method stub
		return tsjl;
	}

	public String list(){
		Pager p = this.get_page();
		p=tsjlser.getPagerByCriteria(p,2);
		setAttribute("_page", p);
		return "list";
	}
	public String add(){
		//网格信息
		/*List<TblGrid> gridlist = gridser.getAllDate();
		super.setAttribute("gridlist", gridlist);
		
		List<TblSyfwgl> list = syfwglser.getAllDate();
		setAttribute("syfwgl", list);*/
		return "add";
	}
	public String save(){
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		String sswg=getParameter("sswg");
		String nr=getParameter("nr");
		List<TblJmxx> wgry=jmxxser.findByHql("from TblJmxx where wangge='"+sswg+"'");
		if(wgry!=null&&wgry.size()>0){//发送短信给网格人员
			for(TblJmxx jmxx:wgry){
			TblTsjl tsjl = new TblTsjl();
			tsjl.setName(jmxx.getName());
			tsjl.setPhone(jmxx.getPhone());
			tsjl.setNr(nr);
			tsjl.setZt(0);
			tsjl.setFl(2);
			tsjl.setSj(new Date());
			tsjlser.save(tsjl);
			}
		}
		//操作日志添加
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "人员管理>> 信息通知  ", "添加", "发送了“"+sswg+"”的通知信息", null);
		return "success";
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
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "人员管理 >> 信息通知", "删除", "删除推送对象为“"+StringUtils.join(list,'、')+"”的数据", null);
		
		tsjlser.deleteById(id);
	}
}
