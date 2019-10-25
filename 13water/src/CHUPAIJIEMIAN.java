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

public class CHUPAIJIEMIAN {
	JFrame jf = new JFrame("出牌");
	void ChupaiJiemian(String na, int id, String card, String tok) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1133,640);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
	    JPanel jp6 = new JPanel();
		JLabel jl = new JLabel();
		jp1.setBounds(0, 0, 1135, 638);
		jp1.setOpaque(true);
		jp2.setBounds(52, 26, 106, 59);
		jp2.setOpaque(true);
		jp3.setBounds(519, 438, 113, 58);
		jp3.setOpaque(true);
		jp4.setBounds(820, 510, 100, 45);
		jp4.setOpaque(true);
		jp5.setBounds(476, 97, 180, 91);
	    jp5.setOpaque(true);
	    jp6.setBounds(416, 188, 300, 182);
	    jp6.setOpaque(true);
		jl.setBounds(0, 0, 1133, 640);
		Icon icon = new ImageIcon("images/出牌.jpg");
		Icon fanhui = new ImageIcon("icon/返回.jpg");
		Icon zhunbei = new ImageIcon("icon/出牌.jpg");
		jl.setIcon(icon);
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		jb1.setIcon(fanhui);
		jb1.setBounds(0, 0, 106, 59);
		jb2.setIcon(zhunbei);
		jb2.setBounds(0, 0, 113, 58);
		JTextArea name = new JTextArea(na);
		name.setBounds(0, 0, 100, 45);
		name.setEnabled(false);
		Font x = new Font("Serif",1,20);
		name.setFont(x);
		CHUPAI cp = new CHUPAI();
		String result = cp.CardPlaying(card);
		for(int count = 0,i = 0;i<result.length();i++) {
	        if(result.charAt(i)=='$'||result.charAt(i)=='&'||result.charAt(i)=='#'||result.charAt(i)=='*') {
	        	int j=i+1;
	        	for(;j<result.length();j++) {
	        		if(result.charAt(j)==' '||result.charAt(j)=='"')
	        			break;
	        	}
	        	String pai = result.substring(i, j);
	        	if(pai.charAt(0)=='*') {
	        		StringBuilder strBuilder = new StringBuilder(pai);
	        		strBuilder.setCharAt(0, '_');
	        		pai=strBuilder.toString();
	        	}
	        	JLabel jpai = new JLabel();
	        	Icon iconpai = new ImageIcon("扑克牌图片/"+pai+".JPG");
	        	if(count<3) {
	        		jpai.setBounds(60*count, 0, 60, 91);
	        		jpai.setIcon(iconpai);
	        		jp5.add(jpai);
	        		count++;
	        	}
	        	else if(count>=3&&count<8) {
	        		jpai.setBounds(60*(count-3), 0, 60, 91);
	        		jpai.setIcon(iconpai);
	        		jp6.add(jpai);
	        		count++;
	        	}
	        	else if(count>=8&&count<13) {
	        		jpai.setBounds(60*(count-8), 91, 60, 91);
	        		jpai.setIcon(iconpai);
	        		jp6.add(jpai);
	        		count++;
	        	}
	        }
	    }
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RESULT = "{\"id\": "+ id +",\"card\": ["+ result + "]}";
				try 
	        	{
	        		HttpPost HP = new HttpPost();
	        		JSONObject jsonb = new JSONObject(HP.Post("http://api.revth.com/game/submit", RESULT, 2, tok));
	        		if(jsonb.getInt("status")!=0) {
	        			JFrame Error = new JFrame("错误");
	        			Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        			Error.setSize(225,130);
	        			Error.setLocation(800, 440);
	        			JPanel JP = new JPanel();
	        			JP.setBounds(22, 22, 181, 108);
	        			JP.setOpaque(true);
	        			JTextField xinxi = new JTextField("\n错误码:"+jsonb.getInt("status"));
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
	        			JFrame Error = new JFrame("成功");
	        			Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        			Error.setSize(225,130);
	        			Error.setLocation(800, 440);
	        			JPanel JP = new JPanel();
	        			JP.setBounds(22, 22, 181, 108);
	        			JP.setOpaque(true);
	        			JTextField xinxi = new JTextField("出牌成功！！！");
	        			xinxi.setBounds(22, 22, 181, 50);
	        			xinxi.setEnabled(false);
	        			Font x = new Font("Serif",0,20);
	        			xinxi.setFont(x);
	        			JButton quedin = new JButton("确认");
	        			quedin.setBounds(62, 84, 100, 34);
	        			quedin.addActionListener(new ActionListener() {
	        				public void actionPerformed(ActionEvent g) {
	        					//System.out.println(RESULT);
	        					Error.dispose();
	        					jf.dispose();
	        				}
	        			});
	        			JP.add(xinxi);
	        			JP.add(quedin);
	        			Error.add(JP);
	        			Error.setVisible(true);
	        		}
	        	} 
	        	catch (JSONException f) 
	        	{
	        		f.printStackTrace();
	        	}
			}
		});
		jp2.add(jb1);
		jp3.add(jb2);
		jp4.add(name);
		jp1.add(jl);
		jp2.setLayout(null);
		jp3.setLayout(null);
		jp4.setLayout(null);
		jp5.setLayout(null);
	    jp6.setLayout(null);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp4);
		jf.add(jp5);
	    jf.add(jp6);
		jf.add(jp1);
		jf.pack();
		jf.setVisible(true);
	}
}
