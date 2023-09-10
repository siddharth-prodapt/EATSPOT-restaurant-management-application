package inventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import restaurant.loginDashboad.RestaurantDashboard;

public class InventoryDriverMethod {
	public static void addItemToInventory() {
		String name;
		float price;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("\t\t\t\t\tEnter name     : ");
			name = br.readLine();
			System.out.println("\t\t\t\t\tEnter price    : ");
			price = Float.parseFloat(br.readLine());

			Item newItem = new Item(name, price, RestaurantDashboard.getRestaurantLoginId());
//			System.out.println("Restaurant ID  : "+RestaurantDashboard.getRestaurantLoginId());
			InventoryDBServices.addItemsToInventory(newItem);
		} catch (IOException e) {
			System.out.println("\t\t\t\t\tFail to add item to inventory. Try Again");
		}
	}

	public static void updateItemInInventory() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Item updateItem = new Item();

		try {
			System.out.println("Enter Item Id    : ");
			updateItem.setItemId(Integer.parseInt(br.readLine()));
			System.out.println("Enter Item Name  : ");
			updateItem.setItemName(br.readLine());
			System.out.println("Enter Item Price : ");
			updateItem.setItemPrice(Float.parseFloat(br.readLine()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		InventoryDBServices.updateItemInInventory(updateItem.getItemId(), updateItem);
	}

	public static void viewAllItemDetails() {
		
		ArrayList<Item> itemsList = InventoryDBServices.getAllItemDetails(RestaurantDashboard.getRestaurantLoginId());
		
		System.out.println("+--------+-----------------------------+-----------+");
		System.out.format("%10s%30s%12s", "Item ID |", "Item Name               |", "Item Price |\n");
		System.out.println("+--------+-----------------------------+-----------+");

		
		itemsList.forEach(ob->{
//			System.out.println("Item Id      : "+ ob.getItemId());
//			System.out.println("Item Name    : "+ ob.getItemName());
//			System.out.println("Item Price   : "+ ob.getItemPrice());
//			
			System.out.format("%8s  %28s  %12s", ob.getItemId(), ob.getItemName(), ob.getItemPrice());
			System.out.println();
		});
		
		System.out.println("+--------+-----------------------------+-----------+");
	}
}
