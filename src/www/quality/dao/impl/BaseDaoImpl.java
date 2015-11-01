package www.quality.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import www.quality.dao.BaseDao;
import www.quality.util.HqlHelper;
import www.quality.util.Pager;

/**
 * 类的描述:该类封装的对数据库进行增删改的基本方法,所有model所对应的DaoImpl继承该类 作者:杜长吉 创建日期 2012-03-19
 * 
 * 修改人 修改日期 修改原因描述
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class BaseDaoImpl<T extends Serializable, PK extends Serializable>
		extends HibernateDaoSupport implements BaseDao<T, PK> {

	private Class<T> clazz;
	private String FROM_ENTITY;

	public BaseDaoImpl() {
		// 获取有泛型信息的父类Class
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 获取这个类型参数的真实类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		FROM_ENTITY = " from " + this.clazz.getName() + " ";
	}

	/**
	 * 注入sessionFactory
	 * 
	 * @param sessionFactory
	 */
	@Resource(name = "sessionFactory")
	public void setSessionFactoryZR(SessionFactory sessionFactory) {
		// 调用父类的设置sessionFactory的方法
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 保存实体集合
	 * 
	 * @param entity
	 */
	public void saveCollection(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * 根据ID删除实体
	 * 
	 * @param id
	 */
	public void deleteById(PK id) {
		T o = findById(id);
		if (null != o) {
			getHibernateTemplate().delete(o);
		}
		/*
		 * 实体不存在异常 else { throw new
		 * RuntimeException("ID为【"+id+"】的"+clazz.getSimpleName()+"不存在！"); }
		 */
	}

	/**
	 * 根据ID集合删除实体
	 * 
	 * @param ids
	 */
	public void deleteByIds(PK[] ids) {
		List<T> entities = findByIds(ids);
		if (null != entities && entities.size() > 0) {
			getHibernateTemplate().deleteAll(entities);
		}
	}

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 更新实体集合
	 * 
	 * @param entities
	 */
	public void updateCollection(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * 保存或更新实体
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 保存或更新实体集合
	 * 
	 * @param entities
	 */
	public void saveOrUpdateCollection(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * 通过ID查询实体
	 * 
	 * @param id
	 * @return
	 */
	public T findById(PK id) {
		if (id != null) {
			return getHibernateTemplate().get(clazz, id);
		}
		return null;
	}

	/**
	 * 通过ID集合查询实体
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(PK[] ids) {
		StringBuffer sb = new StringBuffer("(");
		if (ids == null || ids.length < 1) {
			return null;
		}
		for (PK id : ids) {
			sb.append("'" + id + "',");
		}
		String inClause = sb.substring(0, sb.length() - 1);
		String hql = "FROM " + clazz.getSimpleName() + " AS t WHERE t.id IN "
				+ inClause;
		return getHibernateTemplate().find(hql);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> getAll() {
		return getHibernateTemplate().loadAll(clazz);
	}

	/**
	 * 通过HqlHelper分页查询，结果封装成PageBean（借助HqlHelper）
	 * 
	 * @param pageNum
	 * @param hqlHelper
	 * @return
	 */
	public Pager findPageBeanByHqlHelper(int pageNum, int pageSize,
			HqlHelper hqlHelper) {
		List<Object> parameters = hqlHelper.getParameters();
		// 每页显示多少条数据
		int rowNumPerPage = pageSize;

		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(hqlHelper.getQueryListHql());
		if (parameters != null) {
			// 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * rowNumPerPage);
		listQuery.setMaxResults(rowNumPerPage);
		List list = listQuery.list(); // 执行查询

		// 查询总记录数
		Query countQuery = getSession().createQuery(
				hqlHelper.getQueryCountHql());
		if (parameters != null) {
			// 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		int count = ((Long) countQuery.uniqueResult()).intValue();

		return new Pager(pageNum, rowNumPerPage, count, list); // 执行查询
	}

	/**
	 * 通过Hql语句分页查询，结果封装成PageBean（不借助HqlHelper，自己写Hql语句）
	 * 
	 * @param pageNum
	 * @param hql
	 * @return
	 */
	public Pager findPageBeanByHql(int pageNum, int pageSize, String hql) {
		// 每页显示多少条数据
		int rowNumPerPage = pageSize;

		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(hql);
		listQuery.setFirstResult((pageNum - 1) * rowNumPerPage);
		listQuery.setMaxResults(rowNumPerPage);
		// 执行查询
		List list = listQuery.list();

		// 查询总记录数
		Query countQuery = getSession().createQuery(hql);
		int count = countQuery.list().size();
		Pager pager = new Pager(pageNum, rowNumPerPage, count, list);
		pager.setTotalPages(((int) Math
				.ceil((double) count / (double) pageSize)));
		return pager;
	}

	/**
	 * 通过HqlHelper查询，不带分页
	 * 
	 * @param hqlHelper
	 * @return
	 */
	public List<T> findByHqlHelper(HqlHelper hqlHelper) {
		List<Object> parameters = hqlHelper.getParameters();
		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(hqlHelper.getQueryListHql());
		if (parameters != null) {
			// 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		return listQuery.list();
	}

	/**
	 * 通过Hql语句分页查询，确定唯一记录
	 * 
	 * @param hql
	 * @return
	 */
	public T findUniqueByHql(String hql) {
		List<T> list = findByHql(hql);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

	/**
	 * 通过Hql语句分页查询，不带分页
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> findByHql(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 通过Hql语句及指定到Hql语句中的参数查询，不带分页
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> findByHql(String hql, Object[] params) {
		return this.getHibernateTemplate().find(hql, params);
	}

	/**
	 * 通过给定的条件分页查询
	 * 
	 * @param pageNum
	 * @param criteria
	 * @return
	 */
	public List<T> findByCriteria(int pageNum, int pageSize,
			DetachedCriteria criteria) {
		return this.getHibernateTemplate().findByCriteria(criteria,
				(pageNum - 1) * pageSize, pageSize);
	}

	/**
	 * 通过给定的条件查询，不带分页
	 * 
	 * @param criteria
	 * @return
	 */
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * 不带泛型的查询
	 * 
	 * @param criteria
	 * @return
	 */
	public List findByDetachedCriteria(DetachedCriteria criteria) {
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unused")
	public Pager getPagerByCriteria(DetachedCriteria d, Pager p, Order order) {
		d.setProjection(Projections.rowCount());
		Integer count = (Integer) getHibernateTemplate().findByCriteria(d).get(
				0);
		p.setTotalRows(count);
		if (order != null)
			d.addOrder(order);
		d.setProjection(null);
		int n = p.getFirstRow();
		List data = getHibernateTemplate().findByCriteria(d, p.getFirstRow(),
				p.getPageRows());
		p.setTotalPages(((int) Math.ceil((double) count
				/ (double) p.getPageRows())));
		p.setData(data);
		return p;
	}

	public int bulkUpdate(String queryString) {
		return getHibernateTemplate().bulkUpdate(queryString);
	}

	public int bulkUpdate(String queryString, Object[] values) {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public Iterator iterate(String queryString, boolean isDefault) {
		if (isDefault)
			queryString = FROM_ENTITY.concat(queryString);
		return getHibernateTemplate().iterate(queryString);
	}

	
	public Iterator iterate(String queryString, boolean isDefault,
			Object[] values) {
		if (isDefault)
			queryString = FROM_ENTITY.concat(queryString);
		return getHibernateTemplate().iterate(queryString, values);
	}

	public void closeIterator(Iterator it) {
		getHibernateTemplate().closeIterator(it);
	}

	public List findBySql(String queryString, boolean isDefault) {
		return findBySql(queryString, isDefault, null, -1, -1);
	}

	public List findBySql(String queryString, boolean isDefault, Object[] values) {
		return findBySql(queryString, isDefault, values, -1, -1);
	}

	public List findBySql(String queryString, boolean isDefault,
			int firstResult, int maxResults) {
		return findBySql(queryString, isDefault, null, firstResult, maxResults);
	}

	public List findBySql(final String queryString, final boolean isDefault,
			final Object[] values, final int firstResult, final int maxResults) {

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				SQLQuery query = arg0.createSQLQuery(queryString);
				if (values != null && values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				if (firstResult >= 0 && maxResults > 0) {
					query.setFirstResult(firstResult);
					query.setMaxResults(maxResults);
				}
				if (isDefault)
					query.addEntity(clazz);
				return query.list();
			}
		});
	}
}
