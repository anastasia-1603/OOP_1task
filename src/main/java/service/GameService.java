package service;

import model.*;

import java.util.*;

public class GameService
{
    public Game createGame(List<Player> players)
    {
        Game game = new Game();
        game.setPlayers(players);
        return game;
    }

    private void dealCards(List<Player> players)
    {
        List<Card> deck = createDeck();
        Collections.shuffle(deck);
        int numberCards = deck.size() / players.size();
        for (Player player : players)
        {
            List<Card> cards = deck.subList(0, numberCards);
            player.acceptCards(cards);
            deck.removeAll(cards);
        }
        deck.clear();
    }

    private List<Card> createDeck()
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

    public void playGame(Game game)
    {
        int round = 1;
        while (!isPointsMore100(game.getPlayers()))
        {
            playRound(game);
            scorePoints(game.getPlayers());
            printRound(game, round++);
        }
        printWinner(findWinner(game.getPlayers()));
    }

    private void playRound(Game game)
    {
        dealCards(game.getPlayers());
        while (calcNumberAllCards(game.getPlayers()) > 0)
        {
            playTrick(game.getPlayers());
        }
    }

    private boolean isPointsMore100(List<Player> players)
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

    private Player findWinner(List<Player> players)
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

    private void playTrick(List<Player> players)
    {
        Map<Player, Card> trick = new HashMap<>();
        Player winner = new Player();
        Card maxCard = new Card(Rank.TWO, Suit.SPADES);
        for (Player player : players)
        {
            Card playerCard = player.getPlayerCards().get(0);
            trick.put(player, player.getPlayerCards().remove(0));
            if (playerCard.getRank().ordinal() > maxCard.getRank().ordinal())
            {
                maxCard = playerCard;
                winner = player;
            }
        }
        winner.setTrick(new ArrayList<>(trick.values()));
    }

    private int calcNumberAllCards(List<Player> players)
    {
        int numberCards = 0;
        for (Player player : players)
        {
            numberCards += player.getPlayerCards().size();
        }
        return numberCards;
    }

    private void scorePoints(List<Player> players)
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

    private void printRound(Game game, int numberRound)
    {
        System.out.printf("Round %d\n", numberRound);
        for (Player player : game.getPlayers())
        {
            System.out.printf("Player %s: cards ", player.getName());
            printCards(player.getPlayerCards());
            System.out.printf("\nNumber of points: %d", player.getPoints());
            System.out.println();
        }
    }

    private void printCards(List<Card> cards)
    {
        for (Card card : cards)
        {
            System.out.printf("%s_%s ",
                    card.getRank().toString().toLowerCase(),
                    card.getSuit().toString().charAt(0));
        }
    }

    private void printWinner(Player player)
    {
        System.out.printf("%s win!", player.getName());
    }
}
