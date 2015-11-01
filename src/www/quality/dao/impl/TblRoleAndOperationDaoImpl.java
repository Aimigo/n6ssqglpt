package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblRoleAndOperationDao;
import www.quality.model.TblRoleAndOperation;

@Repository(TblRoleAndOperationDao.TBLROLEANDOPERATION_DAO_IMPL)
public class TblRoleAndOperationDaoImpl extends
		BaseDaoImpl<TblRoleAndOperation, Serializable> implements
		TblRoleAndOperationDao {

}
