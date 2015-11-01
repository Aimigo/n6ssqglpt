package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblUserAndRole;

public interface TblUserAndRoleDao extends
		BaseDao<TblUserAndRole, Serializable> {
	public static final String TBLUSERANDROLE_DAO_IMPL = "www.quality.dao.impl.TblUserAndRoleDaoImpl";
}
