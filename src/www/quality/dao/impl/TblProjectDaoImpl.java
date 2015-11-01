package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblProjectDao;
import www.quality.model.TblProject;

@Repository(TblProjectDao.TBLPROJECT_DAO_IMPL)
public class TblProjectDaoImpl extends BaseDaoImpl<TblProject, Serializable>
		implements TblProjectDao {

}
