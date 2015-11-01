package www.quality.service;

import java.util.List;

import www.quality.model.TblGrjkda;
import www.quality.util.Pager;

/**
*类的描述:TblGrjkda(个人健康档案)所对应的服务方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblGrjkdaService {

	public static final String TBLGRJKDA_SERVICE_IMPL = "www.pdwy.service.impl.TblGrjkdaServiceImpl";
	
	/** 
	* 函 数 名 : save
	* 功能描述：该方法是对TblGrjkda在数据库表中进行添加数据
	* 参数描述  :TblGrjkda的对象 
	* 返回值  :无返回值
	* 数据库表:	TblGrjkda
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void save(TblGrjkda obj);
	
	/** 
	* 函 数 名 : update
	* 功能描述：该方法是对TblGrjkda在数据库表中进行修改数据
	* 参数描述  :TblGrjkda的对象 
	* 返回值  :无返回值
	* 数据库表:	TblGrjkda
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void update(TblGrjkda obj);
	
	/** 
	* 函 数 名 : delete
	* 功能描述：该方法是对TblGrjkda在数据库表中进行删除数据
	* 参数描述  :TblGrjkda的对象 
	* 返回值  :无返回值
	* 数据库表:	TblGrjkda
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void delete(Integer id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对TblGrjkda在数据库表中查询所有的数据
	* 参数描述  : 无
	* 返回值  : List<TblGrjkda>
	* 数据库表:	TblGrjkda
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public List<TblGrjkda> getAllDate();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对TblGrjkda在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TblGrjkda
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public Pager getDataByPager(Pager p);
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对TblGrjkda在数据库表中根据id查询单行数据
	* 参数描述  : TblGrjkda的id
	* 返回值  : TblGrjkda对象
	* 数据库表:	TblGrjkda
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public TblGrjkda getOneById(Integer id);

	public Pager getPagerByCriteria(Pager p);
	
	/** 
	* 函 数 名 : deleteById
	* 功能描述：该方法批量或单独删除TblGrjkda在数据库表中的数据	
	* 参数描述  : id 可以是单个也可以是多个 如1，2，3
	* 返回值  :void
	* 数据库表:	TblGrjkda
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	*/
	public void deleteById(String id);
	
	public List<String> getGrdahsByIds(String ids);

	public List<String> getXmsByIds(String ids);
	
}
