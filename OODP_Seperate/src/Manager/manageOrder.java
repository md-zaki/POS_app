package Manager;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Entity.Table;
import Entity.order;
import Entity.staff;
import Entity.member;
import Entity.menuItems;

/**
 * Manager Class for Orders
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class manageOrder implements Serializable{
    private static final long serialVersionUID = 12345L;

    /**
     * array list of all the orders the restaurant has taken
     */
    private ArrayList<order> allOrders = new ArrayList<order>();

    /**
     * Constructor for manageOrder
     */
    public manageOrder() {
    }

    /**
     * Function to create new order and add into array list of orders
     * @throws Exception
     */
    public void createNewOrder() throws Exception {
        Scanner scan = new Scanner(System.in);
        int ordId = allOrders.size() + 1;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        staff prepby = addStaff();
        Table table = chooseTable();
        order newOrder = new order(ordId, date, time, prepby,table);
        newOrder.addOrderItem();
        newOrder.viewOrder();
        allOrders.add(newOrder);
        try {
            saveOrders();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Order successfully created");
        System.out.println();
    }

    /**
     * Function to remove any order from the all orders list
     */
    public void removeOrder() {
        Scanner scan = new Scanner(System.in);
        viewAllOrders();
        System.out.println("Enter index of order to delete:");

        while (!scan.hasNextInt())
        {
            System.out.println("Please enter a valid integer.");
            System.out.printf("Enter index of order to delete: ");
            scan.next();
        }

        int index = scan.nextInt();
        scan.nextLine();
        allOrders.remove(index-1);
    }

    /**
     * Function to edit any order in the order list
     * @throws Exception
     */
    public void editOrder() throws Exception {
        Scanner scan = new Scanner(System.in);
        viewAllOrders();
        if (allOrders.size() != 0) {
            System.out.println("Enter order id to edit: ");

            while (!scan.hasNextInt())
            {
                System.out.println("Please enter a valid integer.");
                System.out.printf("Enter order id to edit: ");
                scan.next();
            }

            int pick = scan.nextInt();
            scan.nextLine();
            order toEdit = allOrders.get(pick - 1);
            if(toEdit.getIsPaid()==true)
            {
                System.out.println("Unable to edit order. Order has already been fulfilled and paid for. ");
            }
            else
            {
                do {
                    System.out.println("(1) Add items into order");
                    System.out.println("(2) Remove items into order");
                    System.out.println("(3) Exit");
                    System.out.printf("Select a choice: ");

                    while (!scan.hasNextInt())
                    {
                        System.out.println("Please enter a valid option.");
                        System.out.printf("Select a choice: ");
                        scan.next();
                    }

                    pick = scan.nextInt();
                    scan.nextLine();
                    switch (pick) {
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
                } while (pick != 3);
            }
        }
        try {
            saveOrders();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Function to choose which staff prepared the order
     * @return staff that prepared the order
     * @throws Exception
     */
    public static staff addStaff() throws Exception {
        int i = 1;
        Scanner scan = new Scanner(System.in);
        manageStaff staffdb = new manageStaff();
        staffdb = staffdb.readStaff();
        ArrayList<staff> staffList = staffdb.getStaffList();
        System.out.println("Which staff prepared the order?");
        for (staff staffId : staffList) {
            System.out.println("(" + i + ") " + staffId.getName());
            i++;
        }
        System.out.printf("Select a choice: ");

        while (!scan.hasNextInt())
        {
            System.out.println("Please enter a valid option.");
            System.out.printf("Select a choice: ");
            scan.next();
        }

        int pick = scan.nextInt();
        scan.nextLine();
        System.out.println("========");
        staff prepby = (staff) staffList.get(pick - 1);
        return prepby;
    }

    /**
     * Function to choose the table the order was taken from
     * @return table the order was taken from
     * @throws Exception
     */
    public static Table chooseTable() throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int i=1;
        manageTable tabledb = new manageTable();

        try {
            tabledb = tabledb.readTables();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //tabledb.printTable();
        for(Table table : tabledb.getTableList())
        {
            System.out.println("(" + i + ") Table No: " + table.getTableNo() + ", Table Size: " + table.getTableSize());
            i++;
        }
        System.out.println("Select a choice: ");

        while (!scan.hasNextInt())
        {
            System.out.println("Please enter a valid option.");
            System.out.printf("Select a choice: ");
            scan.next();
        }

        int pick = scan.nextInt();
        scan.nextLine();
        Table toChoose = (Table) tabledb.getTableList().get(pick-1);
        while(toChoose.getIsAvailable()==false)
        {
            System.out.println("Table unavailable, choose another table: ");

            while (!scan.hasNextInt())
            {
                System.out.println("Please enter a valid option.");
                System.out.printf("Select a choice: ");
                scan.next();
            }

            pick = scan.nextInt();
            scan.nextLine();
            toChoose = (Table) tabledb.getTableList().get(pick-1);
        }
        tabledb.getTableList().get(pick-1).setIsAvailable(false);
        tabledb.saveTables();
        return toChoose;
    }

    /**
     * View all orders taken in the restaurant's lifetime
     */
    public void viewAllOrders() {
        System.out.println("======= ALL ORDERS =======");
        if (allOrders.size() == 0) {
            System.out.println();
            System.out.println("NO ORDERS AVAILABLE");
            System.out.println();
        }
        for (order order : allOrders) {
            order.viewOrder();
        }
        System.out.println("==========================");
    }


    /**
     * Prints out a specific order's invoice
     * Vacate the table when order invoice is printed out
     * @throws Exception
     */
    public void printInvoice() throws Exception{
        int i = 1;
        double total = 0;
        double discount = 1;
        Scanner scan = new Scanner(System.in);
        viewAllOrders();
        System.out.println("For which order would you like to print the invoice?: ");
        while (!scan.hasNextInt())
        {
            System.out.println("Please enter a valid integer.");
            System.out.printf("For which order would you like to print the invoice?: ");
            scan.next();
        }
        int index = scan.nextInt();
        order toPrint = allOrders.get(index - 1);
        System.out.println("Is the customer a member? (y/n): ");
        char ans = scan.next().charAt(0);
        scan.nextLine();
        if (ans == 'y') {
            System.out.println("Key in customer's member ID: ");
            while (!scan.hasNextLong())
            {
                System.out.println("Please enter a valid integer.");
                System.out.printf("Key in customer's member ID: ");
                scan.next();
            }
            long id = scan.nextLong();
            discount = discount(id);
        }
        System.out.println("============= INVOICE ================");
        System.out.println("Order ID: " + toPrint.getOrderId());
        System.out.println("Date Ordered: " + toPrint.getDate().getDayOfMonth() + "/"
                + toPrint.getDate().getMonthValue() + "/" + toPrint.getDate().getYear());
        System.out.println("Time Ordered: " + toPrint.getTime().getHour() + ":" + toPrint.getTime().getMinute());
        System.out.println("Prepared by: " + toPrint.getStaff().getName());
        System.out.println("Table No: " + toPrint.getTable().getTableNo());
        System.out.println("Ordered Items: ");
        for (menuItems item : toPrint.getOrderItems()) {
            System.out.println("(" + i + ") " + item.getName() + " $" + item.getPrice());
            total = total + item.getPrice();
            i++;
        }
        System.out.println();
        System.out.println("Subtotal: $ " + total);
        System.out.println();
        if (discount != 1) {
            System.out.println("Applicable Discount: " + String.format("%.2f", (1 - discount) * 100)  + "%");
            System.out.println("Discount: - $" + String.format("%.2f",(total * (1 - discount))));
            total = total * discount;
        }
        System.out.println();
        System.out.println("Applicable GST: 7%");
        System.out.println("Total after GST: + $" + String.format("%.2f",(total * 0.07)));
        total = total + (total * 0.07);
        System.out.println();
        System.out.println("TOTAL: $" + String.format("%.2f",total));
        System.out.println();
        System.out.println("=========== END OF INVOICE ==============");

        toPrint.setIsPaid(true);//paid

        manageTable tabledb = new manageTable();
        try {
            tabledb = tabledb.readTables();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for(Table table : tabledb.getTableList())
        {
            if (table.getTableNo() == toPrint.getTable().getTableNo())
            {
                table.setIsAvailable(true);
            }
        }

        try {
            tabledb.saveTables();
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

    /**
     * Calculate the discount applicable for invoice calculation
     * @param memberId member ID of member
     * @return discount received
     * @throws Exception
     */
    public static double discount(long memberId) throws Exception{
        manageMember manageMember = new manageMember();
        manageMember = manageMember.readMemberList();
        int check = manageMember.getMemberListIndexById(memberId);
        if (check == -1) {
            System.out.println("\n======================================");
            System.out.println("Invalid member Id");
            System.out.println("======================================\n");
        } else {
            System.out.println("\n======================================");
            System.out.println("Member Found!");
            System.out.println("Name: " +  manageMember.getMemberList().get(check).getName());
            System.out.println("Membership Tier: " +  manageMember.getMemberList().get(check).getTier());
            System.out.println("======================================\n");
            member.tier tier = manageMember.getMemberList().get(check).getTier();
            switch (tier) {
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

    /**
     * Function to save the list of orders to a txt file
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void saveOrders() throws ClassNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("orderSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * Function to populate the order list by reading the data from the saved txt file
     */
    public manageOrder readOrders() throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream("orderSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageOrder Testorders = (manageOrder) objectInputStream.readObject();
        objectInputStream.close();
        return Testorders;
    }

    /**
     * Funtion to calculate the number of each product sold in a specific month and year
     * @param month month of revenue
     * @param year year of revenue
     * @return returns an array representing the quantity of each product that is sold
     * @throws Exception
     */
    public int[] NumOfProductSold(int month, int year) throws Exception
    {   int i;
        mainMenu testMenu = new mainMenu();
        try {
            testMenu = testMenu.readMenu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int[] sales = new int[testMenu.getMenuItems().size()];
        for(i=0;i<testMenu.getMenuItems().size();i++)
        {
            sales[i] = 0;
        }
        for(order order : allOrders)
        {
            if((order.getDate().getMonthValue()==month) && (order.getDate().getYear()==year) && order.getIsPaid() == true)
            {
                for(menuItems items: order.getOrderItems())
                {
                    i = 0;
                    for(menuItems id : testMenu.getMenuItems())
                    {
                        if(items.getName().equals(id.getName()))
                        {
                            sales[i]++;
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        return sales;
    }

    /**
     * function to calculate the actual revenue of each product for a specific month and year
     * @param sales array representing the quantity of each product that is sold
     * @return array representing the revenue in SGD of each product that is sold
     * @throws Exception
     */
    public double[] revenueOfProductSold(int[] sales) throws Exception
    {
        int i;
        mainMenu testMenu = new mainMenu();
        try {
            testMenu = testMenu.readMenu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        double[] revenue = new double[testMenu.getMenuItems().size()];
        for(i=0;i<testMenu.getMenuItems().size();i++)
        {
            revenue[i] = 0;
        }
        for(i=0;i<testMenu.getMenuItems().size();i++)
        {
            revenue[i] = sales[i]*(testMenu.getMenuItems().get(i).getPrice());
        }

        return revenue;
    }

    /**
     * Prints the revenue of each item for a specific year and month
     * Also prints total revenue for that year and month
     * @throws Exception
     */
    public void printRevenueReport() throws Exception
    {   int i=0;
        mainMenu testMenu = new mainMenu();
        try {
            testMenu = testMenu.readMenu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Key in month(1-12): ");
        while (!scan.hasNextInt())
        {
            System.out.println("Please enter a valid integer.");
            System.out.printf("Key in month(1-12): ");
            scan.next();
        }
        int month = scan.nextInt();
        scan.nextLine();
        System.out.println("Key in Year: ");
        while (!scan.hasNextInt())
        {
            System.out.println("Please enter a valid integer.");
            System.out.printf("Key in Year: ");
            scan.next();
        }
        int year = scan.nextInt();
        scan.nextLine();
        int[] sales = NumOfProductSold(month, year);
        double[] revenue = revenueOfProductSold(sales);
        System.out.println();
        System.out.println("======== Product Revenue ========");
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        for(menuItems item : testMenu.getMenuItems())
        {
            System.out.println("Item: " + item.getName() + ", Quantity: " + sales[i] + ", Product Revenue: $" + String.format("%.2f",revenue[i]));
            i++;
        }

        double total = 0;
        for(i=0;i<testMenu.getMenuItems().size();i++)
        {
            total = total + revenue[i];
        }

        System.out.println();
        System.out.println("Total Revenue: $" + String.format("%.2f",total));
        System.out.println("======== End Of Revenue ========");
        

    }
}

