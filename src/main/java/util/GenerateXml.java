package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.CaConstant;
import com.aisino.PKCS7;

import sajt.dzfppt.service.impl.EntranceImpl;

public class GenerateXml {

	/**
	 * 规范解析
	 * 
	 * @param srcFileName
	 * @param password
	 * @return
	 */
	public static String shiftWith3DES(String srcFileName, String password) {

		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			// content行标志
			boolean contentFlag = false;
			// 暂时停止写入标志
			boolean stopFlag = false;
			// 输入流
			br = new BufferedReader(new FileReader(new File(srcFileName)));

			// 输出流
			pw = new PrintWriter(new File(getName(srcFileName)));

			// 接收行变量
			String msg = null;
			// 接收内容变量
			String content = "";
			String cryptograph = "";
			// 获取content内容
			while ((msg = br.readLine()) != null) {

				if (msg.contains("<!--")) {
					continue;
				}

				if (!stopFlag) {
					pw.append(msg + "\n");
				}

				// 获取content内容控制
				if (msg.contains("</content>")) {
					contentFlag = false;
					// cryptograph = Base64Util.getCryprtograph(content);
					System.out.println(content);
					cryptograph = EncryptionMsg.TripleDES_Base64_Encrypt(password, content);
					stopFlag = false;
					// 追加入加密内容
					pw.append("\t\t\t" + cryptograph + "\n");
					pw.append("\t\t" + "</content>" + "\n");
					// 解密
					// System.out.println(Base64Util.cryptographToPlaintext(cryptograph));
				}
				if (contentFlag) {
					content += msg;
				}
				if (msg.contains("<content>")) {
					contentFlag = true;
					stopFlag = true;
				}

			}
			// System.out.println("content = " + content);

			System.out.println("密文" + cryptograph);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		return getName(srcFileName);
	}

	/**
	 * test -- 明文模式
	 */
	public static String plaintPattern(String context) {
		try {
			String head = "";
			String content = "";
			String tail = "";
			String password = "";
			Boolean zipFlag = false;
			Boolean threeDesFlag = false;
			Boolean caFlag = false;

			if (context.contains("<zipCode>1</zipCode>")) {
				zipFlag = true;
			}
			if (context.contains("<encryptCode>1</encryptCode>")
					&& context.toUpperCase().contains("<codeType>3DES</codeType>".toUpperCase())) {
				threeDesFlag = true;
				password = context.substring(context.indexOf("</passWord>") - 24, context.indexOf("</passWord>"));
			}
			if (context.contains("<encryptCode>2</encryptCode>")
					&& context.toUpperCase().contains("<codeType>CA</codeType>".toUpperCase())) {
				caFlag = true;
			}
			if (context.contains("<content>") && context.contains("</content>")) {
				head = context.substring(0, context.indexOf("<content>") + "<content>".length());
				content = context.substring(context.indexOf("<content>") + "<content>".length(),
						context.indexOf("</content>"));
				tail = context.substring(context.indexOf("</content>"));
			} else if (context.replace(" ", "").contains("<content/>")) {
				content = context.replace(" ", "");
				head = context.substring(0, context.indexOf("<content/>"));
				tail = context.substring(context.indexOf("<content/>") + "<content/>".length());
			}

			String result = "";
			if (zipFlag && threeDesFlag) {
				result = head + EncryptionMsg.TripleDES_Base64_Encrypt(password, content) + tail;
			} else if (zipFlag) {
				result = head + EncryptionMsg.Gzip_Base64_Enycrypt(content) + tail;
			} else if (threeDesFlag) {
				result = head + EncryptionMsg.TripleDES_Base64_Encrypt(password, content) + tail;
			} else if (caFlag) {
				final String trustsBytes = (CaConstant.getPath() + CaConstant.getProperty("PUBLIC_TRUSTS"))
						.substring(1);
				String decryptPFXBytes = (CaConstant.getPath() + CaConstant.getProperty("CLIENT_DECRYPTPFX"))
						.substring(1);
				String decryptPFXKey = CaConstant.getProperty("CLIENT_DECRYPTPFX_KEY");
				final PKCS7 pkcs7Client = new PKCS7(FileUtils.readFileToByteArray(new File(trustsBytes)),
						FileUtils.readFileToByteArray(new File(decryptPFXBytes)), decryptPFXKey);
				final byte[] encodeData = pkcs7Client.pkcs7Encrypt(content, FileUtils.readFileToByteArray(
						new File(CaConstant.getPath() + CaConstant.getProperty("PLATFORM_DECRYPTCER"))));
				content = new String(Base64.encodeBase64(encodeData));
				result = head + content + tail;
			} else {
				result = head + EncryptionMsg.Base64_Encrypt(content) + tail;
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("解析异常..");
		}
		return null;
	}

	/**
	 * 通用解析
	 * 
	 * @param srcFileName
	 * @return
	 */
	public static String shift_plus(String srcFileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(srcFileName)));
			String msg = null;
			String context = "";
			while ((msg = br.readLine()) != null) {
				context += msg;
			}
			String head = "";
			String content = "";
			String tail = "";
			String password = "";
			Boolean zipFlag = false;
			Boolean threeDesFlag = false;
			Boolean caFlag = false;

