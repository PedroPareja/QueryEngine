package es.pedropareja.database.generic;

import es.pedropareja.database.generic.QueryManager.ConnectionManager;
import es.pedropareja.database.generic.QueryManager.StatementGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBWriter implements AutoCloseable
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

    private Connection getConnection() throws SQLException
    {
        if(connectionManager == null)
            throw new SQLException("Connection Manager was not provided (null found)");

        if(currentConnection == null)
            currentConnection = connectionManager.getConnection();

        return currentConnection;
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
    public void close() throws Exception
    {
        if(currentConnection != null)
        {
            if(!committed)
            {
                if (autoCommit && !error)
                    commit();
                else if (autoRollback && error)
                    rollback();
            }

            currentConnection.close();
            currentConnection = null;
        }
    }

    private void executeWrite(StatementGenerator statementGenerator) throws SQLException
    {
        committed = false;

        try
        {
            Connection connection = getConnection();

            try (PreparedStatement statement = statementGenerator.createStatement(connection))
            {
                statement.execute();
            }
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
}
