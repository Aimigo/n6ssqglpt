package www.quality.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TblLoginLog implements Serializable {

	private Integer id;
	private String username;//登陆用户名
	private String realname;//用户真实名称
	private String loginip;//登陆IP
	private Date logintime;//登陆时间
	
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
}
