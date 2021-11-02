public class menuItems {
    public enum menuItemType{mainCourse,
        drinks,
        dessert,
        set};
    private menuItemType type;
    private String name;
    private String description;
    private double price;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public menuItemType getType() {
        return this.type;
    }

    public void setName(menuItemType type) {
        this.type = type;
    }

    public menuItems(String name, String description, double price, menuItemType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type=type;
    }

}
