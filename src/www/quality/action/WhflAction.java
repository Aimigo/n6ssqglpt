package www.quality.action;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblUser;
import www.quality.model.TblWhfl;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 *类的描述:该类是对应TblWhfl的管理操作
 *作者:李留洋
 *创建日期:2013-01-24
 *修改人
 *修改日期
 *修改原因描述
 */
@SuppressWarnings("serial")
@Controller
@Scope("whfltotype")
public class WhflAction extends BasicAction implements ModelDriven<TblWhfl> {

	private TblWhfl whfl = new TblWhfl();
	//private static final String URL = "whfl_list.action";

	public TblWhfl getModel() {
		// TODO Auto-generated method stub
		return whfl;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblWhfl的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
		String name = getParameter("name");
		
		Pager p = this.get_page();
		p.setPageRows(30);
		if (name != null) {
			p = whflser.getPagerByCriteria(p, name);
		} else {
			p = whflser.getPagerByCriteria(p, null);
		}
		p.putParam("name", name);
		setAttribute("_page", p);
		//查询top5分类中有多少数据
        Map topmap=whylser.getTop();
        setAttribute("topmap", topmap);
        JSONArray json = JSONArray.fromObject(topmap);  
       // System.out.println(json);  
       // resp.setContentType("application/json,charset=utf-8");  
       // out.print(json.toString());
        setAttribute("topjson",json.toString());
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
	 */
	public String save(){

		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "数码文化娱乐信息系统>>数码文化娱乐资源>>文化娱乐资源分类", "添加","添加了名称为“"+whfl.getName()+"”的数据", null);
		//保存数据
		whflser.save(whfl);
		return "success";
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
		whfl = whflser.getOneById(Integer.parseInt(id));
		super.setAttribute("whfl", whfl);
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
		logser.getLog(user.getUsername(), "共青团农场社区管理平台", "数码文化娱乐信息系统>>数码文化娱乐资源>>文化娱乐资源分类", "修改", "修改了名称为“"+whfl.getName()+"”的数据", null);
		whflser.update(whfl);
		return "success";
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
		String id = getParameter("id");
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				 whfl=whflser.getOneById(Integer.parseInt(string));
				//操作日志添加
				TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
				logser.getLog(user.getUsername(), "共青团农场社区管理平台", "数码文化娱乐信息系统>>数码文化娱乐资源>>文化娱乐资源分类", "删除","删除了名称为“"+whfl.getName()+"”的数据", null);
				whflser.deleteById(string);
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
		whfl = whflser.getOneById(Integer.parseInt(id));
		super.setAttribute("whfl", whfl);
		return "detail";
	}

	/**
	 * 验证是否重复
	 * 0 不重复
	 * 1 重复
	 * @throws ParseException 
	 */
	public void isRepeat() throws ParseException{
		List<TblWhfl> list = whflser.getByName(whfl.getName());
		if(null==list||list.size()<=0){
			super.setAjaxData("0");
		}else{
			super.setAjaxData("1");
		}
	}

}
