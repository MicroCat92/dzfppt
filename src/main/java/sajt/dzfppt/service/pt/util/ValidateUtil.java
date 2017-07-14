package sajt.dzfppt.service.pt.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * <p>
 * [描述信息：基础验证工具类]
 * </p>
 * 
 * @version 1.0 Created on Nov 2, 2013 3:14:51 PM
 */
public class ValidateUtil {

    /**
     * <p>
     * 判断是否是手机号码
     * </p>
     * 
     * @param mobiles
     * @return boolean
     * @date: Created on Nov 2, 2013 3:14:37 PM
     */
    public static boolean isMobileNO(String mobiles) {
        boolean bl = false;
        if (mobiles.length() < 11) {
            return bl;
        } else {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            bl = m.matches();
        }
        return bl;
    }

    /**
     * 非空验证操作
     * 
     * @param parmaterStr
     * @return
     */
    public static boolean checkParmaterIsEmpty(String parmaterStr) {
        if (parmaterStr == null || "".equals(parmaterStr) || "".equals(parmaterStr.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 长度验证
     * 
     * @param parmaterStr
     * @param smallStrNumber
     * @param bigStrNumber
     * @return
     */
    public static boolean checkParmaterLength(String parmaterStr, int smallStrNumber, int bigStrNumber) {
        if (parmaterStr.length() < smallStrNumber) {
            return true;
        }
        if (parmaterStr.length() > bigStrNumber) {
            return true;
        }
        return false;
    }
    
    
    /**
     * 固定长度验证
     */
    public static boolean checkStaLength(String parmaterStr, int len) {
        if (parmaterStr.length()==len) {
            return true;
        }else{
        	return false;
        }      	
    }
    /**
     * 数值类型验证
     */
    public static boolean checkNumerical(String parmaterStr) {
	    try {
			Double.valueOf(parmaterStr);
			return true;
		} catch (Exception e) {
			return false;
		}
    }
    /**
     * 邮箱格式校验
     */
    public static boolean checkEmail(String parmaterStr) {
    	boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(parmaterStr);
            flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
    /**
     * 购方税号格式校验
     */
    public static boolean checkGHF_NSRSBH(String parmaterStr) {
    	boolean flag = false;
        try{
            String check = "[A-Z,a-z,0-9,-]*";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(parmaterStr);
            flag = matcher.matches();
           }catch(Exception e){
                flag = false;
           }
        return flag;
    }
    /**
     * <p>
     * 功能实现描述
     * </p>
     * 
     * @param args
     *            void
     * @date: Created on Nov 2, 2013 3:11:17 PM
     */
    public static void main(String[] args) {
    }
    public static byte[] unGZip(byte[] data) {
		  byte[] b = null;
		  try {
		   ByteArrayInputStream bis = new ByteArrayInputStream(data);
		   GZIPInputStream gzip = new GZIPInputStream(bis);
		   byte[] buf = new byte[1024];
		   int num = -1;
		   ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   while ((num = gzip.read(buf, 0, buf.length)) != -1) {
		    baos.write(buf, 0, num);
		   }
		   b = baos.toByteArray();
		   baos.flush();
		   baos.close();
		   gzip.close();
		   bis.close();
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  }
		  return b;
	 }
}
