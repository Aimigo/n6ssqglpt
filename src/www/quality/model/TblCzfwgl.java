package www.quality.model;
// default package

import java.util.Date;


/**
 * TblCzfwgl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblCzfwgl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String sswg;
     private Integer syfwid;
     private String czr;
     private String dwjdz;
     private String fzsyxz;
     private Date zfsj;

     private String syfwname;
    // Constructors

    /** default constructor */
    public TblCzfwgl() {
    }

    
    /** full constructor */
    public TblCzfwgl(String sswg, Integer syfwid, String czr, String dwjdz, String fzsyxz, Date zfsj) {
        this.sswg = sswg;
        this.syfwid = syfwid;
        this.czr = czr;
        this.dwjdz = dwjdz;
        this.fzsyxz = fzsyxz;
        this.zfsj = zfsj;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSswg() {
        return this.sswg;
    }
    
    public void setSswg(String sswg) {
        this.sswg = sswg;
    }

    public Integer getSyfwid() {
        return this.syfwid;
    }
    
    public void setSyfwid(Integer syfwid) {
        this.syfwid = syfwid;
    }

    public String getCzr() {
        return this.czr;
    }
    
    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getDwjdz() {
        return this.dwjdz;
    }
    
    public void setDwjdz(String dwjdz) {
        this.dwjdz = dwjdz;
    }

    public String getFzsyxz() {
        return this.fzsyxz;
    }
    
    public void setFzsyxz(String fzsyxz) {
        this.fzsyxz = fzsyxz;
    }

    public Date getZfsj() {
        return this.zfsj;
    }
    
    public void setZfsj(Date zfsj) {
        this.zfsj = zfsj;
    }


	public String getSyfwname() {
		return syfwname;
	}


	public void setSyfwname(String syfwname) {
		this.syfwname = syfwname;
	}
   








}