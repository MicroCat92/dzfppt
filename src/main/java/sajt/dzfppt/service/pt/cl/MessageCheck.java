package sajt.dzfppt.service.pt.cl;

import java.util.Map;

import org.apache.log4j.Logger;

import sajt.dzfppt.dao.DsptxxDao;
import sajt.dzfppt.dao.NsrxxDao;
import sajt.dzfppt.entity.Dsptxx;
import sajt.dzfppt.entity.Nsrxx;
import sajt.dzfppt.service.pt.bean.Data;
import sajt.dzfppt.service.pt.bean.GlobalInfo;
import sajt.dzfppt.service.pt.util.RSAUtil;
import sajt.dzfppt.service.pt.util.ValidateUtil;
import sajt.dzfppt.service.pt.util.XmlPar;

public class MessageCheck {
	
	private final static Logger log = Logger.getLogger(MessageCheck.class);

	public static String checkGlobalInfo(GlobalInfo globalInfo, Map<String, Object> map, DsptxxDao dsptxxDao, NsrxxDao nsrxxDao) {
		String dspt = globalInfo.getUserName();
		String taxpayerId = globalInfo.getTaxpayerId();

		if (ValidateUtil.checkParmaterIsEmpty(taxpayerId)) {
			log.info("纳税人识别号不能为空");
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_TAXPAYERID_ISNULL, "纳税人识别号不能为空!", null);
		}
		if (ValidateUtil.checkParmaterIsEmpty(globalInfo.getAppId())) {
			log.info("应用标识不能为空");
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_APPID_ISNULL, "应用标识不能为空!", null);
		}
		if (ValidateUtil.checkParmaterIsEmpty(globalInfo.getInterfaceCode())) {
			log.info("接口编码不能为空");
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_INTERFACECODE_ISNULL, "接口编码不能为空!", null);
		}
		if (ValidateUtil.checkParmaterIsEmpty(globalInfo.getUserName())) {
			log.info("平台编码不能为空");
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_USERNAME_ISNULL, "平台编码不能为空!", null);
		}
		if (ValidateUtil.checkParmaterIsEmpty(globalInfo.getRequestCode())) {
			log.info("数据交换请求发起方代码不能为空");
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_REQUESTCODE_ISNULL, "数据交换请求发起方代码不能为空!", null);
		}
		if (ValidateUtil.checkParmaterIsEmpty(globalInfo.getAuthorizationCode())) {
			log.info("纳税人授权码不能为空");
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_AUTHORIZATIONCODE_ISNULL, "纳税人授权码不能为空!", null);
		}
		
		Nsrxx nsrxx = nsrxxDao.selectByNsrsbh(taxpayerId);
		if (nsrxx == null) {
			log.error("纳税人识别号不存在！");
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_TAXPAYERID_NOEXIST, "纳税人识别号不存在!", null);
		} else {
			// 虚拟电商平台数据
			Dsptxx dsptxx = dsptxxDao.selectByDsptbm(globalInfo.getUserName());
			if (dsptxx == null) {
				log.error("纳税人识别号，平台编码和纳税人授权码不匹配");
				return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_NOTMATCH, "纳税人识别号，平台编码和纳税人授权码不匹配!", null);
			}
			if (dsptxx.getSjly() == null || "".equals(dsptxx.getSjly().trim())) {
				String DSPTBM = nsrxx.getDsptbm();
				dsptxx = dsptxxDao.selectByDsptbm(DSPTBM);
//				String DSPTSQL = "select * from tbl_dsptxx where DSPTBM=?";
//				List<Record> dsptlist = Db.find(DSPTSQL, DSPTBM);
				if (dsptxx == null) {
					log.error("纳税人识别号，平台编码和纳税人授权码不匹配");
					return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_NOTMATCH, "纳税人识别号，平台编码和纳税人授权码不匹配!", null);
				} else {
					String SQM = dsptxx.getSqm();
					if (!globalInfo.getAuthorizationCode().equals(SQM)) {
						return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_NOTMATCH, "纳税人识别号，平台编码和纳税人授权码不匹配!", null);
					}
					String state = dsptxx.getState();
					if ("0".equals(state)) {
						return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_SQM_OVERDUE, "纳税人授权码已过期!", null);
					}
				}
			} else {// 平台传过来的，先校验授权码，再验签
				if (globalInfo.getInterfaceCode().trim().equals(XmlPar.EI_FPMXXZ)
						|| globalInfo.getInterfaceCode().trim().equals(XmlPar.EI_FPXZ)) {
					if (!globalInfo.getAuthorizationCode().trim().equals(dsptxx.getSqm())) {
						return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_NOTMATCH, "纳税人识别号，平台编码和纳税人授权码不匹配!");
					}
				} else {
					if (globalInfo.getInterfaceCode().trim()
							.equals(XmlPar.EI_FPMXXZ)
							|| globalInfo.getInterfaceCode().trim()
									.equals(XmlPar.EI_FPXZ)) {
						if (!globalInfo.getAuthorizationCode().trim().equals(dsptxx.getSqm())) {
							return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_NOTMATCH, "纳税人识别号，平台编码和纳税人授权码不匹配!", null);
						}
	        		}else{
	        			nsrxx = nsrxxDao.selectAllByNsrxbh(taxpayerId);
//	        			String sqmsql = "select * from tbl_nsrxx nsrxx left join tbl_dsptxx dsptxx on nsrxx.DSPTBM=dsptxx.dsptbm where nsrxx.NSRSBH=?";
//						Record sqmrd = Db.findFirst(sqmsql, globalInfo
//								.getTaxpayerId().trim());
						String sqm = nsrxx.getDsptxx().getSqm();
						if (!globalInfo.getAuthorizationCode().trim().equals(sqm)) {
							return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_NOTMATCH, "纳税人识别号，平台编码和纳税人授权码不匹配!");
						}
	        		}
	        		String publicKey=dsptxx.getPublicKey();
	        		String requestcode=globalInfo.getRequestCode();
	        		Map<String,String> signmap=RSAUtil.getSignMap(globalInfo);
	        		Data data=(Data)map.get(XmlPar.DATA);
	        		signmap.put("encryptCode",data.getEncryptCode().trim());
	        		signmap.put("codeType", data.getCodeType()==null?"":data.getCodeType().trim());
	        		signmap.put("content",(String)map.get(XmlPar.ENCRYPTIONCON));
	        		log.info("加签内容：");
	        		log.info(RSAUtil.getSignatureContent(signmap));
	        		log.info("加签密文：");
	        		log.info(requestcode);
	        		try {
	        			if(!RSAUtil.verify(RSAUtil.getSignatureContent(signmap),RSAUtil.getPublickey(publicKey), requestcode)){
	            			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_REQUESTCODE_ERROR, "验签不通过!");
	            		}
					} catch (Exception e) {
						return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_REQUESTCODE_ERROR, "验签不通过!");
					}
				}
			}
		}
		return null;
	}

}
