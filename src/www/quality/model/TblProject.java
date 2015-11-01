package www.quality.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TblProject implements Serializable {

	private Integer id;
	private String code;//编号
	private String name;//名称
	private String ms;//描述
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	
	
}
