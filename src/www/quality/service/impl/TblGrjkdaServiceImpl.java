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

import www.quality.dao.TblGrjkdaDao;
import www.quality.model.TblGrjkda;
import www.quality.service.TblGrjkdaService;
import www.quality.util.Pager;


/**
*类的描述:实现TblGrjkdaService(个人健康档案)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblGrjkdaService.TBLGRJKDA_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblGrjkdaServiceImpl implements TblGrjkdaService {

	@Resource(name=TblGrjkdaDao.TBLGRJKDA_DAO_IMPL)
	private TblGrjkdaDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblGrjkda obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblGrjkda obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblGrjkda> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblGrjkda.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblGrjkda getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrjkda.class);
		Map<String, String> params = p.getParams();
		String grdah=params.get("grdah");
		if(null!=grdah&&!"".equals(grdah)){
			criteria.add(Restrictions.like("grdah", grdah, MatchMode.ANYWHERE));
		}
		String xm=params.get("xm");
		if(null!=xm&&!"".equals(xm)){
			criteria.add(Restrictions.like("xm", xm, MatchMode.ANYWHERE));
		}
		String grsfz=params.get("grsfz");
		if(null!=grsfz&&!"".equals(grsfz)){
			criteria.add(Restrictions.like("grsfz", grsfz, MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("jdrq"));
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
	public List<String> getGrdahsByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrjkda.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("grdah"));
		return dao.findByDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<String> getXmsByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrjkda.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("xm"));
		return dao.findByDetachedCriteria(criteria);
	}

}
