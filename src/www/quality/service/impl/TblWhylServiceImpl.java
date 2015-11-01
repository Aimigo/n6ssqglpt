package www.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import www.quality.dao.TblWhflDao;
import www.quality.dao.TblWhylDao;
import www.quality.model.TblWhyl;
import www.quality.service.TblWhylService;
import www.quality.util.Pager;




/**
*类的描述:实现TblWhylService(机构表)接口方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
@Service(TblWhylService.TBLWHYL_SERVICE_IMPL)
@Transactional(readOnly=true)
public class TblWhylServiceImpl implements TblWhylService {

	@Resource(name=TblWhylDao.TBLWHYL_DAO_IMPL)
	private TblWhylDao dao;
	@Resource(name=TblWhflDao.TBLWHFL_DAO_IMPL)
	private TblWhflDao fldao;
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(TblWhyl obj) {		
		dao.save(obj);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(TblWhyl obj) {
		dao.update(obj);	
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		StringBuffer sbuf=new StringBuffer();
		sbuf.append("delete from TblWhyl t where t.id in("+id+")");
		dao.bulkUpdate(sbuf.toString());//删除表中的信息
	}

	public List<TblWhyl> getAllDate() {
		return dao.getAll();
	}

	public Pager getDataByPager(Pager p) {
		DetachedCriteria d=DetachedCriteria.forClass(TblWhyl.class,"r");
		return dao.getPagerByCriteria(d, p, Order.desc("r.id"));
	}

	public TblWhyl getOneById(Integer id) {		
		return dao.findById(id);
	}

	public Pager getPagerByCriteria(Pager pager) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblWhyl.class,"R");
		Map<String, String> params = pager.getParams();
		String bt=params.get("bt");
		String flname=params.get("flname");
		String ly=params.get("ly");
		String username=params.get("username");
		if(null!=bt&&!"".equals(bt)){
			criteria.add(Restrictions.like("bt", bt,MatchMode.ANYWHERE));
		}
		if(null!=flname&&!"".equals(flname)){
			criteria.add(Restrictions.like("flname", flname,MatchMode.ANYWHERE));
		}
		if(null!=ly&&!"".equals(ly)){
			criteria.add(Restrictions.like("ly", ly,MatchMode.ANYWHERE));
		}
		if(null!=username&&!"".equals(username)){
			criteria.add(Restrictions.like("username", username,MatchMode.ANYWHERE));
		}
		
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.id"));
	}
	
	public Pager getPagerByCriteria(Pager pager,String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblWhyl.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		return dao.getPagerByCriteria(criteria, pager, Order.desc("R.date"));
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
				this.delete(Integer.parseInt(id));//逐个删除
			
		
	}

	public List<TblWhyl> getByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblWhyl.class,"R");
		if(null!=name&&!"".equals(name)){
			criteria.add(Restrictions.eq("name", name));
		}
		criteria.addOrder(Order.desc("date"));
		return dao.findByCriteria(criteria);
	}

	public List<TblWhyl> getByDateAndName(Date date,String name) throws ParseException {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TblWhyl.class,"R");
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
	
		public boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
            String mediaPicPath) throws Exception {
        // 创建一个List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // 添加转换工具路径
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
        convert.add("-qscale");     //指定转换的质量
        convert.add("6");
        convert.add("-ab");        //设置音频码率
        convert.add("64");
        convert.add("-ac");        //设置声道数
        convert.add("2");
        convert.add("-ar");        //设置声音的采样频率
        convert.add("22050");
        convert.add("-r");        //设置帧频
        convert.add("24");
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add(codcFilePath);

        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("17"); // 添加起始时间为第17秒
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("0.001"); // 添加持续时间为1毫秒
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("800*280"); // 添加截取的图片大小为350*240
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            builder.start();
            
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }

		public Map getTop() {
			Map topmap=new LinkedHashMap<String,Integer>();
			List toplist=dao.findBySql("select flid,count(*) from tblwhyl group by flid order by count(*) desc", false);
			
			List total=dao.findBySql("select count(*) from tblwhyl", false);
			topmap.put("total", total.get(0));
			Integer top4total=0;
			for(int i=0;i<toplist.size();i++){
				if(i>3){topmap.put("其他", Integer.valueOf(total.get(0).toString())-top4total); break;}
				Object[] pp=(Object[])toplist.get(i);
				topmap.put(fldao.findById(Integer.valueOf(pp[0].toString())).getName(), Integer.valueOf(pp[1].toString()));
				top4total+=Integer.valueOf(pp[1].toString());
			}
			return topmap;
		}
}
