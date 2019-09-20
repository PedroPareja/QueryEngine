package es.pedropareja.database.generic;

import es.pedropareja.database.generic.DBFilterProcessor.DBFilterParamSetter;
import es.pedropareja.database.generic.exceptions.QueryGenException;

import java.util.*;

public abstract class DBTableMapper
{
    private static final int DEFAULT_SEARCH_DEPTH = 4;

    private static final Comparator<Class<Enum<? extends DBFieldInfo>>> tableComparator = (a,b) -> a.hashCode() - b.hashCode();
    private Map<Class<Enum<? extends DBFieldInfo>>, Map<Class<Enum<? extends DBFieldInfo>>, TableMapperEntry>> searchMap = new TreeMap<>(tableComparator);

    private int maxSearchDepth = DEFAULT_SEARCH_DEPTH;

    public DBTableMapper() {}

    public <T extends Enum<?> & DBFieldInfo> DBTableMapper(T[][][] fieldsMapping)
    {
        for(T[][] entry: fieldsMapping)
        {
            if(entry.length != 2)
                throw new QueryGenException("Entry array must contain 2 elements");

            addEntry(entry[0], entry[1]);
        }
    }

    public int getMaxSearchDepth()
    {
        return maxSearchDepth;
    }

    public void setMaxSearchDepth(int maxSearchDepth)
    {
        this.maxSearchDepth = maxSearchDepth;
    }

    public <T extends Enum<?> & DBFieldInfo> void addEntry(T[] fieldsTable1, T[] fieldsTable2)
    {
        if(fieldsTable1 == null || fieldsTable2 == null || fieldsTable1.length == 0 || fieldsTable2.length == 0)
            throw new QueryGenException("Field arrays must contain at least one element");

        final Class<Enum<? extends DBFieldInfo>> table1 = (Class<Enum<? extends DBFieldInfo>>) fieldsTable1[0].getClass();
        final Class<Enum<? extends DBFieldInfo>> table2 = (Class<Enum<? extends DBFieldInfo>>) fieldsTable2[0].getClass();

        TableMapperEntry entry = new TableMapperEntry(fieldsTable1, fieldsTable2);

        getChildrenMap(table1).put(table2, entry);
        getChildrenMap(table2).put(table1, entry);
    }

    private Map<Class<Enum<? extends DBFieldInfo>>, TableMapperEntry> getChildrenMap(Class<Enum<? extends DBFieldInfo>> key)
    {
        Map<Class<Enum<? extends DBFieldInfo>>, TableMapperEntry> result = searchMap.get(key);

        if(result == null)
        {
            result = new TreeMap<>(tableComparator);
            searchMap.put(key, result);
        }

        return result;
    }

    public List<FieldEquity> getEquities(Class<Enum<? extends DBFieldInfo>> table1, Class<Enum<? extends DBFieldInfo>> table2)
    {
        Map<Class<Enum<? extends DBFieldInfo>>, TableMapperEntry> childrenMap = searchMap.get(table1);

        if(childrenMap == null)
            return null;

        TableMapperEntry entry = childrenMap.get(table2);

        if(entry == null)
            return null;

        return entry.getEquities();
    }

    public boolean existsEquity(Class<Enum<? extends DBFieldInfo>> table1, Class<Enum<? extends DBFieldInfo>> table2)
    {
        Map<Class<Enum<? extends DBFieldInfo>>, TableMapperEntry> childrenMap = searchMap.get(table1);

        if(childrenMap == null)
            return false;

        return childrenMap.get(table2) != null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof DBTableMapper))
            return false;

        DBTableMapper tableMapper = (DBTableMapper) obj;

        return searchMap.equals(tableMapper.searchMap);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(searchMap);
    }

    public <T extends Enum<?> & DBFieldInfo> Solution solve(Class<T> fromTable, List<Class<T>> tables)
    {

    }

    private <T extends Enum<?> & DBFieldInfo> Stack<Class<T>> solvePath(Class<T> fromTable, Class<T> targetTable)
    {

    }

    private <T extends Enum<? extends DBFieldInfo>> Stack<Class<T>> solvePath(Class<T> targetTable, Queue<Stack<Class<T>>> searchQueue)
    {
        while(!searchQueue.isEmpty())
        {
            Stack<Class<T>> solutionStack = searchQueue.poll();

            if(solutionStack.peek() == targetTable)
                return solutionStack;

            if(solutionStack.size() < maxSearchDepth)
                for(Class<Enum<? extends DBFieldInfo>> table: searchMap.keySet())
                    if(existsEquity(solutionStack.peek(), table))
                    {
                        Stack<Class<T>> newStack = new Stack<>();
                        newStack.addAll(solutionStack);
                        newStack.add((T)table);

                    }

        }

        return null;
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

        public List<FieldEquity> getEquities()
        {
            List<FieldEquity> result = new ArrayList<>();

            for(int i=0; i < nexusFieldsTable1.length; i++)
                result.add(new FieldEquity(nexusFieldsTable1[i], nexusFieldsTable2[i]));

            return result;
        }

        private static boolean compareArrays(DBFieldInfo[] array1, DBFieldInfo[] array2)
        {
            if(array1.length != array2.length)
                return false;

            for(int i=0; i < array1.length; i++)
                if(!array1[i].equalsField(array2[i]))
                    return false;

            return true;
        }

        @Override
        public boolean equals(Object obj)
        {
            if(!(obj instanceof TableMapperEntry))
                return false;

            TableMapperEntry tmEntry = (TableMapperEntry) obj;

            return compareArrays(nexusFieldsTable1, tmEntry.nexusFieldsTable1)
                    && compareArrays(nexusFieldsTable2, tmEntry.nexusFieldsTable2);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(nexusFieldsTable1, nexusFieldsTable2);
        }
    }

    public static class FieldEquity
    {
        private final DBFieldInfo field1;
        private final DBFieldInfo field2;

        public FieldEquity(DBFieldInfo field1, DBFieldInfo field2)
        {
            this.field1 = field1;
            this.field2 = field2;
        }

        public DBFieldInfo getField1()
        {
            return field1;
        }

        public DBFieldInfo getField2()
        {
            return field2;
        }
    }

    public static class TableJoin
    {
        final Class<? extends Enum<? extends DBFieldInfo>> joinTable;
        final List<FieldEquity> fieldEquities;

        TableJoin(Class<? extends Enum<? extends DBFieldInfo>> joinTable, List<FieldEquity> fieldEquities)
        {
            this.joinTable = joinTable;
            this.fieldEquities = fieldEquities;
        }

        public Class<? extends Enum<? extends DBFieldInfo>> getJoinTable()
        {
            return joinTable;
        }

        public List<FieldEquity> getFieldEquities()
        {
            return fieldEquities;
        }
    }

    public static class Solution
    {
        final Class<? extends Enum<? extends DBFieldInfo>> fromTable;
        final List<TableJoin> tableJoins;

        Solution(Class<? extends Enum<? extends DBFieldInfo>> fromTable, List<TableJoin> tableJoins)
        {
            this.fromTable = fromTable;
            this.tableJoins = tableJoins;
        }

        public Class<? extends Enum<? extends DBFieldInfo>> getFromTable()
        {
            return fromTable;
        }

        public List<TableJoin> getTableJoins()
        {
            return tableJoins;
        }
    }
}
