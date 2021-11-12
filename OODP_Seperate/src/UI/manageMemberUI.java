package UI;
import java.util.Scanner;
import Manager.manageMember;

/**
 * This is a UI class used to display membership options.
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class manageMemberUI {
    /**
     * Starts the UI for the user to select the member operations.
     * After selection of the desired operation, appropriate methods from the manageMember will be called. 
     * @param manageMember manageMember object which consist of all logic functions related to member
     * @throws Exception
     */
    public static void start(manageMember manageMember) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Select your operations");
            System.out.println("(1) View all members");
            System.out.println("(2) View member by ID");
            System.out.println("(3) Add new member");
            System.out.println("(4) Update member info");
            System.out.println("(5) Remove member");
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
            switch (choice) {
                case 1:
                    manageMember.viewListOfMembers();
                    break;
                case 2:
                    System.out.printf("Enter member id: ");
                    long viewMemberId = scan.nextLong();
                    scan.nextLine();
                    manageMember.viewIndividualMember(viewMemberId);
                    break;
                case 3:
                    manageMember.addMember();
                    break;
                case 4:
                    System.out.printf("Enter member id to update: ");
                    long memberUpdate = scan.nextLong();
                    scan.nextLine();
                    manageMember.updateMember(memberUpdate);
                    break;
                case 5:
                    manageMember.removeMember();
                    break;
            }

        } while (choice != 6);
    }
}
