package www.quality.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.google.gson.Gson;

import freemarker.template.SimpleDate;

import www.quality.dao.TblLcrkDao;
import www.quality.model.Series;
import www.quality.model.TblLcrk;
import www.quality.service.TblLcrkService;
import www.quality.util.Pager;

@Service(TblLcrkService.TBLLCRK_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblLcrkServiceImpl implements TblLcrkService {

	@Resource(name=TblLcrkDao.TBLLCRK_DAO_IMPL)
	private TblLcrkDao dao;
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblLcrk obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblLcrk obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblLcrk t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblLcrk> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblLcrk.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblLcrk getOneById(Integer id) {		
		List<TblLcrk> list = dao.findByHql("from TblLcrk where id="+id);
		//return dao.findById(id);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblLcrk.class,"R");
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("zinv", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("R.id"));
	}
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblLcrk.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteById(String id) {
		String name="";
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				TblLcrk lcrk = dao.findById(Integer.parseInt(string));
				if(lcrk!=null){
					name+=" "+lcrk.getZinv();
				}
				this.delete(Integer.parseInt(string));//逐个删除
			}
		}
		return name;
	}


	public List<TblLcrk> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblLcrk.class,"R");
		if(null!=date&&!"".equals(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			//当前月的最后一天 
			cal.set(Calendar.DATE, 1);  
			cal.roll(Calendar.DATE, -1);  
	        Date endTime=cal.getTime();  
	        String endTimeStr=sdf.format(endTime)+" 23:59:59";
	        //--当前月的第一天            
	        cal.set(Calendar.DAY_OF_MONTH, 1);   
	        Date beginTime=cal.getTime();
	        String beginTimeStr=sdf.format(beginTime)+" 00:00:00";
	        
			criteria.add(Restrictions.ge("date",  df.parse(beginTimeStr)));
			criteria.add(Restrictions.le("date", df.parse(endTimeStr)));
		}
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		return dao.findByCriteria(criteria);
	}
	public List findByHql(String hql){
		List list = dao.findByHql(hql);
		return list;
	}
	public List getTjData(){
		List<String> dt = new ArrayList<String>();
		List<Series> dta = new ArrayList<Series>();
		try{
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			DetachedCriteria dc2 = DetachedCriteria.forClass(TblLcrk.class);
			dc2.setProjection( 
					Projections.projectionList()
					.add( Projections.groupProperty("danwei") )
					.add( Projections.rowCount() )
					 );
		
			String s3=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+1;
			Date d3 = fm.parse(s3);
			String s4=(date.getYear()+1900)+"-"+(date.getMonth()+2)+"-"+1;
			Date d4 = fm.parse(s4);
			dc2.add(Restrictions.ge("liuchushijian", d3));
			dc2.add(Restrictions.lt("liuchushijian", d4));
			List list2 = dao.findByCriteria(dc2);
			Series series1 = new Series();
			series1.name="本月新增";
			for(int i=0;i<list2.size();i++){
				Object[] s=(Object[])list2.get(i);
				dt.add(s[0]+"");
				series1.data.add(s[1]);
			}
			
			
			DetachedCriteria dc = DetachedCriteria.forClass(TblLcrk.class);
			dc.setProjection( 
					Projections.projectionList()
					.add( Projections.groupProperty("danwei") )
					.add( Projections.rowCount() )
					//.add( Projections.count("danwei") )
					//.add( Projections.property("danwei") )
					 );
			
			String s1=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate());
			Date d1 = fm.parse(s1);
			String ss=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1);
			Date d2 = fm.parse(ss);
			dc.add(Restrictions.ge("liuchushijian", d1));
			dc.add(Restrictions.lt("liuchushijian", d2));
			List list = dao.findByCriteria(dc);
			Series series2 = new Series();
			series2.name="今日新增";
			
			for(String nm:dt){
				Boolean b=false;
				for(int i=0;i<list.size();i++){
					Object[] s=(Object[])list2.get(i);
					if(nm.equals(s[0])){
						b=true;
						series2.data.add(s[1]);
						break;
					}
					/*if(dt.contains(s[0])){
						series2.data.add(dt.indexOf(s[0]),s[1]);
					}else{
						series2.data.add(0);
					}*/
				}
				if(!b){
					series2.data.add(0);
				}
			}
			
			dta.add(series1);
			dta.add(series2);
			Gson gson = new Gson();
			ArrayList dd = new ArrayList();
			dd.add(gson.toJson(dt));
			dd.add(gson.toJson(dta));
			return dd;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public List getTjData2(){
		ArrayList<String> title = new ArrayList<String>();
		Series series = new Series();
		series.name="新增人口";
		try{
			Date date = new Date();
			date.setYear(date.getYear()-1);
			date.setMonth(date.getMonth()+1);
			date.setDate(1);
			//String s1=(date.getYear()+1900-1)+"-"+(date.getMonth()+1)+"-"+(date.getDate());
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			date=fm.parse(fm.format(date));
			System.out.println(fm.format(date));
			for(int i=0;i<12;i++){
				DetachedCriteria dc = DetachedCriteria.forClass(TblLcrk.class);
				dc.setProjection( 
						Projections.projectionList()
						.add( Projections.rowCount() )
						 );
				
				dc.add(Restrictions.ge("liuchushijian", fm.parse(fm.format(date))));
				title.add((date.getYear()+1900)+"年"+(date.getMonth()+1)+"月");
				date.setMonth(date.getMonth()+1);
				dc.add(Restrictions.lt("liuchushijian", fm.parse(fm.format(date))));
				List list = dao.findByCriteria(dc);
				series.data.add(list.get(0));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ArrayList<String> list2 = new ArrayList<String>();
		Gson gson = new Gson();
		list2.add(gson.toJson(title));
		list2.add(gson.toJson(series));
		return list2;
	}
	public static void main(String[] args) {
		
		Date date = new Date();
		//System.out.println(list.size());
		//date.setMonth(date.getMonth()+2);
		date.setYear(date.getYear()-1);
		System.out.println(date.getYear()+"--"+date.getMonth());
	}
}
