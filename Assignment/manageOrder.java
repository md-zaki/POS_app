import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
public class manageOrder {
    private static ArrayList<order> allOrders = new ArrayList<order>(); 

    public manageOrder()
    {
        allOrders = new ArrayList<order>();
    }

    public static void createNewOrder() throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int ordId = allOrders.size()+1;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        staff prepby = addStaff();
        order newOrder = new order(ordId, date, time,prepby);
        newOrder.addOrderItem();
        newOrder.viewOrder();
        allOrders.add(newOrder);
        System.out.println("Order successfully created");
        System.out.println();
        
    }

    public static void editOrder() throws Exception
    {
        Scanner scan = new Scanner(System.in);
        viewAllOrders();
        if(allOrders.size()!=0)
        {
        System.out.println("Enter order id to edit: ");
		int pick = scan.nextInt();
		String dummy = scan.nextLine();
        order toEdit = allOrders.get(pick-1);
        do{
        System.out.println("(1) Add items into order");
		System.out.println("(2) Remove items into order");
        System.out.println("(3) Exit");
        pick = scan.nextInt();
		dummy = scan.nextLine();
        switch (pick) 
			{
			    case 1: 
					toEdit.addOrderItem();
				    break;
			    case 2:
					toEdit.removeOrderItem();
					break;
			    case 3:
				    break;
			    default:
				System.out.println("Please enter a valid option");
			}
        }while (pick !=3);
        }
    }

    public static staff addStaff() throws Exception
    {
        int i=1;
        Scanner scan = new Scanner(System.in);
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
        return prepby;
    }

    public static void viewAllOrders()
    {
        if(allOrders.size()==0) 
        {
            System.out.println();
            System.out.println("NO ORDERS AVAILABLE");
            System.out.println();
        }
        for(order order : allOrders)
        {
            order.viewOrder();
        }
    }

    public void startOrder() throws Exception
    {
        Scanner scan = new Scanner(System.in);
		int choice;
        do{
        System.out.println("\n(1) Create new order");
		System.out.println("(2) Edit Existing order");
		System.out.println("(3) View all orders");
		System.out.println("(4) Print invoice");
		System.out.println("(5) Exit");
		System.out.printf("Select a choice: ");
        choice = scan.nextInt();
			scan.nextLine();
			switch (choice) 
			{
			    case 1:
                createNewOrder();
                    break;
			    case 2:
                editOrder();
					break;
			    case 3:
                viewAllOrders();
				    break;
			    case 4:
                printInvoice();
					break;
			    case 5:
				break;
			    default:
				System.out.println("Please enter a valid option");
			}
		} while (choice != 5);
    }

    public static void printInvoice()
    {
        int i=1;
        double total=0;
        double discount=1;
        Scanner scan = new Scanner(System.in);
        viewAllOrders();
        System.out.println("For which order would you like to print the invoice?: ");
        int index = scan.nextInt();
        order toPrint = allOrders.get(index-1);
        System.out.println("Is the customer a member? (y/n): ");
        char ans = scan.nextLine().charAt(0);
        if(ans == 'y')
        {
            System.out.println("Key in customer's member ID: ");
            long id = scan.nextLong();
            discount = discount(id);
        }
        System.out.println("Order ID: " + toPrint.getOrderId());
		System.out.println("Date Ordered: " + toPrint.getDate().getDayOfMonth() +"/" + toPrint.getDate().getMonthValue() + "/" + toPrint.getDate().getYear());
		System.out.println("Time Ordered: " + toPrint.getTime().getHour() +":" + toPrint.getTime().getMinute());
		System.out.println("Prepared by: " + toPrint.getStaff().getName());
		System.out.println("Ordered Items: ");
		for(menuItems item : toPrint.getOrderItems())
		{
			System.out.println("	(" + i + ") " + item.getName() + item.getPrice());
            total = total + item.getPrice();
			i++;
		}
        System.out.println("Subtotal: $ " + total);
        if(discount!=1)
        {
            System.out.println("Applicable Discount: " + (1-discount)*100 + "%");
            System.out.println("Discount: - $" + (total*(1-discount)));
            total=total*discount;
        }

        System.out.println("Applicable GST: 7%");
        System.out.println("Total after GST: + $" + (total*0.07));
        total = total+(total*0.07);
        System.out.println("TOTAL: " + total);
    }

    public static double discount(long memberId)
    {
        int check = manageMember.getMemberListIndexById(memberId);
        if(check == -1)
        {
            System.out.println("Invalid member Id");
        }
        else
        {
            member.tier tier = manageMember.getMemberList().get(check).getTier();
            switch(tier)
            {
                case Gold:
                    return 0.85;
                case Silver:
                    return 0.9;
                case Bronze:
                    return 0.95;
            }
            
        }
        return 1;
    }
}
