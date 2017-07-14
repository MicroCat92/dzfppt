package sajt.dzfppt.service.pt.cl;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

import sajt.dzfppt.service.pt.bean.Data;
import sajt.dzfppt.service.pt.bean.DzfpInterfaceBean;
import sajt.dzfppt.service.pt.bean.GlobalInfo;
import sajt.dzfppt.service.pt.bean.ReturnStateInfo;
import sajt.dzfppt.service.pt.util.GZipUtils;
import sajt.dzfppt.service.pt.util.PassWordCreate;
import sajt.dzfppt.service.pt.util.TripleDESUtil;
import sajt.dzfppt.service.pt.util.ValidateUtil;
import sajt.dzfppt.service.pt.util.XMLShellFactory;
import sajt.dzfppt.service.pt.util.XmlPar;


/**
 * <p>
 * 处理xml数据，转码，打zip包,生成xml
 * </p>
 */
public class ProXml {
	public static String CHARSET = "UTF-8";
	private final static Logger  log = Logger.getLogger(ProXml.class);
	/**
	 * 
	 * <p>
	 * 转码
	 * </p>
	 * @param string
	 * @return String
	 */
	public static String encode(String res) throws Exception{
		Base64 base = new Base64();
		return new String(base.encode(res.getBytes(CHARSET)));
	}
	
	public static byte[] encode(byte[] res) throws Exception{
		Base64 base = new Base64();
		return base.encode(res);
	}

	/**
	 * 
	 * <p>
	 * 解码
	 * </p>
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 *             byte[]
	 * @throws DecoderException 
	 */
	public static String decode(String str) throws UnsupportedEncodingException, DecoderException {
		return new String(new Base64().decode(str.getBytes()),CHARSET);
	}
	
	public static byte[] decodeByte(byte[] str) throws DecoderException {
		return new Base64().decode(str);
	}
	
	/**
	 * 
	 * <p>
	 * 解码
	 * </p>
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 *             byte[]
	 * @throws DecoderException 
	 */
	public static byte[] decode(byte[] str) throws UnsupportedEncodingException, DecoderException {
		return new Base64().decode(str);
	}
	
	/**
	 * 
	 * <p>
	 * 传输数据与固定值比较大小
	 * </p>
	 * 
	 * @param byteRsp
	 * @param size
	 * @return boolean
	 */
	public static String isZip(String xml, int size) {
		if(xml == null){
			xml = "";
		}
		String isz = "0";
		if (xml.getBytes().length > 1024 * size){
			isz = "1";
		}
		return isz;
	}

	/**
	 * 
	 * <p>
	 * 根据原始生成传输数据 
	 * </p>
	 * 
	 * @param data
	 * @param globalInfo
	 * @param responseStateInfo
	 * @param content
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */

