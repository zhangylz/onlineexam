package com.mrkj.ygl.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mrkj.ygl.Connection.DaoConnection;
import com.mrkj.ygl.entity.UserInfo;

/**
 * The beauty of the code
 * 
 * @author Administrator
 *
 *	1��ϵͳ����㴦��ϵͳ����Ĳ��������½��ע�ᣬȨ�޵ȵ�
 *
 *	2����������ʹ�õ�JDK7��JDBC4.1��׼���ñ�׼��Connection��ResultSet��Statement��ʵ����Closeable��ڣ�������try-with-resources����е��ã��Ϳ����Զ��ر���Դ��
 */
public class SystemService{

	public Map<String,Object> selectUserInfoBy�û��� (String �û���){
		
		Map<String,Object> ���ؽ���� = new HashMap<String, Object>();
		
		try {
			DaoConnection dc = DaoConnection.initDaoConnection();
			PreparedStatement Ԥ����ִ�� = dc.getԤ����ִ��(UserInfo.SELECTBY�û���.toString(),�û���);
			ResultSet ���ݿ����� = Ԥ����ִ��.executeQuery();
			while (���ݿ�����.next()){
				���ؽ����.put("id", ���ݿ�����.getString(1));
				���ؽ����.put(UserInfo.�û���.toString(), ���ݿ�����.getString(2));
				���ؽ����.put(UserInfo.����.toString(), ���ݿ�����.getString(3));
				���ؽ����.put(UserInfo.����.toString(), ���ݿ�����.getString(4));
				���ؽ����.put(UserInfo.ְ��.toString(), ���ݿ�����.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ���ؽ����;
	}
	
	public int insert (String id,String �û���,String ����,String ����,String ְ��){
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		int resultInt = 0;
		try {
			PreparedStatement Ԥ����ִ�� = dc.getԤ����ִ��(UserInfo.INSERT.toString(),id,�û���,����,����,ְ��);
			resultInt = Ԥ����ִ��.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultInt;
	}

	
}
