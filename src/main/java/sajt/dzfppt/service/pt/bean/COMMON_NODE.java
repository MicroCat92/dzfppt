package sajt.dzfppt.service.pt.bean;

public class COMMON_NODE {

	private String NAME;
	private String VALUE;

	public COMMON_NODE() {
	}

	// FPQQ
	public COMMON_NODE(int i) {
		if (i == 0) {
			this.NAME = "TSFS";
			this.VALUE = "0";
		} else {
			if (i == 1) {
				this.NAME = "SJ";
				this.VALUE = "";
			} else {
				if (i == 2) {
					this.NAME = "EMAIL";
					this.VALUE = "";
				}
			}
		}
	}

	// TSFSXX & FPXX
//	public COMMON_NODE(ECInvoice inv, String lableName, int i) {
//		if ("TSFSXX".equals(lableName)) {
//			if (i == 0) {
//				this.NAME = "TSFS";
//				this.VALUE = "0";
//			} else {
//				if (i == 1) {
//					this.NAME = "SJ";
//					this.VALUE = inv.getCustTel();
//				} else {
//					if (i == 2) {
//						this.NAME = "EMAIL";
//						this.VALUE = inv.getCustMail();
//					}
//				}
//			}
//		} else if ("FPXX".equals(lableName)) {
//			if (i == 0) {
//				this.NAME = "FPQQLSH";
//				this.VALUE = inv.getFpqqlsh();
//			} else if (i == 1) {
//				this.NAME = "NSRSBH";
//				this.VALUE = inv.getCustTaxNr();
//			} else if (i == 2) {
//				this.NAME = "FP_DM";
//				this.VALUE = inv.getInvoiceCode();
//			} else if (i == 3) {
//				this.NAME = "FP_HM";
//				this.VALUE = inv.getInvoiceNr();
//			}
//		}
//
//	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
}
