package UI;

import java.util.Scanner;
import Manager.*;

/**
 * This is a UI class used to display reservation options
 * 
 * @author Timothy, Malcom
 * @version 1.0
 * @since 2021-11-11
 */
public class manageReservationUI {

    /**
     * Starts the UI for user to select reservation operations. After selection of
     * reservation operation, appropriate methods from manageReservation is called.
     * 
     * @param tbManager     A manageTable object to perform management logic of
     *                      tables.
     * @param memberManager A manageMember object to perform management logic of
     *                      members.
     * @param res           A manageReservation object to perform management logic
     *                      of reservations.
     */
    public static void start(manageTable tbManager, manageMember memberManager, manageReservation res) {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("(1) Create Reservation Booking ");
            System.out.println("(2) List all Reservation Booking ");
            System.out.println("(3) Remove Reservation Booking ");
            System.out.println("(5) Exit");
            System.out.printf("Select a choice: ");

            while (!scan.hasNextInt()) {
                System.out.println("Please enter a valid option.");
                System.out.printf("Select a choice: ");
                scan.next();
            }

            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
            case 1:
                res.createReservation(tbManager, memberManager);
                break;
            case 2:
                res.checkReservation();
                break;
            case 3:
                res.removeReservation();
                break;
            case 5:
                break;
            default:
                System.out.println("Please enter a valid option");
            }

        } while (choice != 5);
    }
}
