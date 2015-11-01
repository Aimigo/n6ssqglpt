package www.quality.service;

import java.util.List;
import www.quality.model.TblRole;
import www.quality.util.Pager;

public interface TblRoleService {

	public static final String TBLROLE_SERVICE_IMPL = "www.quality.service.impl.TblRoleServiceImpl";
	
	/** 
	* 函 数 名 : saveRole
	* 功能描述：该方法是对Tblrole在数据库表中进行添加数据
	* 参数描述  :Tblrole的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLROLE
	*/
	public void saveRole(TblRole role);
	
	/** 
	* 函 数 名 : updateRole
	* 功能描述：该方法是对Tblrole在数据库表中进行修改数据
	* 参数描述  :Tblrole的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLROLE
	*/
	public void updateRole(TblRole role);
	
	/** 
	* 函 数 名 : deleteRole
	* 功能描述：该方法是对Tblrole在数据库表中进行删除数据
	* 参数描述  :Tblrole的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLROLE
	*/
	public void deleteRole(Integer id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对Tblrole在数据库表中查询所有的数据
	* 参数描述  : 无
	* 返回值  : List<Tblrole>
	* 数据库表:	TBLROLE
	*/
	public List<TblRole> getAllDate();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对Tblrole在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TBLROLE
	*/
	public Pager getDataByPager(Pager p);
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对Tblrole在数据库表中根据id查询单行数据
	* 参数描述  : Tblrole的id
	* 返回值  : Tblrole对象
	* 数据库表:	TBLROLE
	*/
	public TblRole getOneById(Integer id);

	/** 
	* 函 数 名 : getPagerByCriteria
	* 功能描述：该方法通过条件对Tblrole在数据库表中的多个数据进行分页查找
	* 参数描述  : Pager 根据需要新增参数
	* 返回值  :Pager
	* 数据库表:	TBLROLE
	*/
	public Pager getPagerByCriteria(Pager pager, String name);

	/** 
	* 函 数 名 : deleteRoleById
	* 功能描述：该方法批量或单独删除Tblrole在数据库表中的数据	
	* 参数描述  : id 可以是单个也可以是多个 如1，2，3
	* 返回值  :void
	* 数据库表:	TBLROLE
	*/
	public void deleteRoleById(String id);

	/** 
	* 函 数 名 : getRoleByName
	* 功能描述：通过角色名称精确找到对应数据 用于验证角色是否重复
	* 参数描述  : 
	* 返回值  :List<Tblrole>
	* 数据库表:	TBLROLE
	*/
	public List<TblRole> getRoleByName(String name);

	public List<String> getNamesByIds(String ids);
}
