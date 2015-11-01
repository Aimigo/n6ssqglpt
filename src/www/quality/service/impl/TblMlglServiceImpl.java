package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import www.quality.dao.TblMlflDao;
import www.quality.dao.TblMlglDao;
import www.quality.model.TblMlgl;
import www.quality.service.TblMlglService;
import www.quality.util.Pager;


/**
*类的描述:实现TblMlglService(目录管理表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblMlglService.TBLMLGL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblMlglServiceImpl implements TblMlglService {

	@Resource(name=TblMlglDao.TBLMLGL_DAO_IMPL)
	private TblMlglDao dao;
	@Resource(name=TblMlflDao.TBLMLFL_DAO_IMPL)
	private TblMlflDao fldao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblMlgl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblMlgl obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblMlgl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblMlgl.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblMlgl getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMlgl.class);
		Map<String, String> params = p.getParams();
		String bt=params.get("bt");
		if(null!=bt&&!"".equals(bt)){
			criteria.add(Restrictions.like("bt", bt, MatchMode.ANYWHERE));
		}
		String flid=params.get("flid");
		if(null!=flid&&!"".equals(flid)){
			criteria.add(Restrictions.eq("flid", Integer.parseInt(flid)));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime=params.get("begintime");
		if(null!=begintime&&!"".equals(begintime)){
			try {
				criteria.add(Restrictions.ge("scsj", sdf.parse(begintime+" 00:00:00")));
			} catch (ParseException e) {
				System.err.println("开始时间格式化错误！");
			}
		}
		String endtime=params.get("endtime");
		if(null!=endtime&&!"".equals(endtime)){
			try {
				criteria.add(Restrictions.le("scsj", sdf.parse(endtime+" 23:59:59")));
			} catch (ParseException e) {
				System.err.println("结束时间格式化错误！");
			}
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("scsj"));
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
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMlgl.class);
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
		List toplist=dao.findBySql("select flid,count(*) from tblmlgl group by flid order by count(*) desc", false);
		
		List total=dao.findBySql("select count(*) from tblmlgl", false);
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
