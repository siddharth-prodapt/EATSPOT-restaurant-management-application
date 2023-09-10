package restaurant.loginDashboad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import inventory.InventoryDriverMethod;
import invoice.InvoiceDBServices;
import invoice.InvoiceDriverMethods;
import order.OrderDBServices;
import order.OrderDriverMethod;
import restaurant.RestaurantDBServices;
import restaurant.model.Restaurant;
import table.Table;
import table.TableController;
import table.TableDBServices;

public class RestaurantDashboard {
	private static int restaurantLoginId;
	private static Restaurant currentRestaurant;

	public static void setRestaurantLoginId(int resId) {
		restaurantLoginId = resId;
	}

	public static int getRestaurantLoginId() {
		return restaurantLoginId;
	}

	public static Restaurant getCurrentRestaurant() {
		return currentRestaurant;
	}

	public static void setCurrentRestaurant(Restaurant curRes) {
		currentRestaurant = curRes;
	}

	private static void displayRestaurantDetails() {
		Restaurant restaurant = RestaurantDBServices.getRestaurantById(restaurantLoginId);

		System.out.println("\t\t\t\t\tRestaurant Id   : " + restaurant.getRestaurantId());
		System.out.println("\t\t\t\t\tName            : " + restaurant.getRestaurantName());
		System.out.println("\t\t\t\t\tAddress         : " + restaurant.getAddress());
		System.out.println("\t\t\t\t\tLocation        : " + restaurant.getLocation());
		System.out.println("\t\t\t\t\tCity            : " + restaurant.getCity());
		System.out.println("\t\t\t\t\tState           : " + restaurant.getState());
		System.out.println("\t\t\t\t\tEmail Id        : " + restaurant.getEmailId());
		System.out.println("----------------------------------------------------------------------");
	}

	private static void updateRestaurantDetails() {

		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t\t\tDo you really want to update details(y/n): ");
		String choice = sc.next();

		if (choice.toLowerCase().equals("n")) {
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Restaurant restaurant = new Restaurant();

		String name = "", location = "", city = "", state = "", address = "";
		try {
			System.out.println("\t\t\t\t\tEnter restaurant name   : ");
			name = br.readLine();
			System.out.println("\t\t\t\t\tStreet/Building         : ");
			location = br.readLine();
			System.out.println("\t\t\t\t\tCity                    :");
			city = br.readLine();
			System.out.println("\t\t\t\t\tState                   :");
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
			System.out.println("\n\t\t\t\t\t------------------");
			System.out.println("\t\t\t\t\tInventory Services");
			System.out.println("\t\t\t\t\t-------------------");
			System.out.println(
					"\t\t\t\t\t1. Add Item to Inventory\n\t\t\t\t\t2. Update Item in Inventory\n\t\t\t\t\t3. View All Inventory items\n\t\t\t\t\t4. Exit");
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
			case 4:
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
			System.out.println("\t\t\t\t\t------------");
			System.out.println("\t\t\t\t\tTABLE MENU");
			System.out.println("\t\t\t\t\t------------\n");
			System.out.println(
					"\t\t\t\t\t1. Create Table \n\t\t\t\t\t2. Update Table Details \n\t\t\t\t\t3. Table Availability \n\t\t\t\t\t4. Search Table By Id \n\t\t\t\t\t5. Show all tables\n\t\t\t\t\t6. Delete Table \n\t\t\t\t\t7. Exit");

			System.out.println("\t\t\t\t\tSelect options: ");
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
			case 7:
				System.out.println("Closing...");
				break;
			default:
				System.out.println("\t\t\t\tTry Again");
			}

		} while (options != 7);
	}

	private static void displayOrderMenu() {
		Scanner sc = new Scanner(System.in);
		int options = -1;
		do {
			System.out.println("\t\t\t\t\t-----------");
			System.out.println("\t\t\t\t\tOrder Menu");
			System.out.println("\t\t\t\t\t-----------");

			System.out.println(
					"\t\t\t\t\t1. Generate Order Id and Reserve Table\n\t\t\t\t\t2. Order Items at Table\n\t\t\t\t\t3. Exit ");
			options = sc.nextInt();
			switch (options) {
			case 1:
				OrderDriverMethod.createOrderIdAndReserveTable();
				break;
			case 2:
				OrderDriverMethod.orderItems();
				break;
			// order food
			// enter order Id
			}
		} while (options != 3);
	}

	public static void invoiceServices() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t\t\t--------------");
		System.out.println("\t\t\t\t\tInvoice Menu");
		System.out.println("\t\t\t\t\t--------------");
		int option = -1;
		do {
			System.out.println(
					"\t\t\t\t\t1. Generate Invoice Using Order\n\t\t\t\t\t2. Exit\n\t\t\t\t\t3. View Unpaid Orders");
			option = sc.nextInt();
			switch (option) {
			case 1:
				InvoiceDriverMethods.displayBill();
				break;
			case 2:
				break;
			case 3:
				OrderDBServices.viewUnpaidOrders();
				break;
			default:
				System.out.println("\t\t\t\t\tTry Again");
			}
		} while (option != 2);

	}

	public static void showRestaurantDashboard() {
		System.out.println("\n\t\t\t\t\t---------------------");
		System.out.println("\t\t\t\t\tRESTAURANT DASHBOARD");
		System.out.println("\t\t\t\t\t---------------------");
		int options = -1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(
					"\t\t\t\t\t1. Restaurant Details\n\t\t\t\t\t2. Update Restaurant Details\n\t\t\t\t\t3. Orders\n\t\t\t\t\t4. Tables\n\t\t\t\t\t5. Inventory\n\t\t\t\t\t6. Invoice Management\n\t\t\t\t\t7. Logout");
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
				invoiceServices();
				break;
			case 7:
				System.out.println("\t\t\t\t\tLogged Out!");
				break;
			default:
				System.out.println("\t\t\t\t\tTry Again! with valid option");

			}
		} while (options != 7);
	}
}
