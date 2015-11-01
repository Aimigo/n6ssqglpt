package www.quality.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;
import sun.misc.*;

/**
 * DES encryption algorithm, providing the encryption and decryption algorithm
 * for byte array and string
 * 
 * @author : Yao (WICT)
 * @version 1.0
 */
public class CryptionDataAll {
	// The length of Encryptionstring should be 8 bytes and not be
	// a weak key
	private String EncryptionString;
	private String Encode = "";

	// The initialization vector should be 8 bytes
	private final byte[] EncryptionIV;
	private final static String DES = "DES/CBC/PKCS5Padding";

	// pram pp=new pram();

	// final static String code=pp.getDbDrv();
	public CryptionDataAll() {
		// Encode="11111111";
		this("12345678");
	}

	/**
	 * Saving key for encryption and decryption
	 * 
	 * @param EncryptionString
	 *            String
	 */
	public CryptionDataAll(String EncryptionString) {
		this(EncryptionString, "GBK");
	}

	/**
	 * Saving key for encryption and decryption
	 * 
	 * @param EncryptionString
	 *            String
	 */
	public CryptionDataAll(String EncryptionString, String Encode) {
		this.EncryptionString = EncryptionString;
		this.Encode = Encode;
		this.EncryptionIV = EncryptionString.getBytes();
	}

