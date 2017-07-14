package sajt.dzfppt.service.pt.util;

public class XmlPar {
	
	public final static String SYSTEM_LEVEL_ERROR = "system.error";
	public final static String MESSAGE_ANA_ERROR = "error.entity";
	/**
	 * 1.3.1 发票开具
	 */
	public final static String EI_FPKJ = "ECXML.FPKJ.BC.E_INV";
	/**
	 * 1.3.2 发票信息下载
	 */
	public final static String EI_FPXZ = "ECXML.FPXZ.CX.E_INV";
	/**
	 * 1.3.3 发票开具结果推送
	 */
	public final static String EI_FPKJJG_TS = "ECXML.FPKJJG.TS.E_INV";
	/**
	 * 1.3.4 邮箱/手机发票推送
	 */
	public final static String EI_EMAIL = "ECXML.EMAILPHONEFPTS.TS.E.INV";
	/**
	 * 发票明细下载
	 */
	public final static String EI_FPMXXZ = "ECXML.FPMXXZ.CX.E_INV";
	/**
	 * 签章接口
	 */
	public final static String FP_QIANZHANG = "ECXML.FPQZ.BC.E.INV";
	/**
	 * 订单明细信息查询
	 */
	public final static String EI_DDCX = "EXCML.DDCX.E_INV";
	/**
	 * 发票上传接口
	 */
	public final static String FP_SC = "ECXML.FPSC.E_INV";

	/**
	 * 企业开通申请接口
	 */
	public final static String QY_KT = "ECXML.QYSQ.KTSQ";
	/**
	 * 企业绑定校验接口
	 */
	public final static String EI_BDJY = "ECXML.QYSQ.BDJY";
	/**
	 * 获取企业开票抬头
	 */
	public final static String EI_KPTTXX = "ECXML.QY.KPTTXX";
	/**
	 * 获取企业可用发票数量 API
	 */
	public static String EI_KYFPSL = "ECXML.QY.KYFPSL";
	public static String QZ_DATAEXID = "EI_";
	public static String ROOT_BASE = "interface";
	public static String NS_ONE = "";
	public static String NS_TWO = "http://www.w3.org/2001/XMLSchema-instance";
	public static String NS_THREE = "http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd";
	public static String VERSION_NO = "WLFP1.0";
	public static String APPID = "appId";
	public static String GLOBALINFO = "globalInfo";
	public static String USERNAME = "userName";
	public static String PASSWORD = "passWord";
	public static String TERMINALCODE = "terminalCode";
	public static String INTERFACECODE = "interfaceCode";
	public static String REQUESTCODE = "requestCode";
	public static String REQUESTTIME = "requestTime";
	public static String VERSION = "version";
	public static String TAXPAYERID = "taxpayerId";
	public static String AUTHORIZATIONCODE = "authorizationCode";
	public static String RESPONSECODE = "responseCode";
	public static String DATAEXCHANGEID = "dataExchangeId";
	public static String RETURNSTATEINFO = "returnStateInfo";
	public static String RETURNCODE = "returnCode";
	public static String RETURNMESSAGE = "returnMessage";
	public static String DATA = "Data";
	public static String DATADESCRIPTION = "dataDescription";
	public static String ZIPCODE = "zipCode";
	public static String ENCRYPTCODE = "encryptCode";
	public static String CODETYPE = "codeType";
	public static String CONTENT = "content";
	public static String SJZSBH = "sjZsbh";
	public static String QYZSBH = "qyZsbh";
	public static String ENCRYPTIONCON = "encryptioncon";
	/** micro_begin */
	public static String REQUSET_FPKJXX = "request_fpkjxx";
	public static String FPKJXX_FPTXX = "fpkjxx_fptxx";
	public static String FPKJXX_XMXXS = "fpkjxx_xmxxs";
	public static String FPKJXX_XMXX = "fpkjxx_xmxx";
	public static String REQUEST_FPXXXZ = "request_fpxxxz";
	public static String REQUEST_MDXX = "request_mdxx";
	/** micro_end */

	public static String WLFP = "DZFP";
	public static final String SJBM = "121";
	public static String DO_SSUCCESS = "0000";
	public static String BUSI_DOING = "1000";
	public static String DO_FAIL = "9999";

	// **********************************************EC2INTRA******************************************//

