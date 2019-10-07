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

    private static <T> T executeQuery(StatementGenerator statementGenerator, ConnectionManager connectionManager,
            ResultSetExecutor<T> resultSetExecutor)
                throws SQLException
    {
        if(connectionManager == null)
            throw new SQLException("Connection Manager was not provided (null found)");

        try
        (
            Connection connection = connectionManager.getConnection();
            PreparedStatement statement = statementGenerator.createStatement(connection);
            ResultSet resultSet = statement.executeQuery()
        )
        {
            return resultSetExecutor.execute(resultSet);
        }
    }

    public static <T> T singleElementQuery(StatementGenerator statementGenerator, ConnectionManager connectionManager,
            ResultSetProcessor<T> rsProcessor)
                throws SQLException
    {
        return executeQuery(statementGenerator, connectionManager, resultSet -> processNext(resultSet, rsProcessor));
    }

    public static <T> List<T> multiElementsQuery(StatementGenerator statementGenerator, ConnectionManager connectionManager,
            ResultSetProcessor<T> rsProcessor)
                throws SQLException
    {
        return executeQuery(statementGenerator, connectionManager,
                resultSet ->
                {
                   List<T> result = new ArrayList<>();
                   T element;

                   while ((element = processNext(resultSet, rsProcessor)) != null)
                       result.add(element);

                   return result;
                }
        );
    }

    public static Void query(StatementGenerator statementGenerator, ConnectionManager connectionManager,
            ResultSetVoidProcessor rsProcessor)
                throws SQLException
    {
        return executeQuery(statementGenerator, connectionManager,
                resultSet ->
                {
                    while (resultSet.next())
                        rsProcessor.processNext(resultSet);

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
    public interface ResultSetVoidProcessor extends ResultSetProcessor<Void>
    {
        void processNextVoid(ResultSet resultSet) throws SQLException;

        @Override
        default Void processNext(ResultSet resultSet) throws SQLException
        {
            processNextVoid(resultSet);
            return null;
        }
    }
    
    @FunctionalInterface
    private interface ResultSetExecutor <T>
    {
        T execute(ResultSet resultSet) throws SQLException;
    }
}
