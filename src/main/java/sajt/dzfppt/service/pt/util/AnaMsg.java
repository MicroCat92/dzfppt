package sajt.dzfppt.service.pt.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;

public class AnaMsg {

	public static final String CHARSET = "utf-8";

	/**
	 * @param msg
	 */
	public static void PDF_Ana(String msg) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			String pdffile = "";
			BASE64Decoder decoder = new BASE64Decoder();
			// String pdfcon=new String(decoder.decodeBuffer(pdffile));
			File ret = new File("pdf_Ana.pdf");
			FileOutputStream fstream = new FileOutputStream(ret);
			BufferedOutputStream stream = new BufferedOutputStream(fstream);
			stream.write(decoder.decodeBuffer(msg));
			out.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * base64,gzip,3des ���Ľ���
	 * 
	 * @param password
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static String TripleDES_Gzip_Base64_Ana(String password, String msg) throws Exception {
		return new String(
				TripleDESUtil.decryptMode(password, GZipUtils.decompress(new Base64().decode((msg.getBytes(CHARSET))))), CHARSET);
	}

	/**
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String Base64_Ana(String msg) throws UnsupportedEncodingException {
		return new String(new Base64().decode((msg.getBytes(CHARSET))), CHARSET);
	}

	/**
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static String Gzip_Base64_Ana(String msg) throws Exception {
		return new String(GZipUtils.decompress(new Base64().decode((msg.getBytes(CHARSET)))), CHARSET);
	}

	/**
	 * @param password
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String TripleDES_Base64_Ana(String password, String msg) throws UnsupportedEncodingException {
		return new String(TripleDESUtil.decryptMode(password, new Base64().decode((msg.getBytes(CHARSET)))), CHARSET);
	}

}
