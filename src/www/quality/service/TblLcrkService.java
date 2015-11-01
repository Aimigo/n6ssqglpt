package www.quality.service;

import java.util.List;

import www.quality.model.TblLcrk;
import www.quality.util.Pager;


public interface TblLcrkService {

	public static final String TBLLCRK_SERVICE_IMPL = "www.quality.service.impl.TblLcrkServiceImpl";
	/** 
	* 函 数 名 : save
	* 功能描述：该方法是对TblLcrk在数据库表中进行添加数据
	* 参数描述  :TblLcrk的对象 
	* 返回值  :无返回值
	* 数据库表:	TblLcrk
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void save(TblLcrk obj);
	
	/** 
	* 函 数 名 : update
	* 功能描述：该方法是对TblLcrk在数据库表中进行修改数据
	* 参数描述  :TblLcrk的对象 
	* 返回值  :无返回值
	* 数据库表:	TblLcrk
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void update(TblLcrk obj);
	
	/** 
	* 函 数 名 : delete
	* 功能描述：该方法是对TblLcrk在数据库表中进行删除数据
	* 参数描述  :TblLcrk的对象 
	* 返回值  :无返回值
	* 数据库表:	TblLcrk
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void delete(Integer id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对TblLcrk在数据库表中查询所有的数据
	* 参数描述  : 无
	* 返回值  : List<TblLcrk>
	* 数据库表:	TblLcrk
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public List<TblLcrk> getAllDate();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对TblLcrk在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TblLcrk
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public Pager getDataByPager(Pager p);
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对TblLcrk在数据库表中根据id查询单行数据
	* 参数描述  : TblLcrk的id
	* 返回值  : TblLcrk对象
	* 数据库表:	TblLcrk
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public TblLcrk getOneById(Integer id);

	/** 
	* 函 数 名 : getPagerByCriteria
	* 功能描述：该方法通过条件对TblLcrk在数据库表中的多个数据进行分页查找
	* 参数描述  : Pager 根据需要新增参数
	* 返回值  :Pager
	* 数据库表:	TblLcrk
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	 * @param name 
	*/
	public Pager getPagerByCriteria(Pager pager, String name);

	/** 
	* 函 数 名 : deleteById
	* 功能描述：该方法批量或单独删除TblLcrk在数据库表中的数据	
	* 参数描述  : id 可以是单个也可以是多个 如1，2，3
	* 返回值  :void
	* 数据库表:	TblLcrk
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	*/
	public String deleteById(String id);

	/** 
	* 函 数 名 : getByName
	* 功能描述：通过名称精确找到对应数据
	* 参数描述  : 
	* 返回值  :List<TblLcrk>
	* 数据库表:	TblLcrk
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	*/
	public List findByHql(String hql);
	public Pager getPagerByCriteria(Pager p);

	public List getTjData();

	public List getTjData2();

}
