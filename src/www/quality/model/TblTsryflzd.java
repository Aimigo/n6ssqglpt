package www.quality.model;
// default package



/**
 * TblTsryflzd entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblTsryflzd  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer flid;
     private String zdname;
     private String datazd;
     private String zdlx;
     private String zddata;
     private String dw;
     private Integer px;
     private Boolean bt;
     private Boolean show;
     //封装临时数据
     public String value;

    // Constructors

    /** default constructor */
    public TblTsryflzd() {
    }

    
    /** full constructor */
    public TblTsryflzd(Integer flid, String zdname, String datazd, String zdlx, String zddata, String dw, Integer px, Boolean bt) {
        this.flid = flid;
        this.zdname = zdname;
        this.datazd = datazd;
        this.zdlx = zdlx;
        this.zddata = zddata;
        this.dw = dw;
        this.px = px;
        this.bt = bt;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlid() {
        return this.flid;
    }
    
    public void setFlid(Integer flid) {
        this.flid = flid;
    }

    public String getZdname() {
        return this.zdname;
    }
    
    public void setZdname(String zdname) {
        this.zdname = zdname;
    }

    public String getDatazd() {
        return this.datazd;
    }
    
    public void setDatazd(String datazd) {
        this.datazd = datazd;
    }

    public String getZdlx() {
        return this.zdlx;
    }
    
    public void setZdlx(String zdlx) {
        this.zdlx = zdlx;
    }

    public String getZddata() {
        return this.zddata;
    }
    
    public void setZddata(String zddata) {
        this.zddata = zddata;
    }

    public String getDw() {
        return this.dw;
    }
    
    public void setDw(String dw) {
        this.dw = dw;
    }

    public Integer getPx() {
        return this.px;
    }
    
    public void setPx(Integer px) {
        this.px = px;
    }

    public Boolean getBt() {
        return this.bt;
    }
    
    public void setBt(Boolean bt) {
        this.bt = bt;
    }


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public Boolean getShow() {
		return show;
	}


	public void setShow(Boolean show) {
		this.show = show;
	}

}