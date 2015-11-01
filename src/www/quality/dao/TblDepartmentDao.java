package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblDepartment;

public interface TblDepartmentDao extends BaseDao<TblDepartment, Serializable> {
	public static final String TBLDEPARTMENT_DAO_IMPL = "www.quality.dao.impl.TblDepartmentDaoImpl";
}
