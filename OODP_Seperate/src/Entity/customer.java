package Entity;
/**
 * Entity Class for Customer
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class customer implements java.io.Serializable {

    /**
     * Name of Customer
     */
    private String name;

    /**
     * Contact number of Customer
     */
    private long contact;

    /**
	 * create a new Customer having a name and contact number
	 * @param name	            name of the customer
	 * @param contact			Contact number of customer
	 */
    public customer(String name, long contact) {

        this.name = name;
        this.contact = contact;
    }

    /**
	 * get the name of Customer
	 * @return this customer's name
	 */
    public String getName() {
        return this.name;
    }

    /**
	 * set the name of Customer
	 * @param name name of the customer
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * get the contact number of Customer
	 * @return this customer's contact number
	 */
    public long getContact() {
        return this.contact;
    }

     /**
	 * set the contact number of Customer
	 * @param name contact number of the customer
	 */
    public void setContact(long contact) {
        this.contact = contact;
    }

}
