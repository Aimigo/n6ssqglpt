package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblRole;

public interface TblRoleDao extends BaseDao<TblRole, Serializable> {
	public static final String TBLROLE_DAO_IMPL = "www.quality.dao.impl.TblRoleDaoImpl";

	public void merge(TblRole role);
}
