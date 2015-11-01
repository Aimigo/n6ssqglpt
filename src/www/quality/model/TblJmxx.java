package www.quality.model;
// default package

import java.util.Date;


/**
 * TblJmxx entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TblJmxx  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String idNumber;
     private String name;
     private String register;
     private String gender;
     private String alias;
     private String cellphone;
     private String phone;
     private Date birthday;
     private String nation;
     private String politics;
     private String educational;
     private String educationYears;
     private String profession;
     private String unit;
     private String marital;
     private Double height;
     private String bloodType;
     private String faith;
     private String email;
     private String birthplace;
     private String contact;
     private String domicileDetailedAddress;
     private String householdType;
     private String subordinateCompany;
     private String residentialStatus;
     private Double citizenSatisfaction;
     private String presenceHousing;
     private String nohouse;
     private String community;
     private String road;
     private String ridgepole;
     private String element;
     private String roomnumber;
     private String remark;
     private String accountNumber;
     private String accountRelation;
     private String accountIdnumber;
     private String accountName;
     private String accountCellphone;
     private String accountPhone;
     private String accountFlphone;
     private String hushaiState;
     private String accountType;
     private String outReason;
     private Date outTime;
     private String outto;
     private String familyTitle;
     private String familyno;
     private String parentsRelationship;
     private String parentsidno;
     private String parentsname;
     private String parentscellphone;
     private String parentsphone;
     private String specialCrowd;
     private String otherInformation;
     private String ynout;
     private String bedied;
     private String shequjiaozheng;
     private String xingshijiejiao;
     private String jingshengbinren;
     private String xidurenyuan;
     private String aizibinren;
     private String weixianpingren;
     private String zhongdianshangfang;
     private String zhongdianqingnian;
     private String laonianren;
     private String canjiren;
     private String youfuduixiang;
     private String xujiuzhuren;
     private String shiyerenyuan;
     private String yuningfunv;
     private String huzhu;
     private String canbao;
     private String xuesheng;
     private String wuhaojiating;
     private String pinganjiating;
     private String kongbao;
     private String baopi;
     private String workers;
     private String managers;
     private String rests;
     private String wangge;
     private String oldwangge;
     private String oldcommunity;
     private String oldroad;
     private String oldridgepole;
     private String oldelement;
     private String oldroomnumber;
     private Integer isotherwg;
     private String otherwg;
     private String tsryflids;
     private String img;
     private Date createtime;
     private String bingyi;
     private Date liurutime;
     private Date liuchutime;
     private String xingshizheng;
    // Constructors



	/** default constructor */
    public TblJmxx() {
    }

    
    /** full constructor */
    public TblJmxx(String idNumber, String name, String register, String gender, String alias, String cellphone, String phone, Date birthday, String nation, String politics, String educational, String educationYears, String profession, String unit, String marital, Double height, String bloodType, String faith, String email, String birthplace, String contact, String domicileDetailedAddress, String householdType, String subordinateCompany, String residentialStatus, Double citizenSatisfaction, String presenceHousing, String nohouse, String community, String road, String ridgepole, String element, String roomnumber, String remark, String accountNumber, String accountRelation, String accountIdnumber, String accountName, String accountCellphone, String accountPhone, String accountFlphone, String hushaiState, String accountType, String outReason, Date outTime, String outto, String familyTitle, String familyno, String parentsRelationship, String parentsidno, String parentsname, String parentscellphone, String parentsphone, String specialCrowd, String otherInformation, String ynout, String bedied, String shequjiaozheng, String xingshijiejiao, String jingshengbinren, String xidurenyuan, String aizibinren, String weixianpingren, String zhongdianshangfang, String zhongdianqingnian, String laonianren, String canjiren, String youfuduixiang, String xujiuzhuren, String shiyerenyuan, String yuningfunv, String huzhu, String canbao, String xuesheng, String wuhaojiating, String pinganjiating, String kongbao, String baopi, String workers, String managers, String rests, String wangge, String oldwangge, String oldcommunity, String oldroad, String oldridgepole, String oldelement, String oldroomnumber) {
        this.idNumber = idNumber;
        this.name = name;
        this.register = register;
        this.gender = gender;
        this.alias = alias;
        this.cellphone = cellphone;
        this.phone = phone;
        this.birthday = birthday;
        this.nation = nation;
        this.politics = politics;
        this.educational = educational;
        this.educationYears = educationYears;
        this.profession = profession;
        this.unit = unit;
        this.marital = marital;
        this.height = height;
        this.bloodType = bloodType;
        this.faith = faith;
        this.email = email;
        this.birthplace = birthplace;
        this.contact = contact;
        this.domicileDetailedAddress = domicileDetailedAddress;
        this.householdType = householdType;
        this.subordinateCompany = subordinateCompany;
        this.residentialStatus = residentialStatus;
        this.citizenSatisfaction = citizenSatisfaction;
        this.presenceHousing = presenceHousing;
        this.nohouse = nohouse;
        this.community = community;
        this.road = road;
        this.ridgepole = ridgepole;
        this.element = element;
        this.roomnumber = roomnumber;
        this.remark = remark;
        this.accountNumber = accountNumber;
        this.accountRelation = accountRelation;
        this.accountIdnumber = accountIdnumber;
        this.accountName = accountName;
        this.accountCellphone = accountCellphone;
        this.accountPhone = accountPhone;
        this.accountFlphone = accountFlphone;
        this.hushaiState = hushaiState;
        this.accountType = accountType;
        this.outReason = outReason;
        this.outTime = outTime;
        this.outto = outto;
        this.familyTitle = familyTitle;
        this.familyno = familyno;
        this.parentsRelationship = parentsRelationship;
        this.parentsidno = parentsidno;
        this.parentsname = parentsname;
        this.parentscellphone = parentscellphone;
        this.parentsphone = parentsphone;
        this.specialCrowd = specialCrowd;
        this.otherInformation = otherInformation;
        this.ynout = ynout;
        this.bedied = bedied;
        this.shequjiaozheng = shequjiaozheng;
        this.xingshijiejiao = xingshijiejiao;
        this.jingshengbinren = jingshengbinren;
        this.xidurenyuan = xidurenyuan;
        this.aizibinren = aizibinren;
        this.weixianpingren = weixianpingren;
        this.zhongdianshangfang = zhongdianshangfang;
        this.zhongdianqingnian = zhongdianqingnian;
        this.laonianren = laonianren;
        this.canjiren = canjiren;
        this.youfuduixiang = youfuduixiang;
        this.xujiuzhuren = xujiuzhuren;
        this.shiyerenyuan = shiyerenyuan;
        this.yuningfunv = yuningfunv;
        this.huzhu = huzhu;
        this.canbao = canbao;
        this.xuesheng = xuesheng;
        this.wuhaojiating = wuhaojiating;
        this.pinganjiating = pinganjiating;
        this.kongbao = kongbao;
        this.baopi = baopi;
        this.workers = workers;
        this.managers = managers;
        this.rests = rests;
        this.wangge = wangge;
        this.oldwangge = oldwangge;
        this.oldcommunity = oldcommunity;
        this.oldroad = oldroad;
        this.oldridgepole = oldridgepole;
        this.oldelement = oldelement;
        this.oldroomnumber = oldroomnumber;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdNumber() {
        return this.idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getRegister() {
        return this.register;
    }
    
    public void setRegister(String register) {
        this.register = register;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCellphone() {
        return this.cellphone;
    }
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return this.nation;
    }
    
    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitics() {
        return this.politics;
    }
    
    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getEducational() {
        return this.educational;
    }
    
    public void setEducational(String educational) {
        this.educational = educational;
    }

    public String getEducationYears() {
        return this.educationYears;
    }
    
    public void setEducationYears(String educationYears) {
        this.educationYears = educationYears;
    }

    public String getProfession() {
        return this.profession;
    }
    
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMarital() {
        return this.marital;
    }
    
    public void setMarital(String marital) {
        this.marital = marital;
    }

    public Double getHeight() {
        return this.height;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }

    public String getBloodType() {
        return this.bloodType;
    }
    
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getFaith() {
        return this.faith;
    }
    
    public void setFaith(String faith) {
        this.faith = faith;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthplace() {
        return this.birthplace;
    }
    
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDomicileDetailedAddress() {
        return this.domicileDetailedAddress;
    }
    
    public void setDomicileDetailedAddress(String domicileDetailedAddress) {
        this.domicileDetailedAddress = domicileDetailedAddress;
    }

    public String getHouseholdType() {
        return this.householdType;
    }
    
    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getSubordinateCompany() {
        return this.subordinateCompany;
    }
    
    public void setSubordinateCompany(String subordinateCompany) {
        this.subordinateCompany = subordinateCompany;
    }

    public String getResidentialStatus() {
        return this.residentialStatus;
    }
    
    public void setResidentialStatus(String residentialStatus) {
        this.residentialStatus = residentialStatus;
    }

    public Double getCitizenSatisfaction() {
        return this.citizenSatisfaction;
    }
    
    public void setCitizenSatisfaction(Double citizenSatisfaction) {
        this.citizenSatisfaction = citizenSatisfaction;
    }

    public String getPresenceHousing() {
        return this.presenceHousing;
    }
    
    public void setPresenceHousing(String presenceHousing) {
        this.presenceHousing = presenceHousing;
    }

    public String getNohouse() {
        return this.nohouse;
    }
    
    public void setNohouse(String nohouse) {
        this.nohouse = nohouse;
    }

    public String getCommunity() {
        return this.community;
    }
    
    public void setCommunity(String community) {
        this.community = community;
    }

    public String getRoad() {
        return this.road;
    }
    
    public void setRoad(String road) {
        this.road = road;
    }

    public String getRidgepole() {
        return this.ridgepole;
    }
    
    public void setRidgepole(String ridgepole) {
        this.ridgepole = ridgepole;
    }

    public String getElement() {
        return this.element;
    }
    
    public void setElement(String element) {
        this.element = element;
    }

    public String getRoomnumber() {
        return this.roomnumber;
    }
    
    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountRelation() {
        return this.accountRelation;
    }
    
    public void setAccountRelation(String accountRelation) {
        this.accountRelation = accountRelation;
    }

    public String getAccountIdnumber() {
        return this.accountIdnumber;
    }
    
    public void setAccountIdnumber(String accountIdnumber) {
        this.accountIdnumber = accountIdnumber;
    }

    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getAccountCellphone() {
        return this.accountCellphone;
    }
    
    public void setAccountCellphone(String accountCellphone) {
        this.accountCellphone = accountCellphone;
    }

    public String getAccountPhone() {
        return this.accountPhone;
    }
    
    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getAccountFlphone() {
        return this.accountFlphone;
    }
    
    public void setAccountFlphone(String accountFlphone) {
        this.accountFlphone = accountFlphone;
    }

    public String getHushaiState() {
        return this.hushaiState;
    }
    
    public void setHushaiState(String hushaiState) {
        this.hushaiState = hushaiState;
    }

    public String getAccountType() {
        return this.accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getOutReason() {
        return this.outReason;
    }
    
    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    public Date getOutTime() {
        return this.outTime;
    }
    
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getOutto() {
        return this.outto;
    }
    
    public void setOutto(String outto) {
        this.outto = outto;
    }

    public String getFamilyTitle() {
        return this.familyTitle;
    }
    
    public void setFamilyTitle(String familyTitle) {
        this.familyTitle = familyTitle;
    }

    public String getFamilyno() {
        return this.familyno;
    }
    
    public void setFamilyno(String familyno) {
        this.familyno = familyno;
    }

    public String getParentsRelationship() {
        return this.parentsRelationship;
    }
    
    public void setParentsRelationship(String parentsRelationship) {
        this.parentsRelationship = parentsRelationship;
    }

    public String getParentsidno() {
        return this.parentsidno;
    }
    
    public void setParentsidno(String parentsidno) {
        this.parentsidno = parentsidno;
    }

    public String getParentsname() {
        return this.parentsname;
    }
    
    public void setParentsname(String parentsname) {
        this.parentsname = parentsname;
    }

    public String getParentscellphone() {
        return this.parentscellphone;
    }
    
    public void setParentscellphone(String parentscellphone) {
        this.parentscellphone = parentscellphone;
    }

    public String getParentsphone() {
        return this.parentsphone;
    }
    
    public void setParentsphone(String parentsphone) {
        this.parentsphone = parentsphone;
    }

    public String getSpecialCrowd() {
        return this.specialCrowd;
    }
    
    public void setSpecialCrowd(String specialCrowd) {
        this.specialCrowd = specialCrowd;
    }

    public String getOtherInformation() {
        return this.otherInformation;
    }
    
    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getYnout() {
        return this.ynout;
    }
    
    public void setYnout(String ynout) {
        this.ynout = ynout;
    }

    public String getBedied() {
        return this.bedied;
    }
    
    public void setBedied(String bedied) {
        this.bedied = bedied;
    }

    public String getShequjiaozheng() {
        return this.shequjiaozheng;
    }
    
    public void setShequjiaozheng(String shequjiaozheng) {
        this.shequjiaozheng = shequjiaozheng;
    }

    public String getXingshijiejiao() {
        return this.xingshijiejiao;
    }
    
    public void setXingshijiejiao(String xingshijiejiao) {
        this.xingshijiejiao = xingshijiejiao;
    }

    public String getJingshengbinren() {
        return this.jingshengbinren;
    }
    
    public void setJingshengbinren(String jingshengbinren) {
        this.jingshengbinren = jingshengbinren;
    }

    public String getXidurenyuan() {
        return this.xidurenyuan;
    }
    
    public void setXidurenyuan(String xidurenyuan) {
        this.xidurenyuan = xidurenyuan;
    }

    public String getAizibinren() {
        return this.aizibinren;
    }
    
    public void setAizibinren(String aizibinren) {
        this.aizibinren = aizibinren;
    }

    public String getWeixianpingren() {
        return this.weixianpingren;
    }
    
    public void setWeixianpingren(String weixianpingren) {
        this.weixianpingren = weixianpingren;
    }

    public String getZhongdianshangfang() {
        return this.zhongdianshangfang;
    }
    
    public void setZhongdianshangfang(String zhongdianshangfang) {
        this.zhongdianshangfang = zhongdianshangfang;
    }

    public String getZhongdianqingnian() {
        return this.zhongdianqingnian;
    }
    
    public void setZhongdianqingnian(String zhongdianqingnian) {
        this.zhongdianqingnian = zhongdianqingnian;
    }

    public String getLaonianren() {
        return this.laonianren;
    }
    
    public void setLaonianren(String laonianren) {
        this.laonianren = laonianren;
    }

    public String getCanjiren() {
        return this.canjiren;
    }
    
    public void setCanjiren(String canjiren) {
        this.canjiren = canjiren;
    }

    public String getYoufuduixiang() {
        return this.youfuduixiang;
    }
    
    public void setYoufuduixiang(String youfuduixiang) {
        this.youfuduixiang = youfuduixiang;
    }

    public String getXujiuzhuren() {
        return this.xujiuzhuren;
    }
    
    public void setXujiuzhuren(String xujiuzhuren) {
        this.xujiuzhuren = xujiuzhuren;
    }

    public String getShiyerenyuan() {
        return this.shiyerenyuan;
    }
    
    public void setShiyerenyuan(String shiyerenyuan) {
        this.shiyerenyuan = shiyerenyuan;
    }

    public String getYuningfunv() {
        return this.yuningfunv;
    }
    
    public void setYuningfunv(String yuningfunv) {
        this.yuningfunv = yuningfunv;
    }

    public String getHuzhu() {
        return this.huzhu;
    }
    
    public void setHuzhu(String huzhu) {
        this.huzhu = huzhu;
    }

    public String getCanbao() {
        return this.canbao;
    }
    
    public void setCanbao(String canbao) {
        this.canbao = canbao;
    }

    public String getXuesheng() {
        return this.xuesheng;
    }
    
    public void setXuesheng(String xuesheng) {
        this.xuesheng = xuesheng;
    }

    public String getWuhaojiating() {
        return this.wuhaojiating;
    }
    
    public void setWuhaojiating(String wuhaojiating) {
        this.wuhaojiating = wuhaojiating;
    }

    public String getPinganjiating() {
        return this.pinganjiating;
    }
    
    public void setPinganjiating(String pinganjiating) {
        this.pinganjiating = pinganjiating;
    }

    public String getKongbao() {
        return this.kongbao;
    }
    
    public void setKongbao(String kongbao) {
        this.kongbao = kongbao;
    }

    public String getBaopi() {
        return this.baopi;
    }
    
    public void setBaopi(String baopi) {
        this.baopi = baopi;
    }

    public String getWorkers() {
        return this.workers;
    }
    
    public void setWorkers(String workers) {
        this.workers = workers;
    }

    public String getManagers() {
        return this.managers;
    }
    
    public void setManagers(String managers) {
        this.managers = managers;
    }

    public String getRests() {
        return this.rests;
    }
    
    public void setRests(String rests) {
        this.rests = rests;
    }

    public String getWangge() {
        return this.wangge;
    }
    
    public void setWangge(String wangge) {
        this.wangge = wangge;
    }

    public String getOldwangge() {
        return this.oldwangge;
    }
    
    public void setOldwangge(String oldwangge) {
        this.oldwangge = oldwangge;
    }

    public String getOldcommunity() {
        return this.oldcommunity;
    }
    
    public void setOldcommunity(String oldcommunity) {
        this.oldcommunity = oldcommunity;
    }

    public String getOldroad() {
        return this.oldroad;
    }
    
    public void setOldroad(String oldroad) {
        this.oldroad = oldroad;
    }

    public String getOldridgepole() {
        return this.oldridgepole;
    }
    
    public void setOldridgepole(String oldridgepole) {
        this.oldridgepole = oldridgepole;
    }

    public String getOldelement() {
        return this.oldelement;
    }
    
    public void setOldelement(String oldelement) {
        this.oldelement = oldelement;
    }

    public String getOldroomnumber() {
        return this.oldroomnumber;
    }
    
    public void setOldroomnumber(String oldroomnumber) {
        this.oldroomnumber = oldroomnumber;
    }




	public Integer getIsotherwg() {
		return isotherwg;
	}


	public void setIsotherwg(Integer isotherwg) {
		this.isotherwg = isotherwg;
	}



	public String getOtherwg() {
		return otherwg;
	}


	public void setOtherwg(String otherwg) {
		this.otherwg = otherwg;
	}


	public String getTsryflids() {
		return tsryflids;
	}


	public void setTsryflids(String tsryflids) {
		this.tsryflids = tsryflids;
	}


	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public String getBingyi() {
		return bingyi;
	}


	public void setBingyi(String bingyi) {
		this.bingyi = bingyi;
	}


	public Date getLiurutime() {
		return liurutime;
	}


	public void setLiurutime(Date liurutime) {
		this.liurutime = liurutime;
	}


	public Date getLiuchutime() {
		return liuchutime;
	}


	public void setLiuchutime(Date liuchutime) {
		this.liuchutime = liuchutime;
	}


	public String getXingshizheng() {
		return xingshizheng;
	}


	public void setXingshizheng(String xingshizheng) {
		this.xingshizheng = xingshizheng;
	}






}