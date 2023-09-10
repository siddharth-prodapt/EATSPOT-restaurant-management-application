package order;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import inventory.InventoryDBServices;
import inventory.Item;
import restaurant.loginDashboad.RestaurantDashboard;

public class OrderDriverMethod {
	private static int orderId;
	
	public static void setOrderId(int id) {
		orderId = id;
	}
	public int getOrderId() {
		return orderId;
	}
	
	public static void orderItems() {
		Scanner sc = new Scanner(System.in);
		
		int options = -1;
		int qty = 1;
		float subTotal = 0;
		int itemId = -1;

		System.out.println("\t\t\t\t\tEnter orderId : ");
		int orderId = sc.nextInt();
		
		ArrayList<Item> itemsList = InventoryDBServices
				.getAllItemDetails(RestaurantDashboard.getRestaurantLoginId());
		Map<Integer, Item> itemsMap = new TreeMap<Integer, Item>();

		do {
			
			System.out.println("\n\n\n\n\t\t\t\t\t---------------");
			System.out.println("\t\t\t\t\tAVAILABLE ITEMS");
			System.out.println("\t\t\t\t\t----------------");

			itemsList.forEach(item -> {
				System.out.println(
						item.getItemId() + " . " + item.getItemName() + " \t" + "Rs. " + item.getItemPrice());
				itemsMap.put(item.getItemId(), item);
			});

			System.out.println("Enter Item Id   :  ");
			itemId = sc.nextInt();
			System.out.println("Enter Item Qty  : ");
			qty = sc.nextInt();

			subTotal = subTotal + itemsMap.get(itemId).getItemPrice() * qty;
			
			OrderDBServices.orderItems(orderId, itemId, qty);

			System.out.println("Do you want to order again(1-Yes/0-No): ");
			options = sc.nextInt();
		} while (options == 1);

	}
	public static void createOrderIdAndReserveTable() {
		int tableNo;
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t\t\tEnter Table no : ");
		tableNo = sc.nextInt();

		if (OrderDBServices.isTableAvailable(tableNo)) {
//			getTableDetails
			if (OrderDBServices.toggleTableAvailabilityById(tableNo, false)) {
//				Open order tab
				int orderId = OrderDBServices.createOrderId(tableNo, 0); //customerId =  0 for non regd user
//				
				setOrderId(orderId);
				
				System.out.println("\t\t\t\t\tTable Booked for Customer!");
				System.out.println("\t\t\t\t\tReady to serve meals");
				System.out.println("\t\t\t\t\tOrder ID: "+orderId +"\tTableNo: "+tableNo);
			}
		}
		else {
			System.out.println("Selected table is already booked !");
		}
	}
}
