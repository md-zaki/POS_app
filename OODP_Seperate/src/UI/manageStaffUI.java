package UI;
import java.util.Scanner;
import Manager.manageStaff;

/**
 * This is a UI class used to display staff options
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class manageStaffUI {

    /**
     * Starts the UI for the user to select the staff operations.
     * After selection of the desired operation, appropriate methods from the manageStaff will be called.
     * @param staff staff object which consist of all logic functions related to staff
     * @throws Exception
     */
    public static void start(manageStaff staff)throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do
        {
            System.out.println("\nPlease select your operations");
            System.out.println("(1) View All Staffs");
            System.out.println("(2) View Staff by ID");
            System.out.println("(3) Add Staff");
            System.out.println("(4) Update Staff Info");
            System.out.println("(5) Remove Staff");
            System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");

            while (!scan.hasNextInt())
            {
                System.out.println("Please enter a valid option.");
                System.out.printf("Select a choice: ");
                scan.next();
            }

            choice = scan.nextInt();
            scan.nextLine();
            switch (choice)
            {
                case 1:
                    staff.viewStaffList();
                    break;
                case 2:
                    System.out.printf("Enter Staff ID: ");

                    while (!scan.hasNextInt())
                    {
                        System.out.println("Please input an integer.");
                        System.out.printf("Enter Staff ID: ");
                        scan.next();
                    }

                    int staffId = scan.nextInt();
                    scan.nextLine();
                    staff.viewStaffById(staffId);
                    break;
                case 3:
                    staff.addStaff();
                    break;
                case 4:
                    System.out.printf("Enter Staff ID: ");

                    while (!scan.hasNextInt())
                    {
                        System.out.println("Please input an integer.");
                        System.out.printf("Enter Staff ID: ");
                        scan.next();
                    }

                    staffId = scan.nextInt();
                    scan.nextLine();
                    staff.updateStaffInfo(staffId);
                    break;
                case 5:
                    staff.removeStaff();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (choice != 6);
    }
}
