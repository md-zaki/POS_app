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
 * @version 1.0
 * @since 2021-11-11
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
            List<Object> testObj = init(testMenu, testStaff, testOrder, testMember, manageReserv, testTable);
            testMenu = (mainMenu) testObj.get(0);
            testStaff = (manageStaff) testObj.get(1);
            testOrder = (manageOrder) testObj.get(2);
            testMember = (manageMember) testObj.get(3);
            manageReserv = (manageReservation) testObj.get(4);
            testTable = (manageTable) testObj.get(5);
            
            mainAppMenu();
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
     * This function takes in all existing objects of the system and initialize them by reading the existing saved data text file
     * @param testMenu mainMenu object to be initialized
     * @param testStaff manageStaff object to be initialized
     * @param testOrder manageOrder object to be initialized
     * @param testMember manageMember object to be initialized
     * @param manageReserv manageReservation object to be initialized
     * @param testTable manageTable object to be initialized
     * @return List of Objects to be return
     */
    public static List<Object> init(mainMenu testMenu, manageStaff testStaff, manageOrder testOrder, manageMember testMember, manageReservation manageReserv, manageTable testTable)
    { 
        List<Object> objList = new ArrayList<>();
        try {
            objList.add(testMenu.readMenu());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            objList.add(testStaff.readStaff());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            objList.add(testOrder.readOrders());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            objList.add(testMember.readMemberList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            objList.add(manageReserv.readReservation());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            objList.add(testTable.readTables());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return objList;
    }
}
