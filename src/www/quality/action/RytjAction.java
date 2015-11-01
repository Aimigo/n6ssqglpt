package www.quality.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import www.quality.model.TblCbxx;
import www.quality.model.TblJtxx;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsrysj;
import www.quality.model.TblJmxx;
import www.quality.model.TblUser;
import www.quality.model.TblXsxx;
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
public class RytjAction extends BasicAction implements ModelDriven<TblJmxx> {
	
	private TblJmxx model = new TblJmxx();
	TblJtxx jtxx=new TblJtxx();
	TblCbxx cbxx=new TblCbxx();
	TblXsxx xsxx=new TblXsxx();
	//private static final String URL = "Jmxx_list.action";
	TblTsrysj sj = new TblTsrysj();
	public TblJmxx getModel() {
		// TODO Auto-generated method stub
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
		TblUser user = (TblUser) ActionContext.getContext().getSession().get("user");
		String grid=user.getGridname();
		List<Object[]> list=new ArrayList<Object[]>();
		for(int i=0;i<35;i+=5){
			Object[] obj=new Object[2];
			Integer j=i+5;
			if(i==35){
				obj[0]=(i+1)+"岁以上";
			}else if(i==0){
				obj[0]="0~"+j+"岁";
			}else{
				obj[0]=(i+1)+"~"+j+"岁";
			}
			
			Integer count=jmxxser.getCountByAge(i,j,grid);
			obj[1]=count;
			list.add(obj);
		}
		Integer count1=jmxxser.getCountByAge(35,65,grid);
		list.add(new Object[]{"36~65岁",count1});
		Integer count2=jmxxser.getCountByAge(65,80,grid);
		list.add(new Object[]{"65~80岁",count2});
		Integer count3=jmxxser.getCountByAge(80,200,grid);
		list.add(new Object[]{"80岁以",count3});
		setAttribute("agec", new Gson().toJson(list));
		setAttribute("mage",list);
		List lst=tsryser.getTsryPice(grid);
		setAttribute("tsrylst", lst);
		setAttribute("tsrybt", new Gson().toJson(lst));
		//统计户籍人口和非户籍人口  按月和日统计
		List json3=jmxxser.getTjData3(grid);
		setAttribute("data3", json3);
		//统计
		List json4=jmxxser.getTjData4();
		setAttribute("data4", json4);
		setAttribute("json4", new Gson().toJson(json4));
		return "success";
	}

}
