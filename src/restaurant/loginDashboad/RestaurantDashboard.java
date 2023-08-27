package restaurant.loginDashboad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import restaurant.RestaurantDBServices;
import restaurant.model.Restaurant;

public class RestaurantDashboard {
	private static int restaurantLoginId;

	public static void setRestaurantLoginId(int resId) {
		restaurantLoginId = resId;
	}

	private static void displayRestaurantDetails() {
		Restaurant restaurant = RestaurantDBServices.getRestaurantById(restaurantLoginId);

		System.out.println("Restaurant Id   : " + restaurant.getRestaurantId());
		System.out.println("Name            : " + restaurant.getRestaurantName());
		System.out.println("Address         : " + restaurant.getAddress());
		System.out.println("Location        : " + restaurant.getLocation());
		System.out.println("City            : " + restaurant.getCity());
		System.out.println("State           : " + restaurant.getState());
		System.out.println("Email Id        : " + restaurant.getEmailId());
		System.out.println("----------------------------------------------------------------------");
	}

	private static void updateRestaurantDetails() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Do you really want to update details(y/n): ");
		String choice = sc.next();

		if (choice.toLowerCase().equals("n")) {
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Restaurant restaurant = new Restaurant();

		String name = "", location = "", city = "", state = "", address = "";
		try {
			System.out.println("Enter restaurant name   : ");
			name = br.readLine();
			System.out.println("Street/Building         : ");
			location = br.readLine();
			System.out.println("City                    :");
			city = br.readLine();
			System.out.println("State                   :");
			state = br.readLine();
			address = location + ", " + city + ", " + state;
		} catch (Exception e) {
			e.printStackTrace();
		}

		restaurant.setRestaurantName(name);
		restaurant.setCity(city);
		restaurant.setLocation(location);
		restaurant.setState(state);
		restaurant.setAddress(address);
		restaurant.setRestaurantId(restaurantLoginId);

		RestaurantDBServices.updateRestaurantDetails(restaurant);
	}

	public static void showRestaurantDashboard() {
		System.out.println("\n---------------------");
		System.out.println("RESTAURANT DASHBOARD");
		System.out.println("---------------------");
		int options = -1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(
					"1. Restaurant Details\n2. Update Restaurant Details\n3. Orders\n4. Tables\n5. Inventory\n6. Report\n7. Logout");
			options = sc.nextInt();

			switch (options) {
			case 1:
				displayRestaurantDetails();
//				show restaurant details
				break;
			case 2:
//				update restaurant details
				updateRestaurantDetails();
				break;
			case 3:
			case 4:
			case 5:
				break;
			case 6:
				break;
			default:
				System.out.println("Try Again! with valid option");

			}
		} while (options != 7);
	}
}
