package www.quality.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblRegionDao;
import www.quality.model.TblRegion;

@Repository(TblRegionDao.TBLREGION_DAO_IMPL)
public class TblRegionDaoImpl extends BaseDaoImpl<TblRegion, Serializable>
		implements TblRegionDao {

	public TblRegion findByCode(String code) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblRegion.class);
		criteria.add(Restrictions.eq("code", code));
		List<TblRegion> list = this.findByCriteria(criteria);
		if(null!=list&&list.size()>0)
			return list.get(0);
		return null;
	}

}
