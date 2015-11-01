package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblLoginLogDao;
import www.quality.model.TblLoginLog;

@Repository(TblLoginLogDao.TBLLOGINLOG_DAO_IMPL)
public class TblLoginLogDaoImpl extends BaseDaoImpl<TblLoginLog, Serializable>
		implements TblLoginLogDao {

}
