package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

/**
 * 企业绑定校验接口
 * 
 * @author MicroCat
 * @since 2017-02-15
 */
@SuppressWarnings("serial")
public class RESPONSE_QYBDJY implements Serializable {

	private String NSRSBH;// 纳税人识别号
	private String KTZT;// 开通状态

	public String getNSRSBH() {
		return NSRSBH;
	}

	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}

	public String getKTZT() {
		return KTZT;
	}

	public void setKTZT(String kTZT) {
		KTZT = kTZT;
	}

}
