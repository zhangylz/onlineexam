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
 *         ����ģʽ����ʼ�������ݵ�����.
 */
public class DaoConnection {

	private static DaoConnection dc;

	private Connection JDBConnect;

	private static final String dataBasePath = "jdbc:mysql://127.0.0.1:3306/mrks?characterEncoding=utf-8&serverTimezone=UTC"; //"jdbc:mysql://127.0.0.1:3306/mrks";
	private static final String dataBaseUser = "root";
	private static final String dataBasePassword = "123456";
	private static final String dataBaseDriver = "com.mysql.cj.jdbc.Driver"; // old version "com.mysql.jdbc.Driver"

	// ˽�й��캯��
	private DaoConnection() {
	}

	// �����ڲ������������
	{
		try {
			Class.forName(dataBaseDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ��ʼ��
	public static DaoConnection initDaoConnection() {
		if (dc == null) {
			dc = new DaoConnection();
		}
		return dc;
	}

	/**
	 * @return the jDBC����
	 * @throws SQLException
	 */
	public Connection getJDBConnect() throws SQLException {
		JDBConnect = DriverManager.getConnection(dataBasePath, dataBaseUser, dataBasePassword);
		return JDBConnect;
	}

	public PreparedStatement getԤ����ִ��(String sql, Object... args) throws SQLException {

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