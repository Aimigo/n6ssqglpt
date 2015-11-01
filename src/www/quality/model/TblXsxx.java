package www.quality.model;
// default package

import java.util.Date;


/**
 * TblXsxx entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblXsxx  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String sfz;
     private String xb;
     private String csnf;
     private String xxjd;
     private String xx;
     private Date rxsj;
     private Date yjbysj;


    // Constructors

    /** default constructor */
    public TblXsxx() {
    }

    
    /** full constructor */
    public TblXsxx(String sfz, String xb, String csnf, String xxjd, String xx, Date rxsj, Date yjbysj) {
        this.sfz = sfz;
        this.xb = xb;
        this.csnf = csnf;
        this.xxjd = xxjd;
        this.xx = xx;
        this.rxsj = rxsj;
        this.yjbysj = yjbysj;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSfz() {
        return this.sfz;
    }
    
    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getXb() {
        return this.xb;
    }
    
    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsnf() {
        return this.csnf;
    }
    
    public void setCsnf(String csnf) {
        this.csnf = csnf;
    }

    public String getXxjd() {
        return this.xxjd;
    }
    
    public void setXxjd(String xxjd) {
        this.xxjd = xxjd;
    }

    public String getXx() {
        return this.xx;
    }
    
    public void setXx(String xx) {
        this.xx = xx;
    }

    public Date getRxsj() {
        return this.rxsj;
    }
    
    public void setRxsj(Date rxsj) {
        this.rxsj = rxsj;
    }

    public Date getYjbysj() {
        return this.yjbysj;
    }
    
    public void setYjbysj(Date yjbysj) {
        this.yjbysj = yjbysj;
    }
   








}