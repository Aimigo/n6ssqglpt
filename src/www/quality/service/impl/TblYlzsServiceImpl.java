package www.quality.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

import www.quality.dao.TblYlzsDao;
import www.quality.dao.TblYlzsflDao;
import www.quality.model.TblYjzs;
import www.quality.service.TblYlzsService;
import www.quality.util.Pager;


/**
*类的描述:实现TblYlzsService(医疗知识表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblYlzsService.TBLYLZS_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblYlzsServiceImpl implements TblYlzsService {

	@Resource(name=TblYlzsDao.TBLYLZS_DAO_IMPL)
	private TblYlzsDao dao;
	@Resource(name=TblYlzsflDao.TBLYLZSFL_DAO_IMPL)
	private TblYlzsflDao fldao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblYjzs obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblYjzs obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblYjzs> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblYjzs.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblYjzs getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblYjzs.class);
		Map<String, String> params = p.getParams();
		String bt=params.get("bt");
		if(null!=bt&&!"".equals(bt)){
			criteria.add(Restrictions.like("bt", bt,MatchMode.ANYWHERE));
		}
		String nr=params.get("nr");
		if(null!=nr&&!"".equals(nr)){
			criteria.add(Restrictions.like("nr", nr,MatchMode.ANYWHERE));
		}
		String flid=params.get("flid");
		if(null!=flid&&!"".equals(flid)){
			criteria.add(Restrictions.eq("flid", Integer.parseInt(flid)));
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
	public List<String> getBtsByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblYjzs.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("bt"));
		return dao.findByDetachedCriteria(criteria);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getTop() {

		Map topmap=new LinkedHashMap<String,Integer>();
		List toplist=dao.findBySql("select flid,count(*) from tblyjzs group by flid order by count(*) desc", false);
		
		List total=dao.findBySql("select count(*) from tblyjzs", false);
		topmap.put("total", total.get(0));
		Integer top4total=0;
		for(int i=0;i<toplist.size();i++){
			if(i>3){topmap.put("其他", Integer.valueOf(total.get(0).toString())-top4total); break;}
			Object[] pp=(Object[])toplist.get(i);
			topmap.put(fldao.findById(Integer.valueOf(pp[0].toString())).getName(), Integer.valueOf(pp[1].toString()));
			top4total+=Integer.valueOf(pp[1].toString());
		}
		return topmap;
	
	}
}
