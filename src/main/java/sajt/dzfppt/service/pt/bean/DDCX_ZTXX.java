package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

/**
 * 订单明细查询
 * 
 * @author MicroCat
 * @since 2017-01-23
 *
 */
@SuppressWarnings("serial")
public class DDCX_ZTXX implements Serializable {

	private String FPQQLSH; // 发票请求流水号
	private String DDH; // 订单号
	private String DDDATE; // 订单时间
	private String XHF_NSRSBH; // 销货方识别号
	private String XHF_MC; // 销货方名称
	private String XHF_DZDH; // 销货方地址电话
	private String XHF_YHZH; // 销货方银行账号
	private String GHF_MC; // 购货方名称
	private String GHF_NSRSBH; // 购货方识别号
	private String GHF_DZDH; // 购货方地址电话
	private String GHF_YHZH; // 购货方银行账号
	private String KPY; // 开票员
	private String SKY; // 收款员
	private String FHR; // 复核人
	private String KPLX; // 开票类型
	private String YFP_DM; // 原发票代码
	private String YFP_HM; // 原发票号码
	private String BMB_BBH; // 编码表版本号
	private String KPHJJE; // 价税合计金额
	private String KCE; // 扣除额
	private String BZ; // 备注

	public String getFPQQLSH() {
		return FPQQLSH;
	}

	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}

	public String getDDH() {
		return DDH;
	}

	public void setDDH(String dDH) {
		DDH = dDH;
	}

	public String getDDDATE() {
		return DDDATE;
	}

	public void setDDDATE(String dDDATE) {
		DDDATE = dDDATE;
	}

	public String getXHF_NSRSBH() {
		return XHF_NSRSBH;
	}

	public void setXHF_NSRSBH(String xHF_NSRSBH) {
		XHF_NSRSBH = xHF_NSRSBH;
	}

	public String getXHF_MC() {
		return XHF_MC;
	}

	public void setXHF_MC(String xHF_MC) {
		XHF_MC = xHF_MC;
	}

	public String getXHF_DZDH() {
		return XHF_DZDH;
	}

	public void setXHF_DZDH(String xHF_DZDH) {
		XHF_DZDH = xHF_DZDH;
	}

	public String getXHF_YHZH() {
		return XHF_YHZH;
	}

	public void setXHF_YHZH(String xHF_YHZH) {
		XHF_YHZH = xHF_YHZH;
	}

	public String getGHF_MC() {
		return GHF_MC;
	}

	public void setGHF_MC(String gHF_MC) {
		GHF_MC = gHF_MC;
	}

	public String getGHF_NSRSBH() {
		return GHF_NSRSBH;
	}

	public void setGHF_NSRSBH(String gHF_NSRSBH) {
		GHF_NSRSBH = gHF_NSRSBH;
	}

	public String getGHF_DZDH() {
		return GHF_DZDH;
	}

	public void setGHF_DZDH(String gHF_DZDH) {
		GHF_DZDH = gHF_DZDH;
	}

	public String getGHF_YHZH() {
		return GHF_YHZH;
	}

	public void setGHF_YHZH(String gHF_YHZH) {
		GHF_YHZH = gHF_YHZH;
	}

	public String getKPY() {
		return KPY;
	}

	public void setKPY(String kPY) {
		KPY = kPY;
	}

	public String getSKY() {
		return SKY;
	}

	public void setSKY(String sKY) {
		SKY = sKY;
	}

	public String getFHR() {
		return FHR;
	}

	public void setFHR(String fHR) {
		FHR = fHR;
	}

	public String getKPLX() {
		return KPLX;
	}

	public void setKPLX(String kPLX) {
		KPLX = kPLX;
	}

	public String getYFP_DM() {
		return YFP_DM;
	}

	public void setYFP_DM(String yFP_DM) {
		YFP_DM = yFP_DM;
	}

	public String getYFP_HM() {
		return YFP_HM;
	}

	public void setYFP_HM(String yFP_HM) {
		YFP_HM = yFP_HM;
	}

	public String getBMB_BBH() {
		return BMB_BBH;
	}

	public void setBMB_BBH(String bMB_BBH) {
		BMB_BBH = bMB_BBH;
	}

	public String getKPHJJE() {
		return KPHJJE;
	}

	public void setKPHJJE(String kPHJJE) {
		KPHJJE = kPHJJE;
	}

	public String getKCE() {
		return KCE;
	}

	public void setKCE(String kCE) {
		KCE = kCE;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bZ) {
		BZ = bZ;
	}

}
