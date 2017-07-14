package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

/**
 * 获取企业可用发票数量
 * 
 * @author MicroCat
 *
 */
@SuppressWarnings("serial")
public class RESPONSE_KYFPSL implements Serializable {

	private String NSRSBH;
	private String KYFPSL;// 可用发票数量
	private String TJSJ;// 统计时间

	public String getNSRSBH() {
		return NSRSBH;
	}

	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}

	public String getKYFPSL() {
		return KYFPSL;
	}

	public void setKYFPSL(String kYFPSL) {
		KYFPSL = kYFPSL;
	}

	public String getTJSJ() {
		return TJSJ;
	}

	public void setTJSJ(String tJSJ) {
		TJSJ = tJSJ;
	}

}
