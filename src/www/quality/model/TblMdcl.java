package www.quality.model;

// default package

import java.util.Date;

/**
 * TblMdcl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblMdcl implements java.io.Serializable {

	private Integer id;
	private String bt;
	private String nr;
	private Integer flid;
	private String clyj;
	private Date txsj;
	private Date clsj;
	private Integer cjrid;
	private String wg;
	private Integer glyid;
	private Integer dkbmryid;
	private Integer bmldid;
	private Integer ldid;
	private Integer zt;
	private Integer dkbm;
	private String jcfs;
	
	private String cjrname;
	private String glyname;
	private String dkbmryname;
	private String bmldname;
	private String ldname;
	private String flname;
	private String gridname;
	private String dkbmname;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getClyj() {
		return clyj;
	}

	public void setClyj(String clyj) {
		this.clyj = clyj;
	}

	public Date getTxsj() {
		return txsj;
	}

	public void setTxsj(Date txsj) {
		this.txsj = txsj;
	}

	public Date getClsj() {
		return clsj;
	}

	public void setClsj(Date clsj) {
		this.clsj = clsj;
	}

	public Integer getCjrid() {
		return cjrid;
	}

	public void setCjrid(Integer cjrid) {
		this.cjrid = cjrid;
	}

	public String getWg() {
		return wg;
	}

	public void setWg(String wg) {
		this.wg = wg;
	}

	public Integer getGlyid() {
		return glyid;
	}

	public void setGlyid(Integer glyid) {
		this.glyid = glyid;
	}

	public Integer getDkbmryid() {
		return dkbmryid;
	}

	public void setDkbmryid(Integer dkbmryid) {
		this.dkbmryid = dkbmryid;
	}

	public Integer getBmldid() {
		return bmldid;
	}

	public void setBmldid(Integer bmldid) {
		this.bmldid = bmldid;
	}

	public Integer getLdid() {
		return ldid;
	}

	public void setLdid(Integer ldid) {
		this.ldid = ldid;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public Integer getDkbm() {
		return dkbm;
	}

	public void setDkbm(Integer dkbm) {
		this.dkbm = dkbm;
	}

	public String getCjrname() {
		return cjrname;
	}

	public void setCjrname(String cjrname) {
		this.cjrname = cjrname;
	}

	public String getGlyname() {
		return glyname;
	}

	public void setGlyname(String glyname) {
		this.glyname = glyname;
	}

	public String getDkbmryname() {
		return dkbmryname;
	}

	public void setDkbmryname(String dkbmryname) {
		this.dkbmryname = dkbmryname;
	}

	public String getBmldname() {
		return bmldname;
	}

	public void setBmldname(String bmldname) {
		this.bmldname = bmldname;
	}

	public String getLdname() {
		return ldname;
	}

	public void setLdname(String ldname) {
		this.ldname = ldname;
	}

	public Integer getFlid() {
		return flid;
	}

	public void setFlid(Integer flid) {
		this.flid = flid;
	}

	public String getFlname() {
		return flname;
	}

	public void setFlname(String flname) {
		this.flname = flname;
	}

	public String getGridname() {
		return gridname;
	}

	public void setGridname(String gridname) {
		this.gridname = gridname;
	}

	public String getDkbmname() {
		return dkbmname;
	}

	public void setDkbmname(String dkbmname) {
		this.dkbmname = dkbmname;
	}

	public String getJcfs() {
		return jcfs;
	}

	public void setJcfs(String jcfs) {
		this.jcfs = jcfs;
	}

}