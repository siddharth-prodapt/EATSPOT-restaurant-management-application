package user.auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserAuthController {
	
	void login() {
		
	}
	
	void signUp() {
		System.out.println("CUSTOMER SIGNUP MENU");
		System.out.println("---------------------");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name, emailId, phoneNo, loginId, password;
		char ch;
		try {
		System.out.println("Enter Name: ");
		name = br.readLine();
		System.out.println("Enter Email: ");
		emailId = br.readLine();
		System.out.println("Enter Phone: ");
		phoneNo = br.readLine();
		loginId = emailId;
		System.out.println("Enter password: ");
		password = br.readLine();
		
		System.out.println("\nDo you really want to register(y/n):");
		ch = (char) br.read();
		ch = Character.toLowerCase(ch);
		if(ch == 'y') {
			/*
			 * Connect to DB
			 * see user table
			 * Create object
			 * add in DB
			 * 
			 */
		}else {
			System.out.println("Lets Return then!");
			return;
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Customer Signup Exception");
		}
	}
}
