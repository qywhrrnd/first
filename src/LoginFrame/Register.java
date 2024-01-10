package LoginFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class Register extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField tfUserid;
   private JPasswordField pass;
   private JTextField tfName;
   private JLabel lblResult;
   private JButton btnSave;
   private JTextField tfEmail;
   private JTextField tfPhone;
   private JTextField tfBirth;
   private JLabel lblNewLabel_3;
   private JPasswordField pass2;
   private JLabel lblResult2;
   private JLabel lblResult3;
   private JComboBox combo;

   /**
    * Create the frame.
    */
   public Register(Login parent) {
   	setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/image/guri.png")));
      setTitle("회원가입");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 434, 418);
      contentPane = new JPanel();
      contentPane.setForeground(Color.WHITE);
      contentPane.setBackground(Color.DARK_GRAY);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("아이디");
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel.setBounds(65, 34, 57, 15);
      contentPane.add(lblNewLabel);

      tfUserid = new JTextField();
      tfUserid.setToolTipText("");
      tfUserid.setColumns(10);
      tfUserid.setBounds(124, 32, 116, 21);
      contentPane.add(tfUserid);

      JButton btnCheck = new JButton("아이디확인");
      btnCheck.setBackground(SystemColor.controlHighlight);
      btnCheck.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnCheck.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String strUserId = tfUserid.getText();

            if (strUserId.isEmpty()) {
               JOptionPane.showMessageDialog(Register.this, "ID를 입력하세요.");
            } else if (strUserId.length() < 5) {
               JOptionPane.showMessageDialog(Register.this, "ID를 더 길게 입력해주세요");
            } else {
               boolean isAvailable = MemberDAO.isUserIdAvailable(strUserId);
               if (isAvailable) {
                  JOptionPane.showMessageDialog(Register.this, "사용가능한 아이디입니다.");
                  lblResult.setForeground(new Color(21, 155, 38));
                  lblResult.setText("사용가능한 아이디입니다.");
                  btnSave.setEnabled(true);

               } else {
                  JOptionPane.showMessageDialog(Register.this, "중복된 아이디입니다.");
                  lblResult.setForeground(Color.RED);
                  lblResult.setText("사용불가능한 아이디입니다.");
                  btnSave.setEnabled(false);
               }
            }
         }
      });

      combo = new JComboBox<>();
      combo.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      combo.setEditable(true);
      String[] emailOptions = { "직접입력", "@naver.com", "@google.com", "@daum.net" };
      combo.setModel(new DefaultComboBoxModel<>(emailOptions));
      combo.setBounds(251, 202, 102, 22);
      contentPane.add(combo);
      String selectedEmailDomain = (String) combo.getSelectedItem();

      btnCheck.setEnabled(true);
      btnCheck.setBounds(288, 30, 97, 23);
      contentPane.add(btnCheck);

      JLabel lblNewLabel_1 = new JLabel("비밀번호");
      lblNewLabel_1.setForeground(Color.WHITE);
      lblNewLabel_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1.setBounds(55, 71, 57, 15);
      contentPane.add(lblNewLabel_1);

      pass = new JPasswordField();
      pass.setBounds(124, 69, 116, 21);
      contentPane.add(pass);

      JLabel lblNewLabel_2 = new JLabel("이름");
      lblNewLabel_2.setForeground(Color.WHITE);
      lblNewLabel_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_2.setBounds(55, 173, 57, 15);
      contentPane.add(lblNewLabel_2);

      tfName = new JTextField();
      tfName.setColumns(10);
      tfName.setBounds(124, 171, 116, 21);
      contentPane.add(tfName);

      lblResult = new JLabel("");
      lblResult.setForeground(new Color(21, 155, 38));
      lblResult.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblResult.setBounds(252, 64, 170, 15);
      contentPane.add(lblResult);

      btnSave = new JButton("확인");
      btnSave.setBackground(SystemColor.controlHighlight);
      btnSave.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnSave.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String strUserId = tfUserid.getText();
            String strPass = String.valueOf(pass.getPassword());
            String strName = tfName.getText();
            String strEmail = tfEmail.getText();
            String strPhone = tfPhone.getText();
            String strBirth = tfBirth.getText();
            String strPass2 = String.valueOf(pass2.getPassword());

            String selectedEmailDomain = (String) combo.getSelectedItem();

            if (selectedEmailDomain.equals("직접입력")) {

               strEmail = tfEmail.getText();
            } else {

               strEmail = tfEmail.getText() + selectedEmailDomain;
            }

            // MemberDTO 객체 생성
            MemberDTO member = new MemberDTO(strUserId, strPass, strName, strEmail, strPhone, strBirth);

            try {
               String[] spe = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")" };
               boolean b = false;

               int pwd = strPass.length();
               int name = strName.length();
               int email = strEmail.length();
               int phone = strPhone.length();
               int birth = strBirth.length();
               int pwd2 = strPass2.length();
               boolean c = false;
               boolean d = false;

               for (int i = 0; i < selectedEmailDomain.length(); i++) {
                  if (selectedEmailDomain.contains("@")) {
                     d = true;
                     break;
                  }
               }

               for (;;) {
                  if (pwd == pwd2) {
                     if (strPass.equals(strPass2)) {
                        c = true;
                        break;
                     }
                  }
                  break;
               }

               for (String s : spe) {
                  if (strPass.contains(s)) {
                     b = true;
                     break;
                  }
               }

               if (pwd == 0) {
                  JOptionPane.showMessageDialog(Register.this, "비밀번호를 입력하세요");
                  pass.requestFocus();
               } else if (pwd < 5) {
                  JOptionPane.showMessageDialog(Register.this, "더 길게 입력하세요");
                  pass.requestFocus();
               } else if (b != true) {
                  JOptionPane.showMessageDialog(Register.this, "특수문자를 입력하세요");
                  pass.requestFocus();

               } else if (pwd2 == 0) {
                  JOptionPane.showMessageDialog(Register.this, "비밀번호를 재입력 해주세요");
                  pass2.requestFocus();

               } else if (c != true) {
                  lblResult2.setText("");
                  lblResult3.setText("비밀번호가 일치하지 않습니다.");
                  pass2.requestFocus();
               } else {
                  lblResult3.setText("");
                  lblResult2.setText("비밀번호가 일치합니다");
                  if (name == 0) {
                     JOptionPane.showMessageDialog(Register.this, "이름을 입력하세요");
                     tfName.requestFocus();

                  } else if (email == 0) {
                     JOptionPane.showMessageDialog(Register.this, "메일을 입력하세요");
                     tfEmail.requestFocus();
                  } else if(d != true) {
                     JOptionPane.showMessageDialog(Register.this, "이메일 형식이 아닙니다");
                     tfEmail.requestFocus();
                  } else if (phone == 0) {
                     JOptionPane.showMessageDialog(Register.this, "번호를 입력하세요");
                     tfPhone.requestFocus();

                  } else if (phone != 11 && phone != 10) {
                     JOptionPane.showMessageDialog(Register.this, "-를 제외한 10,11자리의 숫자만 입력해주세요");
                     tfPhone.requestFocus();

                  } else if (birth == 0) {
                     JOptionPane.showMessageDialog(Register.this, "생년월일을 입력하세요");
                     tfBirth.requestFocus();

                  } else if (birth != 6) {
                     JOptionPane.showMessageDialog(Register.this, "생년월일 6자리로 입력하세요");
                     tfBirth.requestFocus();

                  } else {
                     MemberDAO.insertMember(member);
                     JOptionPane.showMessageDialog(Register.this, "회원가입 되었습니다.");
                     dispose();

                  }
               }
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(Register.this, e1.getMessage());

            }

         }

      });
      btnSave.setEnabled(false);
      btnSave.setBounds(288, 337, 97, 23);
      contentPane.add(btnSave);

      JLabel lblNewLabel_2_1 = new JLabel("이메일");
      lblNewLabel_2_1.setForeground(Color.WHITE);
      lblNewLabel_2_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_2_1.setBounds(44, 205, 57, 15);
      contentPane.add(lblNewLabel_2_1);

      JLabel lblNewLabel_2_2 = new JLabel("전화번호");
      lblNewLabel_2_2.setForeground(Color.WHITE);
      lblNewLabel_2_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_2_2.setBounds(31, 246, 57, 15);
      contentPane.add(lblNewLabel_2_2);

      JLabel lblNewLabel_2_3 = new JLabel("생년월일");
      lblNewLabel_2_3.setForeground(Color.WHITE);
      lblNewLabel_2_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_2_3.setBounds(31, 282, 57, 15);
      contentPane.add(lblNewLabel_2_3);

      tfEmail = new JTextField();
      tfEmail.setColumns(10);
      tfEmail.setBounds(124, 203, 116, 21);
      contentPane.add(tfEmail);

      tfPhone = new JTextField();
      tfPhone.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
               e.consume();
            }
         }
      });
      tfPhone.setColumns(10);
      tfPhone.setBounds(124, 244, 116, 21);
      contentPane.add(tfPhone);

      tfBirth = new JTextField();
      tfBirth.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
               e.consume();
            }
         }
      });
      tfBirth.setColumns(10);
      tfBirth.setBounds(124, 280, 116, 21);
      contentPane.add(tfBirth);

      lblNewLabel_3 = new JLabel("비밀번호 확인");
      lblNewLabel_3.setForeground(Color.WHITE);
      lblNewLabel_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_3.setBounds(25, 108, 87, 16);
      contentPane.add(lblNewLabel_3);

      pass2 = new JPasswordField();
      pass2.setBounds(123, 107, 116, 21);
      contentPane.add(pass2);

      lblResult2 = new JLabel("");
      lblResult2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblResult2.setForeground(new Color(43, 157, 54));
      lblResult2.setBounds(124, 140, 261, 15);
      contentPane.add(lblResult2);

      lblResult3 = new JLabel("");
      lblResult3.setForeground(new Color(255, 0, 0));
      lblResult3.setBounds(124, 140, 261, 15);
      contentPane.add(lblResult3);

   }
}