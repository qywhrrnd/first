package MainFrame;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import FoodFrame.FoodOrder;
import LoginFrame.DB;
import LoginFrame.Login;
import LoginFrame.MemberDAO;
import LoginFrame.MemberDTO;
import LoginFrame.SelectSeat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Font;

public class MainUser extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private MemberDAO dao;
   private Timer timer;
   private ScheduledExecutorService scheduler;
   private JButton btnStart;
   private JButton btnRpg;
   private JButton btnFps;
   private JButton btnSports;
   private JButton btnMess;
   private JTextPane textPane;
   private Image img;
   public JLabel lblprice;
   

   /**
    * Create the frame.
    */
   public MainUser(String name, String userId, int time, int seatno) {
      setIconImage(Toolkit.getDefaultToolkit().getImage(MainUser.class.getResource("/image/guri.png")));
      setTitle("사용자 화면");

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 705, 483);
      contentPane = new JPanel();
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JPanel panel = new JPanel();
      panel.setBackground(new Color(255,0,0,0));
      panel.setBounds(43, 208, 594, 226);
      contentPane.add(panel);
      panel.setLayout(null);

      JButton btnUserOrder = new JButton("상품주문");
      btnUserOrder.setForeground(Color.WHITE);
      btnUserOrder.setBackground(Color.DARK_GRAY);
      btnUserOrder.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnUserOrder.setEnabled(false);
      btnUserOrder.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            FoodOrder fo = new FoodOrder(name, seatno,time,userId);
            fo.setVisible(true);
         }
      });

      
      
      btnUserOrder.setBounds(448, 80, 127, 47);
      panel.add(btnUserOrder);
      
      btnSports = new JButton("스포츠");
      btnSports.setForeground(Color.WHITE);
      btnSports.setBackground(Color.DARK_GRAY);
      btnSports.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnSports.setEnabled(false);
      btnSports.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         // btnSports 클릭 시 이전의 textPane 리스너들 제거
            for (HyperlinkListener listener : textPane.getHyperlinkListeners()) {
                textPane.removeHyperlinkListener(listener);
            }
            String imagePath1 = "/image/fc.png";
            String imagePath2 = "/image/magu.jpg";
            
            String htmlText = "<a href=\"https://fconline.nexon.com/main/index\"><img src=\"" + getClass().getResource(imagePath1) +"\"></a>";
            htmlText += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"https://ma9.netmarble.net\"><img src=\"" + getClass().getResource(imagePath2) +"\"></a><br>";
            textPane.setContentType("text/html");
            textPane.setEditable(false);
            textPane.setText(htmlText);
            textPane.addHyperlinkListener(event -> {
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(event.getURL().toURI());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
         }
      });
      btnSports.setBounds(236, 10, 94, 47);
      panel.add(btnSports);
      
      btnRpg = new JButton("RPG");
      btnRpg.setForeground(Color.WHITE);
      btnRpg.setBackground(Color.DARK_GRAY);
      btnRpg.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnRpg.setEnabled(false);
      btnRpg.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         // btnSports 클릭 시 이전의 textPane 리스너들 제거
            for (HyperlinkListener listener : textPane.getHyperlinkListeners()) {
                textPane.removeHyperlinkListener(listener);
            }
            String imagePath1 = "/image/maple.jpg";
            String imagePath2 = "/image/lostark.jpg";
            
            String htmlText = "<a href=\"https://maplestory.nexon.com\"><img src=\"" + getClass().getResource(imagePath1) +"\"></a>";
            htmlText += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"https://lostark.game.onstove.com\"><img src=\"" + getClass().getResource(imagePath2) +"\"></a><br>";
            textPane.setContentType("text/html");
            textPane.setEditable(false);
            textPane.setText(htmlText);
            textPane.addHyperlinkListener(event -> {
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(event.getURL().toURI());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
         }
      });
      btnRpg.setBounds(24, 10, 94, 47);
      panel.add(btnRpg);
      
     
      
      btnFps = new JButton("FPS");
      btnFps.setForeground(Color.WHITE);
      btnFps.setBackground(Color.DARK_GRAY);
      btnFps.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnFps.setEnabled(false);
      btnFps.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
         // btnSports 클릭 시 이전의 textPane 리스너들 제거
            for (HyperlinkListener listener : textPane.getHyperlinkListeners()) {
                textPane.removeHyperlinkListener(listener);
            }
            String imagePath1 = "/image/battle.jpg";
            String imagePath2 = "/image/sudden.png";
            String imagePath3 = "/image/over.png";
            
            String htmlText = "<a href=\"https://pubg.game.daum.net/pubg/index.daum\"><img src=\"" + getClass().getResource(imagePath1) +"\"></a>";
            htmlText += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"https://sa.nexon.com\"><img src=\"" + getClass().getResource(imagePath2) +"\"></a>";
            htmlText += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"https://overwatch.blizzard.com\"><img src=\"" + getClass().getResource(imagePath3) +"\"></a><br>";
            textPane.setContentType("text/html");
            textPane.setEditable(false);
            textPane.setText(htmlText);
            
            textPane.addHyperlinkListener(event -> {
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(event.getURL().toURI());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            
            
         }
      });
      btnFps.setBounds(130, 10, 94, 47);
      panel.add(btnFps);
      
     
      
      
      btnMess = new JButton("메신저");
      btnMess.setForeground(Color.WHITE);
      btnMess.setBackground(Color.DARK_GRAY);
      btnMess.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnMess.setEnabled(false);
      btnMess.addActionListener(new ActionListener() {  
         public void actionPerformed(ActionEvent e) {
         // btnSports 클릭 시 이전의 textPane 리스너들 제거
            for (HyperlinkListener listener : textPane.getHyperlinkListeners()) {
                textPane.removeHyperlinkListener(listener);
            }
            
            
            String imagePath1 = "/image/kakao.png";
            String imagePath2 = "/image/discord.jpg";
            
            
            String htmlText = "<a href=\"https://www.kakaocorp.com\"><img src=\"" + getClass().getResource(imagePath1) +"\"></a>";
            htmlText += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"https://discord.com\"><img src=\"" + getClass().getResource(imagePath2) +"\"></a><br>";
            textPane.setContentType("text/html");
            textPane.setEditable(false);
            textPane.setText(htmlText);
            textPane.addHyperlinkListener(event -> {
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(event.getURL().toURI());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } 
            });
         }
      });
      
      
      btnMess.setBounds(342, 10, 94, 47);
      panel.add(btnMess);
      
      textPane = new JTextPane();
      textPane.setEditable(false);
      textPane.setBounds(24, 69, 412, 146);
      panel.add(textPane);
  

      JLabel lblName = new JLabel("사용자 : ");
      lblName.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
      lblName.setForeground(Color.WHITE);
      lblName.setBounds(70, 85, 156, 15);
      contentPane.add(lblName);

      JLabel lblUser = new JLabel("");
      lblUser.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
      lblUser.setForeground(Color.WHITE);
      lblUser.setBounds(128, 85, 98, 15);
      lblUser.setText(name);

      contentPane.add(lblUser);

      JButton btnLogout = new JButton("로그아웃");
      btnLogout.setForeground(Color.WHITE);
      btnLogout.setBackground(Color.DARK_GRAY);
      btnLogout.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnLogout.setEnabled(false);
      btnLogout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int chan = JOptionPane.showConfirmDialog(MainUser.this, "로그아웃하시겠습니까?", "로그아웃",
                  JOptionPane.YES_NO_OPTION);
            if (chan == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(MainUser.this, "로그아웃되었습니다.");
               btnStart.setEnabled(true);
               scheduler.shutdown();
               Login lo = new Login();
               lo.setVisible(true);
               dispose();
            }

         }
      });
      btnLogout.setBounds(238, 82, 97, 23);
      contentPane.add(btnLogout);

      JLabel lblTime = new JLabel("");
      lblTime.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblTime.setForeground(Color.WHITE);
      lblTime.setBounds(70, 49, 156, 23);
      contentPane.add(lblTime);
      this.dao = new MemberDAO();
      timer = new Timer(1000, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            dao.timeCom2(userId, lblTime); // 경과 시간으로 라벨 업데이트
         }
      });

      // 타이머 시작
      timer.start();
      

      dao.timeCom2(userId, lblTime);
      JButton btnChange = new JButton("자리변경");
      btnChange.setForeground(Color.WHITE);
      btnChange.setBackground(Color.DARK_GRAY);
      btnChange.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnChange.setEnabled(false);
      btnChange.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int chan = JOptionPane.showConfirmDialog(MainUser.this, "자리를 변경하시겠습니까?", "자리 변경",
                  JOptionPane.YES_NO_OPTION);
            if (chan == JOptionPane.YES_OPTION) {
               MemberDAO dao = new MemberDAO();
               dao.deleteSeat(userId);
               btnStart.setEnabled(true);
               scheduler.shutdown();
               SelectSeat form = new SelectSeat(name, userId, time);
               form.setVisible(true);
               dispose();
            }
         }
      });
      btnChange.setBounds(347, 82, 97, 23);
      contentPane.add(btnChange);

      JButton btnAddHour = new JButton("1시간추가");
      btnAddHour.setForeground(Color.WHITE);
      btnAddHour.setBackground(Color.DARK_GRAY);
      btnAddHour.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));

      btnAddHour.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MemberDAO dao = new MemberDAO();
            dao.priceAdd(userId);
            dao.priceUser(userId, lblprice);
            dao.timeAdd(userId);
            dao.timeCom2(userId, lblTime);
            
         }
      });
      btnAddHour.setBounds(238, 49, 97, 23);
      contentPane.add(btnAddHour);

      btnStart = new JButton("시작");
      btnStart.setForeground(Color.WHITE);
      btnStart.setBackground(Color.DARK_GRAY);
      btnStart.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnStart.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  MemberDAO dao = new MemberDAO();
        	  dao.priceUser(userId,lblprice);
             btnUserOrder.setEnabled(true);
             btnLogout.setEnabled(true);
             btnChange.setEnabled(true);
              btnSports.setEnabled(true);
              btnRpg.setEnabled(true);
              btnFps.setEnabled(true);
              btnMess.setEnabled(true);
              btnStart.setEnabled(false);
              scheduler = Executors.newSingleThreadScheduledExecutor();

              scheduler.scheduleAtFixedRate(() -> {
                  try (Connection conn = DB.dbConn();
                       PreparedStatement pstmt = conn.prepareStatement("UPDATE member SET time = time - 1 WHERE name = ?")) {
                      pstmt.setString(1, name);
                      int rowsUpdated = pstmt.executeUpdate();

                      if (rowsUpdated <= 0) {
                          // Update statement did not affect any rows, indicating time has reached 0.
                          System.out.println("Time reached 0. Stopping scheduler.");
                          scheduler.shutdown();
                      }

                  } catch (SQLException ex) {
                      ex.printStackTrace();
                  }

                  // 여기서 타이머 동작 중에 라벨을 업데이트하고, 시간이 0이 되면 창을 닫도록 수정
                  SwingUtilities.invokeLater(() -> {
                      dao.timeCom2(userId, lblTime);

                      int remainingTime = dao.getTimeByName(userId);
                      if (remainingTime >= 60) {
                          lblTime.setText("남은시간: " + remainingTime / 60 + "시간" + remainingTime % 60 + "분");
                      } else if (remainingTime < 60 && remainingTime >= 1) {
                          lblTime.setText("남은시간: " + remainingTime + "분");
                      } else if (remainingTime == 0) {
                         scheduler.shutdown();
                         JOptionPane.showMessageDialog(MainUser.this, "시간종료");
                         btnStart.setEnabled(true);
                         Login lo = new Login();
                     lo.setVisible(true);
                     dispose();
                      }
                  });
              }, 0, 1, TimeUnit.MINUTES);
          }
      });

   btnStart.setBounds(347,49,97,23);contentPane.add(btnStart);
   ImageIcon icon = new ImageIcon(MainUser.class.getResource("/image/pc.png"));

   Image img = icon.getImage();
   Image updateImg = img.getScaledInstance(705, 483, Image.SCALE_SMOOTH);
   ImageIcon updateIcon = new ImageIcon(updateImg);
lblprice = new JLabel("");
lblprice.setForeground(Color.WHITE);
lblprice.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
lblprice.setBounds(70, 110, 156, 23);
contentPane.add(lblprice);
JLabel lblBackground = new JLabel("");

   lblBackground.setIcon(updateIcon);
   
      lblBackground.setBounds(0, 0, 689, 483);
      contentPane.add(lblBackground);
      
}

   public void updateLblPrice(String userId) {
       // MemberDAO를 이용하여 사용자의 주문 가격을 가져옴
       MemberDAO mdao = new MemberDAO();
       int userPrice = mdao.priceUser(userId, lblprice);

       lblprice.setText("현재 주문 금액: " + userPrice + "원");
   }
 
}