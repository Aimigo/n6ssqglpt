package www.quality.dao;

import java.io.Serializable;

import www.quality.model.TblSyfwgl;



/**
*类的描述:该接口是对TblSyfwglDao(视频分类表)基本方法行为的定义
*作者:杜长吉
*创建日期 2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblSyfwglDao extends BaseDao<TblSyfwgl, Serializable> {

	public static final String TBLSYFWGL_DAO_IMPL = "www.pdwy.dao.impl.TblSyfwglDaoImpl";
}
