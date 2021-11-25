package view;

import model.Card;
import model.Game;
import model.Player;
import service.GameService;

import java.util.List;
import java.util.Scanner;

public class ConsoleView extends View
{
    GameService gameService;

    private Scanner sc = new Scanner(System.in);
    private int stateMenu;

    @Override
    public void print(Game game)
    {
        selectState();
    }

    private void printMenu()
    {
        System.out.println("""
                Select an action:
                (0) Finish the game
                (1) Play round
                (2) Play game
                Your choice: 
                """);
    }

    private void setStateMenu(int stateMenu)
    {
        this.stateMenu = stateMenu;
    }

    public void selectState()
    {
        printMenu();
        setStateMenu(sc.nextInt());
    }

    public void printRound(Game game)
    {

    }

    public void printRound(Game game, int numberRound)
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

    public void printCards(List<Card> cards)
    {
        for (Card card : cards)
        {
            System.out.printf("%s_%s ",
                    card.getRank().toString().toLowerCase(),
                    card.getSuit().toString().charAt(0));
        }
    }

    public void printPlayers(List<Player> players)
    {
        for (Player player : players)
        {
            System.out.printf("Player %s: cards ", player.getName());
            printCards(player.getPlayerCards());
            System.out.printf("\nNumber of points: %d", player.getPoints());
        }
    }

}
