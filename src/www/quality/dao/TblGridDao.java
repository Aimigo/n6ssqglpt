package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblGrid;

public interface TblGridDao extends BaseDao<TblGrid, Serializable> {
	public static final String TBLGRID_DAO_IMPL = "www.quality.dao.impl.TblGridDaoImpl";
}
