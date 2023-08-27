package table;

public class Table {
	private int tableId;
	private String tableName;
	private int tableQuantity; //nos of persons allowed to sit
	private String tableDesc;
	private int restaurantId;
	
	Table(String tableName, int tableQuantity, String tableDesc){
		this.tableName = tableName;
		this.tableQuantity = tableQuantity;
		this.tableDesc = tableDesc;
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
	public int getTableQuantity() {
		return tableQuantity;
	}
	public void setTableQuantity(int tableQuantity) {
		this.tableQuantity = tableQuantity;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	
}
