package www.quality.model;

import java.util.Date;
// default package



/**
 * TblZz entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblLcrk  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String huzhu;
     private String zinv;
     private Date liuchushijian;
     private Integer nianling;
     private String xianjuzhudi;
     private String danwei;
     private String dianhua;
     private String beizhu;


    // Constructors

    /** default constructor */
    public TblLcrk() {
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getHuzhu() {
		return huzhu;
	}


	public void setHuzhu(String huzhu) {
		this.huzhu = huzhu;
	}


	public String getZinv() {
		return zinv;
	}


	public void setZinv(String zinv) {
		this.zinv = zinv;
	}






	public Date getLiuchushijian() {
		return liuchushijian;
	}


	public void setLiuchushijian(Date liuchushijian) {
		this.liuchushijian = liuchushijian;
	}


	public Integer getNianling() {
		return nianling;
	}


	public void setNianling(Integer nianling) {
		this.nianling = nianling;
	}


	public String getXianjuzhudi() {
		return xianjuzhudi;
	}


	public void setXianjuzhudi(String xianjuzhudi) {
		this.xianjuzhudi = xianjuzhudi;
	}



	public String getDanwei() {
		return danwei;
	}


	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}


	public String getDianhua() {
		return dianhua;
	}


	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}


	public String getBeizhu() {
		return beizhu;
	}


	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

    


}