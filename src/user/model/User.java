package user.model;

public class User {
	private int userId;
	private String userName;
	private String userEmailId;
	private String userPhoneNo;
	private String userLoginId;
	private String userPassword;
	
	
	public User() {
		
	}
	public User(int id, String name, String phone , String emailId, String userId) {
		this.userId = id;
		this.userName = name;
		this.userEmailId = emailId;
		this.userPhoneNo = phone;
		this.userLoginId = userId;
	}
	
	public User(String name, String emailId, String phoneNo, String loginId, String password) {
		// TODO Auto-generated constructor stub
		this.userName = name;
		this.userEmailId = emailId;
		this.userPhoneNo = phoneNo;
		this.userLoginId = loginId;
		this.userPassword = password;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	
	public String getUserPhoneNo() {
		return userPhoneNo;
	}
	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
