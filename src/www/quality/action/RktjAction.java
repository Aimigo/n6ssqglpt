package www.quality.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblJmxx;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsrysj;
import www.quality.model.TblUser;
import www.quality.util.Pager;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
*类的描述:该类是对应TblJmxx的管理操作
*作者:李留洋
*创建日期:2013-01-24
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("serial")
@Controller
@Scope("Jmxxtotype")
public class RktjAction extends BasicAction implements ModelDriven<TblJmxx> {
	
	private TblJmxx model = new TblJmxx();
	TblTsrysj sj = new TblTsrysj();
	public TblJmxx getModel() {
		return model;
	}

	/**
	 * 函  数  名 :list
	 * 功能描述： 无条件或有条件的查询TblJmxx的信息并分页展示
	 * 参数描述：  
	 * 返回值  : String类型的数据，跳转到所对应的页面
	 * 创 建 人: 李留洋
	 * 日      期: 2013-01-24
	 * 修 改 人: 
	 * 日    期:
	 */
	public String list(){
		Pager p = this.get_page();
		p.setPageRows(12);
		p=jmxxser.getPagerByCriteria(p);
		setAttribute("_page", p);
		List<TblTsryfl> fl=jmxxser.getAllTrsy();
		setAttribute("tsryfl", fl);
		return "list";
	}
	public String execute(){
		//统计 每月每日新增量,按网格统计
		List json=jmxxser.getTjData();
		//List json=lcrkser.getTjData();
		setAttribute("data1", json);
		//统计 一年的数据
		List json2=jmxxser.getTjData2();
		setAttribute("data2", json2);
		//统计户籍人口和非户籍人口  按月和日统计
		/*TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		String grid=user.getGridname();
		List json3=jmxxser.getTjData3(grid);
		setAttribute("data3", json3);*/
		
		return "success";
	}
	public void lcrytj(){
		String type = getParameter("type");
		String data = getParameter("time");
		String grid = getParameter("grid");
		String list=jmxxser.getTjData5(type,data,grid);
		//setAttribute("data5",list);
		setAjaxData(list);
	}


}
