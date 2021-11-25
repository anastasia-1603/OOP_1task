import model.*;
import service.GameService;
import service.PlayerService;
import view.ConsoleView;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static Scanner sc = new Scanner(System.in);
    private static ConsoleView view = new ConsoleView();
    public static void main(String[] args) throws Exception
    {
        PlayerService ps = new PlayerService();
        List<Player> players = new LinkedList<>();
        players.add(new Player("1"));
        players.add(new Player("2"));
        players.add(new Player("3"));
        players.add(new Player("4"));
        Player curPl = players.get(0);

        /*GameService gameService = new GameService();
        List<Player> players = gameService.createDefaultPlayers(5);
        *//*players.add(new Player("1"));
        players.add(new Player("2"));
        players.add(new Player("3"));
        players.add(new Player("4"));*//*

        Game game = gameService.createGame(players);
        gameService.playGame(game);

        //gameService.printGame(game);*/
    }

    private static void play()
    {
        GameService gameService = new GameService();

        int state = -1;
        state = selectState();
        while (state != 0)
        {
            switch (state)
            {
                case 1:
                {
                    System.out.println("Enter number of players: ");
                    int n = sc.nextInt();
                    List<Player> players = createPlayers(n);
                    view.printPlayers(players);
                    Game game = new Game();
                    game.setPlayers(players);
                }
            }
        }
    }

    private static List<Player> createPlayers(int numberOfPlayers)
    {
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < numberOfPlayers; i++)
        {
            players.add(new Player(String.valueOf(i)));
        }
        return players;
    }

    private static int selectState()
    {
        System.out.println("""
                Select an action:
                (0) Finish the game
                (1) Create game
                (2) Play round
                (3) Play game
                Your choice: 
                """);
        return sc.nextInt();
    }
}
