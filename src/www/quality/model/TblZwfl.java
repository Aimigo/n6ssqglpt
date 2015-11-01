package www.quality.model;
// default package

/**
 * TblZwfl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblZwfl implements java.io.Serializable {

	// Fields

	private String code;
	private String name;
	private String fcode;

	// Constructors

	/** default constructor */
	public TblZwfl() {
	}

	/** full constructor */
	public TblZwfl(String name, String fcode) {
		this.name = name;
		this.fcode = fcode;
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

}