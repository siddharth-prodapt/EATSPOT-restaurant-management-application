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

import restaurant.model.Restaurant;
import databaseCon.ConnectDB;

public class RestaurantAuth implements IRestaurantAuth {

	String encryptPassword(String password, String emailId) {
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

//            System.out.println("Hex Format: " + hexString.toString());
			encryptedPas = hexString.toString();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		return encryptedPas;
	}

	private void createRestaurantInDB(Restaurant newRestaurant) {
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

			System.out.println(no + " user registered in db");
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

	public void registerRestaurant() {
		String name = null, city = null, state = null, emailId = null, password = null, location = null, address,
				userId, encryptedPassword;
		String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("-------------------");
		System.out.println("Registeration Menu");
		System.out.println("-------------------");
		try {
			System.out.println("Restaurant Name: ");
			name = br.readLine();
			System.out.println("Street/Building: ");
			location = br.readLine();
			System.out.println("City: ");
			city = br.readLine();
			System.out.println("State:");
			state = br.readLine();
			System.out.println("EmailId: ");
			emailId = br.readLine();
			
			while(!emailId.matches(emailRegex)){
				System.out.println("Not valid email id");
				System.out.println("Enter emailId: ");
				emailId = br.readLine();
			}

			if(isEmailIdRegistered(emailId)) // chk whether email already registered or not
			{
				System.out.println("Email Already registered! ");
				return;
			}
			System.out.println("Password: ");
			password = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception occured while registering restaurant");
		}

		address = location + ", " + city + ", " + state;
		encryptedPassword = encryptPassword(password, emailId);
		userId = emailId;

//		System.out.println("Encryp: "+encryptedPassword);

		Restaurant newRestaurant = new Restaurant(name, address, location, city, state, userId, encryptedPassword,
				emailId);
		System.out.println("EmailID to regd: "+emailId);

		createRestaurantInDB(newRestaurant);

	}

	public void loginRestaurant() {
		String userId, password;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("------------");
			System.out.println("Login Menu");
			System.out.println("------------");
			System.out.println("UserID/Email: ");
			userId = br.readLine();
			System.out.println("Password: ");
			password = br.readLine();

//		encryptedPassword == password in db
//		if true then login
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Login Exception");
		}

	}
}
