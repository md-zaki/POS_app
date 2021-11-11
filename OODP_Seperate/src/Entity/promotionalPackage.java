package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entity Class for Promotional Package, inherits from menuItems
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class promotionalPackage extends menuItems implements Serializable{
    /**
     * list of individual menu items in promo package
     */
    private ArrayList<menuItems> itemList;

    /**
	 * Creates a new promo package
     * Create new itemList in constructor
	 * @param name      name of promo package
	 * @param price     price of promo package
     * @param type      set type as set
	 */
    public promotionalPackage(String name,double price, menuItemType type) {
        super(name, null, price,menuItems.menuItemType.set);
        itemList = new ArrayList<menuItems>();
    }

    /**
     * get array list of menu items in promo package
     * @return array list of menu items
     */
    public ArrayList<menuItems> getItemList() {
        return this.itemList;
    }

     /**
     * set array list of menu items in promo package
     * @param itemList array list of menu items
     */
    public void setItemList(ArrayList<menuItems> itemList) {
        this.itemList = itemList;
    }

    /**
     * Adds individual menu items into promo package
     * @param menuList array list of items in promo package
     */
    public void addItem(ArrayList<menuItems> menuList)
    {
        Scanner scan = new Scanner(System.in);
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

