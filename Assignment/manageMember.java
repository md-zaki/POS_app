import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public class manageMember {
    private static ArrayList<member> memberList = new ArrayList<member>();
    Scanner scan = new Scanner(System.in);

    public manageMember()
    {
        memberList = new ArrayList<member>();
    }

    public void addMember()
    {
        int i=1;
        System.out.printf("Enter customer id: ");
        long memberId = scan.nextLong();
        scan.nextLine();
        System.out.printf("Enter name of customer: ");
        String name = scan.nextLine();
        System.out.printf("Enter contact number: ");
        long contact = scan.nextLong();
        System.out.println("Enter membership tier");
        for(member.tier tier : member.tier.values())
        {
            System.out.println(i + ": " + tier);
            i++;
        }
        int tiernum =0;
        member.tier tier = null;
        do
        {
        System.out.printf("Select a tier: ");
        tiernum = scan.nextInt();
        scan.nextLine();
        switch (tiernum) 
			{
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
        }while (tiernum !=1 && tiernum != 2 && tiernum != 3);
        member newMember = new member(name, contact, memberId, tier);
        memberList.add(newMember);
        
    }

    public void viewListOfMembers()
    {
        System.out.println("=============== MEMBER LIST ===============\n");
        for (int i = 0 ; i < memberList.size(); i++)
        {
            System.out.println("Member ID: " + memberList.get(i).getMemberId());
            System.out.println("Customer Name: " + memberList.get(i).getName());
            System.out.println("Customer contact: " + memberList.get(i).getContact());
            System.out.println("Customer membership tier: " + memberList.get(i).getTier());
            System.out.println();
        }
        System.out.println("=============== END OF LIST ===============\n");
    }

    public long getMemberListIndexById(long memberId)
    {
        for(int i=0 ; i<memberList.size(); i++)
        {
            if (memberList.get(i).getMemberId() == memberId)
            {
                return i;
            }
        }
        return -1;
    }

    public void start()
    {
        int choice;
        do
        {
            System.out.println("Select your operations");
            System.out.println("(1) View all members");
            System.out.println("(2) View member by ID");
            System.out.println("(2) Add new member");
            System.out.println("(3) Update member info");
            System.out.println("(4) Remove member");
            System.out.println("(5) Exit");
            System.out.printf("Select a choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice)
            {
                case 1:
                    viewListOfMembers();
                    break;
                case 2:
                    break;
                case 3:
                    addMember();
                    break;
                case 4:
                    removeMember();
                    break;
            }

        } while (choice != 5);
    }

    public void removeMember()
    {
        int i=0;
        long check = 0;
        do{
        System.out.println("Enter member ID to be deleted: ");
        long id = scan.nextLong();
        check = getMemberListIndexById(id);
        if(check==-1)
        {
            System.out.println("Invalid member id");
        }
        else
        {
            memberList.remove((int)check);
            i=1;
        }
        }while(i!=1);
    }

    public void updateMember()
    {
        
    }
}
