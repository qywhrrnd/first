package FoodFrame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import LoginFrame.MemberDAO;
import LoginFrame.MemberDTO;
import MainFrame.MainUser;
import Total.TotalDAO;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.Canvas;

public class FoodOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tb1;
	private JTable tb2;
	private FoodDAO foodDao;
	private MenuDAO menuDao;
	private ArrayList<MenuDTO> menuList;
	private ArrayList<FoodDTO> foodList2;
	private JLabel lblResult;
	private JComboBox combo;
	private int total;
	private TotalDAO tdao;

	/**
    * Create the frame.
    */
   public FoodOrder(String name, int seatno,int time, String userId) {
      setTitle("상품 주문");
      setIconImage(Toolkit.getDefaultToolkit().getImage(FoodOrder.class.getResource("/image/guri.png")));
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 814, 572);
      contentPane = new JPanel();
      contentPane.setBackground(SystemColor.info);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(41, 64, 302, 348);
      contentPane.add(scrollPane);

      foodDao = new FoodDAO();
      menuDao = new MenuDAO();
      menuList = menuDao.listMenu();
      foodList2 = foodDao.listFood(0);
      String[] columnNames = { "이름", "가격" };
      DefaultTableModel model = new DefaultTableModel(columnNames, 0);
      tb1 = new JTable(model);
      tb1.setDefaultEditor(Object.class, null);

      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
      dtcr.setHorizontalAlignment(SwingConstants.CENTER);
      TableColumnModel tcm = tb1.getColumnModel();
      for (int i = 0; i < tcm.getColumnCount(); i++) {
         tcm.getColumn(i).setCellRenderer(dtcr);
      }

      // table.addMouseListener();
      scrollPane.setViewportView(tb1);

      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(451, 64, 302, 348);
      contentPane.add(scrollPane_1);

      String[] columnNames2 = { "이름", "가격" };
      DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);
      tb2 = new JTable(model2);
      tb2.setDefaultEditor(Object.class, null);
      scrollPane_1.setViewportView(tb2);

      TableColumnModel tcm1 = tb2.getColumnModel();
      for (int i = 0; i < tcm1.getColumnCount(); i++) {
         tcm1.getColumn(i).setCellRenderer(dtcr);
      }

      JLabel lblProduct = new JLabel("▶상품");
      lblProduct.setBackground(SystemColor.menu);
      lblProduct.setForeground(SystemColor.desktop);
      lblProduct.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
      lblProduct.setBounds(44, 39, 65, 15);
      contentPane.add(lblProduct);

      JLabel lblBasket = new JLabel("▶장바구니");
      lblBasket.setForeground(SystemColor.menuText);
      lblBasket.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
      lblBasket.setBounds(450, 39, 73, 15);
      contentPane.add(lblBasket);

      JButton btnAdd = new JButton("추가");
      btnAdd.setForeground(new Color(0, 0, 0));
      btnAdd.setBackground(SystemColor.controlHighlight);
      btnAdd.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnAdd.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             int selectedRow = tb1.getSelectedRow();
             
             if (selectedRow != -1) {
            	 String productName = (String) tb1.getValueAt(selectedRow, 0);
            	 Integer productPrice = (Integer) tb1.getValueAt(selectedRow, 1);
            	 
                if (FoodDAO.amountZero(productName) > 0) {
                   foodDao.updateFood(productName);
                   

                   DefaultTableModel mode2 = (DefaultTableModel) tb2.getModel();
                   mode2.addRow(new Object[] { productName, productPrice });
                   int rowCount = tb2.getRowCount();
                   total = 0;

                   for (int i = 0; i < rowCount; i++) {
                      total += (Integer) tb2.getValueAt(i, 1); // 가격이 두 번째 열에 있다고 가정
                   }

                   lblResult.setText("총 주문 금액: " + total + "원");
                }else {
                   JOptionPane.showMessageDialog(FoodOrder.this, productName+" 재고가 없습니다");   
                }
                
                
             } else {
                JOptionPane.showMessageDialog(FoodOrder.this, "상품을 선택하세요.");
             }
          }
       });
      
      btnAdd.setBounds(246, 422, 97, 23);
      contentPane.add(btnAdd);

      JButton btnOrder = new JButton("주문하기");
      btnOrder.setForeground(new Color(0, 0, 0));
      btnOrder.setBackground(SystemColor.controlHighlight);
      btnOrder.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      btnOrder.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 
        	 
            // 장바구니 리스트 조회

            int countRow = tb2.getRowCount();

            if (countRow > 0) {
                  for (int i = 0; i < countRow; i++) {
                      String productName = model2.getValueAt(i, 0).toString();
                      int productPrice = (int) model2.getValueAt(i, 1);
                      FoodBoxDAO.shopping(productName, productPrice, seatno);
                  }
                  MemberDAO mdao = new MemberDAO();
                  mdao.foodPriceAdd(total,userId);
                  MainUser user = new MainUser(name,userId,time,seatno);
                  user.updateLblPrice(userId);
                  
                  JOptionPane.showMessageDialog(FoodOrder.this, "주문이 완료되었습니다.");
                  mdao.priceUser(userId, user.lblprice);
                   dispose();
              } else {
                  JOptionPane.showMessageDialog(FoodOrder.this, "주문하실 제품을 선택해주세요.");
              }
          }
      });
      btnOrder.setBounds(675, 491, 111, 32);
      contentPane.add(btnOrder);

      JButton btnDelete = new JButton("삭제");
      btnDelete.setForeground(new Color(0, 0, 0));
      btnDelete.setBackground(SystemColor.controlHighlight);
      btnDelete.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
      btnDelete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int selectedRow = tb2.getSelectedRow();
            if (selectedRow != -1) {

               // 선택한 행 삭제
               String productName = (String) tb2.getValueAt(selectedRow, 0);
               Integer productPrice = (Integer) tb2.getValueAt(selectedRow, 1);
               foodDao.deleteFood(productName);
               
               DefaultTableModel mode2 = (DefaultTableModel) tb2.getModel();
               mode2.removeRow(selectedRow);
               int rowCount = tb2.getRowCount();
               int total = 0;

               for (int i = 0; i < rowCount; i++) {
                  total += (Integer) tb2.getValueAt(i, 1); // 가격이 두 번째 열에 있다고 가정
               }

               lblResult.setText("총 주문 금액: " + total + "원");

            } else {
               JOptionPane.showMessageDialog(FoodOrder.this, "삭제 할 상품을 선택하세요.");
            }
         }

      });

      btnDelete.setBounds(656, 422, 97, 23);
      contentPane.add(btnDelete);

      lblResult = new JLabel("");
      lblResult.setForeground(SystemColor.desktop);
      lblResult.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
      lblResult.setBounds(509, 457, 165, 42);
      contentPane.add(lblResult);

      combo = new JComboBox<>();
      combo.setForeground(new Color(0, 0, 0));
      combo.setBackground(SystemColor.controlHighlight);
      combo.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 12));
      combo.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
               int idx = combo.getSelectedIndex();
               if (idx != -1) {
                  refreshList(idx);
               }
            }
         }
      });

      combo.setBounds(252, 36, 91, 23);
      contentPane.add(combo);

      JLabel lblArrow = new JLabel("→");
      lblArrow.setFont(new Font("굴림", Font.BOLD, 40));
      lblArrow.setBounds(367, 211, 57, 15);
      contentPane.add(lblArrow);
      for (MenuDTO dto : menuList) {
         combo.addItem(dto.getMenu());
      }

      refreshList(0);
   }

	void refreshList(int idx) {
		int foodnum = menuList.get(idx).getFoodnum();
		foodList2 = foodDao.listFood(foodnum);

		// 기존 데이터 모두 삭제
		DefaultTableModel model = (DefaultTableModel) tb1.getModel();
		model.setRowCount(0);

		for (FoodDTO foodDTO : foodList2) {
			// 데이터 추가
			model.addRow(new Object[] { foodDTO.getFoodname(), foodDTO.getPrice() });
		}

	}

}