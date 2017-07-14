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
public class RESPONSE_DDCX implements Serializable {

	private DDCX_ZTXX DDCX_ZTXX;	//
	private DDCX_XMXX[] DDCX_XMXXS; //项目信息

	public DDCX_ZTXX getDDCX_ZTXX() {
		return DDCX_ZTXX;
	}

	public void setDDCX_ZTXX(DDCX_ZTXX dDCX_ZTXX) {
		DDCX_ZTXX = dDCX_ZTXX;
	}

	public DDCX_XMXX[] getDDCX_XMXXS() {
		return DDCX_XMXXS;
	}

	public void setDDCX_XMXXS(DDCX_XMXX[] dDCX_XMXXS) {
		DDCX_XMXXS = dDCX_XMXXS;
	}

}
