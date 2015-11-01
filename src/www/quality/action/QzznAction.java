package www.quality.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import www.quality.model.TblGrjl;
import www.quality.model.TblQyzp;
import www.quality.model.TblTsjl;
import www.quality.util.Pager;

@SuppressWarnings("serial")
public class QzznAction extends BasicAction {

	public String list() throws ParseException{
		Pager page = this.get_page();
		
		String type = super.getParameter("dtype");
		if(null==type || "".equals(type)){
			type = "1";
		}
		page.putParam("type", type);
		
		if("1".equals(type))
			this.getQyzp(page);
		else if("2".equals(type))
			this.getGrjl(page);
		
		return "list";
	}
	
	/**
	 * 查询企业智能匹配
	 */
	public void getQyzp(Pager page) throws ParseException{
		page=qyzpser.getPagerByCriteria(page);
		/*
		List<TblQyzp> list = page.getData();
		Map<Integer,List<TblGrjl>> map = new HashMap<Integer, List<TblGrjl>>();
		for (TblQyzp qyzp : list) {
			List<TblGrjl> grjls = grjlser.getDataByQyzp(qyzp);
			map.put(qyzp.getId(), grjls);
		}
		super.setAttribute("map", map);
		*/
		super.setAttribute("_page", page);
	}
	
	/**
	 * 查询个人智能匹配
	 */
	public void getGrjl(Pager page){
		page=grjlser.getPagerByCriteria(page);
		/*
		List<TblGrjl> list = page.getData();
		Map<Integer,List<TblQyzp>> map = new HashMap<Integer, List<TblQyzp>>();
		for (TblGrjl grjl : list) {
			List<TblQyzp> qyzps = qyzpser.getDataByGrjl(grjl);
			map.put(grjl.getId(), qyzps);
		}
		super.setAttribute("map", map);
		*/
		super.setAttribute("_page", page);
	}
	
	public String pplist(){
		String id = super.getParameter("id");
		String type = super.getParameter("type");
		if("1".equals(type)){
			TblQyzp qyzp = qyzpser.getOneById(Integer.parseInt(id));
			List<TblGrjl> grjls = grjlser.getDataByQyzp(qyzp);
			super.setAttribute("grjls", grjls);
		}else if("2".equals(type)){
			TblGrjl grjl = grjlser.getOneById(Integer.parseInt(id));
			List<TblQyzp> qyzps = qyzpser.getDataByGrjl(grjl);
			super.setAttribute("qyzps", qyzps);
		}
		super.setAttribute("id", id);
		super.setAttribute("type", type);
		return "pplist";
	}
	
	public void sendMessage(){
		String type = super.getParameter("type");
		String qyid = super.getParameter("qyid");
		String grid = super.getParameter("grid");
		
		if("1".equals(type)){//企业群发
			TblQyzp qyzp = qyzpser.getOneById(Integer.parseInt(qyid));
			
			List<TblGrjl> grjls = grjlser.getDataByQyzp(qyzp);
			for (TblGrjl grjl : grjls) {
				TblTsjl tsjl = new TblTsjl();
				tsjl.setName(grjl.getXm());
				tsjl.setPhone(grjl.getLldh());
				String nr = "亲爱的"+grjl.getXm()
						+ "，共青团劳资科提醒您：有一份适合你的职位:"+qyzp.getQymc()
						+ "正在招聘，如需了解详情请致电："+ qyzp.getLxr()
						+ " 电话："+ qyzp.getLxdh();
				tsjl.setNr(nr);
				tsjl.setZt(0);
				tsjl.setFl(1);
				tsjl.setSj(new Date());
				tsjlser.save(tsjl);
			}
		}else if("2".equals(type)){//个人单发
			TblQyzp qyzp = qyzpser.getOneById(Integer.parseInt(qyid));
			TblGrjl grjl = grjlser.getOneById(Integer.parseInt(grid));
			
			TblTsjl tsjl = new TblTsjl();
			tsjl.setName(grjl.getXm());
			tsjl.setPhone(grjl.getLldh());
			String nr = "亲爱的"+grjl.getXm()
					+ "，共青团劳资科提醒您：有一份适合你的职位:"+qyzp.getQymc()
					+ "正在招聘，如需了解详情请致电："+ qyzp.getLxr()
					+ " 电话："+ qyzp.getLxdh();
			tsjl.setNr(nr);
			tsjl.setZt(0);
			tsjl.setFl(1);
			tsjl.setSj(new Date());
			
			tsjlser.save(tsjl);
		}else if("3".equals(type)){//个人多发
			TblGrjl grjl = grjlser.getOneById(Integer.parseInt(grid));
			
			List<TblQyzp> qyzps = qyzpser.getDataByGrjl(grjl); 
			for (TblQyzp qyzp : qyzps) {
				TblTsjl tsjl = new TblTsjl();
				tsjl.setName(grjl.getXm());
				tsjl.setPhone(grjl.getLldh());
				String nr = "亲爱的"+grjl.getXm()
						+ "，共青团劳资科提醒您：有一份适合你的职位:"+qyzp.getQymc()
						+ "正在招聘，如需了解详情请致电："+ qyzp.getLxr()
						+ " 电话："+ qyzp.getLxdh();
				tsjl.setNr(nr);
				tsjl.setZt(0);
				tsjl.setFl(1);
				tsjl.setSj(new Date());
				tsjlser.save(tsjl);
			}
			
		}
	}
}
