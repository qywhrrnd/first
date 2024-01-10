package LoginFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class SearchPwd extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField tfId;
   private MemberDAO dao;
   private JLabel lblPwd;

   /**
    * Create the frame.
    */
   public SearchPwd() {
   	setTitle("비밀번호 찾기");
   	setIconImage(Toolkit.getDefaultToolkit().getImage(SearchPwd.class.getResource("/image/guri.png")));
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 352, 250);
      contentPane = new JPanel();
      contentPane.setBackground(Color.DARK_GRAY);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblId = new JLabel("비밀번호를 찾고자 하는 아이디를 입력해주세요.");
      lblId.setForeground(Color.WHITE);
      lblId.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblId.setBounds(24, 53, 300, 15);
      contentPane.add(lblId);

      tfId = new JTextField();
      tfId.setBounds(102, 89, 132, 21);
      contentPane.add(tfId);
      tfId.setColumns(10);
      dao = new MemberDAO();
      JButton btnSearchPwd = new JButton("비밀번호 찾기");
      btnSearchPwd.setBackground(new Color(172, 172, 172));
      btnSearchPwd.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnSearchPwd.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            String id = tfId.getText();

            if (id.isEmpty()) {
               JOptionPane.showMessageDialog(SearchPwd.this, "아이디를 입력하세요");
            } else {
               dao.searchPwd(id, lblPwd);
            }
         }
      });
      btnSearchPwd.setBounds(102, 120, 132, 23);
      contentPane.add(btnSearchPwd);

      lblPwd = new JLabel("");
      lblPwd.setForeground(Color.GREEN);
      lblPwd.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
      lblPwd.setBounds(29, 146, 279, 23);
      contentPane.add(lblPwd);
      
      JLabel lblNewLabel = new JLabel("너굴PC방");
      lblNewLabel.setForeground(SystemColor.info);
      lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
      lblNewLabel.setBounds(120, 21, 97, 28);
      contentPane.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("아이디가 기억나지 않는다면?");
      lblNewLabel_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblNewLabel_1.setBackground(SystemColor.activeCaption);
      lblNewLabel_1.setForeground(new Color(206, 206, 206));
      lblNewLabel_1.setBounds(24, 179, 178, 15);
      contentPane.add(lblNewLabel_1);
      
      JButton btnFindId = new JButton("아이디 찾기");
      btnFindId.setForeground(Color.WHITE);
      btnFindId.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnFindId.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            SearchId id = new SearchId();
            id.setVisible(true);
            
         }
      });
      btnFindId.setBackground(SystemColor.activeCaption);
      btnFindId.setBounds(207, 175, 117, 23);
      btnFindId.setBorderPainted(false);
      btnFindId.setFocusPainted(false);
      btnFindId.setContentAreaFilled(false);
      contentPane.add(btnFindId);
   }
}