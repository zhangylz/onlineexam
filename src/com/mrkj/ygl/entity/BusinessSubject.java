package com.mrkj.ygl.entity;

public enum BusinessSubject {

	TABLENAME("bus_subject"),
	
	ID("sub_id"),
	
	��Ŀ("KEMU"),
	
	����ʱ��("CHUANGJIAN"),
	
	select��ҳ("select `sub_id`,`��Ŀ`,`����ʱ��` from bus_subject ORDER BY `����ʱ��` LIMIT ?,?"),
	
	selectAll("select `sub_id`,`��Ŀ`,`����ʱ��` from bus_subject ORDER BY `����ʱ��`"),
	
	selectCount("select count(`sub_id`) as count from bus_subject"),
	
	insert("insert into bus_subject (`sub_id`,`��Ŀ`,`����ʱ��`) VALUES (?,?,NOW())"),
	
	deleteById("delete FROM bus_subject where sub_id = ?"),
	
	update��ĿById("UPDATE bus_subject set `��Ŀ` = ? where `sub_id` = ?");
	
	
	private String �ֶ�;
	
	private BusinessSubject(String field){
		this.�ֶ� = field;
	}
	
	@Override
	public String toString() {
		return this.�ֶ�;
	}
}
