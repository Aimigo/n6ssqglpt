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

import www.quality.dao.TblJnflDao;
import www.quality.dao.TblPxjnDao;
import www.quality.model.Series;
import www.quality.model.TblPxjn;
import www.quality.service.TblPxjnService;
import www.quality.util.Pager;


/**
*类的描述:实现TblPxjnService(培训技能表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblPxjnService.TBLPXJN_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblPxjnServiceImpl implements TblPxjnService {

	@Resource(name=TblPxjnDao.TBLPXJN_DAO_IMPL)
	private TblPxjnDao dao;
	@Resource(name=TblJnflDao.TBLJNFL_DAO_IMPL)
	private TblJnflDao fldao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblPxjn obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblPxjn obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblPxjn t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblPxjn> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblPxjn.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblPxjn getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblPxjn.class);
		Map<String, String> params = p.getParams();
		String bt=params.get("bt");
		if(null!=bt&&!"".equals(bt)){
			criteria.add(Restrictions.like("bt", bt, MatchMode.ANYWHERE));
		}
		
		String type=params.get("type");
		if(null!=type&&!"".equals(type)){
			criteria.add(Restrictions.eq("type", Integer.parseInt(type)));
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
		DetachedCriteria criteria = DetachedCriteria.forClass(TblPxjn.class);
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
		List toplist=dao.findBySql("select flid,count(*) from tblpxjn group by flid order by count(*) desc", false);
		
		List total=dao.findBySql("select count(*) from tblpxjn", false);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getChart() {
		// TODO Auto-generated method stub
		Map topmap=new LinkedHashMap<String,Integer>();
		
		List<Series> series = new ArrayList<Series>();
		series.add(new Series("文档",new ArrayList())); 
		series.add(new Series("视频",new ArrayList())); 
		
		List<String> listCate = new ArrayList<String>();
		
		List list = dao.findBySql("select flid,count(*) from tblpxjn group by flid order by count(*) desc", false);
		for(int i = 0; i < list.size(); i++){
			Object[] pp=(Object[])list.get(i);
			
			String flname = fldao.findById(Integer.valueOf(pp[0].toString())).getName();
			listCate.add(flname);
			
			List fllist = dao.findBySql("select type,count(*) from tblpxjn where flid = "+ Integer.valueOf(pp[0].toString()) +" group by type", false);
			
			if(null!=fllist&&fllist.size()>0){
				if(fllist.size() == 1){
					Object[] obj=(Object[])fllist.get(0);
					if("1".equals(obj[0].toString())){
						for (Series ser : series) {
							if("文档".equals(ser.name)){
								List data = ser.data;
								data.add(Integer.parseInt(obj[1].toString()));
							}else{
								List data = ser.data;
								data.add(0);
							}
						}
					}else if("2".equals(obj[0].toString())){
						for (Series ser : series) {
							if("视频".equals(ser.name)){
								List data = ser.data;
								data.add(Integer.parseInt(obj[1].toString()));
							}else{
								List data = ser.data;
								data.add(0);
							}
						}
					}
				}else{
					for (Object objs : fllist) {
						Object[]  obj= (Object[])objs;
						for (Series ser : series) {
							if("1".equals(obj[0].toString())){
								if("文档".equals(ser.name)){
									List data = ser.data;
									data.add(Integer.parseInt(obj[1].toString()));
								}
							}else if("2".equals(obj[0].toString())){
								if("视频".equals(ser.name)){
									List data = ser.data;
									data.add(Integer.parseInt(obj[1].toString()));
								}
							}
						}
					}
				}
			}
			
		}
		
		topmap.put("Cate", listCate);
		topmap.put("Seri", series);
		return topmap;
	}
}
