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
import www.quality.dao.TblLogDao;

import www.quality.model.TblLog;
import www.quality.service.TblLogService;
import www.quality.util.Pager;

/**
 * 类的描述:实现TblLogService(系统操作日志表)接口方法 作者:杜长吉 创建日期:2012-05-09
 * 
 * 修改人 修改日期 修改原因描述
 */
@Service(TblLogService.TBLLOG_SERVICE_IMPL)
@Transactional(readOnly = true)
public class TblLogServiceImpl implements TblLogService {

	@Resource(name = TblLogDao.TBLLOG_DAO_IMPL)
	private TblLogDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveLog(TblLog log) {
		dao.save(log);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateLog(TblLog log) {
		dao.update(log);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteLog(String id) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("delete from TblLog t where t.id in(" + id + ")");
		dao.bulkUpdate(sbuf.toString());
	}

	public List<TblLog> getAllData() {
		return dao.getAll();
	}

	public Pager getAllDataByPage(Pager p, String cond, String nr) {
		DetachedCriteria d = DetachedCriteria.forClass(TblLog.class, "u");
		if ("0".equals(cond)) {
			// d.add(Restrictions.like("username",username,MatchMode.ANYWHERE));
		}
		if ("1".equals(cond)) {
			d.add(Restrictions.like("username", nr, MatchMode.ANYWHERE));

		}
		return dao.getPagerByCriteria(d, p, Order.desc("u.id"));
	}

	public TblLog getOneById(Integer id) {
		return dao.findById(id);
	}

	public Pager flByhp(Pager p, String hql) {

		return dao.findPageBeanByHql(p.getCurPage(), p.getPageRows(), hql);
	}

	public TblLog findById(int id) {

		return dao.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<TblLog> getLogByUsername(String username) {

		TblLog log = null;
		List<TblLog> list = dao.findByHql("from TblLog where username='"
				+ username + "'");
		if (list != null && list.size() > 0) {
			log = list.get(0);
		}
		return (List<TblLog>) log;

	}

	public Pager getPagerByCriteria(Pager p, String cond, String nr,
			String stime, String etime, String col, String px) {
		DetachedCriteria dc = DetachedCriteria.forClass(TblLog.class, "u");
		if ("0".equals(cond)) {
			// d.add(Restrictions.like("username",username,MatchMode.ANYWHERE));
		}
		if (null != nr && "1".equals(cond)) {
			dc.add(Restrictions.like("username", nr, MatchMode.ANYWHERE));
		}
		if ("2".equals(cond) && null != stime && null != etime) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date sd = null;
			Date ed = null;
			try {
				sd = format.parse(stime+" 00:00:00");
				ed = format.parse(etime+" 23:59:59");
			} catch (Exception e) {
				e.printStackTrace();
			}

			dc.add(Restrictions.ge("operationtime", sd));
			dc.add(Restrictions.le("operationtime", ed));
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

	public void getLog(String username, String projectname,
			String functionname, String operationname, String ms, Date date) {
		// TODO Auto-generated method stub
		TblLog log = new TblLog();
		log.setUsername(username);
		log.setProjectname(projectname);
		log.setFunctionname(functionname);
		log.setOperationname(operationname);
		log.setMs(ms);
		if(null!=date)
			log.setOperationtime(date);
		else
			log.setOperationtime(new Date());
		dao.save(log);
	}
}
