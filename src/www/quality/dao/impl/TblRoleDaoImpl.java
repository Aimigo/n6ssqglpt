package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblRoleDao;
import www.quality.model.TblRole;

@Repository(TblRoleDao.TBLROLE_DAO_IMPL)
public class TblRoleDaoImpl extends BaseDaoImpl<TblRole, Serializable>
		implements TblRoleDao {

	public void merge(TblRole role) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().merge(role);
	}

}
