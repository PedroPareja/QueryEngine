package es.pedropareja.database.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QueryManager
{
    private QueryManager() {}

    private static <T> T executeQuery(StatementGenerator statementGenerator, ConnectionManager connectionManager, StatementExecutor<T> executor)
                throws SQLException
    {
        if(connectionManager == null)
            throw new SQLException("Connection Manager was not provided (null found)");

        try
        (
            Connection connection = connectionManager.getConnection();
            PreparedStatement statement = statementGenerator.createStatement(connection);
        )
        {
            return executor.execute(statement);
        }
    }


    public static <T> T singleElementQuery(StatementGenerator statementGenerator, ConnectionManager connectionManager, ResultSetProcessor<T> rsProcessor) throws SQLException
    {
        return executeQuery(statementGenerator, connectionManager,
                statement ->
                {
                    try(ResultSet resultSet = statement.executeQuery())
                    {
                        return processNext(resultSet, rsProcessor);
                    }
                }
        );
    }

    public static <T> List<T> multiElementsQuery(StatementGenerator statementGenerator, ConnectionManager connectionManager, ResultSetProcessor<T> rsProcessor) throws SQLException
    {
        return executeQuery(statementGenerator, connectionManager,
                statement ->
                {
                   try(ResultSet resultSet = statement.executeQuery())
                   {
                       List<T> result = new ArrayList<>();
                       T element;
                       
                       while ((element = processNext(resultSet, rsProcessor)) != null)
                           result.add(element);

                       return result;
                   }
                }
        );
    }

    public static Void query(StatementGenerator statementGenerator, ConnectionManager connectionManager, ResultSetVoidProcessor rsProcessor) throws SQLException
    {
        return executeQuery(statementGenerator, connectionManager,
                statement ->
                {
                    try(ResultSet resultSet = statement.executeQuery())
                    {
                        while (resultSet.next())
                            rsProcessor.processNext(resultSet);
                    }

                    return null;
                }
        );
    }

    public static Void query(StatementGenerator statementGenerator, ConnectionManager connectionManager) throws SQLException
    {
        return executeQuery(statementGenerator, connectionManager,
                statement ->
                {
                    statement.execute();
                    return null;
                }
        );
    }

    private static <T> T processNext(ResultSet resultSet, ResultSetProcessor<T> rsProcessor) throws SQLException
    {
        if(!resultSet.next())
            return null;

        return rsProcessor.processNext(resultSet);
    }

    @FunctionalInterface
    public interface ConnectionManager
    {
        Connection getConnection() throws SQLException;
    }

    @FunctionalInterface
    public interface StatementGenerator
    {
        PreparedStatement createStatement(Connection connection) throws SQLException;
    }

    @FunctionalInterface
    public interface ResultSetProcessor<T>
    {
        T processNext(ResultSet resultSet) throws SQLException;
    }

    @FunctionalInterface
    public interface ResultSetVoidProcessor extends ResultSetProcessor<Void> {}

    @FunctionalInterface
    private interface StatementExecutor<T>
    {
        T execute(PreparedStatement statement) throws SQLException;
    }
}
