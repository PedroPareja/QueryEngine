package es.pedropareja.database.generic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class QueryParamsHelper
{
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

    public void setLong(Long value) throws SQLException
    {
        setParam((s,i,v)-> s.setLong(i, v), value);
    }

    public void setLong(Long value, boolean condition) throws SQLException
    {
        if(condition)
            setLong(value);
    }

    public void setAllLong(Collection<Long> collection) throws SQLException
    {
        if(collection != null)
            for(Long value: collection)
                setLong(value);
    }

    public void setAllLong(Collection<Long> collection, boolean condition) throws SQLException
    {
        if(condition)
            setAllLong(collection);
    }

    public void setString(String value) throws SQLException
    {
        setParam((s,i,v)-> s.setString(i, v), value);
    }

    public void setString(String value, boolean condition) throws SQLException
    {
        if(condition)
            setString(value);
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
