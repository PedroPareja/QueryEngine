package com.github.pedropareja.database.generic.querygen.with.then;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGWithThenPrv extends QGQueryMiddleEnd
    implements QGWithThen
{
    private final QGQuery mainQuery;

    public QGWithThenPrv(QGQuery mainQuery, QGQueryInit init)
    {
        super(init);
        this.mainQuery = mainQuery;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" ");
        mainQuery.genExpressionOutput(stringBuilder, true, this, context);
    }
}
