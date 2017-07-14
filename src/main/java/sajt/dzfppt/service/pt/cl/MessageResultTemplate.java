package sajt.dzfppt.service.pt.cl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sajt.dzfppt.pojo.ResultData;
import sajt.dzfppt.util.JsonUtil;

public class MessageResultTemplate {

	public static String resultPanel(ResultData resultData, String returnCode, String returnMessage, String content) {
		resultData.setReturnCode(returnCode);
		resultData.setReturnMessage(returnMessage);
		resultData.setContent(content);
//		return JsonUtil.toJson(resultData);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(resultData);
	}
	
	public static String resultPanel(String returnCode, String returnMessage, String content) {
		ResultData resultData = new ResultData();
		resultData.setReturnCode(returnCode);
		resultData.setReturnMessage(returnMessage);
		resultData.setContent(content);
//		return JsonUtil.toJson(resultData);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(resultData);
	}
	
	public static String resultPanel(String returnCode, String returnMessage) {
		ResultData resultData = new ResultData();
		resultData.setReturnCode(returnCode);
		resultData.setReturnMessage(returnMessage);
//		return JsonUtil.toJson(resultData);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(resultData);
	}
	
}
