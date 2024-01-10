package InventoryFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import FoodFrame.FoodDAO;
import FoodFrame.FoodDTO;
import FoodFrame.MenuDAO;
import FoodFrame.MenuDTO;
import LoginFrame.MemberDAO;
import LoginFrame.Register;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class FoodInfo extends JFrame {

	private static final long serialVersionUID = 1L;

	private InventoryDAO dao;
	private FoodDTO food;

	private JPanel contentPane;
	private JTable table;
	private JTextField tfFoodNum;
	private JTextField tfFoodName;
	private JTextField tfAmount;
	private JTextField tfPrice;
	private JTextField tfSearch2;
	private JComboBox comboBox;
	private FoodDAO foodDao;
	private MenuDAO menuDao;
	private ArrayList<MenuDTO> menuList;
	private ArrayList<FoodDTO> foodList2;
	private InventoryDAO dao1;
	private DefaultTableModel model;
	private Vector<String> cols = new Vector<>(Arrays.asList("상품번호", "상품명", "수량", "가격"));

	/**
	 * Create the frame.
	 */
	public FoodInfo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FoodInfo.class.getResource("/image/gear.png")));
		setTitle("재고관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 218, 142));
		contentPane.setForeground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 287, 580, 311);
		contentPane.add(scrollPane);

		foodDao = new FoodDAO();
		menuDao = new MenuDAO();
		menuList = menuDao.listMenu();
		foodList2 = foodDao.listFood(0);

		String[] columnNames = { "상품번호", "상품명", "수량", "가격" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 1);
		table = new JTable(model);
		table.setDefaultEditor(Object.class, null);

		dao = new InventoryDAO();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = table.getSelectedRow();
				tfFoodNum.setText(table.getValueAt(idx, 0) + "");
				tfFoodName.setText(table.getValueAt(idx, 1) + "");
				tfAmount.setText(table.getValueAt(idx, 2) + "");
				tfPrice.setText(table.getValueAt(idx, 3) + "");

			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("▶상품분류");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel.setBounds(30, 81, 70, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("▶상품명");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 122, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("▶수량");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_2.setBounds(30, 160, 57, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("▶가격");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_3.setBounds(30, 200, 57, 15);
		contentPane.add(lblNewLabel_3);

		tfFoodNum = new JTextField();
		tfFoodNum.setBounds(112, 78, 116, 21);
		contentPane.add(tfFoodNum);
		tfFoodNum.setColumns(10);

		tfFoodName = new JTextField();
		tfFoodName.setBounds(112, 119, 116, 21);
		contentPane.add(tfFoodName);
		tfFoodName.setColumns(10);

		tfAmount = new JTextField();
		tfAmount.setBounds(112, 158, 116, 21);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);

		tfPrice = new JTextField();
		tfPrice.setBounds(112, 198, 116, 21);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);

		JButton btnClose2 = new JButton("닫기");
		btnClose2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
		btnClose2.setBackground(SystemColor.controlHighlight);
		btnClose2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose2.setBounds(495, 608, 97, 23);
		contentPane.add(btnClose2);

		JButton btnChange2 = new JButton("재고 수정");
		btnChange2.setBackground(SystemColor.controlHighlight);
		btnChange2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(FoodInfo.this, "수정할 행을 선택해주세요.");
				} else {
					input();
					int result = dao.update_inventory(food);
					if (result == 1) {
						JOptionPane.showMessageDialog(FoodInfo.this, "수정되었습니다.");

						model.setValueAt(food.getFoodnum(), selectedRow, 0);
						model.setValueAt(food.getFoodname(), selectedRow, 1);
						model.setValueAt(food.getAmount(), selectedRow, 2);
						model.setValueAt(food.getPrice(), selectedRow, 3);

						// 테이블에게 모델이 변경되었음을 알림
						model.fireTableRowsUpdated(selectedRow, selectedRow);
						table.clearSelection();
						table.setModel(model);
						clear();
						
					}
					
					
				}
			}
		});
		JButton btnInsert2 = new JButton("재고 추가");
	      btnInsert2.setBackground(SystemColor.controlHighlight);
	      btnInsert2.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {

	            String foodname = tfFoodName.getText();
	            String foodnumText = tfFoodNum.getText();
	            String amountText = tfAmount.getText();
	            String priceText = tfPrice.getText();
	            
	            if (foodnumText.isEmpty()) {
	               JOptionPane.showMessageDialog(FoodInfo.this, "상품코드를 입력하세요");
	            } else if (Integer.parseInt(foodnumText) > 5) {
	               JOptionPane.showMessageDialog(FoodInfo.this, "상품코드는 1~5사이 입니다");
	            } else if (foodname.isEmpty()) {
	               JOptionPane.showMessageDialog(FoodInfo.this, "상품명을 입력하세요");
	            } else if (amountText.isEmpty()) {
	               JOptionPane.showMessageDialog(FoodInfo.this, "수량을 입력하세요");
	            } else if (priceText.isEmpty()) {
	               JOptionPane.showMessageDialog(FoodInfo.this, "가격을 입력하세요");
	            } else {
	               boolean isAvailable = dao.foodAvailable(foodname);
	               if (isAvailable) {
	                  int foodnum = Integer.parseInt(tfFoodNum.getText());
	                  int amount = Integer.parseInt(tfAmount.getText());
	                  int price = Integer.parseInt(tfPrice.getText());
	                  food = new FoodDTO(foodnum, foodname, amount, price);

	                  int result = dao.insert_inventory(foodnum, foodname, amount, price);

	                  if (result == 1) {
	                     JOptionPane.showMessageDialog(FoodInfo.this, "저장되었습니다.");
	                     model.addRow(new Object[] { food.getFoodnum(), food.getFoodname(), food.getAmount(),
	                           food.getPrice() });
	                     // 테이블에게 모델이 변경되었음을 알림
	                     model.fireTableDataChanged();
	                     table.setModel(model);
	                     clear();
	                  }
	               } else {
	                  JOptionPane.showMessageDialog(FoodInfo.this, "중복된 상품명입니다.");
	               }
	            }
	         }
	      });
		btnInsert2.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnInsert2.setBounds(435, 55, 123, 41);
		contentPane.add(btnInsert2);

		btnChange2.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnChange2.setBounds(432, 121, 126, 41);
		contentPane.add(btnChange2);

		JButton btnDelete2 = new JButton("재고 삭제");
		btnDelete2.setBackground(SystemColor.controlHighlight);
		btnDelete2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String foodname = tfFoodName.getText();
				int result = 0;
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(FoodInfo.this, "삭제할 행을 선택해주세요.");
				} else {
					int response = JOptionPane.showConfirmDialog(FoodInfo.this, "삭제하시겠습니까?");

					if (response == JOptionPane.YES_OPTION) {
						result = dao.delete_inventory(foodname);
						if (result != 0) {
							JOptionPane.showMessageDialog(FoodInfo.this, "삭제되었습니다.");
							// 테이블에서 선택된 행을 삭제

							if (selectedRow != -1) {
								DefaultTableModel model = (DefaultTableModel) table.getModel();
								model.removeRow(selectedRow);
							}
							clear();
						} else {
							JOptionPane.showMessageDialog(FoodInfo.this, "삭제에 실패하였습니다.");
						}
					}
				}
			}
		});
		btnDelete2.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnDelete2.setBounds(435, 188, 123, 41);
		contentPane.add(btnDelete2);

		tfSearch2 = new JTextField();
		tfSearch2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfSearch2.setBounds(12, 256, 116, 21);
		contentPane.add(tfSearch2);
		tfSearch2.setColumns(10);

		JButton btnSearch2 = new JButton("상품명 찾기");
		btnSearch2.setBackground(SystemColor.controlHighlight);
		btnSearch2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch2.setBounds(150, 254, 105, 23);
		contentPane.add(btnSearch2);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
		comboBox.setBackground(SystemColor.controlHighlight);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int idx = comboBox.getSelectedIndex();
					if (idx != -1) {
						refreshList(idx);
					}
				}
			}
		});
		comboBox.setBounds(495, 254, 97, 23);
		contentPane.add(comboBox);

		JLabel lblNewLabel_4 = new JLabel("◈ 상품 재고");
		lblNewLabel_4.setBackground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(19, 10, 154, 30);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setFont(new Font("나눔고딕", Font.BOLD, 26));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		for (MenuDTO dto : menuList) {
			comboBox.addItem(dto.getMenu());
		}
		refreshList(0);

	}

	void refreshList(int idx) {
		int foodnum = menuList.get(idx).getFoodnum();
		foodList2 = foodDao.listFood(foodnum);

		// 기존 데이터 모두 삭제
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (FoodDTO foodDTO : foodList2) {
			// 데이터 추가
			model.addRow(new Object[] { foodDTO.getFoodnum(), foodDTO.getFoodname(), foodDTO.getAmount(),
					foodDTO.getPrice() });
		}
	}

	public void search() {
		String foodname = tfSearch2.getText();
		model = new DefaultTableModel(dao.search_inventory(foodname), cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}

	public void input() {
		int foodnum = Integer.parseInt(tfFoodNum.getText());
		String foodname = tfFoodName.getText();
		int amount = Integer.parseInt(tfAmount.getText());
		int price = Integer.parseInt(tfPrice.getText());

		food = new FoodDTO(foodnum, foodname, amount, price);
	}

	public void clear() {
		tfFoodNum.setText("");
		tfFoodName.setText("");
		tfAmount.setText("");
		tfPrice.setText("");

		tfFoodNum.requestFocus();
		tfFoodNum.setEditable(true);
	}

	public void list(FoodDTO food) {

	}

}
