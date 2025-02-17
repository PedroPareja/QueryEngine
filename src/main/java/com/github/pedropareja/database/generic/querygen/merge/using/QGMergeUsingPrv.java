package com.github.pedropareja.database.generic.querygen.merge.using;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGMergeUsingPrv extends QGQueryMiddleEnd implements QGMergeUsing
{
    private final QGQuery query;

    public QGMergeUsingPrv(QGQueryInit init, QGQuery query)
    {
        super(init);
        query.getInit().setFullNamespaces();
        this.query = query;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" USING (");
        query.getInit().genOutput(stringBuilder, context);
        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }
}
