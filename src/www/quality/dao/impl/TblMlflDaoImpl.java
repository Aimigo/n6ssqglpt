package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblMlflDao;
import www.quality.model.TblMlfl;

@Repository(TblMlflDao.TBLMLFL_DAO_IMPL)
public class TblMlflDaoImpl extends BaseDaoImpl<TblMlfl, Serializable>
		implements TblMlflDao {

}
