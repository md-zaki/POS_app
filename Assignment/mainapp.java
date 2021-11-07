import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.ManagerFactoryParameters;

public class mainapp implements Serializable {
	public static void main(String[] args) throws ClassNotFoundException, IOException, Exception {
		mainMenu testMenu = new mainMenu();
		manageStaff testStaff = new manageStaff();
		manageOrder testOrder = new manageOrder();
		manageMember testMember = new manageMember();
		manageReservation manageReserv = new manageReservation();
		manageTable testTable = new manageTable();

		// manageReservation testReservation = new manageReservation();
		Scanner scan = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\nPlease select your operations.");
			System.out.println("(1) Manage Staff");
			System.out.println("(2) Manage Menu");
			System.out.println("(3) Manage Orders");
			System.out.println("(4) Manage Customer Memberships");
			System.out.println("(5) Manage Reservations");
			System.out.println("(6) Manage Tables");
			System.out.println("(7) Exit");
			System.out.printf("Select a choice: ");
			choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
			case 1:
				try {
					testStaff = testStaff.readStaff();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
				testStaff.start();
				testStaff.saveStaffList();
				break;
			case 2:
				try {
					testMenu = testMenu.readMenu();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
				testMenu.editMenu();
				testMenu.saveMenu();
				break;
			case 3:

				try {
					testOrder = testOrder.readOrders();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
				testOrder.startOrder();
				testOrder.saveOrders();
				break;
			case 4:
				try {
					testMember = manageMember.readMemberList();
				} catch (Exception ex) {
					ex.getStackTrace();
				}

				testMember.start();
				break;
			case 5:
				try {
					testMember = manageMember.readMemberList();
					testTable = testTable.readTables();
					manageReserv = manageReserv.readReservation();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
				manageReserv.start(testTable, testMember);
				break;
			case 6:
				try {
					testTable = testTable.readTables();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
				testTable.start();
				break;
			case 7:
				break;
			default:
				System.out.println("Please enter a valid choice");
			}

		} while (choice != 7);

		System.out.println("Program Terminating...");
	}
}
