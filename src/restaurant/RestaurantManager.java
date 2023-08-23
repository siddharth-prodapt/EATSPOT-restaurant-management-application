package restaurant;

import java.util.Scanner;
import restaurant.auth.RestaurantAuth;

public class RestaurantManager {
	public static void restaurantAuthMenu() {
		System.out.println("\n----------------");
		System.out.println("RESTAURANT MENU");
		System.out.println("----------------");
		
		Scanner sc = new Scanner(System.in);
		int option = -1;
		RestaurantAuth resAuth = new RestaurantAuth();
		do {
			System.out.println("1. SignUp\n2. Login");
			option = sc.nextInt();
			
			switch(option) {
			case 1:
				resAuth.registerRestaurant();
				break;
			case 2:
//				resAuth.loginRestaurant();
				break;
				
				default:
					System.out.println("Try Again!");
			}
		}while(option!=3);
		
	}
}
