package www.quality.model;
// default package



/**
 * TblWhfl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblWhfl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer fid;


    // Constructors

    /** default constructor */
    public TblWhfl() {
    }

    
    /** full constructor */
    public TblWhfl(String name, Integer fid) {
        this.name = name;
        this.fid = fid;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getFid() {
        return this.fid;
    }
    
    public void setFid(Integer fid) {
        this.fid = fid;
    }
   








}