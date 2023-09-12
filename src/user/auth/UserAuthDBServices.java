package user.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import databaseCon.ConnectDB;
import user.model.User;

public class UserAuthDBServices {

	public static void createUserInDB(User newUser) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();
		try {
			String query = "insert into customers(name, phone, emailId, userId, password) values(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, newUser.getUserName());
			pstmt.setString(2, newUser.getUserPhoneNo());
			pstmt.setString(3, newUser.getUserEmailId());
			pstmt.setString(4, newUser.getUserLoginId());
			pstmt.setString(5, newUser.getUserPassword());

			int no = pstmt.executeUpdate();

			System.out.println(no + " customers registered in db");
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

}
