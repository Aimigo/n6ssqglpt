package www.quality.model;
// default package



/**
 * TblTsryfl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblTsryfl  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer sort;

    // Constructors

    /** default constructor */
    public TblTsryfl() {
    }

    
    /** full constructor */
    public TblTsryfl(String name, Integer sort) {
        this.name = name;
        this.sort = sort;
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

    public Integer getSort() {
        return this.sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }


}