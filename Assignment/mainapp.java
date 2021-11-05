import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class mainapp implements Serializable{
    public static void main(String[] args) throws ClassNotFoundException, IOException, Exception {
    	mainMenu testMenu = new mainMenu();
		testMenu = testMenu.readMenu();

		manageStaff testStaff = new manageStaff();
		testStaff = testStaff.readStaff();
		 

    	Scanner scan = new Scanner(System.in);
    	int choice;
    	do
    	{
        	System.out.println("\nPlease select your operations.");
        	System.out.println("(1) Manage Staff");
        	System.out.println("(2) Manage Menu");
			System.out.println("(3) Manage Orders");
        	System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");
        	choice = scan.nextInt();
        	scan.nextLine();
        	
        	switch(choice)
        	{
        	case 1:
        		testStaff.start();
				testStaff.saveStaffList();
        		break;
        	case 2:
        		testMenu.editMenu();
				testMenu.saveMenu();
        		break;
			case 3:
        		manageOrder.startOrder();
        		break;
        	default:
        			
        	}
        	
    	} while (choice != 6);
    	
    	System.out.println("Program Terminating...");
    }

	
}
