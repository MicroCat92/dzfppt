package sajt.dzfppt.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.log4j.Logger;

import sajt.dzfppt.service.IStrategy;
import sajt.dzfppt.service.pt.bean.Data;
import sajt.dzfppt.service.pt.bean.GlobalInfo;
import sajt.dzfppt.service.pt.bean.REQUEST_FPXXXZ_NEW;
import sajt.dzfppt.service.pt.cl.MessageResultTemplate;
import sajt.dzfppt.service.pt.cl.ProXml;
import sajt.dzfppt.service.pt.util.ValidateUtil;
import sajt.dzfppt.service.pt.util.XmlPar;

/**
 * 发票下载
 * 
 * @author MicroCat
 *
 */
public class StrategyFpxzImpl implements IStrategy {
	
	private final static Logger log = Logger.getLogger(StrategyFpxzImpl.class);

	@Override
	public String invoke(Map<String, Object> map) {
		try {
			Data data = (Data) map.get(XmlPar.DATA);
			
			REQUEST_FPXXXZ_NEW request_fpxxxz = (REQUEST_FPXXXZ_NEW) ProXml.getDataRoot(data.getContent()).get(0);
			String result = checkContent(map, request_fpxxxz);
			if(result != null) {
				return result;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("发票信息下载信息解码异常 : " + e.getMessage());
			return MessageResultTemplate.resultPanel(XmlPar.SYSTEM_LEVEL_ERROR, "请联系管理员!" + XmlPar.SYSTEM_LEVEL_ERROR + "; 发票信息下载.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("发票信息下载报文解析异常 : " + e.getMessage());
			return MessageResultTemplate.resultPanel(XmlPar.MESSAGE_ANA_ERROR, "content 解析异常");
		}
		return null;
	}

	//return MessageResultTemplate.resultPanel(XmlPar., "!");
	private String checkContent(Map<String, Object> map, REQUEST_FPXXXZ_NEW request_fpxxxz) throws UnsupportedEncodingException {
		GlobalInfo globalInfo=(GlobalInfo) map.get(XmlPar.GLOBALINFO);
		if (request_fpxxxz == null) {
		    return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_RESPONSEFPXXXZ_ISNULL, "发票下载请求数据为空!");
		}
		//发票流水号检查	非空
		if (ValidateUtil.checkParmaterIsEmpty(request_fpxxxz.getFPQQLSH())) {
		    return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_FPQQLSH_ISNULL, "发票请求流水号为空!");
		}
		//发票流水号长度
		if(request_fpxxxz.getFPQQLSH().getBytes("gbk").length<20 || request_fpxxxz.getFPQQLSH().getBytes("gbk").length>50) {
		    return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPT_FPQQLSH_ISNULL_ILLEGALLENGTH, "发票请求流水号长度超出范围!");
		}
		//电商平台编码检查	非空
		if(ValidateUtil.checkParmaterIsEmpty(request_fpxxxz.getDSPTBM())) {
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPT_DSPTBM_ISNULL, "电商平台编码为空!");
		}
		//电商平台编码长度
		if(request_fpxxxz.getDSPTBM().getBytes("gbk").length>8){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPT_DSPTBM_LENGTHTOOLONG, "平台编码长度超出范围!");
		}
		//校验订单号是否为空
		if(ValidateUtil.checkParmaterIsEmpty(request_fpxxxz.getDDH())) {
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_DDH_ISNULL, "订单号为空!");
		}
		//校验订单号长度
		if(request_fpxxxz.getDDH().getBytes("gbk").length>50){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_DDH_LENGTHTOOLONG, "订单号长度超出范围!");
		}
		//校验开票方识别号是否为空
		if(ValidateUtil.checkParmaterIsEmpty(request_fpxxxz.getNSRSBH())) {
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPT_NSRSBH_ISNULL, "开票方识别号为空!");
		}
		//校验开票方识别号长度
		if(request_fpxxxz.getNSRSBH().getBytes("gbk").length>20){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPT_NSRSBH_LENGTHTOOLONG, "开票方识别号长度超出范围!");
		}
		//校验发票下载方式是否为空
		if(ValidateUtil.checkParmaterIsEmpty(request_fpxxxz.getPDF_XZFS())){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_PDF_XZFS_ISNULL, "发票下载方式不能为空!");
		}
		//校验发票下载方式长长度
		if(request_fpxxxz.getPDF_XZFS().getBytes("gbk").length>1){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_PDF_XZFS_LENGTHTOOLONG, "发票下载方式长度超出范围!");
		}
		//校验发票下载方式内容
		String PDF_XZFS=request_fpxxxz.getPDF_XZFS();
		if(!PDF_XZFS.equals("0") && !PDF_XZFS.equals("1") && !PDF_XZFS.equals("2") && !PDF_XZFS.equals("3")){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_PDF_XZFS_UNDEFINEFIELD, "发票下载方式内容错误!");
		}
		String nsrsbh=request_fpxxxz.getNSRSBH();
		String dm=request_fpxxxz.getDSPTBM();
		//校验纳税人识别号内容
		if(!nsrsbh.equals(globalInfo.getTaxpayerId())){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_PDF_NSRSBH_ERROR, "纳税人识别号错误!");
		}
		//校验平台编码内容
		if(!dm.equals(globalInfo.getUserName())){
			return MessageResultTemplate.resultPanel(XmlPar.ERRORCODE_DATA_FPXX_PDF_DSPTBM_ERROR, "平台编码错误!");
		}
		return null;
	}

}
