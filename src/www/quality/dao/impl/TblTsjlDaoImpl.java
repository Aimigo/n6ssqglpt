package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblTsjlDao;
import www.quality.model.TblTsjl;

@Repository(TblTsjlDao.TBLTSJL_DAO_IMPL)
public class TblTsjlDaoImpl extends BaseDaoImpl<TblTsjl, Serializable>
		implements TblTsjlDao {

}
