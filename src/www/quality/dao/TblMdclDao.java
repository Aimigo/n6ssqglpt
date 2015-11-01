package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblMdcl;

public interface TblMdclDao extends BaseDao<TblMdcl, Serializable> {
	public static final String TBLMDCL_DAO_IMPL = "www.quality.dao.impl.TblMdclDaoImpl";
}
