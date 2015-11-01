package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblLog;

public interface TblLogDao extends BaseDao<TblLog, Serializable> {

	public static final String TBLLOG_DAO_IMPL = "www.quality.dao.impl.TblLogDaoImpl";
}
