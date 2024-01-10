package Total;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import MainFrame.UserInfoDAO;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class Total extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tb;
	private TotalDAO tdao;
	private TotalDTO tdto;
	private DefaultTableModel model;
	private Vector<String> cols;
	private int total;
	private JLabel lblTotal;

	/**
	 * Create the frame.
	 */
	public Total() {
		setTitle("매출");
		if (!java.beans.Beans.isDesignTime()) {
		    setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(Total.class.getResource("/image/gear.png")));
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 290);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 218, 142));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 45, 313, 104);
		contentPane.add(scrollPane);

		tdao = new TotalDAO();
		cols = new Vector<String>();
		cols.add("종류");
		cols.add("총매출");

		list();

		tb = new JTable(model);
		scrollPane.setViewportView(tb);
		center();
		
	      

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox combo = new JComboBox();
		combo.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
		combo.setBackground(SystemColor.controlHighlight);

		combo.addItem("전체");
		combo.addItem("2023-11");
		combo.addItem("2023-12");
		combo.addItem("2024-01");

		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int idx = combo.getSelectedIndex();

					if (idx == 0) {
						ttotal();
						center();
						int rowCount = tb.getRowCount();
						total = 0;

						for (int i = 0; i < rowCount; i++) {
							total += (Integer) tb.getValueAt(i, 1); // 가격이 두 번째 열에 있다고 가정
						}

						lblTotal.setText("매출액 : " + total + "원");

					} else if (idx == 1) {
						ttotal1();
						tttotal();
						center();
					} else if (idx == 2) {
						ttotal2();
						tttotal();
						center();
					} else if (idx == 3) {
						ttotal3();
						tttotal();
						center();

					}
				}
			}
		});
		combo.setBounds(195, 10, 130, 23);
		contentPane.add(combo);

		lblTotal = new JLabel("");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 16));
		tttotal();
		lblTotal.setBounds(12, 159, 313, 43);
		contentPane.add(lblTotal);
		
		JButton btnClose = new JButton("닫기");
		btnClose.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
		btnClose.setBackground(SystemColor.controlHighlight);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(121, 218, 97, 23);
		contentPane.add(btnClose);

	}

	public void list() {
		model = new DefaultTableModel(tdao.ttotal(tdto), cols) {
//         @Override
//         public boolean isCellEditable(int row, int column) {
//            return false;
//         }
		};
	}

	void ttotal() {
		// dao.list_member 메서드를 적절히 수정하여 가격순으로 정렬된 사용자 정보를 가져오도록 합니다.
		model = new DefaultTableModel(tdao.ttotal(tdto), cols) {

		};
		tb.setModel(model);
	}

	void ttotal1() {
		// dao.list_member 메서드를 적절히 수정하여 가격순으로 정렬된 사용자 정보를 가져오도록 합니다.
		model = new DefaultTableModel(tdao.ttotal1(tdto), cols) {

		};
		tb.setModel(model);
	}

	void ttotal2() {
		// dao.list_member 메서드를 적절히 수정하여 가격순으로 정렬된 사용자 정보를 가져오도록 합니다.
		model = new DefaultTableModel(tdao.ttotal2(tdto), cols) {

		};
		tb.setModel(model);
	}

	void ttotal3() {
		// dao.list_member 메서드를 적절히 수정하여 가격순으로 정렬된 사용자 정보를 가져오도록 합니다.
		model = new DefaultTableModel(tdao.ttotal3(tdto), cols) {

		};
		tb.setModel(model);
	}
	
	
	void tttotal() {
		int rowCount = tb.getRowCount();
		total = 0;

		for (int i = 0; i < rowCount; i++) {
			total += (Integer) tb.getValueAt(i, 1); // 가격이 두 번째 열에 있다고 가정
		}

		lblTotal.setText("매출액 : " + total + "원");
	}
	
	void center() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
	      dtcr.setHorizontalAlignment(SwingConstants.CENTER);
	      TableColumnModel tcm = tb.getColumnModel();
	      for (int i = 0; i < tcm.getColumnCount(); i++) {
	         tcm.getColumn(i).setCellRenderer(dtcr);
	      }
	}
}
