package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblLcrkDao;
import www.quality.model.TblLcrk;

@Repository(TblLcrkDao.TBLLCRK_DAO_IMPL)
public class TblLcrkDaoImpl extends BaseDaoImpl<TblLcrk, Serializable>
		implements TblLcrkDao {

}
