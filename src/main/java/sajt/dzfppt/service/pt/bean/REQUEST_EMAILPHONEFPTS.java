package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

/**
 * 1.3.6 邮箱/手机发票推送接口 接口说明：将受票方(购货方)的邮箱和手机号推送给电子发票服务平台，平台可通过邮箱/手机APP把发票信息推送给受票方。
 * 调用方式：WEB SERVICE方式 接口编码：ECXML.EMAILPHONEFPTS.TS.E.INV
 * 
 * @author Micro
 *
 */
@SuppressWarnings("serial")
public class REQUEST_EMAILPHONEFPTS implements Serializable {

	private TSFSXX TSFSXX;// 推送方式信息
	private FPXX[] FPXXS;// 发票信息

	public REQUEST_EMAILPHONEFPTS() {}

//	public REQUEST_EMAILPHONEFPTS(ECInvoice inv) {
//		TSFSXX = new TSFSXX(inv);
//		FPXXS = new FPXX[1];
//		FPXXS[0] = new FPXX(inv);
//	}

	public TSFSXX getTSFSXX() {
		return TSFSXX;
	}

	public void setTSFSXX(TSFSXX tSFSXX) {
		TSFSXX = tSFSXX;
	}

	public FPXX[] getFPXXS() {
		return FPXXS;
	}

	public void setFPXXS(FPXX[] fPXXS) {
		FPXXS = fPXXS;
	}

}
