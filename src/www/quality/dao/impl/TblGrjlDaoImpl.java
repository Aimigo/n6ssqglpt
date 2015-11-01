package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblGrjlDao;
import www.quality.model.TblGrjl;

@Repository(TblGrjlDao.TBLGRJL_DAO_IMPL)
public class TblGrjlDaoImpl extends BaseDaoImpl<TblGrjl, Serializable>
		implements TblGrjlDao {

}