	public static final String RESPONSEFAIL = "9999";// 数据类型或格式不合法
	public static final String RESPONSEYYSSUCCESS = "0000";
	// 组织发票开具信息传递给电商
	public static final String ERRORCODE = "errorCode";
	public static final String ERRORMSG = "errorMsg";
	// 外层协议错误代码
	public static final String ERRORCODE_INTERFACECODE_ISNULL = "9000";// 接口编码为空
	public static final String ERRORCODE_USERNAME_ISNULL = "9001";// 平台编码为空
	public static final String ERRORCODE_PASSWORD_ISNULL = "9002";// 密码为空
	public static final String ERRORCODE_REQUESTCODE_ISNULL = "9003";// 数据交换请求发起方代码
	public static final String ERRORCODE_APPID_ISNULL = "9004";// 应用标识为空
	public static final String ERRORCODE_TAXPAYERID_ISNULL = "9005";// 纳税人识别号为空
	public static final String ERRORCODE_AUTHORIZATIONCODE_ISNULL = "9006";// 纳税人授权码为空
	public static final String ERRORCODE_TAXPAYERID_NOEXIST = "9007";// 纳税人识别号不存在
	public static final String ERRORCODE_NOTMATCH = "9008";// 纳税人识别号,平台编码,纳税人授权码不匹配
	public static final String ERRORCODE_SQM_OVERDUE = "9009";// 纳税人授权码过期
	public static final String ERRORCODE_REQUESTCODE_ERROR = "9010";// 验签不通过
	public static final String ERRORCODE_ENCRYPTCONTRACT_DISOBEY = "9011";// 未遵循加密约定

	// 业务层协议错误代码
	public static final String ERRORCODE_DATA_REQUESTFPKJXX_ISNULL = "9100";// 发票开具请求数据为空
	public static final String ERRORCODE_DATA_REQUESTFPTXX_ISNULL = "9101";// 发票开具发票头为空
	public static final String ERRORCODE_DATA_REQUESTXMXXS_ISNULL = "9102";// 发票开具明细为空
	public static final String ERRORCODE_DATA_FPXX_RESPONSEFPXXXZ_ISNULL = "9103";// 发票下载请求数据为空
	public static final String ERRORCODE_DATA_FPMXXZ_RESPONSEFPMXXZ_ISNULL = "9104";// 发票明细下载请求数据为空
	public static final String ERRORCODE_DATA_DDCX_REQUESTDDCX_ISNULL = "9105";// 订单明细查询请求数据为空
	public static final String ERRORCODE_DATA_QYBDJY_REQUESTQYBDJY_ISNULL = "9106";// 企业绑定校验请求数据为空
	public static final String ERRORCODE_DATA_KPTTXX_REQUESTKPTTXX_ISNULL = "9107";// 获取企业开票抬头请求数据为空
	public static final String ERRORCODE_DATA_FPT_PASSWORD_ERROR = "9108";// 3DES加密时密码错误

	public static final String ERRORCODE_DATA_REQUESTDDXX_ISNULL = "9109";// 发票开具订单信息为空
	public static final String ERRORCODE_DATA_REQUESTKYFPSL_ISNULL = "9110";// 企业可用发票数量数据为空

	// 企业绑定校验
	public static final String ERRORCODE_DATA_QYBDJY_NSRMC_ISNULL = "8100";// 纳税人名为空
	public static final String ERRORCODE_DATA_QYBDJY_NSRMC_ILLEGALLENGTH = "8101";// 纳税人名称长度非法
	public static final String ERRORCODE_DATA_QYBDJY_NSRSBH_ISNULL = "8102";// 纳税人识别号为空
	public static final String ERRORCODE_DATA_QYBDJY_NSRSBH_DATAERROR_NOVALIDATE = "8103";// 纳税人信息数据出错，找不到可用纳税人信息!
	public static final String ERRORCODE_DATA_QYBDJY_NSRSBH_ILLEGALLENGTH = "8104";// 纳税人识别号长度非法
	// public static final String ERRORCODE_DATA_QYBDJY_NSRSBH_MISMATCH =
	// "8105";// 纳税人识别号上下文不匹配

