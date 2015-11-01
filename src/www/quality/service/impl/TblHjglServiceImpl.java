package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblHjglDao;
import www.quality.dao.TblTsryflDao;
import www.quality.dao.TblTsryflzdDao;
import www.quality.dao.TblTsrysjDao;
import www.quality.model.TblCzfwgl;
import www.quality.model.TblHjgl;
import www.quality.model.TblSyfwgl;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.model.TblZz;
import www.quality.service.TblHjglService;
import www.quality.util.Pager;
import www.quality.util.ZTree;


/**
*类的描述:实现TblHjglService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblHjglService.TBLHJGL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblHjglServiceImpl implements TblHjglService {

	@Resource(name=TblHjglDao.TBLHJGL_DAO_IMPL)
	private TblHjglDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblHjgl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblHjgl obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblHjgl t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblHjgl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblHjgl.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblHjgl getOneById(Integer id) {		
		return dao.findById(id);
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblHjgl.class,"R");
		Map<String, String> params = p.getParams();
		String num=params.get("num");
		if(null!=num&&!"".equals(num)){
			criteria.add(Restrictions.like("hkbh", num,MatchMode.ANYWHERE));
		}
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("hzname", name,MatchMode.ANYWHERE));
		}
		String grid = params.get("grid");
		if(grid!=null&&!"".equals(grid)){
			DetachedCriteria regDc = DetachedCriteria
					.forClass(TblZz.class);
			regDc.setProjection(Property.forName("objectid"));
			String[] split = grid.split(",");
			LogicalExpression or3=null;
			for(String gd:split){
				gd=gd.trim();
				if("".equals(gd)){
					continue;
				}
				if(or3==null){
					or3 = Restrictions.or(Restrictions.like("grid", gd,MatchMode.ANYWHERE), Restrictions.eq("name", "abcdefvsdfdfss"));
				}else{
					or3=Restrictions.or(Restrictions.like("grid",gd, MatchMode.ANYWHERE), or3);
				}
			}
			if(or3!=null){
				regDc.add(or3);
			}
			DetachedCriteria syfwDc = DetachedCriteria
					.forClass(TblSyfwgl.class);
			syfwDc.setProjection(Property.forName("id"));
			syfwDc.add(Property.forName("lyid").in(regDc));
			criteria.add(Property.forName("syfwid").in(syfwDc));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("R.id"));
	}
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblHjgl.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		String hkbh="";
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				TblHjgl hjgl = dao.findById(Integer.parseInt(string));
				if(hjgl!=null){
					hkbh+=" "+hjgl.getHkbh();
				}
				this.delete(Integer.parseInt(string));//逐个删除
			}
		}
		return hkbh;
	}

	public List<TblHjgl> getByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblHjgl.class,"R");
		
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("hzname", name,MatchMode.ANYWHERE));
		}
		//criteria.addOrder(Order.desc("date"));
		return dao.findByCriteria(criteria);
	}
	public List<TblHjgl> getHjTree(Map<String, String> param){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblHjgl.class,"R");
		String hzname=param.get("hzname");
		if(null!=hzname&&!"".equals(hzname)){
			criteria.add(Restrictions.like("hzname", hzname,MatchMode.ANYWHERE));
		}
		String grid = param.get("grid");
		if(grid!=null&&!"".equals(grid)){
			DetachedCriteria regDc = DetachedCriteria
					.forClass(TblZz.class);
			regDc.setProjection(Property.forName("objectid"));
			String[] split = grid.split(",");
			LogicalExpression or3=null;
			for(String gd:split){
				gd=gd.trim();
				if("".equals(gd)){
					continue;
				}
				if(or3==null){
					or3 = Restrictions.or(Restrictions.like("grid", gd,MatchMode.ANYWHERE), Restrictions.eq("name", "abcdefvsdfdfss"));
				}else{
					or3=Restrictions.or(Restrictions.like("grid",gd, MatchMode.ANYWHERE), or3);
				}
			}
			if(or3!=null){
				regDc.add(or3);
			}
			DetachedCriteria syfwDc = DetachedCriteria
					.forClass(TblSyfwgl.class);
			syfwDc.setProjection(Property.forName("id"));
			syfwDc.add(Property.forName("lyid").in(regDc));
			criteria.add(Property.forName("syfwid").in(syfwDc));
		}
		return dao.findByCriteria(criteria);
	}
	public List<TblHjgl> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblHjgl.class,"R");
		if(null!=date&&!"".equals(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			//当前月的最后一天 
			cal.set(Calendar.DATE, 1);  
			cal.roll(Calendar.DATE, -1);  
	        Date endTime=cal.getTime();  
	        String endTimeStr=sdf.format(endTime)+" 23:59:59";
	        //--当前月的第一天            
	        cal.set(Calendar.DAY_OF_MONTH, 1);   
	        Date beginTime=cal.getTime();
	        String beginTimeStr=sdf.format(beginTime)+" 00:00:00";
	        
			criteria.add(Restrictions.ge("date",  df.parse(beginTimeStr)));
			criteria.add(Restrictions.le("date", df.parse(endTimeStr)));
		}
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		return dao.findByCriteria(criteria);
	}
	public List findByHql(String hql){
		List list = dao.findByHql(hql);
		return list;
	}
	public List getZtreeDate(Map<String,String> param){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(TblSyfwgl.class,"R");
		String dy = param.get("dy");
		if(dy!=null){
			//criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("shi"), "shi")));
			criteria.add(Restrictions.eq("dy",  dy));
		}else{
			String zhuang=param.get("zhuang");
			if(zhuang!=null){
				criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("dy"), "dy")));
				criteria.add(Restrictions.eq("zhuang",  zhuang));
			}else{
				String xqdz=param.get("xqdz");
				if(xqdz!=null){
					criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("zhuang"), "zhuang")));
					criteria.add(Restrictions.eq("xqdz",  xqdz));	
				}else{
					criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("xqdz"), "xqdz")));	
				}
			}
			
		}
		
		List list = dao.findByCriteria(criteria);
		return list;
	}
	public List getZtreeDate(String[] split){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblSyfwgl.class,"R");
		if(split.length==1){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("xqdz"), "xqdz")));	
		}else if(split.length==2){
			List list = dao.findByHql("from TblSyfwgl where xqdz='"+split[1].trim()+"' and fwlx like '%平房%'");
			if(list.size()>0){
				criteria.add(Restrictions.eq("xqdz",  split[1].trim()));
			}else{
				criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("zhuang"), "zhuang")));
				criteria.add(Restrictions.eq("xqdz",  split[1].trim()));	
			}
		}else if(split.length==3){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("dy"), "dy")));
			criteria.add(Restrictions.eq("zhuang",  split[2].trim()));
			criteria.add(Restrictions.eq("xqdz",  split[1].trim()));
		}else if(split.length==4){
			criteria.add(Restrictions.eq("dy",  split[3].trim()));
			criteria.add(Restrictions.eq("zhuang",  split[2].trim()));
			criteria.add(Restrictions.eq("xqdz",  split[1].trim()));
		}
		if(split[0]!=null&&!"".equals(split[0])){
			DetachedCriteria regDc = DetachedCriteria.forClass(TblZz.class);
			regDc.setProjection(Property.forName("objectid"));
			String[] split2 = split[0].split(",");
			LogicalExpression or3=null;
			for(String gd:split2){
				gd=gd.trim();
				if("".equals(gd)){
					continue;
				}
				if(or3==null){
					or3 = Restrictions.or(Restrictions.like("grid", gd,MatchMode.ANYWHERE), Restrictions.eq("name", "abcdefvsdfdfss"));
				}else{
					or3=Restrictions.or(Restrictions.like("grid",gd, MatchMode.ANYWHERE), or3);
				}
			}
			if(or3!=null){
				regDc.add(or3);
			}
			criteria.add(Property.forName("lyid").in(regDc));
		}
		List list = dao.findByCriteria(criteria);
		return list;
	}
	public List<ZTree> getZtreeDate2(String owner,String wg){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblSyfwgl.class,"R");//查询楼房
		DetachedCriteria pzcriteria = DetachedCriteria.forClass(TblSyfwgl.class,"S");//查询平房
		//所属网格的楼宇
		if(wg!=null){
			String[] wges = wg.split(",");
			DetachedCriteria zzDc = DetachedCriteria.forClass(TblZz.class);
			zzDc.setProjection(Property.forName("objectid"));
			Disjunction dis=Restrictions.disjunction();
			for(String wgs:wges){
				if(!"".equals(wgs.trim())){
					dis.add(Restrictions.like("grid",wgs.trim(), MatchMode.ANYWHERE));
				}
			}
			zzDc.add(dis);
			criteria.add(Property.forName("lyid").in(zzDc));
			//pzcriteria.add(Property.forName("lyid").in(zzDc));
		}
		//
		String dw="";
		List<ZTree> treelist = new ArrayList<ZTree>();
		if(owner==null||"".equals(owner.trim())){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("xqdz"), "xqdz")));	
		}else{
			String[] split = owner.split(",");
			if(split.length==1){
				dw="(幢)";
				
				pzcriteria.add(Restrictions.eq("xqdz",  split[0].trim()));
				pzcriteria.add(Restrictions.like("fwlx",  "平房", MatchMode.ANYWHERE));
				List syfwpf = dao.findByCriteria(pzcriteria);
				for(Object fw:syfwpf){
					TblSyfwgl syfw=(TblSyfwgl)fw;
					if(syfw.getZhuang()==null){
						continue;
					}
					ZTree ztree = new ZTree();
					ztree.setId(syfw.getId()+"");
					ztree.setpId(owner);
					ztree.setName(syfw.getZhuang()+"(号)");//(ower.getZhuang()+"(幢)"+ower.getDy()+"(单元)"+ower.getShi()+dw);
					ztree.setParent(false);
					treelist.add(ztree);
				}
				criteria.add(Restrictions.not(Restrictions.like("fwlx",  "平房", MatchMode.ANYWHERE)));
				criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("zhuang"), "zhuang")));
				criteria.add(Restrictions.eq("xqdz",  split[0].trim()));
			}else if(split.length==2){
				dw="(单元)";
				criteria.add(Restrictions.not(Restrictions.like("fwlx",  "平房", MatchMode.ANYWHERE)));
				criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("dy"), "dy")));
				criteria.add(Restrictions.eq("zhuang",  split[1].trim()));
				criteria.add(Restrictions.eq("xqdz",  split[0].trim()));
			}else if(split.length==3){
				dw="(室)";
				criteria.add(Restrictions.not(Restrictions.like("fwlx",  "平房", MatchMode.ANYWHERE)));
				criteria.add(Restrictions.eq("dy",  split[2].trim()));
				criteria.add(Restrictions.eq("zhuang",  split[1].trim()));
				criteria.add(Restrictions.eq("xqdz",  split[0].trim()));
			}
		}
		List list = dao.findByCriteria(criteria);
		for(Object lst:list){
			ZTree ztree = new ZTree();
			if(lst instanceof TblSyfwgl){
				TblSyfwgl syfw=(TblSyfwgl)lst;
				if(syfw.getZhuang()==null){
					continue;
				}
				ztree.setId(syfw.getId()+"");
				ztree.setpId(owner);
				ztree.setName(syfw.getShi()+dw);//(ower.getZhuang()+"(幢)"+ower.getDy()+"(单元)"+ower.getShi()+dw);
				ztree.setParent(false);
				treelist.add(ztree);
			}else{
				if(lst==null){
					continue;
				}
				ztree.setId(((owner==null||owner.equals(""))?lst:(owner+","+lst))+"");
				ztree.setpId(owner);
				ztree.setName(lst+dw);
				ztree.setParent(true);
				treelist.add(ztree);
			}
		}
		
		return treelist;
	}

}
