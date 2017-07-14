package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RESPONSE_FPMXXZ implements Serializable {

	private String FPQQLSH;
	private String DDH;
	private String KPLSH;
	private String FPZL_DM;
	private String FP_DM;
	private String FP_HM;
	private String KPRQ;
	private String KPLX;
	private String HJBHSJE;
	private String KPHJSE;
	private String KPHJJE;// micro add 20170518
	private String PDF_FILE;
	private String PDF_URL;
	private String KCE;
	private FPMXXZ_XMXX[] FPMXXZ_XMXXS;
	private String RETURNCODE;
	private String RETURNMESSAGE;

	private String GHFMC;
	private String GHF_NSRSBH;
	private String GHF_DZDH;
	private String GHF_YHZH;
	private String GHF_SJ;
	private String GHF_EMAIL;

	// micro add 20170518
	private String XHF_NSRSBH;
	private String XHFMC;
	private String XHF_DZDH;
//	private String XHF_DH;
	private String XHF_YHZH;
	private String KPY;
	private String SKY;
	private String FHR;
	private String BZ;
	private String YFP_DM;
	private String YFP_HM;

	private String FPMW;// 发票密文 micro 2017-03-14
	private String JYM;// 校验码
	private String JQBH;// 机器编号

	public String getKPHJJE() {
		return KPHJJE;
	}

	public void setKPHJJE(String kPHJJE) {
		KPHJJE = kPHJJE;
	}

	public String getXHF_NSRSBH() {
		return XHF_NSRSBH;
	}

	public void setXHF_NSRSBH(String xHF_NSRSBH) {
		XHF_NSRSBH = xHF_NSRSBH;
	}

	public String getXHFMC() {
		return XHFMC;
	}

	public void setXHFMC(String xHFMC) {
		XHFMC = xHFMC;
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

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bZ) {
		BZ = bZ;
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

	public String getGHFMC() {
		return GHFMC;
	}

	public void setGHFMC(String gHFMC) {
		GHFMC = gHFMC;
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

	public String getGHF_SJ() {
		return GHF_SJ;
	}

	public void setGHF_SJ(String gHF_SJ) {
		GHF_SJ = gHF_SJ;
	}

	public String getGHF_EMAIL() {
		return GHF_EMAIL;
	}

	public void setGHF_EMAIL(String gHF_EMAIL) {
		GHF_EMAIL = gHF_EMAIL;
	}

	public String getJQBH() {
		return JQBH;
	}

	public void setJQBH(String jQBH) {
		JQBH = jQBH;
	}

	public String getJYM() {
		return JYM;
	}

	public void setJYM(String jYM) {
		JYM = jYM;
	}

	public String getFPMW() {
		return FPMW;
	}

	public void setFPMW(String fPMW) {
		FPMW = fPMW;
	}

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

	public String getKPLSH() {
		return KPLSH;
	}

	public void setKPLSH(String kPLSH) {
		KPLSH = kPLSH;
	}

	public String getFPZL_DM() {
		return FPZL_DM;
	}

	public void setFPZL_DM(String fPZL_DM) {
		FPZL_DM = fPZL_DM;
	}

	public String getFP_DM() {
		return FP_DM;
	}

	public void setFP_DM(String fP_DM) {
		FP_DM = fP_DM;
	}

	public String getFP_HM() {
		return FP_HM;
	}

	public void setFP_HM(String fP_HM) {
		FP_HM = fP_HM;
	}

	public String getKPRQ() {
		return KPRQ;
	}

	public void setKPRQ(String kPRQ) {
		KPRQ = kPRQ;
	}

	public String getKPLX() {
		return KPLX;
	}

	public void setKPLX(String kPLX) {
		KPLX = kPLX;
	}

	public String getHJBHSJE() {
		return HJBHSJE;
	}

	public void setHJBHSJE(String hJBHSJE) {
		HJBHSJE = hJBHSJE;
	}

	public String getKPHJSE() {
		return KPHJSE;
	}

	public void setKPHJSE(String kPHJSE) {
		KPHJSE = kPHJSE;
	}

	public String getPDF_FILE() {
		return PDF_FILE;
	}

	public void setPDF_FILE(String pDF_FILE) {
		PDF_FILE = pDF_FILE;
	}

	public String getPDF_URL() {
		return PDF_URL;
	}

	public void setPDF_URL(String pDF_URL) {
		PDF_URL = pDF_URL;
	}

	public String getKCE() {
		return KCE;
	}

	public void setKCE(String kCE) {
		KCE = kCE;
	}

	public FPMXXZ_XMXX[] getFPMXXZ_XMXXS() {
		return FPMXXZ_XMXXS;
	}

	public void setFPMXXZ_XMXXS(FPMXXZ_XMXX[] fPMXXZ_XMXXS) {
		FPMXXZ_XMXXS = fPMXXZ_XMXXS;
	}

	public String getRETURNCODE() {
		return RETURNCODE;
	}

	public void setRETURNCODE(String rETURNCODE) {
		RETURNCODE = rETURNCODE;
	}

	public String getRETURNMESSAGE() {
		return RETURNMESSAGE;
	}

	public void setRETURNMESSAGE(String rETURNMESSAGE) {
		RETURNMESSAGE = rETURNMESSAGE;
	}

}
