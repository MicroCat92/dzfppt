package util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;



public class EncryptionMsg {
	
	public static final String CHARSET = "utf-8";
	
	/**
	 * Base64 ����
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String Base64_Encrypt(String msg) throws UnsupportedEncodingException {
		return new String(new Base64().encode(msg.getBytes(CHARSET)), CHARSET);
	}
	
	/**
	 * 3DES Base64 ����
	 * @param password
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String TripleDES_Base64_Encrypt(String password, String msg) throws UnsupportedEncodingException {
		return new String(new Base64().encode(TripleDESUtil.encryptMode(password, msg.getBytes(CHARSET))), CHARSET);
	}
	
	/**
	 * Gzip Base64 ����
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static String Gzip_Base64_Enycrypt(String msg) throws UnsupportedEncodingException, Exception {
		return new String(new Base64().encode(GZipUtils.compress(msg.getBytes(CHARSET))), CHARSET);
	}
	
	/**
	 * 3DES GZIP Base64 ����
	 * @param password
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static String TripleDES_Gzip_Base64_Encrypt(String password, String msg) throws UnsupportedEncodingException, Exception {
		return new String(new Base64().encode(GZipUtils.compress(TripleDESUtil.encryptMode(password, msg.getBytes(CHARSET)))), CHARSET);
	}

}
