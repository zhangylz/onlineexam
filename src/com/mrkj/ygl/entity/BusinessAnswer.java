package com.mrkj.ygl.entity;

public enum BusinessAnswer {

	TABLENAME("bus_answer"),
	
	ID("ans_id"),
	
	������("DAAN"),
	
	����ʱ��("CHUANGJIAN"),
	
	���("que_id"),
	
	selectByMainId("SELECT answer.ans_id,answer.`������`,answer.`����ʱ��`,answer.que_id FROM bus_main AS main LEFT JOIN bus_question AS question on main.main_id=question.main_id LEFT JOIN bus_answer AS answer ON question.que_id=answer.que_id WHERE main.main_id = ?");
	
	private String �ֶ�;
	
	private BusinessAnswer(String field){
		this.�ֶ� = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.�ֶ�;
	}
}