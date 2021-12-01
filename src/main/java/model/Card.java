package model;
public class Card implements Comparable<Card>
{
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj.getClass() == this.getClass())
        {
            Card card = (Card) obj;
            return card.rank == this.rank && card.suit == this.suit;
        }
        return false;
    }

    public Rank getRank()
    {
        return rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    @Override
    public String toString()
    {
        return this.getRank().symbol + this.getSuit().unicode;
    }

    @Override
    public int compareTo(Card card)
    {
        return rank.priority - card.getRank().priority;
    }
}
