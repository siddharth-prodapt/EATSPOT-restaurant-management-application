package user;

import java.util.ArrayList;
import java.util.Scanner;

//import com.mysql.cj.xdevapi.Table;

import inventory.InventoryDBServices;
import inventory.Item;
import order.OrderDBServices;
import restaurant.RestaurantDBServices;
import restaurant.model.Restaurant;
import table.Table;
import table.TableDBServices;
import user.model.User;

public class UserDashboard {

	private static User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		UserDashboard.currentUser = currentUser;
	}

	private static void orderFood() {
		
		ArrayList<Restaurant> allRestaurantList = RestaurantDBServices.getAllRestaurantList();
		int tableId = -1;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nAVAILABLE RESTAURANT LIST");
		System.out.println("+-----+--------------------+----------+---------------------+");
		System.out.format("%7s%21s%11s%22s", "|ResId|", "Restaurant Name|", "City|", "Email Id|\n");
		System.out.println("+-----+--------------------+----------+---------------------+");
	
		allRestaurantList.forEach(res->{
			System.out.format("%7s%20s%11s%22s" ,Integer.toString(res.getRestaurantId())+"  ", res.getRestaurantName(), res.getCity(), res.getEmailId());
			
			System.out.println("");
			System.out.println("|-----|--------------------|----------|---------------------|");
		});	
		System.out.println("Enter Restaurant Id: ");
		int resId = sc.nextInt();
		
		
		ArrayList<Item> resItemList = InventoryDBServices.getAllItemDetails(resId);
		System.out.println("---------------------");
		System.out.println("Available Items List");
		System.out.println("---------------------");
		System.out.println("+--------+-----------------------------+-----------+");
		System.out.format("%10s%30s%12s", "Item ID |", "Item Name               |", "Item Price |\n");
		System.out.println("+--------+-----------------------------+-----------+");
	
		resItemList.forEach(item->{
			System.out.format("%10s%30s%12s", Integer.toString(item.getItemId())+"  ", item.getItemName()+"  ", Float.toString(item.getItemPrice())+"  " );
			System.out.println("");
			System.out.println("|--------|-----------------------------|-----------|");
		});
		
		System.out.println("Enter item Id to select : ");
		int itemId = sc.nextInt();
		System.out.println("Enter item quantity: ");
		int itemQty = sc.nextInt();
	
//		ORDER TYPE
		int orderType = -1;
		do {
		System.out.println("1. DineIn\n2. Takeaway");
		orderType = sc.nextInt();
		}while(orderType<1 && orderType>2);
		
		if(orderType == 1) {
			//Dine In
			ArrayList<Table> restaurantTableList = TableDBServices.getAvailableTableOfRestaurant(resId);
			System.out.println("\t\t\t\t\t----------------------");
			System.out.println("\t\t\t\t\tAVAILABLE TABLE LIST");
			System.out.println("\t\t\t\t\t----------------------");
			System.out.println("+--------+-----------------------------+---------+-----------------------------------------------------------+");
			System.out.format("%10s%30s%10s%60s", "TableID |", "Table Name |", "Capacity |", "Description |");
			System.out.println();
			System.out.println("+---------+------------------------------+---------+-----------------------------------------------------------+");
			restaurantTableList.forEach(table->{
				System.out.format(" %8s %29s %9s %59s ", table.getTableId(), table.getTableName(), table.getTableCapacity(), table.getTableDesc());
				System.out.println();
				System.out.println("|--------|-----------------------------|---------|-----------------------------------------------------------|");
			});
			
			System.out.println("Enter table Id: ");
			tableId = sc.nextInt();
			System.out.println("Table Reserved for "+UserDashboard.currentUser.getUserName());
			
			int orderId = OrderDBServices.createOrderId(tableId, UserDashboard.currentUser.getUserId());
			OrderDBServices.orderItems(orderId, itemId, itemQty);
			System.out.println("Your Order ID: "+orderId);
		}
		if(orderType == 2) {
//			Takeaway

			int orderId = OrderDBServices.createOrderId(tableId, UserDashboard.currentUser.getUserId());
			OrderDBServices.orderItems(orderId, itemId, itemQty);
			
			System.out.println("Order Id: "+orderId);
			System.out.println("Your food will be prepared.. after few minutes");
			System.out.println("Kindly Collect from counter.. after clearing the above mentioned bill id");
		}
	}

	private static void viewUserProfile() {
//		cuurentUser
		System.out.println("My Profile Details");
		System.out.println("------------------");

		User user = UserDashboard.currentUser;
		if (user == null) {
			System.out.println("User Not logged In");
			return;
		}
		System.out.println("UserId  : " + user.getUserId());
		System.out.println("Name    : " + user.getUserName());
		System.out.println("EmailId : " + user.getUserEmailId());
		System.out.println("PhoneNo : " + user.getUserPhoneNo());
		System.out.println("LoginId : " + user.getUserLoginId());
		
	}
	public static void viewInvoice() {
		
	}

	public static void showDashboard() {
		System.out.println("\t\t\t\t\t------------------");
		System.out.println("\t\t\t\t\tWelcome "+UserDashboard.currentUser.getUserName());
		System.out.println("\t\t\t\t\t------------------");
		Scanner sc = new Scanner(System.in);
		int option = -1;
		System.out.println("\t\t\t\t\t1. Order Food\n\t\t\t\t\t2. View Profile Detais\n\t\t\t\t\t3.View Invoice");

		System.out.println("Choose your option: ");
		option = sc.nextInt();

		switch (option) {
		case 1:
			orderFood();
			break;

		case 2:
			viewUserProfile();
			break;
		case 3:
			viewInvoice();
			break;
		default:
			System.out.println("Try again with correct option!");
		}
	}
}
