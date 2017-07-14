package sajt.dzfppt.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.log4j.Logger;

import sajt.dzfppt.dao.NsrxxDao;
import sajt.dzfppt.entity.Nsrxx;
import sajt.dzfppt.service.IStrategy;
import sajt.dzfppt.service.pt.bean.Data;
import sajt.dzfppt.service.pt.bean.FPKJXX_DDXX;
import sajt.dzfppt.service.pt.bean.FPKJXX_FPTXX;
import sajt.dzfppt.service.pt.bean.FPKJXX_XMXX;
import sajt.dzfppt.service.pt.bean.GlobalInfo;
import sajt.dzfppt.service.pt.bean.REQUEST_FPKJXX;
import sajt.dzfppt.service.pt.cl.MessageResultTemplate;
import sajt.dzfppt.service.pt.cl.ProXml;
import sajt.dzfppt.service.pt.util.ValidateUtil;
import sajt.dzfppt.service.pt.util.XmlPar;
/**
 * 发票下载
 * @author MicroCat
 *
 */
public class StrategyFpkjImpl implements IStrategy {

	private final static Logger log = Logger.getLogger(StrategyFpkjImpl.class);
	
	@Override
	public String invoke(Map<String, Object> map) {
		try {
			Data data = (Data) map.get(XmlPar.DATA);
			String content = data.getContent();
			
			if(content.contains("€")){
//			throw new Exception();
				return MessageResultTemplate.resultPanel(XmlPar.SYSTEM_LEVEL_ERROR, "请联系管理员!" + XmlPar.SYSTEM_LEVEL_ERROR + "; 发票信息下载.");
			}
			
			if(ValidateUtil.checkParmaterIsEmpty(content)){
//			map.put(XmlPar.DATA, new Data());
//			returnInfo.setReturnCode(XmlPar.ERRORCODE_DATA_FPT_CONTENT_ISNULL);
//			returnInfo.setReturnMessage("内层报文为空");
//			map.put(XmlPar.RETURNSTATEINFO, returnInfo);
//			return map;
				return MessageResultTemplate.resultPanel(XmlPar.SYSTEM_LEVEL_ERROR, "请联系管理员!" + XmlPar.SYSTEM_LEVEL_ERROR + "; 发票信息下载.");
			}
			REQUEST_FPKJXX REQUEST_FPKJXX = (REQUEST_FPKJXX) ProXml.getDataRoot(content).get(0);
			if(REQUEST_FPKJXX == null) {
			}
			
			String result = checkContent(map, REQUEST_FPKJXX);
			return "";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String checkContent(Map<String, Object> map, REQUEST_FPKJXX REQUEST_FPKJXX) throws UnsupportedEncodingException {
		FPKJXX_FPTXX FPKJXX_FPTXX = REQUEST_FPKJXX.getFPKJXX_FPTXX();
		FPKJXX_DDXX FPKJXX_DDXX = REQUEST_FPKJXX.getFPKJXX_DDXX();
		FPKJXX_XMXX[] FPKJXX_XMXXS = REQUEST_FPKJXX.getFPKJXX_XMXXS();
		GlobalInfo globalInfo = (GlobalInfo) map.get(XmlPar.GLOBALINFO);
		String tax_nr = globalInfo.getTaxpayerId();
		NsrxxDao nsrxxDao = (NsrxxDao) map.get("nsrxxDao");
		Nsrxx nsrxx = nsrxxDao.selectByNsrsbh(tax_nr);
		String is_kce = nsrxx.getIsKce();
		String validate = nsrxx.getValidate();
		
		if (REQUEST_FPKJXX == null) {
//		    return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_REQUESTFPKJXX_ISNULL, "发票开具请求数据为空");
		}
		if (FPKJXX_FPTXX == null) {
//		    return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_REQUESTFPTXX_ISNULL, "发票开具发票头为空");
		}
		if (FPKJXX_XMXXS == null || FPKJXX_XMXXS.length < 1) {
//		    return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_REQUESTXMXXS_ISNULL, "发票开具明细为空");
		}
		if (ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getFPQQLSH())) {
//		    return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_FPQQLSH_ISNULL, "发票请求流水号为空");
		}
		if ("0".equals(validate)) {
//		    return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_NSRSBH_VALIDATE, "该税号已停用，不能开票");
		}
		if (FPKJXX_FPTXX.getFPQQLSH().getBytes("gbk").length<20 || FPKJXX_FPTXX.getFPQQLSH().getBytes("gbk").length>50) {
//		    return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_FPQQLSH_ISNULL_ILLEGALLENGTH, "发票请求流水号长度非法");
		}
//		boolean checkFpqqlshExist = DocumentDAO.dao.querycheckFpqqlshExist(FPKJXX_FPTXX.getFPQQLSH());
		Nsrxx nsrxx2 = nsrxxDao.selectByNsrsbh(FPKJXX_FPTXX.getFPQQLSH());
		if(nsrxx2 != null) {
			
//		    return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_FPQQLSH_ISNULL_ISEXIST, "接收开票数据成功！");
		}
//		if (checkFpqqlshExist) {
//		}
		
		//购货方名称检查	非空
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getGHFMC())) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHFMC_ISNULL, "购货方名称为空");
		}
		if(FPKJXX_FPTXX.getGHFMC().getBytes("gbk").length>100) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHFMC_LENGTHTOOLONG, "购货方名称长度不合法");
		}
		if(FPKJXX_FPTXX.getGHFMC().contains("_") || FPKJXX_FPTXX.getGHFMC().contains("&") || FPKJXX_FPTXX.getGHFMC().contains("<")){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHFMC_TSZF, "购货方名称有特殊字符！");
		}
		//GHF_NSRSBH长度校验  15 17 18 20
		if(FPKJXX_FPTXX.getGHF_NSRSBH()!=null&&FPKJXX_FPTXX.getGHF_NSRSBH().length()>0){
			if(!(FPKJXX_FPTXX.getGHF_NSRSBH().getBytes("gbk").length==15 || FPKJXX_FPTXX.getGHF_NSRSBH().getBytes("gbk").length==17 || FPKJXX_FPTXX.getGHF_NSRSBH().getBytes("gbk").length==18 || FPKJXX_FPTXX.getGHF_NSRSBH().getBytes("gbk").length==20 )) {
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHF_NSRSBH_LENGTHMISMATCH, "购货方纳税人识别号不正确");
			}
			//校验购方税号格式
			FPKJXX_FPTXX.setGHF_NSRSBH(FPKJXX_FPTXX.getGHF_NSRSBH().toUpperCase());
			if(!ValidateUtil.checkGHF_NSRSBH(FPKJXX_FPTXX.getGHF_NSRSBH())){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHF_NSRSBH_ERROR,"购货方纳税人识别号格式错误");
			}
		}
		//购货方地址检查
	    if (!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getGHF_DZ()) && FPKJXX_FPTXX.getGHF_DZ().getBytes("gbk").length>80) {
//	        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHFDZ_LENGTHTOOLONG, "购货方地址长度非法");
	    }
		//开票员检查	非空，字段长限制8
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getKPY())) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPY_ISNULL, "开票员为空");
		}
		if(FPKJXX_FPTXX.getKPY().getBytes("gbk").length>8) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPY_LENGTHTOOLONG, "开票员字段长度超出范围");
		}
		//收款员检查	字段长限制8
		if(FPKJXX_FPTXX.getSKY()!=null&&FPKJXX_FPTXX.getSKY().getBytes("gbk").length>8) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_SKY_LENGTHTOOLONG, "收款员长度超出范围");
		}
		//复核人检查	字段长限制8
		if(FPKJXX_FPTXX.getFHR()!=null&&FPKJXX_FPTXX.getFHR().getBytes("gbk").length>8) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_FHR_LENGTHTOOLONG, "复核人长度过长");
		}
		//开票类型检查	非空，字段长限制1，内容1,2
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getKPLX())) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPLX_ISNULL, "开票类型为空");
		}
		if(FPKJXX_FPTXX.getKPLX().getBytes("gbk").length>1) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPLX_LENGTHTOOLONG, "开票类型字段过长");
		}
		String kplx=FPKJXX_FPTXX.getKPLX();
		if(!("1".equals(kplx) || "2".equals(kplx))) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPLX_UNDEFINEFIELD, "开票类型字段非法");
		}
		//原发票代码检查	字段长限制12 kplx为2时是必录
		if(kplx.equals("2")){
			
			if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getYFP_DM())) {
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_YFP_DM_CONSTRAINTERROR, "kplx为2时yfp_dm为必录");
			}
			//原发票号码检查	字段长限制8，kplx为2时是必录
			if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getYFP_HM())) {
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_YFP_HM_CONSTRAINTERROR, "kplx为2时yfp_hm为必录");
			}
		}
		if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getYFP_DM()) && FPKJXX_FPTXX.getYFP_DM().getBytes("gbk").length>12) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_YFP_DM_LENGTHTOOLONG, "原发票代码长度超出范围");
		}
		if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getYFP_HM()) && FPKJXX_FPTXX.getYFP_HM().getBytes("gbk").length>8) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_YFP_HM_LENGTHTOOLONG, "原发票号码长度超出范围");
		}
		//价税合计金额检查 字段长16
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getKPHJJE())) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPHJJE_ISNULL, "价税合计金额为空");
		}
		if(FPKJXX_FPTXX.getKPHJJE().getBytes("gbk").length>16) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPHJJE_OUTOFRANGE, "价税合计金额超出范围");
		}
		if(!ValidateUtil.checkNumerical(FPKJXX_FPTXX.getKPHJJE())){
//			return errorMapCodeOfMsg(XmlPar.ERRORNUMERICAL,"数值类型中含有特殊字符");
		}
		//订单时间检查	格式YYYY-MM-DD HH:MI:SS
		if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_DDXX.getDDDATE()) && !formatCheck(FPKJXX_DDXX.getDDDATE())) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_DDDATE, "订单时间格式不正确");
		}
		//备注检查		字段长度限制200
		if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getBZ()) && FPKJXX_FPTXX.getBZ().getBytes("gbk").length>200) {
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_BZ_LENGTHTOOLONG, "备注字段长度超出范围");
		}
		//校验平台编码是否为空
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getDSPTBM())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_DSPTBM_ISNULL,"平台编码不能为空");
		}
		//校验平台编码长度
		if(FPKJXX_FPTXX.getDSPTBM().getBytes("gbk").length>8){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_DSPTBM_LENGTHTOOLONG,"平台编码长度超出范围");
		}
		//校验平台编码内容
		if(!FPKJXX_FPTXX.getDSPTBM().equals(globalInfo.getUserName())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_DSPTBM_ERRORCONTENT,"平台编码内容错误");
		}
		//校验开票方识别号是否为空
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getNSRSBH())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_NSRSBH_ISNULL,"开票方识别号不能为空");
		}
		//校验开票方识别号长度
		if(FPKJXX_FPTXX.getNSRSBH().getBytes("gbk").length>20){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_NSRSBH_LENGTHTOOLONG,"开票方识别号长度超出范围");
		}
		//校验开票方识别号内容
		if(!FPKJXX_FPTXX.getNSRSBH().equals(globalInfo.getTaxpayerId())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_NSRSBH_ERRORCONTENT,"开票方识别号内容错误");
		}
		//校验开票方名称是否为空
