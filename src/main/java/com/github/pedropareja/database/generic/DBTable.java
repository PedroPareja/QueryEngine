package com.github.pedropareja.database.generic;

import java.util.Comparator;
import java.util.Objects;

public interface DBTable
{
    Comparator<DBTable> tableComparator = (a,b) -> a.getId().hashCode() - b.getId().hashCode();

    DBFieldInfo[] getFields();

    default String getDatabase() { return null; }
    default String getSchema() { return null; }
    default String getTable() { return null; }

    default String getId() { return this.getClass().getName(); }

    default boolean equalsTable(DBTable otherTable)
    {
        return Objects.equals(getId(), otherTable.getId());
    }
}
