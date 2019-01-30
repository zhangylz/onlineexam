package com.mrkj.ygl.servlet3.system;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrkj.ygl.entity.UserInfo;
import com.mrkj.ygl.service.SystemService;

/***
 * ���˼��
 * 
 * �� SystemLogin
 * 
 * ���ฺ���û���صĲ�������½��ע�ᡢ�˳�������
 * 
 * ��������ʹ��4������ʽ put��delete��get��post����ÿ�������˾��ְ��
 * 1��get������·��ת��������
 * 2��delete������ɾ������
 * 3��put���������
 * 4��post����������
 * 
 * 
 *
 */

@WebServlet(urlPatterns="/syslogin",asyncSupported=false)
public class SystemLogin extends HttpServlet{

	private static final SystemService ϵͳ����= new SystemService();
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> parmMap = req.getParameterMap();
		for (Entry<String, String[]> entry:parmMap.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue()[0]);
			
		}
	}

	/* 
	 *	
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		if (session.getAttribute(UserInfo.�û���.toString())!=null){
			req.getRequestDispatcher("WEB-INF/view/main.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("login.jsp?msg=1");
		}
	}

	/* The beauty of the code
	 * 
	 * 
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String �û��� = req.getParameter(UserInfo.�û���.toString());
		String ���� = req.getParameter(UserInfo.����.toString());
		
		Map<String, Object> userInfoMap = ϵͳ����.selectUserInfoBy�û���(�û���);
		if (userInfoMap != null){
			String ȡ�������� = (String)userInfoMap.get(UserInfo.����.toString());
			if (����!=null&&����.equals(ȡ��������)){
				req.getSession().setAttribute("userInof", userInfoMap);
				req.getSession().setAttribute(UserInfo.�û���.toString(),userInfoMap.get(UserInfo.�û���.toString()));
				//ת����ͼ
				req.getRequestDispatcher("WEB-INF/view/main.jsp").forward(req, resp);
			}else{
				resp.sendRedirect("login.jsp?msg=1");
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> parmMap = req.getParameterMap();
		for (Entry<String, String[]> entry:parmMap.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue()[0]);
			
		}
	}

	
	
}
