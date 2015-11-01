package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblPxjnDao;
import www.quality.model.TblPxjn;

@Repository(TblPxjnDao.TBLPXJN_DAO_IMPL)
public class TblPxjnDaoImpl extends BaseDaoImpl<TblPxjn, Serializable>
		implements TblPxjnDao {

}
