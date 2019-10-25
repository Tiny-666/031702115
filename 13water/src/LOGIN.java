package GAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

public class LOGIN {
	String Token = "";
	int User_id = 0;
	String name = "";
	int odk = 0;
	JFrame jf = new JFrame("登录");
	void Login() {
		BEIJIN bj = new BEIJIN();
		bj.LoginOrRegister();
		jf.setVisible(false);
		while(true) {
			if(bj.ok==1) {
				jf.setVisible(true);
				break;
			}
			try {
				Thread.sleep(40);//40ms 1s=1000ms ��ʾһ�뻭����
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1134,639);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JLabel jl = new JLabel();
		jp1.setBounds(0, 0, 1134, 638);
		jp1.setOpaque(true);
		jp2.setBounds(433, 399, 325, 38);
		jp2.setOpaque(true);
		jp3.setBounds(433, 468, 325, 38);
		jp3.setOpaque(true);
		jp4.setBounds(500, 535, 145, 74);
		jp4.setOpaque(true);
		jl.setBounds(0, 0, 1134, 638);
		Icon icon = new ImageIcon("images/登录.jpg");
		Icon denglu = new ImageIcon("icon/登录icon.png");
		JButton jb = new JButton();
		jb.setIcon(denglu);
		jb.setBounds(0, 0, 145, 74);
		jl.setIcon(icon);
		jp1.add(jl);
		JTextField userText = new JTextField(32);
		userText.setBounds(0, 0, 325, 38);
		JPasswordField passwordText = new JPasswordField(32);
		passwordText.setBounds(0, 0, 325, 38);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HttpPost post = new HttpPost();
				@SuppressWarnings("deprecation")
				String value = "{\"username\": \""+userText.getText()+"\",\"password\": \""+passwordText.getText()+"\"}";
				try 
	        	{
	        		JSONObject jsonb = new JSONObject(post.Post("http://api.revth.com/auth/login", value, 0, ""));
	        		if(jsonb.getInt("status")!=0) {
	        			JFrame Error = new JFrame("错误");
	        			Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        			Error.setSize(225,130);
	        			Error.setLocation(800, 440);
	        			JPanel JP = new JPanel();
	        			JP.setBounds(22, 22, 181, 108);
	        			JP.setOpaque(true);
	        			JTextField xinxi = new JTextField("用户名或密码错误");
	        			xinxi.setBounds(22, 22, 181, 50);
	        			xinxi.setEnabled(false);
	        			Font x = new Font("Serif",0,20);
	        			xinxi.setFont(x);
	        			JButton quedin = new JButton("确认");
	        			quedin.setBounds(62, 84, 100, 34);
	        			quedin.addActionListener(new ActionListener() {
	        				public void actionPerformed(ActionEvent g) {
	        					Error.dispose();
	        				}
	        			});
	        			JP.add(xinxi);
	        			JP.add(quedin);
	        			Error.add(JP);
	        			Error.setVisible(true);
	        		}
	        		else {
	        			Token = jsonb.getJSONObject("data").getString("token");
	        			User_id = jsonb.getJSONObject("data").getInt("user_id");
	        			name = userText.getText();
	        			JFrame Error = new JFrame("成功");
	        			Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        			Error.setSize(225,130);
	        			Error.setLocation(800, 440);
	        			JPanel JP = new JPanel();
	        			JP.setBounds(22, 22, 181, 108);
	        			JP.setOpaque(true);
	        			JTextField xinxi = new JTextField("登录成功！！！ ");
	        			xinxi.setBounds(22, 22, 181, 50);
	        			xinxi.setEnabled(false);
	        			Font x = new Font("Serif",0,20);
	        			xinxi.setFont(x);
	        			JButton quedin = new JButton("确定");
	        			quedin.setBounds(62, 84, 100, 34);
	        			quedin.addActionListener(new ActionListener() {
	        				public void actionPerformed(ActionEvent g) {
	        					odk = 1;
	        					jf.setVisible(false);
	        					Error.dispose();
	        				}
	        			});
	        			JP.add(xinxi);
	        			JP.add(quedin);
	        			Error.add(JP);
	        			Error.setVisible(true);
	        			
	        		}
	        	} 
	        	catch (JSONException f) {
	        		f.printStackTrace();
	        	}
			}
		});
		jp2.add(userText);
		jp3.add(passwordText);
		jp4.add(jb);
		jp2.setLayout(null);
		jp3.setLayout(null);
		jp4.setLayout(null);
		jf.add(jp4);
		jf.add(jp3);
		jf.add(jp2);
		jf.add(jp1);
		jf.pack();
		
	}
}
