package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblJnfl;

public interface TblJnflDao extends BaseDao<TblJnfl, Serializable> {
	public static final String TBLJNFL_DAO_IMPL = "www.quality.dao.impl.TblJnflDaoImpl";
}
