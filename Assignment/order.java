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
		FileInputStream fileInputStream = new FileInputStream("testMenuSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        mainMenu menu = (mainMenu) objectInputStream.readObject();
        objectInputStream.close();
		ArrayList<menuItems> foodmenu = menu.getMenuItems();

		
		for(menuItems item : foodmenu)
		{
			System.out.println("(" + i + ") " + item.getName());
			i++;
		}
		System.out.println("Select item to add into order:");
		int choice = scan.nextInt();
		String dummy = scan.nextLine();
		menuItems newAdd = foodmenu.get(choice-1);
		this.orderItems.add(newAdd);
	}

	public void removeOrderItem(){

	}

	public void viewOrder()
    {
		
    }

	public void printOrderInvoice() {
		// TODO - implement order.printOrderInvoice
		throw new UnsupportedOperationException();
	}

	

}
