package www.quality.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TblUserAndRole implements Serializable {

	private Integer id;
	private String rolecode;
	private String usercode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolecode() {
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}	
}
