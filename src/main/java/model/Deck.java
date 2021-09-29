/*package model;

import model.Rank;
import model.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck
{
    private List<Card> deck;

    public Deck()
    {
        deck = new ArrayList<>();
        for (Rank rank : Rank.values())
        {
            for (Suit suit : Suit.values())
            {
                deck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(deck);
    }

    public Deck(List<Card> cards)
    {
        this.deck = cards;
    }

    public void shuffleDeck()
    {
        Collections.shuffle(deck);
    }

    public Card getCard()
    {
        return deck.remove(0);
    }

    public List<Card> getCards(int numberCards)
    {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numberCards; i++)
        {
            cards.add(deck.remove(i));
        }
        return cards;
    }

    public void testPrintDeck()
    {
        for (Card card : this.deck)
        {
            System.out.printf("%s %s ", card.getRank(), card.getSuit());
        }
    }
}

    public List<Card> dealCards(Deck deck, int numberPlayers)
    {
        List<Card> playerCards = new ArrayList<>();
        playerCards = deck.getCards(numberPlayers);
        return playerCards;
    }
*/