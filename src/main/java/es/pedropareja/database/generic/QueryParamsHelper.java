package es.pedropareja.database.generic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class QueryParamsHelper
{
    private static final ParamSetter<Long> longSetter =  (s,i,v)-> s.setLong(i, v);
    private static final ParamSetter<String> stringSetter =  (s,i,v)-> s.setString(i, v);

    private final PreparedStatement statement;
    private int paramIndex = 1;

    public QueryParamsHelper(PreparedStatement statement)
    {
        this.statement = statement;
    }

    private <T> void setParam(ParamSetter<T> paramSetter, T value) throws SQLException
    {
        if(value != null)
            paramSetter.setParam(statement, paramIndex++, value);
    }

    private <T> void setParam(ParamSetter<T> paramSetter, T value, boolean condition) throws SQLException
    {
        if(condition)
            setParam(paramSetter, value);
    }

    private <T> void setAllParam(ParamSetter<T> paramSetter, Collection<T> collection) throws SQLException
    {
        if(collection != null)
            for(T element: collection)
                setParam(paramSetter, element);
    }

    private <T> void setAllParam(ParamSetter<T> paramSetter, Collection<T> collection, boolean condition) throws SQLException
    {
        if(condition)
            setAllParam(paramSetter, collection);
    }


    public void setLong(Long value) throws SQLException
    {
        setParam(longSetter, value);
    }

    public void setLong(Long value, boolean condition) throws SQLException
    {
        setParam(longSetter, value, condition);
    }

    public void setAllLong(Collection<Long> collection) throws SQLException
    {
        setAllParam(longSetter, collection);
    }

    public void setAllLong(Collection<Long> collection, boolean condition) throws SQLException
    {
        setAllParam(longSetter, collection, condition);
    }

    public void setString(String value) throws SQLException
    {
        setParam(stringSetter, value);
    }

    public void setString(String value, boolean condition) throws SQLException
    {
        setParam(stringSetter, value, condition);
    }

    public void setAllString(Collection<String> collection) throws SQLException
    {
        setAllParam(stringSetter, collection);
    }

    public void setAllString(Collection<String> collection, boolean condition) throws SQLException
    {
        setAllParam(stringSetter, collection, condition);
    }

    public <U, F extends Enum<?> & DBFilterProcessor<U>> void applyFilter(Class<F> filterProcessorType, U filter)
            throws SQLException
    {
        for(F filterRule: filterProcessorType.getEnumConstants())
            filterRule.getParamSetter().doAction(this, filter);
    }

    @FunctionalInterface
    private interface ParamSetter<T>
    {
        void setParam(PreparedStatement statement, int paramIndex, T value) throws SQLException;
    }
}
