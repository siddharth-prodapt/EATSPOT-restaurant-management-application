package invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import databaseCon.ConnectDB;
import inventory.Item;
import restaurant.loginDashboad.RestaurantDashboard;

public class InvoiceDBServices {

	public static void generateInvoiceByOrderId(int orderId) {
		ConnectDB ob = new ConnectDB();
		Connection con = ob.getConnection();

		try {
			String queryTest = "select o.id as orderId, o.customer_id, item.id as itemId, item.name, item.price, od.quantity, item.price*od.quantity as Amount from orders as o LEFT JOIN order_details as od on o.id=od.order_id LEFT JOIN items as item on od.item_id=item.id where o.id = ? and o.restaurant_id=?";
//			String query = "INSERT INTO invoices(customer_id, bill_amount, invoice_dt, restaurant_id, order_id) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(queryTest);

			ps.setInt(1, orderId);
			ps.setInt(2, RestaurantDashboard.getRestaurantLoginId());

			ResultSet rs = ps.executeQuery();

			int count = 0;
			float sumTotal = 0f;
			int itemId;
			String itemName;
			float itemPrice;
			int itemQuantity;
			float itemAmount;
			int customerId = -1;
			Invoice bill = new Invoice();
			
			while (rs.next()) {
				if(count == 0)
					customerId = rs.getInt("customer_id");	
				
				itemId = rs.getInt("itemId");
				itemName = rs.getString("name");
				itemPrice = rs.getFloat("price");
				itemQuantity = rs.getInt("quantity");
				itemAmount = rs.getFloat("amount");
				
				Item item = new Item(itemId, itemName, itemPrice, itemQuantity, itemAmount);
				
				bill.addOrderedItemsInList(item);
				
				sumTotal += rs.getFloat("amount");
				count++;
			}

			bill.setBill_amount(sumTotal);
			bill.setCustomer_id(customerId);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
//			   System.out.println(dtf.format(now)); 

			bill.setInvoice_dt(dtf.format(now));
			System.out.println("Bill Date: "+ bill.getInvoice_dt());
			
			bill.setOrder_id(orderId);
			bill.setRestaruant_id(RestaurantDashboard.getRestaurantLoginId());
			
			
			System.out.println("Invoice Generated!");
//			return bill;
			
			String query1 = "INSERT INTO invoices(customer_id, bill_amount, invoice_dt, restaurant_id, order_id ) values (?, ?, ?, ?, ?)";
			ps.close();
			
			ps = con.prepareStatement(query1);
			
			
			ps.setInt(1, bill.getCustomer_id());
			ps.setFloat(2, bill.getBill_amount());
			ps.setString(3, bill.getInvoice_dt());
			ps.setInt(4, bill.getRestaruant_id());
			ps.setInt(5, bill.getOrder_id());
			
//			ps.executeQuery(query1);
			ps.executeUpdate();
			
			System.out.println("Invoice data inserted in DB");
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderID not available. Try Again!");
		}

	}

	public static void getAllInvoiceDetails() {

	}

	public static void getInvoiceDetailsByOrderId() {

	}

	public static void getInvoiceDetailsById() {

	}

	public static void getInvoiceDetailsByCustomerId() {

	}
}
