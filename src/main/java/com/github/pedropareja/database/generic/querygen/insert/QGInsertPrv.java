package com.github.pedropareja.database.generic.querygen.insert;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.exceptions.QueryGenException;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.into.QGIntoPrv;

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

        printTablePath(stringBuilder, ((QGIntoPrv)next).getTable(), context);

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
