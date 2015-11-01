package www.quality.service;

import java.util.List;

import www.quality.model.TblGrid;
import www.quality.util.Pager;

/**
*类的描述:TblGrid(网格信息)所对应的服务方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblGridService {

	public static final String TBLGRID_SERVICE_IMPL = "www.pdwy.service.impl.TblGridServiceImpl";
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对TblGrid在数据库表中查询所有的数据
	* 参数描述  : 无
	* 返回值  : List<TblGrid>
	* 数据库表:	TblGrid
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public List<TblGrid> getAllDate();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对TblGrid在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TblGrid
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public Pager getDataByPager(Pager p);

	/** 
	* 函 数 名 : getPagerByCriteria
	* 功能描述：该方法通过条件对TblGrid在数据库表中的多个数据进行分页查找
	* 参数描述  : Pager 根据需要新增参数
	* 返回值  :Pager
	* 数据库表:	TblGrid
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	 * @param name 
	*/
	public Pager getPagerByCriteria(Pager pager, String name);

	/** 
	* 函 数 名 : getByName
	* 功能描述：通过名称精确找到对应数据
	* 参数描述  : 
	* 返回值  :List<TblGrid>
	* 数据库表:	TblGrid
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	*/
	public List<TblGrid> getByName(String name);

	public String[] getNames();

	public List<TblGrid> getDateByGridname(String gridname);
}
