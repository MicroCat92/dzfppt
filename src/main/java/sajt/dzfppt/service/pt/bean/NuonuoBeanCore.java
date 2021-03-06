package sajt.dzfppt.service.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 获取企业开票抬头信息--诺诺平台返回json bean
 * 
 * @author MicroCat
 * @since 2017-03-24
 */
@SuppressWarnings("serial")
public class NuonuoBeanCore implements Serializable {

	private String code;
	private String describe;
	private NuonuoBeanSub result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public NuonuoBeanSub getResult() {
		return result;
	}

	public void setResult(NuonuoBeanSub result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "NuonuoBeanCore [code=" + code + ", describe=" + describe + ", result=" + result + "]";
	}

}
