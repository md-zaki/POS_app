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
    public manageMember()
    {
        memberList = new ArrayList<customer>();
    }

    public void addMember()
    {
        int i=1;
        Scanner scan = new Scanner(System.in);
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

        
    }
}
