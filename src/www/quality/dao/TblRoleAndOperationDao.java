package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblRoleAndOperation;

public interface TblRoleAndOperationDao extends
		BaseDao<TblRoleAndOperation, Serializable> {
	public static final String TBLROLEANDOPERATION_DAO_IMPL = "www.quality.dao.impl.TblRoleAndOperationDaoImpl";
}
