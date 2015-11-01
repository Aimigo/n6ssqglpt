package www.quality.service;

import java.util.List;
import java.util.Map;

import www.quality.model.TblOperation;

public interface TblOperationService {

	public static final String TBLOPERATION_SERVICE_IMPL = "www.quality.service.impl.TblOperationServiceImpl";
	
	/** 
	* 函 数 名 : saveOperation
	* 功能描述：该方法是对Tbloperation在数据库表中进行添加数据
	* 参数描述  :Tbloperation的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLOPERATION
	*/
	public void saveOperation(List<TblOperation> list);
	
	/** 
	* 函 数 名 : updateOperation
	* 功能描述：该方法是对Tbloperation在数据库表中进行修改数据
	* 参数描述  :functioncode 只能为一个
	* 返回值  :无返回值
	* 数据库表:	TBLOPERATION
	*/
	public void updateOperation(String functioncode,List<TblOperation> list);
	
	/** 
	* 函 数 名 : deleteOperation
	* 功能描述：该方法是对Tbloperation在数据库表中进行删除数据
	* 参数描述  :functioncode
	* 返回值  :无返回值
	* 数据库表:	TBLOPERATION
	*/
	public void deleteOperation(String functioncode);
	
	/** 
	* 函 数 名 : getDataByCode
	* 功能描述：该方法是对Tbloperation在数据库表中根据code来查询数据
	* 参数描述  : Tbloperation的对象 
	* 返回值  : List<Tbloperation>
	* 数据库表:	TBLOPERATION
	*/
	public List<TblOperation> getDataByCode(String code);
	
	
	/** 
	* 函 数 名 : getDataByUsercode
	* 功能描述：该方法是对Tbloperation在数据库表中根据用户的code来查询数据
	* 参数描述  : Tbluser表中的code
	* 返回值  : List<TblroleAndOperation>
	* 数据库表:	TBLROLEANDOPERATION,TBLOPERATION,TBLFUNCTION
	*/
	@SuppressWarnings("rawtypes")
	public Map<String,List> getDataByUsercode(String usercode);
	
	/** 
	* 函 数 名 : renOperationName
	* 功能描述：该方法是对Tbloperation集合中的functioncode来查询操作
	* 参数描述  : functioncode和List<Tbloperation> listoper
	* 返回值  : List<String>
	* 数据库表:	无
	*/
	public List<String> renOperationName(String functioncode,List<TblOperation> listoper);

	/** 
	* 函 数 名 : getDataByFunctionCode
	* 功能描述：该方法是对Tbloperation集合中的functioncode来查询操作
	* 参数描述  : functioncode
	* 返回值  : List<Tbloperation>
	* 数据库表:	无
	*/
	public List<TblOperation> getDataByFunctionCode(String functioncode);

	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对Tbloperation查询操作
	* 参数描述  : 
	* 返回值  : List<Tbloperation>
	* 数据库表:	无
	*/
	public List<TblOperation> getAllData();
	
	/** 
	* 函 数 名 : deleteOperation
	* 功能描述：该方法是对Tbloperation在数据库表中进行删除数据
	* 参数描述  :functioncode name即操作CODE
	* 返回值  :无返回值
	* 数据库表:	TBLOPERATION
	*/
	public void deleteOperation(String functioncode,String name);

	public TblOperation getOneByFuncodeAndFunxxcode(String funcode,
			String funxxcode);
}
