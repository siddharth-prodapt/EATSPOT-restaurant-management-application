package restaurant.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import restaurant.model.Restaurant;


class RestaurantAuth implements IRestaurantAuth {
	
	String encryptPassword(String password) {
//		salting process
//		return password to store in DB
		return password;
	}
	public void registerRestaurant() {
		String name, city = null, state = null, emailId = null, password = null, location = null, address,userId,encryptedPassword;
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
			System.out.println("Password: ");
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception occured while registering restaurant");
		}
		
		address = location+", "+city+", "+state;
		encryptedPassword = encryptPassword(password);
		userId = emailId ;
		
//		Restaurant ob = new Restaurant(name, address, location, city, state, userId, password, emailId);
//		Add these data to databases
		
		
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
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Login Exception");
		}
		
	}
}
