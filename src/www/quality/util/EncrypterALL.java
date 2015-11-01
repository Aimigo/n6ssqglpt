package www.quality.util;

import www.quality.util.CryptionDataAll;

/**
 * 数据加密函数--DES 加密
 * @author DC-IRC开发组
 * @since 12:21:41
 * @version 1.0
 * @see
 */
public class EncrypterALL
{

  //  private static String nnn=nn;
	 /**
	   * 数据解密方法
	   * @param xml
	   * @return
     */

    public static String desc(String xml)
        {
            pram pp=new pram();
            String nn = null;
            
                nn = pp.getDbDrv();
            

        CryptionDataAll pCryptionDataALL = new CryptionDataAll(nn);
            return pCryptionDataALL.unSecureData(xml);
        }

    /**
     * 数据解密方法
     * @param xml
     * @return
     */
    public static String desc(String xml,String key,String encode)
        {
        CryptionDataAll pCryptionDataALL = new CryptionDataAll(key,encode);
            return pCryptionDataALL.unSecureData(xml);
        }

    /**
     * 数据加密方法
     * @param xml
     * @return
     */
        public static String enc(String xml)
        {
                CryptionDataAll pCryptionDataALL = new CryptionDataAll();
                return pCryptionDataALL.SecureData(xml);
        }

        /**
         * 数据加密方法
         * @param xml
         * @return
         */
        public static String enc(String xml,String key,String encode)
        {
                CryptionDataAll pCryptionDataALL = new CryptionDataAll(key,encode);
                return pCryptionDataALL.SecureData(xml);
        }

        public static void main(String[] arg)
        {
                //BD24ECC9C5788272
//		ABF9A71C0A862C90E948702432925F3B5EC2D735E026173155961AD5CF50E39CE095E271ECC554D7CB6EFED29D53633A6EC194C790D50053B4BAC725FEDF321A704F64A898F045C1372A0420F1886E8C

                String mystring = "87AA4EC204138EB26CAEDE7DE19774063E3F4A6EFAC12DE7A4C6BD358659C535";
                //mystring = EncrypterALL.enc(mystring,"12345678","UTF-8");
                String test="3243F08B3E928613B1C84F9338327F00";
                //System.out.println(mystring);
                mystring = EncrypterALL.desc(mystring,"DD)^)%FC","UTF-8");
                System.out.println("111111"+mystring);
                test=EncrypterALL.desc(test,"12345678","UTF-8");
                System.out.println("22222"+test);
                String test2=EncrypterALL.desc(test,"DD)^)%FC","UTF-8");
                 System.out.println("3333"+test2);
        }
}
