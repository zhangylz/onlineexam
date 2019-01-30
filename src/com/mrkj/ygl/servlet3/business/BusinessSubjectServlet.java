package com.mrkj.ygl.servlet3.business;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrkj.ygl.entity.BusinessSubject;
import com.mrkj.ygl.service.BusinessService;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author Administrator
 *
 *���˼��
 *
 *	����ദ���Կ�Ŀ����Ҫ�����з�ҳ�����ӣ��޸ģ�ɾ��
 *
 *1����ҳ����easyui��datagrid�����Ĭ�ϴ��ݲ���page��row
 *
 */

@WebServlet(urlPatterns="/subject",asyncSupported=false)
public class BusinessSubjectServlet extends HttpServlet{

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//��ȡID
		String sub_id = req.getParameter(BusinessSubject.ID.toString());
		//����IDɾ������
		int result = BusinessService.deleteById(sub_id);
		if (result == 1){
			MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
		}else{
			MrksUtils.responseWriteJson(resp, "{\"success\":\"NO\"}");
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("sub");
		if (action!=null){
			if (action.equals("kmbj")){		//��ȡ��Ŀ�༭��ҳ
				req.getRequestDispatcher("/editSubject.jsp").forward(req, resp);
			}else if (action.equals("getSubject") ){		//��ȡ��Ŀ��ҳ�б�
															//easyui datagrid������ݹ����Ĳ���page����ǰҳ��Ĭ��1
				String pageStr = req.getParameter("page");
															//easyui datagrid������ݹ����Ĳ���row����ÿҳ��ʾ�����У���������Ϊ10
				String rowStr = req.getParameter("rows");
															//ת��StringΪInteger,֮�������ﲻ����int����Ϊ��ĳЩ�ض�����²�����ȡΪnull��
															//ʹ��Integer�����˿�ָ���쳣������һ����Ȥ��С����
				Integer page = Integer.parseInt(pageStr);
				Integer row = Integer.parseInt(rowStr);
															//��ȡ�ܹ��ж���������
				Long count = BusinessService.getSubCount();
															//�����page�ļ������ǰҳ���Ӷ��ٺ���ʼ�������仰����Ҫ�˽�MySql��ҳ��ѯ���
				if (page > 0){
					page = (page-1)*row;
				}
															//��page��row��Ϊ�������ݣ�����ʵ�ַ�ҳ��ѯ
				List<Map<String, String>> model = BusinessService.getSubPage(page, row);
				
				String result = null;
				try {
															//����д��һ����������ǰ̨����Easyui datagrid�����Ҫ��Jsonģ��
					result = MrksUtils.getEasyUIDataGridModel(model, count);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
															//����д��һ��������ʹ��response��Json���ظ��ͻ���
				MrksUtils.responseWriteJson(resp, result);
				
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ��Ŀ = req.getParameter(BusinessSubject.��Ŀ.toString());
		String action = req.getParameter("action");
		if ("add".equals(action)){
			int isResult = BusinessService.insert(��Ŀ);
			
			if (isResult == 1){
				MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
			}else{
				MrksUtils.responseWriteJson(resp, "{\"success\":\"NO\"}");
			}
		}else if ("edit".equals(action)){
			String sub_id = req.getParameter(BusinessSubject.ID.toString());
			int isResult = BusinessService.updateById(��Ŀ, sub_id);
			if (isResult == 1){
				MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
			}else{
				MrksUtils.responseWriteJson(resp, "{\"success\":\"NO\"}");
			}
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
