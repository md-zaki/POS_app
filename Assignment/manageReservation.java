import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
                // checkRemoveReservation();
                break;
            case 3:
                // checkTable();
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

    private static void createReservation() {
        Scanner scan = new Scanner(System.in);
        String name;
        LocalDate date;
        LocalTime time;
        int contact;
        int noOfPax;
        String temp;
        boolean member;

        System.out.print("Enter your name: ");
        name = scan.nextLine();

        System.out.print("Enter your contact: ");
        contact = scan.nextInt();

        System.out.print("Enter number of customers reserving table: ");
        noOfPax = scan.nextInt();

        System.out.print("Are you a member? (Y/N): ");
        temp = scan.nextLine();
        if (temp == "Y" || temp == "y") {
            member = true;
        } else if (temp == "N" || temp == "n") {
            member = false;
        }

        System.out.print("Enter Date (dd. MMM. yyyy): ");
        temp = scan.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        date = LocalDate.parse(temp, dtf);

        System.out.print("Enter Time(HH. mm): ");
        temp = scan.nextLine();
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH.mm");
        time = LocalTime.parse(temp, dtf1);

        System.out.println(date);
        System.out.print(time);
    }

}
