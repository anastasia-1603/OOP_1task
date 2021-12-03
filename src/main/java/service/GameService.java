package service;

import exceptions.EmptyListException;
import exceptions.NullPlayersException;
import model.*;

import java.util.*;

public class GameService
{
    private final PlayerService playerService = new PlayerService();
    private final ConsoleService consoleService = new ConsoleService();

    public List<Player> createDefaultPlayers(int numberOfPlayers) throws Exception
    {
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < numberOfPlayers; i++)
        {
            players.add(new Player(String.valueOf(i)));
        }
        playerService.dealCards(players);
        return players;
    }

    public Game createGame(List<Player> players) throws NullPlayersException
    {
        Game game = new Game();
        if (players != null)
        {
            game.setPlayers(players);
        }
        else
        {
            throw new NullPlayersException();
        }
        return game;
    }

    public void playGame(Game game) throws Exception
    {
        int round = 1;
        while (!playerService.isPointsMore100(game.getPlayers()))
        {
            consoleService.printRound(round++);
            playTricks(game);
            playerService.scorePoints(game.getPlayers());
            consoleService.printPoints(game.getPlayers());
            playerService.dealCards(game.getPlayers());
        }
        consoleService.printWinner(playerService.findWinner(game.getPlayers()));
    }

    private void playRound(Game game) throws Exception
    {
        playTricks(game);
    }

    private void playTricks(Game game) throws Exception
    {
        List<Player> players = game.getPlayers();
        Map<Player, Card> trick = new HashMap<>();
        Player winner = new Player();
        Card maxCard = new Card(Rank.TWO, Suit.CLUBS);

        if (players != null)
        {
            if (players.size() != 0)
            {
                while (playerService.calcNumberAllCards(game.getPlayers()) > 0)
                {
                    for (Player player : players)
                    {
                        consoleService.printCards(player);
                        consoleService.printCards(player.getPlayerCards());
                        Card move = playerService.makeAMove(player, maxCard);
                        consoleService.printMove(move);
                        trick.put(player, move);
                    }
                    for (Map.Entry<Player, Card> entry : trick.entrySet())
                    {
                        if (entry.getValue().compareTo(maxCard) > 0)
                        {
                            maxCard = entry.getValue();
                            winner = entry.getKey();
                        }
                    }
                    consoleService.printWhoTakesTrick(maxCard, winner);
                    winner.setTrick(trick.values());
                    if (winner.getPlayerCards().size() != 0)
                    {
                        maxCard = playerService.getCard(winner, 0);
                    }
                    trick.clear();
                }
            }
            else { throw new EmptyListException(); }
        } else { throw new NullPlayersException(); }
    }

    public void playTrick(List<Player> players)
    {

    }
}
