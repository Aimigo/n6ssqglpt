package www.quality.service;

import java.util.Date;
import java.util.List;
import www.quality.model.TblLog;
import www.quality.util.Pager;

public interface TblLogService {

	public static final String TBLLOG_SERVICE_IMPL = "www.quality.service.impl.TblLogServiceImpl";
	
	/** 
	* 函 数 名 : saveLog
	* 功能描述：该方法是对Tbllog在数据库表中进行添加数据
	* 参数描述  :Tbllog的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLLOG
	*/
	public void saveLog(TblLog log);
	
	/** 
	* 函 数 名 : updateLog
	* 功能描述：该方法是对Tbllog在数据库表中进行修改数据
	* 参数描述  :Tbllog的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLLOG
	*/
	public void updateLog(TblLog log);
	
	/** 
	* 函 数 名 : deleteLog
	* 功能描述：该方法是对Tbllog在数据库表中进行删除数据
	* 参数描述  :Tbllog的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLLOG
	*/
	public void deleteLog(String id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对Tbllog在数据库表中查询所有的数据
	* 参数描述  : Tbllog的对象 
	* 返回值  : List<Tbllog>
	* 数据库表:	TBLLOG
	*/
	public List<TblLog> getAllData();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对Tbllog在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TBLLOG
	*/
	public Pager getAllDataByPage(Pager p,String cond,String nr);
	
	public TblLog getOneById(Integer id);

	public Pager flByhp(Pager p, String string);

	public TblLog findById(int parseInt);
	
	public Pager getPagerByCriteria(Pager p,String nr,String cond,String stime,String etime,String col,String px);

	public List<TblLog> getLogByUsername(String username);
	
	/**
	 * 写入操作日志
	 * @param username 用户名
	 * @param departcode 机构CODE
	 * @param functionname1 功能1
	 * @param functionname2 功能2
	 * @param ms 描述
	 * @param date 操作日期 可以不添 默认为当前时间 若不添请写null
	 * String 类型的 若不填写请填写""
	 */
	public void getLog(String username,String realname,String functionname,String operationname,String ms,Date date);
}
