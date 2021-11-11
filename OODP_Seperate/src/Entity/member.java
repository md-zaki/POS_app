package Entity;
/**
 * Entity Class for member, inherits from customer
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class member extends customer{
    /**
     * Different membership tiers a member can have
     */
    public static enum tier
    {
        Gold,
        Silver,
        Bronze
    }

    /**
     * Unique ID number of member
     */
    private long memberId;

    /**
     * membership tier the member has
     */
    private tier memberTier;

    /**
	 * Creates a new member
	 * @param name          name of member
     * @param contact       contant number of member
     * @param memberId      unique ID number of member
     * @param memberTier    membership tier of member
	 */
    public member(String name, long contact, long memberId, tier memberTier)
    {
        super(name, contact);
        this.memberId = memberId;
        this.memberTier = memberTier;
    }

    /**
	 * get the unique membership Id of member
	 * @return this member's membership ID number
	 */
    public long getMemberId()
    {
        return this.memberId;
    }

     /**
	 * set the unique membership Id of member
	 * @param memberId membership Id of member
	 */
    public void setMemberId(long memberId)
    {
        this.memberId = memberId;
    }

    /**
	 * get the membership tier of member
	 * @return this member's membership tier
	 */
    public tier getTier()
    {
        return this.memberTier;
    }

     /**
	 * set the membership tier of member
	 * @param memberTier membership tier
	 */
    public void setTier(tier memberTier) {
        this.memberTier = memberTier;
    }
}
