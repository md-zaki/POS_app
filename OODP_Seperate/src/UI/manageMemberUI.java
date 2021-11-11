package UI;
import java.util.Scanner;

import Manager.manageMember;
public class manageMemberUI {
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
