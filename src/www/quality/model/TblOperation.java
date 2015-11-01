package www.quality.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TblOperation implements Serializable {

	private Integer id;
	private String name;//对应OPERATIONXX的code
	private String code;//自身的code
	private String functioncode;//功能code
	
	private String operationname;//对应OPERATIONXX的name;
	
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
	public String getFunctioncode() {
		return functioncode;
	}
	public void setFunctioncode(String functioncode) {
		this.functioncode = functioncode;
	}
	public String getOperationname() {
		return operationname;
	}
	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}
}
