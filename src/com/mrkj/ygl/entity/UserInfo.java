package com.mrkj.ygl.entity;

/**
 * 
 * @author yuguoliang
 *
 *	1��MySql���ݿ��ں����ʱ����Ѿ�֧�������ֶΣ�����һ���ܲ���ĸ��������ǲ�û����Ը��ʹ�ã�������˲�����ʹ�ã�������Щ����Ҫ���鷳������û��˵������鷳�����������
 *
 *	2���Ҿ�������д��������ɴ��SQL���Ҳд�������ˡ�
 *	
 */
public enum UserInfo {

	TABLENAME("sys_userinfo"),
	
	ID("id"),
	
	�û���("USERNAME"),
	
	����("PASSWORD"),
	
	����("NAME"),
	
	ְ��("JOB"),

	SELECTBY�û���("select `id`,`�û���`,`����`,`����`,`ְ��` from `sys_userinfo` where `�û���` = ?"),
	
	INSERT("insert into `sys_userinfo` (`id`,`�û���`,`����`,`����`,`ְ��`) values (?,?,?,?,?);");
	
	
	private String �ֶ�;
	
	private UserInfo (String field){
		this.�ֶ� = field;
	}

	/**
	 * @return the �ֶ�
	 */
	public String get�ֶ�() {
		return �ֶ�;
	}

	/**
	 * @param �ֶ� the �ֶ� to set
	 */
	public void set�ֶ�(String �ֶ�) {
		this.�ֶ� = �ֶ�;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.�ֶ�;
	}
}
