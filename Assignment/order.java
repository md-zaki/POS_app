
import java.util.*;

public class order {

	Collection<menuItems> menuItem;
	private int orderId;
	private String date;
	private String time;
	private double totalAmount;

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void addOrderItem(int menuItems) {
		// TODO - implement order.addOrderItem
		throw new UnsupportedOperationException();
	}

	public ArrayList<menuItems> getOrderList() {
		// TODO - implement order.getOrderList
		throw new UnsupportedOperationException();
	}

	public void setOrderList(ArrayList<menuItems> orderList) {
		// TODO - implement order.setOrderList
		throw new UnsupportedOperationException();
	}

	public void printOrderInvoice() {
		// TODO - implement order.printOrderInvoice
		throw new UnsupportedOperationException();
	}

	public order(int orderId, String date, int time) {
		// TODO - implement order.order
		throw new UnsupportedOperationException();
	}

}
