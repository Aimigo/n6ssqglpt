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

import www.quality.dao.TblJkzxDao;
import www.quality.dao.TblJkzxflDao;
import www.quality.model.TblJkzx;
import www.quality.service.TblJkzxService;
import www.quality.util.Pager;


/**
*类的描述:实现TblJkzxService(健康咨询表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblJkzxService.TBLJKZX_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblJkzxServiceImpl implements TblJkzxService {

	@Resource(name=TblJkzxDao.TBLJKZX_DAO_IMPL)
	private TblJkzxDao dao;
	@Resource(name=TblJkzxflDao.TBLJKZXFL_DAO_IMPL)
	private TblJkzxflDao fldao;
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblJkzx obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblJkzx obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblJkzx> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblJkzx.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblJkzx getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJkzx.class);
		Map<String, String> params = p.getParams();
		String zxbt=params.get("zxbt");
		if(null!=zxbt&&!"".equals(zxbt)){
			criteria.add(Restrictions.like("zxbt", zxbt,MatchMode.ANYWHERE));
		}
		
		String flid=params.get("flid");
		if(null!=flid&&!"".equals(flid)){
			criteria.add(Restrictions.eq("flid", Integer.parseInt(flid)));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime=params.get("begintime");
		if(null!=begintime&&!"".equals(begintime)){
			try {
				criteria.add(Restrictions.ge("zxsj", sdf.parse(begintime+" 00:00:00")));
			} catch (ParseException e) {
				System.err.println("开始时间格式化错误！");
			}
		}
		String endtime=params.get("endtime");
		if(null!=endtime&&!"".equals(endtime)){
			try {
				criteria.add(Restrictions.le("zxsj", sdf.parse(endtime+" 23:59:59")));
			} catch (ParseException e) {
				System.err.println("结束时间格式化错误！");
			}
		}
		
		String type=params.get("type");
		if("1".equals(type)){
			String yhid=params.get("yhid");
			criteria.add(Restrictions.eq("yjid", Integer.parseInt(yhid)));
		}else if("2".equals(type)){
			String zjid=params.get("zjid");
			String zxfl=params.get("zxfl");
			
			Disjunction dis=Restrictions.disjunction();
			dis.add(Restrictions.and(Restrictions.eq("flid", Integer.parseInt(zxfl)),Restrictions.isNull("zjid")));
			dis.add(Restrictions.eq("zjid", Integer.parseInt(zjid)));
			
			criteria.add(dis);
		}else{
			criteria.add(Restrictions.isNotNull("hfsj"));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("id"));
	}
	
	public Pager getPagerByCriteria(Pager pager,String zxbt) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJkzx.class);
		if(null!=zxbt&&!"".equals(zxbt)){
			criteria.add(Restrictions.like("zxbt", zxbt,MatchMode.ANYWHERE));
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

	@SuppressWarnings("unchecked")
	public List<String> getBtsByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJkzx.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("zxbt"));
		return dao.findByDetachedCriteria(criteria);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getTop() {

		Map topmap=new LinkedHashMap<String,Integer>();
		List toplist=dao.findBySql("select flid,count(*) from tbljkzx group by flid order by count(*) desc", false);
		
		List total=dao.findBySql("select count(*) from tbljkzx", false);
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
