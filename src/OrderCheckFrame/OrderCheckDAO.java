package OrderCheckFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import FoodFrame.FoodBoxDTO;
import LoginFrame.DB;
import LoginFrame.MemberDTO;

public class OrderCheckDAO {
   public Vector FoodBox_list(FoodBoxDTO dto) {
         Vector items = new Vector();
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
            con = DB.dbConn();
            String sql = "select foodname,price,seatno from foodbox";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
               Vector row = new Vector();
               row.add(rs.getString("foodname"));
               row.add(rs.getInt("price"));
               row.add(rs.getInt("seatno"));
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


       public int total() {
           Connection conn = null;
           PreparedStatement pstmt = null;
           ResultSet rs = null;
           int sum = 0; // 실제 합계를 저장할 변수

           try {
               conn = DB.dbConn();
               String sql = "SELECT sum(price) FROM foodbox";
               pstmt = conn.prepareStatement(sql);
               rs = pstmt.executeQuery();

               if (rs.next()) {
                   sum = rs.getInt(1); // 결과가 있다면 합계를 가져옴
               }
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               // close resources
           }

           return sum;
       }
       public void deleteOrder() {
           Connection conn = null;
           PreparedStatement pstmt = null;

           try {
              conn = DB.dbConn();
              String sql = "delete from foodbox";
              pstmt = conn.prepareStatement(sql);
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
       public static void shoppingnum() {
           Connection conn = null;
           PreparedStatement pstmt = null;

           try {
              conn = DB.dbConn();
              String sql = "ALTER TABLE foodbox AUTO_INCREMENT = 1";
              pstmt = conn.prepareStatement(sql);
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
}