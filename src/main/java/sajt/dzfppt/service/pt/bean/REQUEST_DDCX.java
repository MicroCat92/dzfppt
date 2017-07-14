package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

/**
 * 订单明细查询
 * 
 * @author MicroCat
 * @since 2017-01-23
 */
@SuppressWarnings("serial")
public class REQUEST_DDCX implements Serializable {

	private String NSRSBH; // 纳税人识别号
	private String FJH; // 分机号

	public String getNSRSBH() {
		return NSRSBH;
	}

	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}

	public String getFJH() {
		return FJH;
	}

	public void setFJH(String fJH) {
		FJH = fJH;
	}

}
