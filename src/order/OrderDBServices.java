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
			
			rs.next();
			isAvailable = rs.getBoolean("isAvailable");
			
			if (isAvailable) {
				return isAvailable;
			}
		} catch (SQLException e) {
			System.out.println("Create order in Restaurant Exception" + e);
		}
		return false;
	}

	public static boolean toggleTableAvailabilityById(int id, boolean isAvailable) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();

		try {
//			boolean isAvailable = false;
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
	public static int createOrderId(int tableNo, int cid) {
		int orderId = 0;
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();

		try {
			String query = "insert into orders(customer_Id, restaurant_Id, isDineIn, table_id) values(?, ?, true,?)   ";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, cid);
			ps.setInt(2, RestaurantDashboard.getRestaurantLoginId());
			ps.setInt(3, tableNo);
			
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

	public static boolean setInvoiceGenerated(int orderId) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		PreparedStatement ps;
		try {
			String query = "UPDATE orders SET invoice_generated=1 where id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
	
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Order Cant be closed!");
			return false;
		}
		
		return true;
	}

	public static void viewUnpaidOrders() {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		try {
			String query = "SELECT * from orders where restaurant_id=? and invoice_generated=0";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, RestaurantDashboard.getRestaurantLoginId());
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				System.out.println("\n\t\t\t\t\tOrder ID: "+rs.getInt("id"));
				System.out.println("\n\t\t\t\t\tTable ID: "+rs.getInt("table_id"));
				System.out.println("\n\n");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	public static boolean isInvoiceGenerated(int orderId) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		boolean invoiceStatus=false;
		
		try {
			String query= "select invoice_generated from orders where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			invoiceStatus = rs.getBoolean("invoice_generated");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return invoiceStatus;
	}
}
