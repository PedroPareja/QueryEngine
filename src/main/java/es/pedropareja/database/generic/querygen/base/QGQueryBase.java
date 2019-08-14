package es.pedropareja.database.generic.querygen.base;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTableInfo;
import es.pedropareja.database.generic.exceptions.QueryGenException;
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

    public abstract void genOutput(StringBuilder stringBuilder);

    public void genOutputChecked(StringBuilder stringBuilder)
    {
        if(optionalAppearanceValue)
            genOutput(stringBuilder);
    }

    protected void genOutputNext(StringBuilder stringBuilder)
    {
        if(next != null)
            next.genOutputChecked(stringBuilder);
    }

    public static void printField(StringBuilder stringBuilder, DBFieldInfo field, boolean fullNamespaces)
    {
        if(fullNamespaces)
        {
            printTablePath(stringBuilder, field.getClass());
            stringBuilder.append(".");
        }

        stringBuilder.append(field.getName());
    }

    public static void printTablePath(StringBuilder stringBuilder, Class<?> tableType)
    {
        DBTableInfo tableInfo = tableType.getAnnotation(DBTableInfo.class);

        if(tableInfo == null)
            throw new QueryGenException("DBTableInfo annotation not present in " + tableType.getName());

        if(!tableInfo.database().isEmpty())
            stringBuilder.append(tableInfo.database()).append(".");

        if(!tableInfo.schema().isEmpty())
            stringBuilder.append(tableInfo.schema()).append(".");

        stringBuilder.append(tableInfo.table());
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

    protected static <T extends Enum<?> & DBFieldInfo> boolean fieldArrayEquals(T[] fieldArray1, T[] fieldArray2)
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
