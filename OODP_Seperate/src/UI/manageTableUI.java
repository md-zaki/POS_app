package UI;
import java.util.Scanner;
import Manager.manageTable;

/**
 * This is a UI class used to display Table options
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class manageTableUI {

    /**
     * Starts the UI for the user to select the Table operations.
     * After selection of the desired operation, appropriate methods from the manageTable will be called.
     * @param table Table object which consist of all logic functions related to Table
     * @throws Exception
     */
    public static void start(manageTable table)throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("(1) Create Table");
            System.out.println("(2) Remove Table");
            System.out.println("(3) Display all Tables");
            System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
            case 1:
                table.addTable();
                break;
            case 2:
                table.removeTable();
                break;
            case 3:
                table.printTable();
            case 6:
                break;
            default:
                System.out.println("Please enter a valid option");
            }

        } while (choice != 6);
    }
}
