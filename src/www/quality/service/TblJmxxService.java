package www.quality.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import www.quality.model.TblCbxx;
import www.quality.model.TblJmxx;
import www.quality.model.TblJtxx;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.model.TblXsxx;
import www.quality.util.Pager;

/**
*类的描述:TblJmxx(居民信息)所对应的服务方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblJmxxService {

	public static final String TBLJMXX_SERVICE_IMPL = "www.pdwy.service.impl.TblJmxxServiceImpl";
	
	/** 
	* 函 数 名 : save
	* 功能描述：该方法是对TblJmxx在数据库表中进行添加数据
	* 参数描述  :TblJmxx的对象 
	* 返回值  :无返回值
	* 数据库表:	TblJmxx
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void save(TblJmxx obj);
	
	/** 
	* 函 数 名 : update
	* 功能描述：该方法是对TblJmxx在数据库表中进行修改数据
	* 参数描述  :TblJmxx的对象 
	* 返回值  :无返回值
	* 数据库表:	TblJmxx
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void update(TblJmxx obj);
	
	/** 
	* 函 数 名 : delete
	* 功能描述：该方法是对TblJmxx在数据库表中进行删除数据
	* 参数描述  :TblJmxx的对象 
	* 返回值  :无返回值
	* 数据库表:	TblJmxx
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public void delete(Integer id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对TblJmxx在数据库表中查询所有的数据
	* 参数描述  : 无
	* 返回值  : List<TblJmxx>
	* 数据库表:	TblJmxx
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-07
	* 修 改 人: 
	* 日    期: 
	*/
	public List<TblJmxx> getAllDate();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对TblJmxx在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TblJmxx
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public Pager getDataByPager(Pager p);
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对TblJmxx在数据库表中根据id查询单行数据
	* 参数描述  : TblJmxx的id
	* 返回值  : TblJmxx对象
	* 数据库表:	TblJmxx
	* 创 建 人: 杜长吉
	* 日    期: 2012-05-09
	* 修 改 人: 
	* 日    期: 
	*/
	public TblJmxx getOneById(Integer id);

	/** 
	* 函 数 名 : getPagerByCriteria
	* 功能描述：该方法通过条件对TblJmxx在数据库表中的多个数据进行分页查找
	* 参数描述  : Pager 根据需要新增参数
	* 返回值  :Pager
	* 数据库表:	TblJmxx
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	 * @param name 
	*/
	public Pager getPagerByCriteria(Pager pager, String name);

	/** 
	* 函 数 名 : deleteById
	* 功能描述：该方法批量或单独删除TblJmxx在数据库表中的数据	
	* 参数描述  : id 可以是单个也可以是多个 如1，2，3
	* 返回值  :void
	* 数据库表:	TblJmxx
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	*/
	public void deleteById(String id);

	/** 
	* 函 数 名 : getByName
	* 功能描述：通过名称精确找到对应数据
	* 参数描述  : 
	* 返回值  :List<TblJmxx>
	* 数据库表:	TblJmxx
	* 创 建 人: 李留洋
	* 日    期: 2013-01-24
	* 修 改 人: 
	* 日    期: 
	*/
	public List<TblJmxx> getByName(String name);

	public List<TblJmxx> getByDateAndName(Date date,String name) throws ParseException;

	public List findByHql(String hql);

	public List<TblTsryfl> getSpecialType();

	public List<TblTsryflzd> getFlzdByFlid(String flid);

	public List<TblTsryflzd> getZdesByFlid(Integer tsryflid);

	public Pager getPagerByCriteria(Pager p);

	public void saveTsry(TblTsrysj sj);

	public void saveJtxx(TblJtxx jtxx);

	public void saveCbxx(TblCbxx cbxx);

	public void saveXsxx(TblXsxx xsxx);

	public Map<String,List<TblTsryflzd>> getTsryInfoBySFZ(String idNumber);

	public TblJtxx getHuzhuBySFZ(String idNumber);

	public TblCbxx getCanbaoBySFZ(String idNumber);

	public TblXsxx getXueshengBySFZ(String idNumber);

	public String delByIds(String ids);

	public void delTsryByIdcart(String idNumber);

	public void delHzxxByIdcart(String idNumber);

	public void delCbxxByIdcart(String idNumber);

	public void delXsxxByIdcart(String idNumber);

	public List<TblTsryfl> getAllTrsy();

	public TblTsryfl getTsryflById(Integer parseInt);

	public TblJmxx getOneByIdCard(String idcard);

	public void saveJmxxList(ArrayList<TblJmxx> jmxxes);

	public void saveCbxxList(ArrayList<TblCbxx> cbxxes);

	public void saveJtxxList(ArrayList<TblJtxx> jtxxes);

	public void saveXsxxList(ArrayList<TblXsxx> xsxxes);

	public Integer getCountByAge(int i,int j,String grid);

	public List<TblJmxx> getByIdcart(String trim);

	public List getTjData();

	public List getTjData2();

	public Boolean findHjgx(String ryid, String ar, String an);

	public List getXqdz(String ints, String road, String ridgepole,
			String element,String grid);

	public List getTjData3(String grid);

	public List getTjData4();

	public String getTjData5(String type, String data, String grid);

	public List<TblJmxx> getByIdcart2(String idNumber, String jmxxid);

}
