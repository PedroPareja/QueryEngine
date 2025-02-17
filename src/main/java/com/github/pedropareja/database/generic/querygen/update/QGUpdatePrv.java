package com.github.pedropareja.database.generic.querygen.update;

import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGUpdatePrv extends QGQueryInit implements QGUpdate
{
    private final DBTable table;

    public QGUpdatePrv(DBTable table)
    {
        this.table = table;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("UPDATE ");
        printTablePath(stringBuilder, table, context);

        genOutputNext(stringBuilder, context);
    }
}
