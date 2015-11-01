package www.quality.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
*类的描述:对实体类的分页进行封装
*作者:杜长吉
*创建日期：2012-03-19
*
*修改人
*修改日期
*修改原因描述
*/
@SuppressWarnings("rawtypes")
public class Pager {
	
	private int curPage=1;
	
	private int pageRows=13;  //每页显示数据行数
	private int totalPages;   //总的页数
	private int totalRows;	  //总的数据个数
	private List data;		  //分页中的数据List
	private Map<String,String>  params;		  //分页中的查询条件
		
	private int beginPageIndex;    //每组最后的页数
	private int endPageIndex;   //每组第一条数据
	private int yenum=7;		//分页显示页数
	

	

	public Pager() {
		params=new HashMap();
	}
	public Pager(int currentPage, int pageSize, int recordCount,List recordList){
		data=new ArrayList();
		params=new HashMap();
		this.curPage=currentPage;
		this.pageRows=pageSize;
		this.totalRows=recordCount;
		this.data=recordList;
	}
	
	public int getCurPage() {
		return this.curPage;
	}

	public void setCurPage(int curPage) {
		if(curPage<1)
			curPage=1;
		this.curPage = curPage;		
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		if(totalPages<1)
			totalPages=1;
		this.totalPages = totalPages;
		this.zunum();
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}


	public int getFirstPage() {
		return 1;
	}
	public int getLastPage() {
		return this.getTotalPages();
	}
	public int getNextPage() {
		return Math.min(curPage+1,getTotalPages());
	}
	public int getPreviousPage() {
		return Math.max(curPage-1,1);
	}

	public int getFirstRow() {
		return (getCurPage() - 1) * this.pageRows;
	}

	public Map<String,String> getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
	
	public Object removeParam(Object key){
		return this.params.remove(key);
	}
	
	@SuppressWarnings("unchecked")
	public Object putParam(String key,String value){
		return this.params.put(key, value);
	}
	
	public void cleanParams(){
		this.params.clear();		
	}

	
    public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public void zunum(){
		if(totalPages<=yenum){
    		beginPageIndex=1;
    		endPageIndex=totalPages;
    	}else{
    		if(yenum%2==0){
	    		beginPageIndex=curPage-(yenum/2-1);
	    		endPageIndex=curPage+(yenum/2);
    		}else{
    			beginPageIndex=curPage-(yenum/2);
	    		endPageIndex=curPage+(yenum/2);
    		}
    		if(beginPageIndex<1)
    		{
    			beginPageIndex=1;
    			endPageIndex=yenum;
    		}else if(endPageIndex>totalPages){
    			beginPageIndex=totalPages-yenum+1;	
    			endPageIndex=totalPages;
    		}    		
    	}
    }
	
}