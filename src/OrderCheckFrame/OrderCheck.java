package OrderCheckFrame;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import FoodFrame.FoodBoxDAO;
import FoodFrame.FoodBoxDTO;
import LoginFrame.MemberDAO;
import LoginFrame.MemberDTO;
import LoginFrame.SelectSeat;
import MainFrame.MainUser;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class OrderCheck extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private OrderCheckDAO check;
   private FoodBoxDTO dto;
   private JTable tb;
   private Vector<String> cols;
   private Vector<String> rows;
   private DefaultTableModel model;
   /**
    * Launch the application.
    */
   private JLabel lblResult;
   private JScrollPane scrollPane;

   /**
    * Create the frame.
    */
   public OrderCheck() {
   	setIconImage(Toolkit.getDefaultToolkit().getImage(OrderCheck.class.getResource("/image/gear.png")));
      setTitle("주문확인");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 309, 459);
      contentPane = new JPanel();
      contentPane.setBackground(new Color(234, 218, 142));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      scrollPane = new JScrollPane();

      scrollPane.setBounds(12, 10, 267, 319);
      contentPane.add(scrollPane);

      check = new OrderCheckDAO();
      cols = new Vector<String>();
      cols.add("상품명");
      cols.add("가격");
      cols.add("좌석번호");

      list();
      tb = new JTable(model);

      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
      dtcr.setHorizontalAlignment(SwingConstants.CENTER);
      TableColumnModel tcm = tb.getColumnModel();
      for (int i = 0; i < tcm.getColumnCount(); i++) {
         tcm.getColumn(i).setCellRenderer(dtcr);
      }

      JLabel lblResult = new JLabel();
      lblResult.setForeground(Color.BLACK);
      lblResult.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 16));
      int sum = check.total();
      lblResult.setText("상품 매출 : " + sum + "원");
      lblResult.setBounds(59, 339, 176, 33);
      contentPane.add(lblResult);

      scrollPane.setViewportView(tb);

      JButton btnRef = new JButton("정산하기");
      btnRef.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnRef.setBackground(SystemColor.controlHighlight);
      btnRef.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int countRow = tb.getRowCount();
            if (countRow == 0) {
               JOptionPane.showMessageDialog(OrderCheck.this, "정산할게 없습니다");
            } else {
               int ref = JOptionPane.showConfirmDialog(OrderCheck.this, "정산하시겠습니까?", "정산",
                     JOptionPane.YES_NO_OPTION);
               if (ref == JOptionPane.YES_OPTION) {
                  check.deleteOrder();
                  OrderCheckDAO.shoppingnum();
                  JOptionPane.showMessageDialog(OrderCheck.this, "정산되었습니다");
                  dispose();
               }
            }
         }
      });
      btnRef.setBounds(95, 382, 97, 23);
      contentPane.add(btnRef);
   }

   public void list() {
      model = new DefaultTableModel(check.FoodBox_list(dto), cols) {
         @Override
         public boolean isCellEditable(int row, int column) {
            return false;
         }

      };
   }
}