package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblGridDao;
import www.quality.model.TblGrid;

@Repository(TblGridDao.TBLGRID_DAO_IMPL)
public class TblGridDaoImpl extends BaseDaoImpl<TblGrid, Serializable>
		implements TblGridDao {

}
