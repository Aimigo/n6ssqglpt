package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblYlzsDao;
import www.quality.model.TblYjzs;

@Repository(TblYlzsDao.TBLYLZS_DAO_IMPL)
public class TblYlzsDaoImpl extends BaseDaoImpl<TblYjzs, Serializable>
		implements TblYlzsDao {

}
