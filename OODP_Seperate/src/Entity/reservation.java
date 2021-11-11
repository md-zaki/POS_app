package Entity;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Entity Class for Reservations
 * @author Timothy, Malcolm
 * @version 1.0
 * @since 2021-11-11
 */
public class reservation implements java.io.Serializable {
    
    Table table;
    customer cust;
    private LocalDate date;
    private LocalTime time;
    private int numOfpax;

    public reservation(LocalDate date, LocalTime time, customer cust, Table table, int numOfPax) {
        this.date = date;
        this.time = time;
        this.cust = cust;
        this.table = table;
        this.numOfpax = numOfPax;
        // TODO - implement reservation.reservation
        // throw new UnsupportedOperationException();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getNumOfpax() {
        return this.numOfpax;
    }

    public void setNumOfpax(int numOfpax) {
        this.numOfpax = numOfpax;
    }

    public customer getCust() {
        return cust;
    }

    public Table getTable() {
        return table;
    }

    // public void removeReservation(int reservation) {
    // // TODO - implement reservation.removeReservation
    // throw new UnsupportedOperationException();
    // }

}

