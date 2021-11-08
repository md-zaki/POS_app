import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class manageReservation implements Serializable {
    private ArrayList<reservation> reservations = new ArrayList<reservation>();

    public ArrayList<reservation> getReservations() {
        return this.reservations;
    }

    public void start(manageTable tbManager, manageMember memberManager) throws IOException, ClassNotFoundException {
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
                createReservation(tbManager, memberManager);
                break;
            case 2:
                checkReservation();
                break;
            case 3:
                removeReservation();
                break;
            case 4:
                removeExpired();
                break;
            case 6:
                break;
            default:
                System.out.println("Please enter a valid option");
            }

        } while (choice != 6);
    }

    private void removeExpired() {
        Iterator<reservation> iter = reservations.iterator();
        while (iter.hasNext()) {
            reservation r = iter.next();
            if (r.getDate().compareTo(LocalDate.now()) < 0) {
                iter.remove();

            } else if (r.getDate().compareTo(LocalDate.now()) == 0
                    && r.getTime().plusMinutes(15).compareTo(LocalTime.now()) < 0) {
                iter.remove();

            }
        }
        saveReservation();
    }

    private void removeReservation() {
        String name;
        String temp;
        LocalDate date;
        LocalTime time;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name: ");
        name = scan.nextLine();

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
                System.out.print("Enter Time(HH.mm): ");
                temp = scan.nextLine();
                DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH.mm");
                time = LocalTime.parse(temp, dtf1);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date!");
            }
        }

        for (reservation r : reservations) {
            if (r.getCust().getName().equals(name) && r.getDate().compareTo(date) == 0
                    && r.getTime().compareTo(time) == 0) {
                reservations.remove(r);
                break;
            }
        }
        saveReservation();
    }

    private void checkReservation() {
        System.out.println("================ RESERVATIONS ====================");
        for (reservation r : reservations) {
            System.out.println("Customer name: " + r.getCust().getName());
            System.out.println("Customer contact: " + r.getCust().getContact());
            System.out.println("Table number: " + r.getTable().getTableNo());
            System.out.println("Table size: " + r.getTable().getTableSize());
            System.out.println("Number of pax: " + r.getNumOfpax());
            System.out.println("Date: " + r.getDate());
            System.out.println("Time: " + r.getTime());
            System.out.println("--------------------------");
        }
    }

    private void createReservation(manageTable tbManager, manageMember memberManager) {
        // for (Table table : tbManager.getTableList()) {
        // table.setIsAvailable(true);
        // }

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
                System.out.print("Enter Time(HH.mm): ");
                temp = scan.nextLine();
                DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH.mm");
                time = LocalTime.parse(temp, dtf1);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date!");
            }
        }

        customer c = null;
        if (member == true) {
            for (member m : memberManager.getMemberList()) {
                if (m.getName().equals(name) && m.getContact() == contact) {
                    c = m;
                    break;
                }
            }
        } else {
            c = new customer(name, contact);
        }

        /*
         * Table t = null; if (noOfPax % 2 == 0) { for (Table table :
         * tbManager.getTableList()) { if (table.getTableSize() >= noOfPax &&
         * table.getIsAvailable() == true) { t = table; table.setIsAvailable(false);
         * break; } } } else { for (Table table : tbManager.getTableList()) { if
         * (table.getTableSize() >= noOfPax + 1 && table.getIsAvailable() == true) { t =
         * table; table.setIsAvailable(false); break; } } }
         */

        Table t = null;
        Boolean isReserved = true;
        int tempPax = noOfPax;
        ArrayList<Integer> occupiedTables = new ArrayList<Integer>();
        LocalDateTime reservDateTime1;
        LocalDateTime reservDateTime2;
        for (reservation r : reservations) {
            if (LocalDate.now().compareTo(date) > 0
                    || (LocalDate.now().compareTo(date) == 0 && LocalTime.now().compareTo(time) >= 0)) {
                System.out.println("Please make reservations in advance!");
                isReserved = false;
                break;
            } else if (r.getDate().compareTo(date) == 0) {
                reservDateTime1 = LocalDateTime.of(r.getDate(), r.getTime());
                reservDateTime2 = LocalDateTime.of(date, time);
                if ((reservDateTime1.isBefore(reservDateTime2)
                        && reservDateTime1.plusMinutes(120).isAfter(reservDateTime2.plusMinutes(120)))
                        || (reservDateTime2.isBefore(reservDateTime1)
                                && reservDateTime2.plusMinutes(120).isAfter(reservDateTime1.plusMinutes(120)))) {
                    System.out.println("Timing not available!");
                    isReserved = false;
                    break;
                } else {
                    occupiedTables.add(r.getTable().getTableNo());
                }

                /*
                 * if ((r.getTime().isBefore(time) &&
                 * r.getTime().plusMinutes(120).isAfter(time.plusMinutes(120))) ||
                 * (time.isBefore(r.getTime()) &&
                 * time.plusMinutes(120).isAfter(r.getTime().plusMinutes(120)))) {
                 * System.out.println("Timing not available!"); isReserved = false; break; }
                 * else { occupiedTables.add(r.getTable().getTableNo()); }
                 */
            }
        }
        if (isReserved) {
            if (noOfPax % 2 == 1) {
                tempPax = noOfPax + 1;
            }
            for (Table table : tbManager.getTableList()) {
                if (table.getTableSize() >= tempPax && !occupiedTables.contains(table.getTableNo())) {
                    t = table;
                    break;
                }
            }
        }

        if (Objects.isNull(t) || Objects.isNull(c)) {
            System.out.println("Error, Reservation not added!");
        } else {
            reservations.add(new reservation(date, time, c, t, noOfPax));
            saveReservation();
            try {
                tbManager.saveTables();
            } catch (Exception e) {
                e.getStackTrace();
            }
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
