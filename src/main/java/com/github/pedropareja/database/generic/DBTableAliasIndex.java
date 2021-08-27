package com.github.pedropareja.database.generic;

import java.util.HashMap;
import java.util.Map;

public class DBTableAliasIndex
{
    private final Map<DBTableWrapper, String> tableToAlias = new HashMap<>();
    private final Map<String, DBTable> aliasToTable = new HashMap<>();

    public void addIndex(DBTable table, String alias)
    {
        tableToAlias.put(new DBTableWrapper(table), alias);
        aliasToTable.put(alias, table);
    }

    public String getAlias(DBTable table)
    {
        return tableToAlias.get(new DBTableWrapper(table));
    }

    public DBTable getTable(String alias)
    {
        return aliasToTable.get(alias);
    }

    public int size()
    {
        return tableToAlias.size();
    }
}
