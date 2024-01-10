package FoodFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import LoginFrame.DB;
import LoginFrame.MemberDTO;

public class FoodDAO {
	//<장바구니 추가버튼 클릭하면 food 테이블에서 수량 -1>
	public void updateFood(String foodname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DB.dbConn();
			String sql = "UPDATE food SET amount=amount-1 WHERE foodname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, foodname);
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
	
	//<장바구니에서 삭제버튼 클릭하면 food테이블에 수량 +1>
	public void deleteFood(String foodname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DB.dbConn();
			String sql = "UPDATE food SET amount=amount+1 WHERE foodname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, foodname);
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
	   
	   
	   //<상품분류번호를 기준으로 찾은 정보를 ArrayList에 담기>
	   public ArrayList<FoodDTO> listFood(int foodnum) {
	        ArrayList<FoodDTO> items=new ArrayList<FoodDTO>();
	        Connection conn=null;
	        PreparedStatement pstmt=null;
	        ResultSet rs=null;
	        try {
	            conn=DB.dbConn();
	            String sql="select f.foodnum, foodname, amount, price, menu from food f, menu m where f.foodnum=m.foodnum and f.foodnum = ?";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setInt(1, foodnum);
	            rs=pstmt.executeQuery();
	            while(rs.next()){
	                FoodDTO dto=new FoodDTO();
	                dto.setFoodnum(rs.getInt("foodnum"));
	                dto.setFoodname(rs.getString("foodname"));
	                dto.setAmount(rs.getInt("amount"));
	                dto.setPrice(rs.getInt("price"));
	                dto.setMenu(rs.getString("menu"));
	                items.add(dto);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if(rs!=null) rs.close();
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	            try {
	                if(pstmt!=null) pstmt.close();
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }        
	            try {
	                if(conn!=null) conn.close();
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return items;
	    }
	   
	   public static int amountZero(String foodname) {
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         int amount = 0;
	         
			try {
	            conn = DB.dbConn();
	            String sql = "select amount from food where foodname = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, foodname);
	            rs=pstmt.executeQuery();
	            
	            
	            if(rs.next()) {
	            	amount = rs.getInt("amount");
	            }
	                  
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
			return amount;
	      }
}
