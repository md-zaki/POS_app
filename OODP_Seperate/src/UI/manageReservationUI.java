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
            System.out.println("(2) Check/Remove Reservation Booking ");
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
                String isDelete;
                int rID;
                res.checkReservation();
                while (true) {
                    System.out.println("Do you want to delete reservation? (Y/N)");
                    isDelete = scan.nextLine();
                    if (isDelete.equals("Y") || isDelete.equals("y")) {
                        while (true) {
                            try {
                                System.out.print("Enter reservation ID: ");
                                rID = scan.nextInt();
                                if (rID < 0 || rID >= res.getReservations().size()) {
                                    System.out.println("Invalid reservation ID");
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid input");
                                scan.next();
                            }
                        }
                        res.removeReservation(rID);
                        break;
                    } else if (isDelete.equals("N") || isDelete.equals("n")) {
                        break;
                    } else {
                        System.out.println("Please select only (Y/N)");
                    }
                }
                break;
            case 5:
                break;
            default:
                System.out.println("Please enter a valid option");
            }

        } while (choice != 5);
    }
}
