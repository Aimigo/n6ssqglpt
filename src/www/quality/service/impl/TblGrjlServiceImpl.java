package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblGrjlDao;
import www.quality.dao.TblRegionDao;
import www.quality.model.TblGrjl;
import www.quality.model.TblQyzp;
import www.quality.model.TblRegion;
import www.quality.service.TblGrjlService;
import www.quality.util.Pager;


/**
*类的描述:实现TblGrjlService(个人简历表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblGrjlService.TBLGRJL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblGrjlServiceImpl implements TblGrjlService {

	@Resource(name=TblGrjlDao.TBLGRJL_DAO_IMPL)
	private TblGrjlDao dao;
	@Resource(name=TblRegionDao.TBLREGION_DAO_IMPL)
	private TblRegionDao regiondao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblGrjl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblGrjl obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		dao.deleteById(id);//删除表中的信息
	}

	public List<TblGrjl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblGrjl.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblGrjl getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p){
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrjl.class);
		Map<String, String> params = p.getParams();
		String sfzh=params.get("sfzh");
		if(null!=sfzh&&!"".equals(sfzh)){
			criteria.add(Restrictions.like("sfzh", sfzh, MatchMode.ANYWHERE));
		}
		String xm=params.get("xm");
		if(null!=xm&&!"".equals(xm)){
			criteria.add(Restrictions.like("xm", xm, MatchMode.ANYWHERE));
		}
		String djbbh=params.get("djbbh");
		if(null!=djbbh&&!"".equals(djbbh)){
			criteria.add(Restrictions.like("djbbh", djbbh, MatchMode.ANYWHERE));
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("yxq"));
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
	public List<String> getXmsByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrjl.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("xm"));
		return dao.findByDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<TblGrjl> getDataByQyzp(TblQyzp qyzp) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblGrjl.class);
		//学位
		criteria.add(Restrictions.ge("whcd",qyzp.getZdxl()));
		//职位类别
		Disjunction dis=Restrictions.disjunction();
		dis.add(Restrictions.eq("qzyw1", qyzp.getZwlb()));
		dis.add(Restrictions.eq("qzyw2", qyzp.getZwlb()));
		dis.add(Restrictions.eq("qzyw3", qyzp.getZwlb()));
		criteria.add(dis);
		//地区
		List<String> regionCodes = new ArrayList<String>();
		List<String> regionCodesTemp = new ArrayList<String>();
		regionCodes.add(qyzp.getGzdd());
		regionCodesTemp.add(qyzp.getGzdd());
		while(null!=regionCodesTemp&&regionCodesTemp.size()>0){
			DetachedCriteria regionCriteria = DetachedCriteria.forClass(TblRegion.class);
			regionCriteria.add(Restrictions.in("fcode", regionCodesTemp));
			regionCriteria.setProjection(Projections.property("code"));
			regionCodesTemp = regiondao.findByDetachedCriteria(regionCriteria);
			regionCodes.addAll(regionCodesTemp);
		}
		
		//向上单独查询父类
		TblRegion region = regiondao.findByCode(qyzp.getGzdd());
		while(true){
			DetachedCriteria regionCriteriaUp = DetachedCriteria.forClass(TblRegion.class);
			regionCriteriaUp.add(Restrictions.eq("code", region.getFcode()));
			List<TblRegion> list = regiondao.findByCriteria(regionCriteriaUp);
			if(null==list||list.size()<=0)
				break;
			region = list.get(0);
			regionCodes.add(region.getCode());
		}
		
		criteria.add(Restrictions.in("zydq", regionCodes));
		//有效期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			criteria.add(Restrictions.ge("yxq", sdf.parse(sdf.format(new Date()))));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		criteria.addOrder(Order.asc("djsj"));
		return dao.findByCriteria(criteria);
	}

}
