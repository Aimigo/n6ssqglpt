package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblUser;

public interface TblUserDao extends BaseDao<TblUser, Serializable>{
	public static final String TBLUSER_DAO_IMPL = "www.quality.dao.impl.TblUserDaoImpl";
}
