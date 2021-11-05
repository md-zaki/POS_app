public class Table {
	private int tableNo;
	private int tableSize;
	private boolean isAvailable;

	public Table(int tableNo,int tableSize) {
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

	public int getTableNo() {
		return this.tableSize;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

}
