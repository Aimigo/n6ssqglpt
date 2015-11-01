package www.quality.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblLoginLogDao;
import www.quality.model.TblLoginLog;
import www.quality.service.TblLoginLogService;
import www.quality.util.Pager;

@Service(TblLoginLogService.TBLLOGINLOG_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblLoginLogServiceImpl implements TblLoginLogService {

	@Resource(name=TblLoginLogDao.TBLLOGINLOG_DAO_IMPL)
	private TblLoginLogDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveLoginlog(TblLoginLog login) {
		dao.save(login);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateLoginlog(TblLoginLog login) {
		dao.update(login);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteLoginlog(String id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblLoginLog t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());
	}

	public List<TblLoginLog> getAllData() {
		return dao.getAll();
	}

	

	public TblLoginLog getOneById(Integer id) {
		return dao.findById(id);
	}

	public Pager getAllDataByPage(Pager p, String cond, String nr) {
		DetachedCriteria d=DetachedCriteria.forClass(TblLoginLog.class,"u");
		   if("0".equals(cond)){
			   //d.add(Restrictions.like("username",username,MatchMode.ANYWHERE));
		   }if("1".equals(cond)){
			   d.add(Restrictions.like("username",nr,MatchMode.ANYWHERE));
		   
		   }
			return dao.getPagerByCriteria(d, p, Order.desc("u.id"));
		}

	public TblLoginLog findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public TblLoginLog getLoginByCode(String username) {
		TblLoginLog login = null;
		
		List<TblLoginLog> list = dao.findByHql("from TblLoginLog where username='"+username+"'");
		
		if(list!=null&&list.size()>0){
			login=list.get(0);
		}
		
		return login;
		
	}

	public Pager flByhp(Pager p, String hql) {
		
		return dao.findPageBeanByHql(p.getCurPage(), p.getPageRows(), hql);
	}

	public Pager getPagerByCriteria(Pager p,String username,String col,String px) {
		
		DetachedCriteria d=DetachedCriteria.forClass(TblLoginLog.class,"u");
		if(null!=username&&!"".equals(username )){
			d.add(Restrictions.like("username", username,MatchMode.ANYWHERE));
		}
		Order order=Order.desc("u.id");
		if(null!=col&&!"".equals(col)&&null!=px&&!"".equals(px)){
			if("desc".equals(px)){
			order=Order.desc("u."+col);
			}else if("asc".equals(px)){
				order=Order.asc("u."+col);
			}
		}
		return dao.getPagerByCriteria(d, p, order);
	}

	public Pager getPagerByCriteria(Pager p, String cond, String nr,
			String stime, String etime, String col, String px) {
		// TODO Auto-generated method stub
		DetachedCriteria dc = DetachedCriteria.forClass(TblLoginLog.class, "u");
		if (null != nr && "1".equals(cond)) {
			dc.add(Restrictions.like("username", nr, MatchMode.ANYWHERE));
		}else if(null != nr && "2".equals(cond)){
			dc.add(Restrictions.like("realname", nr, MatchMode.ANYWHERE));
		}else if ("3".equals(cond) && null != stime && null != etime) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date sd = null;
			Date ed = null;
			try {
				sd = format.parse(stime+" 00:00:00");
				ed = format.parse(etime+" 23:59:59");
			} catch (Exception e) {
				e.printStackTrace();
			}

			dc.add(Restrictions.ge("logintime", sd));
			dc.add(Restrictions.le("logintime", ed));
		}
		
		Order order = Order.desc("u.id");
		if (null != col && !"".equals(col) && null != px && !"".equals(px)) {
			if ("desc".equals(px)) {
				order = Order.desc("u." + col);
			} else if ("asc".equals(px)) {
				order = Order.asc("u." + col);
			}
		}
		return dao.getPagerByCriteria(dc, p, order);
	}
}
