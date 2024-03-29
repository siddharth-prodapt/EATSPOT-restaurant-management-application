package invoice;

import java.util.ArrayList;

import inventory.Item;

public class Invoice {
	private int invoice_id;
	private int customer_id;
	private float bill_amount;
	private String invoice_dt;
	private int restaruant_id;
	private int order_id;
	private int table_id;
	
	public ArrayList<Item>orderedItemsList = new ArrayList<Item>();
	
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	
	public Invoice() {
		
	}
	public Invoice(int invoiceId, int customerId,float billAmount,String invoiceDt, int resId, int orderId) {
		this.invoice_id = invoiceId;
		this.customer_id  = customerId;
		this.bill_amount = billAmount;
		this.invoice_dt = invoiceDt;
		this.restaruant_id = resId;
		this.order_id = orderId;
	}
	
	public void addOrderedItemsInList(Item item) {
		orderedItemsList.add(item);
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public float getBill_amount() {
		return bill_amount;
	}
	public void setBill_amount(float bill_amount) {
		this.bill_amount = bill_amount;
	}
	public String getInvoice_dt() {
		return invoice_dt;
	}
	public void setInvoice_dt(String invoice_dt) {
		this.invoice_dt = invoice_dt;
	}
	public int getRestaruant_id() {
		return restaruant_id;
	}
	public void setRestaruant_id(int restaruant_id) {
		this.restaruant_id = restaruant_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	
}
