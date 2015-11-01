package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import www.quality.dao.TblQyzpDao;
import www.quality.dao.TblRegionDao;
import www.quality.model.TblGrjl;
import www.quality.model.TblQyzp;
import www.quality.model.TblRegion;
import www.quality.service.TblQyzpService;
import www.quality.util.Pager;


/**
*类的描述:实现TblQyzpService(企业招聘表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblQyzpService.TBLQYZP_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblQyzpServiceImpl implements TblQyzpService {

	@Resource(name=TblQyzpDao.TBLQYZP_DAO_IMPL)
	private TblQyzpDao dao;
	@Resource(name=TblRegionDao.TBLREGION_DAO_IMPL)
	private TblRegionDao regiondao;


	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblQyzp obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblQyzp obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblQyzp t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblQyzp> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblQyzp.class);
		return dao.getPagerByCriteria(d, p, Order.desc("id"));
	}

	public TblQyzp getOneById(Integer id) {		
		return dao.findById(id);
	}
	
	public Pager getPagerByCriteria(Pager p) throws ParseException{
		DetachedCriteria criteria = DetachedCriteria.forClass(TblQyzp.class);
		Map<String, String> params = p.getParams();
		String zpzw=params.get("zpzw");
		if(null!=zpzw&&!"".equals(zpzw)){
			criteria.add(Restrictions.like("zpzw", zpzw, MatchMode.ANYWHERE));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime=params.get("begintime");
		if(null!=begintime&&!"".equals(begintime)){
			try {
				criteria.add(Restrictions.ge("fbrq", sdf.parse(begintime+" 00:00:00")));
			} catch (ParseException e) {
				System.err.println("开始时间格式化错误！");
			}
		}
		String endtime=params.get("endtime");
		if(null!=endtime&&!"".equals(endtime)){
			try {
				criteria.add(Restrictions.le("fbrq", sdf.parse(endtime+" 23:59:59")));
			} catch (ParseException e) {
				System.err.println("结束时间格式化错误！");
			}
		}
		return dao.getPagerByCriteria(criteria, p, Order.desc("fbrq"));
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
	public List<String> getZpzwsByIds(String ids) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblQyzp.class);
		String[] idArray = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String str : idArray) {
			list.add(Integer.parseInt(str));
		}
		criteria.add(Restrictions.in("id", list));
		criteria.setProjection(Projections.property("zpzw"));
		return dao.findByDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<TblQyzp> getDataByGrjl(TblGrjl grjl) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblQyzp.class);
		//学位
		criteria.add(Restrictions.le("zdxl",grjl.getWhcd()));
		//职位类别
		Disjunction dis=Restrictions.disjunction();
		dis.add(Restrictions.eq("zwlb", grjl.getQzyw1()));
		if(null!=grjl.getQzyw2()&&!"".equals(grjl.getQzyw2()))
			dis.add(Restrictions.eq("zwlb", grjl.getQzyw2()));
		if(null!=grjl.getQzyw3()&&!"".equals(grjl.getQzyw3()))
			dis.add(Restrictions.eq("zwlb", grjl.getQzyw3()));
		criteria.add(dis);
		//地区
		List<String> regionCodes = new ArrayList<String>();
		List<String> regionCodesTemp = new ArrayList<String>();
		regionCodes.add(grjl.getZydq());
		regionCodesTemp.add(grjl.getZydq());
		while(null!=regionCodesTemp&&regionCodesTemp.size()>0){
			DetachedCriteria regionCriteria = DetachedCriteria.forClass(TblRegion.class);
			regionCriteria.add(Restrictions.in("fcode", regionCodesTemp));
			regionCriteria.setProjection(Projections.property("code"));
			regionCodesTemp = regiondao.findByDetachedCriteria(regionCriteria);
			regionCodes.addAll(regionCodesTemp);
		}
		
		//向上单独查询父类
		TblRegion region = regiondao.findByCode(grjl.getZydq());
		while(true){
			DetachedCriteria regionCriteriaUp = DetachedCriteria.forClass(TblRegion.class);
			regionCriteriaUp.add(Restrictions.eq("code", region.getFcode()));
			List<TblRegion> list = regiondao.findByCriteria(regionCriteriaUp);
			if(null==list||list.size()<=0)
				break;
			region = list.get(0);
			regionCodes.add(region.getCode());
		}
		
		criteria.add(Restrictions.in("gzdd", regionCodes));
		criteria.addOrder(Order.desc("fbrq"));
		return dao.findByCriteria(criteria);
	}

}
