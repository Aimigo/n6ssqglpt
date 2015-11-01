package www.quality.model;

// default package

import java.util.Date;

/**
 * TblJkzx entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblJkzx implements java.io.Serializable {

	// Fields

	private Integer id;
	private String zxbt;
	private String zxnr;
	private String zxtp;
	private Integer flid;
	private Integer zjid;
	private Integer yjid;
	private String zxhf;
	private Date zxsj;
	private Date hfsj;
	private Boolean zjtx;
	private Boolean tyxtx;

	private String zjname;
	private String yhname;
	private String flname;

	// Constructors

	/** default constructor */
	public TblJkzx() {
	}

	/** full constructor */
	public TblJkzx(String zxbt, String zxnr, String zxtp, Integer flid,
			Integer zjid, Integer yjid, String zxhf, Date zxsj, Date hfsj,
			Boolean zjtx, Boolean tyxtx) {
		this.zxbt = zxbt;
		this.zxnr = zxnr;
		this.zxtp = zxtp;
		this.flid = flid;
		this.zjid = zjid;
		this.yjid = yjid;
		this.zxhf = zxhf;
		this.zxsj = zxsj;
		this.hfsj = hfsj;
		this.zjtx = zjtx;
		this.tyxtx = tyxtx;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZxbt() {
		return this.zxbt;
	}

	public void setZxbt(String zxbt) {
		this.zxbt = zxbt;
	}

	public String getZxnr() {
		return this.zxnr;
	}

	public void setZxnr(String zxnr) {
		this.zxnr = zxnr;
	}

	public String getZxtp() {
		return this.zxtp;
	}

	public void setZxtp(String zxtp) {
		this.zxtp = zxtp;
	}

	public Integer getFlid() {
		return this.flid;
	}

	public void setFlid(Integer flid) {
		this.flid = flid;
	}

	public Integer getZjid() {
		return this.zjid;
	}

	public void setZjid(Integer zjid) {
		this.zjid = zjid;
	}

	public Integer getYjid() {
		return this.yjid;
	}

	public void setYjid(Integer yjid) {
		this.yjid = yjid;
	}

	public String getZxhf() {
		return this.zxhf;
	}

	public void setZxhf(String zxhf) {
		this.zxhf = zxhf;
	}

	public Date getZxsj() {
		return this.zxsj;
	}

	public void setZxsj(Date zxsj) {
		this.zxsj = zxsj;
	}

	public Date getHfsj() {
		return this.hfsj;
	}

	public void setHfsj(Date hfsj) {
		this.hfsj = hfsj;
	}

	public Boolean getZjtx() {
		return this.zjtx;
	}

	public void setZjtx(Boolean zjtx) {
		this.zjtx = zjtx;
	}

	public Boolean getTyxtx() {
		return this.tyxtx;
	}

	public void setTyxtx(Boolean tyxtx) {
		this.tyxtx = tyxtx;
	}

	public String getZjname() {
		return zjname;
	}

	public void setZjname(String zjname) {
		this.zjname = zjname;
	}

	public String getYhname() {
		return yhname;
	}

	public void setYhname(String yhname) {
		this.yhname = yhname;
	}

	public String getFlname() {
		return flname;
	}

	public void setFlname(String flname) {
		this.flname = flname;
	}

}