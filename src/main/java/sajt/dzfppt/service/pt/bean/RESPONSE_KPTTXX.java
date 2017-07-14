package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

/**
 * 获取企业开票抬头
 * @author MicroCat
 * @since  2017-0324
 *
 */
@SuppressWarnings("serial")
public class RESPONSE_KPTTXX implements Serializable {

	private String QYYH;
	private String YHZH;
	private String KPDZ;
	private String KPDH;
	private String KPMC;
	private String KPSH;
	private String KPDM;

	public String getQYYH() {
		return QYYH;
	}

	public void setQYYH(String qYYH) {
		QYYH = qYYH;
	}

	public String getYHZH() {
		return YHZH;
	}

	public void setYHZH(String yHZH) {
		YHZH = yHZH;
	}

	public String getKPDZ() {
		return KPDZ;
	}

	public void setKPDZ(String kPDZ) {
		KPDZ = kPDZ;
	}

	public String getKPDH() {
		return KPDH;
	}

	public void setKPDH(String kPDH) {
		KPDH = kPDH;
	}

	public String getKPMC() {
		return KPMC;
	}

	public void setKPMC(String kPMC) {
		KPMC = kPMC;
	}

	public String getKPSH() {
		return KPSH;
	}

	public void setKPSH(String kPSH) {
		KPSH = kPSH;
	}

	public String getKPDM() {
		return KPDM;
	}

	public void setKPDM(String kPDM) {
		KPDM = kPDM;
	}

}
