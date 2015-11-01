package www.quality.service.impl;

import java.util.ArrayList;
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
import www.quality.model.TblFunction;
import www.quality.model.TblOperation;
import www.quality.service.TblFunctionService;
import www.quality.util.Pager;

@Service(TblFunctionService.TBLFUNCTION_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblFunctionServiceImpl implements TblFunctionService {

	@Resource(name=TblFunctionDao.TBLFUNCTION_DAO_IMPL)
	private TblFunctionDao dao;
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveFunction(TblFunction fun) {
		dao.save(fun);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateFunction(TblFunction fun) {
		dao.update(fun);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteFunction(Integer id) {
		
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblFunction t where t.id in ("+id+")");
		TblFunction f=this.getOneById(id);
		StringBuffer sb=new StringBuffer();
		sb.append("delete from TblOperation t where t.functioncode in ('"+f.getCode()+"')");
		/*
		List<Tbloperation> list=operdao.getDataByOpCode(f.getCode());//根据功能code来查找功能操作信息表的数据
		if(list!=null&&list.size()>0){
			String str=this.getOperationCode(list);//根据查出来的model数据集合，来分离出Tbloperation的code
			StringBuffer s=new StringBuffer();
			s.append("delete from TblroleAndOperation t where t.operationcode in("+str+")");
			dao.bulkUpdate(s.toString());//删除角色和功能操作信息表关联表数据
		}
		*/
		
		StringBuffer s=new StringBuffer();
		s.append("delete from TblRoleAndOperation t where t.funcode in('"+f.getCode()+"')");
		dao.bulkUpdate(s.toString());//删除角色和功能操作信息表关联表数据
		
		dao.bulkUpdate(sb.toString());//在删除功能操作信息表中的数据
		dao.bulkUpdate(sbuf.toString());//最后删除功能表中的信息
	}

	@SuppressWarnings("unused")
	private String getOperationCode(List<TblOperation> list){
		
		String str="";
		for(TblOperation o:list){
			if(str=="")
				str="'"+o.getCode()+"'";
			else
				str+=","+"'"+o.getCode()+"'";
		}
		return str;
	}
	
	public List<TblFunction> getAllData() {
		DetachedCriteria criteria = DetachedCriteria.forClass(TblFunction.class,"F");
		criteria.addOrder(Order.asc("marker"));
		return dao.findByCriteria(criteria);
	}

	public TblFunction getOneById(Integer id) {
		return dao.findById(id);
	}

	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblFunction.class,"F");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("F.id"));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteFunctionById(String id) {
		// TODO Auto-generated method stub
		String ids = id;
		
		//将ids转换成String数组再转换成Integer数组 为避免出错
		String[] idstr = ids.split(",");
		Integer[] idss = new Integer[idstr.length];//记录每次循环得出的ID一边查找其子节点
		
		for (int i = 0 ; i<idstr.length ;i++) {
			idss[i] = Integer.parseInt(idstr[i]);
		}
		
		DetachedCriteria criteria = DetachedCriteria.forClass(TblFunction.class,"F");
		criteria.add(Restrictions.in("id", idss));
		criteria.setProjection(Projections.property("code"));
		List<String> code = dao.findByDetachedCriteria(criteria);
				
		while(true){
			DetachedCriteria c = DetachedCriteria.forClass(TblFunction.class,"F");
			c.add(Restrictions.in("fcode", code));
			List<TblFunction> list = dao.findByCriteria(c);
			
			if(null==list||list.size()<=0)
				break;
			
			code = new ArrayList<String>();
			
			for (TblFunction fun : list) {
				ids += ","+fun.getId();
				code.add(fun.getCode());
			}
		}
		
		if(null!=ids&&!"".equals(ids)){
			String[] idsss = ids.split(",");
			for (String string : idsss) {
				this.deleteFunction(Integer.parseInt(string));//逐个删除
			}
		}
	}

	public TblFunction getOneByCode(String code) {
		// TODO Auto-generated method stub
		return dao.getOneBycode(code);
	}

	public List<TblFunction> getAllByCode(String fcode) {
		List<TblFunction> hql = dao.findByHql("from TblFunction where fcode='"+fcode+"'");
		return hql;
	}

	public List<TblFunction> getListByNameAndFcode(String name,String fcode) {
		List<TblFunction> list = dao.findByHql("from TblFunction where name='"+name+"' and fcode='"+fcode+"'");
		return list;
	}
	
	public void getTree(List<TblFunction> list,TblFunction fun){
		List<TblFunction> byCode = getAllByCode(fun.getCode());
		for(int i=0;i<byCode.size();i++){
			TblFunction func1 = byCode.get(i);
			list.add(func1);
			getTree(list,func1);
		}
	}
	
	public List<TblFunction> getsecndfnbypro(String procode) {
		List<TblFunction> fn_list = dao.findByHql("from TblFunction where projectcode='"+procode+"' and fcode='"+0+"'");
		ArrayList<TblFunction> list = new ArrayList<TblFunction>();
		for(TblFunction fn:fn_list){
			List<TblFunction> list2 = dao.findByHql("from TblFunction where  fcode='"+fn.getCode()+"'");
			list.addAll(list2);
		}
		return list;
	}

	public List<TblFunction> getFList(String code){
		List<TblFunction> ls=new ArrayList<TblFunction>();
		List<TblFunction> lst=new ArrayList<TblFunction>();
		TblFunction fn1 = dao.findByHql("from TblFunction where code='"+code+"'").get(0);
		ls.add(fn1);
		int i=0;
		while(!"0".equals(fn1.getFcode())&&i<10){
			i++;
			fn1=dao.findByHql("from TblFunction where code='"+fn1.getFcode()+"'").get(0);
			ls.add(fn1);
		}
		for(int j=ls.size()-1;j>=0;j--){
			lst.add(ls.get(j));
		}
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<String> getNamesByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria d = DetachedCriteria.forClass(TblFunction.class, "U");
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		d.add(Restrictions.in("id", list));
		d.setProjection(Projections.property("name"));
		return dao.findByDetachedCriteria(d);
	}

	/**
	 * 点击树形菜单时，查询本级及所有下级菜单
	 */
	@SuppressWarnings("unchecked")
	public Pager getPagerByCode(Pager pager, String code) {
		List<String> list = new  ArrayList<String>();
		List<String> listTemp = new ArrayList<String>();
		list.add(code);
		listTemp.add(code);
		while(null!=listTemp&&listTemp.size()>0){
			DetachedCriteria criteria= DetachedCriteria.forClass(TblFunction.class);
			criteria.setProjection(Projections.property("code"));
			criteria.add(Restrictions.in("fcode",listTemp));
		    listTemp=dao.findByDetachedCriteria(criteria);
		    list.addAll(listTemp);
		}
		
		DetachedCriteria dc= DetachedCriteria.forClass(TblFunction.class,"F");
		dc.add(Restrictions.in("code",list));
		return dao.getPagerByCriteria(dc, pager, Order.asc("F.id"));
	}

}
