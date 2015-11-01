package www.quality.dao;

import java.io.Serializable;
import java.util.List;

import www.quality.model.TblFunction;

public interface TblFunctionDao extends BaseDao<TblFunction, Serializable> {

	public static final String TBLFUNCTION_DAO_IMPL = "www.quality.dao.impl.TblFunctionDaoImpl";
	
	
	/** 
	* 函 数 名 : getOneBycode
	* 功能描述：该方法是对Tblfunction在数据库表中查询单行数据
	* 参数描述  : Tblfunction的code
	* 返回值  : List<Tblfunction>
	* 数据库表 : TBLFUNCTION
	*/
	TblFunction getOneBycode(String code);
	
	/** 
	* 函 数 名 : getDataByCode
	* 功能描述：该方法是对Tblfunction在数据库表中查询数据
	* 参数描述  : Tblfunction的code集合
	* 返回值  : List<Tblfunction>
	* 数据库表 : TBLFUNCTION
	* 修 改 人: 
	* 日    期: 
	*/
	List<TblFunction> getDataByCode(List<String> code);

	List<String> findFcodeByCodes(List<String> codes);
	
}
