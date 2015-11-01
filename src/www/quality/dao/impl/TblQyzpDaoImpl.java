package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblQyzpDao;
import www.quality.model.TblQyzp;

@Repository(TblQyzpDao.TBLQYZP_DAO_IMPL)
public class TblQyzpDaoImpl extends BaseDaoImpl<TblQyzp, Serializable>
		implements TblQyzpDao {

}
