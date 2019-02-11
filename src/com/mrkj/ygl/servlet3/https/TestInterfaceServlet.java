package com.mrkj.ygl.servlet3.https;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
 
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
 
public class TestInterfaceServlet extends HttpServlet
{
    
    /**
     * ����ӿ������
     * ���ڻ�ȡ�û����󣬷����û�����ʹ�á�
     */
    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException
    {
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpServletRequest httprequest = (HttpServletRequest)request;
        httpResponse.setContentType("text/html;charset=UTF-8");
        
        Map<String, String> mapHeader = getHeaderMap(httprequest);// ��ȡhttpͷ��Ϣ
        
        String requestXml = "";//���������xml����
        String responseXml = "";//���ص�xml����
        Document doc = null;
        
        InputStream is = request.getInputStream();
        System.out.println("======http header timestamp is ��" + mapHeader.get("timestamp"));
        
        SAXReader saxReader = new SAXReader();
        Map<String, String> map = new HashMap<String, String>();
        
        try
        {
            doc = saxReader.read(is);
            requestXml = doc.asXML();
            System.out.println("======request xml ��" + requestXml);
            Element rootElm = doc.getRootElement();//��root���ڵ��ȡ������
            map = parseXML(rootElm, new HashMap<String, String>());
            
            String msgname = map.get("msgname");//��http body�л�ȡ����msgnameֵ
            
            // �Ƿ�https����
            if (request.isSecure())
            {
                //https����
                if ("loginReq".equals(msgname))
                {
                    System.out.println("the https+xml request is loginReq");
                    responseXml =
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ecity><msgname>loginRep</msgname><msgversion>1.0.0</msgversion><result><rspcode>0</rspcode><rspdesc>�ɹ�</rspdesc></result><svccont><uid>111</uid></svccont></ecity>";
                }
                else
                {
                    System.out.println("����ӿ����Ʋ��ڱ�ϵͳ��");
                    responseXml =
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ecity><msgversion>1.0.0</msgversion><result><rspcode>1</rspcode><rspdesc>����ӿ����Ʋ��ڱ�ϵͳ��</rspdesc></result></ecity>";
                }
            }
            else
            {
                //http����  
                if ("logOutReq".equals(msgname))
                {
                    System.out.println("the http+xml request is logOut");
                    responseXml =
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ecity><msgname>logOutRep</msgname><msgversion>1.0.0</msgversion><result><rspcode>0</rspcode><rspdesc>�ɹ�</rspdesc></result><svccont><uid>111</uid></svccont></ecity>";
                }
                else
                {
                    System.out.println("����ӿ����Ʋ��ڱ�ϵͳ��");
                    responseXml =
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ecity><msgversion>1.0.0</msgversion><result><rspcode>1</rspcode><rspdesc>����ӿ����Ʋ��ڱ�ϵͳ��</rspdesc></result></ecity>";
                }
            }
            
        }
        catch (Exception e1)
        {
            System.out.println("�������ݲ���ȷ");
            responseXml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ecity><msgversion>1.0.0</msgversion><result><rspcode>2</rspcode><rspdesc>�������ݲ���ȷ</rspdesc></result></ecity>";
            
        }
        
        // �Ա��Ľ���ѹ������
        String AcceptEncoding = "gzip";
        if (mapHeader != null)
            AcceptEncoding = mapHeader.get("Accept-Encoding");
        if (null != AcceptEncoding && "gzip".equals(AcceptEncoding))
        {
            // ʹ��gzipѹ������
            System.out.println("======output response xml with gzip is : " + responseXml);
            response.getOutputStream().write(gZip(responseXml.getBytes()));
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
        else
        {
            // ��ѹ�����ģ�ֱ�Ӵ���
            System.out.println("======output response xml without gzip is : " + responseXml);
            response.getOutputStream().write(responseXml.getBytes());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
        
    }
    
    /**
     * ��ȡhttpͷ��Ϣ 
     * <������ϸ����>
     * @param request
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public Map<String, String> getHeaderMap(HttpServletRequest request)
    {
        Map<String, String> map = new HashMap<String, String>();
        if (null != request.getHeader("msgname") && !"".equals(request.getHeader("msgname")))
            map.put("msgname", request.getHeader("msgname"));
        if (null != request.getHeader("Accept-Encoding") && !"".equals(request.getHeader("Accept-Encoding")))
            map.put("Accept-Encoding", request.getHeader("Accept-Encoding"));
        if (null != request.getHeader("timestamp") && !"".equals("timestamp"))
            map.put("timestamp", request.getHeader("timestamp"));
        return map;
    }
    
    /**
     * ��xml������map��ֵ��
     * <������ϸ����>
     * @param ele ��Ҫ������xml����
     * @param map ���Ϊ�գ������ڲ�����ѭ��ʹ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private Map<String, String> parseXML(Element ele, Map<String, String> map)
    {
        for (Iterator<?> i = ele.elementIterator(); i.hasNext();)
        {
            Element node = (Element)i.next();
            System.out.println("======parseXML node name:" + node.getName());
            if (node.attributes() != null && node.attributes().size() > 0)
            {
                for (Iterator<?> j = node.attributeIterator(); j.hasNext();)
                {
                    Attribute item = (Attribute)j.next();
                    System.out.println("======parseXML property name��" + item.getName() + " property value��"
                        + item.getValue() + "\n");
                    map.put(item.getName(), item.getValue());
                }
            }
            if (node.getText().length() > 0)
            {
                System.out.println("======parseXML node value��" + node.getText());
                map.put(node.getName(), node.getText());
            }
            if (node.elementIterator().hasNext())
            {
                parseXML(node, map);
            }
        }
        return map;
    }
    
    /**
     * gZipѹ������
     * ��ԭ����ͨ��gzipѹ��
     * @param data
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public static byte[] gZip(byte[] data)
    {
        byte[] b = null;
        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return b;
    }
}
