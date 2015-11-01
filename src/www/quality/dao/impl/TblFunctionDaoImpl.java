package www.quality.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblFunctionDao;
import www.quality.model.TblFunction;

@Repository(TblFunctionDao.TBLFUNCTION_DAO_IMPL)
public class TblFunctionDaoImpl extends BaseDaoImpl<TblFunction, Serializable>
		implements TblFunctionDao {

	public TblFunction getOneBycode(String code) {
		DetachedCriteria d=DetachedCriteria.forClass(TblFunction.class);
		d.add(Restrictions.eq("code", code));
		List<TblFunction> list=this.findByCriteria(d);
		try {
			return list.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public List<TblFunction> getDataByCode(List<String> code) {
		DetachedCriteria d=DetachedCriteria.forClass(TblFunction.class);
		d.add(Restrictions.in("code", code));
		d.addOrder(Order.asc("marker"));
		return this.findByCriteria(d);
	}

	@SuppressWarnings("unchecked")
	public List<String> findFcodeByCodes(List<String> codes) {
		// TODO Auto-generated method stub
		DetachedCriteria d=DetachedCriteria.forClass(TblFunction.class);
		d.add(Restrictions.in("code", codes));
		d.setProjection(Projections.property("fcode"));
		return this.findByDetachedCriteria(d);
	}

}
