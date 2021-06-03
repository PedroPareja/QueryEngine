package es.pedropareja.database.generic;

import es.pedropareja.database.generic.QueryManager.ConnectionManager;
import es.pedropareja.database.generic.QueryManager.StatementGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBWriter implements AutoCloseable, ConnectionManager
{
    private final ConnectionManager connectionManager;
    private Connection currentConnection;
    private boolean autoCommit = true;
    private boolean autoRollback = true;
    private boolean committed = false;
    private boolean error = false;

    public DBWriter(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;
    }

    public DBWriter(ConnectionManager connectionManager, boolean autoCommit, boolean autoRollback)
    {
        this(connectionManager);
        this.autoCommit = autoCommit;
        this.autoRollback = autoRollback;
    }

    public static void executeWrite(StatementGenerator statementGenerator, ConnectionManager connectionManager) throws SQLException
    {
        try (DBWriter dbWriter = new DBWriter(connectionManager))
        {
            dbWriter.executeWrite(statementGenerator);
        }
    }

    public static <T> T executeWrite(StatementGenerator statementGenerator, StatementExecutor<T> statementExecutor,
            ConnectionManager connectionManager)
                throws SQLException
    {
        try (DBWriter dbWriter = new DBWriter(connectionManager))
        {
            return dbWriter.executeWrite(statementGenerator, statementExecutor);
        }
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        if(connectionManager == null)
            throw new SQLException("Connection Manager was not provided (null found)");

        if(currentConnection == null)
        {
            currentConnection = connectionManager.getConnection();
            currentConnection.setAutoCommit(false);
        }

        return currentConnection;
    }

    @Override
    public boolean isConnectionToKeptAlive()
    {
        return true;
    }

    public void rollback() throws SQLException
    {
        committed = true;

        if(currentConnection != null)
            currentConnection.rollback();
    }

    public void commit() throws SQLException
    {
        committed = true;

        if(currentConnection != null)
            currentConnection.commit();
    }

    public boolean isCommitted()
    {
        return committed;
    }

    @Override
    public void close() throws SQLException
    {
        if(currentConnection != null)
        {
            if(!committed)
            {
                if (autoCommit && !error)
                    commit();
                else if (autoRollback)
                    rollback();
            }

            currentConnection.close();
            currentConnection = null;
        }
    }

    private <T> T execute(StatementGenerator statementGenerator, StatementExecutor<T> statementExecutor) throws SQLException
    {
        committed = false;

        try
        {
            Connection connection = getConnection();

            try (PreparedStatement statement = statementGenerator.createStatement(connection))
            {
                if(statementExecutor != null)
                    return statementExecutor.execute(statement);
                else
                    statement.execute();
            }
        }
        catch (Exception e)
        {
            error = true;
            throw e;
        }

        return null;
    }

    public void executeWrite(StatementGenerator statementGenerator) throws SQLException
    {
        execute(statementGenerator, statement -> statement.execute());
    }

    public <T> T executeWrite(StatementGenerator statementGenerator, StatementExecutor<T> statementExecutor) throws SQLException
    {
        return execute(statementGenerator, statementExecutor);
    }

    public <R, E extends Exception> R executeTransaction(FunctionExecutor<R,E> functionExecutor) throws E
    {
        try
        {
            return functionExecutor.execute();
        }
        catch (Exception e)
        {
            error = true;
            throw e;
        }
    }

    public <E extends Exception> void executeTransaction(CodeExecutor<E> codeExecutor) throws E
    {
        try
        {
            codeExecutor.execute();
        }
        catch (Exception e)
        {
            error = true;
            throw e;
        }
    }

    public boolean isAutoCommit()
    {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit)
    {
        this.autoCommit = autoCommit;
    }

    public boolean isAutoRollback()
    {
        return autoRollback;
    }

    public void setAutoRollback(boolean autoRollback)
    {
        this.autoRollback = autoRollback;
    }

    public boolean isError()
    {
        return error;
    }

    public void resetError()
    {
        error = false;
    }

    @FunctionalInterface
    public interface StatementExecutor<T>
    {
        T execute(PreparedStatement statement) throws SQLException;
    }

    @FunctionalInterface
    public interface FunctionExecutor<R, E extends Exception>
    {
        R execute() throws E;
    }

    @FunctionalInterface
    public interface CodeExecutor<E extends Exception>
    {
        void execute() throws E;
    }
}
