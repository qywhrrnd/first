package LoginFrame;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainFrame.MainUser;
import MainFrame.MainView;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class SelectSeat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBack;

	/**
	 * Create the frame.
	 * 
	 * @param name
	 * @param time 
	 * @param login
	 */
	public SelectSeat(String name, String userId, int time) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectSeat.class.getResource("/image/guri.png")));
		setTitle("좌석 선택");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 436, 270);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(21, 155, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn1 = new JButton("1번");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.DARK_GRAY);
		btn1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(1);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "1번자리입니다.");
					dao.insertSeat(new MemberDTO(1, userId));
					MainUser main = new MainUser(name, userId, time, 1);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn1.setBounds(12, 46, 68, 55);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2번");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.DARK_GRAY);
		btn2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(2);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "2번자리입니다.");
					dao.insertSeat(new MemberDTO(2, userId));
					MainUser main = new MainUser(name, userId, time, 2);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn2.setBounds(92, 46, 68, 55);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3번");
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.DARK_GRAY);
		btn3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(3);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "3번자리입니다.");
					dao.insertSeat(new MemberDTO(3, userId));
					MainUser main = new MainUser(name, userId, time, 3);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn3.setBounds(172, 46, 68, 55);
		contentPane.add(btn3);

		JButton btn4 = new JButton("4번");
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.DARK_GRAY);
		btn4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(4);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "4번자리입니다.");
					dao.insertSeat(new MemberDTO(4, userId));
					MainUser main = new MainUser(name, userId, time, 4);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn4.setBounds(252, 46, 68, 55);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5번");
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.DARK_GRAY);
		btn5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(5);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "5번자리입니다.");
					dao.insertSeat(new MemberDTO(5, userId));
					MainUser main = new MainUser(name, userId, time, 5);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn5.setBounds(332, 46, 68, 55);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6번");
		btn6.setForeground(Color.WHITE);
		btn6.setBackground(Color.DARK_GRAY);
		btn6.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(6);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "6번자리입니다.");
					dao.insertSeat(new MemberDTO(6, userId));
					MainUser main = new MainUser(name, userId, time, 6);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn6.setBounds(12, 138, 68, 55);
		contentPane.add(btn6);

		JButton btn7 = new JButton("7번");
		btn7.setForeground(Color.WHITE);
		btn7.setBackground(Color.DARK_GRAY);
		btn7.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(7);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "7번자리입니다.");
					dao.insertSeat(new MemberDTO(7, userId));
					MainUser main = new MainUser(name, userId, time, 7);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn7.setBounds(92, 138, 68, 55);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8번");
		btn8.setForeground(Color.WHITE);
		btn8.setBackground(Color.DARK_GRAY);
		btn8.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(8);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "8번자리입니다.");
					dao.insertSeat(new MemberDTO(8, userId));
					MainUser main = new MainUser(name, userId, time, 8);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn8.setBounds(172, 138, 68, 55);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9번");
		btn9.setForeground(Color.WHITE);
		btn9.setBackground(Color.DARK_GRAY);
		btn9.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(9);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "9번자리입니다.");
					dao.insertSeat(new MemberDTO(9, userId));
					MainUser main = new MainUser(name, userId, time, 9);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn9.setBounds(252, 138, 68, 55);
		contentPane.add(btn9);

		JButton btn10 = new JButton("10번");
		btn10.setForeground(Color.WHITE);
		btn10.setBackground(Color.DARK_GRAY);
		btn10.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 11));
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();

				boolean isCheck = MemberDAO.isCheckSeat(10);

				if (isCheck == true) {
					JOptionPane.showMessageDialog(SelectSeat.this, "10번자리입니다.");
					dao.insertSeat(new MemberDTO(10, userId));
					MainUser main = new MainUser(name, userId, time, 10);
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(SelectSeat.this, "이미 있는 자리입니다.");

				}

			}
		});
		btn10.setBounds(332, 138, 68, 55);
		contentPane.add(btn10);
		
		lblBack = new JLabel("");
		ImageIcon icon = new ImageIcon(SelectSeat.class.getResource("/image/lol.png"));
		
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(436, 270, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		lblBack.setIcon(updateIcon);
        
		lblBack.setBounds(0, 0, 436, 270);
		lblBack.setHorizontalAlignment(JLabel.CENTER);
		
    	getContentPane().add(lblBack);
    	setVisible(true);
		contentPane.add(lblBack);

	}
}