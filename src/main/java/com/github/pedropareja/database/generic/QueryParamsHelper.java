package com.github.pedropareja.database.generic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collection;

public class QueryParamsHelper
{
    protected static final ParamSetter<Long> longSetter =  (s,i,v) -> s.setLong(i, v);
    protected static final ParamSetter<Integer> intSetter = (s,i,v) -> s.setInt(i, v);
    protected static final ParamSetter<Short> shortSetter = (s,i,v) -> s.setShort(i, v);
    protected static final ParamSetter<String> stringSetter =  (s,i,v) -> s.setString(i, v);
    protected static final ParamSetter<BigDecimal> bigDecimalSetter = (s,i,v) -> s.setBigDecimal(i, v);
    protected static final ParamSetter<Boolean> booleanSetter = (s,i,v) -> s.setBoolean(i, v);
    protected static final ParamSetter<Timestamp> timestampSetter = (s,i,v) -> s.setTimestamp(i, v);

    protected static final int TYPE_LONG = Types.NUMERIC;
    protected static final int TYPE_INT = Types.NUMERIC;
    protected static final int TYPE_SHORT = Types.NUMERIC;
    protected static final int TYPE_STRING = Types.VARCHAR;
    protected static final int TYPE_BIGDECIMAL = Types.NUMERIC;
    protected static final int TYPE_BOOLEAN = Types.NUMERIC;
    protected static final int TYPE_TIMESTAMP = Types.TIMESTAMP;
    

    protected final PreparedStatement statement;
    protected int paramIndex = 1;

    public QueryParamsHelper(PreparedStatement statement)
    {
        this.statement = statement;
    }

    protected <T> void setParam(ParamSetter<T> paramSetter, int sqlType, T value) throws SQLException
    {
        if(value != null)
            paramSetter.setParam(statement, paramIndex++, value);
        else
            setNull(sqlType);
    }

    protected <T> void setParam(ParamSetter<T> paramSetter, int sqlType, T value, boolean condition) throws SQLException
    {
        if(condition)
            setParam(paramSetter, sqlType, value);
    }

    protected <T> void setAllParam(ParamSetter<T> paramSetter, int sqlType, Collection<T> collection) throws SQLException
    {
        if(collection != null)
            for(T element: collection)
                setParam(paramSetter, sqlType, element);
    }

    protected <T> void setAllParam(ParamSetter<T> paramSetter, int sqlType, Collection<T> collection, boolean condition) throws SQLException
    {
        if(condition)
            setAllParam(paramSetter, sqlType, collection);
    }

    public void setNull(int sqlType) throws SQLException
    {
        statement.setNull(paramIndex++, sqlType);
    }

    public void setNull(int sqlType, boolean condition) throws SQLException
    {
        if(condition)
            setNull(sqlType);
    }

    public void setAllNull(int count, int sqlType) throws SQLException
    {
        for(int i=0; i < count; i++)
            setNull(sqlType);
    }

    public void setAllNull(int count, int sqlType, boolean condition) throws SQLException
    {
        if(condition)
            setAllNull(count, sqlType);
    }

    public void setLong(Long value) throws SQLException
    {
        setParam(longSetter, TYPE_LONG, value);
    }

    public void setLong(Long value, boolean condition) throws SQLException
    {
        setParam(longSetter, TYPE_LONG, value, condition);
    }

    public void setAllLong(Collection<Long> collection) throws SQLException
    {
        setAllParam(longSetter, TYPE_LONG, collection);
    }

    public void setAllLong(Collection<Long> collection, boolean condition) throws SQLException
    {
        setAllParam(longSetter, TYPE_LONG, collection, condition);
    }

    public void setInt(Integer value) throws SQLException
    {
        setParam(intSetter, TYPE_INT, value);
    }

    public void setInt(Integer value, boolean condition) throws SQLException
    {
        setParam(intSetter, TYPE_INT, value, condition);
    }

    public void setAllInt(Collection<Integer> collection) throws SQLException
    {
        setAllParam(intSetter, TYPE_INT, collection);
    }

    public void setAllInt(Collection<Integer> collection, boolean condition) throws SQLException
    {
        setAllParam(intSetter, TYPE_INT, collection, condition);
    }

    public void setShort(Short value) throws SQLException
    {
        setParam(shortSetter, TYPE_SHORT, value);
    }

    public void setShort(Short value, boolean condition) throws SQLException
    {
        setParam(shortSetter, TYPE_SHORT, value, condition);
    }

