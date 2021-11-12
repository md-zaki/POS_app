import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import Manager.*;
import UI.mainMenuUI;
import UI.manageMemberUI;
import UI.manageOrderUI;
import UI.manageReservationUI;
import UI.manageStaffUI;
import UI.manageTableUI;

/**
 * This is the Main Class for the entire application
 * @author Ju Khang, Zaki
 * @version 1.1
 * @since 2021-11-13
 */
public class mainapp implements Serializable {
    public static void main(String[] args) throws Exception {
        mainMenu testMenu = new mainMenu();
        manageStaff testStaff = new manageStaff();
        manageOrder testOrder = new manageOrder();
        manageMember testMember = new manageMember();
        manageReservation manageReserv = new manageReservation();
        manageTable testTable = new manageTable();

        Scanner scan = new Scanner(System.in);
        int choice;
        do {            
            testMenu = init(testMenu);
            testStaff = init(testStaff);
            testOrder = init(testOrder);
            testMember = init(testMember);
            manageReserv = init(manageReserv);
            testTable = init(testTable);
            mainAppMenu();

            while (!scan.hasNextInt())
            {
                System.out.println("Please enter a valid option.");
                System.out.printf("Select a choice: ");
                scan.next();
            }

            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
            case 1:
                manageStaffUI.start(testStaff);
                testStaff.saveStaffList();
                break;
            case 2:
                mainMenuUI.start(testMenu);
                testMenu.saveMenu();
                break;
            case 3:
                manageOrderUI.start(testOrder);
                testOrder.saveOrders();
                break;
            case 4:
                manageMemberUI.start(testMember);
                testMember.saveMemberList();
                break;
            case 5:
                manageReservationUI.start(testTable, testMember, manageReserv);
                manageReserv.saveReservation();
                break;
            case 6:
                manageTableUI.start(testTable);
                testTable.saveTables();
                break;
            case 7:
                break;
            default:
                System.out.println("Please enter a valid choice");
            }

        } while (choice != 7);

        System.out.println("Program Terminating...");
    }

    /**
     * This function simply prints the Overall Main Menu for the Application
     */
    public static void mainAppMenu()
    {
        System.out.println("\nPlease select your operations.");
        System.out.println("(1) Manage Staff");
        System.out.println("(2) Manage Menu");
        System.out.println("(3) Manage Orders");
        System.out.println("(4) Manage Customer Memberships");
        System.out.println("(5) Manage Reservations");
        System.out.println("(6) Manage Tables");
        System.out.println("(7) Exit");
        System.out.printf("Select a choice: ");
    }

    /**
     * This function initialize the mainMenu object by reading an existing saved txt file.
     * If file does not exist, system will create a new txt file upon any successful mainMenu operations.
     * @param testMenu mainMenu object to be initialized
     * @return initialized mainMenu object
     */
    public static mainMenu init(mainMenu testMenu)
    {
        try {
            testMenu = testMenu.readMenu();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return testMenu;
    }

    /**
     * This function initialize the manageStaff object by reading an existing saved txt file
     * If file does not exist, system will create a new txt file upon any successful manageStaff operations.
     * @param testStaff manageStaff object to be initialized
     * @return initialized manageStaff object
     */
    public static manageStaff init(manageStaff testStaff)
    {
        try {
            testStaff = testStaff.readStaff();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return testStaff;
    }

    /**
     * This function initialize the manageOrder object by reading an existing saved txt file
     * If file does not exist, system will create a new txt file upon any successful manageOrder operations.
     * @param testOrder manageOrder object to be initialized
     * @return initialized manageOrder object
     */
    public static manageOrder init(manageOrder testOrder)
    {
        try {
            testOrder = testOrder.readOrders();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return testOrder;
    }

    /**
     * This function initialize the manageMember object by reading an existing saved txt file
     * If file does not exist, system will create a new txt file upon any successful manageMember operations.
     * @param testMember manageMember object to be initialized
     * @return initialized manageMember object
     */
    public static manageMember init(manageMember testMember)
    {
        try {
            testMember = testMember.readMemberList();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return testMember;
    }

    /**
     * This function initialize the manageReservation object by reading an existing saved txt file
     * If file does not exist, system will create a new txt file upon any successful manageReservation operations.
     * @param manageReserv manageReservation object to be initialized
     * @return initialized manageReservation object
     */
    public static manageReservation init(manageReservation manageReserv)
    {
        try {
            manageReserv = manageReserv.readReservation();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return manageReserv;
    }

    /**
     * This function initialize the manageTable object by reading an existing saved txt file
     * If file does not exist, system will create a new txt file upon any successful manageTable operations.
     * @param testTable manageTable object to be initialized
     * @return initialized manageTable object
     */
    public static manageTable init(manageTable testTable)
    {
        try {
            testTable = testTable.readTables();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return testTable;
    }
}
