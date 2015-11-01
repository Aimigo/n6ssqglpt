package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblDepartmentDao;
import www.quality.model.TblDepartment;

@Repository(TblDepartmentDao.TBLDEPARTMENT_DAO_IMPL)
public class TblDepartmentDaoImpl extends BaseDaoImpl<TblDepartment, Serializable>
		implements TblDepartmentDao {

}
