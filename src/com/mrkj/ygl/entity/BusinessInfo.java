package com.mrkj.ygl.entity;

public enum BusinessInfo {

	TABLENAME("bus_info"),
	
	ID("info_id"),
	
	�û���("USERNAME"),
	
	����("FENSHU"),
	
	���("main_id"),
	
	insert("insert into bus_info (`info_id`,`�û���`,`����`,`main_id`) VALUES (?,?,?,?) "),
	
	select��ҳ("select main.`main_id`,main.`����`,main.`����ʱ��`,main.`����ʱ��`,main.`sub_id`,info.`����` from bus_main as main RIGHT JOIN bus_info as info on main.main_id = info.main_id WHERE info.`�û���`=? ORDER BY `����ʱ��` LIMIT ?,?"),
	
	selectCount("select count(`info_id`) as count from bus_info where `�û���`= ?"),;
	
	
	private String �ֶ�;
	
	private BusinessInfo(String field){
		this.�ֶ� = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.�ֶ�;
	}
}
