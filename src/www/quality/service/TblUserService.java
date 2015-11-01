package www.quality.service;

import java.util.LinkedHashMap;
import java.util.List;

import www.quality.model.TblFunction;
import www.quality.model.TblUser;
import www.quality.util.Pager;

public interface TblUserService {
	
	public static final String TBLUSER_SERVICE_IMPL = "www.quality.service.impl.TblUserServiceImpl";


	
	/** 
	* 函 数 名 : getAllDataByPage
	* 功能描述：对返回来的数据进行封装并分页
	* 参数描述：分页Pager对象
	* 返回值  :  Pager对象
	* 数据库表:	TBLUSER
	*/
	Pager getAllDataByPage(Pager p);

	/** 
	* 函 数 名 : addUser
	* 功能描述：该方法是对TblUser在数据库表中进行添加数据
	* 参数描述  :TblUser的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLUSER
	*/
	void addUser(TblUser user);
	
	/** 
	* 函 数 名 : updateUser
	* 功能描述：该方法是对TblUser在数据库表中的数据进行修改
	* 参数描述  :TblUser的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLUSER
	*/
	void updateUser(TblUser user);
	
	/** 
	* 函 数 名 : deleteUser
	* 功能描述：该方法是对TblUser在数据库表中的数据进行删除
	* 参数描述  :TblUser表中的id
	* 返回值  :无返回值
	* 数据库表:	TBLUSER
	*/
	void deleteUser(String id);
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法通过主键id对TblUser在数据库表中的单个数据进行查找
	* 参数描述  : TblUser表中的id
	* 返回值  :TblUser的对象
	* 数据库表:	TBLUSER
	*/
	TblUser getOneById(Integer id);

	/** 
	* 函 数 名 : getListByUsername
	* 功能描述：该方法通过username对TblUser在数据库表中【启用】的多个数据进行查找
	* 参数描述  : TblUser表中的username
	* 返回值  :TblUser的集合对象
	* 数据库表:	TBLUSER
	*/
	List<TblUser> getListByUsername(String username);
	
	/** 
	* 函 数 名 : getListByUsername
	* 功能描述：该方法通过username对TblUser在数据库表中的多个数据进行查找
	* 参数描述  : TblUser表中的username
	* 返回值  :TblUser的集合对象
	* 数据库表:	TBLUSER
	*/
	public List<TblUser> getListByUsernameIgnoreState(String username);
	
	/** 
	* 函 数 名 : getPagerByCriteria
	* 功能描述：该方法通过条件对TblUser在数据库表中的多个数据进行分页查找
	* 参数描述  : Pager 根据需要新增参数
	* 返回值  :Pager
	* 数据库表:	TBLUSER
	*/
	Pager getPagerByCriteria(Pager pager, String sel , String content);

	/** 
	* 函 数 名 : deleteUserById
	* 功能描述：该方法批量或单独删除TblUser在数据库表中的数据	
	* 参数描述  : id 可以是单个也可以是多个 如1，2，3
	* 返回值  :void
	*/
	void deleteUserById(String id);
	
	/** 
	* 函 数 名 : getDataByRolecode
	* 功能描述：该方法是对TblroleAndOperation在数据库表中根据角色的code来查询属于当前项目CODE的数据
	* 参数描述  : TblroleAndOperation的对象 
	* 返回值  : List<TblroleAndOperation>
	* 数据库表:	TBLROLEANDOPERATION
	*/
	public LinkedHashMap<String, List<TblFunction>> getDataByUsercode(String usercode,String projectcode);
	
	/** 
	* 函 数 名 : getOperationByUsercodeAndUrl
	* 功能描述：该方法是对TblroleAndOperation在数据库表中根据角色的code来查询数据
	* 参数描述  : TblroleAndOperation的对象 
	* 返回值  : List<String>
	* 数据库表:	TBLROLEANDOPERATION TBLUSERANDROLE
	*/
	public List<String> getOperationByUsercodeAndUrl(String usercode,String URL);
	
	/** 
	* 函 数 名 : getOperationByUsercodeAndFuncode
	* 功能描述：该方法是对TblroleAndOperation在数据库表中根据角色的code来查询数据
	* 参数描述  : TblroleAndOperation的对象 
	* 返回值  : List<String>
	* 数据库表:	TBLROLEANDOPERATION TBLUSERANDROLE
	*/
	public List<String> getOperationByUsercodeAndFuncode(String usercode, String funcode);

	List<String> getNamesByIds(String ids);

	List<TblUser> getDataByHealthid(String flid);

	List<TblUser> getUsersByRolename(String rolename);

	List<TblUser>  getUserByGridName(String wangge);


}
