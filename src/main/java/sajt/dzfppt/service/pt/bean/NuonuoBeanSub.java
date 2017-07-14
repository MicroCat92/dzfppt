package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;

/**
 * 获取企业开票抬头信息--诺诺平台返回json bean
 * 
 * @author MicroCat
 * @since 2017-03-24
 */
@SuppressWarnings("serial")
public class NuonuoBeanSub implements Serializable {

	private String accountBlank;
	private String bankAccount;
	private String code;
	private String id;
	private String kpAddr;
	private String kpCode;
	private String kpName;
	private String kpTel;

	public String getAccountBlank() {
		return accountBlank;
	}

	public void setAccountBlank(String accountBlank) {
		this.accountBlank = accountBlank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKpAddr() {
		return kpAddr;
	}

	public void setKpAddr(String kpAddr) {
		this.kpAddr = kpAddr;
	}

	public String getKpCode() {
		return kpCode;
	}

	public void setKpCode(String kpCode) {
		this.kpCode = kpCode;
	}

	public String getKpName() {
		return kpName;
	}

	public void setKpName(String kpName) {
		this.kpName = kpName;
	}

	public String getKpTel() {
		return kpTel;
	}

	public void setKpTel(String kpTel) {
		this.kpTel = kpTel;
	}

	@Override
	public String toString() {
		return "NuonuoBean_Result [accountBlank=" + accountBlank + ", bankAccount=" + bankAccount + ", code=" + code
				+ ", id=" + id + ", kpAddr=" + kpAddr + ", kpCode=" + kpCode + ", kpName=" + kpName + ", kpTel=" + kpTel
				+ "]";
	}

}
