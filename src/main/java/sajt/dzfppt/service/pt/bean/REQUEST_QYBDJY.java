package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;
/**
 * 企业绑定校验接口
 * 
 * @author MicroCat
 * @since  2017-02-15
 */
@SuppressWarnings("serial")
public class REQUEST_QYBDJY implements Serializable {
	
	private String NSRMC;//纳税人名称
	private String NSRSBH;//纳税人识别号

	public String getNSRMC() {
		return NSRMC;
	}

	public void setNSRMC(String nSRMC) {
		NSRMC = nSRMC;
	}

	public String getNSRSBH() {
		return NSRSBH;
	}

	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}

}
