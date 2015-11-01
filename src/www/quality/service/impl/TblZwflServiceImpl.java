package www.quality.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblZwflDao;
import www.quality.model.TblZwfl;
import www.quality.service.TblZwflService;


/**
*类的描述:实现TblZwflService(职位分类表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblZwflService.TBLZWFL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblZwflServiceImpl implements TblZwflService {

	@Resource(name=TblZwflDao.TBLZWFL_DAO_IMPL)
	private TblZwflDao dao;

	public List<TblZwfl> getAllDate() {
		return dao.getAll();
	}

	public List<TblZwfl> getDataByFcode(String fcode) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblZwfl.class);
		if(null==fcode||"".equals(fcode))
			fcode = "0";
		criteria.add(Restrictions.eq("fcode", fcode));
		criteria.addOrder(Order.asc("code"));
		return dao.findByCriteria(criteria);
	}

}
