package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblZzDao;
import www.quality.dao.TblTsryflDao;
import www.quality.dao.TblTsryflzdDao;
import www.quality.dao.TblTsrysjDao;
import www.quality.model.TblZz;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.service.TblZzService;
import www.quality.util.Pager;


/**
*类的描述:实现TblZzService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblZzService.TBLZZ_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblZzServiceImpl implements TblZzService {

	@Resource(name=TblZzDao.TBLZZ_DAO_IMPL)
	private TblZzDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblZz obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblZz obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblZz t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblZz> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblZz.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblZz getOneById(Integer id) {		
		List<TblZz> list = dao.findByHql("from TblZz where objectid="+id);
		//return dao.findById(id);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZz.class,"R");
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		String grid = params.get("grid");
		if(grid!=null&&!"".equals(grid)){
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
				criteria.add(or3);
			}
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("R.id"));
	}
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZz.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		String name="";
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				TblZz zz = dao.findById(Integer.parseInt(string));
				if(zz!=null){
					name+=zz.getName()+" ";
				}
				this.delete(Integer.parseInt(string));//逐个删除
			}
		}
		return name;
	}


	public List<TblZz> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZz.class,"R");
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
	public List getAllOwners(String grid){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZz.class,"R");
		criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("owner"), "owner")));
		if(grid!=null&&!"".equals(grid)){
			if(grid!=null&&!"".equals(grid)){
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
					criteria.add(or3);
				}
			}
		}
		List list = dao.findByCriteria(criteria);
		return list;
	}
	public List<TblZz> getByOwner(String owner,String grid){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZz.class,"R");
		criteria.add(Restrictions.eq("owner",  owner));
		if(grid!=null&&!"".equals(grid)){
			if(grid!=null&&!"".equals(grid)){
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
					criteria.add(or3);
				}
			}
		}
		 List list = dao.findByCriteria(criteria);
		return list;
		
	}

}
