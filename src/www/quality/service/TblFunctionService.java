package www.quality.service;

import java.util.List;

import www.quality.model.TblFunction;
import www.quality.util.Pager;

public interface TblFunctionService {

	public static final String TBLFUNCTION_SERVICE_IMPL = "www.quality.service.impl.TblFunctionServiceImpl";
	
	/** 
	* 函 数 名 : saveFunction
	* 功能描述：该方法是对Tblfunction在数据库表中进行添加数据
	* 参数描述  :Tblfunction的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLFUNCTION
	*/
	public void saveFunction(TblFunction fun);
	
	/** 
	* 函 数 名 : updateFunction
	* 功能描述：该方法是对Tblfunction在数据库表中进行修改数据
	* 参数描述  :Tblfunction的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLFUNCTION
	*/
	public void updateFunction(TblFunction fun);
	
	/** 
	* 函 数 名 : deleteFunction
	* 功能描述：该方法是对Tblfunction在数据库表中进行删除数据及其关联表中的数据,注意--这个方法删除的是最后一级的子节点
	* 参数描述  :Tblfunction的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLFUNCTION
	*/
	public void deleteFunction(Integer id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对Tblfunction在数据库表中查询所有的数据
	* 参数描述  : Tblfunction的对象 
	* 返回值  : List<Tblfunction>
	* 数据库表:	TBLFUNCTION
	*/
	public List<TblFunction> getAllData();
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对Tblfunction在数据库表中根据id查询单行数据
	* 参数描述  : Tblfunction的id
	* 返回值  : Tblfunction表中的id
	* 数据库表:	TBLFUNCTION
	*/
	public TblFunction getOneById(Integer id);

	/** 
	* 函 数 名 : getPagerByCriteria
	* 功能描述：该方法通过条件对TBLFUNCTION在数据库表中的多个数据进行分页查找
	* 参数描述  : Pager 根据需要新增参数
	* 返回值  :Pager
	* 数据库表:	TBLFUNCTION
	*/
	public Pager getPagerByCriteria(Pager pager, String name);
	
	public Pager getPagerByCode(Pager pager,String code);

	/** 
	* 函 数 名 : deleteFunctionById
	* 功能描述：该方法批量或单独删除TBLFUNCTION在数据库表中的数据	
	* 参数描述  : id 可以是单个也可以是多个 如1，2，3
	* 返回值  :void
	* 数据库表:	TBLFUNCTION
	*/
	public void deleteFunctionById(String id);

	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对Tblfunction在数据库表中根据id查询单行数据
	* 参数描述  : Tblfunction的id
	* 返回值  : Tblfunction表中的id
	* 数据库表:	TBLFUNCTION
	*/
	public TblFunction getOneByCode(String code);

	public List<TblFunction> getListByNameAndFcode(String name,String fcode);
	
	public List<TblFunction> getAllByCode(String fcode);

	public List<TblFunction> getsecndfnbypro(String procode);
	
	public List<TblFunction> getFList(String code);

	public List<String> getNamesByIds(String ids);

	
}
