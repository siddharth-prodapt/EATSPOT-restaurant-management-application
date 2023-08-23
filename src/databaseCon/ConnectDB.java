package databaseCon;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	private static Connection con;
	public Connection getConnection() {
		try {
			String mySqlDriver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/restaurant_management";
			String user= "root";
			String pas = "root";
			
//			2. load and register driver
			Class.forName(mySqlDriver);
//			3. Establish connection
			con = DriverManager.getConnection(url, user, pas);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception in JDBC connection");
		}
		
		return con;
	}
}
