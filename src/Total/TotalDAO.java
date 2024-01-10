package Total;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import LoginFrame.DB;
import LoginFrame.MemberDTO;

public class TotalDAO {
   
   public void ttimeAdd() {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DB.dbConn();
         String sql = "insert into total values(1,\"시간\",1000,now())";
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
   
   
   public void tfoodAdd(int total) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;

	      try {
	         conn = DB.dbConn();
	         String sql = "insert into total values(2,\"식품\",?,now())";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, total);
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
  
  public Vector ttotal3(TotalDTO total) {
      Vector items = new Vector();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = DB.dbConn();
         String sql = "SELECT tname, SUM(tprice) FROM total WHERE tday BETWEEN '2024-01-01' AND '2024-01-31'GROUP BY tname";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("tname"));
            row.add(rs.getInt("sum(tprice)"));
           
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
  
  
  
  public Vector ttotal2(TotalDTO total) {
      Vector items = new Vector();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = DB.dbConn();
         String sql = "SELECT tname, SUM(tprice) FROM total WHERE tday BETWEEN '2023-12-01' AND '2023-12-31' GROUP BY tname";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("tname"));
            row.add(rs.getInt("sum(tprice)"));
           
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
  
  
  public Vector ttotal1(TotalDTO total) {
      Vector items = new Vector();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = DB.dbConn();
         String sql = "SELECT tname, SUM(tprice) FROM total WHERE tday BETWEEN '2023-11-01' AND '2023-11-30' GROUP BY tname";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("tname"));
            row.add(rs.getInt("sum(tprice)"));
           
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
  
  
  public Vector ttotal(TotalDTO total) {
      Vector items = new Vector();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = DB.dbConn();
         String sql = "SELECT tname, SUM(tprice) FROM total GROUP BY tname";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("tname"));
            row.add(rs.getInt("sum(tprice)"));
           
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