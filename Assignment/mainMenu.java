import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class mainMenu implements Serializable{
    private ArrayList<menuItems> menuList;
    //private static int numOfItems=0;

    public mainMenu() throws IOException, ClassNotFoundException {
        //numOfItems = 0;
        menuList = new ArrayList<menuItems>();
    }

    public ArrayList<menuItems> getMenuItems() {
        return this.menuList;
    }

    public void setMenuItems(ArrayList<menuItems> menuItems) {
        this.menuList = menuItems;
    }

   /* public int getNumOfItems() {
        return mainMenu.numOfItems;
    }*/

    /*public void setNumOfItems(int numOfItems) {
        mainMenu.numOfItems = numOfItems;
    }*/

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

    public void removeMenuItem() {
        Scanner scan = new Scanner(System.in);
        viewMenu();
        System.out.println("Enter index of menu item to delete:");
        int index = scan.nextInt();
        String dummy = scan.nextLine();
        menuList.remove(index-1);
    }

    public void updateMenuItem(ArrayList<menuItems> menuList) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter index of menu item to update:");
        int index = scan.nextInt();
        //String dummy = scan.nextLine();
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

    public void editMenu() throws IOException, ClassNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        int choice;
        do{
        System.out.println("(1) Add new alacarte item to menu");
        System.out.println("(2) Add new promotion package to menu");
        System.out.println("(3) Update menu item in food menu");
        System.out.println("(4) Remove a menu item from food menu");
        System.out.println("(5) View Menu");
        System.out.println("(6) Exit");
        System.out.println("Select a choice: ");
        choice = scan.nextInt();
        String dummy = scan.nextLine();
        	
        	switch (choice) {
	            case 1: 
	            		addMenuItem();
	                    break;
	            case 2:
	            		addPromo(this.menuList);
	                    break;
	            case 3:
	            		updateMenuItem(this.menuList);
	                	break;
	            case 4:
	            		removeMenuItem();
	            		break;
	            case 5:
	            		viewMenu();
	            		break;
	            case 6:
        	}
        } while (choice != 6);
        
        
        
    }

    public void saveMenu() throws ClassNotFoundException, IOException 
	{
		FileOutputStream fileOutputStream = new FileOutputStream("testMenuSave.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
	}

	public mainMenu readMenu() throws ClassNotFoundException, IOException 
	{
		FileInputStream fileInputStream = new FileInputStream("testMenuSave.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        mainMenu menu = (mainMenu) objectInputStream.readObject();
        objectInputStream.close();
		return menu;
	}
    
}
