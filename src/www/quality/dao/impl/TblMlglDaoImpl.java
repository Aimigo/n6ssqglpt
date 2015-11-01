package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblMlglDao;
import www.quality.model.TblMlgl;

@Repository(TblMlglDao.TBLMLGL_DAO_IMPL)
public class TblMlglDaoImpl extends BaseDaoImpl<TblMlgl, Serializable>
		implements TblMlglDao {

}
