package UI;
import java.util.Scanner;
import Manager.manageOrder;

/**
 * This is a UI class used to display order options
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class manageOrderUI {
    /**
     * Starts the UI for the user to select the menu order operations.
     * After selection of the desired operation, appropriate methods from the manageOrder will be called.
     * @param manageOrder manageOrder object which consist of all logic functions related to order
     * @throws Exception
     */
    public static void start(manageOrder manageOrder) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n(1) Create new order");
            System.out.println("(2) Edit Existing order");
            System.out.println("(3) View all orders");
            System.out.println("(4) Print invoice");
            System.out.println("(5) Remove order");
            System.out.println("(6) Sale Revenue Report");
            System.out.println("(7) Exit");
            System.out.printf("Select a choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    manageOrder.createNewOrder();
                    break;
                case 2:
                    manageOrder.editOrder();
                    break;
                case 3:
                    manageOrder.viewAllOrders();
                    break;
                case 4:
                    manageOrder.printInvoice();
                    break;
                case 5:
                    manageOrder.removeOrder();
                    break;
                case 6:
                    manageOrder.printRevenueReport();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (choice != 7);
    }
}
