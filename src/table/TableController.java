package table;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.Scanner;

import restaurant.loginDashboad.RestaurantDashboard;

public class TableController {
	public static void displayCreateTable() {
		String name, desc;
		int capacity;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("\n------------");
			System.out.println("Create Table");
			System.out.println("--------------");
			System.out.println("Table Name: ");
			name = br.readLine();
			System.out.println("Table Capacity: ");
			capacity = Integer.parseInt(br.readLine());
			System.out.println("Table Desc: ");
			desc = br.readLine();

			int restaurantId = RestaurantDashboard.getRestaurantLoginId();

			Table newTable = new Table(name, capacity, desc, restaurantId);

			TableDBServices.createTable(newTable);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in table creation...");
		}
	}

	public static void displayAllTableDetails() {
//		to show all registered table details
// connect->searchRestaurantTable->Regd
		System.out.println("ALL REGD TABLES");
		System.out.println("-----------------");

		int resId = RestaurantDashboard.getRestaurantLoginId();

		TableDBServices.getAllTableDetails(resId);
	}

	public static void displayAvailableTable() {
		System.out.println("Available Tables/Unoccupied Tables");
		
		TableDBServices.getAvailableTable();
	}
	void updateTableDetailsMenu() {

	}

	void deleteTableMenu() {

	}

	public static void searchTableById() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter table ID: ");
		int tableId = sc.nextInt();
		TableDBServices.findTableDetailsById(tableId);
	}

}
