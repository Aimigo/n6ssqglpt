package www.quality.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblUser;
import www.quality.model.TblWhfl;
import www.quality.model.TblWhyl;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 *类的描述:该类是对应TblWhyl的管理操作
 *作者:李留洋
 *创建日期:2013-01-24
 *修改人
 *修改日期
 *修改原因描述
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class WhylAction extends BasicAction implements ModelDriven<TblWhyl> {

	private TblWhyl whyl = new TblWhyl();
	//private static final String URL = "whyl_list.action";

	public TblWhyl getModel() {
		// TODO Auto-generated method stub
		return whyl;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblWhyl的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
		Pager p = this.get_page();
		p=whylser.getPagerByCriteria(p);
		setAttribute("_page", p);
		return "list";
	}

	/**
	 * 函  数  名 :add
	 * 功能描述：跳转到新增页面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String add(){

		return "add";
	}

	/**
	 * 函  数  名 :save
	 * 功能描述：用于新增
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public String save(){
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "数码文化娱乐信息系统>>数码文化娱乐资源>>数码文化娱乐管理", "添加", "添加了标题为“"+whyl.getBt()+"”的数据", null);
		whyl.setScsj(new Date());
		whyl.setUserid(user.getId());
		whylser.save(whyl);
		return SUCCESS;
	}

	/**
	 * 函  数  名 :edit
	 * 功能描述：跳转到修改界面
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String edit(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入修改页面
		}
		whyl = whylser.getOneById(Integer.parseInt(id));
		super.setAttribute("whyl", whyl);
		return "edit";
	}

	/**
	 * 函  数  名 :update
	 * 功能描述：用于修改
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String update(){
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "数码文化娱乐信息系统>>数码文化娱乐资源>>数码文化娱乐管理", "修改", "修改了标题为“"+whyl.getBt()+"”的数据", null);
		whyl.setUserid(user.getId());
		whylser.update(whyl);
		return SUCCESS;
	}

	/**
	 * 函  数  名 :delete
	 * 功能描述：用于批量或单独删除
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String delete(){
		String id = getParameter("ids");
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				 whyl=whylser.getOneById(Integer.parseInt(string));
				//操作日志添加
				TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
				logser.getLog(user.getUsername(), "共青团农场社区管理平台", "数码文化娱乐信息系统>>数码文化娱乐资源>>数码文化娱乐管理", "删除", "删除了标题为“"+whyl.getBt()+"”的数据", null);
				whylser.deleteById(string);
			}
		}
		return "success";
	}

	/**
	 * 函  数  名 :detail
	 * 功能描述：用于跳转到查看
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String detail(){
		String id = super.getParameter("id");
		if(null==id || "".equals(id)){
			return "success";	//如果ID为空，则暂时跳到list页面，不进入查看页面
		}
		whyl = whylser.getOneById(Integer.parseInt(id));
		super.setAttribute("whyl", whyl);
		return "detail";
	}

	public String tree(){
		List<TblWhfl> fllist=whflser.getAllDate();
		setAttribute("fllist", fllist);
		return "tree";
	}
}
