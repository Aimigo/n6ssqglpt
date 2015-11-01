package www.quality.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;

import www.quality.dao.TblUserDao;
import www.quality.model.TblUser;


/**
*类的描述:该类是实现所对应TblUserDao接口的方法实现
*作者:杜长吉
*创建日期 2012-03-19
*
*修改人
*修改日期
*修改原因描述
*/
@Repository(TblUserDao.TBLUSER_DAO_IMPL)
public class TblUserDaoImpl extends BaseDaoImpl<TblUser, Serializable> implements TblUserDao {

}
