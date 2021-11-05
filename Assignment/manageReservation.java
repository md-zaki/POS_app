import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class manageReservation {

    public static void start() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("(1) Create Reservation Booking ");
            System.out.println("(2) Check/Remove Reservation Booking ");
            System.out.println("(3) Check Table Availability");
            System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
            case 1:
                createReservation();
                break;
            case 2:
                checkRemoveReservation();
                break;
            case 3:
                checkTable();
                break;
            case 6:
                break;
            default:
                System.out.println("Please enter a valid option");
            }

        } while (choice != 6);
    }

    private void checkTable() {
    }

    private void checkRemoveReservation() {
    }

    private void createReservation() {

    }

}
