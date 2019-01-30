package com.mrkj.ygl.servlet3.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrkj.ygl.entity.BusinessQuestion;
import com.mrkj.ygl.service.BusinessService;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 *���˼��
 *
 *	�Ծ�༭��Ҫ����������һ���������ĸ��𰸣��𰸷ֶ�ѡ��ѡ����Ҫ��ÿ����������ȷ�𰸣��ѵ���������������Ƴ�������Ӧ��ȷ�𰸴������ݿ⣬
 *��һ���ѵ����ڷ������㣬�������û���������μ����û�������
 *
 *�������
 *
 *	����ʹ�ô��𣬴���ʹ����ȷ�𰸵�id�ַ�������ȡÿ���ַ�����ASC����������á�
 *
 */

@WebServlet(urlPatterns="/question",asyncSupported=false)
public class BusinessQuestionServlet extends HttpServlet{

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("que");
		//�����Ծ�༭
		if ("sjbj".equals(action)){
			req.setAttribute("AllSubject", BusinessService.getAllSubPage());
			req.getRequestDispatcher("/editQuestion.jsp").forward(req, resp);
		}else if("getQuestion".equals(action)){
														//easyui datagrid������ݹ����Ĳ���page����ǰҳ��Ĭ��1
			String pageStr = req.getParameter("page");
														//easyui datagrid������ݹ����Ĳ���row����ÿҳ��ʾ�����У���������Ϊ10
			String rowStr = req.getParameter("rows");
														//ת��StringΪInteger,֮�������ﲻ����int����Ϊ��ĳЩ�ض�����²�����ȡΪnull��
														//ʹ��Integer�����˿�ָ���쳣������һ����Ȥ��С����
			Integer page = Integer.parseInt(pageStr);
			Integer row = Integer.parseInt(rowStr);
														//��ȡ�ܹ��ж���������
			Long count = BusinessService.getQueCount();
														//�����page�ļ������ǰҳ���Ӷ��ٺ���ʼ�������仰����Ҫ�˽�MySql��ҳ��ѯ���
			if (page > 0){
				page = (page-1)*row;
			}
														//��page��row��Ϊ�������ݣ�����ʵ�ַ�ҳ��ѯ
			List<Map<String, String>> model = BusinessService.getQuestion(page, row);
			
			String result = null;
			try {
														//����д��һ����������ǰ̨����Easyui datagrid�����Ҫ��Jsonģ��
			result = MrksUtils.getEasyUIDataGridModel(model, count);
			} catch (Exception e) {
			
			e.printStackTrace();
			}
														//����д��һ��������ʹ��response��Json���ظ��ͻ���
			MrksUtils.responseWriteJson(resp, result);
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		if ("add".equals(action)){		//��������
			
			String ������� = req.getParameter(BusinessQuestion.�������.toString());  //����Ԫ��
			String �������� = req.getParameter(BusinessQuestion.��������.toString());	//����Ԫ��
			String ���� = req.getParameter(BusinessQuestion.����.toString());	//����Ԫ��
			String ��� = req.getParameter(BusinessQuestion.���.toString());	//����Ԫ��
			
			String ��1 = req.getParameter("DAAN1");		//��Ԫ��
			String ��2 = req.getParameter("DAAN2");		//��Ԫ��
			String ��3 = req.getParameter("DAAN3");		//��Ԫ��
			String ��4 = req.getParameter("DAAN4");		//��Ԫ��
			String ��1��ȷ = req.getParameter("DAAN1CHECK");		//����𰸱���
			String ��2��ȷ = req.getParameter("DAAN2CHECK");		//����𰸱���
			String ��3��ȷ = req.getParameter("DAAN3CHECK");		//����𰸱���
			String ��4��ȷ = req.getParameter("DAAN4CHECK");		//����𰸱���
			List<String> check = new ArrayList<>();
			Map<String,String> daanMap= new HashMap<>();
			
			String UUID1 = UUID.randomUUID().toString();
			daanMap.put(UUID1, ��1);
			String UUID2 = UUID.randomUUID().toString();
			daanMap.put(UUID2, ��2);
			String UUID3 = UUID.randomUUID().toString();
			daanMap.put(UUID3, ��3);
			String UUID4 = UUID.randomUUID().toString();
			daanMap.put(UUID4, ��4);
			if (��1��ȷ!=null){
				check.add(UUID1);
			}
			
			if (��2��ȷ!=null){
				check.add(UUID2);
			}
			
			if (��3��ȷ!=null){
				check.add(UUID3);
			}
			
			if (��4��ȷ!=null){
				check.add(UUID4);
			}
			//Listת��ΪString[],��������ʹ�õ��Ǵ𰸱��룬�𰸱����ʵ����ͨ��UUID�����ַ�����
			//ת��Ϊchar[]����char[]���ÿһ��Ԫ��ת��Ϊ��Ӧ��asc���룬��asc������ӵó��𰸱���
			Integer �𰸱��� = MrksUtils.statistics(check.toArray(new String[check.size()]));
			
			BusinessService.InsertQuestionAndAnswers(�������, ��������, ����, �𰸱���, ���, daanMap);
			
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}