    public void setAllShort(Collection<Short> collection) throws SQLException
    {
        setAllParam(shortSetter, TYPE_SHORT, collection);
    }

    public void setAllShort(Collection<Short> collection, boolean condition) throws SQLException
    {
        setAllParam(shortSetter, TYPE_SHORT, collection, condition);
    }

    public void setString(String value) throws SQLException
    {
        setParam(stringSetter, TYPE_STRING, value);
    }

    public void setString(String value, boolean condition) throws SQLException
    {
        setParam(stringSetter, TYPE_STRING, value, condition);
    }

    public void setAllString(Collection<String> collection) throws SQLException
    {
        setAllParam(stringSetter, TYPE_STRING, collection);
    }

    public void setAllString(Collection<String> collection, boolean condition) throws SQLException
    {
        setAllParam(stringSetter, TYPE_STRING, collection, condition);
    }

    public void setBigDecimal(BigDecimal value) throws SQLException
    {
        setParam(bigDecimalSetter, TYPE_BIGDECIMAL, value);
    }

    public void setBigDecimal(BigDecimal value, boolean condition) throws SQLException
    {
        setParam(bigDecimalSetter, TYPE_BIGDECIMAL, value, condition);
    }

    public void setAllBigDecimal(Collection<BigDecimal> collection) throws SQLException
    {
        setAllParam(bigDecimalSetter, TYPE_BIGDECIMAL, collection);
    }

    public void setAllBigDecimal(Collection<BigDecimal> collection, boolean condition) throws SQLException
    {
        setAllParam(bigDecimalSetter, TYPE_BIGDECIMAL, collection, condition);
    }

    public void setBoolean(Boolean value) throws SQLException
    {
        setParam(booleanSetter, TYPE_BOOLEAN, value);
    }

    public void setBoolean(Boolean value, boolean condition) throws SQLException
    {
        setParam(booleanSetter, TYPE_BOOLEAN, value, condition);
    }

    public void setAllBoolean(Collection<Boolean> collection) throws SQLException
    {
        setAllParam(booleanSetter, TYPE_BOOLEAN, collection);
    }

    public void setAllBoolean(Collection<Boolean> collection, boolean condition) throws SQLException
    {
        setAllParam(booleanSetter, TYPE_BOOLEAN, collection, condition);
    }

    public void setTimestamp(Timestamp value) throws SQLException
    {
        setParam(timestampSetter, TYPE_TIMESTAMP, value);
    }

    public void setTimestamp(Timestamp value, boolean condition) throws SQLException
    {
        setParam(timestampSetter, TYPE_TIMESTAMP, value, condition);
    }

    public void setAllTimestamp(Collection<Timestamp> collection) throws SQLException
    {
        setAllParam(timestampSetter, TYPE_TIMESTAMP, collection);
    }

    public void setAllTimestamp(Collection<Timestamp> collection, boolean condition) throws SQLException
    {
        setAllParam(timestampSetter, TYPE_TIMESTAMP, collection, condition);
    }

    public <U, F extends Enum<?> & DBFilterProcessor<U>> void applyFilter(Class<F> filterProcessorType, U filter)
            throws SQLException
    {
        if(filter != null)
            for(F filterRule: filterProcessorType.getEnumConstants())
                filterRule.getParamSetter().doAction(this, filter);
    }

    public <U, F extends Enum<?> & DBFilterProcessor<U>> void applyFilters(Class<F> filterProcessorType, Collection<U> filters)
            throws SQLException
    {
        if(filters != null)
            for(U filter: filters)
                applyFilter(filterProcessorType, filter);
    }

    public <U, C,  F extends Enum<?> & DBFilterContextProcessor<U,C>> void applyFilter(Class<F> filterProcessorType, U filter, C context)
            throws SQLException
    {
        if(filter != null)
            for(F filterRule: filterProcessorType.getEnumConstants())
                filterRule.getParamSetter().doAction(this, filter, context);
    }

    public <U, C, F extends Enum<?> & DBFilterContextProcessor<U,C>> void applyFilters(Class<F> filterProcessorType, Collection<U> filters, C context)
            throws SQLException
    {
        if(filters != null)
            for(U filter: filters)
                applyFilter(filterProcessorType, filter, context);
    }

    public void addBatch() throws SQLException
    {
        statement.addBatch();
        paramIndex = 1;
    }
    
    @FunctionalInterface
    protected interface ParamSetter<T>
    {
        void setParam(PreparedStatement statement, int paramIndex, T value) throws SQLException;
    }
}
