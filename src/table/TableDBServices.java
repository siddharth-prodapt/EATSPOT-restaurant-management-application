package table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import databaseCon.ConnectDB;
import restaurant.loginDashboad.RestaurantDashboard;

public class TableDBServices {
	public static void createTable(Table newTable) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		String query = "insert into tables(name, capacity, description, restaurant_Id ) values(?, ?, ?, ?)"; 
		
		try {
			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newTable.getTableName() );
			ps.setInt(2, newTable.getTableCapacity());
			ps.setString(3, newTable.getTableDesc());
			ps.setInt(4, newTable.getRestaurantId());
			
//			System.out.println("Data going to regd: "+newTable.getTableName()+newTable.getTableCapacity()+newTable.getTableDesc()+newTable.getRestaurantId());
			
			ps.executeUpdate();
						
			ResultSet keys = ps.getGeneratedKeys();
			keys.next() ;
			
			System.out.println("Table ID:" + keys.getInt(1) +" registered successfully");
			
			ps.close();
		}
		catch(SQLException e) {
			System.out.println("Create table exception"+e);
		}
		finally {
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void getAllTableDetails(int resId) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		String query = "select * from tables where restaurant_id=?"; 
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, resId);
			ResultSet rs = ps.executeQuery();
			
			if (rs != null)
				while (rs.next()) {
					System.out.println("\n");
					System.out.println("Table ID      :" + rs.getInt("id"));
					System.out.println("Table Name    :" + rs.getString("name"));
					System.out.println("Table Capacity:" + rs.getInt("capacity"));
					System.out.println("Table Desc    :" + rs.getString("description"));
					System.out.println("Availability  :" + rs.getBoolean("isAvailable"));
				}
			
		}
		catch(SQLException e) {
			System.out.println("Get All Table details exception");
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static ArrayList<Table> getAvailableTableOfRestaurant(int resId) {
		
		ArrayList<Table> restaurantTableList = new ArrayList<Table>();
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		String query = "select * from tables where restaurant_id=? and isAvailable = 1"; 
		
		try {
			PreparedStatement ps = con.prepareStatement(query);	
			ps.setInt(1, resId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs != null)
				while (rs.next()) {
					System.out.println("\n");
					System.out.println("Table ID      :" + rs.getInt("id"));
					System.out.println("Table Name    :" + rs.getString("name"));
					System.out.println("Table Capacity:" + rs.getInt("capacity"));
					System.out.println("Table Desc    :" + rs.getString("description"));
					
					Table tab = new Table(rs.getInt("id"), rs.getString("name"), rs.getInt("capacity"), rs.getString("description"));
					restaurantTableList.add(tab);
				}
		}
		catch(SQLException e) {
			System.out.println("Exception in Available Table Details");
		}
		return restaurantTableList;
	}
	
	public static void findTableDetailsById(int tableId) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		String query = "select * from tables where restaurant_id=? and id=?"; 
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, RestaurantDashboard.getRestaurantLoginId());
			ps.setInt(2, tableId);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			System.out.println("\n");
			System.out.println("Table ID      :" + rs.getInt("id"));
			System.out.println("Table Name    :" + rs.getString("name"));
			System.out.println("Table Capacity:" + rs.getInt("capacity"));
			System.out.println("Table Desc    :" + rs.getString("description"));
			System.out.println("Availability  :" + rs.getBoolean("isAvailable"));
			
			
			rs.close();
		}catch(SQLException e) {
			System.out.println("Table ID is not valid");
		}

		
	}
	
	
	void updateTableDetails() {
		
	}
	
	
	
	void deleteTableDetails() {
		
	}
}
