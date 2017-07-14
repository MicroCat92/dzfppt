package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class REQUEST_FPKJXX implements Serializable{
	private FPKJXX_FPTXX FPKJXX_FPTXX;
	private FPKJXX_XMXX[] FPKJXX_XMXXS;
	private FPKJXX_DDXX FPKJXX_DDXX;
    
    public REQUEST_FPKJXX(){}

	public FPKJXX_FPTXX getFPKJXX_FPTXX() {
		return FPKJXX_FPTXX;
	}
	public void setFPKJXX_FPTXX(FPKJXX_FPTXX fPKJXX_FPTXX) {
		FPKJXX_FPTXX = fPKJXX_FPTXX;
	}
	public FPKJXX_XMXX[] getFPKJXX_XMXXS() {
		return FPKJXX_XMXXS;
	}
	public void setFPKJXX_XMXXS(FPKJXX_XMXX[] fPKJXX_XMXXS) {
		FPKJXX_XMXXS = fPKJXX_XMXXS;
	}
	public FPKJXX_DDXX getFPKJXX_DDXX() {
		return FPKJXX_DDXX;
	}
	public void setFPKJXX_DDXX(FPKJXX_DDXX fPKJXX_DDXX) {
		FPKJXX_DDXX = fPKJXX_DDXX;
	}
}
