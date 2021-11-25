package service;

import exceptions.EmptyPlayersException;
import exceptions.NullPlayersException;
import model.Card;
import model.Player;
import model.Rank;
import model.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerService
{

    public Player findWinner(List<Player> players)
    {
        Player winner = players.get(0);
        for (Player player : players)
        {
            if (player.getPoints() < winner.getPoints())
            {
                winner = player;
            }
        }
        return winner;
    }

    public boolean isPointsMore100(List<Player> players)
    {
        for (Player player : players)
        {
            if (player.getPoints() >= 100)
            {
                return true;
            }
        }
        return false;
    }

    /*public Card makeAMove(Player player, Suit suit)
    {
        if (isHaveCard(suit, player))
        {

        }
    }*/

   /* public void HandOverCard(Card card, Player player1, Player player2) throws Exception
    {
        if (removeCard(player1, card))
        {
            player2.getPlayerCards().add(card);
        }
        else
        {
            throw new Exception("The player doesn't have this card");
        }
    }*/

    public Card removeCard(Player player, Card card)
    {
        int index = -1;
        if (player.getPlayerCards().contains(card))
        {
           index = player.getPlayerCards().indexOf(card);
        }
        return player.getPlayerCards().remove(index);
    }

    public void dealCards(List<Player> players) throws Exception
    {
        List<Card> deck = createDeck();
        Collections.shuffle(deck);
        if (players != null)
        {
            if (players.size() != 0)
            {
                int numberCards = deck.size() / players.size();
                for (Player player : players)
                {
                    List<Card> cards = deck.subList(0, numberCards);
                    player.acceptCards(cards);
                    deck.removeAll(cards);
                }
                deck.clear();

            } else
            { throw new EmptyPlayersException(); }

        } else
        { throw new NullPlayersException(); }
    }

    public List<Card> createDeck()
    {
        List<Card> allCards = new ArrayList<>();
        for (Rank rank : Rank.values())
        {
            for (Suit suit : Suit.values())
            {
                allCards.add(new Card(rank, suit));
            }
        }
        return allCards;
    }

    public int calcNumberAllCards(List<Player> players)
    {
        int numberCards = 0;
        for (Player player : players)
        {
            numberCards += player.getPlayerCards().size();
        }
        return numberCards;
    }

    public boolean isHaveCard(Card card, Player player)
    {
        if (player != null)
        {
            return player.getPlayerCards().contains(card);
        }
        else
        {
            throw new NullPointerException();
        }
    }

    public boolean isHaveCard(Suit suit, Player player)
    {
        if (player != null)
        {
            for (Card card : player.getPlayerCards())
            {
                if (card.getSuit() == suit)
                {
                    return true;
                }
            }
        }
        else
        {
            throw new NullPointerException();
        }
        return false;
    }

    public void scorePoints(List<Player> players)
    {
        for (Player player : players)
        {
            for (Card card : player.getTrick())
            {
                if (card.getSuit() == Suit.HEARTS)
                {
                    player.setPoints(1);
                }
                else if (card.getSuit() == Suit.SPADES && card.getRank() == Rank.QUEEN)
                {
                    player.setPoints(13);
                }
            }
        }
    }
}
