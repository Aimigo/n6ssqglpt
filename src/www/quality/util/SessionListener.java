package www.quality.util;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@SuppressWarnings("rawtypes")
public class SessionListener implements HttpSessionListener,HttpSessionBindingListener{
	
	private static HashMap hUserName = new HashMap();//保存sessionID和username的映射       
	
	/**以下是实现HttpSessionListener中的方法**/       
	public void sessionCreated(HttpSessionEvent se){       
	}             
	
	public void sessionDestroyed(HttpSessionEvent se){           
		hUserName.remove(se.getSession().getId());       
	}      

	/**
	 *  isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法       
	 *  @param sUserid String-登录的用户id      
	 *  @return boolean-该用户是否已经登录过的标志 
	 */
	@SuppressWarnings("unchecked")
	public static boolean isAlreadyEnter(HttpSession session,Integer sUserid){           
		    boolean flag = false;           
		    if(hUserName.containsValue(sUserid.toString())){//如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中               
		    flag = true;               
	
		    //遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)               
		    Iterator iter = hUserName.entrySet().iterator();              
		    while (iter.hasNext()) {                   
		    	Map.Entry entry = (Map.Entry)iter.next();                   
		    	Object key = entry.getKey();                   
		    	Object val = entry.getValue();                   
		    	if( ( (Integer)val ).equals(sUserid) ){                       
		    		hUserName.remove(key);                   
		    		}               
		    	}               
		    hUserName.put( session.getId(),sUserid);//添加现在的sessionID和username               
		    System.out.println("hUserName = " + hUserName);          
		    }           
		    else{//如果该用户没登录过，直接添加现在的sessionID和username               
		    	//flag = false;               
		    	hUserName.put( session.getId(),sUserid);              
		    	System.out.println("hUserName = " + hUserName);           
		    	}           
		    return flag;       
	}   
	
	/**
	 *  isOnline-用于判断用户是否在线       *
	 *  @param session HttpSession-登录的用户session
	 *  @return boolean-该用户是否在线的标志             
	 */
	public static boolean isOnline(Integer sUserid){           
		boolean flag = false;      
		if(hUserName.containsValue(sUserid)){            
			flag = true;          
			}           
		return flag;       
    }  
	
	/**
	 * function:判断用户是否登录
	 * @param userName:  用户名
	 * @return boolean-用户是否在线的标志  
	 */
	public static boolean isOnline(String userName){
		boolean flag = false;          
	    if(hUserName.containsValue(userName)){
	       flag = true;  
	    }
		return flag;
	}
	/**
	 * function:判断用户是否登录
	 * @param userName:  用户名
	 * @return boolean-用户是否在线的标志  
	 */
	public static String zxzjid(){
		String zjid = "";          
	    // for( int i=0;i<hUserName.size();i++){
	    	 zjid+=hUserName.values();
	    	 zjid=zjid.substring(1,zjid.length()-1);
	    // };
		return zjid;
	}
	/**
	 * function:删除当前用户的session
	 * @param userName: 用户名
	 */
	public static void deleteCurUserSession(String userName){
		
	    Iterator iter = hUserName.entrySet().iterator();              
	    while (iter.hasNext()) {                   
	    	Map.Entry entry = (Map.Entry)iter.next();                   
	    	Object key = entry.getKey();                   
	    	Object val = entry.getValue();                   
	    	if( ( (String)val ).equals(userName)){                       
	    		hUserName.remove(key);                   
	    		}               
	    	}   
	}

	public void valueBound(HttpSessionBindingEvent e) {
		
	}

	public void valueUnbound(HttpSessionBindingEvent e) {
		deleteCurUserSession(e.getName());
	}
}