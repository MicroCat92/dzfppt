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
public class DDCX_XMXX implements Serializable {

	private String XH;		//明细行号
	private String XMBM;	//项目编码
	private String XMMC;	//项目名称
	private String XMDW;	//项目单位
	private String GGXH;	//规格型号
	private String XMSL;	//项目数量
	private String XMDJ;	//项目单价
	private String XMJE;	//项目金额
	private String SL;		//税率
	private String SE;		//税额
	private String FPHXZ;	//发票行性质
	private String SWBM;	//税务编码
	private String ZXBM;	//自行编码
	private String YHZCBS;	//优惠政策标识
	private String LSLBS;	//零税率标识
	private String ZZSTSGL;	//增值税特殊管理

	public String getXH() {
		return XH;
	}

	public void setXH(String xH) {
		XH = xH;
	}

	public String getXMBM() {
		return XMBM;
	}

	public void setXMBM(String xMBM) {
		XMBM = xMBM;
	}

	public String getXMMC() {
		return XMMC;
	}

	public void setXMMC(String xMMC) {
		XMMC = xMMC;
	}

	public String getXMDW() {
		return XMDW;
	}

	public void setXMDW(String xMDW) {
		XMDW = xMDW;
	}

	public String getGGXH() {
		return GGXH;
	}

	public void setGGXH(String gGXH) {
		GGXH = gGXH;
	}

	public String getXMSL() {
		return XMSL;
	}

	public void setXMSL(String xMSL) {
		XMSL = xMSL;
	}

	public String getXMDJ() {
		return XMDJ;
	}

	public void setXMDJ(String xMDJ) {
		XMDJ = xMDJ;
	}

	public String getXMJE() {
		return XMJE;
	}

	public void setXMJE(String xMJE) {
		XMJE = xMJE;
	}

	public String getSL() {
		return SL;
	}

	public void setSL(String sL) {
		SL = sL;
	}

	public String getSE() {
		return SE;
	}

	public void setSE(String sE) {
		SE = sE;
	}

	public String getFPHXZ() {
		return FPHXZ;
	}

	public void setFPHXZ(String fPHXZ) {
		FPHXZ = fPHXZ;
	}

	public String getSWBM() {
		return SWBM;
	}

	public void setSWBM(String sWBM) {
		SWBM = sWBM;
	}

	public String getZXBM() {
		return ZXBM;
	}

	public void setZXBM(String zXBM) {
		ZXBM = zXBM;
	}

	public String getYHZCBS() {
		return YHZCBS;
	}

	public void setYHZCBS(String yHZCBS) {
		YHZCBS = yHZCBS;
	}

	public String getLSLBS() {
		return LSLBS;
	}

	public void setLSLBS(String lSLBS) {
		LSLBS = lSLBS;
	}

	public String getZZSTSGL() {
		return ZZSTSGL;
	}

	public void setZZSTSGL(String zZSTSGL) {
		ZZSTSGL = zZSTSGL;
	}

}
