package www.quality.util;


import java.io.*;
import java.util.*;



public class pram {
    private static Properties getProperties() throws IOException {
        Properties pro = new Properties();
        pro.load(pram.class.getResourceAsStream("db.properties"));
        return pro;
    }

    public  String getDbDrv() {
        Properties pro;
        String dbDrv = "";
		try {
			pro = getProperties();
			dbDrv = pro.getProperty("code").trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return dbDrv;
    }
    public  String getDbApply() throws IOException {
            Properties pro = getProperties();
            String dbDrv = pro.getProperty("apply").trim();
            return dbDrv;
    }
    public  String getDbWatch() throws IOException {
            Properties pro = getProperties();
            String dbDrv = pro.getProperty("watch").trim();
            return dbDrv;
    }
    public  String getDbCheck() throws IOException {
                Properties pro = getProperties();
                String dbDrv = pro.getProperty("check").trim();
                return dbDrv;
    }
    public  String getDbTech() throws IOException {
            Properties pro = getProperties();
            String dbDrv = pro.getProperty("tech").trim();
            return dbDrv;
    }
    public  String getLink() throws IOException {
        Properties pro = getProperties();
        String dbDrv = pro.getProperty("link").trim();
        return dbDrv;
}
    public  String getServiceName() throws IOException {
        Properties pro = getProperties();
        String dbDrv = pro.getProperty("serviceName").trim();
        return dbDrv;
}
    public  String getMeans() throws IOException {
        Properties pro = getProperties();
        String dbDrv = pro.getProperty("means").trim();
        return dbDrv;
}

    public String getYspath() throws IOException{
    	Properties pro = getProperties();
        String dbDrv = pro.getProperty("yspath").trim();
        return dbDrv;
    }
    
    public String getJlpath() throws IOException{
    	Properties pro = getProperties();
        String dbDrv = pro.getProperty("jlpath").trim();
        return dbDrv;
    }
    public  String getYsPath() throws IOException {
        Properties pro = getProperties();
        String dbDrv = pro.getProperty("yspath").trim();
        return dbDrv;

}
    public  String getJlPath() throws IOException {
        Properties pro = getProperties();
        String dbDrv = pro.getProperty("jlpath").trim();
        return dbDrv;
}
}
