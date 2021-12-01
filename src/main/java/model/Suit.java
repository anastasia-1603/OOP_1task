package model;

public enum Suit
{
    CLUBS (Colors.BLUE + "\u2663" + Colors.RESET),
    DIAMONDS (Colors.CYAN + "\u2666" + Colors.RESET),
    HEARTS (Colors.RED + "\u2665" + Colors.RESET),
    SPADES (Colors.GREEN + "\u2660" + Colors.RESET);

    public final String unicode;

    Suit(String unicode)
    {
        this.unicode = unicode;
    }
}
