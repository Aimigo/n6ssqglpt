package www.quality.action;

import java.util.ArrayList;
import java.util.List;

import www.quality.model.TblRegion;
import www.quality.util.ZTree;


import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class RegionAction extends BasicAction implements ModelDriven<TblRegion> {
	
	private Gson gson = new Gson();
	
	private TblRegion region = new TblRegion();

	public TblRegion getModel() {
		// TODO Auto-generated method stub
		return region;
	}
	
	public String execute(){
		return "success";
	}
	
	public void getTree(){
		List<TblRegion> list = regionser.getDataByFcode(region.getCode());
		List<ZTree> treelist = new ArrayList<ZTree>();
		for (TblRegion reg: list) {
			ZTree ztree = new ZTree();
			ztree.setId(reg.getCode()+"");
			ztree.setpId(reg.getFcode()+"");
			ztree.setName(reg.getName());
			if(3==reg.getRegionlevel())
				ztree.setParent(false);
			else
				ztree.setParent(true);
			treelist.add(ztree);
		}
		super.setAjaxData(gson.toJson(treelist));
	}
}
