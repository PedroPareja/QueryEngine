package es.pedropareja.database.generic.querygen.base;

import es.pedropareja.database.generic.DBDynamicTableInfo;
import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTableInfo;
import es.pedropareja.database.generic.exceptions.QueryGenException;

public class QGTableInfo
{
    private String database;
    private String schema;
    private String table;

    public static <T extends DBFieldInfo, U> QGTableInfo getTableInfo(Class<T> tableType, U context) throws QueryGenException
    {
        if(tableType.getEnumConstants().length == 0)
            throw new QueryGenException("Table definition must contain at least one field ('" + tableType.getName() + "')");

        return getTableInfo(tableType.getEnumConstants()[0], context);
    }

    public static <T extends DBFieldInfo, U> QGTableInfo getTableInfo(T field, U context)
    {
        QGTableInfo result = new QGTableInfo();

        if(field instanceof DBDynamicTableInfo)
        {
            DBDynamicTableInfo<U> dynamicTableInfo = (DBDynamicTableInfo<U>) field;
            result.setDatabase(dynamicTableInfo.getDatabase(context));
            result.setSchema(dynamicTableInfo.getSchema(context));
            result.setTable(dynamicTableInfo.getTable(context));
        }

        DBTableInfo tableInfo = field.getClass().getAnnotation(DBTableInfo.class);

        if(tableInfo == null)
            throw new QueryGenException("DBTableInfo annotation not present in " + field.getClass().getName());

        if(result.getDatabase() == null)
            result.setDatabase(tableInfo.database());

        if(result.getSchema() == null)
            result.setSchema(tableInfo.schema());

        if(result.getTable() == null)
            result.setTable(tableInfo.table());

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
}
