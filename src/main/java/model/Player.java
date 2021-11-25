package model;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private List<Card> playerCards;
    private List<Card> trick; 
    private String name;
    private int points;

    public Player()
    {
        this.playerCards = new ArrayList<>();
        this.trick = new ArrayList<>();
        this.name = null;
        this.points = 0;
    }

    public Player(String name)
    {
        this.playerCards = new ArrayList<>();
        this.trick = new ArrayList<>();
        this.name = name;
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

    public void setTrick(List<Card> trick)
    {
        this.trick.addAll(trick);
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

    public void acceptCards(List<Card> cards)
    {
        playerCards.addAll(cards);
    }

    public void setPlayerCards(List<Card> playerCards)
    {
        this.playerCards = playerCards;
    }
}
