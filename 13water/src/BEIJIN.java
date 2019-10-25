package GAME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BEIJIN {
	int ok = 0;
	JFrame jf = new JFrame("±³¾°");
	void LoginOrRegister() {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1135,638);
		jf.setLocation(50, 50);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JLabel jl = new JLabel();
		jp1.setBounds(0, 0, 1135, 638);
		jp1.setOpaque(true);
		jp2.setBounds(353, 427, 145, 74);
		jp2.setOpaque(true);
		jp3.setBounds(675, 427, 152, 74);
		jp3.setOpaque(true);
		jl.setBounds(0, 0, 1135, 638);
		Icon icon = new ImageIcon("images/±³¾°.jpg");
		Icon denglu = new ImageIcon("icon/µÇÂ¼icon.png");
		Icon zhuce = new ImageIcon("icon/×¢²áicon.png");
		jl.setIcon(icon);
		JButton jb1 = new JButton();
		JButton jb2 = new JButton();
		jb1.setIcon(denglu);
		jb1.setBounds(0, 0, 145, 74);
		jb2.setIcon(zhuce);
		jb2.setBounds(0, 0, 152, 74);
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok = 1;
				jf.setVisible(false);
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				REGISTER l = new REGISTER();
				l.Register();
				ok = 2;
				//jf.setVisible(false);
			}
		});
		jp2.add(jb1);
		jp3.add(jb2);
		jp1.add(jl);
		jp2.setLayout(null);
		jp3.setLayout(null);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp1);
		jf.pack();
		jf.setVisible(true);
	}
}
