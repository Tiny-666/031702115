package GAME;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpPost {
    public String Post(String pathUrl, String data, int zhuangtai, String token){
        OutputStreamWriter out = null;
        BufferedReader br = null;
        String result = "{\"status\": 1}";
        try 
        {
            URL url = new URL(pathUrl);
            //�򿪺�url֮�������
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //����ʽ
            conn.setRequestMethod("POST");
            //conn.setRequestMethod("GET");

            //����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            if(zhuangtai == 0) {
            	conn.setRequestProperty("Content-Type", "application/json");
            }
            else if(zhuangtai == 1) {
            	conn.setRequestProperty("x-auth-token", token);
            }
            else if(zhuangtai == 2) {
            	conn.setRequestProperty("Content-Type", "application/json");
            	conn.setRequestProperty("x-auth-token", token);
            }

            //DoOutput�����Ƿ���httpUrlConnection�����DoInput�����Ƿ��httpUrlConnection���룬���ⷢ��post�����������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            /**
             * �����������룬���ǵ��õ�����http�ӿ�
             */
            //��ȡURLConnection�����Ӧ�������
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");//
            //�����������������
            out.write(data);
            //flush������Ļ���
            out.flush();
            int code = conn.getResponseCode();
            if(code==200) 
            {
            	//System.out.println("success");
            	/**
            	 * ����Ĵ����൱�ڣ���ȡ���õ�����http�ӿں󷵻صĽ��
            	 */
            	//��ȡURLConnection�����Ӧ��������
            	InputStream is = conn.getInputStream();
            	//����һ���ַ�������
            	br = new BufferedReader(new InputStreamReader(is));     
            	String str = "";
            	result = "";
            	while ((str = br.readLine()) != null)
            	{
            		result += str;
            	}
            	//System.out.println(result);
            	//�ر���
            	is.close();	
            }
            else
            	//System.out.println("fail");
            //�Ͽ����ӣ�disconnect���ڵײ�tcp socket���ӿ���ʱ���жϣ�������ڱ������߳�ʹ�þͲ��жϡ�
            conn.disconnect();
            
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        
        //finally 
        //{
        	
        	try 
        	{
        		if (out != null)
        		{
        			out.close();
        		}
        		if (br != null)
        		{
        			br.close();
        		}
        		
        	} 
        	catch (IOException e) 
        	{
        		e.printStackTrace();
        	}
        //}
        return result;
    }
}
