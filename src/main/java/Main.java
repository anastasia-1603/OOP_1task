import model.*;
import service.ConsoleService;
import service.GameService;
import java.util.List;


public class Main
{
     public static void main(String[] args) throws Exception
    {
        GameService gameService = new GameService();
        List<Player> players = gameService.createDefaultPlayers(4);
        Game game = gameService.createGame(players);
        gameService.playGame(game);
    }
}
