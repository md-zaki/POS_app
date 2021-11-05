public class customer {

	private String name;
	private long contact;

	public customer(String name, long contact)
	{
		
		this.name = name;
		this.contact = contact;
	}

	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContact() {
		return this.contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}
	
}
