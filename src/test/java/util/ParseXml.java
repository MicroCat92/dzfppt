package util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import sajt.dzfppt.dao.DsptxxDao;
import sajt.dzfppt.entity.Dsptxx;
import sajt.dzfppt.service.pt.bean.Data;
import sajt.dzfppt.service.pt.bean.GlobalInfo;
import sajt.dzfppt.service.pt.bean.ReturnStateInfo;
import sajt.dzfppt.service.pt.cl.ProXmlPT;
import sajt.dzfppt.service.pt.util.AnaMsg;
import sajt.dzfppt.service.pt.util.DesException;
import sajt.dzfppt.service.pt.util.GZipUtils;
import sajt.dzfppt.service.pt.util.PassWordCreate;
import sajt.dzfppt.service.pt.util.TripleDESUtil;
import sajt.dzfppt.service.pt.util.ValidateUtil;
import sajt.dzfppt.service.pt.util.XmlPar;

public class ParseXml {
	
	final public static String CHARSET = "UTF-8";
	private final static Logger log = Logger.getLogger(ParseXml.class);
	
	/**
	 * 外层报文解析工具方法
	 * 
	 * @param e
	 * @return
	 */
	private static String labelProcess(Element e) {
		return e.getText() == null ? "" : e.getText().trim();
	}