			if (context.contains("<zipCode>1</zipCode>")) {
				zipFlag = true;
			}
			if (context.contains("<encryptCode>1</encryptCode>")
					&& context.toUpperCase().contains("<codeType>3DES</codeType>".toUpperCase())) {
				threeDesFlag = true;
				password = context.substring(context.indexOf("</passWord>") - 24, context.indexOf("</passWord>"));
			}
			if (context.contains("<encryptCode>2</encryptCode>")
					&& context.toUpperCase().contains("<codeType>CA</codeType>".toUpperCase())) {
				caFlag = true;
			}
			if (context.contains("<content>") && context.contains("</content>")) {
				head = context.substring(0, context.indexOf("<content>") + "<content>".length());
				content = context.substring(context.indexOf("<content>") + "<content>".length(),
						context.indexOf("</content>"));
				tail = context.substring(context.indexOf("</content>"));
			} else if (context.replace(" ", "").contains("<content/>")) {
				content = context.replace(" ", "");
				head = context.substring(0, context.indexOf("<content/>"));
				tail = context.substring(context.indexOf("<content/>") + "<content/>".length());
			}

			String result = "";
			if (zipFlag && threeDesFlag) {
				result = head + EncryptionMsg.TripleDES_Base64_Encrypt(password, content) + tail;
			} else if (zipFlag) {
				result = head + EncryptionMsg.Gzip_Base64_Enycrypt(content) + tail;
			} else if (threeDesFlag) {
				result = head + EncryptionMsg.TripleDES_Base64_Encrypt(password, content) + tail;
			} else if (caFlag) {
				final String trustsBytes = (CaConstant.getPath() + CaConstant.getProperty("PUBLIC_TRUSTS"))
						.substring(1);
				String decryptPFXBytes = (CaConstant.getPath() + CaConstant.getProperty("CLIENT_DECRYPTPFX"))
						.substring(1);
				String decryptPFXKey = CaConstant.getProperty("CLIENT_DECRYPTPFX_KEY");
				final PKCS7 pkcs7Client = new PKCS7(FileUtils.readFileToByteArray(new File(trustsBytes)),
						FileUtils.readFileToByteArray(new File(decryptPFXBytes)), decryptPFXKey);
				final byte[] encodeData = pkcs7Client.pkcs7Encrypt(content, FileUtils.readFileToByteArray(
						new File(CaConstant.getPath() + CaConstant.getProperty("PLATFORM_DECRYPTCER"))));
				content = new String(Base64.encodeBase64(encodeData));
				result = head + content + tail;
			} else {
				result = head + EncryptionMsg.Base64_Encrypt(content) + tail;
			}
			IOUtil.redirectInfo(result, getName(srcFileName));
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("解析异常..");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static String getName(String name) {
		if (name != null) {
			return "cryptograph" + name.substring(name.indexOf("_"));
		}
		return "default.xml";
	}

	public static void main(String[] args) {
		// GenerateXml.shift("plaintext_fpkj.xml");
		String s = GenerateXml.shift_plus("pt_fpkj3.xml");
		System.out.println(s);
		System.out.println("ada".contains("A"));
	}

}
