/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package sajt.dzfppt.service.pt.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import sajt.dzfppt.service.pt.bean.GlobalInfo;

/**
 * RSA加密算法工具类
 * @author xingqian.xq
 * @version $Id: RSAUtil.java, v 0.1 2017-01-13 下午3:16 xingqian.xq Exp $$
 */
public class RSAUtil {
    /**
     * 加签
     * @param data  待加签数据
     * @param privateKey  私钥
     * @return  签名
     * @throws Exception  异常
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {

        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateK);
        signature.update(data.getBytes("GBK"));
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     * @param publicKey  公钥
     * @param srcData  原始字符串
     * @param sign  签名
     * @return  是否验签通过
     * @throws Exception  异常
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {

        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicK);
        signature.update(srcData.getBytes("GBK"));
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    /**
     * 获取密钥对
     * @return  密钥对
     * @throws Exception  异常
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

    /**
     * 获取私钥
     *
     * @param privateKey  私钥字符串
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] encodedKey = privateKey.getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     * @param publicKey   公钥字符串
     * @return 公钥对象
     * @throws Exception 异常
     */
    public static PublicKey getPublickey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey
            .getBytes()));
        return keyFactory.generatePublic(bobPubKeySpec);
    }
    @Test
    public void test() throws Exception{
//    	String filepath="E:/tmp/"; 
    	String content="<REQUEST_COMMON_DOWNLOAD_CA class=\"REQUEST_COMMON_DOWNLOAD_CA\">\n" +
                "  <DSPTBM>12110302</DSPTBM>\n" +
                "  <DSPTZCM>51807674</DSPTZCM>\n" +
                "  <NSRSBH>510107698868804</NSRSBH>\n" +
                "  <NSRSQM>1234567890Ko1ztjNt4xGg76Tqxl4rvg==</NSRSQM>\n" +
                "  </REQUEST_COMMON_DOWNLOAD_CA>";
    	String publickey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALC3pUMkQMOY/f3TrV0bKXmkwQ+kpl+e1BmIYtbXjTgzdo41LFoKJ+NctodY+wa143aSttbg5n5BVA7X9zQYCR8CAwEAAQ==";
    	String privatekey="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAsLelQyRAw5j9/dOtXRspeaTBD6SmX57UGYhi1teNODN2jjUsWgon41y2h1j7BrXjdpK21uDmfkFUDtf3NBgJHwIDAQABAkAwll2e50HTRaZX+rJIziYKJw1Z4rhHxCCm4IPkdCGE2NLe4Y+qCxElc9OXhJQLsUiug33+umz1a8nyl093XuaxAiEA+27Lqjpfjunf8zZNOEb2xcm4WfleeAGaRYaCz39ictkCIQCz7Wko2eJi+s8bfvYWMsq/DtZ2dqvvcrYKR3kVmKFwtwIhAJ5zi2wiz1fMMbMfNHjT1+57mm8xRQSR1fwcsdE8MPm5AiEAn0xXaVKWq3lQ+hALAhZQCJ2zZMimauYNo8YpvuQWMrsCIGGjodc6NIcyT3ZxJJ7f4sLp5848lKH2YtR02qREEv3W";
        String signstr=sign(content,getPrivateKey(privatekey));  
        System.out.println("签名原串："+content);  
        System.out.println("签名串："+signstr);  
        System.out.println();  
          
        System.out.println("---------------公钥校验签名------------------");  
        System.out.println("签名原串："+content);  
        System.out.println("签名串："+signstr);  
          
        System.out.println("验签结果："+verify(content,getPublickey(publickey),signstr));  
        System.out.println();  
    }
    public static String getSignatureContent(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }
    public static Map<String, String> getSignMap(GlobalInfo globalInfo) {
		// TODO Auto-generated method stub
    	Map<String,String> signmap =new HashMap<String, String>();
    	signmap.put("terminalCode", globalInfo.getTerminalCode());
    	signmap.put("appId", globalInfo.getAppId());
    	signmap.put("version", globalInfo.getVersion());
    	signmap.put("interfaceCode", globalInfo.getInterfaceCode());
    	signmap.put("requestTime", globalInfo.getRequestTime());
    	signmap.put("responseCode", globalInfo.getResponseCode());
    	signmap.put("dataExchangeId", globalInfo.getDataExchangeId());
    	signmap.put("userName", globalInfo.getUserName());
    	signmap.put("passWord", globalInfo.getPassWord());
    	signmap.put("taxpayerId", globalInfo.getTaxpayerId());
    	signmap.put("authorizationCode", globalInfo.getAuthorizationCode());
		return signmap;
	}
}
