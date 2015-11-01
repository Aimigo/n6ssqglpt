package www.quality.model;
// default package



/**
 * TblZz entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblZz  implements java.io.Serializable {


    // Fields    

     private Long objectid;
     private String grade;
     private String name;
     private String type;
     private String crop;
     private String owner;
     private Double area;
     private String grid;
     private String shequ;
     private String xiaoqu;
     private String dong;
     private String shape;


    // Constructors

    /** default constructor */
    public TblZz() {
    }

    
    /** full constructor */
    public TblZz(String grade, String name, String type, String crop, String owner, Double area, String grid, String shequ, String xiaoqu, String dong, String shape) {
        this.grade = grade;
        this.name = name;
        this.type = type;
        this.crop = crop;
        this.owner = owner;
        this.area = area;
        this.grid = grid;
        this.shequ = shequ;
        this.xiaoqu = xiaoqu;
        this.dong = dong;
        this.shape = shape;
    }

   
    // Property accessors

    public Long getObjectid() {
        return this.objectid;
    }
    
    public void setObjectid(Long objectid) {
        this.objectid = objectid;
    }

    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getCrop() {
        return this.crop;
    }
    
    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getOwner() {
        return this.owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getArea() {
        return this.area;
    }
    
    public void setArea(Double area) {
        this.area = area;
    }

    public String getGrid() {
        return this.grid;
    }
    
    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getShequ() {
        return this.shequ;
    }
    
    public void setShequ(String shequ) {
        this.shequ = shequ;
    }

    public String getXiaoqu() {
        return this.xiaoqu;
    }
    
    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    public String getDong() {
        return this.dong;
    }
    
    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getShape() {
        return this.shape;
    }
    
    public void setShape(String shape) {
        this.shape = shape;
    }
   








}