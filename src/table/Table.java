package table;

public class Table {
	private int tableId;
	private String tableName;
	private int tableCapacity; 
	private String tableDesc;
	private int restaurantId;
	private boolean isAvailable;
	
	public Table(int tableId, String tableName, int tableCapacity, String tableDesc) {
		this.tableId = tableId;
		this.tableName = tableName;
		this.tableCapacity = tableCapacity;
		this.tableDesc = tableDesc;
	}
	
	
	public Table(String tableName, int tableQuantity, String tableDesc, int restaurantId){
		this.tableName = tableName;
		this.tableCapacity = tableQuantity;
		this.tableDesc = tableDesc;
		this.setRestaurantId(restaurantId);
	}
	
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getTableCapacity() {
		return tableCapacity;
	}
	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
