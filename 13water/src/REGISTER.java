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

public class REGISTER {
	JFrame jf = new JFrame("×¢²á");
	void Register() {
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
		Icon icon = new ImageIcon("images/×¢²á.jpg");
		Icon denglu = new ImageIcon("icon/×¢²áicon.png");
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
				//System.out.println(value);
				try 
	        	{
	        		JSONObject jsonb = new JSONObject(post.Post("http://api.revth.com/register", value, 0, ""));
	        		if(jsonb.getInt("status")!=0) {
	        			JFrame Error = new JFrame("´íÎó");
	        			Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        			Error.setSize(225,130);
	        			Error.setLocation(800, 440);
	        			JPanel JP = new JPanel();
	        			JP.setBounds(22, 22, 181, 108);
	        			JP.setOpaque(true);
	        			JTextField xinxi = new JTextField("×¢²áÊ§°Ü£¡£¡£¡ ");
	        			xinxi.setBounds(22, 22, 181, 50);
	        			xinxi.setEnabled(false);
	        			Font x = new Font("Serif",0,20);
	        			xinxi.setFont(x);
	        			JButton quedin = new JButton("È·¶¨");
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
	        			JFrame Error = new JFrame("³É¹¦");
	        			Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        			Error.setSize(225,130);
	        			Error.setLocation(800, 440);
	        			JPanel JP = new JPanel();
	        			JP.setBounds(22, 22, 181, 108);
	        			JP.setOpaque(true);
	        			JTextField xinxi = new JTextField("×¢²á³É¹¦£¡£¡£¡");
	        			xinxi.setBounds(22, 22, 181, 50);
	        			xinxi.setEnabled(false);
	        			Font x = new Font("Serif",0,20);
	        			xinxi.setFont(x);
	        			JButton quedin = new JButton("È·¶¨");
	        			quedin.setBounds(62, 84, 100, 34);
	        			quedin.addActionListener(new ActionListener() {
	        				public void actionPerformed(ActionEvent g) {
	        					Error.dispose();
	        					jf.setVisible(false);
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
		jf.setVisible(true);
	}
}
