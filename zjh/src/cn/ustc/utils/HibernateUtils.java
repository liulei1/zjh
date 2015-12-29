package cn.ustc.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * hibernate 工具类
 * 
 * @author liu
 * 
 */
public class HibernateUtils {
	private static Configuration configuration;
	private static SessionFactory sessionFactory;

	// 只对SessionFactory初始化一次, 只有一个实例
	static {
		// 实例化配置对象，加载配置文件 hibernate.cfg.xml
		configuration = new Configuration().configure();
		// 创建会话连接工厂
		sessionFactory = configuration.buildSessionFactory();
	}

	public static Session openSession() {
		// 创建会话
		Session session = sessionFactory.openSession();
		return session;

	}
	
	/**
	 * 获取当前session
	 * @return
	 */
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}