	public static String getXml(GlobalInfo globalInfo,ReturnStateInfo returnStateInfo, Data data) throws Exception{
		return getXml(globalInfo, returnStateInfo, data, true);
	}
	/**
	 * 业务数据已加密，只封装外层xml
	 * @param globalInfo
	 * @param returnStateInfo
	 * @param data
	 * @return
	 */
	public static String getXmlOuter(GlobalInfo globalInfo,ReturnStateInfo returnStateInfo, Data data) throws Exception{
		return getXml(globalInfo, returnStateInfo, data, false);
	}
	
	
	private static String getXml(GlobalInfo globalInfo,ReturnStateInfo returnStateInfo, Data data,boolean flag) throws Exception{
		String resData = "";
		Element root = new Element(XmlPar.ROOT_BASE);
		Namespace ns = Namespace.getNamespace(XmlPar.NS_ONE);
		root.addNamespaceDeclaration(ns);
		Namespace ns1 = Namespace.getNamespace("xsi", XmlPar.NS_TWO);
		root.addNamespaceDeclaration(ns1);
		Namespace ns2 = Namespace.getNamespace("schemaLocation",XmlPar.NS_THREE);
		root.addNamespaceDeclaration(ns2);
		Document doc = new Document(root);
		Element eRoot = doc.getRootElement();
		eRoot.setAttribute("version", XmlPar.VERSION_NO);
	
		// 全局信息
		Element eGlobalInfo = new Element(XmlPar.GLOBALINFO);
		Element eterminalCode=new Element(XmlPar.TERMINALCODE);
		eterminalCode.setText(globalInfo.getTerminalCode());
		eGlobalInfo.addContent(eterminalCode);
		
		Element eAppID = new Element(XmlPar.APPID);
		eAppID.setText(globalInfo.getAppId());
		eGlobalInfo.addContent(eAppID);
		
		Element eversion=new Element(XmlPar.VERSION);
		eversion.setText(globalInfo.getVersion());
		eGlobalInfo.addContent(eversion);
		
		Element eInterfaceCode;
		eInterfaceCode = new Element(XmlPar.INTERFACECODE);
		eInterfaceCode.setText(globalInfo.getInterfaceCode());
		eGlobalInfo.addContent(eInterfaceCode);
		
		Element ERequestCode = new Element(XmlPar.REQUESTCODE);
		ERequestCode.setText(globalInfo.getRequestCode());
		eGlobalInfo.addContent(ERequestCode);
		
		Element eRequestTime = new Element(XmlPar.REQUESTTIME);
		eRequestTime.setText(globalInfo.getRequestTime());
		eGlobalInfo.addContent(eRequestTime);
		
		Element eResponseCode;
		eResponseCode = new Element(XmlPar.RESPONSECODE);
		eResponseCode.setText(globalInfo.getResponseCode());
		eGlobalInfo.addContent(eResponseCode);
		
		Element eDataExchangeId = new Element(XmlPar.DATAEXCHANGEID);
		eDataExchangeId.setText(globalInfo.getDataExchangeId());
		eGlobalInfo.addContent(eDataExchangeId);
		
		Element eUserName = new Element(XmlPar.USERNAME);
		eUserName.setText(globalInfo.getUserName());
		eGlobalInfo.addContent(eUserName);
		
		Element ePassWord = new Element(XmlPar.PASSWORD);
		ePassWord.setText(globalInfo.getPassWord());
		eGlobalInfo.addContent(ePassWord);
		
		Element etaxpayerId=new Element(XmlPar.TAXPAYERID);
		etaxpayerId.setText(globalInfo.getTaxpayerId());
		eGlobalInfo.addContent(etaxpayerId);
		
		Element eauthorizationCode=new Element(XmlPar.AUTHORIZATIONCODE);
		eauthorizationCode.setText(globalInfo.getAuthorizationCode());
		eGlobalInfo.addContent(eauthorizationCode);
		// 返回信息
		Element eReturnStateInfo = new Element(XmlPar.RETURNSTATEINFO);
		Element eReturnCode = new Element(XmlPar.RETURNCODE);
		eReturnCode.setText(returnStateInfo.getReturnCode());
		eReturnStateInfo.addContent(eReturnCode);
		Element eReturnMessage = new Element(XmlPar.RETURNMESSAGE);
		if (returnStateInfo.getReturnMessage() != null
				&& !returnStateInfo.getReturnMessage().equals("null")
				&& !returnStateInfo.getReturnMessage().equals("")) {
			eReturnMessage.setText(encode(returnStateInfo.getReturnMessage()));
		} else {
			eReturnMessage.setText("");
		}
		eReturnStateInfo.addContent(eReturnMessage);
		// 交换数据
		Element eData = new Element(XmlPar.DATA);
		Element eDataDescription;
		eDataDescription = new Element(XmlPar.DATADESCRIPTION);
		Element eZipCode = new Element(XmlPar.ZIPCODE);
		String iszip="";
		if(data!=null&&data.getZipCode()!=null){
			iszip=data.getZipCode();
		}else{
			iszip=isZip(data.getContent(), 10);
		}
		eZipCode.setText(iszip);
		eDataDescription.addContent(eZipCode);
		Element eEncryptCode = new Element(XmlPar.ENCRYPTCODE);
		eEncryptCode.setText(data.getEncryptCode());
		eDataDescription.addContent(eEncryptCode);
		Element eCodeType = new Element(XmlPar.CODETYPE);
		eCodeType.setText(data.getCodeType());
		eDataDescription.addContent(eCodeType);
		Element eContent = new Element(XmlPar.CONTENT);
		String content="";
		if(flag){
		    content=encodeData(data.getContent(), iszip, data.getEncryptCode(), globalInfo.getPassWord(),globalInfo.getUserName());
//			Test3DES test3des=new Test3DES();
//			byte[] jiamicon=data.getContent().getBytes("UTF-8");
//	        byte[] key="bDo4Vs6uBMpJfjwVUdCiSwyy".getBytes("UTF-8");
//	        byte[] jiamibyte=test3des.des3EncodeECB(key,jiamicon);
//	    	content=new sun.misc.BASE64Encoder().encode(jiamibyte);
			eContent.setText(content);
		}else{
			eContent.setText(data.getData());
		}
		eData.addContent(eDataDescription);
		eData.addContent(eContent);
		root.addContent(eGlobalInfo);
		root.addContent(eReturnStateInfo);
		root.addContent(eData);
		ByteArrayOutputStream byteRsp = null;
		
		
		Format format = Format.getCompactFormat();
		format.setEncoding(CHARSET);
		format.setIndent(" ");
		XMLOutputter xmlout = new XMLOutputter(format);
		byteRsp = new ByteArrayOutputStream();
		xmlout.output(doc, byteRsp);
		resData = byteRsp.toString(CHARSET);
		if(!ValidateUtil.checkParmaterIsEmpty(content)){
			int conbg=resData.indexOf("<content>")+9;
			int conend=resData.indexOf("</content>");
			String con=resData.substring(conbg,conend);
			resData=resData.replace(con,content);
		}
		return resData;
	}

