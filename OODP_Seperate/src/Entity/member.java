package Entity;

public class member extends customer{
    public static enum tier
    {
        Gold,
        Silver,
        Bronze
    }

    private long memberId;
    private tier memberTier;

    public member(String name, long contact, long memberId, tier memberTier)
    {
        super(name, contact);
        this.memberId = memberId;
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

    public tier getTier()
    {
        return this.memberTier;
    }

    public void setTier(tier memberTier) {
        this.memberTier = memberTier;
    }
}
