package LoginFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import FoodFrame.FoodBoxDTO;
import MainFrame.InfoCheck;

public class MemberDAO {
	private ScheduledExecutorService scheduler;

	public static boolean isUserIdAvailable(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isAvailable = false;

		try {
			conn = DB.dbConn();
			String sql = "SELECT COUNT(*) FROM member WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isAvailable = rs.getInt(1) == 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// 리소스 닫기 (ResultSet, PreparedStatement, Connection)
		}

		return isAvailable;
	}

	public static void insertMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn(); 
			String sql = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?, 0,0,0,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getBirth());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// 리소스 닫기 (PreparedStatement, Connection)
		}
	}

	public void insertSeat(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "UPDATE member SET seatno = ? WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getSeatno());
			pstmt.setString(2, member.getUserId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static boolean isCheckSeat(int seatno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isCheck = false;

		try {
			conn = DB.dbConn();
			String sql = "SELECT COUNT(*) FROM member WHERE seatno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isCheck = rs.getInt(1) == 0;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return isCheck;
	}

	public void deleteSeat(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "UPDATE member SET seatno = 0 WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void timeAdd(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "UPDATE member SET TIME = time + 60 WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ScheduledExecutorService timeStart(MemberDTO member) {
		scheduler = Executors.newSingleThreadScheduledExecutor();

		scheduler.scheduleAtFixedRate(() -> {
			try (Connection conn = DB.dbConn();
					PreparedStatement pstmt = conn
							.prepareStatement("UPDATE member SET time = time - 1 WHERE userid = ?")) {
				pstmt.setString(1, member.getName());
				int rowsUpdated = pstmt.executeUpdate();

				if (rowsUpdated <= 0) {
					// Update statement did not affect any rows, indicating time has reached 0.
					System.out.println("Time reached 0. Stopping scheduler.");
					scheduler.shutdown();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}, 0, 1, TimeUnit.MINUTES);
		return scheduler;

	}

	public void useCom(int seatno, JLabel labelToUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "SELECT userid FROM member WHERE seatno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String userId = rs.getString("userid");
				labelToUpdate.setText("사용자: " + userId);
			} else {
				labelToUpdate.setText("빈 자리");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public void timeCom(int seatno, JLabel labelToUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "SELECT time FROM member WHERE seatno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int time = rs.getInt("time");
				if (time >= 60) {
					labelToUpdate.setText("남은시간: " + time / 60 + "시간" + time % 60 + "분");

				} else if (time < 60) {
					labelToUpdate.setText("남은시간: " + time + "분");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public void timeCom2(String userid, JLabel labelToUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "SELECT time FROM member WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int time = rs.getInt("time");
				if (time >= 60) {
					labelToUpdate.setText("남은시간: " + time / 60 + "시간" + time % 60 + "분");

				} else if (time < 60) {
					labelToUpdate.setText("남은시간: " + time + "분");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public void selectInfoCheck(int seatno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.dbConn();
			String sql = "SELECT userid,pwd,name,email,phone,birth FROM member WHERE seatno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userId = rs.getString("userid");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");

				InfoCheck ic = new InfoCheck(userId);
				ic.setInfo(userId, pwd, name, email, phone, birth); // InfoCheck에 이 값을 설정하는 메서드를 정의해주세요.
				ic.setVisible(true);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	public int getTimeByName(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int remainingTime = 0;

		try {
			conn = DB.dbConn();
			String sql = "SELECT time FROM member WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				remainingTime = rs.getInt("time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return remainingTime;
	}

	public void searchId(String name, int birth, JLabel labelToUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "SELECT userid FROM member WHERE name = ? and birth = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, birth);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String Id = rs.getString("userid");
				labelToUpdate.setText("아이디 :  " + Id);
			} else {
				labelToUpdate.setText("정보에 맞는 사용자가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public void searchPwd(String userid, JLabel labelToUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "SELECT pwd FROM member WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String pwd = rs.getString("pwd");
				labelToUpdate.setText("비밀번호 :  " + pwd);
			} else {
				labelToUpdate.setText("아이디에 맞는 사용자가 없습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public void priceAdd(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "UPDATE member SET price = price + 1000 WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void foodPriceAdd(int total, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "UPDATE member SET price = price + ? WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, total);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void priceCom(int seatno, JLabel labelToUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "SELECT price FROM member WHERE seatno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int price = rs.getInt("price");
				if (price >= 0) {
					labelToUpdate.setText("이용요금: " + price + "원");

				} else if (price == 0) {
					labelToUpdate.setText("이용요금: " + 0 + "원");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public static void deletePrice(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "update member set price = 0 where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void stackPrice(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "update member set stackprice = stackprice + price where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public int priceUser(String userId, JLabel labelToUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int price=0;

		try {
			conn = DB.dbConn();
			String sql = "SELECT price FROM member WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				price = rs.getInt("price");
				if (price >= 0) {
					labelToUpdate.setText("이용요금: " + price + "원");

				} else if (price == 0) {
					labelToUpdate.setText("이용요금: " + 0 + "원");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return price;

	}
}