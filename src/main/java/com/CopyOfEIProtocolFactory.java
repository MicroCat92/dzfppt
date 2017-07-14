package com;

import static com.StaticConstant.CHARSET;
import static com.google.common.base.Strings.nullToEmpty;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.aisino.PKCS7;

public final class CopyOfEIProtocolFactory {
    private final static Logger LOGGER = Logger.getLogger(CopyOfEIProtocolFactory.class);

    /**
     * BASE64加密
     */
    private static byte[] encode(byte[] res) {
        try {
            return Base64.encodeBase64(res);
        } catch (Exception e) {
            LOGGER.error("未知：" + e);
            return null;
        }
    }

    /**
     * base64解码
     */
    private static byte[] decode(byte[] str) {
        return Base64.decodeBase64(str);
    }

    private static String decodeData(String org, String zipCode, String encryptCode, String password, final EShopCertificateBytesInfo certificate) throws Exception {
        byte[] tempContent;
            tempContent = org.getBytes(CHARSET);
            tempContent = decode(tempContent);
                final PKCS7 pkcs7 = new PKCS7(certificate.getTrustsBytes(), certificate.getPrivatePFXBytes(), certificate.getPrivatePFXKey());
                tempContent = pkcs7.pkcs7Decrypt(tempContent);
            return new String(tempContent, CHARSET);
    }

   
    private static String encodeData(String org, String zipCode, String encryptCode, String password, final EShopCertificateBytesInfo certificate) throws UnsupportedEncodingException {
		byte[] temp_content = nullToEmpty(org).getBytes(CHARSET);
		try {
			final PKCS7 pkcs7 = new PKCS7(certificate.getTrustsBytes(),
					certificate.getPrivatePFXBytes(),
					certificate.getPrivatePFXKey());
			temp_content = pkcs7.pkcs7Encrypt(org,
					certificate.getPublicPFXBytes());

			return new String(encode(temp_content), CHARSET);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UnsupportedEncodingException未知：",
					e.fillInStackTrace());
		} catch (Exception e) {
			LOGGER.error("Exception未知：", e.fillInStackTrace());
		}
		return "";
    }

}
