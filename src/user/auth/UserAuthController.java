package user.auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseCon.ConnectDB;
import restaurant.loginDashboad.RestaurantDashboard;
import user.UserDashboard;
import user.model.User;

public class UserAuthController {

	private static String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

	private static String encryptPassword(String password, String emailId) {
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

	private static boolean isEmailIdRegistered(String emailId) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean login() {
		String userId, password;
		boolean isLogin = false;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("------------");
			System.out.println("Login Menu");
			System.out.println("------------");
			System.out.println("UserID/Email: ");
			userId = br.readLine();
			while (!userId.matches(emailRegex)) {
				System.out.println("Incorrect email id. Try Again!");
				System.out.println("Enter correct userId/emailId");
				userId = br.readLine();
			}
			System.out.println("Password: ");
			password = br.readLine();

			String encryptedPassword = encryptPassword(password, userId);

			String query = "select * from customers where userId=?";

			ConnectDB ob = new ConnectDB();
			Connection con = ob.getConnection();
			PreparedStatement ps = con.prepareStatement(query);

			try {

				ps.setString(1, userId);

				ResultSet rs = ps.executeQuery();
				rs.next();

//				System.out.println("user id from db: " + rs.getInt("id"));
				if (rs.wasNull()) {

					System.out.println("User/EmailId not registerd");
				}

				if (rs.getString("password").equals(encryptedPassword)) {
					System.out.println("Logged IN");
					isLogin = true;
					
					User currentUser = new User(rs.getInt("id"),rs.getString("name"), rs.getString("phone"), rs.getString("emailId"), rs.getString("userId"));
					UserDashboard.setCurrentUser(currentUser);
//					
				} else {
					System.out.println("Incorrect Password");
					System.out.println("Try to Login again");
				}

			} catch (SQLException e) {

				e.printStackTrace();
				System.out.println("User Not Registered in Database");
			} finally {
				con.close();
			}

		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("Try Again. Incorrect password/Email. ");
		}

		return isLogin;
	}

	public static void signUp() {
		System.out.println("\t\t\t\t\tCUSTOMER SIGNUP MENU");
		System.out.println("\t\t\t\t\t---------------------");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = null, emailId = null, phoneNo = null, loginId = null, password = null;
		String encryptedPassword;
		char ch;
		try {
			System.out.println("\t\t\t\t\tEnter Name: ");
			name = br.readLine();
			System.out.println("\t\t\t\t\tEnter Email: ");
			emailId = br.readLine();
			System.out.println("\t\t\t\t\tEnter Phone: ");
			phoneNo = br.readLine();
			loginId = emailId;

			while (!emailId.matches(emailRegex)) {
				System.out.println("\t\t\t\t\tNot valid email id");
				System.out.println("\t\t\t\t\tEnter emailId: ");
				emailId = br.readLine();
			}

			if (isEmailIdRegistered(emailId)) // chk whether email already registered or not
			{
				System.out.println("\t\t\t\t\tEmail Already registered! ");
				return;
			}

			System.out.println("\t\t\t\t\tEnter password: ");
			password = br.readLine();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Customer Signup Exception");
		}

		encryptedPassword = encryptPassword(password, emailId);
		User newUser = new User(name, emailId, phoneNo, loginId, encryptedPassword);

		UserAuthDBServices.createUserInDB(newUser);

	}
}
