package exceptions;

public class NullPlayersException extends Exception
{
    public NullPlayersException()
    {
        super("Players must not be null");
    }
}
