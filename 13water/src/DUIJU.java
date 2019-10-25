package GAME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DUIJU {
	JFrame jf = new JFrame("对局");
	int page=0;
	void Duiju(int ID,String tok) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1134,640);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();//����
		JPanel jp2 = new JPanel();//���ذ�ť
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JLabel jl = new JLabel();//����
		jp1.setBounds(0, 0, 1134, 640);
		jp1.setOpaque(true);
		jp2.setBounds(52, 26, 106, 59);
		jp2.setOpaque(true);
		jp3.setBounds(382, 570, 125, 45);//��һҳ
		jp3.setOpaque(true);
		jp4.setBounds(627, 570, 125, 45);
		jp4.setOpaque(true);
		jl.setBounds(0, 0, 1133, 640);
		Icon icon = new ImageIcon("images/详情.jpg");
		Icon fanhui = new ImageIcon("icon/返回.jpg");
		jl.setIcon(icon);
		JButton jb1 = new JButton();
		JButton jb2 = new JButton("上一页");//��һҳ
		JButton jb3 = new JButton("下一页");//��һҳ
		jb1.setIcon(fanhui);
		jb1.setBounds(0, 0, 106, 59);
		jb2.setBounds(0, 0, 125, 45);
		jb3.setBounds(0, 0, 125, 45);
		String[] tou = {"战局id","分数","手牌"};
		OkHttpClient client = new OkHttpClient();
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page>0) page--;
				Request request = new Request.Builder()
						  .url("http://api.revth.com/history?page="+page+"&limit=20&player_id="+ID)
						  .get()
						  .addHeader("x-auth-token", tok)
						  .build();
				String Result ="";
				Call call = client.newCall(request);
				try {
		            Response response = call.execute();
		            Result = response.body().string();
		        } catch (IOException f) {
		            f.printStackTrace();
		        }
				try {
					JSONObject jj = new JSONObject(Result);
					String ju = jj.getString("data");
					JSONArray jsb = new JSONArray(ju);
					int n=jsb.length();
		    		Object[][] Data = new Object[n][3];
		    		for(int i=0;i<n;i++) {
		    			JSONObject x = new JSONObject(jsb.getString(i));
		    			Data[i][0] = x.getInt("id");
		    			Data[i][1] = x.getInt("score");
		    			Data[i][2] = x.getString("card");
		    		}
		    		JTable table = new JTable(Data,tou);
		    		TableColumnModel cm = table.getColumnModel();
		    		TableColumn column = cm.getColumn(2);  
		    		column.setPreferredWidth(320);
		    		column.setMaxWidth(330);
		    		column.setMinWidth(320);
		    		JScrollPane JSP = new JScrollPane(table);
		    		table.setBounds(0, 0, 780, 347);
		    		JSP.setBounds(176, 150, 780, 347);
		    		jp2.add(jb1);
		    		jp1.add(jl);
		    		jp3.add(jb2);
		    		jp4.add(jb3);
		    		jp2.setLayout(null);
		    		jp3.setLayout(null);
		    		jp4.setLayout(null);
		    		jf.add(jp2);
		    		jf.add(JSP);
		    		jf.add(jp3);
		    		jf.add(jp4);
		    		jf.add(jp1);
		    		jf.pack();
		    		jf.setVisible(true);
		    	}
		    	catch (JSONException f) {
		    		f.printStackTrace();
		    	}
			}
		});
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
				Request request = new Request.Builder()
						  .url("http://api.revth.com/history?page="+page+"&limit=20&player_id="+ID)
						  .get()
						  .addHeader("x-auth-token", tok)
						  .build();
				String Result ="";
				Call call = client.newCall(request);
				try {
		            Response response = call.execute();
		            Result = response.body().string();
		        } catch (IOException f) {
		            f.printStackTrace();
		        }
				try {
					JSONObject jj = new JSONObject(Result);
					String ju = jj.getString("data");
					JSONArray jsb = new JSONArray(ju);
					int n=jsb.length();
		    		Object[][] Data = new Object[n][3];
		    		for(int i=0;i<n;i++) {
		    			JSONObject x = new JSONObject(jsb.getString(i));
		    			Data[i][0] = x.getInt("id");
		    			Data[i][1] = x.getInt("score");
		    			Data[i][2] = x.getString("card");
		    		}
		    		JTable table = new JTable(Data,tou);
		    		TableColumnModel cm = table.getColumnModel();
		    		TableColumn column = cm.getColumn(2);  
		    		column.setPreferredWidth(320);
		    		column.setMaxWidth(330);
		    		column.setMinWidth(320);
		    		JScrollPane JSP = new JScrollPane(table);
		    		table.setBounds(0, 0, 780, 347);
		    		JSP.setBounds(176, 150, 780, 347);
		    		jp2.add(jb1);
		    		jp1.add(jl);
		    		jp3.add(jb2);
		    		jp4.add(jb3);
		    		jp2.setLayout(null);
		    		jp3.setLayout(null);
		    		jp4.setLayout(null);
		    		jf.add(jp2);
		    		jf.add(JSP);
		    		jf.add(jp3);
		    		jf.add(jp4);
		    		jf.add(jp1);
		    		jf.pack();
		    		jf.setVisible(true);
		    	}
		    	catch (JSONException f) {
		    		f.printStackTrace();
		    	}
			}
		});
		
		Request request = new Request.Builder()
				  .url("http://api.revth.com/history?page="+page+"&limit=20&player_id="+ID)
				  .get()
				  .addHeader("x-auth-token", tok)
				  .build();
		String Result ="";
		Call call = client.newCall(request);
		try {
            Response response = call.execute();
            Result = response.body().string();
        } catch (IOException f) {
            f.printStackTrace();
        }
		try {
			JSONObject jj = new JSONObject(Result);
			String ju = jj.getString("data");
			JSONArray jsb = new JSONArray(ju);
			int n=jsb.length();
    		Object[][] Data = new Object[n][3];
    		for(int i=0;i<n;i++) {
    			JSONObject x = new JSONObject(jsb.getString(i));
    			Data[i][0] = x.getInt("id");
    			Data[i][1] = x.getInt("score");
    			Data[i][2] = x.getString("card");
    		}
    		JTable table = new JTable(Data,tou);
    		TableColumnModel cm = table.getColumnModel();
    		TableColumn column = cm.getColumn(2);  
    		column.setPreferredWidth(320);
    		column.setMaxWidth(330);
    		column.setMinWidth(320);
    		JScrollPane JSP = new JScrollPane(table);
    		table.setBounds(0, 0, 780, 347);
    		JSP.setBounds(176, 150, 780, 347);
    		jp2.add(jb1);
    		jp1.add(jl);
    		jp3.add(jb2);
    		jp4.add(jb3);
    		jp2.setLayout(null);
    		jp3.setLayout(null);
    		jp4.setLayout(null);
    		jf.add(jp2);
    		jf.add(JSP);
    		jf.add(jp3);
    		jf.add(jp4);
    		jf.add(jp1);
    		jf.pack();
    		jf.setVisible(true);
    	}
    	catch (JSONException f) {
    		f.printStackTrace();
    	}
		
	}
	
}
