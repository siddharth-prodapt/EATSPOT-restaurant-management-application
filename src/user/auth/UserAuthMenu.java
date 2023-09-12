package user.auth;

import java.util.Scanner;

import user.UserDashboard;

public class UserAuthMenu {
	public static void displayUserAuthMenu() {
		int option = -1;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("\t\t\t\t\t------------------");
			System.out.println("\t\t\t\t\tCustomer Auth Menu");
			System.out.println("\t\t\t\t\t------------------");
			System.out.println("\t\t\t\t\t1. SignUp\n\t\t\t\t\t2. Login");
			option = sc.nextInt();

			switch (option) {
			case 1:
				UserAuthController.signUp();
				break;
			case 2:
				
				if(UserAuthController.login()) {
//					System.out.println("Hii.. customer logged In");
					UserDashboard.showDashboard();
				}
				break;
			default:
				System.out.println("Enter correct Option");
			}
		} while (option != 3);

	}
}
