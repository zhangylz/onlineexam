package com.mrkj.ygl.thread;

import java.io.IOException;
import java.util.Date;

import javax.websocket.Session;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * The beauty of the code
 * 
 * @author Administrator
 *
 *	���˼��
 *
 *1��ÿ�������ߣ�����ͬһʱ����⣬�޷�ͳһ��ʱ������������Ҫʹ�ö��߳�Ϊÿ�������߿���һ���̼߳�ʱ��
 *
 *2�����๹��ʱ����WebSocket Session���ݽ�������ʱ�������ǻ�ʹ�������ͻ�������״̬
 *
 */
public class Timekeeping implements Runnable {

	private Session session;
	
	//Ŀ��ʱ��
	private Long target;
	
	private Long time;
	
	//����
	public Timekeeping(Long target,Session session){
		this.session = session;
		this.target = target;
	}
	
	@Override
	public void run() {
		try {
			//������Ϣ���ͻ��ˣ����߿ͻ��˵�ǰʱ�䣬��Ŀ��ʱ�䣬�������ǲ�ʹ�ÿͻ�������ʱ�䣬��ʱ��׼���շ�������ʱ��
			String resultStart = "{\"state\":\"start\"}";
			this.session.getBasicRemote().sendText(resultStart);
			while (true) {
				
				Thread.sleep(500);
				
				Date current = new Date();
				Long currentLong = current.getTime();
				ObjectMapper om = new ObjectMapper();
				
				if (currentLong >= this.target){
					//Map<String,String> resultMap = new HashMap<>();
					//resultMap.put("state", "online");
					//resultMap.put("message", "end");
					//om.writeValueAsString(resultMap);
					//��JS���ؽ���״̬
					if (session.isOpen()){
						String resultEnd = "{\"state\":\"end\",\"message:\":\"end\"}";
						session.getBasicRemote().sendText(resultEnd);
						//����ѭ�������߳�
					}
					break;	
				}else{
					if (session.isOpen()){
						String resultEnd = "{\"state\":\"time\",\"endtime\":"+this.target+",\"currentime\":"+currentLong+"}";
						session.getBasicRemote().sendText(resultEnd);
					}else {
						break;	
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    public void sendMessage(String message) throws IOException{
        //��ͻ���������Ϣ�������ʱ��ϻ���ͻ�������һ��״̬�����߿ͻ���ʱ�䵽�ˣ���Ҫ�ύ�Ծ�
    	this.session.getBasicRemote().sendText(message);
        
    }
	
}
