package es.pedropareja.database.generic.querygen.base;

import es.pedropareja.database.generic.DBDynamicTableInfo;
import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTable;
import es.pedropareja.database.generic.DBTableInfo;
import es.pedropareja.database.generic.exceptions.QueryGenException;

public class QGTableInfo
{
    private String database;
    private String schema;
    private String table;

    public static <T extends DBFieldInfo, U> QGTableInfo getTableInfo(T field, U context)
    {
        return getTableInfo(field.getParentTable(), context);
    }

    public static <U> QGTableInfo getTableInfo(DBTable table, U context)
    {
        QGTableInfo result = new QGTableInfo();

        if(table instanceof DBDynamicTableInfo && context != null)
        {
            DBDynamicTableInfo<U> dynamicTableInfo = (DBDynamicTableInfo<U>) table;
            result.setDatabase(dynamicTableInfo.getDatabase(context));
            result.setSchema(dynamicTableInfo.getSchema(context));
            result.setTable(dynamicTableInfo.getTable(context));
        }

        assignProperties(result, ()-> table.getDatabase(), ()-> table.getSchema(), ()-> table.getTable());

        DBTableInfo tableInfo = table.getClass().getAnnotation(DBTableInfo.class);

        if(tableInfo != null)
            assignProperties(result, ()-> tableInfo.database(), ()-> tableInfo.schema(), ()-> tableInfo.table());

        if(result.getTable() == null)
            throw new QueryGenException("Table name info was not found for " + table.getClass().getName() + " id='" + table.getId() + "'");

        return result;
    }

    public String getDatabase()
    {
        return database;
    }

    public void setDatabase(String database)
    {
        this.database = database;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema(String schema)
    {
        this.schema = schema;
    }

    public String getTable()
    {
        return table;
    }

    public void setTable(String table)
    {
        this.table = table;
    }

    private static <T> void assignProperties(QGTableInfo result,
            DBPropertyGetter databaseGetter, DBPropertyGetter schemaGetter,
            DBPropertyGetter tableGetter)
    {
        if(result.getDatabase() == null)
            result.setDatabase(databaseGetter.getProperty());

        if(result.getSchema() == null)
            result.setSchema(schemaGetter.getProperty());

        if(result.getTable() == null)
            result.setTable(tableGetter.getProperty());
    }

    @FunctionalInterface
    private interface DBPropertyGetter
    {
        String getProperty();
    }
}
