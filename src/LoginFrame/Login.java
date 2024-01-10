package LoginFrame;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import InventoryFrame.FoodInfo;
import MainFrame.MainView;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {

   private static final long serialVersionUID = 1L;
   public static Object rs;
   private JPanel contentPane;
   private JTextField userid;
   private JPasswordField pwd;
   private JLabel lblResult;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Login frame = new Login();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */

   public Login() {
      setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/guri.png")));
      setTitle("너굴PC방");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      setBounds(100, 100, 423, 490);
      contentPane = new JPanel();
      contentPane.setBackground(Color.DARK_GRAY);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JButton btnLogin = new JButton("로그인");
      btnLogin.setBackground(Color.DARK_GRAY);
      btnLogin.setForeground(Color.WHITE);
      btnLogin.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnLogin.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String strUserid = userid.getText();
            String strpwd = String.valueOf(pwd.getPassword());
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            try {
               conn = DB.dbConn();
               String sql = "select * from member where userid =? and pwd =?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, strUserid);
               pstmt.setString(2, strpwd);
               rs = pstmt.executeQuery();
               if (rs.next()) {
                  lblResult.setText(rs.getString("name") + "님 환영합니다.");
                  if (rs.getString("userid").equals("admin")) {
                     MainView main = new MainView(rs.getString("name"));
                     main.setVisible(true);
                     dispose();
                  } else {
                     SelectSeat form = new SelectSeat(rs.getString("name"), strUserid, rs.getInt("time"));
                     form.setVisible(true);
                     dispose();
                  }
               }else if(strUserid.isEmpty()) {
            	   JOptionPane.showMessageDialog(Login.this, "아이디를 입력하세요.");
               }else if(strpwd.isEmpty()){
            	   JOptionPane.showMessageDialog(Login.this, "비밀번호를 입력하세요.");
               }else {
                  lblResult.setText("아이디 비번 틀림");
               }
            } catch (Exception e2) {
               e2.printStackTrace();
            } finally {
               try {
                  if (rs != null)
                     rs.close();

               } catch (SQLException e1) {
                  e1.printStackTrace();
               }
               try {
                  if (pstmt != null)
                     pstmt.close();
               } catch (SQLException e1) {
                  e1.printStackTrace();
               }
               try {
                  if (conn != null)
                     conn.close();
               } catch (SQLException e1) {
                  e1.printStackTrace();
               }
            }

         }
      });

      btnLogin.setBounds(277, 347, 118, 23);
      contentPane.add(btnLogin);

      InputMap inputMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
      ActionMap actionMap = contentPane.getActionMap();
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "login");
      actionMap.put("login", new AbstractAction() {
         @Override
         public void actionPerformed(ActionEvent e) {
            btnLogin.doClick();
         }
      });

      JButton btnSign = new JButton("회원가입");
      btnSign.setBackground(Color.DARK_GRAY);
      btnSign.setForeground(Color.WHITE);
      btnSign.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnSign.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Register form = new Register(Login.this);
            form.setVisible(true);

         }
      });
      btnSign.setBounds(277, 314, 118, 23);
      contentPane.add(btnSign);

      userid = new JTextField();
      userid.setBounds(133, 315, 116, 21);
      contentPane.add(userid);
      userid.setColumns(10);
      
      userid.setFocusTraversalKeysEnabled(false);
      InputMap inputMap1 = userid.getInputMap(JComponent.WHEN_FOCUSED);
      ActionMap actionMap1 = userid.getActionMap();
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "transferFocus");
      actionMap.put("transferFocus", new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
                pwd.requestFocusInWindow();
            }
      });
      
      JLabel lblId = new JLabel("아이디");
      lblId.setForeground(Color.WHITE);
      lblId.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
      lblId.setBounds(64, 318, 57, 15);
      contentPane.add(lblId);

      JLabel lblpwd = new JLabel("비밀번호");
      lblpwd.setForeground(Color.WHITE);
      lblpwd.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
      lblpwd.setBounds(51, 350, 70, 15);
      contentPane.add(lblpwd);

      pwd = new JPasswordField();
      pwd.setBounds(133, 346, 116, 21);
      contentPane.add(pwd);

      lblResult = new JLabel("");
      lblResult.setForeground(new Color(255, 36, 36));
      lblResult.setBounds(95, 369, 169, 42);
      contentPane.add(lblResult);

      ImageIcon icon = new ImageIcon(Login.class.getResource("/image/gul2.png"));

      Image img = icon.getImage();
      Image updateImg = img.getScaledInstance(383, 202, Image.SCALE_SMOOTH);
      ImageIcon updateIcon = new ImageIcon(updateImg);

      JPanel panel = new JPanel();
      panel.setBackground(new Color(255, 255, 255));
      panel.setBounds(12, 208, 383, 76);
      contentPane.add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("너굴PC방에 온걸 환영한다 인간들아!\r\n");
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 21));
      lblNewLabel.setBounds(12, 10, 359, 55);
      panel.add(lblNewLabel);

      JLabel lblNugul = new JLabel("");
      lblNugul.setBackground(Color.DARK_GRAY);
      lblNugul.setBounds(12, 10, 383, 202);
      contentPane.add(lblNugul);

      lblNugul.setIcon(updateIcon);

      lblNugul.setHorizontalAlignment(JLabel.CENTER);
      
      JButton btnId = new JButton("아이디 찾기");
      btnId.setForeground(new Color(0, 0, 0));
      btnId.setBackground(new Color(172, 172, 172));
      btnId.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnId.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		SearchId id = new SearchId();
            id.setVisible(true);
      	}
      });
      btnId.setBounds(277, 380, 118, 23);
      contentPane.add(btnId);
      
      JButton btnPwd = new JButton("비밀번호찾기");
      btnPwd.setForeground(new Color(0, 0, 0));
      btnPwd.setBackground(new Color(172, 172, 172));
      btnPwd.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnPwd.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		SearchPwd pwd = new SearchPwd();
            pwd.setVisible(true);
      	}
      });
      btnPwd.setBounds(277, 408, 118, 23);
      contentPane.add(btnPwd);
      setVisible(true);


   }
}