package www.quality.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblRoleDao;
import www.quality.dao.TblUserAndRoleDao;
import www.quality.model.TblRole;
import www.quality.model.TblUserAndRole;
import www.quality.service.TblUserAndRoleService;

@Service(TblUserAndRoleService.TBLUSERANDROLE_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblUserAndRoleServiceImpl implements TblUserAndRoleService {

	@Resource(name=TblUserAndRoleDao.TBLUSERANDROLE_DAO_IMPL)
	private TblUserAndRoleDao dao;
	@Resource(name=TblRoleDao.TBLROLE_DAO_IMPL)
	private TblRoleDao roledao;
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUandR(List<TblUserAndRole> list) {		
		dao.saveCollection(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUAndR(String usercode, List<TblUserAndRole> list) {
		this.deleteUAndR(usercode);
		dao.saveCollection(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUAndR(String usercode) {		
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblUserAndRole t where t.usercode in ('"+usercode+"')");
		dao.bulkUpdate(sbuf.toString());
	}

	@SuppressWarnings("unchecked")
	public List<TblRole> getRoleByUsercode(String usercode) {
		DetachedCriteria d=DetachedCriteria.forClass(TblUserAndRole.class,"ur");
		d.add(Restrictions.eq("ur.usercode", usercode));
		d.setProjection(Projections.property("ur.rolecode"));	
		List<String> list=dao.findByDetachedCriteria(d);		
		DetachedCriteria dr=DetachedCriteria.forClass(TblRole.class,"r");
		if(list!=null&&list.size()>0)
			dr.add(Restrictions.in("r.code", list));
		else 
			dr.add(Restrictions.eq("r.code", null));
		return roledao.findByCriteria(dr);
	}
	
}
