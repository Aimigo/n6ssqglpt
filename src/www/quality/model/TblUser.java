package www.quality.model;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class TblUser implements Serializable {
	private Integer id;
	private String username;
	private String password;
	private String usercode;
	private String realname;
	private Date age;
	private String sex;
	private String email;
	private String phone;
	private String address;
	private String ms;
	private String state;
	private String gridname;
	private Integer departmentid;
	private Integer healthid;
	
	private String departmentname;
	private String healthname;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGridname() {
		return gridname;
	}
	public void setGridname(String gridname) {
		this.gridname = gridname;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public Integer getHealthid() {
		return healthid;
	}
	public void setHealthid(Integer healthid) {
		this.healthid = healthid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getHealthname() {
		return healthname;
	}
	public void setHealthname(String healthname) {
		this.healthname = healthname;
	}
	
}
