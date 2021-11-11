package Entity;
import java.util.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import Manager.mainMenu;

/**
 * Entity Class for Order
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class order implements Serializable{

    /**
	 * Contains individual menu items ordered in an order
	 */
    private ArrayList<menuItems> orderItems;

    /**
	 * Unique order ID of order
	 */
    private int orderId;

    /**
	 * Date order was taken
	 */
    private LocalDate date;

    /**
	 * Time order was taken
	 */
    private LocalTime time;

    /**
	 * The staff that prepared the order
	 */
    private staff prepBy;

    /**
	 * The table the order was taken from
	 */
    private Table table;

    /**
	 * Indicates whether order was already paid
	 */
    private boolean isPaid;

    /**
	 * Creates a new order. paid will be set to false. Set to true when invoice is printed
	 * @param orderId       Unique order ID of order
     * @param date          Date order was taken
     * @param time          Time order was taken
     * @param prepBy        The staff that prepared the order
     * @param table         The table the order was taken from
	 */
    public order(int orderId, LocalDate date, LocalTime time,staff prepBy, Table table) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.prepBy = prepBy;
        orderItems = new ArrayList<menuItems>();
        this.table = table;
        this.isPaid = false;
    }

    /**
	 * Get unique order id
     * @return order id
	 */
    public int getOrderId() {
        return this.orderId;
    }
    
    /**
	 * set the order id
	 * @param orderId order id of order
	 */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    /**
	 * Get order date
     * @return order date
	 */
    public LocalDate getDate() {
        return this.date;
    }

    /**
	 * set the order date
	 * @param date order date
	 */
    public void setDate(LocalDate date) {
        this.date = date;
    }


    /**
	 * Get staff who prepared order
     * @return staff
	 */
    public staff getStaff()
    {
        return prepBy;
    }


    /**
	 * Get time of order
     * @return order time
	 */
    public LocalTime getTime() {
        return this.time;
    }


     /**
	 * set the order time
	 * @param time order time
	 */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
	 * Get the list of items in the order
     * @return array list of menu items
	 */
    public ArrayList<menuItems> getOrderItems() {
        return this.orderItems;
    }

    /**
	 * set the list menu items
	 * @param orderItems list of menu items
	 */
    public void setMenuItems(ArrayList<menuItems> orderItems) {
        this.orderItems = orderItems;
    }

     /**
	 * Get the table of order
     * @return table of order
	 */
    public Table getTable()
    {
        return this.table;
    }


    /**
	 * set the table of order
	 * @param table table of order
	 */
    public void setTable(Table table)
    {
        this.table = table;
    }


    /**
	 * Get whether order has been paid or not
     * @return true if paid, false if not paid
	 */
    public boolean getIsPaid()
    {
        return this.isPaid;
    }

    /**
	 * set the paid status of order
	 * @param isPaid paid status
	 */
    public void setIsPaid(boolean isPaid)
    {
        this.isPaid = isPaid;
    }

    /**
     * Add items from menu into order list of this order
     * User will be asked to picked from a list of menu items from menu
     * @throws Exception
     */
    public void addOrderItem() throws Exception {
        Scanner scan = new Scanner(System.in);

        mainMenu menu = new mainMenu();
        menu = menu.readMenu();
        ArrayList<menuItems> foodmenu = menu.getMenuItems();

        System.out.println("======= ADDING ITEMS INTO ORDER =======");
        System.out.println("1. Add Items into Order");
        System.out.println("2. Stop adding items");
        int choice = scan.nextInt();
        String dummy = scan.nextLine();
        while(choice == 1)
        {
            int i=1;
            System.out.println("Which item would you like to add to Order");
            System.out.println("========");
            for(menuItems item : foodmenu)
            {
                System.out.println("(" + i + ") " + item.getName());
                i++;
            }
            System.out.println("Enter Choice: ");
            int pick = scan.nextInt();
            dummy = scan.nextLine();
            System.out.println("========");

            menuItems toAdd = (menuItems) foodmenu.get(pick-1);
            this.orderItems.add(toAdd);
            System.out.println("1. Add more Items into Order");
            System.out.println("2. Stop");
            System.out.println("Enter Choice: ");
            choice = scan.nextInt();
            dummy = scan.nextLine();
            System.out.println("========");
        }
    }

    /**
     * Remove any menu items from this order
     * User will be asked which item to remove
     */
    public void removeOrderItem(){
        Scanner scan = new Scanner(System.in);
        this.viewOrder();
        System.out.println("Enter Item to remove from order: ");
        int pick = scan.nextInt();
        String dummy = scan.nextLine();
        menuItems toRemove = this.orderItems.get(pick-1);
        this.orderItems.remove(toRemove);
    }

    /**
     * Prints out all details about this order
     */
    public void viewOrder()
    {
        int i=1;
        System.out.println();
        System.out.println("Order ID: " + this.getOrderId());
        System.out.println("Table No: " + this.getTable().getTableNo());
        System.out.println("Date Ordered: " + this.getDate().getDayOfMonth() +"/" + this.getDate().getMonthValue() + "/" + this.getDate().getYear());
        System.out.println("Time Ordered: " + this.getTime().getHour() +":" + this.getTime().getMinute());
        System.out.println("Prepared by: " + this.getStaff().getName());
        System.out.println("Paid? : " + this.getIsPaid());
        System.out.println("Ordered Items: ");
        for(menuItems item : this.orderItems)
        {
            System.out.println("	(" + i + ") " + item.getName());
            i++;
        }
    }
}
