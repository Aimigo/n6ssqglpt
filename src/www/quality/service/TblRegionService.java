package www.quality.service;

import java.util.List;

import www.quality.model.TblRegion;

/**
*类的描述:TblRegion(行政区划)所对应的服务方法
*作者:杜长吉
*创建日期:2012-05-07
*
*修改人
*修改日期
*修改原因描述
*/
public interface TblRegionService {

	public static final String TBLREGION_SERVICE_IMPL = "www.pdwy.service.impl.TblRegionServiceImpl";
	
	public List<TblRegion> getAllDate();
	
	public List<TblRegion> getDataByFcode(String fcode);
	
}
