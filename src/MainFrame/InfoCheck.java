package MainFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LoginFrame.Login;
import LoginFrame.MemberDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class InfoCheck extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUserid2;
	private JTextField tfPwd2;
	private JTextField tfName2;
	private JTextField tfEmail2;
	private JTextField tfPhone2;
	private JTextField tfBirth2;


	/**
	 * Create the frame.
	 */
	//public InfoCheck(String userid,String pwd,String name, String email, String phone, String birth, int seatno) {
    public InfoCheck(String userId) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoCheck.class.getResource("/image/gear.png")));	
		setTitle("사용자 정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 254, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 218, 142));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel.setBounds(22, 30, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfUserid2 = new JTextField();
		tfUserid2.setEditable(false);
		tfUserid2.setBounds(91, 27, 116, 21);
		contentPane.add(tfUserid2);
		tfUserid2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_1.setBounds(22, 65, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfPwd2 = new JTextField();
		tfPwd2.setEditable(false);
		tfPwd2.setBounds(91, 62, 116, 21);
		contentPane.add(tfPwd2);
		tfPwd2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setForeground(SystemColor.desktop);
		lblNewLabel_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_2.setBounds(22, 100, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfName2 = new JTextField();
		tfName2.setEditable(false);
		tfName2.setBounds(91, 98, 116, 21);
		contentPane.add(tfName2);
		tfName2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("이메일");
		lblNewLabel_3.setForeground(SystemColor.desktop);
		lblNewLabel_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_3.setBounds(22, 134, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfEmail2 = new JTextField();
		tfEmail2.setEditable(false);
		tfEmail2.setBounds(91, 132, 116, 21);
		contentPane.add(tfEmail2);
		tfEmail2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("전화번호");
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_4.setBounds(22, 169, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfPhone2 = new JTextField();
		tfPhone2.setEditable(false);
		tfPhone2.setBounds(91, 167, 116, 21);
		contentPane.add(tfPhone2);
		tfPhone2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("생년월일");
		lblNewLabel_5.setForeground(SystemColor.desktop);
		lblNewLabel_5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_5.setBounds(22, 201, 57, 15);
		contentPane.add(lblNewLabel_5);
		
		tfBirth2 = new JTextField();
		tfBirth2.setEditable(false);
		tfBirth2.setBounds(91, 199, 116, 21);
		contentPane.add(tfBirth2);
		tfBirth2.setColumns(10);
		
		
		
		
		JButton btnClose2 = new JButton("닫기");
		btnClose2.setBackground(SystemColor.controlHighlight);
		btnClose2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
		btnClose2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose2.setBounds(67, 245, 97, 23);
		contentPane.add(btnClose2);
		
		JButton btnOut = new JButton("종료");
		btnOut.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
		btnOut.setBackground(SystemColor.controlHighlight);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int chan = JOptionPane.showConfirmDialog(InfoCheck.this, "종료시키겠습니까?", "종료",
		                  JOptionPane.YES_NO_OPTION);
		            if (chan == JOptionPane.YES_OPTION) {
		               JOptionPane.showMessageDialog(InfoCheck.this, "사용종료되었습니다.");
		               MemberDAO dao = new MemberDAO();
		               MemberDAO.stackPrice(userId);
						dao.deleteSeat(userId);
			            MemberDAO.deletePrice(userId);
		                dispose();
		            }
	            
			}
		});
		btnOut.setBounds(67, 281, 97, 23);
		contentPane.add(btnOut);
	}
    
    public void setInfo(String userId, String pwd, String name, String email, String phone, String birth) {
        tfUserid2.setText(userId);
        tfPwd2.setText(pwd);
        tfName2.setText(name);
        tfEmail2.setText(email);
        tfPhone2.setText(phone);
        tfBirth2.setText(birth);
    }
}
