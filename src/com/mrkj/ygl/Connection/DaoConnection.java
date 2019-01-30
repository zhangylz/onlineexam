package com.mrkj.ygl.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 *         单例模式，初始化与数据的连接.
 */
public class DaoConnection {

	private static DaoConnection dc;

	private Connection JDBConnect;

	private static final String dataBasePath = "jdbc:mysql://127.0.0.1:3306/mrks?characterEncoding=utf-8&serverTimezone=UTC"; //"jdbc:mysql://127.0.0.1:3306/mrks";
	private static final String dataBaseUser = "root";
	private static final String dataBasePassword = "123456";
	private static final String dataBaseDriver = "com.mysql.cj.jdbc.Driver"; // old version "com.mysql.jdbc.Driver"

	// 私有构造函数
	private DaoConnection() {
	}

	// 匿名内部类加载驱动类
	{
		try {
			Class.forName(dataBaseDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 初始化
	public static DaoConnection initDaoConnection() {
		if (dc == null) {
			dc = new DaoConnection();
		}
		return dc;
	}

	/**
	 * @return the jDBC连接
	 * @throws SQLException
	 */
	public Connection getJDBConnect() throws SQLException {
		JDBConnect = DriverManager.getConnection(dataBasePath, dataBaseUser, dataBasePassword);
		return JDBConnect;
	}

	public PreparedStatement get预处理执行(String sql, Object... args) throws SQLException {

		DaoConnection dc = initDaoConnection();

		PreparedStatement ps = dc.getJDBConnect().prepareStatement(sql);
		int i = 1;
		if (args != null & args.length > 0) {
			for (Object arg : args) {
				ps.setObject(i, arg);
				i++;
			}
		}
		return ps;
	}

}