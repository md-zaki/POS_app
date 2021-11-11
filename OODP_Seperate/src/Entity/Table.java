package Entity;

/**
 * Entity Class for Tables
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class Table implements java.io.Serializable {
    /**
     * unique table number of table
     */
    private int tableNo;

    /**
     * capacity of table
     */
    private int tableSize;

    /**
     * if current table is available or not
     */
    private boolean isAvailable;

    /**
	 * Creates a new table in the restaurant
	 * @param tableNo       unique table number
     * @param tableSize     capacity of table
	 */
    public Table(int tableNo, int tableSize) {
        this.tableNo = tableNo;
        this.tableSize = tableSize;
        isAvailable = true;
    }

    /**
     * get capacity of table
     * @return table capacity
     */
    public int getTableSize() {
        return this.tableSize;
    }


    /**
     * set capacity of table
     * @param tableSize capacity of table
     */
    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }


    /**
     * get whether if the table is available
     * @return availability of table
     */
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    /**
     * set availability of table
     * @param isAvailable availability of table
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * get table number
     * @return table number
     */
    public int getTableNo() {
        return this.tableNo;
    }

    /**
     * set table number
     * @param tableNo table number
     */
    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

}

