package restaurant;

import java.util.Scanner;
import restaurant.auth.RestaurantAuth;
import restaurant.loginDashboad.RestaurantDashboard;

public class RestaurantManager {
	public static void restaurantAuthMenu() {
		System.out.println("\n\t\t\t\t\t----------------");
		System.out.println("\t\t\t\t\tRESTAURANT MENU");
		System.out.println("\t\t\t\t\t----------------");
		
		Scanner sc = new Scanner(System.in);
		int option = -1;
		RestaurantAuth resAuth = new RestaurantAuth();
		do {
			System.out.println("\t\t\t\t\t1. SignUp\n\t\t\t\t\t2. Login");
			option = sc.nextInt();
			
			switch(option) {
			case 1:
				resAuth.registerRestaurant();
				break;
			case 2:
				if(resAuth.loginRestaurant()) {
					RestaurantDashboard.showRestaurantDashboard();
				}
				break;
				
				default:
					System.out.println("\t\t\t\t\tTry Again!");
			}
		}while(option!=3);
		
	}
}
