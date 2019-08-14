package es.pedropareja.database.generic;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class PoolConnectionManager implements QueryManager.ConnectionManager
{
    public abstract HikariConfig getConfig();

    protected final HikariDataSource dataSource;

    protected PoolConnectionManager()
    {
        this.dataSource = new HikariDataSource(getConfig());
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
}
