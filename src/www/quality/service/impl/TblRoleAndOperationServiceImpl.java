package www.quality.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblRoleAndOperationDao;
import www.quality.model.TblRoleAndOperation;
import www.quality.service.TblRoleAndOperationService;

@Service(TblRoleAndOperationService.TBLROLEANDOPERATION_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblRoleAndOperationServiceImpl implements
		TblRoleAndOperationService {

	@Resource(name=TblRoleAndOperationDao.TBLROLEANDOPERATION_DAO_IMPL)
	private TblRoleAndOperationDao dao;
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveRoleAndOperation(List<TblRoleAndOperation> list) {
		dao.saveCollection(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateRoleAndOperation(String rolecode,
			List<TblRoleAndOperation> list) {
		this.deleteRoleAndOperation(rolecode);
		dao.saveCollection(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRoleAndOperation(String rolecode) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblRoleAndOperation t where t.rolecode in ('"+rolecode+"')");
		dao.bulkUpdate(sbuf.toString());
	}

	public List<TblRoleAndOperation> getDataByRolecode(String rolecode) {	
		DetachedCriteria d=DetachedCriteria.forClass(TblRoleAndOperation.class,"t");
		d.add(Restrictions.in("t.rolecode", rolecode.split(",")));
		return dao.findByCriteria(d);
	}

}
