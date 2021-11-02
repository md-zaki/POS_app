import java.util.ArrayList;
import java.util.Scanner;
public class promotionalPackage extends menuItems {
	Scanner scan = new Scanner(System.in);
	public promotionalPackage(String name,double price, menuItemType type) {
		super(name, "", price,type);
		numOfItems = 0;
        itemList = new ArrayList<menuItems>();
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

	public void addItem(ArrayList<menuItems> menuList)
	{
		System.out.println("======= ADDING ITEMS INTO PROMOTION PACKAGE =======");
		System.out.println("1. Add Items into Promo");
		System.out.println("2. Stop adding items");
		int choice = scan.nextInt();
		String dummy = scan.nextLine();
		while(choice == 1)
		{
			
			System.out.println("Which item would you like to add to the Promotion Package?");
			int i=1;
			System.out.println("========");
			for(menuItems item : menuList)
			{
				if(item.getType()!= menuItems.menuItemType.set)
				{
					System.out.println(i + ": " + item.getName());
				}
				i++;
			}
			System.out.println("Enter Choice: ");
			int pick = scan.nextInt();
			dummy = scan.nextLine();
			System.out.println("========");
			menuItems toAdd = (menuItems) menuList.get(pick-1);
			this.itemList.add(toAdd);
			System.out.println("1. Add more Items into Promo");
			System.out.println("2. Stop");
			System.out.println("Enter Choice: ");
			choice = scan.nextInt();
			dummy = scan.nextLine();
			System.out.println("========");
		}
	}

	

}
