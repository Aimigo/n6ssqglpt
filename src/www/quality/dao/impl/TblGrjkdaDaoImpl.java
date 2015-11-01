package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblGrjkdaDao;
import www.quality.model.TblGrjkda;

@Repository(TblGrjkdaDao.TBLGRJKDA_DAO_IMPL)
public class TblGrjkdaDaoImpl extends BaseDaoImpl<TblGrjkda, Serializable>
		implements TblGrjkdaDao {

}
