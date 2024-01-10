package LoginFrame;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {

	public static Connection dbConn() {
		Connection con = null;
		try {
			FileInputStream fis = new FileInputStream("C:/work/db1.prop");
			Properties prop = new Properties();
			prop.load(fis);
			String url = prop.getProperty("url");
			String id = prop.getProperty("id");
			String password = prop.getProperty("password");
			con = DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}
}

