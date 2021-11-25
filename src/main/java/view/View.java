package view;

import model.Game;
import service.GameService;

public abstract class View
{
    GameService gameService;

    public abstract void print(Game game);
}
