package es.pedropareja.database.generic.querygen.insert;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.exceptions.QueryGenException;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.into.QGIntoPrv;

public class QGInsertPrv extends QGQueryInit implements QGInsert
{
    private final DBFieldInfo[] fieldList;

    @SafeVarargs
    public QGInsertPrv(DBFieldInfo ... fieldList)
    {
        this.fieldList = fieldList;
    }

    public DBFieldInfo[] getFieldList()
    {
        return fieldList;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("INSERT INTO ");

        if(next == null || !(next instanceof QGIntoPrv))
            throw new QueryGenException("QGInsert must be followed by QGInto");

        printTablePath(stringBuilder, ((QGIntoPrv)next).getTableType(), context);

        stringBuilder.append(" (");

        for(int i=0; i < fieldList.length; i++)
        {
            stringBuilder.append(i != 0 ? ", " : "");
            printField(stringBuilder, fieldList[i], false, context);
        }

        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }
}
