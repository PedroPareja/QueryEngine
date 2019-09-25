package es.pedropareja.database.generic.querygen.base;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.QueryGenConfig;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;

import java.util.List;

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
    public static <T extends DBFieldInfo, U> void printField(StringBuilder stringBuilder, T field, boolean fullNamespaces, U context)
    {
        if(fullNamespaces)
        {
            printTablePath(stringBuilder, (Class<T>)field.getClass(), context);
            stringBuilder.append(".");
        }

        printOptionalQuoted(stringBuilder, field.getName());
    }

    public static <T extends DBFieldInfo, U> void printTablePath(StringBuilder stringBuilder, Class<T> tableType, U context)
    {
        QGTableInfo tableInfo = QGTableInfo.getTableInfo(tableType, context);

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
    public boolean equals(Object obj)
    {
        if(obj == null || !(obj instanceof QGQueryBase))
            return false;

        return getInit().equalsQuery(((QGQueryBase) obj).getInit());
    }

    public boolean equalsQuery(QGQueryBase query)
    {
        if(query == null || !equalsUntilHere(query))
            return false;

        if(next == null)
            return query.next == null;

        return next.equalsQuery(query.next);
    }

    public abstract boolean equalsUntilHere(QGQueryBase q);

    public boolean equalsUntilConditionsElement(QGQueryBase q)
    {
        QGQueryBase qThis = this;
        QGQueryBase qOther = q;

        while(qThis != null)
        {
            if(qThis instanceof QGLinkConditions && qOther instanceof QGLinkConditions)
                return qThis.getClass() == qOther.getClass();

            if(!qThis.equalsUntilHere(qOther))
                return false;

            qThis = qThis.next;
            qOther = qOther.next;
        }

        return qOther == null;
    }

    public QGLinkConditions searchNextConditionsElement()
    {
        QGQueryBase qElement = this;

        while (qElement != null)
        {
            if(qElement instanceof QGLinkConditions)
                return (QGLinkConditions)qElement;

            qElement = qElement.next;
        }

        return null;
    }

    public boolean getNextOptionalAppearanceValueAndReset()
    {
        boolean result = nextOptionalAppearanceValue;
        nextOptionalAppearanceValue = true;
        return result;
    }

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
}