	// 外层报文处理
	public static Map<String, Object> parseOuterMessage(String xml, DsptxxDao dsptxxDao) throws Exception {
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();

			Document doc = new SAXBuilder().build(new InputSource(new StringReader(xml)));
			Element root = doc.getRootElement();
			List<Element> node = root.getChildren();

			if (node == null || node.size() <= 0) {
				return map;
			}

			GlobalInfo globalInfo = new GlobalInfo();
			for (int i = 0; i < node.size(); i++) {
				Element e1 = node.get(i);
				String labelName = e1.getName();
				if (XmlPar.GLOBALINFO.equals(labelName)) {// globalInfo 解析

					globalInfo.setTerminalCode(labelProcess(e1.getChild(XmlPar.TERMINALCODE)));
					globalInfo.setAppId(labelProcess(e1.getChild(XmlPar.APPID)));
					globalInfo.setVersion(labelProcess(e1.getChild(XmlPar.VERSION)));
					globalInfo.setInterfaceCode(labelProcess(e1.getChild(XmlPar.INTERFACECODE)));
					globalInfo.setUserName(labelProcess(e1.getChild(XmlPar.USERNAME)));
					globalInfo.setPassWord(labelProcess(e1.getChild(XmlPar.PASSWORD)));
					globalInfo.setTaxpayerId(labelProcess(e1.getChild(XmlPar.TAXPAYERID)));
					globalInfo.setAuthorizationCode(labelProcess(e1.getChild(XmlPar.AUTHORIZATIONCODE)));
					globalInfo.setRequestCode(labelProcess(e1.getChild(XmlPar.REQUESTCODE)));
					globalInfo.setRequestTime(labelProcess(e1.getChild(XmlPar.REQUESTTIME)));
					globalInfo.setResponseCode(labelProcess(e1.getChild(XmlPar.RESPONSECODE)));
					globalInfo.setDataExchangeId(labelProcess(e1.getChild(XmlPar.DATAEXCHANGEID)));

					map.put(labelName, globalInfo);
				} else if (XmlPar.RETURNSTATEINFO.equals(labelName)) {// returnStateInfo
																		// 解析
					ReturnStateInfo returneStateInfo = new ReturnStateInfo();

					returneStateInfo.setReturnCode(labelProcess(e1.getChild(XmlPar.RETURNCODE)));
					returneStateInfo
							.setReturnMessage(AnaMsg.Base64_Ana(labelProcess(e1.getChild(XmlPar.RETURNMESSAGE))));

					map.put(labelName, returneStateInfo);
				} else if (XmlPar.DATA.equals(labelName)) {
					Data data = new Data();

					data.setDataDescription(labelProcess(e1.getChild(XmlPar.DATADESCRIPTION)));
					Element eSon = e1.getChild(XmlPar.DATADESCRIPTION);
					data.setZipCode(labelProcess(eSon.getChild(XmlPar.ZIPCODE)));
					data.setEncryptCode(labelProcess(eSon.getChild(XmlPar.ENCRYPTCODE)));
					data.setCodeType(labelProcess(eSon.getChild(XmlPar.CODETYPE)));

					// 打印
					log.debug(labelProcess(e1.getChild(XmlPar.CONTENT)));

					map.put(XmlPar.ENCRYPTIONCON, labelProcess(e1.getChild(XmlPar.CONTENT)));

					String content = decodeData(labelProcess(e1.getChild(XmlPar.CONTENT)), data.getZipCode(),
							data.getEncryptCode(), globalInfo.getPassWord(), globalInfo.getUserName(), dsptxxDao);
					data.setContent(content);

					map.put(labelName, data);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("外层报文解析异常 : " + e.getMessage());
			throw e;
		}
		return map;
	}
	
	// content 解码
		private static String decodeData(String org, String zipCode, String encryptCode, String password, String dsptbm,
				DsptxxDao dsptxxDao) throws Exception {
			byte[] temp_coutnent;
			if (org != null && !org.equals("")) {
				// 管理端使用
				temp_coutnent = org.getBytes(CHARSET);
				temp_coutnent = new Base64().decode(temp_coutnent);
				// 处理端使用
				// temp_coutnent = decode(org.getBytes());
				if ("1".equals(zipCode)) {
					temp_coutnent = GZipUtils.decompress(temp_coutnent);
				}
				if ("1".equals(encryptCode)) {
					// String zcmsql="select * from tbl_dsptxx where DSPTBM=?";
					// Record zcmrd=Db.findFirst(zcmsql,dsptbm);
					// String ZCM=zcmrd.getStr("ZCM");
					Dsptxx dsptxx = dsptxxDao.selectByDsptbm(dsptbm);
					String ZCM = dsptxx.getZcm();
					if (ValidateUtil.checkParmaterIsEmpty(password)) {
						// password=zcmrd.getStr("3DES_KEY");
						password = dsptxx.get3desKey();
						temp_coutnent = TripleDESUtil.decryptMode(password, temp_coutnent);
					} else {
						String valpd = PassWordCreate.passWordCreate(ZCM, password.substring(0, 10));
						if (!password.equals(valpd)) {
							throw new DesException("加密方式为3DES加密，密码不正确");
						}
						temp_coutnent = TripleDESUtil.decryptMode(password.substring(10, password.length()), temp_coutnent);
					}
				} else if ("2".equals(encryptCode)) {
					// Date start = new Date();
					//// String zspassword =
					// SajtSvrImpl.appConfig.props.get("EC_OWN_KEY_PWD");
					//// String filePath =
					// SajtSvrImpl.appConfig.props.get("EC_OWN_KEY");
					//// String derPath =
					// URLDecoder.decode(SajtSvrImpl.appConfig.props.get("EC_PUBLIC_KEY"));
					//// QYCaCryptUtil qyutil = new
					// QYCaCryptUtil(FileUtils.readFileToByteArray(new
					// File(derPath)), zspassword, filePath);
					//// temp_coutnent = qyutil.decrypt(temp_coutnent);
					//
					// // 平台端解密过程 :平台端私钥(pfx)、pwd
					// final String trustsBytes =
					// SajtSvrImpl.appConfig.props.get("PUBLIC_TRUSTS");
					// String decryptPFXBytes =
					// SajtSvrImpl.appConfig.props.get("EC_PT_PFX");
					// String decryptPFXKey =
					// SajtSvrImpl.appConfig.props.get("EC_PT_PFX_PWD");
					// final PKCS7 pkcs7Platform = new
					// PKCS7(FileUtils.readFileToByteArray(new File(trustsBytes)),
					// FileUtils.readFileToByteArray(new File(decryptPFXBytes)),
					// decryptPFXKey);
					// temp_coutnent = pkcs7Platform.pkcs7Decrypt(new
					// sun.misc.BASE64Decoder().decodeBuffer(org));
					//
					// Date end = new Date();
					// log.info((new StringBuilder("encodeData use time
					// ---------~~~~~~~~~~~~~~~~~~~~~--------->")).append(end.getTime()
					// - start.getTime()).toString());
				} else if ("0".equals(encryptCode)) {

					// throw new Exception("内层报文必须加密。");
				} else {
					throw new Exception("内层报文的加密方式不对。");
				}
				String content = new String(temp_coutnent, CHARSET);

				return content;

			} else {
				return "";
			}
		}

}
