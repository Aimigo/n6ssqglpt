package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblMdclDao;
import www.quality.model.TblMdcl;

@Repository(TblMdclDao.TBLMDCL_DAO_IMPL)
public class TblMdclDaoImpl extends BaseDaoImpl<TblMdcl, Serializable>
		implements TblMdclDao {

}