	// 发票开具
	// 业务数据
	// 明细错误编码
	public static final String ERRORCODE_DATA_FPT_FPQQLSH_ISNULL = "9200";// 发票请求流水号为空
	public static final String ERRORCODE_DATA_FPT_FPQQLSH_ISNULL_ILLEGALLENGTH = "9201";// 发票请求流水号长度非法
	public static final String ERRORCODE_DATA_FPT_FPQQLSH_ISNULL_ISEXIST = "9202";// 发票请求流水号已经存在
	public static final String ERRORCODE_DATA_FPT_GHFMC_ISNULL = "9203";// 购货方名称为空
	public static final String ERRORCODE_DATA_FPT_KPY_ISNULL = "9204";// 开票员为空
	public static final String ERRORCODE_DATA_FPT_KPY_LENGTHTOOLONG = "9205";// 开票员字段长度超出范围
	public static final String ERRORCODE_DATA_FPT_SKY_LENGTHTOOLONG = "9206";// 收款员长度超出范围
	public static final String ERRORCODE_DATA_FPT_FHR_LENGTHTOOLONG = "9207";// 复核人长度过长
	public static final String ERRORCODE_DATA_FPT_KPLX_ISNULL = "9208";// 开票类型为空
	public static final String ERRORCODE_DATA_FPT_KPLX_LENGTHTOOLONG = "9209";// 开票类型字段过长
	public static final String ERRORCODE_DATA_FPT_KPLX_UNDEFINEFIELD = "9210";// 开票类型字段非法
	public static final String ERRORCODE_DATA_FPT_YFP_DM_LENGTHTOOLONG = "9211";// 原发票代码长度超出范围
	public static final String ERRORCODE_DATA_FPT_YFP_DM_CONSTRAINTERROR = "9212";// 约定：kplx为2时yfp_dm为必录
	public static final String ERRORCODE_DATA_FPT_YFP_HM_LENGTHTOOLONG = "9213";// 原发票号码长度超出范围
	public static final String ERRORCODE_DATA_FPT_YFP_HM_CONSTRAINTERROR = "9214";// 约定：kplx为2时yfp_hm为必录
	public static final String ERRORCODE_DATA_FPT_KPHJJE_ISNULL = "9215";// 价税合计金额为空
	public static final String ERRORCODE_DATA_FPT_KPHJJE_OUTOFRANGE = "9216";// 价税合计金额超出范围
	public static final String ERRORCODE_DATA_FPT_DDDATE = "9217";// 订单时间格式不正确
	public static final String ERRORCODE_DATA_FPT_BZ_LENGTHTOOLONG = "9218";// 备注字段长度超出范围
	public static final String ERRORCODE_DATA_FPT_TSZF = "9219";// 输入区有特殊字符
	public static final String ERRORCODE_DATA_FPT_GHF_NSRSBH_LENGTHMISMATCH = "9220";// 购货方纳税人识别号长度不正确
	public static final String ERRORCODE_DATA_FPT_DSPTBM_ISNULL = "9221"; // 平台编码为空
	public static final String ERRORCODE_DATA_FPT_DSPTBM_LENGTHTOOLONG = "9222"; // 平台编码长度超出范围
	public static final String ERRORCODE_DATA_FPT_NSRSBH_ISNULL = "9223"; // 开票方识别号为空
	public static final String ERRORCODE_DATA_FPT_NSRSBH_LENGTHTOOLONG = "9224"; // 开票方识别号长度超出范围
	public static final String ERRORCODE_DATA_FPT_NSRMC_ISNULL = "9225"; // 开票方名称为空
	public static final String ERRORCODE_DATA_FPT_NSRMC_LENGTHTOOLONG = "9226"; // 开票方名称长度超出范围
	public static final String ERRORCODE_DATA_FPT_DKBZ_ISNULL = "9227";// 代开标志为空
	public static final String ERRORCODE_DATA_FPT_DKBZ_LENGTHTOOLONG = "9228";// 代开标志长度超出范围
	public static final String ERRORCODE_DATA_FPT_DKBZ_UNDEFINEFIELD = "9229";// 代开标志的值未定义
	public static final String ERRORCODE_DATA_FPT_KPXM_ISNULL = "9230";// 主要开票项目为空
	public static final String ERRORCODE_DATA_FPT_KPXM_LENGTHTOOLONG = "9231";// 主要开票项目长度超出范围
	public static final String ERRORCODE_DATA_FPT_BMB_BBH_ISNULL = "9232";// 编码表版本号为空
	public static final String ERRORCODE_DATA_FPT_BMB_BBH_LENGTHTOOLONG = "9233";// 编码表版本号超出范围
	public static final String ERRORCODE_DATA_FPT_XHF_NSRSBH_ISNULL = "9234";// 销货方识别号为空
	public static final String ERRORCODE_DATA_FPT_XHF_NSRSBH_LENGTHTOOLONG = "9235";// 销货方识别号长度超出范围
	public static final String ERRORCODE_DATA_FPT_XHFMC_ISNULL = "9236";// 销货方名称为空
	public static final String ERRORCODE_DATA_FPT_XHFMC_LENGTHTOOLONG = "9237";// 销货方名称长度超出范围
	public static final String ERRORCODE_DATA_FPT_XHF_DZ_ISNULL = "9238";// 销货方地址为空
	public static final String ERRORCODE_DATA_FPT_XHF_DZ_LENGTHTOOLONG = "9239";// 销货方地址长度超出范围
	public static final String ERRORCODE_DATA_FPT_XHF_DH_ISNULL = "9240";// 销货方电话为空
	public static final String ERRORCODE_DATA_FPT_XHF_DH_LENGTHTOOLONG = "9241";// 销货方电话长度超出范围
	public static final String ERRORCODE_DATA_FPT_GHF_SJ_ISNULL = "9242";// 购货方手机号码为空
	public static final String ERRORCODE_DATA_FPT_GHF_SJ_LENGTHTOOLONG = "9243";// 购货方手机号码长度超出范围
	public static final String ERRORCODE_DATA_FPT_GHFQYLX_ISNULL = "9244";// 购货方企业类型为空
	public static final String ERRORCODE_DATA_FPT_GHFQYLX_LENGTHTOOLONG = "9245";// 购货方企业类型长度超出范围
	public static final String ERRORCODE_DATA_FPT_GHFQYLX_UNDEFINEFIELD = "9246";// 购货方企业类型未定义
	public static final String ERRORCODE_DATA_FPT_CZDM_ISNULL = "9247";// 操作代码为空
	public static final String ERRORCODE_DATA_FPT_CZDM_LENGTHTOOLONG = "9248";// 操作代码长度超出范围
	public static final String ERRORCODE_DATA_FPT_CZDM_UNDEFINEFIELD = "9249";// 操作代码未定义
	public static final String ERRORCODE_DATA_FPT_QD_BZ_ISNULL = "9250";// 清单标志为空
	public static final String ERRORCODE_DATA_FPT_QD_BZ_LENGTHTOOLONG = "9251";// 清单标志长度超出范围
	public static final String ERRORCODE_DATA_FPT_QD_BZ_UNDEFINEFIELD = "9252";// 清单标志内容未定义
	public static final String ERRORCODE_DATA_FPT_QDXMMC_ISNULL = "9253";// 清单标志为1时，清单发票项目名称为空
	public static final String ERRORCODE_DATA_FPT_QDXMMC_LENGTHTOOLONG = "9254";// 清单发票项目名称长度超出范围
	public static final String ERRORCODE_DATA_FPT_CONTENT_ISNULL = "9255";// 内层报文为空
	public static final String ERRORCODE_DATA_FPT_DSPTBM_ERRORCONTENT = "9256"; // 平台编码内容错误
	public static final String ERRORCODE_DATA_FPT_NSRSBH_ERRORCONTENT = "9257"; // 开票方识别号内容错误
	public static final String ERRORCODE_DATA_FPT_XHF_NSRSBH_ERROR = "9258";// 销货方识别号内容错误
	public static final String ERRORCODE_DATA_FPT_GHF_NSRSBH_ERROR = "9259";// 购货方格式错误
	public static final String ERRORCODE_DATA_FPT_GHFMC_TSZF = "9260";// 购货方名称有特殊字符
	public static final String ERRORCODE_DATA_FPT_NSRSBH_VALIDATE = "9261";// 税号已停用
	public static final String ERRORCODE_DATA_MXXX_ISSRED_ERROR = "9262";// 开红票时，原发票代码和号码不能为空
	public static final String ERRORCODE_DATA_MXXX_FP_DMHM_NOEXIST = "9263";// 开红票时，原发票代码，发票号码不存在
	public static final String ERRORCODE_DATA_MXXX_FP_DMHM_ISISSUED = "9264";// 红票被重复红冲
	public static final String ERRORCODE_DATA_MXXX_FP_REDZJE_ERROR = "9265";// 红票的价税合计金额和蓝票不一致

