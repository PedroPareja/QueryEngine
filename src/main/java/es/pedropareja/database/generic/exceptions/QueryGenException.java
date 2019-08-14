package es.pedropareja.database.generic.exceptions;

public class QueryGenException extends RuntimeException
{
    public QueryGenException(String message)
    {
        super(message);
    }

    public QueryGenException(String message, Throwable t)
    {
        super(message, t);
    }
}
