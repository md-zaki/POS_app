public class Table {
	public enum size{


	}
	private size tableSize;
	private boolean isAvailable;

	public Table(size tableSize) {
		this.tableSize = tableSize;
		isAvailable = true;
	}

	public size getTableSize() {
		return this.tableSize;
	}

	public void setTableSize(size tableSize) {
		this.tableSize = tableSize;
	}

	public boolean getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