	public static final String ERRORCODE_DATA_FPT_DDH_ISNULL = "9266";// DDH为空
	public static final String ERRORCODE_DATA_FPT_GHFMC_LENGTHTOOLONG = "9267";// 购方名称长度过长
	public static final String ERRORCODE_DATA_FPT_GHFDZ_LENGTHTOOLONG = "9268";// 购方地址长度过长

	public static final String ERRORCODE_DATA_MXXX_XMBM_ISNULL = "9300";// 商品编码为空
	public static final String ERRORCODE_DATA_MXXX_XMBM_LENGTHTOOLONG = "9301";// 商品编码长度超出范围
	public static final String ERRORCODE_DATA_MXXX_XMMC_LENGTHTOOLONG = "9302";// 项目名称长度超出范围
	public static final String ERRORCODE_DATA_MXXX_XMDW_LENGTHTOOLONG = "9303";// 项目单位长度超出范围
	public static final String ERRORCODE_DATA_MXXX_GGXH_LENGTHTOOLONG = "9304";// 项目规格型号超出范围
	public static final String ERRORCODE_DATA_MXXX_XMSL_OUTOFRANGE = "9305";// 项目数量超出范围
	public static final String ERRORCODE_DATA_MXXX_HSBZ_ISNULL = "9306";// 含税标志为空
	public static final String ERRORCODE_DATA_MXXX_HSBZ_UNDEFINEFIELD = "9307";// 含税标志值未定义内容
	public static final String ERRORCODE_DATA_MXXX_HSBZ_LENGTHTOOLONG = "9308";// 含税标志字段长度超出范围
	public static final String ERRORCODE_DATA_MXXX_XMDJ_ISNULL = "9309";// 项目单价为空
	public static final String ERRORCODE_DATA_MXXX_XMDJ_OUTOFRANGE = "9310";// 项目单价字段长度超出范围
	public static final String ERRORCODE_DATA_MXXX_XMJE_ISNULL = "9311";// 项目金额为空
	public static final String ERRORCODE_DATA_MXXX_XMJE_OUTOFRANGE = "9312";// 项目金额字段长度超出范围
	public static final String ERRORCODE_DATA_MXXX_SL_ISNULL = "9313";// 税率为空
	public static final String ERRORCODE_DATA_MXXX_SL_OUTOFRANGE = "9314";// 税率超出范围
	public static final String ERRORCODE_DATA_MXXX_SE_OUTOFRANGE = "9315";// 税额超出范围
	public static final String ERRORCODE_DATA_MXXX_XMMC_ISNULL = "9316";// 项目名称为空

