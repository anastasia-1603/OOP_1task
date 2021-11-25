package exceptions;

public class EmptyPlayersException extends Exception
{
    public EmptyPlayersException()
    {
        super("Players list must not be empty");
    }
}
