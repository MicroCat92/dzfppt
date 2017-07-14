package sajt.dzfppt.service.pt.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import sajt.dzfppt.exception.InvokeWSException;
import sajt.dzfppt.service.impl.EntranceImpl;

public class HttpClientInvoke {
	
	private final static Logger log = Logger.getLogger(HttpClientInvoke.class);

	static int socketTimeout = 30000;// 请求超时时间
	static int connectTimeout = 30000;// 传输超时时间
	
	private static CloseableHttpClient closeableHttpClient;
	private static HttpPost httpPost;
	
	private static String HTTPCLIENT_HEAD;
	private static String HTTPCLIENT_TAIL;
	private static String WSDL;
	
	static {
		try {
			Properties properties = new Properties();
			String path = EntranceImpl.class.getClassLoader().getResource("/").toURI().getPath();
			System.out.println(path);
			properties.load(new FileInputStream(new File(path + "service.properties")));
			HTTPCLIENT_HEAD = properties.getProperty("HTTPCLIENT_HEAD");
			HTTPCLIENT_TAIL = properties.getProperty("HTTPCLIENT_TAIL");
			WSDL = properties.getProperty("WSDL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("httpclient 接口调用初始化异常 : " + e.getMessage());
			System.exit(0);
		}
	}

	public static String invoke(String xml) throws InvokeWSException {
		String retStr = null;
		try {
			log.info("httpclient 调用 ws");
			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
			HttpPost httpPost = new HttpPost(WSDL);

			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			httpPost.setConfig(requestConfig);

			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setHeader("SOAPAction", "");
			
			xml = HTTPCLIENT_HEAD + xml + HTTPCLIENT_TAIL;//包装报文
			System.out.println("&&&&&&&");
			log.info("XML -- " + xml);
			System.out.println("&&&&&&&");
			
			StringEntity data = new StringEntity(xml, Charset.forName("UTF-8"));
			httpPost.setEntity(data);
			CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				retStr = EntityUtils.toString(httpEntity, "UTF-8");
				retStr = StringEscapeUtils.unescapeXml(retStr);// 反转义
				System.out.println(retStr);
			}
			// 释放资源
			closeableHttpClient.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("调用 ws 异常 : " + e.getMessage());
			throw new InvokeWSException("调用 ws 异常 : " + e.getMessage());
		}
		return retStr;
	}

}
