package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblProject;

public interface TblProjectDao extends BaseDao<TblProject, Serializable> {
	public static final String TBLPROJECT_DAO_IMPL = "www.quality.dao.impl.TblProjectDaoImpl";
}
