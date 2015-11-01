package www.quality.service;

import java.text.ParseException;
import java.util.List;

import www.quality.model.TblGrjl;
import www.quality.model.TblQyzp;
import www.quality.util.Pager;

/**
*类的描述:TblQyzp(企业招聘)所对应的服务方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblQyzpService {

	public static final String TBLQYZP_SERVICE_IMPL = "www.pdwy.service.impl.TblQyzpServiceImpl";
	
	/** 
	* 函 数 名 : save
	* 功能描述：该方法是对TblQyzp在数据库表中进行添加数据
	* 参数描述  :TblQyzp的对象 
	* 返回值  :无返回值
	* 数据库表:	TblQyzp
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void save(TblQyzp obj);
	
	/** 
	* 函 数 名 : update
	* 功能描述：该方法是对TblQyzp在数据库表中进行修改数据
	* 参数描述  :TblQyzp的对象 
	* 返回值  :无返回值
	* 数据库表:	TblQyzp
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void update(TblQyzp obj);
	
	/** 
	* 函 数 名 : delete
	* 功能描述：该方法是对TblQyzp在数据库表中进行删除数据
	* 参数描述  :TblQyzp的对象 
	* 返回值  :无返回值
	* 数据库表:	TblQyzp
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void delete(Integer id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对TblQyzp在数据库表中查询所有的数据
	* 参数描述  : 无
	* 返回值  : List<TblQyzp>
	* 数据库表:	TblQyzp
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public List<TblQyzp> getAllDate();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对TblQyzp在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TblQyzp
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public Pager getDataByPager(Pager p);
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对TblQyzp在数据库表中根据id查询单行数据
	* 参数描述  : TblQyzp的id
	* 返回值  : TblQyzp对象
	* 数据库表:	TblQyzp
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public TblQyzp getOneById(Integer id);

	public Pager getPagerByCriteria(Pager p) throws ParseException;
	
	/** 
	* 函 数 名 : deleteById
	* 功能描述：该方法批量或单独删除TblQyzp在数据库表中的数据	
	* 参数描述  : id 可以是单个也可以是多个 如1，2，3
	* 返回值  :void
	* 数据库表:	TblQyzp
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	*/
	public void deleteById(String id);

	public List<String> getZpzwsByIds(String ids);

	public List<TblQyzp> getDataByGrjl(TblGrjl grjl);
}
