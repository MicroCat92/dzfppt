package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.GenerateXml;

import java.util.Properties;

/**
 * @author Administrator
 * 西部CA配置文件路径和参数
 */
public final class CaConstant {

	private final static Logger LOGGER = LoggerFactory.getLogger(CaConstant.class);

	public final static String DEFAULT_CHARSET = "UTF-8";
	private static Properties properties;
	private static String PATH;

	static {
		try {
			LOGGER.info("CaConstant 初始化参数");
			System.out.println("CaConstant 初始化参数");
			PATH = CaConstant.class.getClassLoader().getResource("/").toURI().getPath();
			properties = new Properties();
			properties.load(CaConstant.class.getResourceAsStream("/pkcs7.properties"));
		} catch (Exception e) {
			LOGGER.error("pkcs7接口初始化系统参数失败!", e.fillInStackTrace());
		}
	}

	public static String getPath() {
		return CaConstant.PATH;
	}
	/**
	 * 读取配置文件里key的值
	 * 
	 * @param key
	 *            配置文件里的key
	 * @return
	 * @throws java.io.IOException
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
