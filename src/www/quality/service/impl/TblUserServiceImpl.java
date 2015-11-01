package www.quality.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

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

import www.quality.dao.TblFunctionDao;
import www.quality.dao.TblRoleAndOperationDao;
import www.quality.dao.TblRoleDao;
import www.quality.dao.TblUserAndRoleDao;
import www.quality.dao.TblUserDao;
import www.quality.model.TblFunction;
import www.quality.model.TblRole;
import www.quality.model.TblRoleAndOperation;
import www.quality.model.TblUser;
import www.quality.model.TblUserAndRole;
import www.quality.service.TblUserAndRoleService;
import www.quality.service.TblUserService;
import www.quality.util.Pager;


@SuppressWarnings("unchecked")
@Service(TblUserService.TBLUSER_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblUserServiceImpl implements TblUserService {
	
	@Resource(name=TblUserDao.TBLUSER_DAO_IMPL)
	private TblUserDao dao;
	@Resource(name=TblUserAndRoleService.TBLUSERANDROLE_SERVICE_IMPL)
	private TblUserAndRoleService uarser;
	@Resource(name=TblFunctionDao.TBLFUNCTION_DAO_IMPL)
	private TblFunctionDao fundao;
	@Resource(name=TblRoleDao.TBLROLE_DAO_IMPL)
	private TblRoleDao roledao;
	@Resource(name=TblUserAndRoleDao.TBLUSERANDROLE_DAO_IMPL)
	private TblUserAndRoleDao uardao;
	@Resource(name=TblRoleAndOperationDao.TBLROLEANDOPERATION_DAO_IMPL)
	private TblRoleAndOperationDao raodao;

	public Pager getAllDataByPage(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblUser.class,"u");
		return dao.getPagerByCriteria(d, p, Order.desc("u.id"));
	}
		
	// 如果有事务,那么加入事务,没有的话新建一个(不写的情况下)
    //@Transactional(propagation=Propagation.REQUIRED) 
	// 容器不为这个方法开启事务
    //@Transactional(propagation=Propagation.NOT_SUPPORTED)
	// 不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
    // @Transactional(propagation=Propagation.REQUIRES_NEW) 
	//isolation = Isolation.DEFAULT事物的级别
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(TblUser user) {
		dao.save(user);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUser(TblUser user) {
		dao.update(user);		
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUser(String id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblUser t where t.id in("+id+")");
		TblUser user=this.getOneById(Integer.parseInt(id));
		StringBuffer sb=new StringBuffer();
		sb.append("delete from TblUserAndRole t where t.usercode in ('"+user.getUsercode()+"')");
		dao.bulkUpdate(sb.toString());//首先删除用户和角色关联表中的信息
		dao.bulkUpdate(sbuf.toString());//再删除用户表中的信息
	}
	
	public TblUser getOneById(Integer id) {		
		return dao.findById(id);
	}

	public List<TblUser> getListByUsername(String username) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblUser.class,"U");
		if(username!=null&&!"".equals(username))
			criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("state", "启用"));
		return dao.findByCriteria(criteria);
	}
	
	public List<TblUser> getListByUsernameIgnoreState(String username) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblUser.class,"U");
		if(username!=null&&!"".equals(username))
			criteria.add(Restrictions.eq("username", username));
		return dao.findByCriteria(criteria);
	}

	public Pager getPagerByCriteria(Pager pager, String sel , String content) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblUser.class,"U");
		if(null!=content&&!"".equals(content)){
			if("1".equals(sel)){//用户名
				criteria.add(Restrictions.like("username", content,MatchMode.ANYWHERE));
			}else if("2".equals(sel)){//真实姓名
				criteria.add(Restrictions.like("realname", content,MatchMode.ANYWHERE));
			}
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("U.id"));
	}

	public void deleteUserById(String id) {
		// TODO Auto-generated method stub
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				this.deleteUser(string);//逐个删除
			}
		}
	}

	public LinkedHashMap<String, List<TblFunction>> getDataByUsercode(String usercode,String projectcode) {
		// TODO Auto-generated method stub
		//1.从用户和角色表中得到角色列表
		List<TblRole> list=uarser.getRoleByUsercode(usercode);//查询到角色List
		//2.从得到的角色列表中得到角色code
		List<String> li=this.renRolecode(list);
		//3.从得到的角色code在角色和功能表中得到功能CODE列表
		DetachedCriteria d=DetachedCriteria.forClass(TblRoleAndOperation.class,"ro");
		d.setProjection(Projections.property("ro.funcode"));
		
		if(li!=null&&li.size()>0)
		    d.add(Restrictions.in("ro.rolecode",li));
		else
			d.add(Restrictions.eq("ro.rolecode",null));
		
		List<String> liss=raodao.findByDetachedCriteria(d);
		liss=new ArrayList<String>(new LinkedHashSet<String>(liss));//去掉重复的功能表code
		//4.从得到的不重复的功能CODE列表中得到其以及父功能的列表
		List<String> lii = new ArrayList<String>();
		lii.addAll(liss);
		List<String> liiTemp = new ArrayList<String>();
		liiTemp.addAll(liss);
		while(liiTemp!=null&&liiTemp.size()>0){
			liiTemp = fundao.findFcodeByCodes(liiTemp);
			lii.addAll(liiTemp);
		}
		
		//5.从得到不重复的功能编码CODE中找到属于PROJECTCODE的功能CODE
		DetachedCriteria dc = DetachedCriteria.forClass(TblFunction.class,"fun");
		dc.setProjection(Projections.property("code"));
		
		if(lii!=null&&lii.size()>0)
		    dc.add(Restrictions.in("fun.code",lii));
		else
			dc.add(Restrictions.eq("fun.code",null));
		
		if(null!=projectcode&&!"".equals(projectcode))
			dc.add(Restrictions.eq("fun.projectcode", projectcode));
		dc.addOrder(Order.asc("fun.marker"));
		
		List<String> liii = fundao.findByDetachedCriteria(dc);
		
		//6.将得到的不重复的功能编码CODE编成MAP
		LinkedHashMap<String,List<TblFunction>> map = new LinkedHashMap<String, List<TblFunction>>();
		List<TblFunction> functionlist = fundao.getDataByCode(liii);
		for (TblFunction function : functionlist) {
			if(!map.containsKey(function.getFcode())){
				map.put(function.getFcode(),new ArrayList<TblFunction>());
			}
			map.get(function.getFcode()).add(function);
		}
		return map;
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

	public List<String> getOperationByUsercodeAndUrl(String usercode, String URL) {
		// TODO Auto-generated method stub
		//1.从用户和角色表中得到角色列表
		List<TblRole> list=uarser.getRoleByUsercode(usercode);//查询到角色List
		//2.从得到的角色列表中得到角色code
		List<String> li=this.renRolecode(list);
		//3.通过URL得到功能CODE
		DetachedCriteria d=DetachedCriteria.forClass(TblFunction.class,"fun");
		d.setProjection(Projections.property("fun.code"));
		if(null!=URL&&!"".equals(URL)){
			d.add(Restrictions.eq("fun.url", URL));
		}else{
			d.add(Restrictions.eq("fun.url", -1));		//前提条件：URL不能为-1 估计不可能为-1 。。
		}
		List<String> funcodes = fundao.findByDetachedCriteria(d);
		if(null==funcodes||funcodes.size()<=0)
			return null;
		String funcode = funcodes.get(0);
		//根据功能CODE和角色CODE查询TBLROLEANDOPERATION表中的操作数据 并去除重复
		DetachedCriteria de=DetachedCriteria.forClass(TblRoleAndOperation.class,"ro");
		de.setProjection(Projections.property("ro.operationname"));
		if(li!=null&&li.size()>0){
		    de.add(Restrictions.in("ro.rolecode",li));
		}else{
			de.add(Restrictions.eq("ro.rolecode",null));
		}
		if(null!=funcode&&!"".equals(funcode)){
			de.add(Restrictions.eq("ro.funcode",funcode));
		}else{
			de.add(Restrictions.eq("ro.funcode", null));		//这个功能code为null也是不可以的	
		}
		List<String> liss=raodao.findByDetachedCriteria(de);
		liss=new ArrayList<String>(new LinkedHashSet<String>(liss));//去掉重复的功能表code
		return liss;
	}

	public List<String> getOperationByUsercodeAndFuncode(String usercode, String funcode) {
		// TODO Auto-generated method stub
		//1.从用户和角色表中得到角色列表
		List<TblRole> list=uarser.getRoleByUsercode(usercode);//查询到角色List
		//2.从得到的角色列表中得到角色code
		List<String> li=this.renRolecode(list);
		//3.根据功能CODE和角色CODE查询TBLROLEANDOPERATION表中的操作数据 并去除重复
		DetachedCriteria de=DetachedCriteria.forClass(TblRoleAndOperation.class,"ro");
		de.setProjection(Projections.property("ro.operationname"));
		if(li!=null&&li.size()>0){
		    de.add(Restrictions.in("ro.rolecode",li));
		}else{
			de.add(Restrictions.eq("ro.rolecode",null));
		}
		if(null!=funcode&&!"".equals(funcode)){
			de.add(Restrictions.eq("ro.funcode",funcode));
		}else{
			de.add(Restrictions.eq("ro.funcode", null));		//这个功能code为null也是不可以的	
		}
		List<String> liss=raodao.findByDetachedCriteria(de);
		liss=new ArrayList<String>(new LinkedHashSet<String>(liss));//去掉重复的功能表code
		return liss;
	}

	public List<String> getNamesByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria d = DetachedCriteria.forClass(TblUser.class, "U");
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		d.add(Restrictions.in("id", list));
		d.setProjection(Projections.property("realname"));
		return dao.findByDetachedCriteria(d);
	}

	public List<TblUser> getDataByHealthid(String flid) {
		// TODO Auto-generated method stub
		DetachedCriteria d=DetachedCriteria.forClass(TblUser.class);
		d.add(Restrictions.eq("healthid", Integer.parseInt(flid)));
		return dao.findByCriteria(d);
	}

	public List<TblUser> getUsersByRolename(String rolename) {
		// TODO Auto-generated method stub
		DetachedCriteria role=DetachedCriteria.forClass(TblRole.class);
		role.add(Restrictions.eq("name",rolename));
		role.setProjection(Projections.property("code"));
		List<String> rolecodes = roledao.findByDetachedCriteria(role);
		
		DetachedCriteria uar=DetachedCriteria.forClass(TblUserAndRole.class);
		uar.add(Restrictions.in("rolecode",rolecodes));
		uar.setProjection(Projections.property("usercode"));
		List<String> usercodes = uardao.findByDetachedCriteria(uar);
		
		DetachedCriteria user=DetachedCriteria.forClass(TblUser.class);
		user.add(Restrictions.in("usercode",usercodes));
		return dao.findByCriteria(user);
	}
	public List<TblUser> getUserByGridName(String wangge){
		DetachedCriteria userCriteria=DetachedCriteria.forClass(TblUser.class);
		userCriteria.add(Restrictions.like("gridname",wangge,MatchMode.ANYWHERE));
		return dao.findByCriteria(userCriteria);
	}
}
