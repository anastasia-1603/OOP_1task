package model;

public enum Suit
{
    CLUBS ("\u2663"), // Colors.BLUE + "\u2663" + Colors.RESET
    DIAMONDS ("\u2666"), //Colors.CYAN + "\u2666" + Colors.RESET
    HEARTS ("\u2665"), //Colors.RED + "\u2665" + Colors.RESET
    SPADES ("\u2660"); //Colors.GREEN + "\u2660" + Colors.RESET

    public final String unicode;

    Suit(String unicode)
    {
        this.unicode = unicode;
    }
}
