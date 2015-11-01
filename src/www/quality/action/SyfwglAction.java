package www.quality.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblCzfwgl;
import www.quality.model.TblRegion;
import www.quality.model.TblUser;
import www.quality.model.TblSyfwgl;
import www.quality.model.TblZz;
import www.quality.util.Pager;
import www.quality.util.ZTree;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
*类的描述:该类是对应TblSyfwgl的管理操作
*作者:李留洋
*创建日期:2013-01-24
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("Syfwgltotype")
public class SyfwglAction extends BasicAction implements ModelDriven<TblSyfwgl> {
	
	private TblSyfwgl model = new TblSyfwgl();
	public TblSyfwgl getModel() {
		return model;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblSyfwgl的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
		Pager p = this.get_page();
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		p.putParam("grid", user.getGridname());
		p=syfwglser.getPagerByCriteria(p);
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
		//List<TblZz> list = zzser.getAllDate();
		//setAttribute("zzxx", list);
		return "add";
	}
	public String getZzList(){
		return "pop";
		/*List<TblZz> list = zzser.getAllDate();
		//setAttribute("zzxx", list);
		///setAttribute("zzxx",new Gson().toJson(list));
		setAjaxData(new Gson().toJson(list));*/
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
		//保存数据
		syfwglser.save(model);
		//保存特殊人员
		Boolean isCzfw = model.getIsCzfw();
		if(isCzfw){
			TblCzfwgl czfwgl = new TblCzfwgl();
			Integer lyid = model.getLyid();
			TblZz zz = zzser.getOneById(lyid);
			//czfwgl.setCzr(model.get);
			String grid = zz.getGrid();
			czfwgl.setSswg(grid);
			czfwgl.setSyfwid(model.getId());
			czfwgl.setCzr(model.getCqr());
			czfwglser.save(czfwgl);
		}
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理", "人员管理 >> 实有房屋管理", "添加", "添加了实有房屋名为“"+model.getZhuang()+"”的房屋", null);
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
		model = syfwglser.getOneById(Integer.parseInt(id));
		super.setAttribute("model", model);
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
		//model.get
		syfwglser.update(model);
		Boolean isCzfw = model.getIsCzfw();
		if(isCzfw){
			TblCzfwgl czfwgl = new TblCzfwgl();
			List list = czfwglser.findByHql("from TblCzfwgl where syfwid='"+model.getId()+"'");
			if(list!=null&&list.size()>0){
				czfwgl=(TblCzfwgl)list.get(0);
			}
			Integer lyid = model.getLyid();
			TblZz zz = zzser.getOneById(lyid);
			String grid = zz.getGrid();
			czfwgl.setSswg(grid);
			czfwgl.setCzr(model.getCqr());
			czfwgl.setSyfwid(model.getId());
			czfwglser.update(czfwgl);
		}else{
			List list = czfwglser.findByHql("from TblCzfwgl where syfwid='"+model.getId()+"'");
			if(list!=null&&list.size()>0){
				TblCzfwgl czfwgl=(TblCzfwgl)list.get(0);
				czfwglser.delete(czfwgl.getId());
			}
			
		}
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理", "人员管理 >> 实有房屋管理", "修改", "修改了实有房屋信息名为“"+model.getZhuang()+"”的数据", null);
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
		String id = getParameter("ids");
		String name=syfwglser.deleteById(id);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理", "人员管理 >> 实有房屋管理", "删除", "删除了实有房屋名为“"+name+"”的数据", null);
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
		model = syfwglser.getOneById(Integer.parseInt(id));
		super.setAttribute("Syfwgl", model);
		return "detail";
	}
	public void getTree(){
		String level = getParameter("level");
		String owner = getParameter("code");
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		String grid=user.getGridname();
		List<ZTree> treelist = new ArrayList<ZTree>();
		if(level!=null&&"1".equals(level)){
			List<String> lst=zzser.getAllOwners(grid);
			for (String ower: lst) {
				if(ower==null){
					continue;
				}
				if("".equals(ower.trim())){
					continue;
				}
				ZTree ztree = new ZTree();
				ztree.setId(ower);
				ztree.setpId("0");
				ztree.setName(ower.trim());
				ztree.setParent(true);
				treelist.add(ztree);
			}
		}else{
			List<TblZz> list = zzser.getByOwner(owner,grid);
			for (TblZz reg: list) {
				if(reg.getName()==null){
					continue;
				}
				if("".equals(reg.getName().trim())){
					continue;
				}
				ZTree ztree = new ZTree();
				ztree.setId(reg.getObjectid()+"");
				ztree.setpId(owner);
				ztree.setName(reg.getName());
				ztree.setParent(false);
				treelist.add(ztree);
			}
		}
		
		Gson gson = new Gson();
		super.setAjaxData(gson.toJson(treelist));
	}
}
