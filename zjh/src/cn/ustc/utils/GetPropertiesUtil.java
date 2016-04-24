package cn.ustc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置文件
 * @author liu
 *
 */
public class GetPropertiesUtil {
	/**
	 * 获取Properties对象，通过调用该对象的get方法，可以传入key值，获取config.properties文件对应的内容
	 * @return
	 */
	public static Properties getProperties(){
		InputStream in = new GetPropertiesUtil().getClass().getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
