package www.quality.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblCzfwgl;
import www.quality.model.TblGrid;
import www.quality.model.TblSyfwgl;
import www.quality.model.TblUser;
import www.quality.model.TblZzrxx;
import www.quality.util.Pager;
import www.quality.util.ZTree;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
*类的描述:该类是对应TblZzrxx的管理操作
*作者:李留洋
*创建日期:2013-01-24
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("Zztotype")
public class ZzrxxAction extends BasicAction implements ModelDriven<TblZzrxx> {
	
	private TblZzrxx model = new TblZzrxx();
	public TblZzrxx getModel() {
		return model;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblZzrxx的信息并分页展示
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
		p=zzrxxser.getPagerByCriteria(p);
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
		//网格信息
		List<TblGrid> gridlist = gridser.getAllDate();
		super.setAttribute("gridlist", gridlist);
		/*
		List<TblSyfwgl> list = syfwglser.getAllDate();
		setAttribute("syfwgl", list);*/
		List<TblCzfwgl> czfw = czfwglser.getAllDate();
		setAttribute("czfw",czfw);
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
		//保存数据
		zzrxxser.save(model);
		//保存特殊人员
		
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		//logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", "人员管理 >> 租住人信息管理", "添加", "", null);
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
		List<TblGrid> gridlist = gridser.getAllDate();
		super.setAttribute("gridlist", gridlist);
		
		List<TblSyfwgl> list = syfwglser.getAllDate();
		setAttribute("syfwgl", list);
		
		model = zzrxxser.getOneById(Integer.parseInt(id));
		super.setAttribute("model", model);
		TblSyfwgl syfwgl = syfwglser.getOneById(model.getCcfwid());
		setAttribute("syfw",syfwgl);
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
		zzrxxser.update(model);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", "人员管理 >> 租住人信息管理", "修改", "", null);
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
		zzrxxser.deleteById(id);
		//操作日志添加
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user"); // 当前登录的用户
		logser.getLog(user.getUsername(), "共青团农场社区管理平台>>人员管理 ", "人员管理 >> 租住人信息管理", "删除", "", null);
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
		model = zzrxxser.getOneById(Integer.parseInt(id));
		super.setAttribute("Zz", model);
		TblSyfwgl syfwgl = syfwglser.getOneById(model.getCcfwid());
		setAttribute("syfw",syfwgl);
		return "detail";
	}
	public String getSyfwTree(){
		return "pop2";
	}
	/*public void getFwtree(){
		String level = getParameter("level");
		String owner = getParameter("code");
		
		List<ZTree> treelist = new ArrayList<ZTree>();
		Map<String,String> param=new HashMap<String,String>();
		if(level!=null){
			String dw="";
			 if("2".equals(level)){
				param.put("dy", owner);
				dw="(室)";
				List<TblSyfwgl> ls=zzrxxser.getZtreeDate(param);
				for (TblSyfwgl ower: ls) {
					ZTree ztree = new ZTree();
					ztree.setId(ower.getId()+"");
					ztree.setpId("0");
					ztree.setName(ower.getShi()+dw);//(ower.getZhuang()+"(幢)"+ower.getDy()+"(单元)"+ower.getShi()+dw);
					ztree.setParent(false);
					treelist.add(ztree);
				}
			}else{
				if("-1".equals(level)){
				}else if("0".equals(level)){
					param.put("xqdz", owner);
					dw="(幢)";
				}else if("1".equals(level)){
					param.put("zhuang", owner);
					dw="(单元)";
				}
				List<String> lst=zzrxxser.getZtreeDate(param);
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
					ztree.setName(ower.trim()+dw);
					ztree.setParent(true);
					treelist.add(ztree);
				}
			}
			
		}

		
		Gson gson = new Gson();
		super.setAjaxData(gson.toJson(treelist));
	}*/
	public void getFwtree(){
		String owner = getParameter("code");
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		List<ZTree> treelist2=hjglser.getZtreeDate2(owner,user.getGridname());
		Gson gson = new Gson();
		super.setAjaxData(gson.toJson(treelist2));
		/*
		String level = getParameter("level");
		String owner = getParameter("code");
		if(owner==null){
			owner="0";
		}
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		String grid=user.getGridname();
		List<ZTree> treelist = new ArrayList<ZTree>();
		String dw="";
		String[] split = owner.split(",");
		split[0]=grid;
		 if("2".equals(level)){
			dw="(室)";
			List<TblSyfwgl> ls=hjglser.getZtreeDate(split);
			for (TblSyfwgl ower: ls) {
				ZTree ztree = new ZTree();
				ztree.setId(ower.getId()+"");
				ztree.setpId(owner);
				ztree.setName(ower.getShi()+dw);//(ower.getZhuang()+"(幢)"+ower.getDy()+"(单元)"+ower.getShi()+dw);
				ztree.setParent(false);
				treelist.add(ztree);
			}
		}else{
			List lst=hjglser.getZtreeDate(split);
			if(lst.get(0) instanceof TblSyfwgl){
				dw="(号)";
				List<TblSyfwgl> syfw=(List<TblSyfwgl>)lst;
				for (TblSyfwgl ower: syfw) {
					ZTree ztree = new ZTree();
					ztree.setId(ower.getId()+"");
					ztree.setpId(owner);
					ztree.setName(ower.getZhuang()+dw);//(ower.getZhuang()+"(幢)"+ower.getDy()+"(单元)"+ower.getShi()+dw);
					ztree.setParent(false);
					treelist.add(ztree);
				}
			}else{
				if("0".equals(level)){
					dw="(幢)";
				}else if("1".equals(level)){
					dw="(单元)";
				}
				List<String> str=(List<String>)lst;
				for (String ower: str) {
					if(ower==null){
						continue;
					}
					if("".equals(ower.trim())){
						continue;
					}
					ZTree ztree = new ZTree();
					ztree.setId(owner+","+ower);
					ztree.setpId(owner);
					ztree.setName(ower.trim()+dw);
					ztree.setParent(true);
					treelist.add(ztree);
				}
			}
		}
		Gson gson = new Gson();
		super.setAjaxData(gson.toJson(treelist));*/
	}
}
