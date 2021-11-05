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
    private static ArrayList<customer> memberList = new ArrayList<customer>();
    Scanner scan = new Scanner(System.in);

    public manageMember()
    {
        memberList = new ArrayList<customer>();
    }

    public void addMember()
    {
        int i=1;
        System.out.println("Enter customer id: ");
        long memberId = scan.nextLong();
        scan.nextLine();
        System.out.println("Enter name of customer: ");
        String name = scan.nextLine();
        System.out.println("Enter contact number: ");
        long contact = scan.nextLong();
        System.out.println("Enter type of menu item:");
        for(customer.tier tier : customer.tier.values())
        {
            System.out.println(i + ": " + tier);
            i++;
        }
        int tiernum = scan.nextInt();
<<<<<<< Updated upstream
        customer.tier tier = null;
        do{
        switch (tiernum) 
			{
			    case 1: 
                    tier = customer.tier.Gold;
				    break;
			    case 2:
                    tier = customer.tier.Silver;
					break;
			    case 3:
                    tier = customer.tier.Bronze;
				    break;
                case 4:
			    default:
				System.out.println("Please enter a valid option");
			}
        }while (tiernum !=4);
        customer newMember = new customer(memberId, name, contact, tier);
        memberList.add(newMember);
        
=======
    }

    public void viewListOfMembers()
    {
        System.out.println("=============== MEMBER LIST ===============\n");
        for (int i = 0 ; i < memberList.size(); i++)
        {
            System.out.println("Customer ID: " + memberList.get(i).getMemberId());
            System.out.println("Customer Name: " + memberList.get(i).getName());
            System.out.println("Customer contact: " + memberList.get(i).getContact());
            System.out.println("Customer membership tier: " + memberList.get(i).getTier());
            System.out.println();
        }
        System.out.println("=============== END OF LIST ===============\n");
>>>>>>> Stashed changes
    }
}
