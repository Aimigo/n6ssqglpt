package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import www.quality.dao.TblTsjlDao;
import www.quality.model.TblTsjl;
import www.quality.service.TblTsjlService;
import www.quality.util.Pager;


/**
*类的描述:实现TblTsjlService(推送记录)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblTsjlService.TBLTSJL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblTsjlServiceImpl implements TblTsjlService {

	@Resource(name=TblTsjlDao.TBLTSJL_DAO_IMPL)
	private TblTsjlDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblTsjl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblTsjl obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblTsjl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblTsjl.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblTsjl getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p,int i){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsjl.class);
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		
		criteria.add(Restrictions.eq("fl", i));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime=params.get("begintime");
		if(null!=begintime&&!"".equals(begintime)){
			try {
				criteria.add(Restrictions.ge("sj", sdf.parse(begintime+" 00:00:00")));
			} catch (ParseException e) {
				System.err.println("开始时间格式化错误！");
			}
		}
		String endtime=params.get("endtime");
		if(null!=endtime&&!"".equals(endtime)){
			try {
				criteria.add(Restrictions.le("sj", sdf.parse(endtime+" 23:59:59")));
			} catch (ParseException e) {
				System.err.println("结束时间格式化错误！");
			}
		}
		return dao.getPagerByCriteria(criteria, p, Order.asc("zt"));
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
		DetachedCriteria criteria = DetachedCriteria.forClass(TblTsjl.class);
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
