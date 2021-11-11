package UI;
import java.util.Scanner;

import Manager.manageStaff;
public class manageStaffUI {
    public static void start(manageStaff staff)throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do
        {
            System.out.println("(1) View All Staffs");
            System.out.println("(2) View Staff by ID");
            System.out.println("(3) Add Staff");
            System.out.println("(4) Update Staff Info");
            System.out.println("(6) Exit");
            System.out.printf("Select a choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice)
            {
                case 1:
                    staff.viewStaffList();
                    break;
                case 2:
                    System.out.printf("Enter Staff ID: ");
                    int staffId = scan.nextInt();
                    scan.nextLine();
                    staff.viewStaffById(staffId);
                    break;
                case 3:
                    staff.addStaff();
                    break;
                case 4:
                    System.out.printf("Enter Staff ID: ");
                    staffId = scan.nextInt();
                    scan.nextLine();
                    staff.updateStaffInfo(staffId);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (choice != 6);
    }
}
