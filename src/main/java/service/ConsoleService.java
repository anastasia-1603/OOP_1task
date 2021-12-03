package service;

import model.Card;
import model.Colors;
import model.Player;

import java.util.List;

public class ConsoleService
{
    public void printWinner(Player player)
    {
        System.out.printf(Colors.GREEN_BOLD_BRIGHT + "%s win!" + Colors.RESET, player.getName());
    }

    public void printMove(Card move)
    {
        System.out.printf("Makes a move with %s\n", move);
    }

    public void printWhoTakesTrick(Card maxCard, Player winner)
    {
        System.out.printf("Max card - %s, a player %s takes a trick.\n",
                maxCard, Colors.PURPLE_BOLD + winner.getName() + Colors.RESET);
        System.out.println();
    }

    public void printCards(List<Card> cards)
    {
        for (Card card : cards)
        {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    public void printRound(int numberRound)
    {
        System.out.printf(Colors.GREEN_UNDERLINED+"Round %d\n"+Colors.RESET, numberRound);
    }

    public void printPlayerCards(Player player)
    {
        System.out.printf("Player %s cards: ", Colors.PURPLE_BOLD + player.getName() + Colors.RESET);
        printCards(player.getPlayerCards());
    }

    public void printPoints(List<Player> players)
    {
        for (Player player : players)
        {
            System.out.printf("Player %s points: %d", Colors.PURPLE_BOLD + player.getName() + Colors.RESET, player.getPoints());
            System.out.println();
        }
    }
}
