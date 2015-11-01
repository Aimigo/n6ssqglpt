package www.quality.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblCbxxDao;
import www.quality.dao.TblJmxxDao;
import www.quality.dao.TblTsrysjDao;
import www.quality.dao.TblJtxxDao;
import www.quality.dao.TblTsryflDao;
import www.quality.dao.TblTsryflzdDao;
import www.quality.dao.TblTsrysjDao;
import www.quality.dao.TblXsxxDao;
import www.quality.model.TblCbxx;
import www.quality.model.TblTsrysj;
import www.quality.model.TblJtxx;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.model.TblXsxx;
import www.quality.service.TblTsrysjService;
import www.quality.util.Pager;


/**
*类的描述:实现TblTsrysjService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblTsrysjService.TBLTSRYSJ_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblTsrysjServiceImpl implements TblTsrysjService {

	@Resource(name=TblJmxxDao.TBLJMXX_DAO_IMPL)
	private TblJmxxDao jmxxdao;
	@Resource(name=TblTsryflDao.TBLTSRYFL_DAO_IMPL)
	private TblTsryflDao fldao;
	@Resource(name=TblTsryflzdDao.TBLTSRYFLZD_DAO_IMPL)
	private TblTsryflzdDao zddao;
	@Resource(name=TblTsrysjDao.TBLTSRYSJ_DAO_IMPL)
	private TblTsrysjDao dao;
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblTsrysj obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblTsrysj obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblTsrysj t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblTsrysj> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblTsrysj.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblTsrysj getOneById(Integer id) {		
		return dao.findById(id);
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsrysj.class,"R");
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("ryname", name,MatchMode.ANYWHERE));
		}
		String ryflid = params.get("ryflid");
		if(null!=ryflid&&!"".equals(ryflid)){
			criteria.add(Restrictions.eq("flid", Integer.parseInt(ryflid)));
		}
		return dao.getPagerByCriteria(criteria, p, Order.asc("R.id"));
	}
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsrysj.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
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

	public List<TblTsrysj> getByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsrysj.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		criteria.addOrder(Order.desc("date"));
		return dao.findByCriteria(criteria);
	}

	public List<TblTsrysj> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsrysj.class,"R");
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
	public List<TblTsryfl> getSpecialType(){
		List<TblTsryfl> list = fldao.findByHql("from TblTsryfl where 1=1 order by sort asc");
		return list;
	}
	public List<TblTsryflzd> getShowFlzdByFlid(String flid){
		List<TblTsryflzd> list = zddao.findByHql("from TblTsryflzd where flid="+flid+" and show=1  order by px asc");
		return list;
	}
	public List<TblTsryflzd> getZdesByFlid(Integer tsryflid){
		List<TblTsryflzd> list = zddao.findByHql("from TblTsryflzd where flid="+tsryflid+" order by px asc");
		return list;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delByIds(String ids){
		//需要级联删除 特殊人员数据表。和家庭信息（户主）表，参保信息表，学生信息表。
		List<TblTsrysj> list = dao.findByHql("from TblTsrysj where id in("+ids+")");
		for(TblTsrysj jmxx:list){
			dao.deleteById(jmxx.getId());
		}
		
	}
	public TblTsryfl getFlByName(String name){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsryfl.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		List<TblTsryfl> list = fldao.findByCriteria(criteria);
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public TblTsryfl getFlById(String ryflid){
		TblTsryfl tsryfl = fldao.findById(Integer.parseInt(ryflid));
		return tsryfl;
	}
	public List<TblTsryflzd> getFlzdByFlid(String flid){
		List<TblTsryflzd> list = zddao.findByHql("from TblTsryflzd where flid="+flid+" order by px asc");
		return list;
	}
	public List<TblTsrysj> getByHql(String hql){
		List<TblTsrysj> list = dao.findByHql(hql);
		return list;
	}
	public void saveAllTsry(List<TblTsrysj> mydatas){
		dao.saveCollection(mydatas);
	}
	public List getTsryPice(String grid){
		String sql="select fl.name ,count(*) from TblTsrysj sj,TblTsryfl fl where fl.id=sj.flid ";
		if(grid!=null&&!"".equals(grid)){
			sql+=" and sj.sfz in(select ID_NUMBER from SYSDIC_JUMINXINXI where 1=2 ";
			String[] split = grid.split(",");
			String sp="";
			for(String s:split){
				sp+=" or wangge like '%"+s.trim()+"%' or otherwg like '%"+s.trim()+"%'";
			}
			sql+=sp+")";
		}
		sql+=" group by fl.name";
		List fd = dao.findBySql(sql, false);
		return fd;
	}
	public List<TblTsrysj> getBySfz(String trim){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsrysj.class,"R");
			criteria.add(Restrictions.eq("sfz", trim));
		List list = fldao.findByCriteria(criteria);
		return list;
	}
}
