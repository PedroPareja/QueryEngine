package es.pedropareja.database.generic;

import java.util.Objects;

public class DBTableWrapper implements Comparable<DBTableWrapper>
{
    private final DBTable table;

    public DBTableWrapper(DBTable table)
    {
        this.table = table;
    }

    public DBTable getTable()
    {
        return table;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DBTableWrapper that = (DBTableWrapper) o;
        return Objects.equals(table.getId(), that.table.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(table.getId());
    }

    @Override
    public int compareTo(DBTableWrapper o)
    {
        return table.getId().compareTo(o.table.getId());
    }
}
