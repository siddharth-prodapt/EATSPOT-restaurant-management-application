package restaurant.loginDashboad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import inventory.InventoryDriverMethod;
import order.OrderDriverMethod;
import restaurant.RestaurantDBServices;
import restaurant.model.Restaurant;
import table.Table;
import table.TableController;
import table.TableDBServices;

public class RestaurantDashboard {
	private static int restaurantLoginId;

	public static void setRestaurantLoginId(int resId) {
		restaurantLoginId = resId;
	}

	public static int getRestaurantLoginId() {
		return restaurantLoginId;
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

	private static void inventoryServices() {
		Scanner sc = new Scanner(System.in);
		int options = -1;
		do {
			System.out.println("\n------------------");
			System.out.println("Inventory Services");
			System.out.println("-------------------");
			System.out.println(
					"1. Add Item to Inventory\n2. Update Item in Inventory\n3. View All Inventory items 4. Exit");
			options = sc.nextInt();
			switch (options) {
			case 1:
				InventoryDriverMethod.addItemToInventory();
				break;
			case 2:
				InventoryDriverMethod.updateItemInInventory();
				break;
			case 3:
				InventoryDriverMethod.viewAllItemDetails();
				break;
			default:
				System.out.println("Try Again");
			}
		} while (options != 4);

	}

	public static void showTableServices() {
		int options = -1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("------------");
			System.out.println("TABLE MENU");
			System.out.println("------------\n");
			System.out.println(
					"1. Create Table \n2. Update Table Details \n3. Table Availability \n4. Search Table By Id \n5. Show all tables\n6. Delete Table \n7. Exit");

			System.out.println("Select options: ");
			options = sc.nextInt();

			switch (options) {
			case 1:
				TableController.displayCreateTable();
				break;
			case 2:
				TableController.displayAvailableTable();
				break;
			case 4:
				TableController.searchTableById();
				break;
			case 5:
				TableController.displayAllTableDetails();
				break;
			default:
				System.out.println("Try Again");
			}

		} while (options != 7);
	}

	private static void displayOrderMenu() {
		Scanner sc = new Scanner(System.in);
		int options = -1;
		do {
		System.out.println("Order Menu");
		System.out.println("1. Generate Order Id and Reserve Table\n2. Order Items at Table\n3. View All booked table details\n4. Exit ");
		options = sc.nextInt();
		switch(options) {
		case 1: 
			OrderDriverMethod.createOrderIdAndReserveTable();
			break;
		case 2:
			OrderDriverMethod.orderItems();
			break;
			//order food
			//enter order Id 
		}
		}
		while(options != 4);
	}

	public static void showRestaurantDashboard() {
		System.out.println("\n---------------------");
		System.out.println("RESTAURANT DASHBOARD");
		System.out.println("---------------------");
		int options = -1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(
					"1. Restaurant Details\n2. Update Restaurant Details\n3. Orders\n4. Tables\n5. Inventory\n6. Invoice Management\n7. Logout");
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
				displayOrderMenu();
				break;
			case 4:
				showTableServices();
				break;
			case 5:
				inventoryServices();
				break;
			case 6:
				break;
			case 7:
				System.out.println("Logged Out!");
			default:
				System.out.println("Try Again! with valid option");

			}
		} while (options != 7);
	}
}
