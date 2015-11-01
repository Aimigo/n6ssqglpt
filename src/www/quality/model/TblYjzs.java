package www.quality.model;

// default package

/**
 * TblYjzs entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblYjzs implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bt;
	private String nr;
	private Integer flid;

	private String flname;
	
	// Constructors

	/** default constructor */
	public TblYjzs() {
	}

	/** full constructor */
	public TblYjzs(String bt, String nr, Integer flid) {
		this.bt = bt;
		this.nr = nr;
		this.flid = flid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBt() {
		return this.bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getNr() {
		return this.nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Integer getFlid() {
		return this.flid;
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

}