package www.quality.service;

import java.util.List;

import www.quality.model.TblRoleAndOperation;

/**
*类的描述:TblroleAndOperation所对应的服务方法
*作者:杜长吉
*创建日期:2012-05-08
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblRoleAndOperationService {

	public static final String TBLROLEANDOPERATION_SERVICE_IMPL = "www.quality.service.impl.TblRoleAndOperationServiceImpl";
	
	/** 
	* 函 数 名 : saveRoleAndOperation
	* 功能描述：该方法是对TblroleAndOperation在数据库表中进行添加数据
	* 参数描述  :TblroleAndOperation的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLRODLEANDOPERATION
	*/
	public void saveRoleAndOperation(List<TblRoleAndOperation> list);
	
	/** 
	* 函 数 名 : updateRoleAndOperation
	* 功能描述：该方法是对TblroleAndOperation在数据库表中进行修改数据
	* 参数描述  :TblroleAndOperation的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLRODLEANDOPERATION
	*/
	public void updateRoleAndOperation(String rolecode,List<TblRoleAndOperation> list);
	
	/** 
	* 函 数 名 : updateRoleAndOperation
	* 功能描述：该方法是对TblroleAndOperation在数据库表中根据角色的code删除数据
	* 参数描述  :TblroleAndOperation的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLRODLEANDOPERATION
	*/
	public void deleteRoleAndOperation(String rolecode);
	
	/** 
	* 函 数 名 : getDataByRolecode
	* 功能描述：该方法是对TblroleAndOperation在数据库表中根据角色的code来查询数据
	* 参数描述  : TblroleAndOperation的对象 
	* 返回值  : List<TblroleAndOperation>
	* 数据库表:	TBLROLEANDOPERATION
	*/
	public List<TblRoleAndOperation> getDataByRolecode(String rolecode);
	
}
