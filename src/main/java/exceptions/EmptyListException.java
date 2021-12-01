package exceptions;

public class EmptyListException extends Exception
{
    public EmptyListException()
    {
        super("List must not be empty");
    }
}
