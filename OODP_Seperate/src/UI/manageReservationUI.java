package UI;
import java.util.Scanner;

import Manager.manageReservation;
import Manager.manageTable;
import Manager.*;
public class manageReservationUI {
    public static void start(manageTable tbManager, manageMember memberManager, manageReservation res)
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("(1) Create Reservation Booking ");
            System.out.println("(2) List all Reservation Booking ");
            System.out.println("(3) Remove Reservation Booking ");
            System.out.println("(4) Remove expired reservations ");
            System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");
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
            case 4:
                res.removeExpired();
                break;
            case 6:
                break;
            default:
                System.out.println("Please enter a valid option");
            }

        } while (choice != 6);
    }
}