	/**
	 * 取得全部明文信息
	 * @return Map
	 */
	
	public static Map<String,Object> getInterface(String requestMessage) throws Exception{
		return getInterface(requestMessage, true);
	}
	/**
	 * 只解密第一层协议
	 */
	public static Map<String,Object> getInterfaceOuter(String requestMessage) throws Exception{
		return getInterface(requestMessage, false);
	}
	
	public static Map<String,Object> getInterface(String requestMessage,boolean flag) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		GlobalInfo globalInfo = new GlobalInfo();
		StringReader read = new StringReader(requestMessage);
		InputSource source = new InputSource(read);
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(source);
		Element root = doc.getRootElement();
		List<?> node = root.getChildren();
		if (node != null && node.size() > 0) {
			for (int i = 0; i < node.size(); i++) {
				Element e1 = (Element) node.get(i);
				if (e1.getName().equals(XmlPar.GLOBALINFO)) {
					globalInfo.setTerminalCode(e1.getChild(XmlPar.TERMINALCODE).getText()==null?"":e1.getChild(XmlPar.TERMINALCODE).getText());
					globalInfo.setAppId(e1.getChild(XmlPar.APPID).getText()==null?"":e1.getChild(XmlPar.APPID).getText());
					globalInfo.setVersion(e1.getChild(XmlPar.VERSION).getText()==null?"":e1.getChild(XmlPar.VERSION).getText());
					globalInfo.setInterfaceCode(e1.getChild(XmlPar.INTERFACECODE).getText()==null?"":e1.getChild(XmlPar.INTERFACECODE).getText());
					globalInfo.setUserName(e1.getChild(XmlPar.USERNAME).getText()==null?"":e1.getChild(XmlPar.USERNAME).getText());
					globalInfo.setPassWord(e1.getChild(XmlPar.PASSWORD).getText()==null?"":e1.getChild(XmlPar.PASSWORD).getText());
					globalInfo.setTaxpayerId(e1.getChild(XmlPar.TAXPAYERID).getText()==null?"":e1.getChild(XmlPar.TAXPAYERID).getText());
					globalInfo.setAuthorizationCode(e1.getChild(XmlPar.AUTHORIZATIONCODE).getText()==null?"":e1.getChild(XmlPar.AUTHORIZATIONCODE).getText());
					globalInfo.setRequestCode(e1.getChild(XmlPar.REQUESTCODE).getText()==null?"":e1.getChild(XmlPar.REQUESTCODE).getText());
					globalInfo.setRequestTime(e1.getChild(XmlPar.REQUESTTIME).getText()==null?"":e1.getChild(XmlPar.REQUESTTIME).getText());
					globalInfo.setResponseCode(e1.getChild(XmlPar.RESPONSECODE).getText()==null?"":e1.getChild(XmlPar.RESPONSECODE).getText());
					globalInfo.setDataExchangeId(e1.getChild(XmlPar.DATAEXCHANGEID).getText()==null?"":e1.getChild(XmlPar.DATAEXCHANGEID).getText());
					map.put(e1.getName(), globalInfo);
				}
				if (e1.getName().equals(XmlPar.RETURNSTATEINFO)) {
					ReturnStateInfo returneStateInfo = new ReturnStateInfo();
					returneStateInfo.setReturnCode(e1.getChild(XmlPar.RETURNCODE).getText()==null?"":e1.getChild(XmlPar.RETURNCODE).getText());
					if (e1.getChild(XmlPar.RETURNMESSAGE).getText() != null&& !e1.getChild(XmlPar.RETURNMESSAGE).getText().equals("")) {
						returneStateInfo.setReturnMessage(decode(e1.getChild(XmlPar.RETURNMESSAGE).getText())==null?"":e1.getChild(XmlPar.RETURNMESSAGE).getText());
					} else {
						returneStateInfo.setReturnMessage("");
					}
					map.put(e1.getName(), returneStateInfo);
				}
				if (e1.getName().equals(XmlPar.DATA)) {
					Data data = new Data();
					data.setDataDescription(e1.getChild(XmlPar.DATADESCRIPTION).getText()==null?"":e1.getChild(XmlPar.DATADESCRIPTION).getText());
					data.setZipCode(e1.getChild(XmlPar.DATADESCRIPTION).getChild(XmlPar.ZIPCODE).getText()==null?"":e1.getChild(XmlPar.DATADESCRIPTION).getChild(XmlPar.ZIPCODE).getText());
					data.setEncryptCode(e1.getChild(XmlPar.DATADESCRIPTION).getChild(XmlPar.ENCRYPTCODE).getText()==null?"":e1.getChild(XmlPar.DATADESCRIPTION).getChild(XmlPar.ENCRYPTCODE).getText());
					data.setCodeType(e1.getChild(XmlPar.DATADESCRIPTION).getChild(XmlPar.CODETYPE).getText()==null?"":e1.getChild(XmlPar.DATADESCRIPTION).getChild(XmlPar.CODETYPE).getText());
					log.debug(e1.getChild(XmlPar.CONTENT).getText());
					if(flag){
						String content=decodeData(e1.getChild(XmlPar.CONTENT).getText(), data.getZipCode(), data.getEncryptCode(), globalInfo.getPassWord(),globalInfo.getUserName());
						data.setContent(content);
					}else{
						data.setData(e1.getChild(XmlPar.CONTENT).getText());
					}
//						if(log.isDebugEnabled())
//						    log.debug(data.getContent()+"+=============请求数据");
					map.put(e1.getName(), data);
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * <p>
	 * 取得系统当前格式化时间
	 * </p>
	 * 
	 * @param args
	 *            void
	 */
	public static String getCurDate(String formatStyle) {
		DateFormat format1 = new SimpleDateFormat(formatStyle);
		return format1.format(new Date());
	}
	
	public static String getFormatDate(Date date,String formatStyle) {
		DateFormat format1 = new SimpleDateFormat(formatStyle);
		return format1.format(date);
	}
	
	public static String decodeData(String org,String zipCode,String encryptCode,String password,String dsptbm) throws Exception{
		byte[] temp_coutnent;
			if (org != null && !org.equals("")) {
//TODO
				//管理端使用
				temp_coutnent = org.getBytes(CHARSET);
				temp_coutnent = decode(temp_coutnent);
				//处理端使用
//				temp_coutnent = decode(org.getBytes());
				if ("1".equals(zipCode)) {
					temp_coutnent = GZipUtils.decompress(temp_coutnent);
				}
				if("1".equals(encryptCode)){
					temp_coutnent = TripleDESUtil.decryptMode(password.substring(10, password.length()), temp_coutnent);
				}else if("2".equals(encryptCode)){
//					if(StaticConstant.CA_STATE.equals("1")){
//						byte[] qyDer=(byte[]) StaticConstant.caPublicKeyMap.get(dsptbm);
//						if(qyDer != null){
//							SJCaCryptUtil sjutil = new SJCaCryptUtil(qyDer);
//						temp_coutnent=sjutil.decrypt(temp_coutnent);
//						}
//					}else if(StaticConstant.CA_STATE.equals("2")){
//						String zspassword=(String) StaticConstant.caPublicKeypasswordMap.get(dsptbm);
//						String filePath=(String) StaticConstant.caPublicKeyMap.get(dsptbm);
//						Properties properties = new Properties();
//						properties.load(StaticConstant.class.getResourceAsStream("/protocol.properties"));
//						String derPath=URLDecoder.decode(StaticConstant.class.getResource("/ca").getPath())+properties.getProperty(dsptbm.substring(0, 8)+"SJ.Der");
//						QYCaCryptUtil qyutil = new QYCaCryptUtil(FileUtils.readFileToByteArray(new File(derPath)),zspassword,filePath);//动态读取税局端的公钥
//						temp_coutnent=qyutil.decrypt(temp_coutnent);//解密
//					}
				}else if("0".equals(encryptCode)){
					
//					throw new Exception("内层报文必须加密。");
				}else{
					throw new Exception("内层报文的加密方式不对。");
				}
				String content = new String(temp_coutnent,CHARSET);
				
				return content;
				
			}else{
				return "";
			}
	}
	
	public static String encodeData(String org,String zipCode,String encryptCode,String password,String dsptbm) throws Exception{
		byte[] temp_content=org==null?"".getBytes():org.getBytes(CHARSET);
		if (StringUtils.isNotEmpty(org)) {
			if("1".equals(encryptCode)){
				temp_content = TripleDESUtil.encryptMode(password.substring(10, password.length()), temp_content);
			}else if("2".equals(encryptCode)){
//				if(StaticConstant.CA_STATE.equals("1")){
//					byte[] qyDer=(byte[]) StaticConstant.caPublicKeyMap.get(dsptbm);
//					if(qyDer != null){
//						SJCaCryptUtil sjutil = new SJCaCryptUtil(qyDer);
//					temp_content=sjutil.encrypt(temp_content);
//					}
//				}else if(StaticConstant.CA_STATE.equals("2")){
//						String zspassword=(String) StaticConstant.caPublicKeypasswordMap.get(dsptbm);
//						String filePath=(String) StaticConstant.caPublicKeyMap.get(dsptbm);
//						Properties properties = new Properties();
//						properties.load(StaticConstant.class.getResourceAsStream("/protocol.properties"));
//						String derPath=URLDecoder.decode(StaticConstant.class.getResource("/ca").getPath())+properties.getProperty(dsptbm.substring(0, 8)+"SJ.Der");
//						QYCaCryptUtil qyutil = new QYCaCryptUtil(FileUtils.readFileToByteArray(new File(derPath)), zspassword, filePath);//动态读取税局端的公钥
//						temp_content=qyutil.encrypt(temp_content);//加密
//				}
			}
			if ("1".equals(zipCode)) {
				temp_content = GZipUtils.compress(temp_content);
			} 
			String content=new sun.misc.BASE64Encoder().encode(temp_content);
			return content;
		} 
		return "";
	}

	/**
	 * 
	 * <p>
	 * 取得返回信息对像
	 * </p>
	 * 
	 * @param args
	 *            void
	 */
	public static ReturnStateInfo getReturnStateInfo(String returnStateCode,
			String returnMessage) {
		ReturnStateInfo returnStateInfo = new ReturnStateInfo();
		returnStateInfo.setReturnCode(returnStateCode);
		returnStateInfo.setReturnMessage(returnMessage);
		return returnStateInfo;
	}

	/**
	 * 
	 * <p>
	 * 取得交互数据对像
	 * </p>
	 * 
	 * @param args
	 *            void
	 */
	public static Data getData(ByteArrayOutputStream out) throws Exception{
		Data data = new Data();
//		data.setEncryptCode(StaticConstant.PROTOCOL_ENCRYPTCODE);
//		data.setCodeType(StaticConstant.PROTOCOL_CODETYPE);
		data.setEncryptCode("");
		data.setCodeType("");
		String temp_str = new String(out.toByteArray(),CHARSET);
		data.setZipCode(ProXml.isZip(temp_str, 10));
		if (temp_str != null && !temp_str.equals("null")&& !temp_str.equals("")) {
			temp_str = temp_str.substring(temp_str.indexOf("<ROOT>") + 6,temp_str.lastIndexOf("</ROOT>"));
		} else {
			temp_str = "";
		}
		data.setContent(temp_str);
		return data;
	}
	

	public static String replaceStr(String s) {
		return s.replaceAll(" ", "");
	}

	public static List<?> getDataRoot(String xml) throws Exception {
		String xmlRootStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <ROOT>";
		String xmlRootEnd = "</ROOT>";
	    if(xml.startsWith("<?xml")){
	        XMLShellFactory factory = XMLShellFactory.newInstance();
	        return (List<?>) factory.generateDomainObject(xml).get(0);
	    }else{
	        XMLShellFactory factory = XMLShellFactory.newInstance();
            return (List<?>) factory.generateDomainObject(xmlRootStart + xml + xmlRootEnd).get(0);
	    }
	    
	}
	
	/**
     * 构建GlobalInfo对象
     * @param map map中必须包含key为“ywbm”的接口编码、key为”dsptbm“的电商平台编码
     * @param globalInfo 必须实例化
     * @param data
     */
	public static void getGlobalInfo(Map<String,Object> map, GlobalInfo globalInfo) throws Exception{
		String password = PassWordCreate.passWordCreate(map.get("zcm").toString(),"1234567890");
		globalInfo.setAppId(XmlPar.WLFP);
		globalInfo.setInterfaceCode((String)map.get("ywbm"));
		globalInfo.setPassWord(password);
		globalInfo.setRequestTime(getCurDate("yyyyMMddHHmmss"));
		globalInfo.setResponseCode("121");
		globalInfo.setRequestCode((String)map.get("dsptbm"));
		globalInfo.setUserName((String)map.get("dsptbm"));
		globalInfo.setDataExchangeId((new StringBuilder(String.valueOf((String)map.get("dsptbm")))).append((String)map.get("ywbm")).append(getCurDate("yyyyMMdd")).append(getRdom(9)).toString());
    }
	
	public static GlobalInfo getGlobalInfo(String busiType,String dsptbm,Date requestTime) {
		GlobalInfo globalInfo=new GlobalInfo();
        globalInfo.setAppId(XmlPar.WLFP);
        globalInfo.setInterfaceCode(busiType);
        globalInfo.setRequestTime(ProXml.getCurDate("yyyyMMddHHmmss"));
        globalInfo.setResponseCode(XmlPar.SJBM);
        globalInfo.setRequestCode(dsptbm);
        globalInfo.setUserName(dsptbm);
        return globalInfo;
    }
	
	public static GlobalInfo getGlobalInfo(String busiType,String dsptbm,String dataExId) {
		GlobalInfo globalInfo=new GlobalInfo();
        globalInfo.setAppId(XmlPar.WLFP);
        globalInfo.setInterfaceCode(busiType);
        globalInfo.setRequestTime(ProXml.getCurDate("yyyyMMddHHmmss"));
        globalInfo.setResponseCode(XmlPar.SJBM);
        globalInfo.setRequestCode(dsptbm);
        globalInfo.setUserName(dsptbm);
        globalInfo.setDataExchangeId(dataExId);
        return globalInfo;
    }
	
	
    
	/**
	 * 获取随机数
	 * @param ws
	 * @return
	 */
    public static String getRdom(int ws) {
        Random r = new Random();
        String nums = Integer.toString((Math.abs(r.nextInt(Integer.MAX_VALUE))));
        if (nums.length() >= ws)
            return nums.substring(nums.length() - ws);
        else
            return StringUtils.leftPad(nums, ws, "0");
    }
    
//    /**
//	 * 
//	 * <p>日志bean</p>
//	 * 
//	 * @param map
//	 * @return LogBean
//	 */
//	public static LogBean getLogBean(Map<String,Object> map,String type) throws Exception{
//		LogBean logBean = new LogBean();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		GlobalInfo globalInfoR = (GlobalInfo) map.get(XmlPar.GLOBALINFO);
//		ReturnStateInfo returnStateInfoR = (ReturnStateInfo) map.get(XmlPar.RETURNSTATEINFO);
//		if(returnStateInfoR == null){
//			returnStateInfoR = new ReturnStateInfo();
//		}
//		logBean.setAppId(globalInfoR.getAppId());
//		logBean.setDataEcChangeId(globalInfoR.getDataExchangeId());
//		logBean.setInterFaceCode(globalInfoR.getInterfaceCode());
//		logBean.setRequestCode(globalInfoR.getRequestCode());
//		logBean.setRequestTime(sdf.parse(globalInfoR.getRequestTime()));
//		logBean.setResponseCode(globalInfoR.getResponseCode());
//		logBean.setReturnCode(returnStateInfoR.getReturnCode());
//		logBean.setReturnMessage(returnStateInfoR.getReturnMessage());
//		if(type.equals("response")){
//			logBean.setReturnMessage(ProXml.encode(returnStateInfoR.getReturnMessage()));
//		}
//		return logBean;
//	}

//	/**
//	 * 
//	 * <p>数据bean</p>
//	 * @param map
//	 * @return DataLogBean
//	 */
//	public static DataLogBean getDataLogBean(Map<String,Object> map) throws Exception{
//		DataLogBean dataLogBean = new DataLogBean();
//		Data dataR = (Data) map.get(XmlPar.DATA);
//		GlobalInfo globalInfoR = (GlobalInfo) map.get(XmlPar.GLOBALINFO);
//		dataLogBean.setZipCode(dataR.getZipCode());
//		dataLogBean.setResponseCode(globalInfoR.getResponseCode());
//		dataLogBean.setRequestCode(globalInfoR.getRequestCode());
//		dataLogBean.setEncryptCode(dataR.getEncryptCode());
//		dataLogBean.setDataExChangeId(globalInfoR.getDataExchangeId());
//		dataLogBean.setInterFaceCode(globalInfoR.getInterfaceCode());
//		if(dataR.getContent()!=null){
//			dataLogBean.setContent((dataR.getContent()).getBytes());
//		}
//		return dataLogBean;
//	}
	
	public static String beanToxml(Object obj) throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLShellFactory.newInstance().saveXml(out, obj);
        Data data = ProXml.getData(out);
        return data.getContent();
    }
	
	public static Map<String, Object> getInterface(DzfpInterfaceBean dzfpInterface) {
		if(null == dzfpInterface) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(XmlPar.GLOBALINFO, dzfpInterface.getGlobalInfo());
		map.put(XmlPar.DATA, dzfpInterface.getData());
		map.put(XmlPar.RETURNSTATEINFO, dzfpInterface.getReturneStateInfo());
		return map;
	}
}
