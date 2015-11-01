package www.quality.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import www.quality.util.HqlHelper;
import www.quality.util.Pager;


/**
*类的描述:该类封装的对数据库进行增删改的基本方法,所有model所对应的Dao继承该接口
*作者:杜长吉
*创建日期 2012-03-19
*
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("rawtypes")
public interface BaseDao<T extends Serializable, PK extends Serializable> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * 保存实体集合
	 * 
	 * @param entity
	 */
	void saveCollection(Collection<T> entities);

	/**
	 * 根据ID删除实体
	 * 
	 * @param id
	 */
	void deleteById(PK id);
	
	/**
	 * 根据ID集合删除实体
	 * 
	 * @param id
	 */
	void deleteByIds(PK[] ids);
	
	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 更新实体集合
	 * 
	 * @param entity
	 */
	void updateCollection(Collection<T> entities);
	
	/**
	 * 保存或更新实体
	 * @param entity
	 */
	void saveOrUpdate(T entity);
	
	/**
	 * 保存或更新实体集合
	 * @param entities
	 */
	void saveOrUpdateCollection(Collection<T> entities);

	/**
	 * 通过ID查询实体
	 * 
	 * @param id
	 * @return
	 */
	T findById(PK id);

	/**
	 * 通过ID集合查询实体
	 * 
	 * @param ids
	 * @return
	 */
	List<T> findByIds(PK[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> getAll();

	/**
	 * 通过HqlHelper分页查询，结果封装成PageBean（借助HqlHelper）
	 * 
	 * @param pageNum
	 * @param hqlHelper
	 * @return
	 */
	Pager findPageBeanByHqlHelper(int pageNum, int pageSize,HqlHelper hqlHelper);
	
	/**
	 * 通过Hql语句分页查询，结果封装成PageBean（不借助HqlHelper，自己写Hql语句）
	 * 
	 * @param pageNum
	 * @param hql
	 * @return
	 */
	Pager findPageBeanByHql(int pageNum, int pageSize ,String hql);
	
	/**
	 * 通过HqlHelper查询，不带分页
	 * 
	 * @param hqlHelper
	 * @return
	 */
	List<T> findByHqlHelper(HqlHelper hqlHelper);
	
	/**
	 * 通过Hql语句分页查询，确定唯一记录
	 * 
	 * @param hql
	 * @return
	 */
	T findUniqueByHql(String hql);
	
	/**
	 * 通过Hql语句分页查询，不带分页
	 * 
	 * @param hql
	 * @return
	 */
	List<T> findByHql(String hql);
	
	/**
	 * 通过Hql语句及指定到Hql语句中的参数查询，不带分页
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	List<T> findByHql(String hql, Object[] params);
	
	/**
	 * 通过给定的条件分页查询
	 * 
	 * @param pageNum
	 * @param criteria
	 * @return
	 */
	List<T> findByCriteria(int pageNum, int pageSize, DetachedCriteria criteria);
	
	/**
	 * 通过给定的条件查询，不带分页
	 * 
	 * @param criteria
	 * @return
	 */
	List<T>	findByCriteria(DetachedCriteria criteria);	
	
	/**
	 * 通过给定的条件查询，不带分页,不带泛型
	 * @param criteria
	 * @return
	 */
	public List	findByDetachedCriteria(DetachedCriteria criteria);
	
	/**
	 * 投影查询分页数据
	 * @param d
	 * @param p
	 * @param order
	 * @return
	 */
	public Pager getPagerByCriteria(DetachedCriteria d, Pager p, Order order);
	/**使用HSQL语句直接增加、更新、删除实体
     * @param queryString
     * @return
     */
    public int bulkUpdate(String queryString);

    /**使用带参数的HSQL语句增加、更新、删除实体
     * @param queryString
     * @param values
     * @return
     */
    public int bulkUpdate(String queryString, Object [] values);
    
    /**强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
     */
    public void flush();
    
    /**使用HSQL语句检索数据，返回 Iterator
     * @param queryString
     * @param isDefault true  在 query前面加上 from entityName/false 不加
     * @return
     */
	public Iterator iterate(String queryString,boolean isDefault);

    /**使用带参数HSQL语句检索数据，返回 Iterator
     * @param queryString
     * @param isDefault true  在 query前面加上 from entityName/false 不加
     * @param values
     * @return
     */
	public Iterator iterate(String queryString,boolean isDefault, Object [] values);

    /** 关闭检索返回的 Iterator
     * @param it
     */
	public void closeIterator(Iterator it);
    
    /**使用SQL语句检索数据
     * @param queryString
	 * @param isDefault true  是否自动转换实体
     * @return
     */
	public List findBySql(String queryString,boolean isDefault);
    
	/**使用带参数的SQL语句检索数据
     * @param queryString
     * @param isDefault true  是否自动转换实体
     * @param values
     * @return
     */
	public List findBySql(String queryString,boolean isDefault, Object [] values);
    
    /**使用SQL语句检索数据
     * @param queryString
     * @param isDefault true  是否自动转换实体
     * @param firstResult	数据起始点
     * @param maxResults	数据个数
     * @return
     */
    public List findBySql(String queryString,boolean isDefault,int firstResult,int maxResults);

    /**使用SQL语句检索数据
     * @param queryString
	 * @param isDefault true  是否自动转换实体
     * @param firstResult	数据起始点
     * @param maxResults	数据个数
     * @param values
     * @return
     */
	public List findBySql(String queryString,boolean isDefault,Object [] values,int firstResult,int maxResults);
    
    
}