//		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getNSRMC())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_NSRMC_ISNULL,"开票方名称不能为空");
//		}
		//校验开票方名称长度
		if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getNSRMC())){
			if(FPKJXX_FPTXX.getNSRMC().getBytes("gbk").length>200){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_NSRMC_LENGTHTOOLONG,"开票方名称长度超出范围");
			}
		}
		//校验代开标志是否为空
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getDKBZ())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_DKBZ_ISNULL,"代开标志不能为空");
		}
		//校验代开标志长度
		if(FPKJXX_FPTXX.getDKBZ().getBytes("gbk").length>1){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_DKBZ_LENGTHTOOLONG,"代开标志长度超出范围");
		}
		//校验代开标志内容
		if(!FPKJXX_FPTXX.getDKBZ().equals("0") && !FPKJXX_FPTXX.getDKBZ().equals("1")){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_DKBZ_UNDEFINEFIELD,"代开标志内容错误");
		}
		//校验主要开票是否为空
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getKPXM())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPXM_ISNULL,"主要开票项目不能为空");
		}
		//校验主要开票项目长度
		if(FPKJXX_FPTXX.getKPXM().getBytes("gbk").length>200){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_KPXM_LENGTHTOOLONG,"主要开票项目长度超出范围");
		}
		//校验编码版本号是否为空
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getBMB_BBH())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_BMB_BBH_ISNULL,"编码表版本号不能为空");
		}
		//校验编码版本号长度
		if(FPKJXX_FPTXX.getBMB_BBH().getBytes("gbk").length>20){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_BMB_BBH_LENGTHTOOLONG,"编码表版本号长度超出范围");
		}
		//校验销货方识别号是否为空
		if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getXHF_NSRSBH())){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_XHF_NSRSBH_ISNULL,"销货方识别号不能为空");
		}
		//校验销货方识别号长度
		if(FPKJXX_FPTXX.getXHF_NSRSBH().getBytes("gbk").length>20){
//			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_XHF_NSRSBH_LENGTHTOOLONG,"销货方识别号长度超出范围");
		}
		 if(!FPKJXX_FPTXX.getXHF_NSRSBH().equals(FPKJXX_FPTXX.getNSRSBH())){
//	        	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_XHF_NSRSBH_ERROR,"销货方识别号内容错误");
	        }
		//校验销货方名称长度
	        if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getXHFMC())){
	        	if(FPKJXX_FPTXX.getXHFMC().getBytes("gbk").length>200){
//	    			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_XHFMC_LENGTHTOOLONG,"销货方名称长度超出范围");
	    		}
	        }
	    	//校验销货方地址长度
	        if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getXHF_DZ()) && FPKJXX_FPTXX.getXHF_DZ().getBytes("gbk").length>80){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_XHF_DZ_LENGTHTOOLONG,"销货方地址长度超出范围");
			}
	      //校验销货方电话长度
			if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getXHF_DH()) && FPKJXX_FPTXX.getXHF_DH().getBytes("gbk").length>20){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_XHF_DH_LENGTHTOOLONG,"销货方电话长度超出范围");
			}
			//校验购货方手机号码长度 
			if(!ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getGHF_SJ()) && FPKJXX_FPTXX.getGHF_SJ().getBytes("gbk").length>20){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHF_SJ_LENGTHTOOLONG,"购货方手机号码长度超出范围");
			} 
			//校验购货方企业类型是否为空
			if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getGHFQYLX())){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHFQYLX_ISNULL,"购货方企业类型不能为空");
			}
			//校验购货方企业类型长度
			if(FPKJXX_FPTXX.getGHFQYLX().getBytes("gbk").length>2){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHFQYLX_LENGTHTOOLONG,"购货方企业类型长度超出范围");
			}
			//校验购货方企业类型的内容
			String GHFQYLX=FPKJXX_FPTXX.getGHFQYLX();
			if(!GHFQYLX.equals("01") && !GHFQYLX.equals("02") && !GHFQYLX.equals("03") && !GHFQYLX.equals("04")){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_GHFQYLX_UNDEFINEFIELD,"购货方企业类型内容错误");
			}
			//校验操作代码是否为空
			if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getCZDM())){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_CZDM_ISNULL,"操作代码不能为空");
			}
			//校验操作代码长度
			if(FPKJXX_FPTXX.getCZDM().getBytes("gbk").length>2){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_CZDM_LENGTHTOOLONG,"操作代码长度超出范围");
			}
			//校验操作代码内容
			String CZDM=FPKJXX_FPTXX.getCZDM();
			if(!CZDM.equals("10")  && !CZDM.equals("20")){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_CZDM_UNDEFINEFIELD,"操作代码内容错误");
			}
			//校验清单标志是否为空
			if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getQD_BZ())){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_QD_BZ_ISNULL,"清单标志不能为空");
			}else{
				if(FPKJXX_FPTXX.getQD_BZ().equals("1")){
					//校验清单发票项目名称是否为空
					if(ValidateUtil.checkParmaterIsEmpty(FPKJXX_FPTXX.getQDXMMC())){
//						return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_QDXMMC_ISNULL,"清单标志为1时，清单发票项目名称不能为空");
					}
					//校验清单发票项目名称长度
					if(FPKJXX_FPTXX.getQDXMMC().getBytes("gbk").length>200){
//						return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_QDXMMC_LENGTHTOOLONG,"清单发票项目名称长度超出范围");
					}
				}
			}
			//校验清单标志长度
			if(FPKJXX_FPTXX.getQD_BZ().getBytes("gbk").length>1){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_QD_BZ_LENGTHTOOLONG,"清单标志长度超出范围");
			}
			//校验清单标志内容
			String QD_BZ=FPKJXX_FPTXX.getQD_BZ();
			if(!QD_BZ.equals("0") && !QD_BZ.equals("1")){
//				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_FPT_QD_BZ_UNDEFINEFIELD,"清单标志内容错误");
			}
			boolean zkflag=false;
			String zkmc="";
			String zkbm="";
			// 判断发票开具明细信息是否为空
			for (int i = 0; i < FPKJXX_XMXXS.length; i++) {
			    FPKJXX_XMXX fpkjxx_xmxx = FPKJXX_XMXXS[i];
			    //项目金额检查	非空，字段长度限制16
			    if (ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getXMJE())) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMJE_ISNULL, "项目金额为空");
			    }
			    if (fpkjxx_xmxx.getXMJE().getBytes("gbk").length>16) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMJE_OUTOFRANGE, "项目金额字段长度超出范围");
			    }
			    if(!ValidateUtil.checkNumerical(fpkjxx_xmxx.getXMJE())){
//					return errorMapCodeOfMsg(XmlPar.ERRORNUMERICAL,"数值类型中含有特殊字符");
				}
			    //税率检查	非空，字段长度限制10
			    if (ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getSL())) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_SL_ISNULL, "税率为空");
			    }		    
			    if (fpkjxx_xmxx.getSL().getBytes("gbk").length>10 || Double.parseDouble(fpkjxx_xmxx.getSL())>1 || Double.parseDouble(fpkjxx_xmxx.getSL())<0) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_SL_OUTOFRANGE, "税率超出范围");
			    }
			    //含税标志检查	非空，字段长度限制1（0-含税，1-不含税）
			    if (ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getHSBZ())) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_HSBZ_ISNULL, "含税标志为空");
			    }
			    if (fpkjxx_xmxx.getHSBZ().getBytes("gbk").length>1) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_HSBZ_LENGTHTOOLONG, "含税标志字段长度超出范围");
			    }
			    if(!("1".equals(fpkjxx_xmxx.getHSBZ()) || "0".equals(fpkjxx_xmxx.getHSBZ()))) {
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_HSBZ_UNDEFINEFIELD, "含税标志值未定义内容");
			    }
			    Double hsje=0.0;
			    hsje=Double.parseDouble(fpkjxx_xmxx.getXMJE());
			    if(!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getKCE())){
			    	if("0".equals(is_kce)){
			    		if(!(Double.parseDouble(fpkjxx_xmxx.getKCE())==0)){
//			    			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_IS_KCE_0,"该税号无法开具差额征收的票。");
			    		}
			    	}
			    	if(FPKJXX_FPTXX.getKPLX().equals("1")){
				    	if(Double.parseDouble(fpkjxx_xmxx.getKCE())>0){
				    		if(Double.parseDouble(fpkjxx_xmxx.getKCE())>hsje){
//				    			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_CEZS_BLUE_DYXMJE,"差额征税蓝字发票时，扣除额不能大于不含税金额");
				    		}
				    		if("2".equals(fpkjxx_xmxx.getFPHXZ())){
				    			if(FPKJXX_XMXXS.length>2){
//				    				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_CHZS_BLUE_ZKH_ERROR ,"差额征税蓝字发票时，若存在折扣行，商品行不能大于2行");
				    			}
				    		}else{
								if(FPKJXX_XMXXS.length>1){
//									return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_CHZS_BLUE_ZKH_ERROR ,"差额征税蓝字发票时，不存在折扣行，商品行不能大于1行");	    				
								}
				    		}
				    	}else if(Double.parseDouble(fpkjxx_xmxx.getKCE())<0){
//				    		return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_CHZS_BLUE_XY0,"差额征税蓝字发票时，扣除额不能小于0");
				    	}
			    	}else{
			    		if(Double.parseDouble(fpkjxx_xmxx.getKCE())<0){
				    		if(Double.parseDouble(fpkjxx_xmxx.getKCE())<hsje){
//				    			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_CEZS_RED_XYXMJE,"差额征税红字发票时，扣除额不能小于不含税金额");
				    		}
			    			if(FPKJXX_XMXXS.length>1){
//			    				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_CEZS_RED_ZKH_ERROR,"差额征税红字发票时，商品行不能大于1行");
			    			}
				    	}else if(Double.parseDouble(fpkjxx_xmxx.getKCE())>0){
//				    		return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_CEZS_RED_DY0,"差额征税红字发票时，扣除额不能大于0");
				    	}
			    	}
			    }
