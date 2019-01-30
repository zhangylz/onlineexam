package com.mrkj.ygl.entity;

public enum BusinessQuestion {

	TABLENAME("bus_question"),
	
	ID("que_id"),
	
	�������("WENTI"),
	
	��������("LEIXING"),
	
	����ʱ��("CHUANGJIAN"),
	
	�𰸱���("BIANMA"),
	
	����("FENSHU"),
	
	���("main_id"),
	
	select��ҳ("select `que_id`,`�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,`main_id` from bus_question ORDER BY `����ʱ��` LIMIT ?,?"),
	
	selectCount("select count(`que_id`) as count from bus_question"),
	
	insert("insert into bus_question (`que_id`,`�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,main_id) VALUES (?,?,?,NOW(),?,?,?)"),
	
	selectByMainId("select `que_id`,`�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,`main_id` from bus_question where `main_id`=? ORDER BY `����ʱ��`"),
	
	selectById("select `�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,`main_id` from bus_question where `que_id`=?");
	
	private String �ֶ�;
	
	private BusinessQuestion (String field){
		this.�ֶ� = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.�ֶ�;
	}
}
