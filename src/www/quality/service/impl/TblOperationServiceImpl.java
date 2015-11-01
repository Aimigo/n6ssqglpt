package www.quality.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblFunctionDao;
import www.quality.dao.TblOperationDao;
import www.quality.model.TblFunction;
import www.quality.model.TblOperation;
import www.quality.model.TblRole;
import www.quality.model.TblRoleAndOperation;
import www.quality.model.TblUserAndRole;
import www.quality.service.TblOperationService;
import www.quality.service.TblUserAndRoleService;

@Service(TblOperationService.TBLOPERATION_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblOperationServiceImpl implements TblOperationService {

	@Resource(name=TblOperationDao.TBLOPERATION_DAO_IMPL)
	private TblOperationDao dao;
	@Resource(name=TblUserAndRoleService.TBLUSERANDROLE_SERVICE_IMPL)
	private TblUserAndRoleService uarser;
	@Resource(name=TblFunctionDao.TBLFUNCTION_DAO_IMPL)
	private TblFunctionDao fundao;
	
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOperation(List<TblOperation> list) {
		dao.saveCollection(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateOperation(String functioncode, List<TblOperation> list) {
		this.deleteOperation(functioncode);
		dao.saveCollection(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteOperation(String functioncode) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblOperation t where t.functioncode in ('"+functioncode+"')");
		StringBuffer s=new StringBuffer();
		s.append("delete from TblRoleAndOperation t where t.funcode in ('"+functioncode+"')");
		dao.bulkUpdate(s.toString());
		dao.bulkUpdate(sbuf.toString());
	}

	/**
	 * 字符加单引号，方便删除数据
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unused")
	private String renStr(String code){	
		String str="";
		for(String s:code.split(",")){
			if(str=="")
				str="'"+s+"'";
			else
				str+=","+"'"+s+"'";
		}
		return str;
	}
	
	public List<TblOperation> getDataByCode(String code) {
		DetachedCriteria d=DetachedCriteria.forClass(TblOperation.class,"t");
		d.add(Restrictions.eq("t.code", code));
		return dao.findByCriteria(d);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,List> getDataByUsercode(String usercode) {	
		
		List<TblRole> list=uarser.getRoleByUsercode(usercode);//查询到角色List
		List<String> li=this.renRolecode(list);
		DetachedCriteria d=DetachedCriteria.forClass(TblRoleAndOperation.class,"ro");
		d.setProjection(Projections.property("ro.operationcode"));
		if(li!=null&&li.size()>0)
		    d.add(Restrictions.in("ro.rolecode",li));
		else
			d.add(Restrictions.eq("ro.rolecode",null));
		List<String> liss=dao.findByDetachedCriteria(d);
		List<String> lis=new ArrayList<String>(new LinkedHashSet<String>(liss));//去掉重复的功能表code
		DetachedCriteria dd=DetachedCriteria.forClass(TblOperation.class);
		if(lis!=null&&lis.size()>0)
			dd.add(Restrictions.in("code", lis));
		else
			dd.add(Restrictions.eq("code", null));
		
		List<TblOperation> ll=dao.findByCriteria(dd);//	找到功能操作信息表中的数据
		
		List<String> lii=new ArrayList<String>(new LinkedHashSet<String>(this.findFunction(ll)));//去掉重复的功能表code;//
		
		List<TblFunction> listfun=fundao.getDataByCode(lii);//最终的去掉重复后的功能
		
		Map<String, List> map=new HashMap<String, List>();
		
		map.put("funlist", listfun);//功能表
		map.put("operlist", ll);//功能操作信息表
		
		return  map;
	}
	
	
	public List<String> renOperationName(String functioncode,List<TblOperation> listoper){	
		List<String> list=new ArrayList<String>();
		for(TblOperation o:listoper){
			if(o.getFunctioncode().equals(functioncode))
				list.add(o.getName());
		}
		return list;
	}
	
	/**
	 * 根据功能操作表中的信息
	 * @param list
	 * @return
	 */
	private List<String> findFunction(List<TblOperation> list){		
		
		List<String> li=this.renFuncode(list);
		
		List<String> listfun=new ArrayList<String>();
		for(String o:li){
			TblFunction fun=fundao.getOneBycode(o);
			listfun=this.getFun(fun,listfun);
		}
		return new ArrayList<String>(new LinkedHashSet<String>(listfun));//去掉重复的功能权限
	}
	
	/**
	 * 根据功能表中子节点找到祖辈的节点,放入List中
	 * @param o
	 * @param list
	 * @return
	 */
	private List<String> getFun(TblFunction o,List<String> list){			
		if(this.getFunctionLevel(o.getCode())!=1){		
			list.add(o.getCode());
			return this.getFun(fundao.getOneBycode(o.getFcode()),list);		
		}else
			return list;
	}
	
	/**
	 * 获得功能的级别
	 * @param code
	 * @return
	 */
	private int getFunctionLevel(String code) {
		TblFunction fun = fundao.getOneBycode(code);
		int level = 1;
		if (fun != null) {
			while (fun != null && !fun.getFcode().equals("-1")) {
				fun = fundao.getOneBycode(fun.getFcode());
				level++;
			}
		}
		return level;
	}
	
	/**
	 * 取出角色code，封装成List
	 * @param li
	 * @return
	 */
	private List<String> renRolecode(List<TblRole> li){		
		List<String> list=new ArrayList<String>();	
		for(TblRole role:li){
			list.add(role.getCode());
		}
		return list;
	}
	
	/**
	 * 去掉功能重复code
	 * @param li
	 * @return
	 */
	private List<String> renFuncode(List<TblOperation> li){
		List<String> list=new ArrayList<String>();
		for(TblOperation o:li){
			list.add(o.getFunctioncode());
		}
		return new ArrayList<String>(new LinkedHashSet<String>(list));//去掉重复的功能权限
	}
	
	public static void main(String[] args) {
		TblUserAndRole r1=new TblUserAndRole();
		r1.setId(1);
		r1.setRolecode("haoba");
		r1.setUsercode("haixing");
		
		TblUserAndRole r2=new TblUserAndRole();
		r2.setId(1);
		r2.setRolecode("haoba");
		r2.setUsercode("haixing");
		
		TblUserAndRole r3=new TblUserAndRole();
		r3.setId(2);
		r3.setRolecode("meishi");
		r3.setUsercode("haixingba");
		
		List<String> list=new ArrayList<String>();
		list.add("ff");list.add("ff");list.add("aa");
		
//		HashSet h = new HashSet(list);       
//		list.clear();       
//		list.addAll(h);       
	}

	public List<TblOperation> getDataByFunctionCode(String functioncode) {
		// TODO Auto-generated method stub
		DetachedCriteria d=DetachedCriteria.forClass(TblOperation.class,"t");
		d.add(Restrictions.eq("t.functioncode", functioncode));
		return dao.findByCriteria(d);
	}

	public List<TblOperation> getAllData() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteOperation(String functioncode, String name) {
		// TODO Auto-generated method stub
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblOperation t where t.functioncode in ('"+functioncode+"') and t.name in ('"+name+"')");
		StringBuffer s=new StringBuffer();
		s.append("delete from TblRoleAndOperation t where t.funcode in ('"+functioncode+"') and t.funxxcode in ('"+name+"')");
		dao.bulkUpdate(s.toString());
		dao.bulkUpdate(sbuf.toString());
	}

	public TblOperation getOneByFuncodeAndFunxxcode(String funcode,
			String funxxcode) {
		// TODO Auto-generated method stub
		DetachedCriteria d=DetachedCriteria.forClass(TblOperation.class,"t");
		d.add(Restrictions.eq("t.functioncode", funcode));
		d.add(Restrictions.eq("t.name", funxxcode));
		List<TblOperation> list = dao.findByCriteria(d);
		if(null==list||list.size()<=0)
			return null;
		return list.get(0);
	}
	
}