	public static final String ERRORCODE_KPLX_CONSTRAINTERROR = "9317";// 不含税金额为负开票类型必须为负
	public static final String ERRORCODE_DATA_MXXX_FPHXZ_ISNULL = "9318";// 发票行性质为空
	public static final String ERRORCODE_DATA_MXXX_FPHXZ_LENGTHTOOLONG = "9319";// 发票行性质长度超出范围
	public static final String ERRORCODE_DATA_MXXX_FPHXZ_UNDEFINEFIELD = "9320";// 发票行性质内容未定义
	public static final String ERRORCODE_DATA_MXXX_YHZCBS_ISNULL = "9321";// 优惠政策标识为空
	public static final String ERRORCODE_DATA_MXXX_YHZCBS_LENGTHTOOLONG = "9322";// 优惠政策标识长度超出范围
	public static final String ERRORCODE_DATA_MXXX_YHZCBS_UNDEFINEFIELD = "9323";// 优惠政策标识内容未定义
	public static final String ERRORCODE_DATA_MXXX_LSLBS_LENGTHTOOLONG = "9324";// 零税率标识长度超出范围
	public static final String ERRORCODE_DATA_MXXX_LSLBS_UNDEFINEFIELD = "9325";// 零税率标识内容未定义
	public static final String ERRORCODE_DATA_MXXX_ZZSTSGL_ISNULL = "9326";// 增值税特殊管理为空
	public static final String ERRORCODE_DATA_MXXX_ZZSTSGL_LENGTHTOOLONG = "9327";// 增值税特殊管理长度超出范围
	public static final String EXVALUEOUTOFMAXVALUE = "9328";// 不含税金额超出最大值
	public static final String ERRORCODE_DATA_MXXX_XMJE_ERROR = "9329";// 项目金额不等于项目数量乘以项目单价
	public static final String ERRORCODE_DATA_MXXX_XMSL_ISNULL = "9330";// 项目数量不能为空
	public static final String ERRORCODE_DATA_ZKH_ERROR = "9332";// 折扣行要紧挨被折扣行之后
	public static final String ERRORCODE_DATA_ZKHMC_ERROR = "9333";// 折扣行的商品名称、商品编码要与被折行相同
	public static final String ERRORCODE_DATA_CHZS_BLUE_XY0 = "9334";// 差额征税蓝字发票时，扣除额不能小于0
	public static final String ERRORCODE_DATA_CHZS_BLUE_ZKH_ERROR = "9335";// 差额征税蓝字发票时，商品行数错误
	public static final String ERRORCODE_DATA_CEZS_BLUE_DYXMJE = "9336";// 差额征税蓝字发票时，扣除额不能大于不含税金额
	public static final String ERRORCODE_DATA_CEZS_RED_ZKH_ERROR = "9337";// 差额征税红字发票时，商品行不能大于1行
	public static final String ERRORCODE_DATA_CEZS_RED_XYXMJE = "9338";// 差额征税红字发票时，扣除额不能小于不含税金额
	public static final String ERRORCODE_DATA_CEZS_RED_DY0 = "9339";// 差额征税红字发票时，扣除额不能大于0
	public static final String ERRORCODE_DATA_MXXX_XMDJXY0 = "9341";// 项目单价不能小于0
	public static final String ERRORCODE_DATA_MXXX_ZKHJE_DY0 = "9342";// 折扣行金额不能大于0
	public static final String ERRORCODE_DATA_MXXX_IS_KCE_0 = "9343";// 该税号无权开差额票
	public static final String ERRORCODE_DATA_MXXX_FP_SPBM_BEGIN_ERROR = "9346";// 商品编码首位必须是数字
	public static final String ERRORCODE_DATA_MXXX_YHZCBS_SL_NOTZERO = "9347";// 零税率标识不为空时，税率要为0
	public static final String ERRORCODE_DATA_MXXX_ZZSTSGL_ERROR = "9348";// 零税率标识内容与增值税特殊管理内容不相符
	public static final String ERRORCODE_DATA_MXXX_YHZCBS_ERROR = "9349";// 零税率标识内容为1或2，增值税特殊管理内容为免税或不征税时，优惠政策标识必须为1

