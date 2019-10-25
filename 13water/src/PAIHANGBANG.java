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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PAIHANGBANG {
	JFrame jf = new JFrame("排行榜");
	void Paihangbang() {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1134,640);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();//����
		JPanel jp2 = new JPanel();//���ذ�ť
		JLabel jl = new JLabel();//����
		jp1.setBounds(0, 0, 1134, 640);
		jp1.setOpaque(true);
		jp2.setBounds(52, 26, 106, 59);
		jp2.setOpaque(true);
		jl.setBounds(0, 0, 1133, 640);
		Icon icon = new ImageIcon("images/排行榜背景.jpg");
		Icon fanhui = new ImageIcon("icon/返回.jpg");
		jl.setIcon(icon);
		JButton jb1 = new JButton();
		jb1.setIcon(fanhui);
		jb1.setBounds(0, 0, 106, 59);
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		String[] tou = {"排名","名字","分数","玩家id"};
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				  .url("http://api.revth.com/rank")
				  .get()
				  .build();
		Call call = client.newCall(request);
		String result = "";
		try {
            Response response = call.execute();
            result = response.body().string();
        } catch (IOException f) {
            f.printStackTrace();
        }
		//System.out.println(result);
		try {
			JSONArray jsb = new JSONArray(result);
			int n=jsb.length();
    		Object[][] Data = new Object[n][4];
    		for(int i=0;i<n;i++) {
    			JSONObject x = new JSONObject(jsb.getString(i));
    			Data[i][0] = i;
    			Data[i][1] = x.getString("name");
    			Data[i][2] = x.getInt("score");
    			Data[i][3] = x.getInt("player_id");
    		}
    		JTable table = new JTable(Data,tou);
    		JScrollPane JSP = new JScrollPane(table);
    		table.setBounds(0, 0, 780, 347);
    		JSP.setBounds(185, 165, 780, 347);
    		jf.add(jp2);
    		jf.add(JSP);
    		jp2.add(jb1);
    		jp1.add(jl);
    		jp2.setLayout(null);
    		jf.add(jp2);
    		jf.add(jp1);
    		jf.pack();
    		jf.setVisible(true);
    	}
    	catch (JSONException f) {
    		f.printStackTrace();
    	}
		
	}
}
