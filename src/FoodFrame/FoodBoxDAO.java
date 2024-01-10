package FoodFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import LoginFrame.DB;

public class FoodBoxDAO {


   public static void shopping(String foodname, int price, int seatno) {
         Connection conn = null;
         PreparedStatement pstmt = null;
         try {
            conn = DB.dbConn();
            String sql = "INSERT INTO foodbox(foodname, price, seatno) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, foodname);
            pstmt.setInt(2, price);
            pstmt.setInt(3, seatno);
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
   
   
   
   public static void shoppingCancel(int snum) {
         Connection conn = null;
         PreparedStatement pstmt = null;

         try {
            conn = DB.dbConn();
            String sql = "delete from foodbox where snum = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, snum);
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
   
   
}