	// 发票下载，pdf下载
	public static final String ERRORCODE_DATA_FPXX_FPQQLSH_ISNULL = "9400";// 发票请求流水号为空
	public static final String ERRORCODE_DATA_FPXX_FPQQLSH_NOTEXIST = "9401";// 开票处理中
	public static final String ERRORCODE_DATA_FPXX_DDH_ISNULL = "9402";// 订单号为空
	public static final String ERRORCODE_DATA_FPXX_DDH_LENGTHTOOLONG = "9403";// 订单号长度超出范围
	public static final String ERRORCODE_DATA_FPXX_PDF_XZFS_ISNULL = "9404";// 下载方式为空
	public static final String ERRORCODE_DATA_FPXX_PDF_XZFS_LENGTHTOOLONG = "9405";// 下载方式长度超出范围
	public static final String ERRORCODE_DATA_FPXX_PDF_XZFS_UNDEFINEFIELD = "9406";// 下载方式内容未定义
	public static final String ERRORCODE_DATA_FPXX_PDF_NSRSBH_ERROR = "9407";// 纳税人识别号错误
	public static final String ERRORCODE_DATA_FPXX_PDF_DSPTBM_ERROR = "9408";// 电商平台编码错误
	// 邮件推送
	public static final String ERRORCODE_DATA_EMAIL_TSFS_HEAD = "9500";// 推送方式头信息不能为空
	public static final String ERRORCODE_DATA_EMAIL_TSFS_CONTENT = "9501";// 推送方式主体内容不能为空
	public static final String ERRORCODE_DATA_EMAIL_NAME_ISNULL = "9502";// 节点NAME的值不能为空
	public static final String ERRORCODE_DATA_EMAIL_TSFS_ISNULL = "9503";// 推送方式不能为空
	public static final String ERRORCODE_DATA_EMAIL_TSFS_ERROR = "9504";// 目前只支持邮箱推送，不支持其它方式
	public static final String ERRORCODE_DATA_EMAIL_ADRESS_ISNULL = "9505";// 邮箱地址不能为空
	public static final String ERRORCODE_DATA_EMAIL_ADRESS_ERROR = "9506";// 邮箱地址格式不正确
	public static final String ERRORCODE_DATA_EMAIL_TSFS_CONTENT_ERROR = "9507";// 推送方式主体内容错误
	public static final String ERRORCODE_DATA_FPXX_HEAD_ISNULL = "9508";// 发票信息头信息不能为空
	public static final String ERRORCODE_DATA_FPXX_CONTENT_ISNULL = "9509";// 发票信息主体信息不能为空
	public static final String ERRORCODE_DATA_FPQQLSH_ISNULL = "9510";// 发票请求流水号不能为空
	public static final String ERRORCODE_DATA_NSRSBH_ISNULL = "9511";// 纳税人识别号不能为空
	public static final String ERRORCODE_DATA_FP_DM_ISNULL = "9512";// 发票代码不能为空
	public static final String ERRORCODE_DATA_FP_HM_ISNULL = "9513";// 发票号码不能为空
	public static final String ERRORCODE_DATA_FPXX_CONTENT_ERROR = "9514";// 发票信息主体内容错误
	public static final String ERRORCODE_DATA_FPXX_NOMATCH = "9515";// 传入的发票流水号，纳税人识别号，发票代码，发票号码有误，没有找到对应的发票
	public static final String ERRORCODE_DATA_EMAIL_DAO = "9516";// 数据库操作异常

