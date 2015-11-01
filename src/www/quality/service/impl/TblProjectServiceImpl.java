package www.quality.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblProjectDao;
import www.quality.model.TblProject;
import www.quality.service.TblProjectService;
import www.quality.util.Pager;

@Service(TblProjectService.TBLPROJECT_SERVICE_IMPL)
@Transactional(readOnly = true)
public class TblProjectServiceImpl implements TblProjectService {

	@Resource(name = TblProjectDao.TBLPROJECT_DAO_IMPL)
	private TblProjectDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveProject(TblProject pro) {
		dao.save(pro);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateProject(TblProject pro) {
		dao.update(pro);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteProject(Integer id) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("delete from TblProject t where t.id in(" + id + ")");
		dao.bulkUpdate(sbuf.toString());
	}

	public List<TblProject> getAlldata() {
		return dao.getAll();
	}

	public Pager getAllDataByPage(Pager p, String cond, String nr) {
		DetachedCriteria d = DetachedCriteria.forClass(TblProject.class, "u");
		if ("0".equals(cond)) {
			// d.add(Restrictions.like("username",username,MatchMode.ANYWHERE));
		}
		if ("1".equals(cond)) {
			d.add(Restrictions.like("code", nr, MatchMode.ANYWHERE));

		}
		return dao.getPagerByCriteria(d, p, Order.desc("u.id"));
	}

	public TblProject getOneById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteall(String ids) {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("delete from TblProject where id in(" + ids + ")");
		dao.bulkUpdate(sbuf.toString());
	}

	public TblProject findById(int id) {
		return dao.findById(id);
	}

	public TblProject getProByCode(String code) {

		TblProject pro = null;
		List<TblProject> list = dao.findByHql("from TblProject where code='"
				+ code + "'");
		if (list != null && list.size() > 0) {
			pro = list.get(0);
		}
		return pro;

	}

	public Pager flByhp(Pager p, String hql) {

		return dao.findPageBeanByHql(p.getCurPage(), p.getPageRows(), hql);
	}

	public Pager getPagerByCriteria(Pager p, String name, String col, String px) {
		DetachedCriteria d = DetachedCriteria.forClass(TblProject.class, "U");
		if (null != name && !"".equals(name)) {
			d.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		Order order = Order.desc("U.id");
		if (null != col && !"".equals(col) && null != px && !"".equals(px)) {
			if ("desc".equals(px)) {
				order = Order.desc("U." + col);
			} else if ("asc".equals(px)) {
				order = Order.asc("U." + col);
			}
		}
		return dao.getPagerByCriteria(d, p, order);
	}

	public List<TblProject> findByName(String name) {
		DetachedCriteria d = DetachedCriteria.forClass(TblProject.class, "U");
		if (null != name && !"".equals(name)) {
			d.add(Restrictions.eq("name", name));
		}
		return dao.findByCriteria(d);
	}

	@SuppressWarnings("unchecked")
	public List<String> getNamesByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria d = DetachedCriteria.forClass(TblProject.class, "U");
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		d.add(Restrictions.in("id", list));
		d.setProjection(Projections.property("name"));
		return dao.findByDetachedCriteria(d);
	}

}
