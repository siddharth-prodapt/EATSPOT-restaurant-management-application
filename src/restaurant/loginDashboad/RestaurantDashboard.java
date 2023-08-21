package restaurant.loginDashboad;

import java.util.Scanner;

public class RestaurantDashboard {
	void showRestaurantDashboard() {
		System.out.println("RESTAURANT DASHBOARD");
		System.out.println("---------------------");
		int options = -1;
		Scanner sc  = new Scanner(System.in);
		do {
			System.out.println("1. Restaurant Details\n2. Update Restaurant Details\n3. Orders\n4. Tables\n5. Inventory\n6. Report\n7. Exit");
			options = sc.nextInt();
			
			switch(options) {
			case 1: 
//				show restaurant details
				break;
			case 2:
//				update restaurant details
			case 3:
			case 4: 
			case 5:
				break;
			case 6:
				break;
				default: System.out.println("Try Again! with valid option");
				
			}
		}while(options!=7);
	}
}
