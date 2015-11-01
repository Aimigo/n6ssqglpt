package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblMdclDao;
import www.quality.dao.TblMdflDao;
import www.quality.model.TblMdcl;
import www.quality.service.TblMdclService;
import www.quality.util.Pager;


/**
*类的描述:实现TblMdclService(矛盾处理表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblMdclService.TBLMDCL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblMdclServiceImpl implements TblMdclService {

	@Resource(name=TblMdclDao.TBLMDCL_DAO_IMPL)
	private TblMdclDao dao;
	@Resource(name=TblMdflDao.TBLMDFL_DAO_IMPL)
	private TblMdflDao fldao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblMdcl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblMdcl obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblMdcl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblMdcl.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblMdcl getOneById(Integer id) {		
		return dao.findById(id);
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMdcl.class);
		Map<String, String> params = p.getParams();
		String bt=params.get("bt");
		if(null!=bt&&!"".equals(bt)){
			criteria.add(Restrictions.like("bt", bt, MatchMode.ANYWHERE));
		}
		
		String flid =params.get("flid");
		if(null!=flid&&!"".equals(flid)){
			criteria.add(Restrictions.eq("flid", Integer.parseInt(flid)));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime=params.get("begintime");
		if(null!=begintime&&!"".equals(begintime)){
			try {
				criteria.add(Restrictions.ge("txsj", sdf.parse(begintime+" 00:00:00")));
			} catch (ParseException e) {
				System.err.println("开始时间格式化错误！");
			}
		}
		String endtime=params.get("endtime");
		if(null!=endtime&&!"".equals(endtime)){
			try {
				criteria.add(Restrictions.le("txsj", sdf.parse(endtime+" 23:59:59")));
			} catch (ParseException e) {
				System.err.println("结束时间格式化错误！");
			}
		}
		
		String type=params.get("type");
		if("1".equals(type)){
			String userid=params.get("userid");
			criteria.add(Restrictions.eq("cjrid", Integer.parseInt(userid)));
		}else if("2".equals(type)){
			String wg=params.get("wg");
			String[] wgs = wg.split(", ");
			criteria.add(Restrictions.in("wg", wgs));
		}else if("3".equals(type)){
			String dkbm=params.get("dkbm");
			criteria.add(Restrictions.eq("dkbm",Integer.parseInt(dkbm)));
			
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 1));
			dis.add(Restrictions.eq("zt", 2));
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("4".equals(type)){
			String userid=params.get("userid");
			criteria.add(Restrictions.eq("bmldid", Integer.parseInt(userid)));
			
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 2));
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("5".equals(type)){
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("6".equals(type)){
			
		}else{//其他人员只能看到处理过的
			criteria.add(Restrictions.eq("zt", 4));
		}
		
		String flag=params.get("flag");//是否处理过
		if("no".equals(flag)){
			criteria.add(Restrictions.ne("zt", 4));
		}else if("yes".equals(flag)){
			criteria.add(Restrictions.eq("zt", 4));
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
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMdcl.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("bt"));
		return dao.findByDetachedCriteria(criteria);
	}

	public List<TblMdcl> getListByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMdcl.class);
		Map<String, String> params = p.getParams();
		String bt=params.get("bt");
		if(null!=bt&&!"".equals(bt)){
			criteria.add(Restrictions.like("bt", bt, MatchMode.ANYWHERE));
		}
		
		String flid =params.get("flid");
		if(null!=flid&&!"".equals(flid)){
			criteria.add(Restrictions.eq("flid", Integer.parseInt(flid)));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime=params.get("begintime");
		if(null!=begintime&&!"".equals(begintime)){
			try {
				criteria.add(Restrictions.ge("txsj", sdf.parse(begintime+" 00:00:00")));
			} catch (ParseException e) {
				System.err.println("开始时间格式化错误！");
			}
		}
		String endtime=params.get("endtime");
		if(null!=endtime&&!"".equals(endtime)){
			try {
				criteria.add(Restrictions.le("txsj", sdf.parse(endtime+" 23:59:59")));
			} catch (ParseException e) {
				System.err.println("结束时间格式化错误！");
			}
		}
		
		String type=params.get("type");
		if("1".equals(type)){
			String userid=params.get("userid");
			criteria.add(Restrictions.eq("cjrid", Integer.parseInt(userid)));
		}else if("2".equals(type)){
			String wg=params.get("wg");
			String[] wgs = wg.split(",");
			criteria.add(Restrictions.in("wg", wgs));
		}else if("3".equals(type)){
			String dkbm=params.get("dkbm");
			criteria.add(Restrictions.eq("dkbm",Integer.parseInt(dkbm)));
			
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 1));
			dis.add(Restrictions.eq("zt", 2));
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("4".equals(type)){
			String userid=params.get("userid");
			criteria.add(Restrictions.eq("bmldid", Integer.parseInt(userid)));
			
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 2));
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("5".equals(type)){
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("6".equals(type)){
			
		}else{//其他人员只能看到处理过的
			criteria.add(Restrictions.eq("zt", 4));
		}
		
		String flag=params.get("flag");//是否处理过
		if("no".equals(flag)){
			criteria.add(Restrictions.ne("zt", 4));
		}else if("yes".equals(flag)){
			criteria.add(Restrictions.eq("zt", 4));
		}
		
		criteria.addOrder(Order.desc("id"));
		return dao.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getSomeDataByPager(Pager page) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		
		DetachedCriteria criteriaAll = DetachedCriteria.forClass(TblMdcl.class);
		criteriaAll.addOrder(Order.desc("id"));
		List<TblMdcl> mdcllist = dao.findByCriteria(criteriaAll);
		if(null!=mdcllist&&mdcllist.size()>0)
			list.add(mdcllist.get(0));
		else
			list.add(null);
		
		//查询所有矛盾个数
		criteriaAll.setProjection(Projections.count("id"));
		List<Integer> listAll = dao.findByDetachedCriteria(criteriaAll);
		if(null!=listAll&&listAll.size()>0)
			list.add(listAll.get(0));
		else
			list.add(0);
		
		//查询所有已解决矛盾个数
		criteriaAll.add(Restrictions.eq("zt", 4));
		criteriaAll.setProjection(Projections.count("id"));
		List<Integer> listYjj = dao.findByDetachedCriteria(criteriaAll);
		if(null!=listYjj&&listYjj.size()>0)
			list.add(listYjj.get(0));
		else
			list.add(0);
		
		//存入未解决矛盾个数
		list.add(Integer.parseInt(list.get(1)+"")-Integer.parseInt(list.get(2)+""));
		
		DetachedCriteria criteria = DetachedCriteria.forClass(TblMdcl.class);
		Map<String, String> params = page.getParams();
		
		String type=params.get("type");
		if("1".equals(type)){
			String userid=params.get("userid");
			criteria.add(Restrictions.eq("cjrid", Integer.parseInt(userid)));
		}else if("2".equals(type)){
			String wg=params.get("wg");
			String[] wgs = wg.split(",");
			criteria.add(Restrictions.in("wg", wgs));
		}else if("3".equals(type)){
			String dkbm=params.get("dkbm");
			criteria.add(Restrictions.eq("dkbm",Integer.parseInt(dkbm)));
			
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 1));
			dis.add(Restrictions.eq("zt", 2));
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("4".equals(type)){
			String userid=params.get("userid");
			criteria.add(Restrictions.eq("bmldid", Integer.parseInt(userid)));
			
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 2));
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("5".equals(type)){
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.eq("zt", 3));
			dis.add(Restrictions.eq("zt", 4));
			
			criteria.add(dis);
		}else if("6".equals(type)){
			
		}else{//其他人员只能看到处理过的
			criteria.add(Restrictions.eq("zt", 4));
		}
		criteria.addOrder(Order.desc("id"));
		
		//查询当前用户未解决的矛盾
		
		if("1".equals(type)){
			criteria.add(Restrictions.eq("zt", -1));
		}else if("2".equals(type)){
			criteria.add(Restrictions.eq("zt", 0));
		}else if("3".equals(type)){
			criteria.add(Restrictions.eq("zt", 1));
		}else if("4".equals(type)){
			criteria.add(Restrictions.eq("zt", 2));
		}else if("5".equals(type)){
			criteria.add(Restrictions.eq("zt", 3));
		}else if("6".equals(type)){

		}
		criteria.setProjection(Projections.count("id"));
		List<Integer> listGr = dao.findByDetachedCriteria(criteria);
		if(null!=listGr&&listGr.size()>0)
			list.add(listGr.get(0));
		else
			list.add(0);
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getTop() {

		Map topmap=new LinkedHashMap<String,Integer>();
		List toplist=dao.findBySql("select flid,count(*) from tblmdcl group by flid order by count(*) desc", false);
		
		List total=dao.findBySql("select count(*) from tblmdcl", false);
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
