package www.quality.action;

import java.util.ArrayList;
import java.util.List;

import www.quality.model.TblZwfl;
import www.quality.util.ZTree;


import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ZwflAction extends BasicAction implements ModelDriven<TblZwfl> {
	
	private Gson gson = new Gson();
	
	private TblZwfl zwfl = new TblZwfl();

	public TblZwfl getModel() {
		// TODO Auto-generated method stub
		return zwfl;
	}
	
	public String execute(){
		return "success";
	}
	
	public void getTree(){
		List<TblZwfl> list = zwflser.getDataByFcode(zwfl.getCode());
		List<ZTree> treelist = new ArrayList<ZTree>();
		for (TblZwfl zw: list) {
			ZTree ztree = new ZTree();
			ztree.setId(zw.getCode());
			ztree.setpId(zw.getFcode());
			ztree.setName(zw.getName());
			if(3==zw.getCode().length())
				ztree.setParent(false);
			else
				ztree.setParent(true);
			treelist.add(ztree);
		}
		super.setAjaxData(gson.toJson(treelist));
	}
}
