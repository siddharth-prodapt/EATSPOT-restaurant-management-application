import java.util.Scanner;
import restaurant.RestaurantManager;

public class Main {

	public static void main(String[] args) {
		int option = -1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("HOME");
			System.out.println("1. Restaurant\n2. User\n3. Admin");
			System.out.println("Select user type: ");
			option = sc.nextInt();
			
			switch(option) {
			case 1:
				RestaurantManager.restaurantAuthMenu();
				break;
			case 2: break;
			case 3: break;
			default: 
				System.out.println("Select correct option!");
			}
		}while(option!=4);

	}

}
