import java.util.Scanner;
import restaurant.RestaurantManager;
import user.auth.UserAuthMenu;

public class Main {

	public static void main(String[] args) {
		int option = -1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\t\t\t\t\t\t\tWelcome to");
			System.out.println("\t\t\t\t\t  ___  ___  _____        ___  ___   ___   _____ \r\n"
					+ "\t\t\t\t\t| __|/   \\|_   _|      / __|| _ \\ / _ \\ |_   _|\r\n"
					+ "\t\t\t\t\t| _| | - |  | |        \\__ \\|  _/| (_) |  | |  \r\n"
					+ "\t\t\t\t\t|___||_|_|  |_|        |___/|_|   \\___/   |_|  \r\n"
					+ "\t\t\t\t\t");
			System.out.println("\t\t\t\t\t\tRestaurant Management CLI App");
			
			
			
			System.out.println("\t\t\t\t\t1. Restaurant\n\t\t\t\t\t2. User");
			System.out.println("\t\t\t\t\tSelect user type: ");
			option = sc.nextInt();
			
			switch(option) {
			case 1:
				RestaurantManager.restaurantAuthMenu();
				break;
			case 2:
				UserAuthMenu.displayUserAuthMenu();
				break;
			case 3: break;
			default: 
				System.out.println("\t\t\t\t\tSelect correct option!");
			}
		}while(option!=3);

	}

}
