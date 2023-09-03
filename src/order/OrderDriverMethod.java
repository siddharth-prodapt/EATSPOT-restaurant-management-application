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

		System.out.println("Enter orderId: ");
		int orderId = sc.nextInt();
		
		ArrayList<Item> itemsList = InventoryDBServices
				.getAllItemDetails(RestaurantDashboard.getRestaurantLoginId());
		Map<Integer, Item> itemsMap = new TreeMap<Integer, Item>();

		do {
			System.out.println("\n---------------");
			System.out.println("Item Available");

			itemsList.forEach(item -> {
				System.out.println(
						item.getItemId() + " . " + item.getItemName() + " \t" + "Rs. " + item.getItemPrice());
				itemsMap.put(item.getItemId(), item);
			});

			System.out.println("Enter Item Id:  ");
			itemId = sc.nextInt();
			System.out.println("Enter Item Qty: ");
			qty = sc.nextInt();

			subTotal = subTotal + itemsMap.get(itemId).getItemPrice() * qty;
			
			OrderDBServices.orderItems(orderId, itemId, qty);

			System.out.println("Do you want to again order(1-Yes/0-No): ");
			options = sc.nextInt();
		} while (options == 1);

	}
	public static void createOrderIdAndReserveTable() {
		int tableNo;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Table no: ");
		tableNo = sc.nextInt();
//		int index = 0;

		if (OrderDBServices.isTableAvailable(tableNo)) {
//			getTableDetails
			if (OrderDBServices.bookTableById(tableNo)) {
//				Open order tab
				int orderId = OrderDBServices.createOrderId(tableNo);
//				
				setOrderId(orderId);
				
				System.out.println("Table Booked for Customer!");
				System.out.println("Ready to serve meals");
				System.out.println("Order ID: "+orderId +"\tTableNo: "+tableNo);
			}

			
			/*
			 * create order - orderId, cusomerId-NA, subTotal=(itemId.price)*qty,
			 * tableId=tableNo, isDineIn=true" order details - orderid=getOrderId(having
			 * table No == tableNo) itemI = itemId, price = item.price, qty=?
			 * 
			 */

			// setTable availabiltiy to booked
		}
		else {
			System.out.println("Selected table is not available!");
		}

//		chk table is it available
//		if yes book it
// view available items
//		enter quantity
	}
}
