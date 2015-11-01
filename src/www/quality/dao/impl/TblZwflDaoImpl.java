package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblZwflDao;
import www.quality.model.TblZwfl;

@Repository(TblZwflDao.TBLZWFL_DAO_IMPL)
public class TblZwflDaoImpl extends BaseDaoImpl<TblZwfl, Serializable>
		implements TblZwflDao {

}