	// 发票明细下载
	public static final String ERRORCODE_DATA_FPMXXZ_FPQQLSH_ISNULL = "9600";// 发票请求流水号为空
	public static final String ERRORCODE_DATA_FPMXXZ_FPQQLSH_NOTEXIST = "9601";// 发票请求流水号不存在
	public static final String ERRORCODE_DATA_FPMXXZ_FPQQLSH_PROCESSING = "9602";// 开票处理中
	public static final String ERRORCODE_DATA_FPMXXZ_FPQQLSH_ILLEGALLENGTH = "9603";// 发票请求流水号长度非法
	public static final String ERRORCODE_DATA_FPMXXZ_DSPTBM_ISNULL = "9604";// 电商平台编码为空
	public static final String ERRORCODE_DATA_FPMXXZ_DSPTBM_LENGTHTOOLONG = "9605";// 平台编码长度超出范围
	public static final String ERRORCODE_DATA_FPMXXZ_DDH_ISNULL = "9606";// 订单号为空
	public static final String ERRORCODE_DATA_FPMXXZ_DDH_LENGTHTOOLONG = "9607";// 订单号长度超出范围
	public static final String ERRORCODE_DATA_FPMXXZ_DDH_ISWRONG = "9608";// 订单号和发票请求流水号不匹配
	public static final String ERRORCODE_DATA_FPMXXZ_NSRSBH_ISNULL = "9609";// 开票方识别号为空
	public static final String ERRORCODE_DATA_FPMXXZ_NSRSBH_LENGTHTOOLONG = "9610";// 开票方识别号长度超出范围
	public static final String ERRORCODE_DATA_FPMXXZ_PDF_XZFS_ISNULL = "9611";// 发票下载方式不能为空
	public static final String ERRORCODE_DATA_FPMXXZ_PDF_XZFS_LENGTHTOOLONG = "9612";// 发票下载方式长度超出范围
	public static final String ERRORCODE_DATA_FPMXXZ_PDF_XZFS_UNDEFINEFIELD = "9613";// 发票下载方式字段值非法
	public static final String ERRORCODE_DATA_FPMXXZ_NSRSBH_MISMATCH = "9614";// 报文头与报文内容纳税人识别号不一致
	public static final String ERRORCODE_DATA_FPMXXZ_DSPTBM_MISMATCH = "9615";// 报文头与报文内容平台编码不一致

	// 订单明细查询
	public static final String ERRORCODE_DATA_DDCX_NSRSBH_ISNULL = "9700";// 纳税人识别号为空
	public static final String ERRORCODE_DATA_DDCX_NSRSBH_ILLEGALLENGTH = "9701";// 纳税人识别号长度非法
	public static final String ERRORCODE_DATA_DDCX_NSRSBH_NOTEXIST = "9702";// 纳税人识别号不存在
	public static final String ERRORCODE_DATA_DDCX_NSRSBH_MISMATCH = "9703";// 纳税人识别号上下文不匹配
	public static final String ERRORCODE_DATA_DDCX_FJH_ISNULL = "9704";// 金税盘分机号为空
	public static final String ERRORCODE_DATA_DDCX_FJH_ILLEGALLENGTH = "9705";// 金税盘分机号长度非法
	public static final String ERRORCODE_DATA_DDCX_FJH_ILLEGALLBIND = "9706";// 当前纳税人识别号下错误的金税盘分机号

