package com.mrkj.ygl.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.mrkj.ygl.entity.UserInfo;
import com.mrkj.ygl.service.SystemService;
import com.mrkj.ygl.util.MrksUtils;

public class TestMedth {

	@Test
	public void test() {
		System.out.println(UserInfo.����);
		System.out.println(UserInfo.SELECTBY�û���.toString());
	}

	@Test
	public void testStstemService(){
		
		SystemService ss = new SystemService();
		
		Map<String, Object> ����� = ss.selectUserInfoBy�û���("username");
		Map<String, Object> �����1 = ss.selectUserInfoBy�û���("username");
	
		System.out.println(�����);
	}
	
	@Test
	public void testTimekeeping (){
		Long timekeep = 1000L*60L*50L;
		Date current = new Date();
		
		Long currentLong = current.getTime();
		
		Long target = currentLong+timekeep;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String s = sdf.format(new Date(target));
		
		System.out.println(s);
	}
	
	@Test
	public void  testUUIDSp (){
		
		String s = UUID.randomUUID().toString();
		String[] sArr = s.split("-");
		for (int i = 0 ; i < sArr.length ;i++)
		System.out.println(sArr[i]);
		
		
		System.out.println();
		
	}
	
	@Test
	public void testS(){
		
		int result = MrksUtils.statistics(UUID.randomUUID().toString(),UUID.randomUUID().toString(),UUID.randomUUID().toString());
		
		System.out.println(result);
	}
	
	
}
