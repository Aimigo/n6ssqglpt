package www.quality.service.impl;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.Decoder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;

import www.quality.dao.TblCbxxDao;
import www.quality.dao.TblJmxxDao;
import www.quality.dao.TblJtxxDao;
import www.quality.dao.TblTsryflDao;
import www.quality.dao.TblTsryflzdDao;
import www.quality.dao.TblTsrysjDao;
import www.quality.dao.TblXsxxDao;
import www.quality.model.Series;
import www.quality.model.TblCbxx;
import www.quality.model.TblCzfwgl;
import www.quality.model.TblJmxx;
import www.quality.model.TblJtxx;
import www.quality.model.TblLcrk;
import www.quality.model.TblSyfwgl;
import www.quality.model.TblTsryfl;
import www.quality.model.TblTsryflzd;
import www.quality.model.TblTsrysj;
import www.quality.model.TblXsxx;
import www.quality.model.TblZz;
import www.quality.service.TblJmxxService;
import www.quality.util.Pager;


/**
*类的描述:实现TblJmxxService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblJmxxService.TBLJMXX_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblJmxxServiceImpl implements TblJmxxService {

	@Resource(name=TblJmxxDao.TBLJMXX_DAO_IMPL)
	private TblJmxxDao dao;
	@Resource(name=TblTsryflDao.TBLTSRYFL_DAO_IMPL)
	private TblTsryflDao fldao;
	@Resource(name=TblTsryflzdDao.TBLTSRYFLZD_DAO_IMPL)
	private TblTsryflzdDao zddao;
	@Resource(name=TblTsrysjDao.TBLTSRYSJ_DAO_IMPL)
	private TblTsrysjDao sjdao;
	
	@Resource(name=TblJtxxDao.TBLJTXX_DAO_IMPL)
	private TblJtxxDao jtxxdao;
	@Resource(name=TblCbxxDao.TBLCBXX_DAO_IMPL)
	private TblCbxxDao cbxxdao;
	@Resource(name=TblXsxxDao.TBLXSXX_DAO_IMPL)
	private TblXsxxDao xsxxdao;
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblJmxx obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblJmxx obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblJmxx t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblJmxx> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblJmxx.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblJmxx getOneById(Integer id) {		
		return dao.findById(id);
	}
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJmxx.class,"R");
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		//按年龄统计
		LogicalExpression or=null;
		String age2=params.get("age2");
		if(age2!=null&&!"".equals(age2)){
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String[] split = age2.split(",");
			try{
				String s1=("".equals(split[0].trim()))?split[1].trim():split[0].trim();
				String s2=("".equals(split[1].trim()))?split[0].trim():split[1].trim();
				if(!"".equals(s1)&&!"".equals(s2)){
					Date p1 = format.parse((date.getYear()+1900-Integer.parseInt(s1)+1)+"-1-1");
					//criteria.add(Restrictions.lt("birthday", p1));
					Date p2=format.parse((date.getYear()+1900-Integer.parseInt(s2))+"-1-1");
					//criteria.add(Restrictions.ge("birthday", p2));
					LogicalExpression and = Restrictions.and(Restrictions.lt("birthday", p1)
							, Restrictions.ge("birthday", p2));
					or = Restrictions.or(and, Restrictions.eq("name", "qwerttyuiopkkjhhg"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}//按年龄统计
		String age=params.get("age");
		if(age!=null&&!"".equals(age)){
			if(!age.contains("-1")){
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String[] a = age.split(",");
				if(a!=null){
					try{
					
					for(String g:a){
						if("".equals(g.trim())){
							continue;
						}
						String[] s = g.split("_");
						Date p1 = format.parse((date.getYear()+1900-Integer.parseInt(s[0].trim())+"-12-31 23:59:59"));
						System.out.println(format.format(p1));
						Date p2=format.parse((date.getYear()+1900-Integer.parseInt(s[1].trim())+"-1-1 0:0:0"));
						System.out.println(format.format(p2));
						LogicalExpression and = Restrictions.and(Restrictions.le("birthday", p1)
								, Restrictions.ge("birthday", p2));
						if(s.length>=1){
							if(or==null){
								or = Restrictions.or(and, Restrictions.eq("name", "qwerttyuiopkkjhhg"));
							}else{
								or=Restrictions.or(and, or);
							}
						}
					}
					
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
		}
		if(or!=null){
			criteria.add(or);
		}
		String typename = params.get("typename");
			if(null!=typename&&!"".equals(typename)){
			try {
				typename = URLDecoder.decode(typename, "UTF-8");
				List tn = dao.findByHql("from TblTsryfl where name='"+typename+"'");
				if(tn!=null&&tn.size()>0){
					params.put("type", ((TblTsryfl)tn.get(0)).getId()+"");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//按特殊人员统计
		String type=params.get("type");
		if(null!=type&&!"".equals(type)){
			if(!type.contains("-1")){
				DetachedCriteria ryDc = DetachedCriteria
						.forClass(TblTsrysj.class);
				ryDc.setProjection(Property.forName("sfz"));
				LogicalExpression or2=null;
				String[] split = type.split(",");
				for(int i=0;i<split.length;i++){
					if(or2==null){
						or2 = Restrictions.or(Restrictions.eq("flid", Integer.parseInt(split[i].trim())), Restrictions.eq("flid", 9993));
					}else{
						or2 = Restrictions.or(Restrictions.eq("flid", Integer.parseInt(split[i].trim())), or2);
					}
				}
				ryDc.add(or2);
				criteria.add(Property.forName("idNumber").in(ryDc));
			}
		}
		String sex=params.get("sex");
		if(sex!=null&&!"".equals(sex)){
			criteria.add(Restrictions.eq("gender",sex));
		}
		
		String grid = params.get("grid");
		if(grid!=null&&!"".equals(grid)){
			String[] split = grid.split(",");
			LogicalExpression or3=null;
			for(String gd:split){
				gd=gd.trim();
				if("".equals(gd)){
					continue;
				}
				if(or3==null){
					or3 = Restrictions.or(Restrictions.eq("wangge", gd), Restrictions.like("otherwg", gd,MatchMode.ANYWHERE));
				}else{
					or3=Restrictions.or(Restrictions.eq("wangge", gd), or3);
					or3=Restrictions.or(Restrictions.like("otherwg", gd,MatchMode.ANYWHERE), or3);
				}
			}
			if(or3!=null){
				criteria.add(or3);
			}
		}
		criteria.addOrder(Order.desc("R.accountNumber"));
		return dao.getPagerByCriteria(criteria, p, Order.desc("R.accountRelation"));
	}
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJmxx.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
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

	public List<TblJmxx> getByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJmxx.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		criteria.addOrder(Order.desc("date"));
		return dao.findByCriteria(criteria);
	}

	public List<TblJmxx> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJmxx.class,"R");
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
	public List<TblTsryfl> getSpecialType(){
		List<TblTsryfl> list = fldao.findByHql("from TblTsryfl where 1=1 order by sort asc");
		return list;
	}
	public List<TblTsryflzd> getFlzdByFlid(String flid){
		List<TblTsryflzd> list = zddao.findByHql("from TblTsryflzd where flid="+flid+" order by px asc");
		return list;
	}
	public List<TblTsryflzd> getZdesByFlid(Integer tsryflid){
		List<TblTsryflzd> list = zddao.findByHql("from TblTsryflzd where flid="+tsryflid+" order by px asc");
		return list;
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveTsry(TblTsrysj sj){
		sjdao.save(sj);
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveJtxx(TblJtxx jtxx){
		jtxxdao.save(jtxx);
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveCbxx(TblCbxx cbxx){
		cbxxdao.save(cbxx);
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveXsxx(TblXsxx xsxx){
		xsxxdao.save(xsxx);
	}
	public Map<String,List<TblTsryflzd>> getTsryInfoBySFZ(String idNumber){
		List<TblTsrysj> tsry = sjdao.findByHql("from TblTsrysj where sfz ='"+idNumber.trim()+"'");
		Map<String,List<TblTsryflzd>> m=new HashMap<String,List<TblTsryflzd>>();
		for(TblTsrysj rysj:tsry){
			
			TblTsryfl fl = fldao.findById(rysj.getFlid());
			List<TblTsryflzd> list = zddao.findByHql("from TblTsryflzd where flid="+rysj.getFlid());
			if(list==null||list.size()<=0){
				continue;
			}
			for(TblTsryflzd zd:list){
				try {
					Object invoke = TblTsrysj.class.getMethod("get" + zd.getDatazd().substring(0, 1).toUpperCase()
											+ zd.getDatazd().substring(1)).invoke(rysj);
					if(invoke==null){
						continue;
					}
					zd.setValue(invoke.toString().trim());
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			m.put(fl.getName()+"_"+fl.getId(),list);
		}
		return m;
	}
	public TblJtxx getHuzhuBySFZ(String idNumber){
		List<TblJtxx> list = jtxxdao.findByHql("from TblJtxx where sfz='"+idNumber+"'");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public TblCbxx getCanbaoBySFZ(String idNumber){
		List<TblCbxx> list = cbxxdao.findByHql("from TblCbxx where sfz='"+idNumber+"'");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public TblXsxx getXueshengBySFZ(String idNumber){
		List<TblXsxx> list = xsxxdao.findByHql("from TblXsxx where sfz='"+idNumber+"'");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public String delByIds(String ids){
		//需要级联删除 特殊人员数据表。和家庭信息（户主）表，参保信息表，学生信息表。
		List<TblJmxx> list = dao.findByHql("from TblJmxx where id in("+ids+")");
		String name="";
		for(TblJmxx jmxx:list){
			 name+=jmxx.getName()+" ";
			dao.deleteById(jmxx.getId());
			sjdao.bulkUpdate("delete from TblTsrysj where sfz ='"+jmxx.getIdNumber()+"'");
			xsxxdao.bulkUpdate("delete from TblXsxx where sfz ='"+jmxx.getIdNumber()+"'");
			jtxxdao.bulkUpdate("delete from TblJtxx where sfz ='"+jmxx.getIdNumber()+"'");
			cbxxdao.bulkUpdate("delete from TblCbxx where sfz ='"+jmxx.getIdNumber()+"'");
		}
		return name;
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delTsryByIdcart(String idNumber){
		sjdao.bulkUpdate("delete from TblTsrysj where sfz ='"+idNumber+"'");
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delHzxxByIdcart(String idNumber){
		jtxxdao.bulkUpdate("delete from TblJtxx where sfz ='"+idNumber+"'");
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delCbxxByIdcart(String idNumber){
		cbxxdao.bulkUpdate("delete from TblCbxx where sfz ='"+idNumber+"'");
	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delXsxxByIdcart(String idNumber){
		xsxxdao.bulkUpdate("delete from TblXsxx where sfz ='"+idNumber+"'");
	}
	public List<TblTsryfl> getAllTrsy(){
		//List<TblTsryfl> all = fldao.getAll();
		List<TblTsryfl> all =fldao.findByHql("from TblTsryfl where 1=1 order by sort asc");
		return all;
	}
	public TblTsryfl getTsryflById(Integer id){
		TblTsryfl fl = fldao.findById(id);
		return fl;
	}
	public TblJmxx getOneByIdCard(String idcard){
		List<TblJmxx> jmlist=dao.findByHql("from TblJmxx where idNumber='"+idcard.trim()+"'");
		if(jmlist!=null&&jmlist.size()>0){
			return jmlist.get(0);
		}
		return null;
	}

	public void saveJmxxList(ArrayList<TblJmxx> jmxxes) {
		dao.saveCollection(jmxxes);
	}

	public void saveCbxxList(ArrayList<TblCbxx> cbxxes) {
		cbxxdao.saveCollection(cbxxes);
	}

	public void saveJtxxList(ArrayList<TblJtxx> jtxxes) {
		jtxxdao.saveCollection(jtxxes);
	}

	public void saveXsxxList(ArrayList<TblXsxx> xsxxes) {
		xsxxdao.saveCollection(xsxxes);
		
	}
	public Integer getCountByAge(int i,int j,String grid){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblJmxx.class,"R");
		//按年龄统计
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date p1 ;
		Date p2;
		try {
			p1 = format.parse((date.getYear()+1900-i)+"-1-1");
			p2 = format.parse((date.getYear()+1900-j)+"-1-1");
			/*if(i==0){
				p1 = format.parse((date.getYear()+1900-i+1)+"-1-1");
			}
			if(i==35){
				p2 = format.parse((date.getYear()+1900-i-150)+"-1-1");
			}*/
			criteria.add(Restrictions.lt("birthday", p1));
			criteria.add(Restrictions.gt("birthday", p2));
			
			//String grid = params.get("grid");
			if(grid!=null&&!"".equals(grid)){
				String[] split = grid.split(",");
				LogicalExpression or3=null;
				for(String gd:split){
					gd=gd.trim();
					if("".equals(gd)){
						continue;
					}
					if(or3==null){
						or3 = Restrictions.or(Restrictions.eq("wangge", gd), Restrictions.like("otherwg", gd,MatchMode.ANYWHERE));
					}else{
						or3=Restrictions.or(Restrictions.eq("wangge", gd), or3);
						or3=Restrictions.or(Restrictions.like("otherwg", gd,MatchMode.ANYWHERE), or3);
					}
				}
				if(or3!=null){
					criteria.add(or3);
				}
			}
			
			List<TblJmxx> jm = dao.findByCriteria(criteria);
			System.out.println(jm.size());
			return jm.size();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public List<TblJmxx> getByIdcart(String idNumber){
		List<TblJmxx> list = dao.findByHql("from TblJmxx where idNumber='"+idNumber+"'");
		return list;
	}
	public List getTjData(){
		List<String> dt = new ArrayList<String>();
		List<Series> dta = new ArrayList<Series>();
		try{
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			DetachedCriteria dc2 = DetachedCriteria.forClass(TblJmxx.class);
			dc2.setProjection( 
					Projections.projectionList()
					.add( Projections.groupProperty("wangge") )
					.add( Projections.rowCount() )
					 );
		
			String s3=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+1;
			Date d3 = fm.parse(s3);
			String s4=(date.getYear()+1900)+"-"+(date.getMonth()+2)+"-"+1;
			Date d4 = fm.parse(s4);
			dc2.add(Restrictions.ge("createtime", d3));
			dc2.add(Restrictions.lt("createtime", d4));
			List list2 = dao.findByCriteria(dc2);
			Series series1 = new Series();
			series1.name="本月新增";
			for(int i=0;i<list2.size();i++){
				Object[] s=(Object[])list2.get(i);
				if(s[0]==null){
					dt.add("其他网格");
				}else{
					dt.add(s[0]+"");
				}
				
				series1.data.add(s[1]);
			}
			
			
			DetachedCriteria dc = DetachedCriteria.forClass(TblJmxx.class);
			dc.setProjection( 
					Projections.projectionList()
					.add( Projections.groupProperty("wangge") )
					.add( Projections.rowCount() )
					 );
			
			String s1=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate());
			Date d1 = fm.parse(s1);
			String ss=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1);
			Date d2 = fm.parse(ss);
			dc.add(Restrictions.ge("createtime", d1));
			dc.add(Restrictions.lt("createtime", d2));
			List list = dao.findByCriteria(dc);
			Series series2 = new Series();
			series2.name="今日新增";
			
			for(String nm:dt){
				Boolean b=false;
				for(int i=0;i<list.size();i++){
					Object[] s=(Object[])list2.get(i);
					String st=s[0]+"";
					if(s[0]==null){
						st="其他网格";
					}
					if(nm.equals(st)){
						b=true;
						series2.data.add(s[1]);
						break;
					}
				}
				if(!b){
					series2.data.add(0);
				}
			}
			
			
			dta.add(series2);
			dta.add(series1);
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
				DetachedCriteria dc = DetachedCriteria.forClass(TblJmxx.class);
				dc.setProjection( 
						Projections.projectionList()
						.add( Projections.rowCount() )
						 );
				
				dc.add(Restrictions.ge("createtime", fm.parse(fm.format(date))));
				title.add((date.getYear()+1900)+"年"+(date.getMonth()+1)+"月");
				date.setMonth(date.getMonth()+1);
				dc.add(Restrictions.lt("createtime", fm.parse(fm.format(date))));
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
	public Boolean findHjgx(String ryid, String ar, String an){
		String hql="from TblJmxx where 1=1 ";
		if(ryid!=null&&!"".equals(ryid.trim())){
			hql+="and id !="+ryid.trim();
		}
		hql+=" and accountNumber='"+an+"' and accountRelation='"+ar+"'";
		List<TblJmxx> list = dao.findByHql(hql);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	public List getXqdz(String ints, String road, String ridgepole,
			String element,String grid){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblSyfwgl.class,"R");
		if("0".equals(ints)){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("xqdz"), "xqdz")));	
			criteria.addOrder(Order.asc("R.xqdz"));
		}else if("1".equals(ints)){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("zhuang"), "zhuang")));
			criteria.addOrder(Order.asc("R.zhuang"));
			criteria.add(Restrictions.eq("xqdz",  road));	
		}else if("2".equals(ints)){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("dy"), "dy")));
			criteria.addOrder(Order.asc("R.dy"));
			criteria.add(Restrictions.eq("zhuang",  ridgepole));
			criteria.add(Restrictions.eq("xqdz",  road));
		}else if("3".equals(ints)){
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("shi"), "shi")));
			criteria.addOrder(Order.asc("R.shi"));
			criteria.add(Restrictions.eq("dy",  element));
			criteria.add(Restrictions.eq("zhuang",  ridgepole));
			criteria.add(Restrictions.eq("xqdz",  road));
		}
		/*DetachedCriteria regDc2 = DetachedCriteria
				.forClass(TblCzfwgl.class);
		regDc2.setProjection(Property.forName("syfwid"));
		criteria.add(Property.forName("id").in(regDc2));
		*/
		
		if(grid!=null&&!"".equals(grid)){
			DetachedCriteria regDc = DetachedCriteria
					.forClass(TblZz.class);
			regDc.setProjection(Property.forName("objectid"));
			String[] split2 = grid.split(",");
			LogicalExpression or3=null;
			for(String gd:split2){
				gd=gd.trim();
				if("".equals(gd)){
					continue;
				}
				if(or3==null){
					or3 = Restrictions.or(Restrictions.like("grid", gd,MatchMode.ANYWHERE), Restrictions.eq("name", "abcdefvsdfdfss"));
				}else{
					or3=Restrictions.or(Restrictions.like("grid",gd, MatchMode.ANYWHERE), or3);
				}
			}
			if(or3!=null){
				regDc.add(or3);
			}
			criteria.add(Property.forName("lyid").in(regDc));
		}
		
		List list = dao.findByCriteria(criteria);
		return list;
	}

	public List getTjData3(String grid) {
		Disjunction dis=null;
		if(grid!=null&&!"".equals(grid)){
			dis=Restrictions.disjunction();
			String[] split2 = grid.split(",");
			for(String gd:split2){
				gd=gd.trim();
				if("".equals(gd)){
					continue;
				}
				dis.add(Restrictions.like("wangge", gd,MatchMode.ANYWHERE));
			}
		}
		
		
		List<Object[]> list = new ArrayList<Object[]>();
		//全部
		DetachedCriteria r5 = DetachedCriteria.forClass(TblJmxx.class, "R5");
		r5.setProjection(Projections.rowCount());
		r5.add(Restrictions.eq("register", "户籍人口"));
		
		DetachedCriteria r6 = DetachedCriteria.forClass(TblJmxx.class, "R6");
		r6.setProjection(Projections.rowCount());
		r6.add(Restrictions.ne("register", "户籍人口"));
		if(dis!=null){
			r5.add(dis);
			r6.add(dis);
		}
		list.add(new Object[] { dao.findByCriteria(r5).get(0),
				dao.findByCriteria(r6).get(0) });
		
		/*
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//当天
		DetachedCriteria r1 = DetachedCriteria.forClass(TblJmxx.class, "R3");
		r1.setProjection(Projections.rowCount());
		r1.add(Restrictions.eq("register", "户籍人口"));
		
		DetachedCriteria r2 = DetachedCriteria.forClass(TblJmxx.class, "R4");
		r2.setProjection(Projections.rowCount());
		r2.add(Restrictions.ne("register", "户籍人口"));
		
		
		try {
			Date p1 = format.parse((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
			Date p2=format.parse((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1));
			
			r1.add(Restrictions.ge("createtime",p1));
			r2.add(Restrictions.gt("createtime", p1));
			r1.add(Restrictions.le("createtime", p2));
			r2.add(Restrictions.lt("createtime",  p2));
			list.add(new Object[] { dao.findByCriteria(r1).get(0),
					dao.findByCriteria(r2).get(0) });
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//本月
		DetachedCriteria r3 = DetachedCriteria.forClass(TblJmxx.class, "R1");
		r3.setProjection(Projections.rowCount());
		r3.add(Restrictions.eq("register", "户籍人口"));
		DetachedCriteria r4 = DetachedCriteria.forClass(TblJmxx.class, "R2");
		r4.setProjection(Projections.rowCount());
		r4.add(Restrictions.ne("register", "户籍人口"));
		
		
		
		try {
			Date p1 = format.parse((date.getYear()+1900)+"-"+(date.getMonth()+1)+"-1");
			Date p2=format.parse((date.getYear()+1900)+"-"+(date.getMonth()+2)+"-1");
			r3.add(Restrictions.ge("createtime", p1));
			r4.add(Restrictions.ge("createtime", p1));
			r3.add(Restrictions.lt("createtime", p2));
			r4.add(Restrictions.lt("createtime", p2));
			list.add(new Object[] { dao.findByCriteria(r3).get(0),
					dao.findByCriteria(r4).get(0) });
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		return list;
	}
	public List getTjData4(){
		
		List list = new ArrayList();
		String[] ss=new String[]{"场部一区网格","场部二区网格","场部三区网格","场部四区网格","场部五区网格","场部六区网格","场部七区网格","一连一区网格",
								"一连二区网格","二连一区网格","二连二区网格","三连网格","四连一区网格 ","四连二区网格","五连网格","六连网格  ","七连网格",
								"八连一区网格 ","八连二区网格","葡萄公司网格"};
		Series series0 = new Series();
		series0.setName("全部");
		Series series1 = new Series();
		series1.setName("户籍人口");
		Series series2 = new Series();
		series2.setName("非户籍人口");
		Series series3 = new Series();
		series3.setName("户籍户");
		//Series series4 = new Series();
		//series4.setName("非户籍户");
		//HashMap<String,Integer[]> hashMap = new HashMap<String,Integer[]>();
		for(String wg:ss){
			String sql="select count(*) from SYSDIC_JUMINXINXI where wangge like '%"+wg+"%' "+
							" and register is not null ";	
			String sql1="select count(*) from SYSDIC_JUMINXINXI where wangge like '%"+wg+"%' "+
					" and register = '户籍人口'";
			String sql2="select count(*) from SYSDIC_JUMINXINXI where wangge like '%"+wg+"%' "+
					" and  register != '户籍人口'";
			String sql3="select count(distinct account_Idnumber) from SYSDIC_JUMINXINXI where wangge like '%"+wg+"%' "+
					" and  register = '户籍人口' ";
			series0.data.add(Integer.parseInt(dao.findBySql(sql, false).get(0).toString()));
			series1.data.add(Integer.parseInt( dao.findBySql(sql1, false).get(0).toString()));
			series2.data.add(Integer.parseInt( dao.findBySql(sql2, false).get(0).toString()));
			series3.data.add(Integer.parseInt( dao.findBySql(sql3, false).get(0).toString()));
			//series3.data.add(Integer.parseInt( dao.findBySql(sql3, false).get(0).toString()));
			///hashMap.put(wg, new Integer[]{a0,a1,a2});
		}
		list.add(series0);
		list.add(series1);
		list.add(series2);
		list.add(series3);
		ArrayList list2 = new ArrayList();
		list2.add(ss);
		list2.add(list);
		return list2;
	}
	public String getTjData5(String type, String time, String grid){
		/*String[] ss=new String[]{"场部一区网格","场部二区网格","场部三区网格","场部四区网格","场部五区网格","场部六区网格","场部七区网格","一连一区网格",
				"一连二区网格","二连一区网格","二连二区网格","三连网格","四连一区网格 ","四连二区网格","五连网格","六连网格  ","七连网格",
				"八连一区网格 ","八连二区网格","葡萄公司网格"};*/
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String> title = new ArrayList<String>();
		Series series = new Series();
		if("0".equals(grid)){
			series.name="全部";
		}else{
			series.name=grid;
		}
		
		try{
			if("1".equals(type)){
				Integer month=Integer.parseInt(time.split("-")[1]);
				Date date=fm.parse(time+"-1");
				System.out.println(fm.format(date));
				while(date.getMonth()==month-1){
					DetachedCriteria dc = DetachedCriteria.forClass(TblJmxx.class);
					dc.setProjection( Projections.projectionList().add( Projections.rowCount()));
					if(!"0".equals(grid)){
						dc.add(Restrictions.like("wangge", grid,MatchMode.ANYWHERE));
					}
					title.add((date.getYear()+1900)+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日");
					dc.add(Restrictions.ge("createtime", fm.parse(fm.format(date))));
					date.setDate(date.getDate()+1);
					dc.add(Restrictions.lt("createtime", fm.parse(fm.format(date))));
					List list = dao.findByCriteria(dc);
					series.data.add(list.get(0));
				}
			}else{
				Date date=fm.parse(time+"-1-1");
				System.out.println(fm.format(date));
				for(int i=0;i<12;i++){
					DetachedCriteria dc = DetachedCriteria.forClass(TblJmxx.class);
					dc.setProjection(Projections.projectionList().add( Projections.rowCount()));
					if(!"0".equals(grid)){
						dc.add(Restrictions.like("wangge", grid,MatchMode.ANYWHERE));
					}
					dc.add(Restrictions.ge("createtime", fm.parse(fm.format(date))));
					title.add((date.getYear()+1900)+"年"+(date.getMonth()+1)+"月");
					date.setMonth(date.getMonth()+1);
					dc.add(Restrictions.lt("createtime", fm.parse(fm.format(date))));
					List list = dao.findByCriteria(dc);
					series.data.add(list.get(0));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		ArrayList<Object> list2 = new ArrayList<Object>();
		Gson gson = new Gson();
		list2.add(title);
		list2.add(series);
		return gson.toJson(list2);
	}
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.getMonth());
	}
	public List<TblJmxx> getByIdcart2(String idNumber, String jmxxid){
		String hql="from TblJmxx where idNumber='"+idNumber+"'";
		if(jmxxid!=null&&!"".equals(jmxxid)){
			hql+=" and id!="+jmxxid;
		}
		List<TblJmxx> list = dao.findByHql(hql);
		return list;
	}
}
