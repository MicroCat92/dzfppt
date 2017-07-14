package sajt.dzfppt.service;

import java.util.Map;

/**
 * 接口策略
 * @author MicroCat
 *
 */
public interface IStrategy {

	public String invoke(Map<String, Object> map);
	
}
