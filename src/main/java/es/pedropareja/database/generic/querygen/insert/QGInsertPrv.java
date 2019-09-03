package es.pedropareja.database.generic.querygen.insert;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGInsertPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryInit implements QGInsert
{
    private final T[] fieldList;

    @SafeVarargs
    public QGInsertPrv(T ... fieldList)
    {
        this.fieldList = fieldList;
    }

    public T[] getFieldList()
    {
        return fieldList;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("INSERT (");

        for(int i=0; i < fieldList.length; i++)
            stringBuilder.append(i!= 0 ? ", " : "").append(fieldList[i].getName());

        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGInsertPrv))
            return false;

        return fieldArrayEquals(fieldList, ((QGInsertPrv<?>)q).fieldList);
    }
}
