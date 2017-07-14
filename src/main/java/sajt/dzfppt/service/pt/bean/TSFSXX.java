package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TSFSXX implements Serializable {

	private COMMON_NODE[] COMMON_NODES;

	public TSFSXX() {}

//	public TSFSXX(ECInvoice inv) {
//		int size = 3;
//		COMMON_NODES = new COMMON_NODE[size];
//		for (int i = 0; i < size; i++) {
//			COMMON_NODES[i] = new COMMON_NODE(inv, "TSFSXX", i);
//		}
//	}

	public COMMON_NODE[] getCOMMON_NODES() {
		return COMMON_NODES;
	}

	public void setCOMMON_NODES(COMMON_NODE[] cOMMON_NODES) {
		COMMON_NODES = cOMMON_NODES;
	}

}
