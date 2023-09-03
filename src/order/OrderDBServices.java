package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import databaseCon.ConnectDB;
import restaurant.loginDashboad.RestaurantDashboard;

public class OrderDBServices {
	public static boolean isTableAvailable(int tableId) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();

		
		boolean isAvailable;

		try {
			
			String query = "select id, name, isAvailable from tables where restaurant_id=? and tables.id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, RestaurantDashboard.getRestaurantLoginId());
			
			ps.setInt(2, tableId);
			ResultSet rs = ps.executeQuery();
			System.out.println(RestaurantDashboard.getRestaurantLoginId());
			
//			while(rs.next()) {
//				System.out.println(rs.getInt(1));
//			}
			
			rs.next();

			tableId = rs.getInt("id");
			
			String tableName = rs.getString(2);
			isAvailable = rs.getBoolean("isAvailable");
			
			System.out.println(tableId+ tableName+ isAvailable);
			if (isAvailable) {
				return isAvailable;
			}
		} catch (SQLException e) {
			System.out.println("Create order in Restaurant Exception" + e);
		}
		return false;
	}

	public static boolean bookTableById(int id) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();

		try {
			boolean isAvailable = false;
			String query = "UPDATE tables SET isAvailable=? where tables.id=? and restaurant_id = ?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setBoolean(1, isAvailable);
			ps.setInt(2, id);
			ps.setInt(3, RestaurantDashboard.getRestaurantLoginId());

			int no = ps.executeUpdate();

			if (no == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// return order Id created.
	public static int createOrderId(int tableNo) {
		int orderId = 0;
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();

		try {
			String query = "insert into orders(customer_Id, restaurant_Id, isDineIn, table_id) values(000, ?, true,?)   ";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, RestaurantDashboard.getRestaurantLoginId());
			ps.setInt(2, tableNo);
			
			ps.executeUpdate();
			String query1 = "select last_insert_id() as id";
			
			ResultSet rs = ps.executeQuery(query1);

			rs.next();
			
			orderId = rs.getInt("id");
			
		} catch (SQLException e) {
			System.out.println("Create Order Exception");
		}
		return orderId;
	}

	public static void orderItems(int orderId, int itemId, int qty) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		try {
			String query = "INSERT INTO order_details(order_id, item_id, quantity) values(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, orderId);
			ps.setInt(2, itemId);
			ps.setInt(3, qty);
			
			ps.executeUpdate();
			
			System.out.println("Item Ordered! ");
		}catch(SQLException e) {
			System.out.println("Order Items Exception");
		}
	}
}
