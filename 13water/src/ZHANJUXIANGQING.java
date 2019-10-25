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
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.json.*;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZHANJUXIANGQING {
	JFrame jf = new JFrame("战局详情");
	void Lishi(String tok) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1136,638);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();// ����
		JPanel jp3 = new JPanel();//text
		JPanel jp4 = new JPanel();
		JLabel jl = new JLabel();
		jp1.setBounds(0, 0, 1136, 638);
		jp1.setOpaque(true);
		jp2.setBounds(52, 26, 106, 59);
		jp2.setOpaque(true);
		jp3.setBounds(374, 90, 325, 40);
		jp3.setOpaque(true);
		jp4.setBounds(860, 90, 115, 40);//��ť
		jp4.setOpaque(true);
		jl.setBounds(0, 0, 1136, 638);
		Icon icon = new ImageIcon("images/详情.jpg");
		Icon fanhui = new ImageIcon("icon/返回.jpg");
		JButton jb1 = new JButton();
		JButton jb2 = new JButton("搜索");
		jb1.setIcon(fanhui);
		jb1.setBounds(0, 0, 106, 59);
		jb2.setBounds(0, 0, 115, 40);
		jl.setIcon(icon);
		jp1.add(jl);
		JTextField id = new JTextField(32);
		id.setBounds(0, 0, 325, 40);
		
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tou = {"玩家id","名字","分数","手牌"};
				String ID = id.getText();
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder()
				  .url("http://api.revth.com/history/"+ID)
				  .get()
				  .addHeader("x-auth-token", tok)
				  .build();
				Call call = client.newCall(request);
				String result = "";
				try {
                    Response response = call.execute();
                    result = response.body().string();
                } catch (IOException f) {
                    f.printStackTrace();
                }
				try {
					JSONObject jsonb = new JSONObject(result);
					String xx= jsonb.getJSONObject("data").getString("detail");
					JSONArray jsb = new JSONArray(xx);
		    		Object[][] Data = new Object[4][4];
		    		for(int i=0;i<4;i++) {
		    			JSONObject x = new JSONObject(jsb.getString(i));
		    			Data[i][0] = x.getInt("player_id");
		    			Data[i][1] = x.getString("name");
		    			Data[i][2] = x.getInt("score");
		    			Data[i][3] = x.getString("card");
		    		}
		    		JTable table = new JTable(Data,tou);
		    		TableColumnModel cm = table.getColumnModel();
		    		TableColumn column = cm.getColumn(3);  
		    		column.setPreferredWidth(320);
		    		column.setMaxWidth(330);
		    		column.setMinWidth(320);
		    		JScrollPane JSP = new JScrollPane(table);
		    		table.setBounds(0, 0, 780, 88);
		    		JSP.setBounds(185, 165, 780, 88);
		    		jf.add(jp4);
		    		jf.add(jp3);
		    		jf.add(jp2);
		    		jf.add(JSP);
		    		jf.add(jp1);
		    		jf.pack();
		    		jf.setVisible(true);
	        	}
	        	catch (JSONException f) {
	        		f.printStackTrace();
	        	}
			}
		});
		jp1.add(jl);
		jp2.add(jb1);
		jp3.add(id);
		jp4.add(jb2);
		jp2.setLayout(null);
		jp3.setLayout(null);
		jp4.setLayout(null);
		jf.add(jp4);
		jf.add(jp3);
		jf.add(jp2);
		jf.add(jp1);
		jf.pack();
		jf.setVisible(true);
	}
}
