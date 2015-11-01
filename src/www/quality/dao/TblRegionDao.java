package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblRegion;

public interface TblRegionDao extends BaseDao<TblRegion, Serializable> {
	public static final String TBLREGION_DAO_IMPL = "www.quality.dao.impl.TblRegionDaoImpl";

	TblRegion findByCode(String code);
}
