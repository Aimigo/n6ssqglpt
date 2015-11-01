package www.quality.model;

// default package

import java.util.Date;

/**
 * TblQyzp entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblQyzp implements java.io.Serializable {

	// Fields

	private Integer id;
	private String zpzw;
	private String qymc;
	private String zwyx;
	private String gzdd;
	private String dz;
	private String lxr;
	private String lxdh;
	private Date fbrq;
	private String zdxl;
	private String zwlb;
	private String zwms;
	private String zpgs;
	private String bz;
	private Integer userid;

	private String gzddname;
	private String zwlbname;
	private String username;
	
	// Constructors

	/** default constructor */
	public TblQyzp() {
	}

	/** full constructor */
	public TblQyzp(String zpzw, String qymc, String zwyx, String gzdd,
			Date fbrq, String zdxl, String zwlb, String zwms, String zpgs,
			String bz, Integer userid) {
		this.zpzw = zpzw;
		this.qymc = qymc;
		this.zwyx = zwyx;
		this.gzdd = gzdd;
		this.fbrq = fbrq;
		this.zdxl = zdxl;
		this.zwlb = zwlb;
		this.zwms = zwms;
		this.zpgs = zpgs;
		this.bz = bz;
		this.userid = userid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZpzw() {
		return this.zpzw;
	}

	public void setZpzw(String zpzw) {
		this.zpzw = zpzw;
	}

	public String getQymc() {
		return this.qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}

	public String getZwyx() {
		return this.zwyx;
	}

	public void setZwyx(String zwyx) {
		this.zwyx = zwyx;
	}

	public String getGzdd() {
		return this.gzdd;
	}

	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}

	public Date getFbrq() {
		return this.fbrq;
	}

	public void setFbrq(Date fbrq) {
		this.fbrq = fbrq;
	}

	public String getZdxl() {
		return this.zdxl;
	}

	public void setZdxl(String zdxl) {
		this.zdxl = zdxl;
	}

	public String getZwlb() {
		return this.zwlb;
	}

	public void setZwlb(String zwlb) {
		this.zwlb = zwlb;
	}

	public String getZwms() {
		return this.zwms;
	}

	public void setZwms(String zwms) {
		this.zwms = zwms;
	}

	public String getZpgs() {
		return this.zpgs;
	}

	public void setZpgs(String zpgs) {
		this.zpgs = zpgs;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getGzddname() {
		return gzddname;
	}

	public void setGzddname(String gzddname) {
		this.gzddname = gzddname;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getZwlbname() {
		return zwlbname;
	}

	public void setZwlbname(String zwlbname) {
		this.zwlbname = zwlbname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}