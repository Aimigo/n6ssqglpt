package www.quality.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblGridDao;
import www.quality.model.TblGrid;
import www.quality.service.TblGridService;
import www.quality.util.Pager;


/**
*类的描述:实现TblGridService(网格表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblGridService.TBLGRID_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblGridServiceImpl implements TblGridService {

	@Resource(name=TblGridDao.TBLGRID_DAO_IMPL)
	private TblGridDao dao;

	public List<TblGrid> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblGrid.class);
		return dao.getPagerByCriteria(d, p, Order.desc("name"));
	}

	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrid.class);
		Map<String, String> params = p.getParams();
		String name=params.get("name");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("name"));
	}
	
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrid.class);
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.like("name", name,MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("name"));
	}

	public List<TblGrid> getByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrid.class);
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		return dao.findByCriteria(criteria);
	}
	public String[] getNames(){
		List list = dao.findBySql("select name from XJ_LIAND_GRID", false);
		if(list!=null&&list.size()>0){
			return (String[])list.get(0);
		}
		return null;
	}
	public List<TblGrid> getDateByGridname(String gridname){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrid.class);
		String[] split = gridname.split(",");
		LogicalExpression or3=null;
		for(String gd:split){
			gd=gd.trim();
			if("".equals(gd)){
				continue;
			}
			if(or3==null){
				or3 = Restrictions.or(Restrictions.like("name", gd,MatchMode.ANYWHERE), Restrictions.eq("name", "gfgddsgdgdg"));
			}else{
				or3=Restrictions.or(Restrictions.like("name", gd,MatchMode.ANYWHERE), or3);
			}
		}
		if(or3!=null){
			criteria.add(or3);
		}
		List<TblGrid> list = dao.findByCriteria(criteria);
		
		return list;
	}

}
