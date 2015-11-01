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
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblCzfwglDao;
import www.quality.model.TblCzfwgl;
import www.quality.service.TblCzfwglService;
import www.quality.util.Pager;


/**
*类的描述:实现TblCzfwglService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblCzfwglService.TBLCZFWGL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblCzfwglServiceImpl implements TblCzfwglService {

	@Resource(name=TblCzfwglDao.TBLCZFWGL_DAO_IMPL)
	private TblCzfwglDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblCzfwgl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblCzfwgl obj) {
		dao.saveOrUpdate(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblCzfwgl t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblCzfwgl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblCzfwgl.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblCzfwgl getOneById(Integer id) {		
		List<TblCzfwgl> list = dao.findByHql("from TblCzfwgl where id="+id);
		//return dao.findById(id);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblCzfwgl.class,"R");
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("czr", name,MatchMode.ANYWHERE));
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
					or3 = Restrictions.or(Restrictions.like("sswg", gd,MatchMode.ANYWHERE), Restrictions.eq("czr", "abcdefvsdfdfss"));
				}else{
					or3=Restrictions.or(Restrictions.like("sswg",gd, MatchMode.ANYWHERE), or3);
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
		DetachedCriteria criteria = DetachedCriteria.forClass(TblCzfwgl.class,"R");
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
				TblCzfwgl czfwgl = dao.findById(Integer.parseInt(string));
				if(czfwgl!=null){
					name+=czfwgl.getSyfwname()+" ";
				}
				
				this.delete(Integer.parseInt(string));//逐个删除
			}
		}
		return name;
	}


	public List<TblCzfwgl> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblCzfwgl.class,"R");
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

}
