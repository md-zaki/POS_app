import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
public class manageOrder {
    private static ArrayList<order> allOrders; 

    public manageOrder()
    {
        allOrders = new ArrayList<order>();
    }

    public static void createOrder() throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int i=1;
        int ordId = allOrders.size()+1;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        manageStaff staffdb = new manageStaff();
		staffdb = staffdb.readStaff();
        ArrayList<staff> staffList = staffdb.getStaffList();
        System.out.println("Which staff prepared the order?");
        for(staff staffId : staffList)
			{
				System.out.println("(" + i + ") " + staffId.getName());
				i++;
			}
        System.out.println("Enter Choice: ");
		int pick = scan.nextInt();
		String dummy = scan.nextLine();
		System.out.println("========");
        staff prepby = (staff) staffList.get(pick-1);
        order newOrder = new order(ordId, date, time,prepby);
        newOrder.addOrderItem();
        newOrder.viewOrder();
        allOrders.add(newOrder);

        
    }
}
