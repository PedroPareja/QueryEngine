package com.github.pedropareja.database.generic.querygen.base;

import com.github.pedropareja.database.generic.DBTableAliasIndex;
import com.github.pedropareja.database.generic.querygen.QueryGenConfig;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.exceptions.QueryGenException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class QGQueryBase implements QGLinkBase, QGQuery
{
    protected QGQueryMiddleEnd next;
    protected boolean nextOptionalAppearanceValue = true;
    protected boolean optionalAppearanceValue = true;

    @Override
    public <T extends QGQueryMiddleEnd> T assignNext(T queryMiddleEnd)
    {
        queryMiddleEnd.setOptionalAppearanceValue(getNextOptionalAppearanceValueAndReset());
        next = queryMiddleEnd;
        return queryMiddleEnd;
    }

    public abstract <T> void genOutput(StringBuilder stringBuilder, T context);

    public <T> void genOutputChecked(StringBuilder stringBuilder, T context)
    {
        if(optionalAppearanceValue)
            genOutput(stringBuilder, context);
        else
            genOutputNext(stringBuilder, context);
    }

    protected <T> void genOutputNext(StringBuilder stringBuilder, T context)
    {
        if(next != null)
            next.genOutputChecked(stringBuilder, context);
    }

    @SuppressWarnings("unchecked")
    public static <U> void printField(StringBuilder stringBuilder, DBFieldInfo field, boolean fullNamespaces, U context)
    {
        if(fullNamespaces)
        {
            printTablePath(stringBuilder, field.getParentTable(), context);
            stringBuilder.append(".");
        }

        printOptionalQuoted(stringBuilder, field.getName());
    }

    public static <U> void printTablePath(StringBuilder stringBuilder, DBTable table, U context)
    {
        QGTableInfo tableInfo = QGTableInfo.getTableInfo(table, context);

        if(!tableInfo.getDatabase().isEmpty())
        {
            printOptionalQuoted(stringBuilder, tableInfo.getDatabase());
            stringBuilder.append(".");
        }

        if(!tableInfo.getSchema().isEmpty())
        {
            printOptionalQuoted(stringBuilder, tableInfo.getSchema());
            stringBuilder.append(".");
        }

        printOptionalQuoted(stringBuilder, tableInfo.getTable());
    }

    public static void printOptionalQuoted(StringBuilder stringBuilder, String value)
    {
        if(QueryGenConfig.QUOTED_FIELDS)
            stringBuilder.append("\"");

        stringBuilder.append(value);

        if(QueryGenConfig.QUOTED_FIELDS)
            stringBuilder.append("\"");
    }


    @Override
    public boolean getNextOptionalAppearanceValueAndReset()
    {
        boolean result = nextOptionalAppearanceValue;
        nextOptionalAppearanceValue = true;
        return result;
    }

    @Override
    public void setNextOptionalAppearanceValue(boolean nextOptionalAppearanceValue)
    {
        this.nextOptionalAppearanceValue = nextOptionalAppearanceValue;
    }

    protected static <T extends DBFieldInfo> boolean fieldArrayEquals(T[] fieldArray1, T[] fieldArray2)
    {
        if(fieldArray1 == fieldArray2)
            return true;

        if(fieldArray1 == null || fieldArray2 == null || fieldArray1.length != fieldArray2.length)
            return false;

        for(int i=0; i < fieldArray1.length; i++)
            if(!fieldArray1[i].equalsField(fieldArray2[i]))
                return false;

        return true;
    }

    protected static boolean conditionListEquals(List<QGConditionBase> conditionList1, List<QGConditionBase> conditionList2)
    {
        if(conditionList1 == conditionList2)
            return true;

        if(conditionList1 == null || conditionList2 == null || conditionList1.size() != conditionList2.size())
            return false;

        for(int i=0; i < conditionList1.size(); i++)
            if(!conditionList1.get(i).equals(conditionList2.get(i)))
                return false;

        return true;
    }

    public static <T> List<T> joinLists(List<T> list1, List<T> list2)
    {
        if(list1 == null)
            return list2;

        if(list2 == null)
            return list1;

        List<T> result = new ArrayList<>(list1);
        result.addAll(list2);
        return result;
    }

    public static <T> boolean equalsElements(T e1, T e2)
    {
        return Objects.equals(e1, e2);
    }

    public static DBTable getTableInstance(Class<? extends DBTable> tableType)
    {
        try
        {
            return (DBTable) tableType.getMethod("getInstance").invoke(null, new Object[0]);
        }
        catch (Exception e) {}

        if(tableType.isEnum())
            return tableType.getEnumConstants()[0].getFields()[0].getParentTable();

        throw new QueryGenException("Table type '" + tableType.getName() + "' is not an enum nor implement static method getInstance. Fix it or use an instance of the table instead of the type");
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(fullNamespaces)
            getInit().setFullNamespaces();

        getInit().genOutput(stringBuilder, context);
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return null;
    }

    @Override
    public boolean isComplex()
    {
        return true;
    }

    public abstract DBTableAliasIndex getTableAliasIndex();
    protected abstract void setTableAliasIndex(DBTableAliasIndex tableAliasIndex);
}
