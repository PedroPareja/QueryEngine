package es.pedropareja.database.generic;

public interface DBDynamicTableInfo<T>
{
    default String getDatabase(T context) { return null; }
    default String getSchema(T context) { return null; }
    default String getTable(T context) { return null; }
}
