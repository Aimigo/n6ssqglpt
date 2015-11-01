package www.quality.model;
// default package



/**
 * TblYlzsfl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblYlzsfl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;


    // Constructors

    /** default constructor */
    public TblYlzsfl() {
    }

    
    /** full constructor */
    public TblYlzsfl(String name) {
        this.name = name;
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
   








}