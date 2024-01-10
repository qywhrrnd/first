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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class SearchId extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField tfName;
   private JTextField tfBirth;
   private MemberDAO dao;

   /**
    * Create the frame.
    */
   public SearchId() {
   	setTitle("아이디 찾기");
   	setIconImage(Toolkit.getDefaultToolkit().getImage(SearchId.class.getResource("/image/guri.png")));
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 313, 279);
      contentPane = new JPanel();
      contentPane.setBackground(Color.DARK_GRAY);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblName = new JLabel("이름을 입력하세요");
      lblName.setForeground(Color.WHITE);
      lblName.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblName.setBounds(92, 53, 116, 15);
      contentPane.add(lblName);

      JLabel lblId = new JLabel("");
      lblId.setHorizontalAlignment(SwingConstants.CENTER);
      lblId.setForeground(Color.GREEN);
      lblId.setBackground(Color.WHITE);
      lblId.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblId.setBounds(29, 198, 242, 15);
      contentPane.add(lblId);

      tfName = new JTextField();
      tfName.setBounds(92, 78, 116, 21);
      contentPane.add(tfName);
      tfName.setColumns(10);

      JLabel lblBirth = new JLabel("생년월일을 입력하세요");
      lblBirth.setForeground(Color.WHITE);
      lblBirth.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblBirth.setBounds(81, 109, 162, 15);
      contentPane.add(lblBirth);

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
      tfBirth.setBounds(92, 134, 116, 21);
      contentPane.add(tfBirth);
      dao = new MemberDAO();

      JButton btnSearchId = new JButton("아이디 찾기");
      btnSearchId.setBackground(new Color(172, 172, 172));
      btnSearchId.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnSearchId.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String birthText = tfBirth.getText();
            String name = tfName.getText();

            if (name.isEmpty()) {
               JOptionPane.showMessageDialog(SearchId.this, "이름을 입력하세요");
            } else if (birthText.isEmpty()) {
               JOptionPane.showMessageDialog(SearchId.this, "생년월일을 입력하세요");
            } else {
               int birth = Integer.parseInt(tfBirth.getText());
               dao.searchId(name, birth, lblId);
            }
         }
      });

      btnSearchId.setBounds(92, 165, 116, 23);
      contentPane.add(btnSearchId);
      
      JLabel lblNewLabel = new JLabel("너굴PC방");
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setForeground(SystemColor.info);
      lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 25));
      lblNewLabel.setBounds(77, 10, 143, 28);
      contentPane.add(lblNewLabel);
   }
}