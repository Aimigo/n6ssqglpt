package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblUserAndRoleDao;
import www.quality.model.TblUserAndRole;

/**
*类的描述:该类是实现所对应TblUserAndRoleDao(用户和角色关联表)接口的方法实现
*作者:杜长吉
*创建日期 2012-03-19
*
*修改人
*修改日期
*修改原因描述
*/
@Repository(TblUserAndRoleDao.TBLUSERANDROLE_DAO_IMPL)
public class TblUserAndRoleDaoImpl extends
		BaseDaoImpl<TblUserAndRole, Serializable> implements TblUserAndRoleDao {

}
