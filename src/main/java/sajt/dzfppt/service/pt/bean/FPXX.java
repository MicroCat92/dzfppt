package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FPXX implements Serializable {

	private COMMON_NODE[] COMMON_NODES;

	public FPXX() {}

//	public FPXX(ECInvoice inv) {
//		int size = 4;
//		COMMON_NODES = new COMMON_NODE[size];
//		for (int i = 0; i < 4; i++) {
//			COMMON_NODES[i] = new COMMON_NODE(inv, "FPXX", i);
//		}
//	}

	public COMMON_NODE[] getCOMMON_NODES() {
		return COMMON_NODES;
	}

	public void setCOMMON_NODES(COMMON_NODE[] cOMMON_NODES) {
		COMMON_NODES = cOMMON_NODES;
	}

}
