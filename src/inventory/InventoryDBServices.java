package inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseCon.ConnectDB;
import restaurant.loginDashboad.RestaurantDashboard;

public class InventoryDBServices {
	public static void addItemsToInventory(Item newItem) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		String query = "insert into items(name, price, restaurant_id) values(?, ?, ?)"; 
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, newItem.getItemName());
			ps.setFloat(2, newItem.getItemPrice());
			ps.setFloat(3,  newItem.getRestaurant_id());
			
			ps.executeUpdate();
			
			System.out.println("Item added successfully");
			ps.close();
		}
		catch(SQLException e) {
			System.out.println("add items to inventory exception");
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

	public static void updateItemInInventory(int itemId, Item updateItemObj) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		String query = "UPDATE items SET name=?, price=? WHERE restaurant_id=? "; 
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, updateItemObj.getItemName());
			ps.setFloat(2, updateItemObj.getItemPrice());
			ps.setInt(3, RestaurantDashboard.getRestaurantLoginId());
			
			int no = ps.executeUpdate();
			
			if(no == 1) {
				System.out.println("Details updated Successfully!");
			}
		}
		catch(SQLException e) {
			System.out.println("Update inventory items exception");
			System.out.println("RestaurantID doesnt exist");			
		}
	}

	public static ArrayList<Item> getAllItemDetails(int restaurantId) {
		ArrayList<Item> itemsList = new ArrayList<Item>();
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		
		String query = "SELECT * from items where restaurant_id=?"; 
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, restaurantId);
			
			ResultSet rs = ps.executeQuery();
			
			int id, resId;
			String name;
			float price;
			
			Item item;
			
			while(rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				price = rs.getFloat("price");
				
				item = new Item(id, name, price);
				
				itemsList.add(item);
			}
		}
		catch(SQLException e) {
			System.out.println("Get all restaurant item details exception");
		}
		return itemsList;
		
	}
}
