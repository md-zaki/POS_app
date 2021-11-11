package Entity;

import java.io.Serializable;
/**
 * Entity Class for menuItems
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class menuItems implements Serializable{

     /**
     * Different types a menu item can be
     */
    public enum menuItemType implements Serializable{
        mainCourse,
        drinks,
        sides,
        dessert,
        set};

    /**
     * type of menu item
     */
    private menuItemType type;

    /**
     * name of menu item
     */
    private String name;

    /**
     * Description of menu item
     */
    private String description;

    /**
     * price of menu item
     */
    private double price;
    
     /**
	 * Creates a new menu item
	 * @param name              name of menu item
     * @param description       description of menu item
     * @param price             price of menu item
     * @param type              type of menu item
	 */
    public menuItems(String name, String description, double price, menuItemType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type=type;
    }

     /**
	 * get the name of menu item
	 * @return this menu item's name
	 */
    public String getName() {
        return this.name;
    }

     /**
	 * set the name of menu item
	 * @param name name of menu item
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * get the description of menu item
	 * @return this menu item's description
	 */
    public String getDescription() {
        return this.description;
    }

    /**
	 * set the description of menu item
	 * @param description description of menu item
	 */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
	 * get the price of menu item
	 * @return this menu item's price
	 */
    public double getPrice() {
        return this.price;
    }

    /**
	 * set the price of menu item
	 * @param price price of menu item
	 */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
	 * get the type of menu item
	 * @return this menu item's type
	 */
    public menuItemType getType() {
        return this.type;
    }

     /**
	 * set the type of menu item
	 * @param type type of menu item
	 */
    public void setName(menuItemType type) {
        this.type = type;
    }

    

}

