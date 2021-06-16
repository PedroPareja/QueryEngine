package com.github.pedropareja.database.generic.querygen.into;

import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.insert.QGInsertPrv;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGIntoPrv extends QGQueryMiddleEnd implements QGInto
{
    private final DBTable table;

    public QGIntoPrv(DBTable table, QGQueryInit init)
    {
        super(init);
        this.table = table;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        if(next == null)
        {
            stringBuilder.append(" VALUES (");

            int numParams = ((QGInsertPrv) getInit()).getFieldList().length;
            for (int i = 0; i < numParams; i++)
                stringBuilder.append(i != 0 ? ", " : "").append("?");

            stringBuilder.append(")");
        }

        genOutputNext(stringBuilder, context);
    }

    public DBTable getTable()
    {
        return table;
    }
}
