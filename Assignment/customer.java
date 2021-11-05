public class customer {

	public static enum tier
	{
		Gold,
		Silver,
		Bronze
	}
	
	reservation reserv;
	private long memberId;
	private String name;
	private long contact;
	private tier memberTier;

	public customer(long memberId, String name, long contact, tier memberTier)
	{
		this.memberId = memberId;
		this.name = name;
		this.contact = contact;
		this.memberTier = memberTier;
	}

	public long getMemberId()
	{
		return this.memberId;
	}

	public void setMemberId(long memberId)
	{
		this.memberId = memberId;
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

	public tier getTier()
	{
		return this.memberTier;
	}

	public void setTier(tier memberTier) {
		this.memberTier = memberTier;
	}
	
}
