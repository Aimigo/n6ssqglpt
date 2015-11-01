package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblJnflDao;
import www.quality.model.TblJnfl;

@Repository(TblJnflDao.TBLJNFL_DAO_IMPL)
public class TblJnflDaoImpl extends BaseDaoImpl<TblJnfl, Serializable>
		implements TblJnflDao {

}
