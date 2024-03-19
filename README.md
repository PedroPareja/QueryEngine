# QueryEngine
Generic Java Database Query Engine.

## Documentation

Proper Documentation and Examples will be included (TODO)




### Hikari

You can use this library in combination with Hikari Connection Pool.

This class was removed to avoid Hikari dependency in the project:

```java
package com.github.pedropareja.database.generic;

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
```
