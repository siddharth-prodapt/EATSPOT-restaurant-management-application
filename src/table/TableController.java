package table;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TableController {
	void createTable() {
		String name, desc;
		int qty;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("Create Table");
			System.out.println("--------------");
			System.out.println("Table Name: ");
			name = br.readLine();
			System.out.println("Table Quantity: ");
			qty = Integer.parseInt(br.readLine());
			System.out.println("Table Desc: ");
			desc = br.readLine();
			
			Table newTable = new Table(name, qty, desc);
			
//			create DB connection
			/*
			 * insert new table
			 *  data in particular restaurant id
			 */
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in table creation...");
		}
	}
	
	
	void displayTableDetails() {
//		to show all registered table details
// connect->searchRestaurantTable->Regd
	}
	
	void updateTableDetails() {
		
	}
	
	void deleteTable() {
		
	}
	
	void searchTable(int tableId) {
		
	}
	
}
