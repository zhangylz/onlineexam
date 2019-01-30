package com.mrkj.ygl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.mrkj.ygl.Connection.DaoConnection;
import com.mrkj.ygl.entity.BusinessAnswer;
import com.mrkj.ygl.entity.BusinessInfo;
import com.mrkj.ygl.entity.BusinessMain;
import com.mrkj.ygl.entity.BusinessQuestion;
import com.mrkj.ygl.entity.BusinessSubject;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 *
 *
 */

public class BusinessService {

	public static List<Map<String, String>> getSubPage (Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement sp = dc.getԤ����ִ��(BusinessSubject.select��ҳ.toString(), page,row);
			ResultSet rs = sp.executeQuery();
			if (rs != null){
				while (rs.next()){
					Map<String, String> reaultMap = new HashMap<String, String>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					reaultMap.put(BusinessSubject.ID.toString(), rs.getString(1));
					reaultMap.put(BusinessSubject.��Ŀ.toString(),rs.getString(2));
					Long sqlDateLong = rs.getTimestamp(3).getTime();//rs.getDate(3).getTime();
					reaultMap.put(BusinessSubject.����ʱ��.toString(), sdf.format(new Date(sqlDateLong)));
					resultListMap.add(reaultMap);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultListMap;
	}
	
	public static List<Map<String, String>> getAllSubPage (){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement sp = dc.getԤ����ִ��(BusinessSubject.selectAll.toString());
			ResultSet rs = sp.executeQuery();
			if (rs != null){
				while (rs.next()){
					Map<String, String> reaultMap = new HashMap<String, String>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					reaultMap.put(BusinessSubject.ID.toString(), rs.getString(1));
					String kskmAndJk = rs.getString(2);
					String[] kskmJks = kskmAndJk.split("-");
					if (kskmJks.length==2){
						reaultMap.put(BusinessSubject.��Ŀ.toString(),kskmJks[0]);
						reaultMap.put("jiankao",kskmJks[1]);
					}else{
						reaultMap.put(BusinessSubject.��Ŀ.toString(),"δ֪");
						reaultMap.put("jiankao","δ֪");
					}
					Long sqlDateLong = rs.getTimestamp(3).getTime();//rs.getDate(3).getTime();
					reaultMap.put(BusinessSubject.����ʱ��.toString(), sdf.format(new Date(sqlDateLong)));
					resultListMap.add(reaultMap);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultListMap;
	}
	
	public static Long getSubCount (){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessSubject.selectCount.toString());
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static int insert (String ��Ŀ){
		int resultInt = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			String sub_id = UUID.randomUUID().toString();
			PreparedStatement ps = dc.getԤ����ִ��(BusinessSubject.insert.toString(),sub_id,��Ŀ);
			resultInt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultInt;
	}

	public static int deleteById (String sub_id){
		int resultInt = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessSubject.deleteById.toString(), sub_id);
			resultInt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultInt;
	}
	
	public static int updateById (String ��Ŀ,String sub_id){
		int resultInt = 0;
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessSubject.update��ĿById.toString(),��Ŀ ,sub_id);
			resultInt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultInt;
	}
	/***************************************************�Ծ�༭***************************************************/
	
	public static List<Map<String, String>> getQuestion (Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessQuestion.select��ҳ.toString(), page,row);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> resultMap = new HashMap<>();
				String que_id = rs.getString(1);
				String ������� = rs.getString(2);
				String �������� = rs.getString(3);
				Long timestamp = rs.getTimestamp(4).getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String ����ʱ�� = sdf.format(new Date(timestamp));
				String ���� = rs.getInt(6)+"";
				String ��� = rs.getString(7);
				resultMap.put(BusinessQuestion.ID.toString(), que_id);
				resultMap.put(BusinessQuestion.�������.toString(), �������);
				resultMap.put(BusinessQuestion.��������.toString(), ��������);
				resultMap.put(BusinessQuestion.����ʱ��.toString(),����ʱ��);
				resultMap.put(BusinessQuestion.����.toString(),����);
				resultMap.put(BusinessQuestion.���.toString(),���);
				resultListMap.add(resultMap);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultListMap;
	}
	
	public static Long getQueCount (){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessQuestion.selectCount.toString());
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 
	 * @param �������
	 * @param ��������
	 * @param ����
	 * @param �𰸱���
	 * @param ���
	 * @param daanMap
	 * @return
	 */
	public static int InsertQuestionAndAnswers (String �������,String ��������,String ����,int �𰸱���,String ���,Map<String,String> daanMap){
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			String questionSQL = BusinessQuestion.insert.toString();
			
			Connection connection = dc.getJDBConnect();
			
			Statement statement = connection.createStatement();
			String que_id = UUID.randomUUID().toString();
			statement.addBatch("insert into bus_question (`que_id`,`�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,main_id) "
					+ "VALUES ('"+que_id+"','"+�������+"','"+��������+"',NOW(),"+�𰸱���+","+����+",'"+���+"')");
			Set<Entry<String, String>> daanEntry = daanMap.entrySet();
			for (Entry<String, String> entry : daanEntry){
				statement.addBatch("INSERT into bus_answer (`ans_id`,`������`,`����ʱ��`,`que_id`) VALUES ('"+entry.getKey()+"','"+entry.getValue()+"',NOW(),'"+que_id+"')");
			}
			result = statement.executeBatch()[0];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/***************************************************�Ծ�����service***************************************************/
	public static List<Map<String, String>> getMain (Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessMain.select��ҳ.toString(), page,row);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> resultMap = new HashMap<>();
				String main_id = rs.getString(1);
				String ���� = rs.getString(2);
				long timestamp = rs.getTimestamp(3).getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String ����ʱ�� = sdf.format(new Date(timestamp));
				long ����ʱ�� = rs.getLong(4);
				String ��� = rs.getString(5);
				String ���value = rs.getString(6);
				resultMap.put(BusinessMain.ID.toString(), main_id);
				resultMap.put(BusinessMain.����.toString(),����);
				resultMap.put(BusinessMain.����ʱ��.toString(),����ʱ��);
				resultMap.put(BusinessMain.����ʱ��.toString(),����ʱ��+"");
				resultMap.put(BusinessMain.���.toString(),���);
				resultMap.put(BusinessMain.���value.toString(), ���value);
				resultListMap.add(resultMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultListMap;
	}
	
	public static Long getMainCount (){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessMain.selectCount.toString());
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static int insertMain (String ID,String ����,String ���,String ����ʱ��){
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessMain.insert.toString(),ID,����,����ʱ��,���);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int updatetMain (String ����,String ����ʱ��,String ���,String ID){
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessMain.updateById.toString(),����,����ʱ��,���,ID);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * ����sub_id��ȡ��Ŀ�������Ծ�
	 * @param sub_id
	 * @return
	 */
	public static List<Map<String,String>> getMainBySubject (String sub_id){
		List<Map<String,String>> mains = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			//SELECT `main_id`,`����`,`����ʱ��`,`����ʱ��`,`sub_id` FROM bus_main where `sub_id` = ?
			PreparedStatement ps = dc.getԤ����ִ��(BusinessMain.selectBySub_id.toString(),sub_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> entity = new HashMap<>();
				String main_id = rs.getString(1);
				String ���� = rs.getString(2);
				entity.put(BusinessMain.ID.toString(), main_id);
				entity.put(BusinessMain.����.toString(), ����);
				mains.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mains;
	}
	/************************************************Action*************************************************************/
	/**
	 * ���� main_id ��ȡһ���������Ծ�
	 * @param parmMain_id
	 * @return
	 */
	public static Map<String,Object> getAllQuestion (String parmMain_id){
		
		//SELECT `main_id`,`����`,`����ʱ��`,`����ʱ��`,`sub_id` FROM bus_main where `main_id` = ?
		String selectMainSQL = BusinessMain.selectById.toString();
		/*
		 *select `que_id`,`�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,`main_id` 
		 *from bus_question where `main_id`=? ORDER BY `����ʱ��`
		 */
		String selectQuestionSQL = BusinessQuestion.selectByMainId.toString();
		/*
		 * SELECT answer.ans_id,answer.`������`,answer.`����ʱ��`,answer.que_id FROM bus_main AS main 
		 * LEFT JOIN bus_question AS question on main.main_id=question.main_id 
		 * LEFT JOIN bus_answer AS answer ON question.que_id=answer.que_id WHERE main.main_id = ?
		 */
		String selectAnswerSQL =  BusinessAnswer.selectByMainId.toString();
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		PreparedStatement ps;
		ResultSet rs;
		Map<String,Object> resultMainMap = new HashMap<>();
		List<Map<String,String>> resultQuestionsMaps = new ArrayList<>();
		List<Map<String,String>> resultAnswerMaps = new ArrayList<>();
		try {
			//selectById ("SELECT `main_id`,`����`,`����ʱ��`,`����ʱ��`,`sub_id` FROM bus_main where main_id = ?");
			ps = dc.getԤ����ִ��(selectMainSQL,parmMain_id);
			rs = ps.executeQuery();
			rs.next();
			
			String main_id = rs.getString(1);
			String ���� = rs.getString(2);
			Long ����ʱ�� = rs.getTimestamp(3).getTime();
			String ����ʱ�� = rs.getString(4);
			String sub_id = rs.getString(5);
			resultMainMap.put("main_id", main_id);
			resultMainMap.put(BusinessMain.����.toString(), ����);
			resultMainMap.put(BusinessMain.����ʱ��.toString(), MrksUtils.TrasformGetimeToString(����ʱ��));
			resultMainMap.put(BusinessMain.����ʱ�� .toString(), ����ʱ��);
			resultMainMap.put("sub_id",sub_id);     //main ���ݷ�װ���
			
			//select `que_id`,`�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,`main_id` from bus_question where `main_id`=? ORDER BY `����ʱ��`
			ps = dc.getԤ����ִ��(selectQuestionSQL,parmMain_id);
			rs = ps.executeQuery();
			
			while (rs.next()){
				Map<String,String> question = new HashMap<>();
			
				String que_id = rs.getString(1);
				String ������� = rs.getString(2);
				String �������� = rs.getString(3);
				����ʱ�� = rs.getTimestamp(4).getTime();
				String �𰸱��� = rs.getInt(5)+"";
				String ���� = rs.getInt(6)+"";
				main_id = rs.getString(7);
				question.put("que_id", que_id);
				question.put(BusinessQuestion.�������.toString(),�������);
				question.put(BusinessQuestion.��������.toString(), ��������);
				question.put(BusinessQuestion.����ʱ��.toString(), MrksUtils.TrasformGetimeToString(����ʱ��));
				question.put(BusinessQuestion.�𰸱���.toString(),�𰸱���);
				question.put(BusinessQuestion.����.toString(),����);
				question.put("main_id",main_id);
				resultQuestionsMaps.add(question);
			}
			resultMainMap.put("questions", resultQuestionsMaps);
			//SELECT answer.ans_id,answer.`������`,answer.`����ʱ��`,answer.que_id FROM bus_main AS main LEFT JOIN bus_question AS question on main.main_id=question.main_id LEFT JOIN bus_answer AS answer ON question.que_id=answer.que_id WHERE main.main_id = ?
			ps = dc.getԤ����ִ��(selectAnswerSQL,parmMain_id);
			rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> answer = new HashMap<>();
				
				String ans_id = rs.getString(1);
				String ������ = rs.getString(2);
				����ʱ�� = rs.getTimestamp(3).getTime();
				String que_id = rs.getString(4);
				answer.put("ans_id", ans_id);
				answer.put(BusinessAnswer.������.toString(), ������);
				answer.put(BusinessAnswer.����ʱ��.toString(), MrksUtils.TrasformGetimeToString(����ʱ��));
				answer.put("que_id", que_id);
				
				resultAnswerMaps.add(answer);
			}
			resultMainMap.put("answers", resultAnswerMaps);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultMainMap;
	}
	
	/**
	 * �������
	 * @param parmMap
	 * @return
	 */
	public static int countScore (Map<String,String[]> parmMap,String username){
		
		int result = 0;
		String main_id = parmMap.get("main_id")[0];
		DaoConnection dc = DaoConnection.initDaoConnection();
		Set<Entry<String, String[]>> entrySet = parmMap.entrySet();
		int count = 0;
		for(Entry<String, String[]> entry : entrySet){
			if ("action".equals(entry.getKey())){
				break ;
			}
			String que_id = entry.getKey();
			String[] values = entry.getValue();
			try {
				//"select `�������`,`��������`,`����ʱ��`,`�𰸱���`,`����`,`main_id` from bus_question where `que_id`=?"
				PreparedStatement ps = dc.getԤ����ִ��(BusinessQuestion.selectById.toString(), que_id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()){
					Integer �𰸱��� = rs.getInt(4);
					Integer ���� = rs.getInt(5);
					for(Object o:values){
						System.out.println("values="+o);
					}
					
					int ת���𰸱��� = MrksUtils.statistics(values);
					if (ת���𰸱��� == �𰸱���){
						count+=����;
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//"insert into bus_info (`info_id`,`�û���`,`����`,`main_id`) VALUES (?,?,?,?) "
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessInfo.insert.toString(),UUID.randomUUID().toString(),username,count,main_id);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<Map<String, String>> getKsfs (String username,Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			
			PreparedStatement ps = dc.getԤ����ִ��(BusinessInfo.select��ҳ.toString(),username,page,row);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> resultMap = new HashMap<>();
				String main_id = rs.getString(1);
				String ���� = rs.getString(2);
				long timestamp = rs.getTimestamp(3).getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String ����ʱ�� = sdf.format(new Date(timestamp));
				long ����ʱ�� = rs.getLong(4);
				String ���� = rs.getString(6);
				resultMap.put(BusinessMain.ID.toString(), main_id);
				resultMap.put(BusinessMain.����.toString(),����);
				resultMap.put(BusinessMain.����ʱ��.toString(),����ʱ��);
				resultMap.put(BusinessMain.����ʱ��.toString(),sdf.format(new Date(����ʱ��)));
				resultMap.put(BusinessInfo.����.toString(),����);
				resultListMap.add(resultMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultListMap;
	}
	
	public static Long getInfoCount (String username){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.getԤ����ִ��(BusinessInfo.selectCount.toString(),username);
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
}
