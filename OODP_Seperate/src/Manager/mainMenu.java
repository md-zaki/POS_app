package Manager;

import Entity.menuItems;
import Entity.customer;
import Entity.promotionalPackage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manager Class for menu and menu items
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class mainMenu implements Serializable{

    private static final long serialVersionUID = 12345L;

    /**
     * List of menu items and promotional packages in the restaurant menu
     */
    private ArrayList<menuItems> menuList;

    /**
     * Constructor for mainMenu, creates new list of menu items
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public mainMenu() throws IOException, ClassNotFoundException {
        menuList = new ArrayList<menuItems>();
    }

    /**
     * Get list of items in menu including promo packages
     * @return menu list
     */
    public ArrayList<menuItems> getMenuItems() {
        return this.menuList;
    }

    /**
     * set menu items list
     * @param menuItems menu items list
     */
    public void setMenuItems(ArrayList<menuItems> menuItems) {
        this.menuList = menuItems;
    }

    /**
     * Add new ala carte items into Menu
     */
    public void addMenuItem() {
        Scanner scan = new Scanner(System.in);
        int i =1;
        System.out.println("Enter name of menu item:");
        String name = scan.nextLine();

        System.out.println("Enter description of menu item:");
        String desc = scan.nextLine();

        System.out.println("Enter price of menu item:");
        double price = scan.nextDouble();
        String dummy = scan.nextLine();

        System.out.println("Enter type of menu item:");
        for(menuItems.menuItemType itemType : menuItems.menuItemType.values())
        {
            System.out.println(i + ": " + itemType);
            i++;
        }
        int typenum = scan.nextInt();

        menuItems.menuItemType typechoice = menuItems.menuItemType.values()[typenum-1];
        dummy = scan.nextLine();
        menuItems newAdd = new menuItems(name, desc, price, typechoice);
        menuList.add(newAdd);

        //mainMenu.numOfItems++;

    }

    /**
     * Remove any items in menu including promo package
     */
    public void removeMenuItem() {
        Scanner scan = new Scanner(System.in);
        viewMenu();
        System.out.println("Enter index of menu item to delete:");
        int index = scan.nextInt();
        String dummy = scan.nextLine();
        menuList.remove(index-1);
    }

    /**
     * Edit any items in menu including promo package
     * @param menuList list of items in menu
     * @throws IOException
     */
    public void updateMenuItem(ArrayList<menuItems> menuList) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter index of menu item to update:");
        int index = scan.nextInt();
        scan.nextLine();
        index = index -1;
        int i =1;
        if(menuList.get(index).getType()!= menuItems.menuItemType.set)
        {
            System.out.println("Updating item " + menuList.get(index).getName());
            String name = menuList.get(index).getName();
            String desc = menuList.get(index).getDescription();
            double price = menuList.get(index).getPrice();
            menuItems.menuItemType typechoice = menuList.get(index).getType();
            System.out.println("Select operations");
            System.out.println("1. Update " + menuList.get(index).getName() + " Name");
            System.out.println("2. Update " + menuList.get(index).getName() + " description");
            System.out.println("3. Update " + menuList.get(index).getName() + " price");
            System.out.println("4. Update " + menuList.get(index).getName() + " item type");
            System.out.println("5. Exit");
            System.out.printf("Select choice: ");
            int choice = scan.nextInt();
            scan.nextLine();
            while (choice != 5)
            {
                if (choice == 1)
                {
                    System.out.println("Enter updated name of menu item:");
                    name = scan.nextLine();

                    //String dummy = scan.nextLine();
                }
                else if (choice == 2)
                {
                    System.out.println("Enter updated description of menu item:");
                    desc = scan.nextLine();
                }
                else if (choice == 3)
                {
                    System.out.println("Enter updated price of menu item:");
                    price = scan.nextDouble();
                    String dummy = scan.nextLine();
                }
                else if (choice == 4)
                {
                    System.out.println("Enter updated type of menu item:");
                    for(menuItems.menuItemType itemType : menuItems.menuItemType.values())
                    {
                        System.out.println(i + ": " + itemType);
                        i++;
                    }
                    int typenum = scan.nextInt();
                    typechoice = menuItems.menuItemType.values()[typenum-1];
                    //menuItems.menuItemType typechoice = menuItems.menuItemType.values()[typenum-1];
                    String dummy = scan.nextLine();
                }
                else
                {
                    System.out.println("Please enter a valid choice");
                }

                menuItems newAdd = new menuItems(name, desc, price, typechoice);
                menuList.set(index, newAdd);
                /*
                FileOutputStream fileOutputStream = new FileOutputStream("testMenuSave.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(menuList);
                objectOutputStream.flush();
                objectOutputStream.close();*/

                //String dummy = scan.nextLine();

                System.out.println("Select operations");
                System.out.println("1. Update " + menuList.get(index).getName() + " Name");
                System.out.println("2. Update " + menuList.get(index).getName() + " description");
                System.out.println("3. Update " + menuList.get(index).getName() + " price");
                System.out.println("4. Update " + menuList.get(index).getName() + " item type");
                System.out.println("5. Exit");
                System.out.printf("Select choice: ");
                choice = scan.nextInt();
                scan.nextLine();
            }

        }
        else
        {
            System.out.println("Enter updated name of promo:");
            String name = scan.nextLine();
            System.out.println("Enter updated price of promo:");
            double price = scan.nextDouble();
            //dummy = scan.nextLine();
            promotionalPackage promo = new promotionalPackage(name, price, menuItems.menuItemType.set);
            promo.addItem(menuList);
            menuList.set(index,promo);

        }


    }

    /**
     * Add a new promo package into menu
     * @param menuList
     */
    public void addPromo(ArrayList<menuItems> menuList)
    {
        Scanner scan = new Scanner(System.in);
        int i =1;
        System.out.println("Enter name of promo:");
        String name = scan.nextLine();

        System.out.println("Enter price of promo:");
        double price = scan.nextDouble();
        String dummy = scan.nextLine();

        promotionalPackage promo = new promotionalPackage(name, price, menuItems.menuItemType.set);
        promo.addItem(menuList);
        menuList.add(promo);

    }

    public void printitem(menuItems item) {
        System.out.println();
        System.out.println("Alacarte Name: " + item.getName());
        System.out.println("Alacarte Description: " + item.getDescription());
        System.out.println("Alacarte Price: " + item.getPrice());
        System.out.println("Type: " + item.getType());
    }

    public void printitemPromo(promotionalPackage item)
    {
        System.out.println("Promo Name: " + item.getName());
        System.out.println("Promo Price: " + item.getPrice());
        System.out.println("This Promotion Package contains: ");
        int i;
        int index = 1;
        for(i=0;i<item.getItemList().size();i++)
        {
            //System.out.println("ITEM " + index + ":");
            System.out.println("        Food Name: " + item.getItemList().get(i).getName());
            System.out.println("        Food Description: " + (item.getItemList().get(i).getDescription()));
            System.out.println();
            index++;
        }
    }

    /**
     * Function to view the contents of the entire menu,
     * including promo package
     */
    public void viewMenu()
    {
        System.out.println("============== MENU ==================");
        int i;
        int index=1;

        if(menuList == null)
        {
            System.out.println("Menu is empty\n");
        }
        else
        {
            for(i=0;i<menuList.size();i++)
            {
                if(menuList.get(i).getType() == menuItems.menuItemType.set)
                {

                    System.out.println("ITEM " + index + ":");
                    printitemPromo((promotionalPackage)menuList.get(i));
                }
                else{

                    System.out.println("ITEM " + index + ":");
                    printitem(menuList.get(i));
                    System.out.println();
                }
                index++;
            }
            System.out.println("============== END OF MENU ==================");
        }
    }



    /**
     * Function to save the menu contents to a txt file
     * @throws IOException for when the txt file does not exist
     */
    public void saveMenu() throws ClassNotFoundException, IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream("testMenuSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * Function to populate the menu by reading the data from the saved txt file
     * @return array list of menu items
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public mainMenu readMenu() throws ClassNotFoundException, IOException
    {
        FileInputStream fileInputStream = new FileInputStream("testMenuSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        mainMenu menu = (mainMenu) objectInputStream.readObject();
        objectInputStream.close();
        return menu;
    }

}

