package www.quality.util;

import java.util.ArrayList;
import java.util.List;

/**
*类的描述: 一个简单的封装HQL查询语句的工具类
*作者:胡域
*创建日期:2012-03-19
*
*修改人
*修改日期
*修改原因描述
*/
public class HqlHelper {
	private String fromClause; // From子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句
	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	@SuppressWarnings("rawtypes")
	public HqlHelper(Class clazz, String alias) {
		this.fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	public HqlHelper addWhereCondition(String condition, Object... params) {
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		if (params != null && params.length > 0) {
			for (Object param : params) {
				parameters.add(param);
			}
		}
		return this;
	}

	public HqlHelper addWhereCondition(boolean append, String condition,
			Object... params) {
		if (append) {
			addWhereCondition(condition, params);
		}
		return this;
	}

	public HqlHelper addOrderByProperty(String propertyName, boolean isAsc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName
					+ (isAsc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (isAsc ? " ASC" : " DESC");
		}
		return this;
	}

	public HqlHelper addOrderByProperty(boolean append, String propertyName,
			boolean isAsc) {
		if (append) {
			addOrderByProperty(propertyName, isAsc);
		}
		return this;
	}

	public String getQueryListHql() {
		return fromClause + whereClause + orderByClause;
	}

	public String getQueryCountHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause; // 没有orderBy子句
	}

	public List<Object> getParameters() {
		return parameters;
	}

}
