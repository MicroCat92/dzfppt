package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FPKJXX_DDXX implements Serializable{
	private String DDH;
	private String DDDATE;
	private String THDH;
	
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
	public String getTHDH() {
		return THDH;
	}
	public void setTHDH(String tHDH) {
		THDH = tHDH;
	}
}
