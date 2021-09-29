package service;

import model.*;

import java.util.*;

public class GameService
{
    public GameService()
    {
    }

    private void dealCards(List<Player> players, List<Card> deck)
    {
        int numberCards = deck.size() / players.size();
        for (Player player : players)
        {
            List<Card> cards = deck.subList(0, numberCards);
            player.acceptCards(cards);
            deck.removeAll(cards);
        }
        deck.clear();
    }

    public Game createGame(List<Player> players) //todo мне что то не нравится
    {
        Game game = new Game(players);
        dealCards(game.getPlayers(), game.getAllCards());
        return game;
    }

    public void playGame(Game game) // todo
    {
        List<Player> players = game.getPlayers();
        passCards(players, 3);
        for (Player player : players)
        {
            while (player.getPoints() < 100)
            {
                playRound(players); //todo подозреваю что цикл неправильный
            }
        }
    }

    public void playRound(List<Player> players) //todo
    {

        Map<Player, Card> trick = new HashMap<>();
        Player winner = new Player();
        Card maxCard = new Card(Rank.TWO, Suit.SPADES);
        for (Player player : players)
        {
            Card playerCard = player.getPlayerCards().get(0);
            /*System.out.println(player.getName() + " " + playerCard.getRank() +
                    playerCard.getSuit() + playerCard.getRank().ordinal());*/
            trick.put(player, player.getPlayerCards().remove(0));
            if (playerCard.getRank().ordinal() > maxCard.getRank().ordinal())
            {
                maxCard = playerCard;
                winner = player;
            }
        }

        System.out.println("Выиграл " + winner.getName());

        /*boolean[] turn = new boolean[players.size()];
        for (int i = 0; i < players.size(); i++)
        {
            turn[i] = isFirstMove(players.get(i));
        }
        Suit mainSuit = Suit.CLUBS;
        Map<Card, Player> trick = new HashMap<>();
        for (int i = 0; i < players.size(); i++)
        {
            Card trickCard;
            if (turn[i])
            {
                trickCard = players.get(i).giveCard();
                mainSuit = trickCard.getSuit();
            }
            else
            {
                trickCard = makeMove(players.get(i), mainSuit);
            }
            trick.put(trickCard, players.get(i));
            findMaxCard(trick.keySet());
            trick.
        }*/
    }


    private boolean[] isNext(List<Player> players)
    {
        boolean[] turn = new boolean[players.size()];
        for (int i = 0; i < players.size(); i++)
        {
            turn[i] = isFirstMove(players.get(i));
        }
        return turn;
    }


    private Player findWinnerOfTrick(Map<Player, Card> trick)
    {
        List<Card> cards = new ArrayList<>(trick.values());
        Card maxCard = findMaxCard(cards);
        for (Player player : trick.keySet())
        {
            if (player.getPlayerCards().contains(maxCard))
            {
                return player;
            }
        }
        return null;
    }

    private Card findMaxCard(List<Card> cards) // todo exception
    {
        Card maxCard = cards.get(0);
        for (Card card : cards)
        {
            if (card.getRank().ordinal() > maxCard.getRank().ordinal())
            {
                maxCard = card;
            }
        }
        return maxCard;
    }

 /*   private Card makeFirstMove(List<Player> players) //todo
    {
        Card returnedCard = new Card();
        for (Player player : players)
        {
            returnedCard = isFirstMove(player) ?
                    player.giveCard(Rank.TWO, Suit.CLUBS) : makeMove(player, Suit.CLUBS);
        }
        return returnedCard;
    }*/

   /* private Map<Player, Card> giveTrick(List<Player> players, boolean isStartGame, Player winner) //todo
    {
        Map<Player, Card> trick = new HashMap<>();

        for (Player player : players)
        {
            if (isStartGame)
            {
                if (isFirstMove(player))
                {
                    trick.put(player, player.giveCard(Rank.TWO, Suit.CLUBS));
                } else
                {
                    makeMove(player, Suit.CLUBS);
                }
            } else
            {
                Suit mainSuit;
                while (isFirstMove(player))
                {
                    List<Card> allExceptHearts = findCardsWithSuit(player.getPlayerCards(),
                            Arrays.asList(Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES));
                    Card trickCard = allExceptHearts.remove(0);
                    trick.put(player, trickCard);
                    winner.getPlayerCards().remove(trickCard);
                }
                if ()
                {

                } else
                {
                    makeMove(player, );


                }
            }
            return trick;
        }
    }*/

/*    private Card leadFirst(Player player)
    {
        List<Card> allExceptHearts = findCardsWithSuit(player.getPlayerCards(),
                Arrays.asList(Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES));
        Card trickCard = allExceptHearts.remove(0);
        return player.getPlayerCards().remove(trickCard);
    }*/

    private int calculateTotalNumberCards(List<Player> players)
    {
        int numberCards = 0;
        for (Player player : players)
        {
            numberCards += player.getPlayerCards().size();
        }
        return numberCards;
    }

    private Card makeMove(Player player, Suit mainSuit)
    {
        List<Card> cardsWithSuit = findCardsWithSuit(player.getPlayerCards(), mainSuit);
        if (cardsWithSuit.size() != 0)
        {
            Card card = cardsWithSuit.remove(0);
            player.getPlayerCards().remove(card);
            return card;
        }
        else
        {
            return player.getPlayerCards().remove(0);
        }
    }

    public void passCards(List<Player> players, int numberCards)
    {
        for (int i = 0; i < players.size(); i++)
        {
            Player nextPlayer = players.get(i == (players.size() - 1) ? 0 : i + 1);
            nextPlayer.acceptCards(players.get(i).giveCards(numberCards));
        }
    }

    public List<Card> findCardsWithSuit(List<Card> cards, Suit suit)
    {
        List<Card> cardsWithSuit = new ArrayList<>();
        for (Card card : cards)
        {
            if (card.getSuit() == suit)
            {
                cardsWithSuit.add(card);
            }
        }
        return cardsWithSuit;
    }

    public List<Card> findCardsWithSuit(List<Card> cards, List<Suit> suits)
    {
        List<Card> cardsWithSuit = new ArrayList<>();
        for (Card card : cards)
        {
            if (suits.contains(card.getSuit()))
            {
                cardsWithSuit.add(card);
            }
        }
        return cardsWithSuit;
    }

    /*public boolean isFirstMove(Player player)
    {
        return player.getPlayerCards().contains(new Card(Rank.TWO, Suit.CLUBS));
    }*/

    private void scorePoints(Player player)
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

    public void printGame(Game game)
    {
        printPlayers(game.getPlayers());
    }

    private void printPlayers(List<Player> players)
    {
        for (Player player : players)
        {
            System.out.printf("\nPlayer %s :\n%d points, cards:\n", player.getName(), player.getPoints());
            for (Card card : player.getPlayerCards())
            {
                System.out.printf("%s %s ", card.getRank(), card.getSuit());
            }
            System.out.println("Number cards: " + player.getPlayerCards().size() + "\n");
        }

    }

}
