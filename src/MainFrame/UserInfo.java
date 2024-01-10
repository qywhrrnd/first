package MainFrame;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import LoginFrame.MemberDAO;
import LoginFrame.MemberDTO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class UserInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private Vector<String> cols;
	private DefaultTableModel model;
	private UserInfoDAO dao;

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private MemberDTO member;
	private JTextField tfUserid1;
	private JTextField tfPwd1;
	private JTextField tfName1;
	private JTextField tfEmail1;
	private JTextField tfPhone1;
	private JTextField tfBirth1;
	private JTextField tfSearch1;

	/**
	 * Create the frame.
	 */
	public UserInfo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserInfo.class.getResource("/image/gear.png")));
		setTitle("회원정보 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1015, 416);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 218, 142));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 720, 298);
		contentPane.add(scrollPane);

		dao = new UserInfoDAO();
		cols = new Vector<String>();
		cols.add("아이디");
		cols.add("비밀번호");
		cols.add("이름");
		cols.add("이메일");
		cols.add("전화번호");
		cols.add("생년월일");
		cols.add("총 이용금액");

		list();

		table = new JTable(model);
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = table.getSelectedRow();
				tfUserid1.setEditable(false);
				tfUserid1.setText(table.getValueAt(idx, 0) + "");
				tfPwd1.setText(table.getValueAt(idx, 1) + "");
				tfName1.setText(table.getValueAt(idx, 2) + "");
				tfEmail1.setText(table.getValueAt(idx, 3) + "");
				tfPhone1.setText(table.getValueAt(idx, 4) + "");
				tfBirth1.setText(table.getValueAt(idx, 5) + "");
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("◈회원정보");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 25));
		lblNewLabel.setBounds(29, 10, 161, 49);
		contentPane.add(lblNewLabel);

		Panel panel = new Panel();
		panel.setBackground(new Color(235, 238, 241));
		panel.setBounds(748, 57, 234, 298);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(26, 25, 57, 15);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));

		tfUserid1 = new JTextField();
		tfUserid1.setBounds(89, 22, 116, 21);
		panel.add(tfUserid1);
		tfUserid1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(26, 59, 57, 15);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));

		tfPwd1 = new JTextField();
		tfPwd1.setBounds(89, 56, 116, 21);
		panel.add(tfPwd1);
		tfPwd1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setBounds(26, 93, 57, 15);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));

		tfName1 = new JTextField();
		tfName1.setBounds(89, 90, 116, 21);
		panel.add(tfName1);
		tfName1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("이메일");
		lblNewLabel_4.setBounds(26, 130, 57, 15);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));

		tfEmail1 = new JTextField();
		tfEmail1.setBounds(89, 127, 116, 21);
		panel.add(tfEmail1);
		tfEmail1.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("전화번호");
		lblNewLabel_5.setBounds(26, 160, 57, 15);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_5.setForeground(new Color(0, 0, 0));

		tfPhone1 = new JTextField();
		tfPhone1.addKeyListener(new KeyAdapter() {
	         @Override
	         public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
	               e.consume();
	            }
	         }
	      });
		tfPhone1.setBounds(89, 158, 116, 21);
		panel.add(tfPhone1);
		tfPhone1.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("생년월일");
		lblNewLabel_6.setBounds(26, 196, 57, 15);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));
		lblNewLabel_6.setForeground(new Color(0, 0, 0));

		tfBirth1 = new JTextField();
		tfBirth1.addKeyListener(new KeyAdapter() {
	         @Override
	         public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
	               e.consume();
	            }
	         }
	      });
		tfBirth1.setBounds(89, 194, 116, 21);
		panel.add(tfBirth1);
		tfBirth1.setColumns(10);

		JButton btnNewButton_2 = new JButton("수정");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(SystemColor.controlHighlight);
		btnNewButton_2.setBounds(12, 246, 97, 29);
		panel.add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));

		JButton btnNewButton_3 = new JButton("삭제");
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		btnNewButton_3.setBounds(125, 246, 97, 29);
		panel.add(btnNewButton_3);
		btnNewButton_3.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));

		tfSearch1 = new JTextField();
		tfSearch1.setBounds(331, 29, 149, 21);
		contentPane.add(tfSearch1);
		tfSearch1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfSearch1.setColumns(10);

		JButton btnSearch = new JButton("이름 찾기");
		btnSearch.setForeground(new Color(0, 0, 0));
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.setBounds(492, 27, 93, 23);
		contentPane.add(btnSearch);
		btnSearch.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 13));

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 14));
		comboBox.setBackground(SystemColor.controlHighlight);

		comboBox.addItem("이름순");
		comboBox.addItem("가격순");

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int idx = comboBox.getSelectedIndex();
					if (idx != -1) {
						// 이름순 또는 가격순에 따라 다르게 정렬하는 메서드 호출
						if (idx == 0) {
							// 이름순으로 정렬
							list_Name();
						} else if (idx == 1) {
							// 가격순으로 정렬
							list_Price();
						}
					}
				}
			}
		});

		comboBox.setBounds(629, 24, 101, 23);
		contentPane.add(comboBox);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userid = tfUserid1.getText();
				int result = 0;
				int selectedRow = table.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(UserInfo.this, "삭제할 행을 선택해주세요.");
				} else {
					int response = JOptionPane.showConfirmDialog(UserInfo.this, "삭제하시겠습니까?");
					if (response == JOptionPane.YES_OPTION) {
						result = dao.delete_member(userid);
					}
					if (result == 1) {
						JOptionPane.showMessageDialog(UserInfo.this, "삭제되었습니다.");
						list();
						table.setModel(model);
						clear();
					}
				}
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result = dao.update_member(member);
				int selectedRow = table.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(UserInfo.this, "수정할 행을 선택해주세요.");
				} else {
				if (result == 1) {
					JOptionPane.showMessageDialog(UserInfo.this, "수정되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
			}
		});

		// 길이를 조절할 컬럼 선택
		int emailColumnIndex = cols.indexOf("이메일");
		int phoneColumnIndex = cols.indexOf("전화번호");
		// 특정 컬럼의 너비를 설정
		int emailColumnWidth = 130; // 이메일 컬럼의 원하는 너비 값
		int phoneColumnWidth = 100; // 전화번호 컬럼의 원하는 너비 값
		table.getColumnModel().getColumn(emailColumnIndex).setPreferredWidth(emailColumnWidth);
		table.getColumnModel().getColumn(phoneColumnIndex).setPreferredWidth(phoneColumnWidth);
	}

	public void search() {
		String name = tfSearch1.getText();
		model = new DefaultTableModel(dao.search_member(name), cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}

	public void input() {
		String userid = tfUserid1.getText();
		String pwd = tfPwd1.getText();
		String name = tfName1.getText();
		String email = tfEmail1.getText();
		String phone = tfPhone1.getText();
		String birth = tfBirth1.getText();

		member = new MemberDTO(userid, pwd, name, email, phone, birth);
	}

	public void clear() {
		tfUserid1.setText("");
		tfPwd1.setText("");
		tfName1.setText("");
		tfEmail1.setText("");
		tfPhone1.setText("");
		tfBirth1.setText("");
		tfUserid1.requestFocus();
		tfUserid1.setEditable(true);
	}

	public void list() {
		model = new DefaultTableModel(dao.list_member(member), cols) {
//         @Override
//         public boolean isCellEditable(int row, int column) {
//            return false;
//         }
		};
	}

	void list_Name() {
		// dao.list_member 메서드를 적절히 수정하여 이름순으로 정렬된 사용자 정보를 가져오도록 합니다.
		model = new DefaultTableModel(dao.list_name(member), cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}

	void list_Price() {
		// dao.list_member 메서드를 적절히 수정하여 가격순으로 정렬된 사용자 정보를 가져오도록 합니다.
		model = new DefaultTableModel(dao.list_price(member), cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}
}