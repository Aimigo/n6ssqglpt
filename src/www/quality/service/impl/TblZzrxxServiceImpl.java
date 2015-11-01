package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
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

import www.quality.dao.TblZzrxxDao;
import www.quality.model.TblCzfwgl;
import www.quality.model.TblSyfwgl;
import www.quality.model.TblZz;
import www.quality.model.TblZzrxx;
import www.quality.service.TblZzrxxService;
import www.quality.util.Pager;


/**
*类的描述:实现TblZzrxxService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblZzrxxService.TBLZZRXX_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblZzrxxServiceImpl implements TblZzrxxService {

	@Resource(name=TblZzrxxDao.TBLZZRXX_DAO_IMPL)
	private TblZzrxxDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblZzrxx obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblZzrxx obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblZzrxx t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblZzrxx> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblZzrxx.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblZzrxx getOneById(Integer id) {		
		List<TblZzrxx> list = dao.findByHql("from TblZzrxx where id="+id);
		//return dao.findById(id);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZzrxx.class,"R");
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("xm", name,MatchMode.ANYWHERE));
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
			criteria.add(Property.forName("ccfwid").in(syfwDc));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("R.id"));
	}
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZzrxx.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
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
		DetachedCriteria regDc = DetachedCriteria
				.forClass(TblCzfwgl.class);
		regDc.setProjection(Property.forName("syfwid"));
		criteria.add(Property.forName("id").in(regDc));
		
		List list = dao.findByCriteria(criteria);
		return list;
	}


	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				this.delete(Integer.parseInt(string));//逐个删除
			}
		}
	}


	public List<TblZzrxx> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZzrxx.class,"R");
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
	public List getZtreeDate(String[] split){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblSyfwgl.class,"R");
		if(split.length==1){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("xqdz"), "xqdz")));	
		}else if(split.length==2){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("zhuang"), "zhuang")));
			criteria.add(Restrictions.eq("xqdz",  split[1].trim()));	
		}else if(split.length==3){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("dy"), "dy")));
			criteria.add(Restrictions.eq("zhuang",  split[2].trim()));
			criteria.add(Restrictions.eq("xqdz",  split[1].trim()));
		}else if(split.length==4){
			criteria.add(Restrictions.eq("dy",  split[3].trim()));
			criteria.add(Restrictions.eq("zhuang",  split[2].trim()));
			criteria.add(Restrictions.eq("xqdz",  split[1].trim()));
		}
		DetachedCriteria regDc2 = DetachedCriteria
				.forClass(TblCzfwgl.class);
		regDc2.setProjection(Property.forName("syfwid"));
		criteria.add(Property.forName("id").in(regDc2));
		
		
		if(split[0]!=null&&!"".equals(split[0])){
			DetachedCriteria regDc = DetachedCriteria
					.forClass(TblZz.class);
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

}
