package Manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import Entity.member;

/**
 * Manager Class for Members.
 * Members are customers that holds a membership of the restaurant.
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class manageMember implements Serializable {

    /**
     * UID for Serializable
     */
    private static final long serialVersionUID = 12345L;

    /**
     * Initialize memberList array.
     * This array stores a list of customers who are members of the restaurant, each index is an member object
     */
    private ArrayList<member> memberList = new ArrayList<member>();

    // Constructor class not needed?
    //public manageMember() {
    //    memberList = new ArrayList<member>();
    //}

    /**
     * Gets the membership list, customers who holds a membership of the restaurant
     * @return memberList, array list of member
     */
    public ArrayList<member> getMemberList() {
        return this.memberList;
    }

    /**
     * Function to save the memberList to a txt file
     * @throws IOException for when the txt file does not exist
     */
    public void saveMemberList() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("testMemberSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * Function to populate the memberList by reading the data from the saved txt file
     * @return array list of members
     * @throws ClassNotFoundException
     * @throws IOException for when the txt file does not exist
     */
    public manageMember readMemberList() throws ClassNotFoundException, IOException {

        FileInputStream fileInputStream = new FileInputStream("testMemberSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageMember testMembers = (manageMember) objectInputStream.readObject();
        objectInputStream.close();
        return testMembers;

    }

    /**
     * Adds a customer to the membership list, then calls saveMemberList() to save the updated list.
     * @throws IOException
     */
    public void addMember() throws IOException {
        Scanner scan = new Scanner(System.in);
        int i = 1;
        System.out.printf("Enter customer id: ");

        while (!scan.hasNextLong())
        {
            System.out.println("Please enter a valid integer.");
            System.out.printf("Enter customer id: ");
            scan.next();
        }

        long memberId = scan.nextLong();
        scan.nextLine();
        System.out.printf("Enter name of customer: ");
        String name = scan.nextLine().toUpperCase();
        System.out.printf("Enter contact number: ");

        while (!scan.hasNextInt())
        {
            System.out.println("Please enter a valid integer.");
            System.out.printf("Enter contact number: ");
            scan.next();
        }

        long contact = scan.nextLong();
        System.out.println("Enter membership tier");
        for (member.tier tier : member.tier.values()) {
            System.out.println(i + ": " + tier);
            i++;
        }
        int tiernum = 0;
        member.tier tier = null;
        do {
            System.out.printf("Select a tier: ");

            while (!scan.hasNextInt())
            {
                System.out.println("Please enter a valid integer.");
                System.out.printf("Select a tier: ");
                scan.next();
            }

            tiernum = scan.nextInt();
            scan.nextLine();
            switch (tiernum) {
                case 1:
                    tier = member.tier.Gold;
                    break;
                case 2:
                    tier = member.tier.Silver;
                    break;
                case 3:
                    tier = member.tier.Bronze;
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (tiernum != 1 && tiernum != 2 && tiernum != 3);
        member newMember = new member(name, contact, memberId, tier);
        memberList.add(newMember);
        saveMemberList();
    }

    /**
     * Print function to display all members
     */
    public void viewListOfMembers() {
        System.out.println("=============== MEMBER LIST ===============");
        for (int i = 0; i < memberList.size(); i++) {
            printMember(i);
        }
        System.out.println("=============== END OF LIST ===============\n");
    }

    /**
     * Print function to print out the details of a single member object
     * @param i index of the member in the memberList array
     */
    public void printMember(int i) {
        System.out.println();
        System.out.println("Member ID: " + memberList.get(i).getMemberId());
        System.out.println("Customer Name: " + memberList.get(i).getName());
        System.out.println("Customer contact: " + memberList.get(i).getContact());
        System.out.println("Customer membership tier: " + memberList.get(i).getTier());
        System.out.println();
    }

    /**
     * Function to retrieve the index of a desired member object given the memberId
     * @param memberId attribute memberId, unique identifier of the member object
     * @return integer index of the member object if found, else return -1
     */
    public int getMemberListIndexById(long memberId) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberId() == memberId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Display the information of an individual member given the memberId
     * @param memberId attribute memberId, unique identifier of the member object
     */
    public void viewIndividualMember(long memberId) {
        int check = getMemberListIndexById(memberId);
        if (check == -1) {
            System.out.println("Member ID does not exist");
        } else {
            System.out.println();
            printMember(check);
            System.out.println();
        }
    }

    /**
     * Remove a member from the memberList, they no longer holds a membership of the restaurant.
     * Updates the member txt file after deletion.
     * @throws IOException
     */
    public void removeMember() throws IOException {
        Scanner scan = new Scanner(System.in);
        int i = 0;
        int check = 0;
        do {
            System.out.println("Enter member ID to be deleted: ");

            while (!scan.hasNextInt())
            {
                System.out.println("Please enter a valid integer.");
                System.out.printf("Enter member ID to be deleted: ");
                scan.next();
            }

            long id = scan.nextLong();
            check = getMemberListIndexById(id);
            if (check == -1) {
                System.out.println("Invalid member id");
            } else {
                memberList.remove(check);
                saveMemberList();
                i = 1;
            }
        } while (i != 1);
    }

    /**
     * Updates the information of a member given the memberId.
     * Able to choose which attributes out of all existing attributes of member to edit
     * After editing any information, the saveMemberList() function will be triggered to save the memberList array into the txt file
     * @param memberId attribute memberId
     * @throws IOException
     */
    public void updateMember(long memberId) throws IOException {
        Scanner scan = new Scanner(System.in);
        int choice;
        int memberIndex = getMemberListIndexById(memberId);
        if (memberIndex == -1) {
            System.out.println("Member does not exist");
        } else {
            do {
                member updateMember = new member(memberList.get(memberIndex).getName(),
                        memberList.get(memberIndex).getContact(), memberList.get(memberIndex).getMemberId(),
                        memberList.get(memberIndex).getTier());
                System.out.println("Editing Member " + memberList.get(memberIndex).getName() + " Information");
                System.out.println("Please select your operations");
                System.out.println("(1) Edit Member ID");
                System.out.println("(2) Edit Member Name");
                System.out.println("(3) Edit Member Contact");
                System.out.println("(4) Edit Member Tier");
                System.out.println("(5) View Current Member Info");
                System.out.println("(6) Exit");
                System.out.printf("Enter a choice: ");

                while (!scan.hasNextInt())
                {
                    System.out.println("Please enter a valid integer.");
                    System.out.printf("Enter a choice: ");
                    scan.next();
                }

                choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        System.out.printf("Enter updated member name: ");
                        String updateName = scan.nextLine().toUpperCase();
                        updateMember = new member(updateName, memberList.get(memberIndex).getContact(),
                                memberList.get(memberIndex).getMemberId(), memberList.get(memberIndex).getTier());
                        memberList.set(memberIndex, updateMember);
                        break;
                    case 3:
                        System.out.printf("Enter updated member contact: ");
                        while (!scan.hasNextInt())
                        {
                            System.out.println("Please enter a valid integer.");
                            System.out.printf("Enter updated member contact: ");
                            scan.next();
                        }
                        long updateContact = scan.nextLong();
                        scan.nextLine();
                        updateMember = new member(memberList.get(memberIndex).getName(), updateContact,
                                memberList.get(memberIndex).getMemberId(), memberList.get(memberIndex).getTier());
                        memberList.set(memberIndex, updateMember);
                        break;
                    case 4:
                        int i = 1;
                        System.out.println("Enter updated member tier");
                        for (member.tier tier : member.tier.values()) {
                            System.out.println(i + ": " + tier);
                            i++;
                        }
                        int tiernum = 0;
                        member.tier tier = null;
                        do {
                            System.out.printf("Select a tier: ");
                            while (!scan.hasNextInt())
                            {
                                System.out.println("Please enter a valid integer.");
                                System.out.printf("Select a tier: ");
                                scan.next();
                            }
                            tiernum = scan.nextInt();
                            scan.nextLine();
                            switch (tiernum) {
                                case 1:
                                    tier = member.tier.Gold;
                                    break;
                                case 2:
                                    tier = member.tier.Silver;
                                    break;
                                case 3:
                                    tier = member.tier.Bronze;
                                    break;
                                default:
                                    System.out.println("Please enter a valid option");
                            }
                        } while (tiernum != 1 && tiernum != 2 && tiernum != 3);
                        updateMember = new member(memberList.get(memberIndex).getName(),
                                memberList.get(memberIndex).getContact(), memberList.get(memberIndex).getMemberId(), tier);
                        memberList.set(memberIndex, updateMember);
                        break;
                    case 5:
                        printMember(memberIndex);
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Please enter a valid choice.");
                }
            } while (choice != 6);
        }

    }
}

