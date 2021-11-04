public class Table {

	private int tableSize;
	private boolean isAvailable;

	public Table(int tableSize) {
		this.tableSize = tableSize;
		isAvailable = true;
	}

	public int getTableSize() {
		return this.tableSize;
	}

	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	public boolean getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
