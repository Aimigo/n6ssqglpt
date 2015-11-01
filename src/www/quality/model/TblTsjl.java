package www.quality.model;
// default package

import java.util.Date;


/**
 * TblTsjl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblTsjl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String nr;
     private String name;
     private Date sj;
     private Integer zt;
     private Integer fl;
     private String phone;

    // Constructors

    /** default constructor */
    public TblTsjl() {
    }

    
    /** full constructor */
    public TblTsjl(String nr, String name, Date sj) {
        this.nr = nr;
        this.name = name;
        this.sj = sj;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNr() {
        return this.nr;
    }
    
    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getSj() {
        return this.sj;
    }
    
    public void setSj(Date sj) {
        this.sj = sj;
    }


	public Integer getZt() {
		return zt;
	}


	public void setFl(Integer fl) {
		this.fl = fl;
	}

	public Integer getFl() {
		return fl;
	}


	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
   








}