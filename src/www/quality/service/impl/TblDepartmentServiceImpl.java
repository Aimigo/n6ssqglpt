package www.quality.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblDepartmentDao;
import www.quality.model.TblDepartment;
import www.quality.service.TblDepartmentService;
import www.quality.util.Pager;


/**
*类的描述:实现TblDepartmentService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblDepartmentService.TBLDEPARTMENT_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblDepartmentServiceImpl implements TblDepartmentService {

	@Resource(name=TblDepartmentDao.TBLDEPARTMENT_DAO_IMPL)
	private TblDepartmentDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblDepartment obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblDepartment obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblDepartment> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblDepartment.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblDepartment getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblDepartment.class);
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("id"));
	}
	
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblDepartment.class);
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("id"));
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

	public List<TblDepartment> getByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblDepartment.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		return dao.findByCriteria(criteria);
	}

}
