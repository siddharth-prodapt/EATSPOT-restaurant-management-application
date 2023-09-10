package restaurant.auth;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;
import restaurant.RestaurantDBServices;
import restaurant.loginDashboad.RestaurantDashboard;
import restaurant.model.Restaurant;
import databaseCon.ConnectDB;
import restaurant.loginDashboad.RestaurantDashboard;

public class RestaurantAuth implements IRestaurantAuth {
	private static String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

	private String encryptPassword(String password, String emailId) {
		final String KEY = "qwerty@miniproject";
		String encryptedPas = null;

		try {
//			salting
			String msg = password + KEY + emailId;

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(msg.getBytes());
			byte[] digest = md.digest();
			StringBuffer hexString = new StringBuffer();

			hexString.append(Integer.toHexString(0xFF & digest[0]));

			for (int i = 0; i < digest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & digest[i]));
			}
			encryptedPas = hexString.toString();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		return encryptedPas;
	}

	
	private boolean isEmailIdRegistered(String emailId) {
		/*
		 * <p> Returns {@link #true} if email already registered else <h2>Else
		 * false</h2> </p>
		 */
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();

		try {
			String query = "select emailId from restaurants";

			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			String dbEmail;

			while (rs.next()) {
				dbEmail = rs.getString("emailId");
				if (dbEmail.equals(emailId))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/*-------------------------------------public functions-----------------------------------*/
	public void registerRestaurant() {
		String name = null, city = null, state = null, emailId = null, password = null, location = null, address,
				userId, encryptedPassword;
//		String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\t\t\t\t\t-------------------");
		System.out.println("\t\t\t\t\tRegisteration Menu");
		System.out.println("\t\t\t\t\t-------------------");
		try {
			System.out.println("Restaurant Name : ");
			name = br.readLine();
			System.out.println("Street/Building : ");
			location = br.readLine();
			System.out.println("City            : ");
			city = br.readLine();
			System.out.println("State           :");
			state = br.readLine();
			System.out.println("EmailId         : ");
			emailId = br.readLine();

			while (!emailId.matches(emailRegex)) {
				System.out.println("Not valid email id");
				System.out.println("Enter emailId: ");
				emailId = br.readLine();
			}

			if (isEmailIdRegistered(emailId)) // chk whether email already registered or not
			{
				System.out.println("\nEmail Already registered! ");
				return;
			}
			System.out.println("\nPassword: ");
			password = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception occured while registering restaurant");
		}

		address = location + ", " + city + ", " + state;
		encryptedPassword = encryptPassword(password, emailId);
		userId = emailId;

		Restaurant newRestaurant = new Restaurant(name, address, location, city, state, userId, encryptedPassword,
				emailId);
//		System.out.println("EmailID to regd: " + emailId);

		RestaurantDBServices.createRestaurantInDB(newRestaurant);

	}

	public boolean loginRestaurant() {
		String userId, password;
		boolean isLogin = false;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("\t\t\t\t\t------------");
			System.out.println("\t\t\t\t\tLogin Menu");
			System.out.println("\t\t\t\t\t------------");
			System.out.println("\t\t\t\t\tUserID/Email  : ");
			userId = br.readLine();
			while (!userId.matches(emailRegex)) {
				System.out.println("\t\t\t\t\tIncorrect email id. Try Again!");
				System.out.println("\t\t\t\t\tEnter correct userId/emailId");
				userId = br.readLine();
			}
			System.out.println("\t\t\t\t\tPassword      : ");
			password = br.readLine();

			String encryptedPassword = encryptPassword(password, userId);

			String query = "select * from restaurants where userId=?";
			
			Restaurant res;

			ConnectDB ob = new ConnectDB();
			Connection con = ob.getConnection();
			PreparedStatement ps = con.prepareStatement(query);

			try {

				ps.setString(1, userId);

				ResultSet rs = ps.executeQuery();
				rs.next();
				
				if (rs.wasNull()) {
					System.out.println("\t\t\t\t\tUser not registerd");
				}

				if (rs.getString("password").equals(encryptedPassword)) {
					System.out.println("\n\t\t\t\t\tLogged IN\n");
					isLogin = true;
					
					res = new Restaurant(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("location"), rs.getString("city"), rs.getString("state"), rs.getString("userId"), rs.getString("emailId"));
					RestaurantDashboard.setCurrentRestaurant(res);
					RestaurantDashboard.setRestaurantLoginId(rs.getInt("id"));
				} else {
					System.out.println("\t\t\t\t\tIncorrect Password");
					System.out.println("\t\t\t\t\tTry to Login again");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("\t\t\t\t\tUser Not Registered in Database");
			} finally {
				con.close();
			}

		} catch (Exception e) {
			System.out.println("\t\t\t\t\tTry Again. Incorrect password/Email. ");
		}
		return isLogin;
	}
}
