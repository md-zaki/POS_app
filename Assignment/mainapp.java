import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class mainapp implements Serializable{
    public static void main(String[] args) throws ClassNotFoundException, IOException {
    	manageStaff testStaff = new manageStaff();
    	mainMenu testMenu = new mainMenu();
    	Scanner scan = new Scanner(System.in);
    	int choice;
    	do
    	{
        	System.out.println("Please select your operations.");
        	System.out.println("(1) Manage Staff");
        	System.out.println("(2) Manage Menu");
        	System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");
        	choice = scan.nextInt();
        	scan.nextLine();
        	
        	switch(choice)
        	{
        	case 1:
        		testStaff.start();
        		break;
        	case 2:
        		testMenu.editMenu();
        		break;
        	default:
        			
        	}
        	
    	} while (choice != 6);
    	
    	System.out.println("Program Terminating...");
    }
}
