package www.quality.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TblRole implements Serializable {

	private Integer id;
	private String name;
	private String code;
	private String ms;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
}
