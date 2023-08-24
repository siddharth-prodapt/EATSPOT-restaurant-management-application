package restaurant.model;

public class Restaurant {
	private int restaurantId;
	private String restaurantName;
	private String address;
	private String location;
	private String city;
	private String state;
	private String emailId;
	private String userId;
	private String password;
	
	public Restaurant(){}
	
	public Restaurant(int restaurantid, String restaurantName, String address, String location, String city, String state, String userId,
			 String emailId) {
		super();
		this.restaurantId = restaurantid;
		this.restaurantName = restaurantName;
		this.address = address;
		this.location = location;
		this.city = city;
		this.state = state;
		this.userId = userId;
//		this.password = password;
		this.emailId = emailId;
	}

	public Restaurant(String restaurantName, String address, String location, String city, String state, String userId,
			String password, String emailId) {
		super();

		this.restaurantName = restaurantName;
		this.address = address;
		this.location = location;
		this.city = city;
		this.state = state;
		this.userId = userId;
		this.password = password;
		this.emailId = emailId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
