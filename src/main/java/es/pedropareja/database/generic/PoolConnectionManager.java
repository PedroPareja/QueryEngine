package es.pedropareja.database.generic;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class PoolConnectionManager implements QueryManager.ConnectionManager
{
    public abstract Properties getConfig();

    protected final HikariDataSource dataSource;

    protected PoolConnectionManager()
    {
        this.dataSource = new HikariDataSource(new HikariConfig(getConfig()));
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
}
