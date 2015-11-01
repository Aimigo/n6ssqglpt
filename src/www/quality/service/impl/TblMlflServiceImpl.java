package www.quality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblMlflDao;
import www.quality.model.TblMlfl;
import www.quality.service.TblMlflService;
import www.quality.util.Pager;


/**
*类的描述:实现TblMlflService(目录分类表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblMlflService.TBLMLFL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblMlflServiceImpl implements TblMlflService {

	@Resource(name=TblMlflDao.TBLMLFL_DAO_IMPL)
	private TblMlflDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblMlfl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblMlfl obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblMlfl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblMlfl.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblMlfl getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMlfl.class);
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("id"));
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				this.delete(Integer.parseInt(string));//逐个删除
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getNamesByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMlfl.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("name"));
		return dao.findByDetachedCriteria(criteria);
	}


}
