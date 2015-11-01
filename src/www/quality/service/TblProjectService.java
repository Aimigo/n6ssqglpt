package www.quality.service;

import java.util.List;


import www.quality.model.TblProject;
import www.quality.util.Pager;

public interface TblProjectService {

	public static final String TBLPROJECT_SERVICE_IMPL = "www.quality.service.impl.TblProjectServiceImpl";
	
	/** 
	* 函 数 名 : saveProject
	* 功能描述：该方法是对Tblproject在数据库表中进行添加数据
	* 参数描述  :Tblproject的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLPROJECT
	*/
	public void saveProject(TblProject pro);
	
	/** 
	* 函 数 名 : updateProject
	* 功能描述：该方法是对Tblproject在数据库表中进行修改数据
	* 参数描述  :Tblproject的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLPROJECT
	*/
	public void updateProject(TblProject pro);
	
	/** 
	* 函 数 名 : deleteProject
	* 功能描述：该方法是对Tblproject在数据库表中进行删除数据
	* 参数描述  :Tblproject的对象 
	* 返回值  :无返回值
	* 数据库表:	TBLPROJECT
	*/
	public void deleteProject(Integer id);
	
	/** 
	* 函 数 名 : getAllData
	* 功能描述：该方法是对Tblproject在数据库表中查询所有的数据
	* 参数描述  : Tblproject的对象 
	* 返回值  : List<Tblproject>
	* 数据库表:	TBLproject
	*/
	public List<TblProject> getAlldata();
	
	/** 
	* 函 数 名 : getDataByPager
	* 功能描述：该方法是对Tblproject在数据库表中分页查询数据,根据需要可以添加查询条件
	* 参数描述  : 分页Pager对象
	* 返回值  : 分页对象Pager
	* 数据库表:	TBLPROJECT
	*/
	public Pager getAllDataByPage(Pager p,String cond,String nr);
	
	/** 
	* 函 数 名 : getOneById
	* 功能描述：该方法是对Tblproject在数据库表中根据id查询单行数据
	* 参数描述  : Tblproject的id
	* 返回值  : Tblproject对象
	* 数据库表:	TBLPROJECT
	*/
	public TblProject getOneById(Integer id);
	
	void deleteall(String ids);
	
	TblProject findById(int id);

	public TblProject getProByCode(String code);

	public Pager flByhp(Pager p, String string);

	public Pager getPagerByCriteria(Pager p,String name,String col,String px);

	public List<TblProject> findByName(String name);

	public List<String> getNamesByIds(String ids);

}
