package sajt.dzfppt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sajt.dzfppt.dao.DsptxxDao;
import sajt.dzfppt.dao.NsrxxDao;
import sajt.dzfppt.entity.Dsptxx;
import sajt.dzfppt.entity.Nsrxx;
import sajt.dzfppt.exception.InterfaceCodeException;
import sajt.dzfppt.exception.InvokeWSException;
import sajt.dzfppt.exception.OuterMessageParseException;
import sajt.dzfppt.pojo.ResultData;
import sajt.dzfppt.service.IEntrance;
import sajt.dzfppt.service.IStrategy;
import sajt.dzfppt.service.pt.bean.Data;
import sajt.dzfppt.service.pt.bean.GlobalInfo;
import sajt.dzfppt.service.pt.bean.ReturnStateInfo;
import sajt.dzfppt.service.pt.cl.MessageCheck;
import sajt.dzfppt.service.pt.cl.MessageResultTemplate;
import sajt.dzfppt.service.pt.cl.ProXmlPT;
import sajt.dzfppt.service.pt.util.HttpClientInvoke;
import sajt.dzfppt.service.pt.util.ReturnCode;
import sajt.dzfppt.service.pt.util.XmlPar;
import sajt.shdzfp.sl.service.ECInterfaceSl;
import sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortTypeProxy;

/**
 * 入口处理实现
 * 
 * @author MicroCat
 *
 */
@Service("entranceImpl")
public class EntranceImpl implements IEntrance {

	private final static Logger log = Logger.getLogger(EntranceImpl.class);

	private static String WSDL;

	@Autowired
	private DsptxxDao dsptxxDao;
	@Autowired
	private NsrxxDao nsrxxDao;

	static {
		try {
			Properties properties = new Properties();
			String path = EntranceImpl.class.getClassLoader().getResource("/").toURI().getPath();
			System.out.println(path);
			properties.load(new FileInputStream(new File(path + "service.properties")));
			WSDL = properties.getProperty("WSDL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("service 读取异常 : " + e.getMessage());
			System.exit(0);
		}
	}

	public void test(String xml, DsptxxDao dsptxxDao, NsrxxDao nsrxxDao) {
		this.dsptxxDao = dsptxxDao;
		this.nsrxxDao = nsrxxDao;
		execute(xml);
	}

	@Override
	public String execute(String xml) {
		try {
			// 报文解析
			boolean caBelong = false;
			Map<String, Object> map = null;
			log.info("解析报文");
			map = ProXmlPT.parseOuterMessage(xml, dsptxxDao, caBelong);

			GlobalInfo globalInfo = (GlobalInfo) map.get(XmlPar.GLOBALINFO);

			log.info("开始校验外层报文");
			String errmsg = MessageCheck.checkGlobalInfo(globalInfo, map, dsptxxDao, nsrxxDao);
			if (errmsg != null) {
				log.info("外层报文校验不通过");
				return errmsg;
			}

			String dsptbm = null;
			Dsptxx dsptxx = dsptxxDao.selectByDsptbm(globalInfo.getUserName());
			if (dsptxx.getSjly() != null || "".equals(dsptxx.getSjly().trim())) {
				dsptbm = globalInfo.getUserName();// 企业真实的平台编码
			} else {
				Nsrxx nsrxx = nsrxxDao.selectByNsrsbh(globalInfo.getTaxpayerId());
				dsptbm = nsrxx.getDsptbm();
			}

			// 获取 API 编码
			String interfaceCode = globalInfo.getInterfaceCode();
			IStrategy strategy = getInstance(interfaceCode);

			map.put("dsptxxDao", dsptxxDao);
			map.put("nsrxxDao", nsrxxDao);
			String result = strategy.invoke(map);
			if (result != null) {
				// return result
				return result;
			}
			// invoke pt
			// String resultXml = new
			// SajtIssueInvoiceServicePortTypeProxy(WSDL).eiInterface(xml);
			System.out.println("---------");
			System.out.println(xml);
			System.out.println("---------");
			
//			String resultXml = new SajtIssueInvoiceServicePortTypeProxy(WSDL).eiInterface(xml);
//			System.out.println(resultXml);
			
			System.out.println(" -- 调用 WSL --");
			ECInterfaceSl service = new ECInterfaceSl(WSDL);
			String resultXml = service.getDSInfo(xml);
			System.out.println(" -- 返回报文 --");
			System.out.println(resultXml);
			System.out.println(" -------------");
			
//			String resultXml = HttpClientInvoke.invoke(xml);
//			System.out.println("+++++++++");
//			System.out.println(resultXml);
//			System.out.println("+++++++++");

			// 解析返回报文
			System.out.println(" -- 解析返回报文 --");
			map = ProXmlPT.parseOuterMessage(resultXml, dsptxxDao, !caBelong);
			ReturnStateInfo returnStateInfo = (ReturnStateInfo) map.get(XmlPar.RETURNSTATEINFO);
			String returnCode = returnStateInfo.getReturnCode();
			String returnMessage = returnStateInfo.getReturnMessage();
			String content = ((Data) map.get(XmlPar.DATA)).getContent();
			System.out.println("################### -- content --");
			System.out.println(content);
			content = StringEscapeUtils.unescapeXml(content);// 反转义
			return MessageResultTemplate.resultPanel(returnCode, returnMessage, content);
		} catch (InterfaceCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
			return MessageResultTemplate.resultPanel(ReturnCode.ERROR_INTERFACECODE, "未定义的 interfaceCode!");
		} catch (OuterMessageParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
			log.error("数据类型或格式不合法 值为空,请确认数据传递是否正确!");
			return MessageResultTemplate.resultPanel(XmlPar.RESPONSEFAIL, "数据类型或格式不合法,请求处理失败!");
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvokeWSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
//		return xml;
	}

	private IStrategy getInstance(String interfaceCode) throws InterfaceCodeException {
		IStrategy strategy = null;
		switch (interfaceCode) {
		case XmlPar.EI_FPKJ:
			strategy = new StrategyFpkjImpl();
			break;
		case XmlPar.EI_FPXZ:
			strategy = new StrategyFpxzImpl();
			break;
		default:
			throw new InterfaceCodeException("未定义的 interfaceCode, " + interfaceCode);
		}
		return strategy;

	}

}
