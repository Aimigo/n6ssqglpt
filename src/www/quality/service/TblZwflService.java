package www.quality.service;

import java.util.List;

import www.quality.model.TblZwfl;

/**
*类的描述:TblZwfl(职位分类)所对应的服务方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblZwflService {

	public static final String TBLZWFL_SERVICE_IMPL = "www.pdwy.service.impl.TblZwflServiceImpl";
	
	public List<TblZwfl> getAllDate();
	
	public List<TblZwfl> getDataByFcode(String code);
	
}
