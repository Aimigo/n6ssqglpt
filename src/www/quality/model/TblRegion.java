package www.quality.model;

// default package

/**
 * TblRegion entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblRegion implements java.io.Serializable {

	// Fields

	private String code;
	private String name;
	private String fcode;
	private Short regionlevel;
	private Boolean status;

	// Constructors

	/** default constructor */
	public TblRegion() {
	}

	/** full constructor */
	public TblRegion(String name, String fcode, Short regionlevel, Boolean status) {
		this.name = name;
		this.fcode = fcode;
		this.regionlevel = regionlevel;
		this.status = status;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFcode() {
		return this.fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public Short getRegionlevel() {
		return this.regionlevel;
	}

	public void setRegionlevel(Short regionlevel) {
		this.regionlevel = regionlevel;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}