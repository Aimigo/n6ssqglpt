package www.quality.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblOperationDao;
import www.quality.model.TblOperation;

@Repository(TblOperationDao.TBLOPERATION_DAO_IMPL)
public class TblOperationDaoImpl extends
		BaseDaoImpl<TblOperation, Serializable> implements TblOperationDao {

	public List<TblOperation> getDataByOpCode(String code) {
		DetachedCriteria d=DetachedCriteria.forClass(TblOperation.class);
		d.add(Restrictions.eq("functioncode", code));
		return this.findByCriteria(d);
	}

}
