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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

public class ZHUNBEI {
	JFrame jf = new JFrame("准备");
	int zhanju_id = 0;
	void Zhunbei(String tok, String na) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1133,640);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
		JLabel jl = new JLabel();
		jp1.setBounds(0, 0, 1135, 638);
		jp1.setOpaque(true);
		jp2.setBounds(52, 26, 106, 59);
		jp2.setOpaque(true);
		jp3.setBounds(512, 296, 120, 58);
		jp3.setOpaque(true);
		jp4.setBounds(820, 510, 100, 45);
		jp4.setOpaque(true);
		jp5.setBounds(520, 160, 100, 35);
		jp5.setOpaque(true);
		jl.setBounds(0, 0, 1133, 640);
		Icon icon = new ImageIcon("images/准备背景.jpg");
		Icon fanhui = new ImageIcon("icon/返回.jpg");
		Icon zhunbei = new ImageIcon("icon/准备.jpg");
		jl.setIcon(icon);
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		JButton jb3 = new JButton("千连杀");
		jb1.setIcon(fanhui);
		jb1.setBounds(0, 0, 106, 59);
		jb2.setIcon(zhunbei);
		jb2.setBounds(0, 0, 120, 58);
		jb3.setBounds(0, 0, 100, 35);
		JTextArea name = new JTextArea(na);
		name.setBounds(0, 0, 100, 45);
		name.setEnabled(false);
		Font x = new Font("Serif",1,20);
		name.setFont(x);
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QIANLIANSHA qls = new QIANLIANSHA();
				qls.Qianliansha(tok);
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HttpPost pos = new HttpPost();
	        		JSONObject jsonb = new JSONObject(pos.Post("http://api.revth.com/game/open", "", 1, tok));
	        		if(jsonb.getInt("status")!=0) {
	        			JFrame Error = new JFrame("错误");
	        			Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        			Error.setSize(225,130);
	        			Error.setLocation(800, 440);
	        			JPanel JP = new JPanel();
	        			JP.setBounds(22, 22, 181, 108);
	        			JP.setOpaque(true);
	        			JTextField xinxi = new JTextField("加入战局失败");
	        			xinxi.setBounds(22, 22, 181, 50);
	        			xinxi.setEnabled(false);
	        			Font x = new Font("Serif",0,20);
	        			xinxi.setFont(x);
	        			JButton quedin = new JButton("确定");
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
	        			zhanju_id = jsonb.getJSONObject("data").getInt("id");
	        			CHUPAIJIEMIAN cp = new CHUPAIJIEMIAN();
	        			cp.ChupaiJiemian(na,jsonb.getJSONObject("data").getInt("id"), jsonb.getJSONObject("data").getString("card"),tok);
	        		}
	        	} 
	        	catch (JSONException f) {
	        		f.printStackTrace();
	        	}
			}
		});
		jp2.add(jb1);
		jp3.add(jb2);
		jp4.add(name);
		jp5.add(jb3);
		jp1.add(jl);
		jp2.setLayout(null);
		jp3.setLayout(null);
		jp4.setLayout(null);
		jp5.setLayout(null);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp4);
		jf.add(jp5);
		jf.add(jp1);
		jf.pack();
		jf.setVisible(true);
	}
}
