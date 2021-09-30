package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game
{
    private List<Player> players;
    private List<Card> allCards;
    private boolean isFinish;
    private static int seed = 1;

    public Game(List<Player> players)
    {
        this.players = players;
        List<Card> allCards = new ArrayList<>();
        for (Rank rank : Rank.values())
        {
            for (Suit suit : Suit.values())
            {
                allCards.add(new Card(rank, suit));
            }
        }
        isFinish = false;
        Collections.shuffle(allCards, new Random(seed++));
        this.allCards = allCards;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(List<Player> players)
    {
        this.players = players;
    }

    public List<Card> getAllCards()
    {
        return allCards;
    }

    public void setAllCards(List<Card> allCards)
    {
        this.allCards = allCards;
    }

    public boolean isFinish()
    {
        return isFinish;
    }

    public void setFinish(boolean finish)
    {
        isFinish = finish;
    }
}
