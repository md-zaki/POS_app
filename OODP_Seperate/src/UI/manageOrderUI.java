package UI;
import java.util.Scanner;

import Manager.manageOrder;
public class manageOrderUI {
    public static void start(manageOrder manageOrder) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n(1) Create new order");
            System.out.println("(2) Edit Existing order");
            System.out.println("(3) View all orders");
            System.out.println("(4) Print invoice");
            System.out.println("(5) Exit");
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
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (choice != 5);
    }
}
