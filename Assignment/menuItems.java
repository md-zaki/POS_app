import java.util.*;

public class menuItems {

	menuItemType itemType;
	Collection<order> order;
	private String name;
	private String description;
	private double price;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @param name
	 * @param description
	 * @param price
	 */
	public menuItems(String name, String description, double price) {
		// TODO - implement menuItems.menuItems
		throw new UnsupportedOperationException();
	}

}