package www.quality.service;

import java.util.List;

import www.quality.model.TblLoginLog;
import www.quality.util.Pager;

public interface TblLoginLogService {

	public static final String TBLLOGINLOG_SERVICE_IMPL = "www.quality.service.impl.TblLoginLogServiceImpl";
	
	/** 
	* 函 数 名 : saveLoginlog
	* 功能描述：该方法是对Tblloginlog在数据库表中进行添加数据
	* 参数描述  :Tblloginlog的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLLOGINLOG
	*/
	public void saveLoginlog(TblLoginLog login);
	
	/** 
	* 函 数 名 : updateLoginlog
	* 功能描述：该方法是对Tblloginlog在数据库表中进行修改数据
	* 参数描述  :Tblloginlog的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLLOGINLOG
	*/
	public void updateLoginlog(TblLoginLog login);
	
	/** 
	* 函 数 名 : deleteLoginlog
	* 功能描述：该方法是对Tblloginlog在数据库表中进行删除数据
	* 参数描述  :Tblloginlog的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLLOGINLOG
	*/
	public void deleteLoginlog(String id);
	
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对Tblloginlog在数据库表中查询所有的数据
	* 参数描述  : Tblloginlog的对象 
	* 返回值  : List<Tblloginlog>
	* 数据库表:	TBLLOGINLOG
	*/
	public List<TblLoginLog> getAllData();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对Tblloginlog在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TBLLOGINLOG
	*/
	public Pager getAllDataByPage(Pager p,String cond,String nr); 
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对Tblloginlog在数据库表中根据id查询单行数据
	* 参数描述  : Tblloginlog的id
	* 返回值  : Tblloginlog对象
	* 数据库表:	TBLLOGINLOG
	*/
	public TblLoginLog getOneById(Integer id);

	public TblLoginLog findById(int parseInt);

	public TblLoginLog getLoginByCode(String code);

	public Pager flByhp(Pager p, String string);
	
	public Pager getPagerByCriteria(Pager p,String username,String col,String px);

	public Pager getPagerByCriteria(Pager p, String cond, String nr,
			String stime, String etime, String col, String px);
}
