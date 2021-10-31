package reservationApp;

import java.util.ArrayList;

public class promotionalPackage extends menuItems {

	public promotionalPackage(String name, String description, double price) {
		super(name, description, price);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<menuItems> itemList;
	private int numOfItems;
	
	

	public ArrayList<menuItems> getItemList() {
		return this.itemList;
	}

	
	public void setItemList(ArrayList<menuItems> itemList) {
		this.itemList = itemList;
	}

	public int getNumOfItems() {
		return this.numOfItems;
	}

	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}

}
