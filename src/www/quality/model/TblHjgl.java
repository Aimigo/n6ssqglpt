package www.quality.model;
// default package



/**
 * TblHjgl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblHjgl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer syfwid;
     private String hkbh;
     private String dh;
     private String sj;
     private String sfz;
     private String hzname;
     //非数据字段
     private String syfwname;


    // Constructors

    /** default constructor */
    public TblHjgl() {
    }

    
    /** full constructor */
    public TblHjgl(Integer syfwid, String hkbh, String dh, String sj, String sfz) {
        this.syfwid = syfwid;
        this.hkbh = hkbh;
        this.dh = dh;
        this.sj = sj;
        this.sfz = sfz;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSyfwid() {
        return this.syfwid;
    }
    
    public void setSyfwid(Integer syfwid) {
        this.syfwid = syfwid;
    }

    public String getHkbh() {
        return this.hkbh;
    }
    
    public void setHkbh(String hkbh) {
        this.hkbh = hkbh;
    }

    public String getDh() {
        return this.dh;
    }
    
    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getSj() {
        return this.sj;
    }
    
    public void setSj(String sj) {
        this.sj = sj;
    }

    public String getSfz() {
        return this.sfz;
    }
    
    public void setSfz(String sfz) {
        this.sfz = sfz;
    }


	public String getSyfwname() {
		return syfwname;
	}


	public void setSyfwname(String syfwname) {
		this.syfwname = syfwname;
	}


	public String getHzname() {
		return hzname;
	}


	public void setHzname(String hzname) {
		this.hzname = hzname;
	}
   








}