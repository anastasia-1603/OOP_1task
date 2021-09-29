package model;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private List<Card> playerCards;
    private List<Card> trick; // взятка
    private String name;
    private int points;

    public Player(List<Card> cards, String name)
    {
        this.playerCards = cards;
        this.trick = new ArrayList<>();
        this.name = name;
        this.points = 0;
    }

    public Player()
    {
        this.playerCards = new ArrayList<>();
        this.trick = new ArrayList<>();
        this.name = null;
        this.points = 0;
    }


    public List<Card> getPlayerCards()
    {
        return playerCards;
    }

    public List<Card> getTrick()
    {
        return trick;
    }

    public void setTrick(Card trick)
    {
        this.trick.add(trick);
    }

    public String getName()
    {
        return name;
    }

    public void setPoints(int points)
    {
        this.points += points;
    }

    public int getPoints()
    {
        return points;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void acceptCard(Card card)
    {
        playerCards.add(card);
    }

    public void acceptCards(List<Card> cards)
    {
        playerCards.addAll(cards);
    }

    public Card giveCard()
    {
        return playerCards.remove(0);
    }

    public Card giveCard(Rank rank, Suit suit) // todo сделать исключения
    {
        try
        {
            return playerCards.remove(playerCards.indexOf(new Card(rank, suit)));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<Card> giveCards(int numberCards)
    {
        List<Card> returnedCards = new ArrayList<>();
        for (int i = 0; i < numberCards; i++)
        {
            returnedCards.add(playerCards.remove(i));
        }
        return returnedCards;
    }


    public void setPlayerCards(List<Card> playerCards)
    {
        this.playerCards = playerCards;
    }
}
