package MainFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;

import LoginFrame.DB;
import LoginFrame.MemberDAO;
import LoginFrame.MemberDTO;

public class UserInfoDAO {
	private MemberDTO member;

	// <회원테이블>
	public Vector list_member(MemberDTO member) {
		Vector items = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DB.dbConn();
			String sql = "select userid,pwd,name,email,phone,birth,time,seatno,stackprice from member order by name";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("userid"));
				row.add(rs.getString("pwd"));
				row.add(rs.getString("name"));
				row.add(rs.getString("email"));
				row.add(rs.getString("phone"));
				row.add(rs.getString("birth"));
//            row.add(rs.getInt("time"));
//            row.add(rs.getInt("seatno"));
				row.add(rs.getInt("stackprice"));
				items.add(row);
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
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}

	// <회원찾기>
	public Vector search_member(String name) {
		Vector items = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DB.dbConn();
			String sql = "select userid,pwd,name,email,phone,birth,stackprice from member where name like ? order by name";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("userid"));
				row.add(rs.getString("pwd"));
				row.add(rs.getString("name"));
				row.add(rs.getString("email"));
				row.add(rs.getString("phone"));
				row.add(rs.getString("birth"));
				row.add(rs.getInt("stackprice"));
				items.add(row);
			}
		} catch (Exception e) {
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
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	// <회원 수정>
	public int update_member(MemberDTO member) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DB.dbConn();
			String sql = "update member set pwd=?,NAME=?,email=?,phone=?,birth=? where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getBirth());
			pstmt.setString(6, member.getUserId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// <회원 삭제>
	public int delete_member(String userid) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DB.dbConn();
			String sql = "delete from member where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public String mostPrice() {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String mname = null;
	      
	      try {
	         conn = DB.dbConn();
	         String sql = "SELECT name FROM member WHERE stackprice = (select MAX(stackprice) FROM member)";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	            mname = rs.getString("name");
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
	      return mname;

	   }
	
	
	
	public Vector list_name(MemberDTO member) {
	      Vector items = new Vector();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = DB.dbConn();
	         String sql = "select userid,pwd,name,email,phone,birth,time,seatno,stackprice from member order by name";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Vector row = new Vector();
	            row.add(rs.getString("userid"));
	            row.add(rs.getString("pwd"));
	            row.add(rs.getString("name"));
	            row.add(rs.getString("email"));
	            row.add(rs.getString("phone"));
	            row.add(rs.getString("birth"));
	            row.add(rs.getInt("stackprice"));
	            items.add(row);
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
	            if (con != null)
	               con.close();
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return items;
	   }
	   public Vector list_price(MemberDTO member) {
	      Vector items = new Vector();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = DB.dbConn();
	         String sql = "select userid,pwd,name,email,phone,birth,time,seatno,stackprice from member order by stackprice desc";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Vector row = new Vector();
	            row.add(rs.getString("userid"));
	            row.add(rs.getString("pwd"));
	            row.add(rs.getString("name"));
	            row.add(rs.getString("email"));
	            row.add(rs.getString("phone"));
	            row.add(rs.getString("birth"));
	            row.add(rs.getInt("stackprice"));
	            items.add(row);
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
	            if (con != null)
	               con.close();
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return items;
	   }
}
