package service;

import model.*;

import java.util.*;

public class GameService
{
    public Game createGame(List<Player> players)
    {
        Game game = new Game(players);
        dealCards(game.getPlayers(), game.getAllCards());
        return game;
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

    public void playGame(Game game)
    {
        List<Player> players = game.getPlayers();
        //passCards(players, 3);
        int round = 1;
        do
        {
            playRound(game);
            printRound(game, round++);
        }
        while (game.isFinish());
    }

    public void playRound(Game game)
    {
        while (calcNumberAllCards(game.getPlayers()) > 0)
        {
            printTrick(playTrick(game.getPlayers()));
            scorePoints(game.getPlayers());
            game.setFinish(isPointsMore100(game.getPlayers()));
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

    private Map<Player, Card> playTrick(List<Player> players)
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
        return trick;
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

    public void printGame(Game game)
    {
        printPlayers(game.getPlayers());
    }

    private void printPoints(Player player)
    {
        System.out.printf("Points %s - %d", player.getName(), player.getPoints());
    }

    private void printRound(Game game, int numberRound)
    {
        System.out.printf("Round %d\n", numberRound);
        for (Player player : game.getPlayers())
        {
            System.out.println(player.getName());
            printCards(player.getPlayerCards());
            System.out.println("Number of points: " + player.getPoints());
            System.out.println();
        }
    }

    private void printTrick(Map<Player, Card> trick)
    {
        for (Player player : trick.keySet())
        {
            System.out.printf("%s: card %s_%s\n", player.getName(),
                    trick.get(player).getRank().toString().toLowerCase(),
                    trick.get(player).getSuit().toString().charAt(0));
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
