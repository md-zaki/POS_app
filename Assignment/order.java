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
public class order {

	private ArrayList<menuItems> orderItems;
	private int orderId;
	private LocalDate date;
	private LocalTime time;
	private staff prepBy;
	private invoice invoice;
	public order(int orderId, LocalDate date, LocalTime time,staff prepBy) {
		this.orderId = orderId;
		this.date = date;
		this.time = time;
		this.prepBy = prepBy;
		orderItems = new ArrayList<menuItems>();
		this.invoice = null;
	}
	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public staff getStaff()
	{
		return prepBy;
	}

	public LocalTime getTime() {
		return this.time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public ArrayList<menuItems> getOrderItems() {
        return this.orderItems;
    }
    public void setMenuItems(ArrayList<menuItems> orderItems) {
        this.orderItems = orderItems;
    }

	public invoice getinvoice()
	{
		return invoice;
	}

	public void setinvoice(invoice invoice)
	{
		this.invoice = invoice;
	}

	public void addOrderItem() throws Exception {
		Scanner scan = new Scanner(System.in);
		int i=1;
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

	public void removeOrderItem(){

	}

	public void viewOrder()
    {
		System.out.println();
		System.out.println("Order ID: " + this.getOrderId());
		System.out.println("Date Ordered: " + this.getDate().getDayOfMonth() + this.getDate().getMonthValue() + this.getDate().getYear());
		System.out.println("Time Ordered: " + this.getTime().getHour() + this.getTime().getMinute());
		System.out.println("Prepared by: " + this.getStaff().getName());
    }

	public void printOrderInvoice() {
		throw new UnsupportedOperationException();
	}

	

}
