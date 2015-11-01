package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblLoginLog;

public interface TblLoginLogDao extends BaseDao<TblLoginLog, Serializable> {

	public static final String TBLLOGINLOG_DAO_IMPL = "www.quality.dao.impl.TblLoginLogDaoImpl";
	
}
