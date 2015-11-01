package www.quality.service;

import java.util.List;

import www.quality.model.TblRole;
import www.quality.model.TblUserAndRole;

public interface TblUserAndRoleService {

	public static final String TBLUSERANDROLE_SERVICE_IMPL = "www.quality.service.impl.TblUserAndRoleServiceImpl";
	
	/** 
	* 函 数 名 : saveUandR
	* 功能描述：该方法是对TblUserAndRole在数据库表中进行批量添加数据
	* 参数描述  :TblUserAndRole的集合对象 
	* 返回值  :无返回值
	* 数据库表:	TBLUSERANDROLE
	*/
	public void saveUandR(List<TblUserAndRole> list);
	
	/** 
	* 函 数 名 : updateUAndR
	* 功能描述：该方法是对TblUserAndRole在数据库表中进行批量修改数据
	* 参数描述  :TblUserAndRole的集合对象 和usercode
	* 返回值  :无返回值
	* 数据库表:	TBLUSERANDROLE
	*/
	public void updateUAndR(String usercode,List<TblUserAndRole> list);
	
	/** 
	* 函 数 名 : deleteUAndR
	* 功能描述：该方法是对TblUserAndRole在数据库表中进行批量或单个删除数据
	* 参数描述  :TblUserAndRole的对象 的usercode
	* 返回值  :无返回值
	* 数据库表:	TBLUSERANDROLE
	*/
	public void deleteUAndR(String usercode);
	
	/** 
	* 函 数 名 : getRoleByUsercode
	* 功能描述：该方法是根据usercode来查询TblUserAndRole的rolecode，在根据rolecode来查询role对象
	* 参数描述  :TblUserAndRole的对象 的usercode
	* 返回值  : Tblrole的集合对象
	* 数据库表:	TBLUSERANDROLE,TBLROLE
	*/
	public List<TblRole> getRoleByUsercode(String usercode);
	
}
