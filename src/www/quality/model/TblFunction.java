package www.quality.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TblFunction implements Serializable {

	private Integer id;
	private String name;
	private String code;
	private String fcode;
	private String projectcode;
	private String funicon;//功能图标
	private String funicon2;//功能图标2
	private String url;
	private Integer marker;
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
	public String getFcode() {
		return fcode;
	}
	public void setFcode(String fcode) {
		this.fcode = fcode;
	}
	public String getProjectcode() {
		return projectcode;
	}
	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}
	public String getFunicon() {
		return funicon;
	}
	public void setFunicon(String funicon) {
		this.funicon = funicon;
	}
	public String getFunicon2() {
		return funicon2;
	}
	public void setFunicon2(String funicon2) {
		this.funicon2 = funicon2;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getMarker() {
		return marker;
	}
	public void setMarker(Integer marker) {
		this.marker = marker;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	
}