//			    xmjes=xmjes+Double.parseDouble(fpkjxx_xmxx.getXMJE());
			    //项目编码检查        必须 字段长20
			    if(ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getSPBM())) {
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMBM_ISNULL, "商品编码不能为空");
			    }
			    if(fpkjxx_xmxx.getSPBM().getBytes("gbk").length > 19) {
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMBM_LENGTHTOOLONG, "商品编码长度超出范围");
			    }
			    String begSpbm=fpkjxx_xmxx.getSPBM().substring(0,1);
			    if(!ValidateUtil.checkNumerical(begSpbm)){
//					return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_FP_SPBM_BEGIN_ERROR,"商品编码必须以数字开头");
				}
			    //校验发票行性质是否为空
			    if(ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getFPHXZ())) {
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_FPHXZ_ISNULL, "发票行性质不能为空");
			    }
			    //校验发票行性质长度
			    if(fpkjxx_xmxx.getFPHXZ().getBytes("gbk").length > 1) {
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_FPHXZ_LENGTHTOOLONG, "发票行性质长度超出范围");
			    }
			    //校验发票行性质内容
				String FPHXZ=fpkjxx_xmxx.getFPHXZ();
				if(!FPHXZ.equals("0") && !FPHXZ.equals("1") && !FPHXZ.equals("2")){
//					return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_FPHXZ_UNDEFINEFIELD,"发票行性质内容错误");
				}
				if(zkflag){
					zkflag=false;
					if(!FPHXZ.equals("1")){
//						return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_ZKH_ERROR,"折扣行要紧挨被折行之后");
					}
					if(!fpkjxx_xmxx.getXMMC().equals(zkmc) || !fpkjxx_xmxx.getSPBM().equals(zkbm)){
//						return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_ZKHMC_ERROR,"折扣行的商品名称、商品编码要与被折行相同");
					}
					zkmc="";
					zkbm="";
				}
				if(FPHXZ.equals("2")){
			    	zkflag=true;
			    	zkmc=fpkjxx_xmxx.getXMMC();
			    	zkbm=fpkjxx_xmxx.getSPBM();
			    }
				//校验优惠政策标识是否为空
			    if(ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getYHZCBS())) {
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_YHZCBS_ISNULL, "优惠政策标识不能为空");
			    }else{
			    	if(fpkjxx_xmxx.getYHZCBS().equals("1")){
				    	if(ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getZZSTSGL())){
//				    		return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_ZZSTSGL_ISNULL, "优惠政策标识为1时，增值税特殊管理必填");
				    	}
				    	if(fpkjxx_xmxx.getZZSTSGL().getBytes("gbk").length>50){
//				    		return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_ZZSTSGL_LENGTHTOOLONG, "增值税特殊管理长度超出范围");
				    	}
			    	}
			    }
			    //校验优惠政策标识长度
			    if(fpkjxx_xmxx.getYHZCBS().getBytes("gbk").length > 1) {
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_YHZCBS_LENGTHTOOLONG, "优惠政策标识长度超出范围");
			    }
			    //校验优惠政策标识内容
				String YHZCBS=fpkjxx_xmxx.getYHZCBS();
				if(!YHZCBS.equals("0") && !YHZCBS.equals("1")){
//					return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_YHZCBS_UNDEFINEFIELD,"优惠政策标识内容错误");
				}
				
				//校验零税率标识
			    if(!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getLSLBS())){
			    	//校验零税率标识长度
			    	if(fpkjxx_xmxx.getLSLBS().getBytes("gbk").length>1){
//			    		return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_LSLBS_LENGTHTOOLONG,"零税率标识长度超出范围");
			    	}
			    	//校验零税率标识内容
			    	String LSLBS=fpkjxx_xmxx.getLSLBS();
			    	if(!LSLBS.equals("1") && !LSLBS.equals("2") &&!LSLBS.equals("3")){
//			    		return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_LSLBS_UNDEFINEFIELD,"零税率标识内容错误");
			    	}
			    	//检验优惠政策内容
			    	if(!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getZZSTSGL())){
			    		if("1".equals(LSLBS)){
			    			if(!"免税".equals(fpkjxx_xmxx.getZZSTSGL().trim())){
//			    				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_ZZSTSGL_ERROR,"零税率标识内容与增值税特殊管理内容不相符");
			    			}
			    			if(!"1".equals(fpkjxx_xmxx.getYHZCBS())){
//			    				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_YHZCBS_ERROR,"零税率标识为免税或不征税时，优惠政策标识必须为1");
			    			}
			    		}else if("2".equals(LSLBS)){
			    			if(!"不征税".equals(fpkjxx_xmxx.getZZSTSGL().trim())){
//			    				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_ZZSTSGL_ERROR,"零税率标识内容与增值税特殊管理内容不相符");
			    			}
			    			if(!"1".equals(fpkjxx_xmxx.getYHZCBS())){
//			    				return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_YHZCBS_ERROR,"零税率标识为免税或不征税时，优惠政策标识必须为1");
			    			}
			    		}
			    	}
			    	if(Double.parseDouble(fpkjxx_xmxx.getSL())!=0.0){
//		    			return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_YHZCBS_SL_NOTZERO, "零税率标识不为空时，税率要为0");
		    		}
			    }
			    //校验项目名称是否为空
			    if(ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getXMMC())){
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMMC_ISNULL, "项目名称不能为空");
			    }
			    //项目名称检查	字段长度限制200
			    if (!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getXMMC()) && fpkjxx_xmxx.getXMMC().getBytes("gbk").length>90) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMMC_LENGTHTOOLONG, "项目名称长度超出范围");
			    }
			    //项目单位检查	字段长度限制100
			    if (!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getXMDW()) && fpkjxx_xmxx.getXMDW().getBytes("gbk").length>20) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMDW_LENGTHTOOLONG, "项目单位长度超出范围");
			    }
			    //规格型号检查
			    if (!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getGGXH()) && fpkjxx_xmxx.getGGXH().getBytes("gbk").length>40) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_GGXH_LENGTHTOOLONG, "项目规格型号超出范围");
			    }
			    //项目数量为空校验
			    if(ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getXMSL())){
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMSL_ISNULL, "项目数量不能为空");
			    }
			    //项目数量检查	字段长度限制24
			    if (!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getXMSL()) && fpkjxx_xmxx.getXMSL().getBytes("gbk").length>24) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMSL_OUTOFRANGE, "项目数量超出范围");
			    }
			    if(!ValidateUtil.checkNumerical(fpkjxx_xmxx.getXMSL())){
//					return errorMapCodeOfMsg(XmlPar.ERRORNUMERICAL,"数值类型中含有特殊字符");
				}
			    //项目单价检查	非空，字段长度限制24
			    if (ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getXMDJ())) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMDJ_ISNULL, "项目单价为空");
			    }
			    if (fpkjxx_xmxx.getXMDJ().getBytes("gbk").length>24) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMDJ_OUTOFRANGE, "项目单价字段长度超出范围");
			    }
			    if(!ValidateUtil.checkNumerical(fpkjxx_xmxx.getXMDJ())){
//					return errorMapCodeOfMsg(XmlPar.ERRORNUMERICAL,"数值类型中含有特殊字符");
				}
			    if(Double.parseDouble(fpkjxx_xmxx.getXMDJ())<0){
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMDJXY0,"项目单价不能为负");
			    }
			    Double xmsl=Double.parseDouble(fpkjxx_xmxx.getXMSL());
			    Double xmdj=Double.parseDouble(fpkjxx_xmxx.getXMDJ());
			    Double xmje=Double.parseDouble(fpkjxx_xmxx.getXMJE());
			    java.text.DecimalFormat   df=new  java.text.DecimalFormat("#.00");  
			    Double je=Double.valueOf(df.format(xmsl*xmdj));
			    if(!xmje.equals(je)){
//			    	return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_XMJE_ERROR,"项目金额应该等于项目单价*项目数量");
			    }
			    if("1".equals(FPHXZ)){
					if(xmje>0){
//						return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_ZKHJE_DY0,"折扣行项目金额为负");
					}
				}
			    
			    //税额检查	字段长度限制10
			    if (!ValidateUtil.checkParmaterIsEmpty(fpkjxx_xmxx.getSE()) && fpkjxx_xmxx.getSE().getBytes("gbk").length>10) {
//			        return errorMapCodeOfMsg(XmlPar.ERRORCODE_DATA_MXXX_SE_OUTOFRANGE, "税额超出范围");
			    }
			    
			}
		return null;
	}
	
	//时间格式判断
		private static boolean formatCheck(String time) {
			try {
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
			} catch (ParseException e1) {
				log.error("日期格式不正确："+time);
				return false;
			}
			
			return true;
		}

}
