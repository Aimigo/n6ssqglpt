package www.quality.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblRegionDao;
import www.quality.model.TblRegion;
import www.quality.service.TblRegionService;


/**
*类的描述:实现TblRegionService(行政区划表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblRegionService.TBLREGION_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblRegionServiceImpl implements TblRegionService {

	@Resource(name=TblRegionDao.TBLREGION_DAO_IMPL)
	private TblRegionDao dao;

	public List<TblRegion> getAllDate() {
		return dao.getAll();
	}

	public List<TblRegion> getDataByFcode(String fcode) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblRegion.class);
		if(null==fcode||"".equals(fcode))
			fcode = "0";
		criteria.add(Restrictions.eq("fcode", fcode));
		criteria.add(Restrictions.eq("status", true));
		criteria.addOrder(Order.asc("code"));
		return dao.findByCriteria(criteria);
	}

}
