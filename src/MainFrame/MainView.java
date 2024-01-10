package MainFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InventoryFrame.FoodInfo;
import LoginFrame.DB;
import LoginFrame.Login;
import LoginFrame.MemberDAO;
import LoginFrame.MemberDTO;
import OrderCheckFrame.OrderCheck;
import Total.Total;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class MainView extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private UserInfoDAO udao;

   
   
   /**
    * Create the frame.
    */
   public MainView(String name) {
   	setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/image/gear.png")));
      setTitle("관리자 모드");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 923, 505);
      contentPane = new JPanel();
      contentPane.setBackground(new Color(255, 255, 221));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JButton btnOrder = new JButton("주문확인");
      btnOrder.setForeground(Color.BLACK);
      btnOrder.setBackground(SystemColor.controlHighlight);
      btnOrder.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnOrder.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            OrderCheck list =  new OrderCheck();
            list.setVisible(true);
         }
      });
      btnOrder.setBounds(473, 49, 127, 47);
      contentPane.add(btnOrder);
      
      JButton btnUser = new JButton("회원정보");
      btnUser.setForeground(Color.BLACK);
      btnUser.setBackground(SystemColor.controlHighlight);
      btnUser.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnUser.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            UserInfo info = new UserInfo();
            info.setVisible(true);
         }
      });
      btnUser.setBounds(612, 49, 119, 47);
      contentPane.add(btnUser);
      
      JButton btnInventory = new JButton("재고관리");
      btnInventory.setForeground(Color.BLACK);
      btnInventory.setBackground(SystemColor.controlHighlight);
      btnInventory.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnInventory.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            FoodInfo fi = new FoodInfo();
            fi.setVisible(true);
         }
      });
      btnInventory.setBounds(743, 49, 119, 47);
      contentPane.add(btnInventory);
      
      JPanel panel1 = new JPanel();
      panel1.setBackground(new Color(162, 197, 121));
      panel1.setBounds(39, 139, 155, 141);
      contentPane.add(panel1);
      panel1.setLayout(null);
      
      JLabel lblNewLabel_1 = new JLabel("1번");
      lblNewLabel_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1.setBounds(12, 10, 57, 15);
      panel1.add(lblNewLabel_1);
      
      
     
      JLabel lblUsetext1 = new JLabel("");
      lblUsetext1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      
      MemberDAO dao = new MemberDAO();
      dao.useCom(1, lblUsetext1);
      
      lblUsetext1.setBounds(12, 29, 115, 15);
      panel1.add(lblUsetext1);
      
      JButton btnInfo1 = new JButton("정보 확인");
      btnInfo1.setForeground(Color.BLACK);
      btnInfo1.setBackground(SystemColor.controlHighlight);
      btnInfo1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 1; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
            
         }
      });
      btnInfo1.setBounds(30, 108, 97, 23);
      panel1.add(btnInfo1);
      
      JLabel lblTime1 = new JLabel("");
      lblTime1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime1.setBounds(12, 83, 131, 15);
      dao.timeCom(1, lblTime1);
      panel1.add(lblTime1);
      
      JLabel lblPrice1 = new JLabel("");
      lblPrice1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice1.setBounds(12, 58, 131, 15);
      dao.priceCom(1, lblPrice1);
      panel1.add(lblPrice1);
      
      JPanel panel2 = new JPanel();
      panel2.setBackground(new Color(162, 197, 121));
      panel2.setBounds(206, 139, 155, 141);
      contentPane.add(panel2);
      panel2.setLayout(null);
      
      JButton btnInfo2 = new JButton("정보 확인");
      btnInfo2.setForeground(Color.BLACK);
      btnInfo2.setBackground(SystemColor.controlHighlight);
      btnInfo2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 2; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo2.setBounds(30, 108, 97, 23);
      panel2.add(btnInfo2);
      
      JLabel lblNewLabel_1_1 = new JLabel("2번");
      lblNewLabel_1_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_1.setBounds(12, 10, 57, 15);
      panel2.add(lblNewLabel_1_1);
      
      JLabel lblUsetext2 = new JLabel("");
      lblUsetext2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(2, lblUsetext2);
      lblUsetext2.setBounds(12, 29, 115, 15);
      panel2.add(lblUsetext2);
      
      JLabel lblTime2 = new JLabel("");
      lblTime2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime2.setBounds(12, 83, 131, 15);
      dao.timeCom(2, lblTime2);
      panel2.add(lblTime2);
      
      JLabel lblPrice2 = new JLabel("");
      lblPrice2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice2.setBounds(12, 54, 131, 15);
      dao.priceCom(2, lblPrice2);
      panel2.add(lblPrice2);
      
      JPanel panel3 = new JPanel();
      panel3.setBackground(new Color(162, 197, 121));
      panel3.setBounds(373, 139, 155, 141);
      contentPane.add(panel3);
      panel3.setLayout(null);
      
      JButton btnInfo3 = new JButton("정보 확인");
      btnInfo3.setForeground(Color.BLACK);
      btnInfo3.setBackground(SystemColor.controlHighlight);
      btnInfo3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 3; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo3.setBounds(29, 108, 97, 23);
      panel3.add(btnInfo3);
      
      JLabel lblNewLabel_1_2 = new JLabel("3번");
      lblNewLabel_1_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_2.setBounds(12, 10, 57, 15);
      panel3.add(lblNewLabel_1_2);
      
      JLabel lblUsetext3 = new JLabel("");
      lblUsetext3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(3, lblUsetext3);
      lblUsetext3.setBounds(12, 30, 115, 15);
      panel3.add(lblUsetext3);
      
      JLabel lblTime3 = new JLabel("");
      lblTime3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime3.setBounds(12, 83, 131, 15);
      dao.timeCom(3, lblTime3);
      panel3.add(lblTime3);
      
      JLabel lblPrice3 = new JLabel("");
      lblPrice3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice3.setBounds(12, 55, 131, 15);
      dao.priceCom(3, lblPrice3);
      panel3.add(lblPrice3);
      
      JPanel panel4 = new JPanel();
      panel4.setBackground(new Color(162, 197, 121));
      panel4.setBounds(540, 139, 155, 141);
      contentPane.add(panel4);
      panel4.setLayout(null);
      
      JButton btnInfo4 = new JButton("정보 확인");
      btnInfo4.setForeground(Color.BLACK);
      btnInfo4.setBackground(SystemColor.controlHighlight);
      btnInfo4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 4; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo4.setBounds(26, 108, 97, 23);
      panel4.add(btnInfo4);
      
      JLabel lblNewLabel_1_3 = new JLabel("4번");
      lblNewLabel_1_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_3.setBounds(12, 10, 57, 15);
      panel4.add(lblNewLabel_1_3);
      
      JLabel lblUsetext4 = new JLabel("");
      lblUsetext4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(4, lblUsetext4);
      lblUsetext4.setBounds(12, 29, 115, 15);
      panel4.add(lblUsetext4);
      
      JLabel lblTime4 = new JLabel("");
      lblTime4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime4.setBounds(12, 83, 131, 15);
      dao.timeCom(4, lblTime4);
      panel4.add(lblTime4);
      
      JLabel lblPrice4 = new JLabel("");
      lblPrice4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice4.setBounds(12, 54, 131, 15);
      dao.priceCom(4, lblPrice4);
      panel4.add(lblPrice4);
      
      JPanel panel5 = new JPanel();
      panel5.setBackground(new Color(162, 197, 121));
      panel5.setBounds(707, 139, 155, 141);
      contentPane.add(panel5);
      panel5.setLayout(null);
      
      JButton btnInfo5 = new JButton("정보 확인");
      btnInfo5.setForeground(Color.BLACK);
      btnInfo5.setBackground(SystemColor.controlHighlight);
      btnInfo5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 5; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo5.setBounds(30, 108, 97, 23);
      panel5.add(btnInfo5);
      
      
      
      JLabel lblNewLabel_1_4 = new JLabel("5번");
      lblNewLabel_1_4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_4.setBounds(12, 10, 57, 15);
      panel5.add(lblNewLabel_1_4);
      
      JLabel lblUsetext5 = new JLabel("");
      lblUsetext5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(5, lblUsetext5);
      lblUsetext5.setBounds(12, 29, 115, 15);
      panel5.add(lblUsetext5);
      
      JLabel lblTime5 = new JLabel("");
      lblTime5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime5.setBounds(12, 83, 131, 15);
      dao.timeCom(5, lblTime5);
      panel5.add(lblTime5);
      
      JLabel lblPrice5 = new JLabel("");
      lblPrice5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice5.setBounds(12, 54, 131, 15);
      dao.priceCom(5, lblPrice5);
      panel5.add(lblPrice5);
      
      JPanel panel6 = new JPanel();
      panel6.setBackground(new Color(162, 197, 121));
      panel6.setBounds(39, 305, 155, 141);
      contentPane.add(panel6);
      panel6.setLayout(null);
      
      JButton btnInfo6 = new JButton("정보 확인");
      btnInfo6.setForeground(Color.BLACK);
      btnInfo6.setBackground(SystemColor.controlHighlight);
      btnInfo6.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 6; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo6.setBounds(29, 108, 97, 23);
      panel6.add(btnInfo6);
      
      JLabel lblNewLabel_1_9 = new JLabel("6번");
      lblNewLabel_1_9.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_9.setBounds(12, 10, 57, 15);
      panel6.add(lblNewLabel_1_9);
      
      JLabel lblUsetext6 = new JLabel("");
      lblUsetext6.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(6, lblUsetext6);
      lblUsetext6.setBounds(12, 29, 115, 15);
      panel6.add(lblUsetext6);
      
      JLabel lblTime6 = new JLabel("");
      lblTime6.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime6.setBounds(12, 83, 131, 15);
      dao.timeCom(6, lblTime6);
      panel6.add(lblTime6);
      
      JLabel lblPrice6 = new JLabel("");
      lblPrice6.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice6.setBounds(12, 54, 131, 15);
      dao.priceCom(6, lblPrice6);
      panel6.add(lblPrice6);
      
      JPanel panel7 = new JPanel();
      panel7.setBackground(new Color(162, 197, 121));
      panel7.setBounds(206, 305, 155, 141);
      contentPane.add(panel7);
      panel7.setLayout(null);
      
      JButton btnInfo7 = new JButton("정보 확인");
      btnInfo7.setForeground(Color.BLACK);
      btnInfo7.setBackground(SystemColor.controlHighlight);
      btnInfo7.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo7.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 7; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo7.setBounds(30, 108, 97, 23);
      panel7.add(btnInfo7);
      
      JLabel lblNewLabel_1_8 = new JLabel("7번");
      lblNewLabel_1_8.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_8.setBounds(12, 10, 57, 15);
      panel7.add(lblNewLabel_1_8);
      
      JLabel lblUsetext7 = new JLabel("");
      lblUsetext7.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(7, lblUsetext7);
      lblUsetext7.setBounds(12, 29, 115, 15);
      panel7.add(lblUsetext7);
      
      JLabel lblTime7 = new JLabel("");
      lblTime7.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime7.setBounds(12, 83, 131, 15);
      dao.timeCom(7, lblTime7);
      panel7.add(lblTime7);
      
      JLabel lblPrice7 = new JLabel("");
      lblPrice7.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice7.setBounds(12, 58, 131, 15);
      dao.priceCom(7, lblPrice7);
      panel7.add(lblPrice7);
      
      JPanel panel8 = new JPanel();
      panel8.setBackground(new Color(162, 197, 121));
      panel8.setBounds(373, 305, 155, 141);
      contentPane.add(panel8);
      panel8.setLayout(null);
      
      JButton btnInfo8 = new JButton("정보 확인");
      btnInfo8.setForeground(Color.BLACK);
      btnInfo8.setBackground(SystemColor.controlHighlight);
      btnInfo8.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo8.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 8; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo8.setBounds(30, 108, 97, 23);
      panel8.add(btnInfo8);
      
      JLabel lblNewLabel_1_7 = new JLabel("8번");
      lblNewLabel_1_7.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_7.setBounds(12, 10, 57, 15);
      panel8.add(lblNewLabel_1_7);
      
      JLabel lblUsetext8 = new JLabel("");
      lblUsetext8.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(8, lblUsetext8);
      lblUsetext8.setBounds(12, 29, 115, 15);
      panel8.add(lblUsetext8);
      
      JLabel lblTime8 = new JLabel("");
      lblTime8.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime8.setBounds(12, 83, 131, 15);
      dao.timeCom(8, lblTime8);
      panel8.add(lblTime8);
      
      JLabel lblPrice8 = new JLabel("");
      lblPrice8.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice8.setBounds(12, 54, 131, 15);
      dao.priceCom(8, lblPrice8);
      panel8.add(lblPrice8);
      
      JPanel panel9 = new JPanel();
      panel9.setBackground(new Color(162, 197, 121));
      panel9.setBounds(540, 305, 155, 141);
      contentPane.add(panel9);
      panel9.setLayout(null);
      
      JButton btnInfo9 = new JButton("정보 확인");
      btnInfo9.setForeground(Color.BLACK);
      btnInfo9.setBackground(SystemColor.controlHighlight);
      btnInfo9.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo9.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 9; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo9.setBounds(30, 108, 97, 23);
      panel9.add(btnInfo9);
      
      JLabel lblNewLabel_1_6 = new JLabel("9번");
      lblNewLabel_1_6.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_6.setBounds(12, 10, 57, 15);
      panel9.add(lblNewLabel_1_6);
      
      JLabel lblUsetext9 = new JLabel("");
      lblUsetext9.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(9, lblUsetext9);
      lblUsetext9.setBounds(12, 29, 115, 15);
      panel9.add(lblUsetext9);
      
      JLabel lblTime9 = new JLabel("");
      lblTime9.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime9.setBounds(12, 83, 131, 15);
      dao.timeCom(9, lblTime9);
      panel9.add(lblTime9);
      
      JLabel lblPrice9 = new JLabel("");
      lblPrice9.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice9.setBounds(12, 54, 131, 15);
      dao.priceCom(9, lblPrice9);
      panel9.add(lblPrice9);
      
      JPanel panel10 = new JPanel();
      panel10.setBackground(new Color(162, 197, 121));
      panel10.setBounds(707, 305, 155, 141);
      contentPane.add(panel10);
      panel10.setLayout(null);
      
      JButton btnInfo10 = new JButton("정보 확인");
      btnInfo10.setForeground(Color.BLACK);
      btnInfo10.setBackground(SystemColor.controlHighlight);
      btnInfo10.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnInfo10.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int seatNumber = 10; // 각 버튼에 해당하는 좌석 번호로 수정하세요
            dao.selectInfoCheck(seatNumber);
         }
      });
      btnInfo10.setBounds(30, 108, 97, 23);
      panel10.add(btnInfo10);
      
      JLabel lblNewLabel_1_5 = new JLabel("10번");
      lblNewLabel_1_5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblNewLabel_1_5.setBounds(12, 10, 57, 15);
      panel10.add(lblNewLabel_1_5);
      
      JLabel lblUsetext10 = new JLabel("");
      lblUsetext10.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      dao.useCom(10, lblUsetext10);
      lblUsetext10.setBounds(12, 29, 115, 15);
      panel10.add(lblUsetext10);
      
      JLabel lblTime10 = new JLabel("");
      lblTime10.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblTime10.setBounds(12, 83, 131, 15);
      dao.timeCom(10, lblTime10);
      panel10.add(lblTime10);
      
      JLabel lblPrice10 = new JLabel("");
      lblPrice10.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      lblPrice10.setBounds(12, 54, 131, 15);
      dao.priceCom(10, lblPrice10);
      panel10.add(lblPrice10);
      
      JLabel lblNewLabel = new JLabel("관리자 :");
      lblNewLabel.setForeground(Color.BLACK);
      lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblNewLabel.setBounds(26, 79, 57, 15);
      contentPane.add(lblNewLabel);
      
     
      
      
      JLabel lblAdmin = new JLabel("");
      lblAdmin.setForeground(Color.BLACK);
      lblAdmin.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblAdmin.setBounds(84, 79, 76, 15);
      
      lblAdmin.setText(name);
      
      
      
      contentPane.add(lblAdmin);
      
      JButton btnLogout = new JButton("로그아웃");
      btnLogout.setForeground(Color.BLACK);
      btnLogout.setBackground(SystemColor.controlHighlight);
      btnLogout.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnLogout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Login lo = new Login();
                lo.setVisible(true);
                dispose();
         }
      });
      btnLogout.setBounds(172, 73, 97, 23);
      contentPane.add(btnLogout);
      
      JButton btnRefresh = new JButton("새로고침");
      btnRefresh.setForeground(Color.BLACK);
      btnRefresh.setBackground(SystemColor.controlHighlight);
      btnRefresh.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnRefresh.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainView main = new MainView(name);
            main.setVisible(true);
            dispose();
         }
      });
      btnRefresh.setBounds(172, 40, 97, 23);
      contentPane.add(btnRefresh);
      
      JLabel lblMost = new JLabel("");
      lblMost.setForeground(Color.BLACK);
      lblMost.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblMost.setBounds(84, 45, 76, 15);
      contentPane.add(lblMost);
      udao = new UserInfoDAO();
      
      lblMost.setText(udao.mostPrice());
      
      JLabel lblChlrhaocnf = new JLabel("매출왕 :");
      lblChlrhaocnf.setForeground(Color.BLACK);
      lblChlrhaocnf.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblChlrhaocnf.setBounds(26, 45, 57, 15);
      contentPane.add(lblChlrhaocnf);
      
      JButton btnTotalPrice = new JButton("매출");
      btnTotalPrice.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		Total tt = new Total();
      		tt.setVisible(true);
      	}
      });
      btnTotalPrice.setForeground(Color.BLACK);
      btnTotalPrice.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnTotalPrice.setBackground(SystemColor.controlHighlight);
      btnTotalPrice.setBounds(334, 49, 127, 47);
      contentPane.add(btnTotalPrice);
   }
}