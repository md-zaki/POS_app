import java.time.LocalDate;
import java.time.LocalTime;

public class reservation {

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

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getNumOfpax() {
		return this.numOfpax;
	}

	public void setNumOfpax(int numOfpax) {
		this.numOfpax = numOfpax;
	}

	public void removeReservation(int reservation) {
		// TODO - implement reservation.removeReservation
		throw new UnsupportedOperationException();
	}

}
