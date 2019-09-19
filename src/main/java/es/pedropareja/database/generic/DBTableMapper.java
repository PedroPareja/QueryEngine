package es.pedropareja.database.generic;

import es.pedropareja.database.generic.exceptions.QueryGenException;
import javafx.util.Pair;

import java.util.*;

public abstract class DBTableMapper
{
    private static final Comparator<Class<? extends DBFieldInfo>> tableComparator = (a,b) -> a.hashCode() - b.hashCode();
    private Map<Class<? extends DBFieldInfo>, Map<Class<? extends DBFieldInfo>, TableMapperEntry>> searchMap = new TreeMap<>(tableComparator);

    public DBTableMapper() {}

    public DBTableMapper(DBFieldInfo[][][] fieldsMapping)
    {
        for(DBFieldInfo[][] entry: fieldsMapping)
        {
            if(entry.length != 2)
                throw new QueryGenException("Entry array must contain 2 elements");

            addEntry(entry[0], entry[1]);
        }
    }

    public void addEntry(DBFieldInfo[] fieldsTable1, DBFieldInfo[] fieldsTable2)
    {
        if(fieldsTable1 == null || fieldsTable2 == null || fieldsTable1.length == 0 || fieldsTable2.length == 0)
            throw new QueryGenException("Field arrays must contain at least one element");

        final Class<? extends DBFieldInfo> table1 = fieldsTable1[0].getClass();
        final Class<? extends DBFieldInfo> table2 = fieldsTable2[0].getClass();

        TableMapperEntry entry = new TableMapperEntry(fieldsTable1, fieldsTable2);

        getChildrenMap(table1).put(table2, entry);
        getChildrenMap(table2).put(table1, entry);
    }

    private Map<Class<? extends DBFieldInfo>, TableMapperEntry> getChildrenMap(Class<? extends DBFieldInfo> key)
    {
        Map<Class<? extends DBFieldInfo>, TableMapperEntry> result = searchMap.get(key);

        if(result == null)
        {
            result = new TreeMap<>(tableComparator);
            searchMap.put(key, result);
        }

        return result;
    }

    public List<Pair<DBFieldInfo, DBFieldInfo>> getEquities(Class<? extends DBFieldInfo> table1, Class<? extends DBFieldInfo> table2)
    {
        Map<Class<? extends DBFieldInfo>, TableMapperEntry> childrenMap = searchMap.get(table1);

        if(childrenMap == null)
            return null;

        TableMapperEntry entry = childrenMap.get(table2);

        if(entry == null)
            return null;

        return entry.getEquities();
    }

    public static class TableMapperEntry
    {
        final DBFieldInfo[] nexusFieldsTable1;
        final DBFieldInfo[] nexusFieldsTable2;

        TableMapperEntry(DBFieldInfo[] nexusFieldsTable1, DBFieldInfo[] nexusFieldsTable2)
        {
            if(nexusFieldsTable1.length != nexusFieldsTable2.length)
                throw new QueryGenException("Fields arrays must be of the same size");

            this.nexusFieldsTable1 = nexusFieldsTable1;
            this.nexusFieldsTable2 = nexusFieldsTable2;
        }

        public List<Pair<DBFieldInfo, DBFieldInfo>> getEquities()
        {
            List<Pair<DBFieldInfo, DBFieldInfo>> result = new ArrayList<>();

            for(int i=0; i < nexusFieldsTable1.length; i++)
                result.add(new Pair(nexusFieldsTable1[i], nexusFieldsTable2[i]));

            return result;
        }
    }
}
