package invoice;

import java.io.FileWriter;
import java.util.Scanner;

import order.OrderDBServices;
import restaurant.loginDashboad.RestaurantDashboard;
import utility.UtilityMethod;

public class InvoiceDriverMethods {

	public static void displayBill() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\t\t\t\t\tEnter order ID: ");
		int orderId = sc.nextInt();

		Invoice bill = InvoiceDBServices.generateInvoiceByOrderId(orderId);
		if (bill != null) {
			System.out.println("\n\n\n\n\t\t\t\t\t\t\tINVOICE");
			System.out.println(
					"\t\t+------------------------------------------------------------------------------------------+");
			System.out.println("\t\t\t\t\t\t" + RestaurantDashboard.getCurrentRestaurant().getRestaurantName());
			System.out.println(
					"\t\t+------------------------------------------------------------------------------------------+");
			System.out.println("\t\t\t\t\t\t" + RestaurantDashboard.getCurrentRestaurant().getState());
			System.out.println("\t\t\t\t\t\t" + RestaurantDashboard.getCurrentRestaurant().getLocation());
			System.out.println(
					"\t\t+------------------------------------------------------------------------------------------+");
			System.out
					.println("\t\t    Restaurant ID  : " + RestaurantDashboard.getCurrentRestaurant().getRestaurantId()
							+ "\t\t\tInvoice Date: " + bill.getInvoice_dt());
			System.out.println("\t\t    Invoice ID     : " + bill.getInvoice_id());
			System.out.println("\t\t    Restaurant ID  : " + bill.getRestaruant_id());
			System.out.println(
					"\t\t    Customer ID    : " + bill.getCustomer_id() + "\t\t\tOrder ID   :" + bill.getOrder_id());
//				System.out.println("\t\t    Order ID       : " + bill.getOrder_id());
			System.out.println(
					"\t\t+------------------------------------------------------------------------------------------|");

			System.out.println(
					"\t\t                                        INVOICE                                             ");
			String ob = "asdf";

			System.out.println("\t\t\t\t---------------------------------------------------------------");
			System.out.format("\t\t\t\t| %20s | %10s | %10s | %10s |", "Item Description", "Qty", "Price", "Amount");
			System.out.println();
			System.out.println("\t\t\t\t---------------------------------------------------------------");

			bill.orderedItemsList.forEach(item -> {
				System.out.format("\t\t\t\t| %20s | %10s | %10s | %10s |", item.getItemName(), item.getQuantity(),
						item.getItemPrice(), item.getItemAmount());
				System.out.println();
			});

//				System.out.println("+------------------------------------------------------------------------------------------+");
			System.out.println("\t\t\t\t---------------------------------------------------------------");
			System.out.format("\t\t\t\t%60s", "   Bill Amount : " + Float.toString(bill.getBill_amount()));
			System.out.println();

			System.out.println("\t\t\t\t---------------------------------------------------------------");

			System.out.println("\n\t\t\t\t\tThank You");
			System.out.println("\t\t\t\t\tVisit Again..");
			System.out.println("\n\n\n");

			

			if (OrderDBServices.setInvoiceGenerated(bill.getOrder_id()))
				UtilityMethod.writeBillToFile(bill);
			OrderDBServices.toggleTableAvailabilityById(bill.getTable_id(), true);
		}

	}
}
