package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity Class for Reservations
 * 
 * Reservation object to represent a reservation booked by a customer.
 * Reservation contains table information, customer information, date, time, and
 * number of person.
 * 
 * @author Timothy, Malcom
 * @version 1.0
 * @since 2021-11-11
 */
public class reservation implements java.io.Serializable {

    /**
     * Table object which contains information of table that is reserved for this
     * reservation.
     */
    Table table;
    /**
     * customer object which contains information of customer that reserved this
     * reservation.
     */
    customer cust;
    /**
     * Date of this reservation.
     */
    private LocalDate date;
    /**
     * Time of this reservation.
     */
    private LocalTime time;
    /**
     * Number of pax for this reservation.
     */
    private int numOfpax;

    /**
     * Creates a new reservation object given date, time, customer object, table
     * object and number of pax.
     * 
     * @param date     this reservation date
     * @param time     this reservation time
     * @param cust     Customer's information
     * @param table    table information reserved for this reservation
     * @param numOfPax number of pax reserved for
     */
    public reservation(LocalDate date, LocalTime time, customer cust, Table table, int numOfPax) {
        this.date = date;
        this.time = time;
        this.cust = cust;
        this.table = table;
        this.numOfpax = numOfPax;
    }

    /**
     * Gets this reservation's date.
     * 
     * @return This reservation's date.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Changes the date of this reservation object.
     * 
     * @param date This reservation's new date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets this reservation's time.
     * 
     * @return This revservation's time.
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Changes the time of this reservation object.
     * 
     * @param time This reservation's new time.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Get this reservation's number of pax.
     * 
     * @return This reservation's number of pax.
     */
    public int getNumOfpax() {
        return this.numOfpax;
    }

    /**
     * Changes the number of pax for this reservation.
     * 
     * @param numOfpax This reservation's new number of pax.
     */
    public void setNumOfpax(int numOfpax) {
        this.numOfpax = numOfpax;
    }

    /**
     * Get this reservation's customer information.
     * 
     * @return This reservation's customer object.
     */
    public customer getCust() {
        return cust;
    }

    /**
     * Get this reservation's table information.
     * 
     * @return This reservation's table object.
     */
    public Table getTable() {
        return table;
    }
}
