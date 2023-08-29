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

			System.out.println("Enter name: ");
			name = br.readLine();
			System.out.println("Enter price:");
			price = Float.parseFloat(br.readLine());

			Item newItem = new Item(name, price, RestaurantDashboard.getRestaurantLoginId());
			System.out.println("Restaurant ID: "+RestaurantDashboard.getRestaurantLoginId());
			InventoryDBServices.addItemsToInventory(newItem);
		} catch (IOException e) {
			System.out.println("Add item to inventory exceptions");
		}
	}

	public static void updateItemInInventory() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Item updateItem = new Item();

		try {
			System.out.println("Enter Item Id   : ");
			updateItem.setItemId(Integer.parseInt(br.readLine()));
			System.out.println("Enter Item Name : ");
			updateItem.setItemName(br.readLine());
			System.out.println("Enter Item Price: ");
			updateItem.setItemPrice(Float.parseFloat(br.readLine()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InventoryDBServices.updateItemInInventory(updateItem.getItemId(), updateItem);
	}

	public static void viewAllItemDetails() {
		
		ArrayList<Item> itemsList = InventoryDBServices.getAllItemDetails(RestaurantDashboard.getRestaurantLoginId());
		
		itemsList.forEach(ob->{
			System.out.println("\nItem Id    : "+ob.getItemId());
			System.out.println("Item Name  : "+ob.getItemName());
			System.out.println("Item Price : "+ob.getItemPrice());
		});
		
	}
}
