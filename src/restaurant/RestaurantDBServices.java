package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseCon.ConnectDB;
import restaurant.model.Restaurant;

public class RestaurantDBServices {
	public static void createRestaurantInDB(Restaurant newRestaurant) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		try {
			String query = "insert into restaurants(name, address, location, city, state, emailId, userId, password) values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, newRestaurant.getRestaurantName());
			pstmt.setString(2, newRestaurant.getAddress());
			pstmt.setString(3, newRestaurant.getLocation());
			pstmt.setString(4, newRestaurant.getCity());
			pstmt.setString(5, newRestaurant.getState());
			pstmt.setString(6, newRestaurant.getEmailId());
			pstmt.setString(7, newRestaurant.getUserId());
			pstmt.setString(8, newRestaurant.getPassword());

			int no = pstmt.executeUpdate();

			System.out.println(no + " restaurant registered in db");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public static Restaurant getRestaurantById(int id) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		Restaurant restaurant = null;
		try {
			String query = "select * from restaurants where id=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			rs.next();

			int restaurantId = rs.getInt("id");
			String restaurantName = rs.getString("name");
			String address = rs.getString("address");
			String location = rs.getString("location");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String userId = rs.getString("userId");
			String emailId = rs.getString("emailId");

			restaurant = new Restaurant(restaurantId, restaurantName, address, location, city, state, userId, emailId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return restaurant;

	}

	public static void updateRestaurantDetails(Restaurant res) {

		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		String query = "update restaurants SET name=?, address=?, location=?, city=?, state=?, emailId=? where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,res.getRestaurantName());
			ps.setString(2,res.getAddress());
			ps.setString(3,res.getLocation());
			ps.setString(4,res.getCity());
			ps.setString(5,res.getState());
			ps.setString(6,res.getEmailId());
			ps.setInt(7,res.getRestaurantId()); //where id=
			
			int no = ps.executeUpdate();
			
			System.out.println(no + " record updated! ");
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in update restaurant details");
		}
	}

	public static void deleteRestaurantDetails( int id) {

		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		String query = "update restaurants SET availability=0 where id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			int no = ps.executeUpdate();
			
			System.out.println(no + " record deleted! ");
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in dele restaurant details");
		}

	}
}