	/**
	 * Encrypt a byte array
	 * 
	 * @param SourceData
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public byte[] EncryptionByteData(byte[] SourceData) throws Exception {
		byte[] retByte = null;

		// Create SecretKey object

		byte[] EncryptionByte = EncryptionString.getBytes();
		DESKeySpec dks = new DESKeySpec(EncryptionByte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Create IvParameterSpec object with initialization vector
		IvParameterSpec spec = new IvParameterSpec(EncryptionIV);

		// Create Cipter object
		Cipher cipher = Cipher.getInstance(DES);

		// Initialize Cipher object
		cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);

		// Encrypting data
		retByte = cipher.doFinal(SourceData);
		return retByte;
	}

	/**
	 * Decrypt a byte array
	 * 
	 * @param SourceData
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public byte[] DecryptionByteData(byte[] SourceData) throws Exception {
		byte[] retByte = null;

		// Create SecretKey object
		byte[] EncryptionByte = EncryptionString.getBytes();
		DESKeySpec dks = new DESKeySpec(EncryptionByte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Create IvParameterSpec object with initialization vector
		IvParameterSpec spec = new IvParameterSpec(EncryptionIV);

		// Create Cipter object
		Cipher cipher = Cipher.getInstance(DES);

		// Initialize Cipher object
		cipher.init(Cipher.DECRYPT_MODE, securekey, spec);

		// Decrypting data
		retByte = cipher.doFinal(SourceData);

		return retByte;
	}

	/**
	 * Encrypt a string
	 * 
	 * @param SourceData
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public String EncryptionStringData(String SourceData) throws Exception {
		String retStr = null;
		byte[] retByte = null;

		// Transform SourceData to byte array
		byte[] sorData = SourceData.getBytes();

		// Encrypte data
		retByte = EncryptionByteData(sorData);

		// Encode encryption data
		BASE64Encoder be = new BASE64Encoder();
		retStr = be.encode(retByte);

		return retStr;
	}

	/**
	 * Decrypt a string
	 * 
	 * @param SourceData
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public String DecryptionStringData(String SourceData) throws Exception {
		String retStr = null;
		byte[] retByte = null;

		// Decode encryption data
		BASE64Decoder bd = new BASE64Decoder();
		byte[] sorData = bd.decodeBuffer(SourceData);

		// Decrypting data
		retByte = DecryptionByteData(sorData);
		retStr = new String(retByte);

		return retStr;
	}

	public String SecureData(String str) {

		try {
			return enStrByChar(EncryptionByteData(str.getBytes(Encode)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public String unSecureData(String encstr) {
		try {
			return new String(DecryptionByteData(unStrByChar(encstr)), Encode);

		} catch (Exception es) {
			es.printStackTrace();
		}

		return "";
	}

	public byte[] unStrByChar(String str) {
		if (str == null)
			return null;

		byte[] strResult = new byte[str.length() / 2];

		// System.out.println(str.length()/2);
		for (int i = 0; i < str.length() / 2; i++) {
			// int ss = (getInt(str.charAt(4*i)) << 12) + (getInt(str.charAt(4*i
			// + 1)) << 8) + (getInt(str.charAt(4*i + 2)) << 4) +
			// (getInt(str.charAt(4*i + 3))) ;
			char ss = (char) ((getInt(str.charAt(2 * i)) << 4) + (getInt(str
					.charAt(2 * i + 1))));

			// System.out.println("ss :" + ss) ;

			strResult[i] = (byte) (ss); // getRealchar(ss) ;
		}
		return strResult;

	}

	public String enStrByChar(byte[] str) {
		if (str == null) {
			return "";
		}
		String strResult = "";
		for (int i = 0; i < str.length; i++) {
			char ch = (char) (str[i]);
			// strResult += getNumChar((ch & 0xF000) >> 12);
			// strResult += getNumChar((ch & 0x0F00) >> 8) ;
			strResult += getNumChar((ch & 0x00F0) >> 4);
			strResult += getNumChar(ch & 0x000F);
		}
		return strResult;

	}

	char getNumChar(int i) {
		char ch = ' ';
		switch (i & 0x0F) {
		case 0x00:
			ch = '0';
			break;
		case 0x01:
			ch = '1';
			break;
		case 0x02:
			ch = '2';
			break;
		case 0x03:
			ch = '3';
			break;
		case 0x04:
			ch = '4';
			break;
		case 0x05:
			ch = '5';
			break;
		case 0x06:
			ch = '6';
			break;
		case 0x07:
			ch = '7';
			break;
		case 0x08:
			ch = '8';
			break;
		case 0x09:
			ch = '9';
			break;
		case 0x0A:
			ch = 'A';
			break;
		case 0x0B:
			ch = 'B';
			break;
		case 0x0C:
			ch = 'C';
			break;
		case 0x0D:
			ch = 'D';
			break;
		case 0x0E:
			ch = 'E';
			break;
		case 0x0F:
			ch = 'F';
			break;
		default:
			break;
		}
		return ch;
	}

	int getInt(char ch) {
		short i = 0;

		switch (ch) {
		case '0':
			i = 0;
			break;
		case '1':
			i = 1;
			break;
		case '2':
			i = 2;
			break;
		case '3':
			i = 3;
			break;
		case '4':
			i = 4;
			break;
		case '5':
			i = 5;
			break;
		case '6':
			i = 6;
			break;
		case '7':
			i = 7;
			break;
		case '8':
			i = 8;
			break;
		case '9':
			i = 9;
			break;
		case 'A':
			i = 0x0A;
			break;
		case 'B':
			i = 0x0B;
			break;
		case 'C':
			i = 0x0C;
			break;
		case 'D':
			i = 0x0D;
			break;
		case 'E':
			i = 0x0E;
			break;
		case 'F':
			i = 0x0F;
			break;
		default:
			i = 0;
			break;
		}
		// System.out.println(i) ;
		return i;
	}

	char getRealchar(int i) {
		int tmpi = i & 0xFFFF;
		// System.out.println(tmpi) ;
		return (char) (tmpi);// Character.forDigit(tmpi, 10) ;

	}

	@SuppressWarnings("unused")
	public static void main(String[] arg) {
		// CryptionDataAll test = new CryptionDataAll("87654321") ;
		// String mystring = "xuning" ;
		CryptionDataAll test = new CryptionDataAll();
		String mystring = "50";
		System.out.println(mystring);
		mystring = test.SecureData(mystring);
		System.out.println(mystring);
		mystring = test.unSecureData("6F422F3EC95B1F3B");
		System.out.println(mystring);
		String admin = "admin==2BA086EC85D01AFE";
		// System.out.println(test.unSecureData("F7267FCB7314E584BE1E5053B7891908A6A7AC29BF3B7043"));
	}
}
