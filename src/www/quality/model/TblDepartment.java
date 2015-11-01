package www.quality.model;
// default package



/**
 * TblDepartment entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblDepartment  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;


    // Constructors

    /** default constructor */
    public TblDepartment() {
    }

    
    /** full constructor */
    public TblDepartment(String name) {
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