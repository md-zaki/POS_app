package Manager;

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
import Entity.reservation;
import Entity.customer;
import Entity.Table;
import Entity.member;

/**
 * A manager class used for performing management logic for reservations.
 * 
 * @author Timothy, Malcom
 * @version 1.0
 * @since 2021-11-11
 */
public class manageReservation implements Serializable {

    private static final long serialVersionUID = 12345L;
    /**
     * Array list of reservations to keep track of existing reservations.
     */
    private ArrayList<reservation> reservations = new ArrayList<reservation>();

    /**
     * Gets the array of exisintg reservations
     * 
     * @return the array of existing reservations
     */
    public ArrayList<reservation> getReservations() {
        return this.reservations;
    }

    /**
     * Removes reservations if current date-time is two hours more than the
     * date-time of the reservation.
     */
    public void removeExpired() {
        Iterator<reservation> iter = reservations.iterator();
        LocalDateTime reservDT;
        while (iter.hasNext()) {
            reservation r = iter.next();
            reservDT = LocalDateTime.of(r.getDate(), r.getTime());
            if (reservDT.plusMinutes(60).compareTo(LocalDateTime.now()) < 0) {
                iter.remove();
            }
        }
        saveReservation();
    }

    /**
     * Remove reservation based on the customer's name, date and time.
     */
    public void removeReservation(int rID) {
        reservations.remove(rID);
        saveReservation();
    }

    /**
     * Prints all existing reservations.
     */
    public void checkReservation() {
        removeExpired();
        System.out.println("================ RESERVATIONS ====================");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println("Reservation ID: " + i);
            System.out.println("Customer name: " + reservations.get(i).getCust().getName());
            System.out.println("Customer contact: " + reservations.get(i).getCust().getContact());
            System.out.println("Table number: " + reservations.get(i).getTable().getTableNo());
            System.out.println("Table size: " + reservations.get(i).getTable().getTableSize());
            System.out.println("Number of pax: " + reservations.get(i).getNumOfpax());
            System.out.println("Date: " + reservations.get(i).getDate());
            System.out.println("Time: " + reservations.get(i).getTime());
            System.out.println("--------------------------");
        }
    }

    /**
     * Creates reservation based on user input which are name, date, time, contact,
     * number of pax, member status.
     * 
     * @param tbManager     A manageTable object to perform management logic of
     *                      tables.
     * @param memberManager A manageMember object to perform management logic of
     *                      members.
     */
    public void createReservation(manageTable tbManager, manageMember memberManager) {
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
                scan.next();
            }
        }

        while (true) {
            try {
                System.out.print("Enter number of customers reserving table: ");
                noOfPax = scan.nextInt();
                scan.nextLine();
                if (noOfPax <= 0 || noOfPax > 10) {
                    System.out.println("invalid noOfPax!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("invalid input");
                scan.next();
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
                if (date.compareTo(LocalDate.now()) <= 0) {
                    System.out.println("Please make reservations one day in advance!");
                    continue;
                }
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

        Table t = null;
        int tempPax = noOfPax;
        ArrayList<Integer> occupiedTables = new ArrayList<Integer>();
        LocalDateTime reservDateTime1;
        LocalDateTime reservDateTime2;
        for (reservation r : reservations) {
            reservDateTime1 = LocalDateTime.of(r.getDate(), r.getTime());
            reservDateTime2 = LocalDateTime.of(date, time);
            if ((reservDateTime1.isBefore(reservDateTime2) && reservDateTime1.plusMinutes(120).isAfter(reservDateTime2))
                    || (reservDateTime2.isBefore(reservDateTime1)
                            && reservDateTime2.plusMinutes(120).isAfter(reservDateTime1))
                    || reservDateTime1.compareTo(reservDateTime2) == 0) {

                occupiedTables.add(r.getTable().getTableNo());
            }
        }

        if (noOfPax % 2 == 1) {
            tempPax = noOfPax + 1;
        }
        for (Table table : tbManager.getTableList()) {
            if (table.getTableSize() >= tempPax && !occupiedTables.contains(table.getTableNo())) {
                if (Objects.isNull(t)) {
                    t = table;
                } else if (t.getTableSize() > table.getTableSize()) {
                    t = table;
                }
            }
        }

        if (Objects.isNull(t) || Objects.isNull(c)) {
            if (Objects.isNull(t))
                System.out.println("not enough tables!");
            if (Objects.isNull(c))
                System.out.println("Member not found!");
            System.out.println("Error, Reservation not added!");
        } else {
            reservations.add(new reservation(date, time, c, t, noOfPax));
            saveReservation();
            System.out.println("Reservation added!");
        }
    }

    /**
     * Reads from "reservations.txt" text file and returns a manageReservation
     * object.
     * 
     * @return a manageReservation object that contains the array list of all
     *         existing reservations.
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public manageReservation readReservation() throws ClassNotFoundException, IOException {

        FileInputStream fileInputStream = new FileInputStream("reservations.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageReservation reservations = (manageReservation) objectInputStream.readObject();
        objectInputStream.close();
        return reservations;

    }

    /**
     * Writes this manageReservation object into "reservations.txt" text file.
     */
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