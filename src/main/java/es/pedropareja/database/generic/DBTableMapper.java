package es.pedropareja.database.generic;

import es.pedropareja.database.generic.exceptions.QueryGenException;

import java.util.*;

public abstract class DBTableMapper
{
    private static final int DEFAULT_SEARCH_DEPTH = 4;

    private static final Comparator<Class<? extends DBFieldInfo>> tableComparator = (a,b) -> a.hashCode() - b.hashCode();
    private Map<Class<? extends DBFieldInfo>, Map<Class<? extends DBFieldInfo>, TableMapperEntry>> searchMap = new TreeMap<>(tableComparator);

    private int maxSearchDepth = DEFAULT_SEARCH_DEPTH;

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

    public int getMaxSearchDepth()
    {
        return maxSearchDepth;
    }

    public void setMaxSearchDepth(int maxSearchDepth)
    {
        this.maxSearchDepth = maxSearchDepth;
    }

    public void addEntry(DBFieldInfo[] fieldsTable1, DBFieldInfo[] fieldsTable2)
    {
        if(fieldsTable1 == null || fieldsTable2 == null || fieldsTable1.length == 0 || fieldsTable2.length == 0)
            throw new QueryGenException("Field arrays must contain at least one element");

        if(!(fieldsTable1[0] instanceof Enum) || !(fieldsTable2[0] instanceof Enum))
            throw new QueryGenException("Field arrays must contain DBFieldInfo ENUM values");

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

    public List<FieldEquity> getEquities(Class<? extends DBFieldInfo> table1, Class<? extends DBFieldInfo> table2)
    {
        Map<Class<? extends DBFieldInfo>, TableMapperEntry> childrenMap = searchMap.get(table1);

        if(childrenMap == null)
            return null;

        TableMapperEntry entry = childrenMap.get(table2);

        if(entry == null)
            return null;

        return entry.getEquities();
    }

    public boolean existsEquity(Class<? extends DBFieldInfo> table1, Class<? extends DBFieldInfo> table2)
    {
        Map<Class<? extends DBFieldInfo>, TableMapperEntry> childrenMap = searchMap.get(table1);

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

    public Solution solve(Class<? extends DBFieldInfo> fromTable, Collection<Class<? extends DBFieldInfo>> tables)
    {
        Set<Class<? extends DBFieldInfo>> processedTables = new TreeSet<>((a,b)-> a.hashCode() - b.hashCode());
        List<TableJoin> tableJoins = new ArrayList<>();

        for(Class<? extends DBFieldInfo> table: tables)
        {
            Stack<Class<? extends DBFieldInfo>> path = solvePath(fromTable, table);
            List<TableJoin> pathJoins = getJoins(path);

            for(TableJoin tableJoin: pathJoins)
                if(!processedTables.contains(tableJoin.joinTable))
                {
                    tableJoins.add(tableJoin);
                    processedTables.add(tableJoin.joinTable);
                }
        }

        return new Solution(fromTable, tableJoins);
    }

    private TableJoin getTableJoin(Class<? extends DBFieldInfo> fromTable, Class<? extends DBFieldInfo> targetTable)
    {
        List<FieldEquity> equities = getEquities(fromTable, targetTable);
        return new TableJoin(targetTable, equities);
    }

    private List<TableJoin> getJoins(Stack<Class<? extends DBFieldInfo>> joinStack)
    {
        List<TableJoin> result = new ArrayList<>();

        for(int i=1; i < joinStack.size(); i++)
            result.add(getTableJoin(joinStack.get(i-1), joinStack.get(i)));

        return result;
    }

    private Stack<Class<? extends DBFieldInfo>> solvePath(Class<? extends DBFieldInfo> fromTable,
            Class<? extends DBFieldInfo> targetTable)
    {
        Queue<Stack<Class<? extends DBFieldInfo>>> searchQueue = new LinkedList<>();
        Stack<Class<? extends DBFieldInfo>> initialStack = new Stack<>();
        initialStack.push(fromTable);

        return solvePath(targetTable, searchQueue);
    }

    private Stack<Class<? extends DBFieldInfo>> solvePath(Class<? extends DBFieldInfo> targetTable,
            Queue<Stack<Class<? extends DBFieldInfo>>> searchQueue)
    {
        while(!searchQueue.isEmpty())
        {
            Stack<Class<? extends DBFieldInfo>> solutionStack = searchQueue.poll();

            if(solutionStack.peek() == targetTable)
                return solutionStack;

            if(solutionStack.size() < maxSearchDepth)
                for(Class<? extends DBFieldInfo> table: searchMap.keySet())
                    if(existsEquity(solutionStack.peek(), table))
                    {
                        Stack<Class<? extends DBFieldInfo>> newStack = new Stack<>();
                        newStack.addAll(solutionStack);
                        newStack.add(table);
                        searchQueue.add(newStack);
                    }
        }

        throw new QueryGenException("Could not solve table path to '" + targetTable.getName() + "'");
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

        @Override
        public boolean equals(Object obj)
        {
            if(!(obj instanceof FieldEquity))
                return false;

            FieldEquity instance = (FieldEquity) obj;

            return field1.equalsField(instance.field1) && field2.equalsField(instance.field2);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(field1, field2);
        }
    }

    public static class TableJoin
    {
        final Class<? extends DBFieldInfo> joinTable;
        final List<FieldEquity> fieldEquities;

        TableJoin(Class<? extends DBFieldInfo> joinTable, List<FieldEquity> fieldEquities)
        {
            this.joinTable = joinTable;
            this.fieldEquities = fieldEquities;
        }

        public Class<? extends DBFieldInfo> getJoinTable()
        {
            return joinTable;
        }

        public List<FieldEquity> getFieldEquities()
        {
            return fieldEquities;
        }

        @Override
        public boolean equals(Object obj)
        {
            if(!(obj instanceof TableJoin))
                return false;

            TableJoin instance = (TableJoin) obj;

            return joinTable.equals(instance.joinTable) && fieldEquities.equals(instance.fieldEquities);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(joinTable, fieldEquities);
        }
    }

    public static class Solution
    {
        final Class<? extends DBFieldInfo> fromTable;
        final List<TableJoin> tableJoins;

        Solution(Class<? extends DBFieldInfo> fromTable, List<TableJoin> tableJoins)
        {
            this.fromTable = fromTable;
            this.tableJoins = tableJoins;
        }

        public Class<? extends DBFieldInfo> getFromTable()
        {
            return fromTable;
        }

        public List<TableJoin> getTableJoins()
        {
            return tableJoins;
        }
    }
}
