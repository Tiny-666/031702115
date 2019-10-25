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

public class ZHUYE {
	JFrame jf = new JFrame("主页");
	void Zhuye(String id, String tok,int user_id) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1136,640);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
		JPanel jp6 = new JPanel();
		JLabel jl = new JLabel();
		jp1.setBounds(0, 0, 1136, 640);
		jp1.setOpaque(true);
		jp2.setBounds(625, 165, 279, 74);
		jp2.setOpaque(true);
		jp3.setBounds(614, 445, 136, 73);
		jp3.setOpaque(true);
		jp4.setBounds(627, 305, 274, 75);
		jp4.setOpaque(true);
		jp5.setBounds(132, 15, 100, 30);
		jp5.setOpaque(true);
		jp6.setBounds(802, 445, 137, 72);
		jp6.setOpaque(true);
		jl.setBounds(0, 0, 1136, 640);
		Icon icon = new ImageIcon("images/主页.jpg");
		Icon kaishi = new ImageIcon("icon/开始游戏.jpg");
		Icon duiju = new ImageIcon("icon/对战情况.jpg");
		Icon paihang = new ImageIcon("icon/排行榜.jpg");
		Icon lishi = new ImageIcon("icon/历史战局.jpg");
		jl.setIcon(icon);
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		JButton jb3 = new JButton();
		JButton jb4 = new JButton();
		jb1.setIcon(kaishi);
		jb1.setBounds(0, 0, 279, 74);
		jb2.setIcon(duiju);
		jb2.setBounds(0, 0, 136, 73);
		jb3.setIcon(paihang);
		jb3.setBounds(0, 0, 274, 75);
		jb4.setIcon(lishi);
		jb4.setBounds(0, 0, 137, 72);
		JTextArea name = new JTextArea(id);
		name.setBounds(0, 0, 100, 30);
		name.setEnabled(false);
		Font x = new Font("Serif",0,20);
		name.setFont(x);
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZHUNBEI zb = new ZHUNBEI();
				zb.Zhunbei(tok, id);
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DUIJU dj = new DUIJU();
				dj.Duiju(user_id, tok);
			}
		});
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PAIHANGBANG p = new PAIHANGBANG();
				p.Paihangbang();
			}
		});
		jb4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZHANJUXIANGQING ls = new ZHANJUXIANGQING();
				ls.Lishi(tok);
			}
		});
		jp2.add(jb1);
		jp3.add(jb2);
		jp4.add(jb3);
		jp6.add(jb4);
		jp5.add(name);
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
