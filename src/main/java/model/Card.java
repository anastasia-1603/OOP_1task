package model;
public class Card
{
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Card()
    {
        this.rank = null;
        this.suit = null;
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
}
