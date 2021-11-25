package service;

import exceptions.EmptyPlayersException;
import exceptions.NullPlayersException;
import model.*;

import java.util.*;

public class GameService
{
    private final PlayerService playerService = new PlayerService();
    private final Scanner scn = new Scanner(System.in);

    public GameService()
    {
    }

    public List<Player> createDefaultPlayers(int numberOfPlayers)
    {
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < numberOfPlayers; i++)
        {
            players.add(new Player(String.valueOf(i)));
        }
        return players;
    }

    public Game createGame(List<Player> players)
    {
        Game game = new Game();
        if (players != null)
        {
            game.setPlayers(players);
        }
        else
        {
            throw new NullPointerException();
        }
        return game;
    }

    public void playGame(Game game) throws Exception
    {
        int round = 1;
        while (!playerService.isPointsMore100(game.getPlayers()))
        {
            playRound(game);
            playerService.scorePoints(game.getPlayers());
            printRound(game, round++);
        }
        printWinner(playerService.findWinner(game.getPlayers()));
    }

    private void playRound(Game game) throws Exception
    {
        playerService.dealCards(game.getPlayers());
        while (playerService.calcNumberAllCards(game.getPlayers()) > 0)
        {
           // playTrick(game.getPlayers());
        }
    }

    private boolean isFirstTrick = true;
    /*private void playTrick(List<Player> players) throws Exception
    {
        Map<Player, Card> trick = new HashMap<>();
        Player winner = new Player();
        Card maxCard = new Card(Rank.TWO, Suit.SPADES);

        if (players != null)
        { if (players.size() != 0)
            {
                Card playerCard;
                Player currentPlayer;
                if (isFirstTrick)
                {
                    currentPlayer = findWhoStart(players);
                    if (currentPlayer != null)
                    {
                        playerCard = playerService.removeCard(currentPlayer, new Card(Rank.TWO, Suit.CLUBS));
                    }
                    isFirstTrick = !isFirstTrick;
                }
                else
                {
                    for (Player player : players)
                    {
                        playerCard = player.getPlayerCards().get(0);

                    }
                }
                trick.put(currentPlayer, currentPlayer.getPlayerCards().remove(0));
                if (playerCard.getRank().ordinal() > maxCard.getRank().ordinal())
                {
                    maxCard = playerCard;
                    winner = currentPlayer;
                }
                winner.setTrick(new ArrayList<>(trick.values()));
            }
            else {
                throw new EmptyPlayersException();
            } } else {
            throw new NullPlayersException(); }
    }*/


    private Player findWhoStart(List<Player> players)
    {
        Card requiredCard = new Card(Rank.TWO, Suit.CLUBS);
        for (Player player : players)
        {
            if (player.getPlayerCards().contains(requiredCard))
            {
                return player;
            }
        }
        return null;
    }

    private void printRound(Game game, int numberRound)
    {
        System.out.printf("Round %d\n", numberRound);
        for (Player player : game.getPlayers())
        {
            System.out.printf("Player %s: cards", player.getName());
            printCards(player.getPlayerCards());
            System.out.printf("\nNumber of points: %d", player.getPoints());
            System.out.println();
        }
    }

/*    private int readNumberOfPlayers()
    {
        System.out.println("Enter the number of players: ");
        return scn.nextInt();
    }*/

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
