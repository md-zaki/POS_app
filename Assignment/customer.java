public class customer {

	reservation reserv;
	private String name;
	private long contact;
	private boolean hasMember;

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

	public long getContact() {
		return this.contact;
	}

	/**
	 * 
	 * @param contact
	 */
	public void setContact(long contact) {
		this.contact = contact;
	}

	public boolean getHasMember() {
		return this.hasMember;
	}

	/**
	 * 
	 * @param hasMember
	 */
	public void setHasMember(boolean hasMember) {
		this.hasMember = hasMember;
	}

}