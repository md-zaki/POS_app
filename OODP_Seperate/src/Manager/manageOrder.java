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

public class manageOrder implements Serializable{

    private static final long serialVersionUID = 12345L;

    private ArrayList<order> allOrders = new ArrayList<order>();

    public manageOrder() {
        //allOrders = new ArrayList<order>();
    }

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

    public void editOrder() throws Exception {
        Scanner scan = new Scanner(System.in);
        viewAllOrders();
        if (allOrders.size() != 0) {
            System.out.println("Enter order id to edit: ");
            int pick = scan.nextInt();
            String dummy = scan.nextLine();
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
                    pick = scan.nextInt();
                    dummy = scan.nextLine();
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
        System.out.println("Enter Choice: ");
        int pick = scan.nextInt();
        String dummy = scan.nextLine();
        System.out.println("========");
        staff prepby = (staff) staffList.get(pick - 1);
        return prepby;
    }

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
        System.out.println("Enter Choice: ");
        int pick = scan.nextInt();
        String dummy = scan.nextLine();
        Table toChoose = (Table) tabledb.getTableList().get(pick-1);
        while(toChoose.getIsAvailable()==false)
        {
            System.out.println("Table unavailable, choose another table: ");
            pick = scan.nextInt();
            dummy = scan.nextLine();
            toChoose = (Table) tabledb.getTableList().get(pick-1);
        }

        toChoose.setIsAvailable(false);//set table to be unavailable
        try {
            tabledb.saveTables();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return toChoose;
    }

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



    public void printInvoice() throws Exception{
        int i = 1;
        double total = 0;
        double discount = 1;
        Scanner scan = new Scanner(System.in);
        viewAllOrders();
        System.out.println("For which order would you like to print the invoice?: ");
        int index = scan.nextInt();
        order toPrint = allOrders.get(index - 1);
        System.out.println("Is the customer a member? (y/n): ");
        char ans = scan.next().charAt(0);
        scan.nextLine();
        if (ans == 'y') {
            System.out.println("Key in customer's member ID: ");
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

    public void saveOrders() throws ClassNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("orderSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public manageOrder readOrders() throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream("orderSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        manageOrder Testorders = (manageOrder) objectInputStream.readObject();
        objectInputStream.close();
        return Testorders;
    }
}

