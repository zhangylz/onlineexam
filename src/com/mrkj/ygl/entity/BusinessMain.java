package com.mrkj.ygl.entity;

public enum BusinessMain {

	TABLENAME("bus_main"),
	
	ID("main_id"),
	
	����("BIAOTI"),
	
	����ʱ��("CHUANGJIAN"),
	
	����ʱ��("DATISHIJIAN"),
	
	���("SUB_ID"),
	
	���value("SUB_VALUE"),
	
	select��ҳ("select main.`main_id`,main.`����`,main.`����ʱ��`,main.`����ʱ��`,main.`sub_id`,subject.`��Ŀ` from bus_main as main LEFT JOIN bus_subject as subject on main.sub_id = subject.sub_id ORDER BY `����ʱ��` LIMIT ?,?"),
	
	selectCount("select count(`main_id`) as count from bus_main"),
	
	insert ("insert into bus_main (`main_id`,`����`,`����ʱ��`,`����ʱ��`,`sub_id`) values (?,?,NOW(),?,?)"),
	
	updateById ("UPDATE bus_main SET `����` = ? ,`����ʱ��` = ? , `sub_id` = ? WHERE `main_id` = ?"),
	
	selectById ("SELECT `main_id`,`����`,`����ʱ��`,`����ʱ��`,`sub_id` FROM bus_main where `main_id` = ?"),
	
	selectBySub_id ("SELECT `main_id`,`����`,`����ʱ��`,`����ʱ��`,`sub_id` FROM bus_main where `sub_id` = ?");
	
	private String �ֶ�;
	
	private BusinessMain (String field){
		this.�ֶ� = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.�ֶ�;
	}
}
