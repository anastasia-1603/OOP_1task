import model.Card;
import model.Game;
import model.Player;
import service.GameService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        GameService gameService = new GameService();
        List<Player> players = new LinkedList<>();
        players.add(new Player(new ArrayList<Card>(), "1"));
        players.add(new Player(new ArrayList<Card>(), "2"));
        players.add(new Player(new ArrayList<Card>(), "3"));
        players.add(new Player(new ArrayList<Card>(), "4"));
        Game game = gameService.createGame(players);
        gameService.playGame(game);

        //gameService.printGame(game);


    }
}
