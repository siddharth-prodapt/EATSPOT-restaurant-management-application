package inventory;

public class Item {
	private int itemId;
	private String itemName;
	private float itemPrice;
	private int quantity;
	private int restaurant_id;
	private float itemAmount;
	
	public Item() {
		
	}
	public Item(int itemId, String itemName, float itemPrice, int itemQuantity, float itemAmount){
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = itemQuantity;
		this.itemAmount = itemAmount;
	}
	public Item(String itemName, float itemPrice, int restaurant_id) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.restaurant_id = restaurant_id;
	}
	public Item(int itemId, String itemName, float itemPrice) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
}
