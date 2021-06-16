package com.github.pedropareja.database.generic.querygen.merge;

import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGMergePrv extends QGQueryInit implements QGMerge
{
    private final DBTable table;

    public QGMergePrv(DBTable table)
    {
        this.table = table;
        setFullNamespaces();
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append("MERGE INTO ");
        printTablePath(stringBuilder, table, context);

        genOutputNext(stringBuilder, context);
    }
}
