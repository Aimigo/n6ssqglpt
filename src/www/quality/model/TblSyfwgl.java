package www.quality.model;
// default package

import java.util.Date;


/**
 * TblSyfwgl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblSyfwgl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String sswg;
     private String fwlx;
     private String xqdz;
     private Integer lyid;
     private String zhuang;
     private String dy;
     private String shi;
     private String fwjzqk;
     private String fczh;
     private String cqr;
     private String jzmj;
     private String rjmj;
     private Date jfsj;
     private String jg;
     //非数据库自动
     private String lyname;
     private Boolean isCzfw;
    // Constructors

    /** default constructor */
    public TblSyfwgl() {
    }

    
    /** full constructor */
    public TblSyfwgl(String sswg, String fwlx, String xqdz, Integer lyid, String zhuang, String dy, String shi, String fwjzqk, String fczh, String cqr, String jzmj, String rjmj, Date jfsj, String jg) {
        this.sswg = sswg;
        this.fwlx = fwlx;
        this.xqdz = xqdz;
        this.lyid = lyid;
        this.zhuang = zhuang;
        this.dy = dy;
        this.shi = shi;
        this.fwjzqk = fwjzqk;
        this.fczh = fczh;
        this.cqr = cqr;
        this.jzmj = jzmj;
        this.rjmj = rjmj;
        this.jfsj = jfsj;
        this.jg = jg;
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

    public String getFwlx() {
        return this.fwlx;
    }
    
    public void setFwlx(String fwlx) {
        this.fwlx = fwlx;
    }

    public String getXqdz() {
        return this.xqdz;
    }
    
    public void setXqdz(String xqdz) {
        this.xqdz = xqdz;
    }

    public Integer getLyid() {
        return this.lyid;
    }
    
    public void setLyid(Integer lyid) {
        this.lyid = lyid;
    }

    public String getZhuang() {
        return this.zhuang;
    }
    
    public void setZhuang(String zhuang) {
        this.zhuang = zhuang;
    }

    public String getDy() {
        return this.dy;
    }
    
    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getShi() {
        return this.shi;
    }
    
    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getFwjzqk() {
        return this.fwjzqk;
    }
    
    public void setFwjzqk(String fwjzqk) {
        this.fwjzqk = fwjzqk;
    }

    public String getFczh() {
        return this.fczh;
    }
    
    public void setFczh(String fczh) {
        this.fczh = fczh;
    }

    public String getCqr() {
        return this.cqr;
    }
    
    public void setCqr(String cqr) {
        this.cqr = cqr;
    }

    public String getJzmj() {
        return this.jzmj;
    }
    
    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public String getRjmj() {
        return this.rjmj;
    }
    
    public void setRjmj(String rjmj) {
        this.rjmj = rjmj;
    }

    public Date getJfsj() {
        return this.jfsj;
    }
    
    public void setJfsj(Date jfsj) {
        this.jfsj = jfsj;
    }

    public String getJg() {
        return this.jg;
    }
    
    public void setJg(String jg) {
        this.jg = jg;
    }


	public String getLyname() {
		return lyname;
	}


	public void setLyname(String lyname) {
		this.lyname = lyname;
	}


	public Boolean getIsCzfw() {
		return isCzfw;
	}


	public void setIsCzfw(Boolean isCzfw) {
		this.isCzfw = isCzfw;
	}
   








}