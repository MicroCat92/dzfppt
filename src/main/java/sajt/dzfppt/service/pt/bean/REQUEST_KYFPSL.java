package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;
/**
 * 获取企业可用发票数量
 * @author MicroCat
 *
 */
@SuppressWarnings("serial")
public class REQUEST_KYFPSL implements Serializable {

	private String NSRSBH;

	public String getNSRSBH() {
		return NSRSBH;
	}

	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}

}
