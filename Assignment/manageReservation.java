import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class manageReservation implements Serializable {
    private ArrayList<reservation> reservations = new ArrayList<reservation>();
    private static ArrayList<Table> tables = new ArrayList<Table>();
    private static ArrayList<member> members = new ArrayList<member>();;

    public ArrayList<reservation> getReservations() {
        return this.reservations;
    }

    public void start() throws IOException, ClassNotFoundException {
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

    private void createReservation() {
        tables.add(new Table(1, 2));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 4));
        tables.add(new Table(4, 4));
        tables.add(new Table(5, 8));
        tables.add(new Table(6, 6));

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

        while (true) {
            try {
                System.out.print("Enter your contact: ");
                contact = scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid contact");
            }
        }

        while (true) {
            try {
                System.out.print("Enter number of customers reserving table: ");
                noOfPax = scan.nextInt();
                scan.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }

        while (true) {
            System.out.print("Are you a member? (Y/N): ");
            temp = scan.nextLine();
            if (temp.equals("Y") || temp.equals("y")) {
                member = true;
                break;
            } else if (temp.equals("N") || temp.equals("n")) {
                member = false;
                break;
            } else {
                System.out.println("Please select only (Y/N)");
            }
        }

        // System.out.print("Enter Date (dd.MMM.yyyy e.g 12.Dec.2021): ");
        // temp = scan.nextLine();

        while (true) {
            try {
                System.out.print("Enter Date (dd.MMM.yyyy e.g 12.Dec.2021): ");
                temp = scan.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
                date = LocalDate.parse(temp, dtf);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date!");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Time(HH. mm): ");
                temp = scan.nextLine();
                DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH.mm");
                time = LocalTime.parse(temp, dtf1);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date!");
            }
        }

        if (member == true) {

        }
        customer c = new customer(name, contact);
        // customer c = new member(name, contact, 23, 1);
        Table t = null;
        if (noOfPax % 2 == 0) {
            for (int i = 0; i < tables.size(); i++) {
                if (tables.get(i).getTableSize() >= noOfPax) {
                    t = tables.get(i);
                    tables.get(i).setIsAvailable(false);
                    break;
                }
            }
        } else {
            for (int i = 0; i < tables.size(); i++) {
                if (tables.get(i).getTableSize() >= noOfPax + 1) {
                    t = tables.get(i);
                    tables.get(i).setIsAvailable(false);
                    break;
                }
            }
        }
        if (Objects.isNull(t)) {
            System.out.println("No available tables");
        } else {
            reservations.add(new reservation(date, time, c, t, noOfPax));
            saveReservation();
        }
        System.out.println(date);
        System.out.println(time);

        System.out.println("table assigned is " + t.getTableNo() + " " + t.getTableSize() + " " + t.getIsAvailable());
        for (int i = 0; i < tables.size(); i++) {
            System.out.println(
                    "" + tables.get(i).getTableNo() + tables.get(i).getTableSize() + tables.get(i).getIsAvailable());
        }

    }

    public manageReservation readReservation() throws ClassNotFoundException, IOException {

        FileInputStream fileInputStream = new FileInputStream("reservations.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageReservation reservations = (manageReservation) objectInputStream.readObject();
        objectInputStream.close();
        return reservations;

    }

    public void saveReservation() {
        try {
            FileOutputStream fos = new FileOutputStream("reservations.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
