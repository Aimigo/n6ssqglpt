package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblLogDao;
import www.quality.model.TblLog;

@Repository(TblLogDao.TBLLOG_DAO_IMPL)
public class TblLogDaoImpl extends BaseDaoImpl<TblLog, Serializable> implements
		TblLogDao {

}
