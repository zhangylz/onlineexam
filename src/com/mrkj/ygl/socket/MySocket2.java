package com.mrkj.ygl.socket;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.mrkj.ygl.base.BaseContext;
import com.mrkj.ygl.entity.BusinessMain;
import com.mrkj.ygl.entity.UserInfo;
import com.mrkj.ygl.thread.Timekeeping;

@ServerEndpoint(value = "/mysocketTest",configurator=BaseContext.class)
public class MySocket2 {
    
	private static final long serialVersionUID = 79990006013872453L;
	
	//��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
    private static int onlineCount = 0;
    
    //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
    private static CopyOnWriteArraySet<MySocket2> webSocketSet = new CopyOnWriteArraySet<MySocket2>();
    public static java.util.concurrent.ConcurrentHashMap<String , String> useronline = new java.util.concurrent.ConcurrentHashMap<String , String>();
    //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
    private Session session;
    private ServletContext context = null;
    private HttpSession httpSession = null;
    /**
     * ���ӽ����ɹ����õķ���
     * @param session  ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
     * @throws IOException 
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) throws IOException{
    	//WebSocket Session
        this.session = session;
        webSocketSet.add(this);
        //Servlet Application context
        context = (ServletContext)config.getUserProperties().get(ServletContext.class.getName());
        //Servlet Session
        httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        //��ȡSession����û���Ϣ
        Map<String,Object> userInfoMap = (Map<String,Object>)httpSession.getAttribute("userInof");
        //�ѵ�ǰ�û��ĵĵ����Ӷ������Context
        String �û��� = (String)userInfoMap.get(UserInfo.�û���.toString());
        context.setAttribute(�û���, this);
       
    }
    /**
     * ���ӹرյ��õķ���
     */
    @OnClose
    public void onClose(){
        //�Ͽ����ӣ�����Ͽ�����������Ҫ���û��������ݳ־û�
    	//1�����context������Զ���
    	//2������״̬�¼������
    	//3��
    	try {
    		context.removeAttribute((String)httpSession.getAttribute(UserInfo.�û���.toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /**
     * �յ��ͻ�����Ϣ����õķ���
     * @param message �ͻ��˷��͹�������Ϣ
     * @param session ��ѡ�Ĳ���
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //�û�����ʱ��ÿ�λش�һ���ⶼ�ᷢ��һ����Ϣ�������ͨ�ţ���������û�����������Ϣ��
        if (message != null){
        	switch (message) {
        	//case 1 ���ⲽ��ʼ������ʽ��ʼ��
			case "start":
				//httpSession�д���˵�ǰ�Ծ��ʱ��
				String time = (String)httpSession.getAttribute(BusinessMain.����ʱ��.toString());
				//���Ӽ��� ������һ��Сʱ�����ǵ������ӳ٣���������3�룬JS��ʱ��������
				Long targetMinute = 1000L*60L*Long.parseLong(time);//1000L*60L*60L+3000L;
				//��ȡ��ǰʱ�䣬�õ�ǰʱ��+��Ԥ�ڷ��ӣ��õ����Խ���ʱ��
				Date curretn = new Date();
				
				Long target = curretn.getTime()+targetMinute;
				
				//��ʼ���� ������ʱ��ʼʱ������ͻ��˷���һ��start״̬��ǣ��ͻ���ʹ��js��ʱҲͬ����ʼ��
				//�������ԣ���ʱ����������ͻ��˷���һ��end״̬��iaoji���ͻ���ʹ��js�ύ�������һ�ο��ԡ�

				Timekeeping timekeeping = new Timekeeping(target,session);
				
				Thread thread = new Thread(timekeeping);
				//�����߳�
				thread.start();
				break;
			//case 2 ����Ԥ��Ч�����û�ÿ��ѡ��𰸶��������ڴ浱��
			case "question":
			//case 3 �û��ر�����������������������һ�����ݣ���������־û���ִ���	
			case "close":
				System.out.println("�ر�����");
				onClose();
			default:
					break;
			}
        }
    }
    /**
     * ��������ʱ����
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        //���������쳣ʱ
    	
    }
    /**
     * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ��ӵķ�����
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        //��ͻ���������Ϣ�������ʱ��ϻ���ͻ�������һ��״̬�����߿ͻ���ʱ�䵽�ˣ���Ҫ�ύ�Ծ�
    	this.session.getBasicRemote().sendText(message);
        
    }
}
