package es.pedropareja.database.generic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    protected final PreparedStatement statement;
    protected int paramIndex = 1;

    public QueryParamsHelper(PreparedStatement statement)
    {
        this.statement = statement;
    }

    protected <T> void setParam(ParamSetter<T> paramSetter, T value) throws SQLException
    {
        if(value != null)
            paramSetter.setParam(statement, paramIndex++, value);
    }

    protected <T> void setParam(ParamSetter<T> paramSetter, T value, boolean condition) throws SQLException
    {
        if(condition)
            setParam(paramSetter, value);
    }

    protected <T> void setAllParam(ParamSetter<T> paramSetter, Collection<T> collection) throws SQLException
    {
        if(collection != null)
            for(T element: collection)
                setParam(paramSetter, element);
    }

    protected <T> void setAllParam(ParamSetter<T> paramSetter, Collection<T> collection, boolean condition) throws SQLException
    {
        if(condition)
            setAllParam(paramSetter, collection);
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

    public void setInt(Integer value) throws SQLException
    {
        setParam(intSetter, value);
    }

    public void setInt(Integer value, boolean condition) throws SQLException
    {
        setParam(intSetter, value, condition);
    }

    public void setAllInt(Collection<Integer> collection) throws SQLException
    {
        setAllParam(intSetter, collection);
    }

    public void setAllInt(Collection<Integer> collection, boolean condition) throws SQLException
    {
        setAllParam(intSetter, collection, condition);
    }

    public void setShort(Short value) throws SQLException
    {
        setParam(shortSetter, value);
    }

    public void setShort(Short value, boolean condition) throws SQLException
    {
        setParam(shortSetter, value, condition);
    }

    public void setAllShort(Collection<Short> collection) throws SQLException
    {
        setAllParam(shortSetter, collection);
    }

    public void setAllShort(Collection<Short> collection, boolean condition) throws SQLException
    {
        setAllParam(shortSetter, collection, condition);
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

    public void setBigDecimal(BigDecimal value) throws SQLException
    {
        setParam(bigDecimalSetter, value);
    }

    public void setBigDecimal(BigDecimal value, boolean condition) throws SQLException
    {
        setParam(bigDecimalSetter, value, condition);
    }

    public void setAllBigDecimal(Collection<BigDecimal> collection) throws SQLException
    {
        setAllParam(bigDecimalSetter, collection);
    }

    public void setAllBigDecimal(Collection<BigDecimal> collection, boolean condition) throws SQLException
    {
        setAllParam(bigDecimalSetter, collection, condition);
    }

    public void setBoolean(Boolean value) throws SQLException
    {
        setParam(booleanSetter, value);
    }

    public void setBoolean(Boolean value, boolean condition) throws SQLException
    {
        setParam(booleanSetter, value, condition);
    }

    public void setAllBoolean(Collection<Boolean> collection) throws SQLException
    {
        setAllParam(booleanSetter, collection);
    }

    public void setAllBoolean(Collection<Boolean> collection, boolean condition) throws SQLException
    {
        setAllParam(booleanSetter, collection, condition);
    }

    public void setTimestamp(Timestamp value) throws SQLException
    {
        setParam(timestampSetter, value);
    }

    public void setTimestamp(Timestamp value, boolean condition) throws SQLException
    {
        setParam(timestampSetter, value, condition);
    }

    public void setAllTimestamp(Collection<Timestamp> collection) throws SQLException
    {
        setAllParam(timestampSetter, collection);
    }

    public void setAllTimestamp(Collection<Timestamp> collection, boolean condition) throws SQLException
    {
        setAllParam(timestampSetter, collection, condition);
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
