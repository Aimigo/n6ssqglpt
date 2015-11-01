package www.quality.model;
// default package

import java.util.Date;


/**
 * TblPxjn entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblPxjn  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String bt;
     private String ms;
     private String dz;
     private Integer userid;
     private Date scsj;
     private Integer flid;
     private Integer type;
     private String tp;
     
     private String flname;
     private String username;
     
    // Constructors

    /** default constructor */
    public TblPxjn() {
    }

    
    /** full constructor */
    public TblPxjn(String bt, String ms, String dz, Integer userid, Date scsj, Integer flid) {
        this.bt = bt;
        this.ms = ms;
        this.dz = dz;
        this.userid = userid;
        this.scsj = scsj;
        this.flid = flid;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBt() {
        return this.bt;
    }
    
    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getMs() {
        return this.ms;
    }
    
    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getDz() {
        return this.dz;
    }
    
    public void setDz(String dz) {
        this.dz = dz;
    }

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getScsj() {
        return this.scsj;
    }
    
    public void setScsj(Date scsj) {
        this.scsj = scsj;
    }

    public Integer getFlid() {
        return this.flid;
    }
    
    public void setFlid(Integer flid) {
        this.flid = flid;
    }
    
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getFlname() {
		return flname;
	}

	public void setFlname(String flname) {
		this.flname = flname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}