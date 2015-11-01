package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblMdflDao;
import www.quality.model.TblMdfl;

@Repository(TblMdflDao.TBLMDFL_DAO_IMPL)
public class TblMdflDaoImpl extends BaseDaoImpl<TblMdfl, Serializable>
		implements TblMdflDao {

}
