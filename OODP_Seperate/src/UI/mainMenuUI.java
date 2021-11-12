package UI;
import java.util.Scanner;
import Manager.mainMenu;

/**
 * This is a UI class used to display menu options
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class mainMenuUI {

    /**
     * Starts the UI for the user to select menu options.
     * After selection of the desired operation, appropriate methods from mainMenu will be called.
     * @param mainMenu mainMenu object which consist of all logic functions related to menu
     * @throws Exception
     */
    public static void start(mainMenu mainMenu) throws Exception
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

            while (!scan.hasNextInt())
            {
                System.out.println("Please enter a valid option.");
                System.out.printf("Select a choice: ");
                scan.next();
            }

            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    mainMenu.addMenuItem();
                    break;
                case 2:
                    mainMenu.addPromo(mainMenu.getMenuItems());
                    break;
                case 3:
                    mainMenu.updateMenuItem(mainMenu.getMenuItems());
                    break;
                case 4:
                    mainMenu.removeMenuItem();
                    break;
                case 5:
                    mainMenu.viewMenu();
                    break;
                case 6:
            }
        } while (choice != 6);
    }
}

