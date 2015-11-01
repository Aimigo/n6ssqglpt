package www.quality.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TblLog implements Serializable {

	private Integer id;
	private String username;//用户名
	private String projectname;//项目名称
	private String functionname;//功能名称
	private String operationname;//操作名称
	private String ms;//操作详细描述
	private Date operationtime;//操作时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getFunctionname() {
		return functionname;
	}
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
	public String getOperationname() {
		return operationname;
	}
	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public Date getOperationtime() {
		return operationtime;
	}
	public void setOperationtime(Date operationtime) {
		this.operationtime = operationtime;
	}
	
	
}
