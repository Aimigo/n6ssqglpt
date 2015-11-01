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

import www.quality.dao.TblRoleDao;
import www.quality.model.TblRole;
import www.quality.service.TblRoleService;
import www.quality.util.Pager;

@Service(TblRoleService.TBLROLE_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblRoleServiceImpl implements TblRoleService {

	@Resource(name=TblRoleDao.TBLROLE_DAO_IMPL)
	private TblRoleDao dao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveRole(TblRole role) {		
		dao.save(role);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateRole(TblRole role) {
		dao.merge(role);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRole(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblRole t where t.id in("+id+")");
		TblRole role=dao.findById(id);
		StringBuffer sb=new StringBuffer();
		sb.append("delete from TblUserAndRole t where t.rolecode in('"+role.getCode()+"')");
		
		StringBuffer s=new StringBuffer();
		s.append("delete from TblRoleAndOperation t where t.rolecode in('"+role.getCode()+"')");
			
		dao.bulkUpdate(sb.toString());//首先删除用户和角色关联表中的信息
		dao.bulkUpdate(s.toString());//再删除角色和功能操作信息关联表
		dao.bulkUpdate(sbuf.toString());//再删除角色表中的信息
	}

	public List<TblRole> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblRole.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblRole getOneById(Integer id) {		
		return dao.findById(id);
	}

	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblRole.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRoleById(String id) {
		// TODO Auto-generated method stub
		if(null!=id&&!"".equals(id)){
			String[] ids = id.split(",");
			for (String string : ids) {
				this.deleteRole(Integer.parseInt(string));//逐个删除
			}
		}
	}

	public List<TblRole> getRoleByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblRole.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		return dao.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<String> getNamesByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria d = DetachedCriteria.forClass(TblRole.class, "U");
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		d.add(Restrictions.in("id", list));
		d.setProjection(Projections.property("name"));
		return dao.findByDetachedCriteria(d);
	}
}
