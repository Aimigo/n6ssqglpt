package www.quality.dao;

import java.io.Serializable;
import java.util.List;

import www.quality.model.TblOperation;

public interface TblOperationDao extends BaseDao<TblOperation, Serializable> {

	public static final String TBLOPERATION_DAO_IMPL = "www.quality.dao.impl.TblOperationDaoImpl";
	
	/** 
	* 函 数 名 : getDataByOpCode
	* 功能描述：该方法是对Tbloperation在数据库表中查询数据
	* 参数描述  : Tblfunction的code
	* 返回值  : List<Tbloperation>
	* 数据库表:	TBLOPERATION
	*/
	public List<TblOperation> getDataByOpCode(String code);
}
