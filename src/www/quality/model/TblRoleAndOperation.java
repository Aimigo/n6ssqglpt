package www.quality.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TblRoleAndOperation implements Serializable {

	private Integer id;
	private String rolecode;
	private String funxxcode;
	private String funcode;
	
	private String operationname;//对应OPERATIONXX的name;
	
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
	public String getFunxxcode() {
		return funxxcode;
	}
	public void setFunxxcode(String funxxcode) {
		this.funxxcode = funxxcode;
	}
	public String getFuncode() {
		return funcode;
	}
	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}
	public String getOperationname() {
		return operationname;
	}
	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}
	
}
