package InventoryFrame;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import FoodFrame.FoodDTO;
import LoginFrame.DB;
import MainFrame.UserInfo;

public class InventoryDAO {
	//<재고테이블>
	   public Vector inventory_food(FoodDTO food) {
	      Vector items = new Vector();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = DB.dbConn();
	         String sql = "select foodnum,foodname,amount,price from food order by foodnum ";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Vector row = new Vector();
	            row.add(rs.getString("foodnum"));
	            row.add(rs.getString("foodname"));
	            row.add(rs.getString("amount"));
	            row.add(rs.getString("price"));
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

	 
	 
	 //<재고 저장>
	 public int insert_inventory(int foodnum, String foodname, int amount, int price) {  // (안은 매개변수! 값을 받기위한 변수)
		  int result = 0;
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  try {
			conn = DB.dbConn();
			String sql = "insert into food values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, foodnum);
			pstmt.setString(2, foodname);
			pstmt.setInt(3, amount);
			pstmt.setInt(4, price);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		  return result;
	  }
	 
	// <재고찾기>
	   public Vector search_inventory(String foodname) {
	      Vector items = new Vector();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = DB.dbConn();
	         String sql = "select foodnum,foodname,price,amount from food where foodname like ? order by foodname";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, "%" + foodname + "%");
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            Vector row = new Vector();
	            row.add(rs.getString("foodnum"));
	            row.add(rs.getString("foodname"));
	            row.add(rs.getString("amount"));
	            row.add(rs.getString("price"));
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

	   // <재고 수정>
	   public int update_inventory(FoodDTO food) {
	      int result = 0;
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      try {
	         con = DB.dbConn();
	         String sql = "update food set foodnum=?,foodname=?,amount=?,price=? where foodname=?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, food.getFoodnum());
	         pstmt.setString(2, food.getFoodname());
	         pstmt.setInt(3, food.getAmount());
	         pstmt.setInt(4, food.getPrice());
	         pstmt.setString(5, food.getFoodname());
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

	   // <재고 삭제>
	   public int delete_inventory(String foodname) {
	      int result = 0;
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      try {
	         con = DB.dbConn();
	         String sql = "delete from food where foodname=?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, foodname);
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
	   public static boolean foodAvailable(String foodname) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			boolean isAvailable = false;

			try {
				conn = DB.dbConn();
				String sql = "SELECT COUNT(*) FROM food WHERE foodname = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, foodname);
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

	   
}
