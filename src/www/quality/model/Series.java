package www.quality.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 城市信息
 * 
 * @author niuyong
 * 
 */
public class Series implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public List<Object> data=new ArrayList<Object>();
	public Series(String name, List<Object> data) {
		super();
		this.name = name;
		this.data = data;
	}
	public Series(String name) {
		super();
		this.name = name;
	}
	public Series(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	
}
