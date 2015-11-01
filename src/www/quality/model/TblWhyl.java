package www.quality.model;
// default package

import java.util.Date;


/**
 * TblWhylfl entity. @author MyEclipse Persistence Tools
 */

/**
 * @author pdwy
 *
 */
@SuppressWarnings("serial")
public class TblWhyl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String bt;
     private String ms;
     private String dz;
     private String tp;
     private Integer userid;
     private String username;
     private Date scsj;
     private Integer flid;
     private String flname;
     private String ly;
     private Integer sfsc;

    // Constructors

    /** default constructor */
    public TblWhyl() {
    }

    
    /** full constructor */
    public TblWhyl(String bt, String ms, String dz, Integer userid, Date scsj, Integer flid) {
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


	public String getTp() {
		return tp;
	}


	public void setTp(String tp) {
		this.tp = tp;
	}


	public String getLy() {
		return ly;
	}


	public void setLy(String ly) {
		this.ly = ly;
	}


	public Integer getSfsc() {
		return sfsc;
	}


	public void setSfsc(Integer sfsc) {
		this.sfsc = sfsc;
	}
   
}