	// 发票上传
	public static final String ERRORCODE_DATA_FPSC_NULL = "9801";// 上传发票的信息为空
	public static final String ERRORCODE_DATA_FPSC_FPQQLSH_NULL = "9802";// 上传发票的流水号为空
	public static final String ERRORCODE_DATA_FPSC_NSRSBH_NULL = "9803";// 上传发票的纳税人识别号为空
	public static final String ERRORCODE_DATA_FPSC_FJH_NULL = "9804";// 上传发票的分机号为空
	public static final String ERRORCODE_DATA_FPSC_JQBH_NULL = "9805";// 上传发票的机器编号为空
	public static final String ERRORCODE_DATA_FPSC_FPDM_NULL = "9806";// 上传发票的发票代码为空
	public static final String ERRORCODE_DATA_FPSC_FPHM_NULL = "9807";// 上传发票的发票号码为空
	public static final String ERRORCODE_DATA_FPSC_FPZLDM_NULL = "9808";// 上传发票的发票种类代码为空
	public static final String ERRORCODE_DATA_FPSC_KPRQ_NULL = "9809";// 上传发票的开票日期为空
	public static final String ERRORCODE_DATA_FPSC_FPMW_NULL = "9810";// 上传发票的发票明文为空
	public static final String ERRORCODE_DATA_FPSC_JYM_NULL = "9811";// 上传发票的校验码为空
	public static final String ERRORCODE_DATA_FPSC_JSHJ_NULL = "9812";// 上传发票的价税合计金额为空
	public static final String ERRORCODE_DATA_FPSC_HJJE_NULL = "9813";// 上传发票的合计金额为空
	public static final String ERRORCODE_DATA_FPSC_HJSE_NULL = "9814";// 上传发票的合计金额为空
	public static final String ERRORCODE_DATA_FPSC_FPQQLSH_LENGTH_ERROR = "9815";// 上传发票的发票请求流水号长度错误
	public static final String ERRORCODE_DATA_FPSC_NSRSBH_LENGTH_ERROR = "9816";// 上传发票的纳税人识别号长度错误
	public static final String ERRORCODE_DATA_FPSC_FPDM_LENGTH_ERROR = "9817";// 上传发票的发票代码长度错误
	public static final String ERRORCODE_DATA_FPSC_FPHM_LENGTH_ERROR = "9818";// 上传发票的发票号码长度错误

	// 企业开通
	public static final String ERRORCODE_DATA_QYKT_NULL = "8001";// 企业开通信息为空
	public static final String ERRORCODE_DATA_QYKT_YWDH_NULL = "8002";// 业务单号为空
	public static final String ERRORCODE_DATA_QYKT_YWDH_LENGTH_ERROR = "8003";// 业务单号长度错误
	public static final String ERRORCODE_DATA_QYKT_NSRMC_NULL = "8004";// 纳税人名称为空
	public static final String ERRORCODE_DATA_QYKT_NSRMC_LENGTH_ERROR = "8005";// 纳税人名称长度错误
	public static final String ERRORCODE_DATA_QYKT_NSRSBH_NULL = "8006";// 纳税人识别号为空
	public static final String ERRORCODE_DATA_QYKT_NSRSBH_LENGTH_ERROR = "8007";// 纳税人识别号长度错误
	public static final String ERRORCODE_DATA_QYKT_NSRDZ_NULL = "8008";// 纳税人地址为空
	public static final String ERRORCODE_DATA_QYKT_NSRDZ_LENGTH_ERROR = "8009";// 纳税人地址长度错误
	public static final String ERRORCODE_DATA_QYKT_LXR_NULL = "8010";// 联系人为空
	public static final String ERRORCODE_DATA_QYKT_LXR_LENGTH_ERROR = "8011";// 联系人长度错误
	public static final String ERRORCODE_DATA_QYKT_LXRDH_NULL = "8012";// 联系人地址为空
	public static final String ERRORCODE_DATA_QYKT_LXRDH_LENGTH_ERROR = "8013";// 联系人地址长度错误
	public static final String ERRORCODE_DATA_QYKT_NSRSBH_EXIST = "8014";// 该税号已经存在
	public static final String ERRORCODE_DATA_QYKT_YWDH_EXIST = "8015";// 该业务单号已经存在

	// 获取企业开票抬头信息
	public static final String ERRORCODE_DATA_KPTTXX_URL_ISNULL = "8100";// 二维码的URL为空
	public static final String ERRORCODE_DATA_KPTTXX_URL_LENGTHTOOLONG = "8101";// 二维码的URL长度过长
	public static final String ERRORCODE_DATA_KPTTXX_URL_ILLEGALFORMAT = "8102";// 二维码的URL格式非法
	public static final String ERRORCODE_DATA_KPTTXX_URL_INVOKEERROR = "8103";// 调用诺诺平台发生异常

	// 获取企业可用发票数量
	public static final String ERRORCODE_DATA_KYFPSL_NSRSBH_ISNULL = "8200";// 纳税人识别号为空
	public static final String ERRORCODE_DATA_KYFPSL_NSRSBH_LENGTH_ERROR = "8201";// 纳税人识别号长度错误
	public static final String ERRORCODE_DATA_KYFPSL_NSRSBH_MISMATCH = "8202";// 纳税人识别号上下文不匹配
	
	public static final String BKPXMBH = "9999";
	public static final String ERRORNUMERICAL = "9998";// 数值类型中含有特殊字符
}
